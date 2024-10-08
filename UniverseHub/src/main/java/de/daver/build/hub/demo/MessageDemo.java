package de.daver.build.hub.demo;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.lang.Language;
import de.daver.build.hub.api.lang.LanguageKey;
import de.daver.build.hub.api.lang.PlaceHolder;
import de.daver.build.hub.api.util.User;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class MessageDemo {

    private static final User user = null;

    @SuppressWarnings("unused")
    public void demo() {
        UniverseHub.getLanguageManager().init(DemoKeys.class, Language.BENGALI, Language.GERMAN, Language.ENGLISH);

        UniverseHub.getLanguageManager().get(DemoKeys.MSG)
                .placeholder(new PlaceHolder<>("name", "Daver"))
                .placeholder(new PlaceHolder<>("age", 4, PlaceHolder.Type.INTEGER))
                .send(user);

        UniverseHub.getLanguageManager().get(DemoKeys.MSG)
                .broadcast(new ArrayList<>());

        String message = UniverseHub.getLanguageManager().get(DemoKeys.MSG)
                .language(Language.ARABIC)
                .toMessage();
        System.out.println(message);
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
