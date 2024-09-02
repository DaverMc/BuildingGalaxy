package de.daver.build.hub.core.sql.statement;

import de.daver.build.hub.api.sql.Condition;
import de.daver.build.hub.api.sql.Statement;

public class DeleteFromStatement implements Statement {

    private final String tableName;

    private Condition condition;

    public DeleteFromStatement(String tableName) {
        this.tableName = tableName;
    }

    public DeleteFromStatement where(Condition condition) {
        this.condition = condition;
        return this;
    }


    @Override
    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ").append(tableName);
        sb.append(" \nWHERE ").append(condition.asString());
        return sb.append(";").toString();
    }
}
