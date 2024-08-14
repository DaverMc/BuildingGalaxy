package de.daver.build.hub.gui.event;

import de.daver.build.hub.gui.Gui;
import de.daver.build.hub.util.Player;

public interface GuiOpenEvent extends GuiEvent{

    void onOpen(Gui gui, Player player);

}
