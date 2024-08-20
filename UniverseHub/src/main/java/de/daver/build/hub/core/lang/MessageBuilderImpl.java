package de.daver.build.hub.core.lang;

import de.daver.build.hub.api.lang.MessageBuilder;
import de.daver.build.hub.api.lang.PlaceHolder;
import de.daver.build.hub.api.util.User;

public class MessageBuilderImpl implements MessageBuilder {

    private String rawMessage;

    public MessageBuilderImpl(String rawMessage) {
        this.rawMessage = rawMessage;
    }

    @Override
    public MessageBuilder placeholder(PlaceHolder<?>... placeHolders) {
        for(PlaceHolder<?> placeHolder : placeHolders) {
            this.rawMessage = rawMessage.replaceAll("<" + placeHolder.key() + ">", placeHolder.get());
        }
        return this;
    }

    @Override
    public String toMessage() {
        return this.rawMessage;
    }

    @Override
    public void send(User user) {
        user.send(toMessage());
    }
}
