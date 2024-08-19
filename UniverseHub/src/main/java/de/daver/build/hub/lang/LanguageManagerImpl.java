package de.daver.build.hub.lang;


import de.daver.build.hub.api.lang.Language;
import de.daver.build.hub.api.lang.LanguageKey;
import de.daver.build.hub.api.lang.LanguageManager;

import java.util.HashMap;
import java.util.Map;

public class LanguageManagerImpl implements LanguageManager {

    private final Map<Language, LanguageFile> languageFiles;

    public LanguageManagerImpl() {
        this.languageFiles = new HashMap<>();
    }

    public void init(Class<? extends LanguageKey> keyEnum, Language... languages) {
        this.languageFiles.clear();
        for (Language language : languages) {
            LanguageFile file = new LanguageFile(language.getShortForm());
            file.load();
            file.update(keyEnum);
            this.languageFiles.put(language, file);
        }
    }

    public MessageBuilderImpl get(Language language, LanguageKey key) {
        LanguageFile file = this.languageFiles.get(language);
        if (file == null) return new MessageBuilderImpl(language.getShortForm());
        return new MessageBuilderImpl(file.getRawMessage(key));
    }

}
