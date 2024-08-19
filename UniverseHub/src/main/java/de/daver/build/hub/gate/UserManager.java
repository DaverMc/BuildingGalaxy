package de.daver.build.hub.gate;

import de.daver.build.hub.util.User;

import java.util.List;
import java.util.UUID;

public interface UserManager {

    List<User> getPlayers();

    User getPlayer(UUID uuid);

}
