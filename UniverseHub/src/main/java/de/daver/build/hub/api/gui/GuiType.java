package de.daver.build.hub.api.gui;

public enum GuiType {

    // Chest GUIs
    CHEST1(9, "chest", 0),
    CHEST2(18, "chest", 0),
    CHEST3(27, "chest", 0), // Wird auch für Endertruhen und Shulkerboxen verwendet
    CHEST4(36, "chest", 0),
    CHEST5(45, "chest", 0),
    CHEST6(54, "chest", 0),

    // Specialized GUIs
    ANVIL(3, "anvil", 9),
    BEACON(1, "beacon", 10),
    BREWING_STAND(5, "brewing", 6),
    DISPENSER(9, "dispenser", 1),
    DROPPER(9, "dropper", 2), // Verwenden denselben Typ wie Dispenser
    ENCHANTING_TABLE(2, "enchanting", 5),
    FURNACE(3, "furnace", 3),
    HOPPER(5, "hopper", 11),
    MERCHANT(3, "merchant", 12),
    SHULKER_BOX(27, "shulker_box", 13),
    SMITHING(3, "smithing", 21),
    STONECUTTER(1, "stonecutter", 19),
    ENDER_CHEST(27, "ender_chest", 22);

    private final int slotCount;
    private final String name;
    private final int id;

    GuiType(final int slotCount, final String name, final int id) {
        this.slotCount = slotCount;
        this.name = name;
        this.id = id;
    }

    public int getSlotCount() {
        return this.slotCount;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public static GuiType getByRows(int rows) {
        if (rows < 1 || rows > 6) throw new IllegalArgumentException("rows must be between 1 and 6");
        return GuiType.values()[rows - 1];
    }
}
