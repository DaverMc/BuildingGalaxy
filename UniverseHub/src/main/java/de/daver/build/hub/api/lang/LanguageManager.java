package de.daver.build.hub.api.lang;

public interface LanguageManager {

    void init(Class<? extends LanguageKey> keyEnum, Language... languages);

    MessageBuilder get(Language language, LanguageKey key);

}
