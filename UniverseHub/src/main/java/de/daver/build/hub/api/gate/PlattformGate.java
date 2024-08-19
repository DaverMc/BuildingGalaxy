package de.daver.build.hub.api.gate;

public interface PlattformGate {

    UniverseAdapter getAdapter();

    CommandRegistrator getCommandRegistrator();

    UserManager getUserManager();

    GuiManager getGuiManager();

    ItemManager getItemManager();

}
