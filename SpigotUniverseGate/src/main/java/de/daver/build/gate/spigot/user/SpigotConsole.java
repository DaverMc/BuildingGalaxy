package de.daver.build.gate.spigot.user;

import de.daver.build.hub.api.lang.Language;
import de.daver.build.hub.api.util.Sender;
import org.bukkit.Bukkit;

public class SpigotConsole implements Sender {

    @Override
    public Language getLanguage() {
        return Language.ENGLISH; //TODO Later change to config parameter
    }

    @Override
    public void send(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }

    @Override
    public boolean hasPermission(String permission) {
        return true;
    }
}
