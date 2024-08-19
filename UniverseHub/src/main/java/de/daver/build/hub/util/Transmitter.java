package de.daver.build.hub.util;

import java.util.Collection;

public interface Transmitter {

    void send(User user);

    default void broadcast(Collection<User> users) {
        users.forEach(this::send);
    }

    default void broadcast(User... users) {
        for (User user : users) send(user);
    }

}
