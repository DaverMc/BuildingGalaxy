package de.daver.build.hub.gui;

import de.daver.build.hub.item.Item;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;
import java.util.function.Supplier;

public class Gui {

    private final Slot[] slots;
    private final GuiType type;
    private final Supplier<List<Item>> dynamicItemSupplier;

    public Gui(GuiType type, Supplier<List<Item>> dynamicItemSupplier) {
        this.type = type;
        this.slots = new Slot[type.slotCount()];
        this.dynamicItemSupplier = dynamicItemSupplier;
    }

    public boolean setItem(int slot, Item item) {
        return this.slots[slot].setItem(item);
    }

    public void fillItems(List<Item> items) {
        int index = 0;
        for (Slot slot : slots) {
            if(index >= items.size()) return;
            if (slot.setItem(items.get(index))) index++;
        }
    }

    public void setAccessable(boolean accessable, int...slots) {
        for(int slot : slots) {
            this.slots[slot].setAccessible(accessable);
        }
    }

    public void open(Player player) {
        Inventory inventory = Bukkit.createInventory(player, type.getType());
        if(dynamicItemSupplier != null) fillItems(dynamicItemSupplier.get());
        for (int i = 0; i < type.slotCount(); i++) {
            inventory.setItem(i, slots[i].getItem().toBukkit());
        }
        player.openInventory(inventory);
    }

    public Item getItem(int slot) {
        return this.slots[slot].getItem();
    }

    public Slot[] getSlots() {
        return this.slots;
    }

}
