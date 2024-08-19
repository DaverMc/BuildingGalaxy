package de.daver.build.hub.api.gate;

import java.util.logging.Logger;

public interface PlattformGate {

    UniverseAdapter getAdapter();

    CommandRegistrator getCommandRegistrator();

    UserManager getUserManager();

    GuiManager getGuiManager();

    ItemManager getItemManager();

    WorldSlave getWorldSlave();

    SchedulerMaster getSchedulerMaster();

    Logger getLogger();

}
