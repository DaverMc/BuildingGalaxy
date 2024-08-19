package de.daver.build.hub.api.lang;

public enum Language {

    ENGLISH("EN"),
    MANDARIN("ZH"),
    HINDI("HI"),
    SPANISH("ES"),
    FRENCH("FR"),
    ARABIC("AR"),
    BENGALI("BN"),
    RUSSIAN("RU"),
    PORTUGUESE("PT"),
    URDU("UR"),
    GERMAN("DE");

    private final String shortForm;

    Language(String shortForm) {
        this.shortForm = shortForm;
    }

    public String getShortForm() {
        return this.shortForm;
    }
}

