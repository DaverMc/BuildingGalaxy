package de.daver.build.hub.api.lang;

import de.daver.build.hub.api.util.Transmitter;

public interface MessageBuilder extends Transmitter {

    MessageBuilder placeholder(PlaceHolder... placeHolders);

    String toMessage();

}
