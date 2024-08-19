package de.daver.build.hub.core.gui;

import de.daver.build.hub.api.gui.Gui;
import de.daver.build.hub.api.gui.GuiBuilder;
import de.daver.build.hub.api.gui.GuiType;
import de.daver.build.hub.api.gui.GuiAction;
import de.daver.build.hub.core.gui.action.GuiCloseAction;
import de.daver.build.hub.core.gui.action.GuiSwitchAction;
import de.daver.build.hub.core.gui.action.PageSwitchAction;
import de.daver.build.hub.api.gui.event.GuiCloseEvent;
import de.daver.build.hub.api.gui.event.GuiEvent;
import de.daver.build.hub.api.gui.event.GuiOpenEvent;
import de.daver.build.hub.api.gui.GuiLayout;
import de.daver.build.hub.api.item.Item;
import de.daver.build.hub.api.util.ClickType;

import java.util.List;
import java.util.function.Supplier;

public class GuiBuilderImpl implements GuiBuilder {

    private GuiBuilderImpl(GuiType type) {

    }

    public GuiBuilderImpl title(String title) {
        return this;
    }

    public GuiBuilderImpl id(String id) {
        return this;
    }

    public GuiBuilderImpl staticItem(int slot, Item item) {
        return this;
    }

    public GuiBuilderImpl applyLayout(GuiLayout layout) {
        return this;
    }

    public GuiBuilderImpl addInteraction(int slot, GuiAction action, ClickType... clickTypes) {
        return this;
    }

    public GuiBuilderImpl addGuiSwitch(int slot, String guiId, ClickType... clickTypes) {
        return addInteraction(slot, new GuiSwitchAction(guiId), clickTypes);
    }

    public GuiBuilderImpl addPageSwitch(int slot, boolean increase, ClickType... clickTypes) {
        return addInteraction(slot, new PageSwitchAction(increase), clickTypes);
    }

    public GuiBuilderImpl addPageSwitch(int slot) {
        return addInteraction(slot, new PageSwitchAction());
    }

    public GuiBuilderImpl closeOn(int slot) {
        return addInteraction(slot, new GuiCloseAction());
    }

    public GuiBuilderImpl addEvent(GuiEvent event) {
        return this;
    }

    public GuiBuilderImpl addOpenEvent(GuiOpenEvent event) {
        return addEvent(event);
    }

    public GuiBuilderImpl addCloseEvent(GuiCloseEvent event) {
        return addEvent(event);
    }

    public GuiBuilderImpl accessible(int...slots) {
        return this;
    }

    public GuiBuilderImpl dynamicItems(Supplier<List<Item>> itemSupplier) {
        return this;
    }

    public Gui build() {
        return null;
    }


    public static GuiBuilderImpl create(int rows) {
        return new GuiBuilderImpl(GuiType.getByRows(rows));
    }

    public static GuiBuilderImpl create(GuiType type) {
        return new GuiBuilderImpl(type);
    }
}
