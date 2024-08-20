package de.daver.build.gate.spigot.gui;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.gui.Gui;
import de.daver.build.hub.api.gui.GuiAction;
import de.daver.build.hub.api.gui.Slot;
import de.daver.build.hub.api.gui.event.GuiCloseEvent;
import de.daver.build.hub.api.gui.event.GuiOpenEvent;
import de.daver.build.hub.api.util.ClickType;
import de.daver.build.hub.api.util.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.List;

public class GuiListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    public void onGuiOpen(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        User user = UniverseHub.gate().getUserManager().getUser(player.getUniqueId());
        Gui gui = UniverseHub.getGuiManager().getOpenGui(user);
        GuiOpenEvent guiEvent = gui.getEvent(GuiOpenEvent.class);
        guiEvent.onOpen(gui, user);
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onGuiClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        User user = UniverseHub.gate().getUserManager().getUser(player.getUniqueId());
        Gui gui = UniverseHub.getGuiManager().removeOpenGui(user);
        GuiCloseEvent guiEvent = gui.getEvent(GuiCloseEvent.class);
        guiEvent.onClose(gui, user);
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        int index = event.getRawSlot();
        Player player = (Player) event.getWhoClicked();
        User user = UniverseHub.gate().getUserManager().getUser(player.getUniqueId());
        Gui gui = UniverseHub.getGuiManager().removeOpenGui(user);
        Slot slot = gui.getSlots()[index];
        if(!slot.isAccessible()) event.setCancelled(true);
        List<GuiAction> actions = slot.getActions(ClickType.LEFT); //TODO Change to dynamic transforming
        actions.forEach(guiAction -> guiAction.execute(user, gui));
    }

}
