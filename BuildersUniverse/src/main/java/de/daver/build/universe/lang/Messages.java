package de.daver.build.universe.lang;


import java.util.Locale;

public class Messages {

    private static Messages instance;

    private static Messages get() {
        if (instance == null) instance = new Messages();
        return instance;
    }

    private Messages() {

    }

    public static MessageBuilder get(Locale locale, LanguageKey key) {
        return null;
    }

}
