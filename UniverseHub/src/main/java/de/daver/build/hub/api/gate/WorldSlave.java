package de.daver.build.hub.api.gate;

import de.daver.build.hub.api.util.User;
import de.daver.build.hub.api.world.World;

public interface WorldSlave {

    void createWorld(World world);

    void unloadWorld(World world);

    boolean sendTo(User user, World world);

}
