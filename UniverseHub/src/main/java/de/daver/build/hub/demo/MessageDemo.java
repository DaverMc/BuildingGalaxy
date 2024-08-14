package de.daver.build.hub.demo;

import de.daver.build.hub.lang.BUUNKeys;
import de.daver.build.hub.lang.Language;
import de.daver.build.hub.lang.Messages;
import de.daver.build.hub.lang.PlaceHolder;
import de.daver.build.universe.util.Player;

public class MessageDemo {

    Player player = null;


    public void demo() {
        Messages.init(BUUNKeys.class, Language.BENGALI, Language.ENGLISH, Language.GERMAN);

        Messages.get(Language.GERMAN, BUUNKeys.TEST_MESSAGE)
                .placeholder(new PlaceHolder("name", "Daver"))
                .placeholder(new PlaceHolder("age", 4, PlaceHolder.Type.INTEGER))
                .send(player);
    }

}
