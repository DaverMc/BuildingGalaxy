package de.daver.build.hub.api.lang;

import java.io.File;

public interface LanguageManager {

    void init(Class<? extends LanguageKey> keyEnum, Language... languages);

    MessageBuilder get(LanguageKey key);

    void setFileLocation(File directory);

    Language getDefaultLanguage();
}
