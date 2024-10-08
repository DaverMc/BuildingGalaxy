package de.daver.build.hub.api.gate;

import de.daver.build.hub.api.util.User;

import java.util.List;
import java.util.UUID;

public interface UserManager {

    List<User> getOnlineUsers();

    User getUser(UUID uuid);

}
