package de.daver.build.hub.lang;

public interface LanguageKey {

    String path();

    //Eine Zeile über dem eigentlichen Key
    //Für zum Beispiel die einzelnen placeholder id und Ausgeschiedene oder Beschreibungen
    String description();
}
