package de.daver.build.universe.gui;

import org.bukkit.event.inventory.InventoryType;

public enum GuiType {

    // Chest GUIs
    CHEST1(9, InventoryType.CHEST),
    CHEST2(18, InventoryType.CHEST),
    CHEST3(27, InventoryType.CHEST), // Wird auch für Endertruhen und Shulkerboxen verwendet
    CHEST4(36, InventoryType.CHEST),
    CHEST5(45, InventoryType.CHEST),
    CHEST6(54, InventoryType.CHEST),

    // Specialized GUIs
    ANVIL(3, InventoryType.ANVIL),
    BEACON(1, InventoryType.BEACON),
    BREWING_STAND(5, InventoryType.BREWING),
    DISPENSER(9, InventoryType.DISPENSER),
    DROPPER(9, InventoryType.DISPENSER), // Verwenden denselben Typ wie Dispenser
    ENCHANTING_TABLE(2, InventoryType.ENCHANTING),
    FURNACE(3, InventoryType.FURNACE),
    HOPPER(5, InventoryType.HOPPER),
    MERCHANT(3, InventoryType.MERCHANT),
    SHULKER_BOX(27, InventoryType.SHULKER_BOX),
    SMITHING(3, InventoryType.SMITHING),
    STONECUTTER(1, InventoryType.STONECUTTER),
    ENDER_CHEST(27, InventoryType.ENDER_CHEST);

    private final int slotCount;
    private final InventoryType type;

    GuiType(final int slotCount, final InventoryType type) {
        this.slotCount = slotCount;
        this.type = type;
    }

    public int slotCount() {
        return this.slotCount;
    }

    public InventoryType getType() {
        return this.type;
    }

    public static GuiType getByRows(int rows) {
        if (rows < 1 || rows > 6) throw new IllegalArgumentException("rows must be between 1 and 6");
        return GuiType.values()[rows - 1];
    }
}
