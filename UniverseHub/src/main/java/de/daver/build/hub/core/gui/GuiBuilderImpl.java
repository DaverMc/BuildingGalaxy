package de.daver.build.hub.core.gui;

import de.daver.build.hub.api.gui.*;
import de.daver.build.hub.api.gui.event.GuiCloseEvent;
import de.daver.build.hub.api.gui.event.GuiEvent;
import de.daver.build.hub.api.gui.event.GuiOpenEvent;
import de.daver.build.hub.api.item.Item;
import de.daver.build.hub.api.util.ClickType;
import de.daver.build.hub.core.gui.action.GuiCloseAction;
import de.daver.build.hub.core.gui.action.GuiSwitchAction;
import de.daver.build.hub.core.gui.action.PageSwitchAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GuiBuilderImpl implements GuiBuilder {

    private final Map<Class<? extends GuiEvent>, GuiEvent> guiEvents;
    private final GuiType type;
    private final SlotImpl[] slots;
    private String id;
    private String title;
    private Supplier<List<Item>> dyanmicItemSupplier;

    public GuiBuilderImpl(GuiType type) {
        this.type = type;
        this.slots = new SlotImpl[type.getSlotCount()];
        for (int i = 0; i < type.getSlotCount(); i++) slots[i] = new SlotImpl();
        this.guiEvents = new HashMap<>();
    }

    @Override
    public GuiBuilder id(String id) {
        this.id = id;
        return this;
    }

    @Override
    public GuiBuilder title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public GuiBuilder layout(GuiLayout layout) {
        layout.apply(this.type,this);
        return this;
    }

    @Override
    public GuiBuilder staticItem(int position, Item item) {
        Slot slot = this.slots[position];
        slot.setItem(item);
        slot.setLocked(true);
        return this;
    }

    @Override
    public GuiBuilder addInteraction(int position, GuiAction action, ClickType... clickTypes) {
        Slot slot = this.slots[position];
        for(ClickType clickType : clickTypes) slot.addAction(clickType, action);
        return this;
    }

    @Override
    public GuiBuilder addGuiSwitch(int position, String guiId, ClickType... clickTypes) {
        return addInteraction(position, new GuiSwitchAction(guiId), clickTypes);
    }

    @Override
    public GuiBuilder addPageSwitch(int position, boolean increase, ClickType... clickTypes) {
        return addInteraction(position, new PageSwitchAction(increase), clickTypes);
    }

    @Override
    public GuiBuilder addPageSwitch(int position) {
        return addInteraction(position, new PageSwitchAction());
    }

    @Override
    public GuiBuilder closeOn(int position) {
        return addInteraction(position, new GuiCloseAction());
    }

    @Override
    public GuiBuilder addEvent(GuiEvent event) {
        guiEvents.put(event.getClass(), event);
        return this;
    }

    @Override
    public GuiBuilder addOpenEvent(GuiOpenEvent event) {
        return addEvent(event);
    }

    @Override
    public GuiBuilder addCloseEvent(GuiCloseEvent event) {
        return addEvent(event);
    }

    @Override
    public GuiBuilder accessible(int... slots) {
        for(int slot : slots) this.slots[slot].setAccessible(true);
        return this;
    }

    @Override
    public GuiBuilder dynamicItems(Supplier<List<Item>> itemSupplier) {
        this.dyanmicItemSupplier = itemSupplier;
        return this;
    }

    @Override
    public Gui build() {
        return new GuiImpl(this.type, this.slots,
                this.title, this.id,
                this.dyanmicItemSupplier, this.guiEvents);
    }
}
