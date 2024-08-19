package de.daver.build.hub.lang;

import de.daver.build.hub.api.lang.LanguageKey;

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
        //Die Keys werden eingelesen
        //Alle neuen Keys werden ganz unten hinzugefügt
        //Alle alten Keys werden in der Description als ausgeschieden markiert
        //und anschließend noch unter die neuen Keys geordnet werden.
    }

    private void writeKey(LanguageKey key) {
        //Es soll erst in einer Zeile die Description stehen
        //In der zweiten Zeile wird dann der Key als Property geschrieben
    }

    public String getRawMessage(LanguageKey key) {
        String msg = rawMessages.get(key.path());
        if(msg == null) return key.path();
        return msg;
    }

}
