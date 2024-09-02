package de.daver.build.hub.api.sql;


import de.daver.build.hub.core.sql.statement.*;

public interface Statement {

    //TODO Refactor

    static CreateTableStatement createTable(String tableName) {
        return new CreateTableStatement(tableName);
    }

    static InsertIntoStatement insertInto(String tableName) {
        return new InsertIntoStatement(tableName);
    }

    static DeleteFromStatement deleteFrom(String tableName) {
        return new DeleteFromStatement(tableName);
    }

    static UpdateStatement update(String tableName) {
        return new UpdateStatement(tableName);
    }

    static SelectStatement select() {
        return new SelectStatement();
    }

    String build();

}
