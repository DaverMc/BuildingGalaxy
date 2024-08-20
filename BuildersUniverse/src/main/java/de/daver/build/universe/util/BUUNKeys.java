package de.daver.build.universe.util;

import de.daver.build.hub.api.lang.LanguageKey;

public enum BUUNKeys implements LanguageKey {

    TEST_MESSAGE("buun.test-message", "<version>");

    private final String path;
    private final String description;

    BUUNKeys(final String path, final String description) {
        this.path = path;
        this.description = description;
    }

    @Override
    public String path() {
        return this.path;
    }

    @Override
    public String description() {
        return this.description;
    }
}
