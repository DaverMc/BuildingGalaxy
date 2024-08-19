package de.daver.build.hub.item;

import de.daver.build.hub.api.item.Item;
import de.daver.build.hub.api.item.ItemAction;
import de.daver.build.hub.api.item.ItemBuilder;
import de.daver.build.hub.api.item.Material;
import de.daver.build.hub.api.util.Enchantment;
import de.daver.build.hub.util.ClickType;

public class ItemBuilderImpl implements ItemBuilder{

    public ItemBuilderImpl(Material material) {

    }

    public ItemBuilder displayName(String displayName) {
        return this;
    }

    public ItemBuilder addLoreLine(String loreLine) {
        return this;
    }

    //Makes it enchanted but without being really enchanted only the glow is applied
    public ItemBuilder glow() {
        return this;
    }

    public ItemBuilder enchant(Enchantment enchantment) {
        return this;
    }

    public ItemBuilder addAction(ClickType clickType, ItemAction action) {
        return this;
    }

    public Item build() {
        return null;
    }

}
