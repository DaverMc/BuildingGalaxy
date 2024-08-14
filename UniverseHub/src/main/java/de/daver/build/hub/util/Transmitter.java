package de.daver.build.hub.util;

import java.util.Collection;

public interface Transmitter {

    void send(Player player);

    default void broadcast(Collection<Player> players) {
        players.forEach(this::send);
    }

    default void broadcast(Player... players) {
        for (Player player : players) send(player);
    }

}
