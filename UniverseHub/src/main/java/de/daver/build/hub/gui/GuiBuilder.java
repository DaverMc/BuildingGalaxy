package de.daver.build.hub.gui;

import de.daver.build.hub.gui.action.GuiAction;
import de.daver.build.hub.gui.action.GuiCloseAction;
import de.daver.build.hub.gui.action.GuiSwitchAction;
import de.daver.build.hub.gui.action.PageSwitchAction;
import de.daver.build.hub.gui.event.GuiCloseEvent;
import de.daver.build.hub.gui.event.GuiEvent;
import de.daver.build.hub.gui.event.GuiOpenEvent;
import de.daver.build.hub.gui.layout.GuiLayout;
import de.daver.build.hub.item.Item;
import de.daver.build.hub.util.ClickType;

public class GuiBuilder {

    private GuiBuilder(GuiType type) {

    }

    public GuiBuilder title(String title) {
        return this;
    }

    public GuiBuilder id(String id) {
        return this;
    }

    public GuiBuilder staticItem(int slot, Item item) {
        return this;
    }

    public GuiBuilder applyLayout(GuiLayout layout) {
        return this;
    }

    public GuiBuilder addInteraction(int slot, GuiAction action, ClickType... clickTypes) {
        return this;
    }

    public GuiBuilder addGuiSwitch(int slot, String guiId, ClickType... clickTypes) {
        return addInteraction(slot, new GuiSwitchAction(guiId), clickTypes);
    }

    public GuiBuilder addPageSwitch(int slot, boolean increase, ClickType... clickTypes) {
        return addInteraction(slot, new PageSwitchAction(increase), clickTypes);
    }

    public GuiBuilder addPageSwitch(int slot) {
        return addInteraction(slot, new PageSwitchAction());
    }

    public GuiBuilder closeOn(int slot) {
        return addInteraction(slot, new GuiCloseAction());
    }

    public GuiBuilder addEvent(GuiEvent event) {
        return this;
    }

    public GuiBuilder addOpenEvent(GuiOpenEvent event) {
        return addEvent(event);
    }

    public GuiBuilder addCloseEvent(GuiCloseEvent event) {
        return addEvent(event);
    }

    public Gui build() {
        return null;
    }


    public static GuiBuilder create(int rows) {
        return new GuiBuilder(GuiType.getByRows(rows));
    }

    public static GuiBuilder create(GuiType type) {
        return new GuiBuilder(type);
    }
}
