package de.daver.build.universe.demo;

import de.daver.build.universe.lang.BUUNKeys;
import de.daver.build.universe.lang.Language;
import de.daver.build.universe.lang.Messages;
import de.daver.build.universe.lang.PlaceHolder;
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
