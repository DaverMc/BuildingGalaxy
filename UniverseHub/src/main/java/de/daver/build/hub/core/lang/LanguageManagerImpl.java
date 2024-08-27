package de.daver.build.hub.core.lang;


import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.lang.Language;
import de.daver.build.hub.api.lang.LanguageKey;
import de.daver.build.hub.api.lang.LanguageManager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LanguageManagerImpl implements LanguageManager {

    private final Map<Language, LanguageFile> languageFiles;
    private File fileLocation;

    public LanguageManagerImpl() {
        this.languageFiles = new HashMap<>();
    }

    @Override
    public void init(Class<? extends LanguageKey> keyEnum, Language... languages) {
        this.languageFiles.clear();
        for (Language language : languages) {
            LanguageFile file = new LanguageFile(language.getShortForm(), fileLocation);
            try {
                file.load();
                file.update(keyEnum);
            }catch (IOException e) {
                UniverseHub.gate().getLogger().info(e.getMessage()); //TODO Proper Errorhandling
            }
            this.languageFiles.put(language, file);
        }
    }

    @Override
    public MessageBuilderImpl get(LanguageKey key) {
        return new MessageBuilderImpl(this, key);
    }

    @Override
    public void setFileLocation(File directory) {
        this.fileLocation = directory;
    }

    @Override
    public Language getDefaultLanguage() {
        return Language.ENGLISH; //TODO make it configurable
    }

    protected String getRaw(Language language, LanguageKey key) {
        if(language == null) language = getDefaultLanguage();
        LanguageFile file = languageFiles.get(language);
        if (file == null) return language.getShortForm();
        return file.getRawMessage(key);
    }
}
