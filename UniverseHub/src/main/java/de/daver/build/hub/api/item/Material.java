package de.daver.build.hub.api.item;

import de.daver.build.hub.api.util.MinecraftVersion;

public enum Material {

    // Basic building blocks
    STONE("stone", 1, MinecraftVersion.RELEASE_1_0),
    GRASS_BLOCK("grass_block", 2, MinecraftVersion.RELEASE_1_0),
    DIRT("dirt", 3, MinecraftVersion.RELEASE_1_0),
    COBBLESTONE("cobblestone", 4, MinecraftVersion.RELEASE_1_0),
    WOOD_PLANKS("planks", 5, MinecraftVersion.RELEASE_1_0),
    SAND("sand", 12, MinecraftVersion.RELEASE_1_0),
    GRAVEL("gravel", 13, MinecraftVersion.RELEASE_1_0),
    GOLD_ORE("gold_ore", 14, MinecraftVersion.RELEASE_1_0),
    IRON_ORE("iron_ore", 15, MinecraftVersion.RELEASE_1_0),
    COAL_ORE("coal_ore", 16, MinecraftVersion.RELEASE_1_0),

    // Wood types
    OAK_LOG("oak_log", 17, MinecraftVersion.RELEASE_1_0),
    SPRUCE_LOG("spruce_log", 17, MinecraftVersion.RELEASE_1_2_1),
    BIRCH_LOG("birch_log", 17, MinecraftVersion.RELEASE_1_2_1),
    JUNGLE_LOG("jungle_log", 17, MinecraftVersion.RELEASE_1_2_1),
    ACACIA_LOG("acacia_log", 162, MinecraftVersion.RELEASE_1_7_2),
    DARK_OAK_LOG("dark_oak_log", 162, MinecraftVersion.RELEASE_1_7_2),

    // Crafting materials
    IRON_INGOT("iron_ingot", 265, MinecraftVersion.RELEASE_1_0),
    GOLD_INGOT("gold_ingot", 266, MinecraftVersion.RELEASE_1_0),
    DIAMOND("diamond", 264, MinecraftVersion.RELEASE_1_0),
    STICK("stick", 280, MinecraftVersion.RELEASE_1_0),

    // Tools
    WOODEN_PICKAXE("wooden_pickaxe", 270, MinecraftVersion.RELEASE_1_0),
    STONE_PICKAXE("stone_pickaxe", 274, MinecraftVersion.RELEASE_1_0),
    IRON_PICKAXE("iron_pickaxe", 257, MinecraftVersion.RELEASE_1_0),
    GOLDEN_PICKAXE("golden_pickaxe", 285, MinecraftVersion.RELEASE_1_0),
    DIAMOND_PICKAXE("diamond_pickaxe", 278, MinecraftVersion.RELEASE_1_0),

    // Food items
    APPLE("apple", 260, MinecraftVersion.RELEASE_1_0),
    MUSHROOM_STEW("mushroom_stew", 282, MinecraftVersion.RELEASE_1_0),
    BREAD("bread", 297, MinecraftVersion.RELEASE_1_0),
    PORKCHOP("porkchop", 319, MinecraftVersion.RELEASE_1_0),
    COOKED_PORKCHOP("cooked_porkchop", 320, MinecraftVersion.RELEASE_1_0),

    // Special blocks
    OBSIDIAN("obsidian", 49, MinecraftVersion.RELEASE_1_0),
    NETHERRACK("netherrack", 87, MinecraftVersion.RELEASE_1_0),
    SOUL_SAND("soul_sand", 88, MinecraftVersion.RELEASE_1_0),
    GLOWSTONE("glowstone", 89, MinecraftVersion.RELEASE_1_0),

    // Redstone-related
    REDSTONE("redstone", 331, MinecraftVersion.RELEASE_1_0),
    REDSTONE_TORCH("redstone_torch", 76, MinecraftVersion.RELEASE_1_0),
    LEVER("lever", 69, MinecraftVersion.RELEASE_1_0),
    STONE_BUTTON("stone_button", 77, MinecraftVersion.RELEASE_1_0),
    WOODEN_BUTTON("wooden_button", 143, MinecraftVersion.RELEASE_1_2_1),

    // Ores
    LAPIS_LAZULI("lapis_lazuli", 351, MinecraftVersion.RELEASE_1_0),
    EMERALD("emerald", 388, MinecraftVersion.RELEASE_1_3_1),

    // Nether Update
    NETHERITE_INGOT("netherite_ingot", -1, MinecraftVersion.RELEASE_1_16),
    ANCIENT_DEBRIS("ancient_debris", -1, MinecraftVersion.RELEASE_1_16),

    // Caves and Cliffs Update
    COPPER_INGOT("copper_ingot", -1, MinecraftVersion.RELEASE_1_17),
    AMETHYST_SHARD("amethyst_shard", -1, MinecraftVersion.RELEASE_1_17),

    // Miscellaneous
    EGG("egg", 344, MinecraftVersion.RELEASE_1_0),
    SNOWBALL("snowball", 332, MinecraftVersion.RELEASE_1_0),
    BONE("bone", 352, MinecraftVersion.RELEASE_1_0),
    STRING("string", 287, MinecraftVersion.RELEASE_1_0);

    private final String id;
    private final int oldId;
    private final MinecraftVersion since;

    Material(String id, int oldId, MinecraftVersion since) {
        this.id = id;
        this.oldId = oldId;
        this.since = since;
    }

    public String getId() {
        return id;
    }

    public int getOldId() {
        return oldId;
    }

    public MinecraftVersion getSince() {
        return since;
    }
}

