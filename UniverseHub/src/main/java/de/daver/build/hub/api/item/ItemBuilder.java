package de.daver.build.hub.api.item;

import de.daver.build.hub.core.item.ItemBuilderImpl;
import de.daver.build.hub.api.util.ClickType;

public interface ItemBuilder {

     ItemBuilder displayName(String displayName);
     ItemBuilder addLoreLine(String loreLine);
    //Makes it enchanted but without being really enchanted only the glow is applied
     ItemBuilder glow();
     ItemBuilder enchant(Enchantment enchantment);
     ItemBuilder addAction(ClickType clickType, ItemAction action);
     Item build();

    static ItemBuilder create(Material material) {
        return new ItemBuilderImpl(material);
    }

}
