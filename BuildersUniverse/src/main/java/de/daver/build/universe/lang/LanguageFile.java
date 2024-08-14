package de.daver.build.universe.lang;

import java.util.HashMap;
import java.util.Map;

public class LanguageFile {

    private final Map<String, String> rawMessages;

    public LanguageFile(String language) {
        this.rawMessages = new HashMap<>();
    }

    public void load() {

    }

    public void update(Class<? extends LanguageKey> keyEnum) {

    }

    public String getRawMessage(LanguageKey key) {
        String msg = rawMessages.get(key.path());
        if(msg == null) return key.path();
        return msg;
    }

}
