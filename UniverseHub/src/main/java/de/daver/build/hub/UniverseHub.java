package de.daver.build.hub;

import de.daver.build.hub.api.gate.PlattformGate;
import de.daver.build.hub.api.gate.UniverseAdapter;

public class UniverseHub {

    private static PlattformGate connector;

    public static PlattformGate connector() {
        return connector;
    }

    public static <A extends UniverseAdapter> A instance() {
        return (A) connector().getAdapter();
    }

    public static void setConnector(PlattformGate connector) {
        UniverseHub.connector = connector;
    }

}
