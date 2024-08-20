package de.daver.build.hub.core.item;

import de.daver.build.hub.api.item.Enchantment;
import de.daver.build.hub.api.item.EnchantmentType;

public record EnchantmentImpl(EnchantmentType type, int level) implements Enchantment {
}
