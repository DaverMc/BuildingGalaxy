package de.daver.build.hub.lang;

import de.daver.build.hub.api.lang.MessageBuilder;
import de.daver.build.hub.api.lang.PlaceHolder;
import de.daver.build.hub.util.User;

import java.util.Collection;

public class MessageBuilderImpl implements MessageBuilder {

    public MessageBuilderImpl(String rawMessage) {

    }

    @Override
    public MessageBuilder placeholder(PlaceHolder... placeHolders) {
        return null;
    }

    @Override
    public String toMessage() {
        return "";
    }

    @Override
    public void send(User user) {

    }
}
