package de.daver.build.universe.gui;

import de.daver.build.universe.gui.action.GuiAction;
import de.daver.build.universe.gui.action.GuiCloseAction;
import de.daver.build.universe.gui.action.GuiSwitchAction;
import de.daver.build.universe.gui.action.PageSwitchAction;
import de.daver.build.universe.gui.layout.GuiLayout;
import de.daver.build.universe.item.Item;
import de.daver.build.universe.util.ClickType;

public class GuiBuilder {

    private GuiBuilder(String id) {

    }

    public static GuiBuilder create(String id) {
        return new GuiBuilder(id);
    }

    public GuiBuilder title(String title) {
        return this;
    }

    public GuiBuilder type(GuiType type) {
        return this;
    }

    public GuiBuilder rows(int rows) {
        return this;
    }

    public GuiBuilder staticItem(int slot, Item item) {
        return this;
    }

    //By default, every slot can be filled dynamical
    public GuiBuilder dynamicItem(int slot, Item item) {
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

    public Gui build() {
        return null;
    }

}
