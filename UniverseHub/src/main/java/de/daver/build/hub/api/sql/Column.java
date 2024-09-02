package de.daver.build.hub.api.sql;

public record Column(String name, ColumnType type, boolean nullable, boolean primaryKey) {

    public Column(String name, ColumnType type, boolean primaryKey) {
        this(name, type, true, primaryKey);
    }

    public Column(String name, ColumnType type) {
        this(name, type, false);
    }

}
