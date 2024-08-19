package de.daver.build.hub.world;

import de.daver.build.hub.api.sql.ResultSetTransformer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorldResultSetTransformer implements ResultSetTransformer<WorldImpl> {

    @Override
    public WorldImpl transform(ResultSet resultSet) throws SQLException {
        return null;
    }
}
