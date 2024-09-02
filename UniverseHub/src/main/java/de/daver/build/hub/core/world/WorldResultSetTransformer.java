package de.daver.build.hub.core.world;

import de.daver.build.hub.UniverseHub;
import de.daver.build.hub.api.sql.ResultSetTransformer;
import de.daver.build.hub.api.world.World;
import de.daver.build.hub.api.world.WorldGenerator;
import de.daver.build.hub.api.world.WorldMaster;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class WorldResultSetTransformer implements ResultSetTransformer<World> {

    @Override
    public World transform(ResultSet resultSet) throws SQLException {
        WorldGenerator generator = UniverseHub.getWorldMaster().getGenerator("void");
        return new WorldImpl(UUID.randomUUID().toString(), generator, new ArrayList<>());
        //TODO
    }
}
