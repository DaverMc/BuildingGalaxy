package de.daver.build.hub.core.sql.statement;

import de.daver.build.hub.api.sql.Column;
import de.daver.build.hub.api.sql.ColumnType;
import de.daver.build.hub.api.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class CreateTableStatement implements Statement {

    private final String tableName;
    private final List<Column> columns;
    private boolean notexisting;

    public CreateTableStatement(String tableName) {
        this.tableName = tableName;
        this.columns = new ArrayList<>();
    }

    public CreateTableStatement ifNotExists(boolean nonexistent) {
        this.notexisting = nonexistent;
        return this;
    }

    public CreateTableStatement column(Column column) {
        this.columns.add(column);
        return this;
    }

    public CreateTableStatement column(String columnName, ColumnType type) {
        return this.column(new Column(columnName, type));
    }

    public CreateTableStatement column(String columnName, ColumnType type, boolean primaryKey) {
        return this.column(new Column(columnName, type, primaryKey));
    }

    @Override
    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        if(this.notexisting) sb.append("IF NOT EXISTS ");
        sb.append(tableName);
        sb.append(" (\n");
        for(int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);
            sb.append("\t '").append(column.name()).append("' ");
            sb.append(column.type().asString()).append(" ");
            if(!column.nullable()) sb.append("NOT NULL ");
            if(column.primaryKey()) sb.append("PRIMARY KEY ");
            if(i < columns.size() - 1) sb.append(",");
            sb.append("\n");
        }
        return sb.append(");").toString();
    }
}
