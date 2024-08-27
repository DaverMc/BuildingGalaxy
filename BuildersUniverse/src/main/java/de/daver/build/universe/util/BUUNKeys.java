package de.daver.build.universe.util;

import de.daver.build.hub.api.lang.LanguageKey;

public enum BUUNKeys implements LanguageKey {

    COMMAND_WORLD_DELETE_SUCCESS("buun.command.world.delete.success", "id"),
    COMMAND_WORLD_DELETE_FAILED("buun.command.world.delete.failed", "id"),
    COMMAND_WORLD_CREATE_SUCCESS("buun.command.world.create.success", "id"),
    COMMAND_WORLD_CREATE_FAILED("buun.command.world.create.failed", "id");

    private final String path;
    private final String description;

    BUUNKeys(final String path) {
        this(path, "");
    }

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
