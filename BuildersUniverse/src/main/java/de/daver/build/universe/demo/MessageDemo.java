package de.daver.build.universe.demo;

import de.daver.build.universe.lang.BUUNKeys;
import de.daver.build.universe.lang.Messages;
import de.daver.build.universe.lang.PlaceHolder;
import de.daver.build.universe.util.Player;

import java.util.Locale;

public class MessageDemo {

    Player player = null;


    public void demo() {
        Messages.get(Locale.GERMAN, BUUNKeys.TEST_MESSAGE)
                .placeholder(new PlaceHolder("name", "Daver"))
                .placeholder(new PlaceHolder("age", 4, PlaceHolder.Type.INTEGER))
                .send(player);
    }

}
