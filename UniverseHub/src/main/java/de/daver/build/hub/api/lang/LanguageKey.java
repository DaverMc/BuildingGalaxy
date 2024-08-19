package de.daver.build.hub.api.lang;

public interface LanguageKey {

    String path();

    //Eine Zeile über dem eigentlichen Key
    //Für zum Beispiel die einzelnen placeholder id und Ausgeschiedene oder Beschreibungen
    String description();
}
