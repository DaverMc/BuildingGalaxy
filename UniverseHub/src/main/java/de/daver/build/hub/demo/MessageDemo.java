package de.daver.build.hub.demo;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.lang.Language;
import de.daver.build.hub.api.lang.LanguageKey;
import de.daver.build.hub.lang.LanguageManagerImpl;
import de.daver.build.hub.api.lang.PlaceHolder;
import de.daver.build.hub.util.User;

import java.util.ArrayList;

public class MessageDemo {

    User user = null;


    public void demo() {
        UniverseHub.getLanguageManager().init(DemoKeys.class, Language.BENGALI, Language.GERMAN, Language.ENGLISH);

        UniverseHub.getLanguageManager().get(Language.GERMAN, DemoKeys.MSG)
                .placeholder(new PlaceHolder("name", "Daver"),
                        new PlaceHolder("age", 4, PlaceHolder.Type.INTEGER))
                .send(user);

        UniverseHub.getLanguageManager().get(Language.ENGLISH, DemoKeys.MSG)
                .broadcast(new ArrayList<>());

        String message = UniverseHub.getLanguageManager().get(Language.ARABIC, DemoKeys.MSG)
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
