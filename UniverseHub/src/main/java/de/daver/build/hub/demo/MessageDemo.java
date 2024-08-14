package de.daver.build.hub.demo;

import de.daver.build.hub.lang.Language;
import de.daver.build.hub.lang.LanguageKey;
import de.daver.build.hub.lang.Messages;
import de.daver.build.hub.lang.PlaceHolder;
import de.daver.build.hub.util.Player;

import java.util.ArrayList;

public class MessageDemo {

    Player player = null;


    public void demo() {
        Messages.init(DemoKeys.class, Language.BENGALI, Language.ENGLISH, Language.GERMAN);

        Messages.get(Language.GERMAN, DemoKeys.MSG)
                .placeholder(new PlaceHolder("name", "Daver"))
                .placeholder(new PlaceHolder("age", 4, PlaceHolder.Type.INTEGER))
                .send(player);

        Messages.get(Language.ENGLISH, DemoKeys.MSG)
                .broadcast(new ArrayList<>());

        String message = Messages.get(Language.ARABIC, DemoKeys.MSG)
                .toString();
    }


    public enum DemoKeys implements LanguageKey {
        MSG;

        @Override
        public String path() {
            return "msg";
        }

        @Override
        public String description() {
            return "desc";
        }
    }

}
