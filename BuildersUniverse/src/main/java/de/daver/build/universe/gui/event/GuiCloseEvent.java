package de.daver.build.universe.gui.event;

import de.daver.build.universe.gui.Gui;
import de.daver.build.universe.util.Player;

public interface GuiCloseEvent extends GuiEvent {

    void onClose(Gui gui, Player player);

}
