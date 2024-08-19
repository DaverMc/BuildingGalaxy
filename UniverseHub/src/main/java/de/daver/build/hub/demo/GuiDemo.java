package de.daver.build.hub.demo;

import de.daver.build.hub.gui.layout.TopBottomLayout;
import de.daver.build.hub.gui.Gui;
import de.daver.build.hub.gui.GuiBuilder;
import de.daver.build.hub.gui.GuiType;
import de.daver.build.hub.gui.layout.BorderLayout;
import de.daver.build.hub.gui.layout.BottomBarLayout;
import de.daver.build.hub.item.Item;
import de.daver.build.hub.util.ClickType;
import de.daver.build.hub.util.User;

public class GuiDemo {

    Item item = null;

    public void demo() {
        //Basic Chest Gui with Border Layout
        Gui g1 = GuiBuilder.create(6)
                .title("Loot Chest")
                .applyLayout(new BorderLayout(item))
                .build();

        //Anvil Gui with close Action
        Gui g2 = GuiBuilder.create(GuiType.ANVIL)
                .staticItem(0, item)
                .closeOn(0)
                .build();

        //This chest gui can switch through pages
        Gui g3 = GuiBuilder.create(6)
                .id("lootedItems")
                .applyLayout(new BottomBarLayout(item))
                .closeOn(49)
                .staticItem(48, item)
                .addPageSwitch(48, false, ClickType.SHIFTED_LEFT, ClickType.LEFT)
                .staticItem(50, item)
                .addPageSwitch(50, true)
                .build();

        //This Gui can switch to another gui
        Gui g4 = GuiBuilder.create(4)
                .applyLayout(new TopBottomLayout(item, item))
                .staticItem(4, item)
                .addGuiSwitch(4, "lootedItems")
                .staticItem(0, item)
                .addPageSwitch(0)
                .accessable(1, 3)
                .build();

        Gui g5 = GuiBuilder.create(GuiType.BREWING_STAND)
                .addOpenEvent(this::demoEvent)
                .addCloseEvent(this::demoEvent)
                .build();

    }

    public void demoEvent(Gui g, User p) {
        p.sendMessage("Event!");
    }
}
