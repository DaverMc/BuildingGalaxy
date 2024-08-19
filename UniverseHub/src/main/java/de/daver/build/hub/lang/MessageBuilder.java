package de.daver.build.hub.lang;

import de.daver.build.hub.util.User;

import java.util.Collection;

public class MessageBuilder {

    public MessageBuilder(String rawMessage) {

    }

    public MessageBuilder placeholder(PlaceHolder placeHolder) {
        return this;
    }

    public void send(User user) {

    }

    public void broadcast(Collection<User> users) {

    }

    public String toString() {
        return null;
    }

}
