package de.daver.build.hub.api.util;

public interface EnchantmentBuilder {

    static EnchantmentBuilder create(String id) {
        return null;
    }

    EnchantmentBuilder level(int level);

    Enchantment build();

}
