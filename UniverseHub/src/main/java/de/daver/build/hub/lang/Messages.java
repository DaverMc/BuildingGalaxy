package de.daver.build.hub.lang;


import java.util.HashMap;
import java.util.Map;

public class Messages {

    private static Messages instance;

    private static Messages get() {
        if (instance == null) instance = new Messages();
        return instance;
    }

    private final Map<Language, LanguageFile> languageFiles;

    private Messages() {
        this.languageFiles = new HashMap<>();
    }

    public static void init(Class<? extends LanguageKey> keyEnum, Language... languages) {
        Messages inst = get();
        inst.languageFiles.clear();
        for (Language language : languages) {
            LanguageFile file = new LanguageFile(language.getShortForm());
            file.load();
            file.update(keyEnum);
            inst.languageFiles.put(language, file);
        }
    }

    public static MessageBuilder get(Language language, LanguageKey key) {
        LanguageFile file = get().languageFiles.get(language);
        if (file == null) return new MessageBuilder(language.getShortForm());
        return new MessageBuilder(file.getRawMessage(key));
    }

}
