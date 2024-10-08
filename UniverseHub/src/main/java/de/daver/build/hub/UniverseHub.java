package de.daver.build.hub;

import de.daver.build.hub.api.gate.PlattformGate;
import de.daver.build.hub.api.gate.UniverseAdapter;
import de.daver.build.hub.api.gui.GuiManager;
import de.daver.build.hub.api.lang.LanguageManager;
import de.daver.build.hub.api.world.WorldMaster;
import de.daver.build.hub.core.gui.GuiManagerImpl;
import de.daver.build.hub.core.lang.LanguageManagerImpl;
import de.daver.build.hub.core.world.WorldMasterImpl;

public class UniverseHub {

    private static PlattformGate gate;
    private final static LanguageManager languageManager = new LanguageManagerImpl();
    private final static WorldMaster worldMaster = new WorldMasterImpl();
    private final static GuiManager guiManager = new GuiManagerImpl();

    public static PlattformGate gate() {
        return gate;
    }

    @SuppressWarnings("unchecked")
    public static <A extends UniverseAdapter> A instance() {
        return (A) gate().getAdapter();
    }

    public static void setGate(PlattformGate gate) {
        UniverseHub.gate = gate;
    }

    public static LanguageManager getLanguageManager() {
        return languageManager;
    }

    public static WorldMaster getWorldMaster() {
        return worldMaster;
    }

    public static GuiManager getGuiManager() {
        return guiManager;
    }
}
