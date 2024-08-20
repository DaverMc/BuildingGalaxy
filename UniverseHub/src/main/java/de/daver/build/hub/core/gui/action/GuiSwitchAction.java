package de.daver.build.hub.core.gui.action;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.gui.Gui;
import de.daver.build.hub.api.gui.GuiAction;
import de.daver.build.hub.api.util.User;

public class GuiSwitchAction implements GuiAction {

    private final String id;

    public GuiSwitchAction(String guiId) {
        this.id = guiId;
    }

    @Override
    public void execute(User clicker, Gui oldGui) {
        Gui newGui = UniverseHub.getGuiManager().getGui(this.id);
        oldGui.close(clicker);
        newGui.open(clicker);
    }
}
