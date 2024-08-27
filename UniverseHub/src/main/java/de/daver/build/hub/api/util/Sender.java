package de.daver.build.hub.api.util;

import de.daver.build.hub.api.lang.Language;

public interface Sender {

    Language getLanguage();

    void send(String message);

    boolean hasPermission(String permission);

}
