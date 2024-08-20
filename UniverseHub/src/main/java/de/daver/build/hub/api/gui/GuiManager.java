package de.daver.build.hub.api.gui;

import de.daver.build.hub.api.util.User;

public interface GuiManager {

    Gui getOpenGui(User user);

    Gui getGui(String id);

    void registerGui(String id, Gui gui);

    void addUnregisteredGui(Gui gui);

    void setOpenGui(User user, Gui gui);

    Gui removeOpenGui(User user);

    void removeUnregisteredGui(Gui gui);

}