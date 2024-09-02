package de.daver.build.hub.core.sql.statement;

import de.daver.build.hub.api.sql.Condition;
import de.daver.build.hub.api.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class SelectStatement implements Statement {

    private List<String> columns;

    private String tableName;
    private Condition condition;

    public SelectStatement() {
        this.columns = null;
    }

    public SelectStatement column(String column) {
        if(this.columns == null) this.columns = new ArrayList<>();
        this.columns.add(column);
        return this;
    }

    public SelectStatement from(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public SelectStatement where(Condition condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        if(this.columns == null) sb.append("*");
        else {
            for(int i = 0; i < this.columns.size(); i++) {
                sb.append(this.columns.get(i));
                if(i < this.columns.size() - 1) sb.append(", ");
            }
        }
        sb.append("\nFROM ").append(tableName);
        if(this.condition != null) sb.append("\nWHERE ").append(condition.asString());
        return sb.append(";").toString();
    }
}
