package de.daver.build.hub;

import de.daver.build.hub.api.gate.PlattformGate;
import de.daver.build.hub.api.gate.UniverseAdapter;
import de.daver.build.hub.api.lang.LanguageManager;
import de.daver.build.hub.lang.LanguageManagerImpl;

public class UniverseHub {

    private static PlattformGate connector;
    private final static LanguageManager languageManager = new LanguageManagerImpl();

    public static PlattformGate connector() {
        return connector;
    }

    public static <A extends UniverseAdapter> A instance() {
        return (A) connector().getAdapter();
    }

    public static void setConnector(PlattformGate connector) {
        UniverseHub.connector = connector;
    }

    public static LanguageManager getLanguageManager() {
        return languageManager;
    }
}
