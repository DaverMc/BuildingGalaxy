package de.daver.build.gate.spigot.demo;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.gate.UniverseAdapter;

public class DemoAdapter implements UniverseAdapter {

    @Override
    public void onInitialisation() {
        DemoAdapter adapter = UniverseHub.instance();
        System.out.println("Initialising Demo Adapter");
    }

    @Override
    public void onTermination() {
        System.out.println("Terminating Demo Adapter");
    }
}
