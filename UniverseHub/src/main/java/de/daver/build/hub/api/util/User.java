package de.daver.build.hub.api.util;

import java.util.UUID;

public interface User {

    void sendMessage(String message);

    UUID getUUID();

    boolean hasPermission(String permission);
}
