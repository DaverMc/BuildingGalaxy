package de.daver.build.hub.api.gate;

import de.daver.build.hub.api.gui.Gui;
import de.daver.build.hub.util.User;

public interface GuiManager {

    void openGui(User user, Gui gui);

}
