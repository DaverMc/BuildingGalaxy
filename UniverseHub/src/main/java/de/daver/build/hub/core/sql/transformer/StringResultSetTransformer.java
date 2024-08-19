package de.daver.build.hub.core.sql.transformer;

import de.daver.build.hub.api.sql.ResultSetTransformer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StringResultSetTransformer implements ResultSetTransformer<String> {

    private final String columName;

    public StringResultSetTransformer(String columnName) {
        this.columName = columnName;
    }

    @Override
    public String transform(ResultSet resultSet) throws SQLException {
        return resultSet.getString(columName);
    }
}
