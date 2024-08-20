package de.daver.build.hub.core.world;

import de.daver.build.hub.api.sql.ResultSetTransformer;
import de.daver.build.hub.api.world.World;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorldResultSetTransformer implements ResultSetTransformer<World> {

    @Override
    public World transform(ResultSet resultSet) throws SQLException {
        throw new SQLException("Not implemented yet");
    }
}
