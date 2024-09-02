package de.daver.build.hub.core.sql.statement;

import de.daver.build.hub.api.sql.Condition;
import de.daver.build.hub.api.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class UpdateStatement implements Statement {

    private final String tableName;
    private final List<String> columns;

    private Condition condition;

    public UpdateStatement(String tableName) {
        this.tableName = tableName;
        this.columns = new ArrayList<>();
    }

    public UpdateStatement column(String column) {
        this.columns.add(column);
        return this;
    }

    public UpdateStatement where(Condition condition) {
        this.condition = condition;
        return this;
    }


    @Override
    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(tableName).append(" \nSET ");
        for(int i = 0; i < columns.size(); i++) {
            sb.append(" ").append(columns.get(i)).append(" = ?");
            if(i < columns.size() - 1) sb.append(", \n");
        }
        if(condition != null) sb.append("\nWHERE ").append(condition.asString());
        return sb.append(";").toString();
    }
}
