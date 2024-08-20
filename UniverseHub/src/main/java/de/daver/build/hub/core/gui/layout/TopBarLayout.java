package de.daver.build.hub.core.gui.layout;

import de.daver.build.hub.api.gui.GuiBuilder;
import de.daver.build.hub.api.gui.GuiLayout;
import de.daver.build.hub.api.gui.GuiType;
import de.daver.build.hub.api.item.Item;

public class TopBarLayout implements GuiLayout {

    private final Item item;

    public TopBarLayout(Item barItem) {
        this.item = barItem;
    }

    @Override
    public void apply(GuiType type, GuiBuilder builder) {
        for(int i = 0; i < 9; i++) builder.staticItem(i, item);
    }
}
