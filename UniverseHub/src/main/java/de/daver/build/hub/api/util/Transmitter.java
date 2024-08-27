package de.daver.build.hub.api.util;

import java.util.Collection;

public interface Transmitter {

    void send(Sender user);

    default void broadcast(Collection<Sender> users) {
        users.forEach(this::send);
    }

    default void broadcast(Sender... users) {
        for (Sender user : users) send(user);
    }

}
