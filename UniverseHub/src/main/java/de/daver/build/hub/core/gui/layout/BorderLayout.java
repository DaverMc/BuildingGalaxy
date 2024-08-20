package de.daver.build.hub.core.gui.layout;

import de.daver.build.hub.api.gui.GuiBuilder;
import de.daver.build.hub.api.gui.GuiLayout;
import de.daver.build.hub.api.gui.GuiType;
import de.daver.build.hub.api.item.Item;

public class BorderLayout implements GuiLayout {

    private final Item item;

    public BorderLayout(Item borderItem) {
        this.item = borderItem;
    }

    @Override
    public void apply(GuiType type, GuiBuilder builder) {
        //Top
        for(int i = 0; i < 9; i++) builder.staticItem(i, item);
        //Bottom
        for(int i = 0; i < 9; i++) builder.staticItem(i + type.getSlotCount() - 10, item);
        int rows = type.getSlotCount() / 9;
        //Middle
        for(int i = 0; i < rows; i++) {
            //Left
            builder.staticItem(9 + i * 9, item);
            //Right
            builder.staticItem(17 + i * 9, item);
        }

    }
}
