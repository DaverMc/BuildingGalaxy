package de.daver.build.universe.gui.event;

import de.daver.build.universe.gui.Gui;
import de.daver.build.universe.util.Player;

public interface GuiOpenEvent extends GuiEvent{

    void onOpen(Gui gui, Player player);

}
