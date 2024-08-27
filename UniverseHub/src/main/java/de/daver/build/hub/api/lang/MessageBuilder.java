package de.daver.build.hub.api.lang;

import de.daver.build.hub.api.util.Transmitter;

public interface MessageBuilder extends Transmitter {

    MessageBuilder placeholder(PlaceHolder<?> placeHolder);

    MessageBuilder language(Language language);

    String toMessage();

}
