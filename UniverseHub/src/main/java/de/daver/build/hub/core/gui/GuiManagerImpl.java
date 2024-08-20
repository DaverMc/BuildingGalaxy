package de.daver.build.hub.core.gui;

import de.daver.build.hub.api.gui.Gui;
import de.daver.build.hub.api.gui.GuiManager;
import de.daver.build.hub.api.util.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GuiManagerImpl implements GuiManager {

    private final Map<String, Gui> guiMap;
    private final Map<UUID, Gui> openGui;

    public GuiManagerImpl() {
        this.guiMap = new HashMap<>();
        this.openGui = new HashMap<>();
    }

    @Override
    public Gui getOpenGui(User user) {
        return this.openGui.get(user.getUUID());
    }

    @Override
    public Gui getGui(String id) {
        return this.guiMap.get(id);
    }

    @Override
    public void registerGui(String id, Gui gui) {
        this.guiMap.put(id, gui);
    }

    @Override
    public void addUnregisteredGui(Gui gui) {
        this.guiMap.put(UUID.randomUUID().toString(), gui);
    }

    @Override
    public void setOpenGui(User user, Gui gui) {
        this.openGui.put(user.getUUID(), gui);
    }

    @Override
    public Gui removeOpenGui(User user) {
        Gui gui = this.openGui.remove(user.getUUID());
        if(this.guiMap.containsValue(gui)) return gui;
        removeUnregisteredGui(gui);
        return gui;
    }

    @Override
    public void removeUnregisteredGui(Gui gui) {
        guiMap.entrySet().removeIf(entry -> entry.getValue() == gui);
    }
}
