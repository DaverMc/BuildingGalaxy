package de.daver.build.hub.core.lang;

import de.daver.build.hub.api.lang.LanguageKey;

import java.io.*;
import java.util.*;

public class LanguageFile {

    private final Map<String, String> rawMessages;
    private final Map<String, String> comments;
    private final File file;

    public LanguageFile(String language, File location) {
        this.rawMessages = new HashMap<>();
        this.comments = new HashMap<>();
        this.file = new File(location, language + ".lang");
    }

    public void load() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        String key = null;
        StringBuilder value = new StringBuilder();
        String comment = null;
        while ((line = reader.readLine()) != null) {
            int index = line.indexOf('=');
            int indexSpace = line.indexOf(' ');

            if (index != -1 && indexSpace != -1 && index + 1 == indexSpace) {
                //New Entry
                if (key != null) this.rawMessages.put(key, value.toString());
                key = line.substring(0, index);
                value = new StringBuilder(line.substring(index + 1));
            } else if(line.startsWith("#")) {
                //Comment
                if(comment != null) comments.put(key, comment);
                comment = line;
            }
            //Additional lines
            else value.append(line);
        }
    }

    public void update(Class<? extends LanguageKey> keyEnum) throws IOException {
        //Die Keys werden eingelesen
        //Alle neuen Keys werden ganz unten hinzugefügt
        //Alle alten Keys werden in der Description als ausgeschieden markiert
        //und anschließend noch unter die neuen Keys geordnet werden.
        Set<String> oldKeys = new HashSet<>(this.rawMessages.keySet());
        Set<LanguageKey> newKeys = new HashSet<>();
        for(LanguageKey key : keyEnum.getEnumConstants()) {
            String message = rawMessages.get(key.path());
            //Neuer Eintrag
            if(message == null) newKeys.add(key);
            //Bestehender Eintrag
            else writeKey(key.path(), null, message);

            oldKeys.remove(message);
        }
        for(LanguageKey key : newKeys) {
            //Neuer Eintrag schreiben
            writeKey(key.path(), "NEW", key.description());
        }
        for(String key : oldKeys) {
            //Alter Eintrag
            String message = rawMessages.get(key);
            writeKey(key, "OLD", message);
        }
    }

    private void writeKey(String key, String description, String message) throws IOException {
        //Es soll erst in einer Zeile die Description stehen
        //In der zweiten Zeile wird dann der Key als Property geschrieben
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        String comment;
        if(description == null) comment = comments.get(key);
        else comment = description;
        writer.write("#" + comment);
        writer.newLine();
        writer.write(key + "= " + message);
        writer.newLine();
        writer.flush();
    }

    public String getRawMessage(LanguageKey key) {
        String msg = rawMessages.get(key.path());
        if(msg == null) return key.path();
        return msg;
    }

}
