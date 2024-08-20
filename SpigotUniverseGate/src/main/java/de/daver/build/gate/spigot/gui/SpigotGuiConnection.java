package de.daver.build.gate.spigot.gui;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.gate.GuiConnection;
import de.daver.build.hub.api.gui.Gui;
import de.daver.build.hub.api.util.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SpigotGuiConnection implements GuiConnection {


    @Override
    public void openGui(User user, Gui gui) {
        Player player = Bukkit.getPlayer(user.getUUID());
        if(player == null) throw new NullPointerException("Player not found!");
        InventoryType type = InventoryType.valueOf(gui.getType().getName());
        Inventory inventory = Bukkit.createInventory(player, type);
        for (int i = 0; i < gui.getType().getSlotCount(); i++) {
            ItemStack itemStack = (ItemStack) UniverseHub.gate().getItemManager().transform(gui.getItem(i));
            inventory.setItem(i, itemStack);
        }
        player.openInventory(inventory);
    }

    @Override
    public void updateGui(User user, Gui gui) {
        Player player = Bukkit.getPlayer(user.getUUID());
        if(player == null) throw new NullPointerException("Player not found!");
        Inventory inventory = player.getOpenInventory().getTopInventory();
        for (int i = 0; i < gui.getType().getSlotCount(); i++) {
            ItemStack itemStack = (ItemStack) UniverseHub.gate().getItemManager().transform(gui.getItem(i));
            inventory.setItem(i, itemStack);
        }
    }

    @Override
    public void closeGui(User user) {
        Player player = Bukkit.getPlayer(user.getUUID());
        if(player == null) throw new NullPointerException("Player not found!");
        player.closeInventory();
    }

}
