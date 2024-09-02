package de.daver.build.hub.core.user;

import de.daver.build.hub.api.gate.UserManager;
import de.daver.build.hub.api.util.User;

import java.util.*;

public class UserManagerImpl implements UserManager {
    
    protected final Map<UUID, User> users;
    
    protected UserManagerImpl() {
        users = new HashMap<>();
    }
    
    protected void addOnlineUser(User user) {
        //TODO Implement Database check or insertion
        this.users.put(user.getUUID(), user);
    }

    protected void removeOnlineUser(User user) {
        this.users.remove(user.getUUID());
    }
    
    @Override
    public List<User> getOnlineUsers() {
        return List.of();
    }

    @Override
    public User getUser(UUID uuid) {
        //TODO If Offline check database
        return this.users.get(uuid);
    }
}
