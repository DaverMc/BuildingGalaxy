package de.daver.build.hub.util;

import java.util.UUID;

public interface User {

    void sendMessage(String message);

    UUID getUUID();

}
