package de.daver.build.hub.core.gui.layout;

import de.daver.build.hub.api.gui.GuiBuilder;
import de.daver.build.hub.api.gui.GuiLayout;
import de.daver.build.hub.api.gui.GuiType;
import de.daver.build.hub.api.item.Item;

public class TopBottomLayout implements GuiLayout {

    private final Item bottom;
    private final Item top;

    public TopBottomLayout(Item item) {
        this(item, item);
    }

    public TopBottomLayout(Item topBar, Item bottomBar) {
        this.top = topBar;
        this.bottom = bottomBar;
    }

    @Override
    public void apply(GuiType type, GuiBuilder builder) {
        //Top
        for(int i = 0; i < 9; i++) builder.staticItem(i, top);
        //Bottom
        for(int i = 0; i < 9; i++) builder.staticItem(i + type.getSlotCount() - 10, this.bottom);
    }
}
