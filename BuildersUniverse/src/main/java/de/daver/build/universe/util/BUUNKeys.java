package de.daver.build.universe.util;

public enum BUUNKeys implements LanguageKey {

    TEST_MESSAGE("buun.test-message");

    private final String path;

    BUUNKeys(final String path) {
        this.path = path;
    }

    @Override
    public String path() {
        return this.path;
    }
}
