package de.daver.build.hub.core.gui.layout;

import de.daver.build.hub.api.gui.*;
import de.daver.build.hub.api.item.Item;

public class BottomBarLayout implements GuiLayout {

    private final Item item;

    public BottomBarLayout(Item barItem) {
        this.item = barItem;
    }

    @Override
    public void apply(GuiType type, GuiBuilder builder) {
        for(int i = 0; i < 9; i++) builder.staticItem(i + type.getSlotCount() - 10, item);
    }
}
