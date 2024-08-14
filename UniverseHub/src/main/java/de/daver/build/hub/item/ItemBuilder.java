package de.daver.build.hub.item;

import de.daver.build.universe.util.ClickType;

public class ItemBuilder {

    private ItemBuilder(Material material) {

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

    public static ItemBuilder create(Material material) {
        return new ItemBuilder(material);
    }

}
