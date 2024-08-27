package de.daver.build.gate.spigot.user;

import de.daver.build.hub.api.lang.Language;
import de.daver.build.hub.api.util.User;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SpigotUser implements User {

    private final Player player;

    public SpigotUser(Player player) {
        this.player = player;
    }

    @Override
    public Language getLanguage() {
        return Language.ENGLISH;
    }

    @Override
    public void send(String message) {
        player.sendMessage(message);
    }

    @Override
    public UUID getUUID() {
        return player.getUniqueId();
    }

    @Override
    public boolean hasPermission(String permission) {
        return player.hasPermission(permission);
    }
}
