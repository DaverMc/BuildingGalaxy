package de.daver.build.gate.spigot.gui;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.gate.GuiManager;
import de.daver.build.hub.api.gui.Gui;
import de.daver.build.hub.api.util.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SpigotGuiManager implements GuiManager {


    @Override
    public void openGui(User user, Gui gui) {
        Player player = Bukkit.getPlayer(user.getUUID());
        if(player == null) return;
        InventoryType type = InventoryType.valueOf(gui.getType().getName());
        Inventory inventory = Bukkit.createInventory(player, type);
        //if(dynamicItemSupplier != null) gui.fillItems(dynamicItemSupplier.get()); //TODO
        for (int i = 0; i < gui.getType().getSlotCount(); i++) {
            ItemStack itemStack = (ItemStack) UniverseHub.connector().getItemManager().transform(gui.getItem(i));
            inventory.setItem(i, itemStack);
        }
        player.openInventory(inventory);
    }
}
