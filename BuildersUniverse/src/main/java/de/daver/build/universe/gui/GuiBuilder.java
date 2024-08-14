package de.daver.build.universe.gui;

import de.daver.build.universe.gui.action.GuiAction;
import de.daver.build.universe.gui.action.GuiCloseAction;
import de.daver.build.universe.gui.action.GuiSwitchAction;
import de.daver.build.universe.gui.action.PageSwitchAction;
import de.daver.build.universe.gui.layout.GuiLayout;
import de.daver.build.universe.item.Item;

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

    public GuiBuilder addInteraction(int slot, GuiAction action) {
        return this;
    }

    public GuiBuilder addGuiSwitch(int slot, String guiId) {
        return addInteraction(slot, new GuiSwitchAction(guiId));
    }

    public GuiBuilder addPageSwitch(int slot, boolean increase) {
        return addInteraction(slot, new PageSwitchAction(increase));
    }

    public GuiBuilder closeOn(int slot) {
        return addInteraction(slot, new GuiCloseAction());
    }

    public Gui build() {
        return null;
    }

}
