package de.daver.build.hub.gui.event;

import de.daver.build.hub.gui.Gui;
import de.daver.build.hub.util.User;

public interface GuiOpenEvent extends GuiEvent{

    void onOpen(Gui gui, User user);

}
