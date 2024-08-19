package de.daver.build.hub.api.gui.event;

import de.daver.build.hub.api.gui.Gui;
import de.daver.build.hub.api.util.User;

public interface GuiCloseEvent extends GuiEvent {

    void onClose(Gui gui, User user);

}
