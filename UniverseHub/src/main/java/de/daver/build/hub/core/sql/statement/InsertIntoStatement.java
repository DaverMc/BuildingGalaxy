package de.daver.build.hub.core.sql.statement;

import de.daver.build.hub.api.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class InsertIntoStatement implements Statement {

    private final String tableName;
    private final List<String> columns;

    public InsertIntoStatement(String tableName) {
        this.tableName = tableName;
        this.columns = new ArrayList<>();
    }

    public InsertIntoStatement column(String column) {
        this.columns.add(column);
        return this;
    }

    @Override
    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(tableName);
        sb.append(" (");
        for (int i = 0; i < columns.size(); i++) {
            String column = columns.get(i);
            sb.append(column);
            if (i < columns.size() - 1) sb.append(", ");
        }
        sb.append(") \nVALUES (");
        for (int i = 0; i < columns.size(); i++) {
            sb.append("?");
            if (i < columns.size() - 1) sb.append(",");
        }
        return sb.append(");").toString();
    }
}
