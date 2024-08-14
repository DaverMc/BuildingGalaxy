package de.daver.build.universe.gui;

import java.util.UUID;

public class GuiMaster {

    private static GuiMaster instance;

    public Gui getOpenGui(UUID uuid) {
        return null;
    }

    public static GuiMaster get() {
        if(instance == null) instance = new GuiMaster();
        return instance;
    }

}
