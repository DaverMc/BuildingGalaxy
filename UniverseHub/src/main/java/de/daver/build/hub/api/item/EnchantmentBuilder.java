package de.daver.build.hub.api.item;

import de.daver.build.hub.core.item.EnchantmentBuilderImpl;

public interface EnchantmentBuilder {

    static EnchantmentBuilder create(EnchantmentType type) {
        return new EnchantmentBuilderImpl(type);
    }

    EnchantmentBuilder level(int level);

    Enchantment build();

}
