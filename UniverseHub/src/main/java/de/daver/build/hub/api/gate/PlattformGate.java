package de.daver.build.hub.api.gate;

import de.daver.build.hub.api.util.Sender;

import java.io.File;
import java.util.logging.Logger;

public interface PlattformGate {

    UniverseAdapter getAdapter();

    CommandRegistrator getCommandRegistrator();

    UserManager getUserManager();

    GuiConnection getGuiConnection();

    ItemManager getItemManager();

    WorldSlave getWorldSlave();

    SchedulerMaster getSchedulerMaster();

    Logger getLogger();

    Sender getConsoleSender();

    File getMainDirectory();
}
