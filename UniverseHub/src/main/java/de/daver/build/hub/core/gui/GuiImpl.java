package de.daver.build.hub.core.gui;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.gui.Gui;
import de.daver.build.hub.api.gui.GuiType;
import de.daver.build.hub.api.gui.Slot;
import de.daver.build.hub.api.gui.event.GuiEvent;
import de.daver.build.hub.api.item.Item;
import de.daver.build.hub.api.util.User;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GuiImpl implements Gui {

    private final Map<Class<? extends GuiEvent>, GuiEvent> guiEvents;
    private final Slot[] slots;
    private final GuiType type;
    private final Supplier<List<Item>> dynamicItemSupplier;
    private String title;

    public GuiImpl(GuiType type, Slot[] slots,
                   String title, String id,
                   Supplier<List<Item>> dynamicItemSupplier, Map<Class<? extends GuiEvent>, GuiEvent> guiEvents) {
        this.type = type;
        this.dynamicItemSupplier = dynamicItemSupplier;
        this.slots = slots;
        this.guiEvents = guiEvents;
        this.title = title;
        if(id == null) UniverseHub.getGuiManager().addUnregisteredGui(this);
        else UniverseHub.getGuiManager().registerGui(id, this);
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean setItem(int slot, Item item) {
        return this.slots[slot].setItem(item);
    }

    @Override
    public void fillItems(List<Item> items) {
        int index = 0;
        for (Slot slot : slots) {
            if(index >= items.size()) return;
            if (slot.setItem(items.get(index))) index++;
        }
    }

    @Override
    public void setAccessible(int slot, boolean accessible) {
        this.slots[slot].setAccessible(accessible);
    }

    @Override
    public void setLocked(int slot, boolean locked) {
        this.slots[slot].setLocked(locked);
    }

    @Override
    public void open(User user) {
        if(dynamicItemSupplier.get() != null) fillItems(dynamicItemSupplier.get());
        UniverseHub.gate().getGuiConnection().openGui(user, this);
        UniverseHub.getGuiManager().setOpenGui(user, this);
    }

    @Override
    public void update(User user) {
        UniverseHub.gate().getGuiConnection().updateGui(user, this);
    }

    @Override
    public void close(User user) {
        UniverseHub.gate().getGuiConnection().closeGui(user);
        UniverseHub.getGuiManager().removeOpenGui(user);
    }

    @Override
    public <E extends GuiEvent> E getEvent(Class<E> eventClass) {
        return eventClass.cast(guiEvents.get(eventClass));
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public Item getItem(int slot) {
        return this.slots[slot].getItem();
    }

    @Override
    public Slot[] getSlots() {
        return this.slots;
    }

    @Override
    public GuiType getType() {
        return this.type;
    }

}
