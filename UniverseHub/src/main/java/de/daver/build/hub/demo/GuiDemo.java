package de.daver.build.hub.demo;

import de.daver.build.hub.api.gui.Gui;
import de.daver.build.hub.api.gui.GuiBuilder;
import de.daver.build.hub.core.gui.layout.TopBarLayout;
import de.daver.build.hub.core.gui.layout.TopBottomLayout;
import de.daver.build.hub.api.gui.GuiType;
import de.daver.build.hub.core.gui.layout.BorderLayout;
import de.daver.build.hub.core.gui.layout.BottomBarLayout;
import de.daver.build.hub.api.item.Item;
import de.daver.build.hub.api.util.ClickType;
import de.daver.build.hub.api.util.User;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class GuiDemo {

    @SuppressWarnings("unused")
    public void demo(User user, Item item) {
        //Basic Chest Gui with Border Layout
        @SuppressWarnings("unused")
        Gui g1 = GuiBuilder.create(6)
                .title("Loot Chest")
                .layout(new BorderLayout(item))
                .dynamicItems(ArrayList::new)
                .build();

        //Anvil Gui with close Action
        @SuppressWarnings("unused")
        Gui g2 = GuiBuilder.create(GuiType.ANVIL)
                .layout(new TopBottomLayout(item))
                .staticItem(0, item)
                .closeOn(0)
                .build();

        //This chest gui can switch through pages
        @SuppressWarnings("unused")
        Gui g3 = GuiBuilder.create(6)
                .id("lootedItems")
                .layout(new BottomBarLayout(item))
                .closeOn(49)
                .staticItem(48, item)
                .addPageSwitch(48, false, ClickType.SHIFTED_LEFT, ClickType.LEFT)
                .staticItem(50, item)
                .addPageSwitch(50, true)
                .build();

        //This Gui can switch to another gui
        @SuppressWarnings("unused")
        Gui g4 = GuiBuilder.create(4)
                .layout(new TopBottomLayout(item, item))
                .staticItem(4, item)
                .addGuiSwitch(4, "lootedItems")
                .staticItem(0, item)
                .addPageSwitch(0)
                .accessible(1, 3)
                .addEvent(null)
                .build();

        Gui g5 = GuiBuilder.create(GuiType.BREWING_STAND)
                .layout(new TopBarLayout(item))
                .addOpenEvent(this::demoEvent)
                .addCloseEvent(this::demoEvent)
                .build();

        g5.open(user);
        g5.setTitle(g5.getTitle() + "-V2");
        g5.update(user);
    }

    public void demoEvent(Gui g, User p) {
        p.send("Event!");
    }
}
