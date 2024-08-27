package de.daver.build.hub.core.lang;

import de.daver.build.hub.api.lang.*;
import de.daver.build.hub.api.util.Sender;
import de.daver.build.hub.api.util.User;

import java.util.ArrayList;
import java.util.List;

public class MessageBuilderImpl implements MessageBuilder {

    private final LanguageManagerImpl languageManager;
    private final LanguageKey key;
    private final List<PlaceHolder<?>> placeHolders;

    private Language language;

    public MessageBuilderImpl(LanguageManagerImpl languageManager, LanguageKey key) {
        this.languageManager = languageManager;
        this.placeHolders = new ArrayList<>();
        this.language = null;
        this.key = key;
    }

    @Override
    public MessageBuilder placeholder(PlaceHolder<?> placeHolder) {
        this.placeHolders.add(placeHolder);
        return this;
    }

    @Override
    public MessageBuilder language(Language language) {
        return null;
    }

    @Override
    public String toMessage() {
        String rawMessage = this.languageManager.getRaw(this.language, this.key);
        for(PlaceHolder<?> placeHolder : this.placeHolders) {
            rawMessage = rawMessage.replaceAll("<" + placeHolder.key() + ">", placeHolder.get());
        }
        return rawMessage;
    }

    @Override
    public void send(Sender sender) {
        if(this.language == null) this.language = sender.getLanguage();
        sender.send(toMessage());
    }
}
