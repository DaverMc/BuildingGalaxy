package de.daver.build.hub.core.item;

import de.daver.build.hub.api.item.Enchantment;
import de.daver.build.hub.api.item.EnchantmentBuilder;
import de.daver.build.hub.api.item.EnchantmentType;

public class EnchantmentBuilderImpl implements EnchantmentBuilder {

    private final EnchantmentType type;
    private int level;

    public EnchantmentBuilderImpl(EnchantmentType type) {
        this.type = type;
    }

    @Override
    public EnchantmentBuilder level(int level) {
        this.level = level;
        return this;
    }

    @Override
    public Enchantment build() {
        return new EnchantmentImpl(type, level);
    }
}
