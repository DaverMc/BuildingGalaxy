package de.daver.build.hub.gui.event;

import de.daver.build.hub.gui.Gui;
import de.daver.build.hub.util.User;

public interface GuiCloseEvent extends GuiEvent {

    void onClose(Gui gui, User user);

}
