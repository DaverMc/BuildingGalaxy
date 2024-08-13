package de.daver.build.universe.world;

import de.daver.build.universe.sql.transformer.ResultSetTransformer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorldResultSetTransformer implements ResultSetTransformer<World> {
    @Override
    public World transform(ResultSet resultSet) throws SQLException {
        return null;
    }
}
