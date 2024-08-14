package de.daver.build.hub.gui.event;

import de.daver.build.hub.gui.Gui;
import de.daver.build.hub.util.Player;

public interface GuiCloseEvent extends GuiEvent {

    void onClose(Gui gui, Player player);

}
