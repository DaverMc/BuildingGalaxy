package de.daver.build.hub.sql.transformer;

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
