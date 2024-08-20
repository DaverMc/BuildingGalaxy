package de.daver.build.hub.api.gate;

import de.daver.build.hub.api.gui.Gui;
import de.daver.build.hub.api.util.User;

public interface GuiConnection {

    void openGui(User user, Gui gui);

    void updateGui(User user, Gui gui);

    void closeGui(User user);

}
