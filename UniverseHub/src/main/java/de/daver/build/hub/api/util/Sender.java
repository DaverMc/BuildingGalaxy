package de.daver.build.hub.api.util;

public interface Sender {

    void send(String message);

    boolean hasPermission(String permission);

}
