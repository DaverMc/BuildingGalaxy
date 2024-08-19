package de.daver.build.hub.core.sql;

import de.daver.build.hub.api.sql.DatabaseConnection;
import de.daver.build.hub.api.sql.driver.DatabaseDriver;
import de.daver.build.hub.api.sql.ResultSetTransformer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConnectionImpl implements DatabaseConnection {

    private final DatabaseDriver driver;
    private final Map<Connection, Boolean> connectionPool; //True if usable
    private final int connectionPoolSize;

    public DatabaseConnectionImpl(DatabaseDriver driver, int connectionPoolSize) {
        this.driver = driver;
        this.connectionPool = new HashMap<>();
        this.connectionPoolSize = connectionPoolSize;
    }

    private Connection getConnection() throws SQLException {
        Connection connection = connectionPool.keySet().stream()
                .filter(connectionPool::get)
                .findFirst().orElse(null);
        if(connection == null) {
            connection = driver.createConnection();
            connectionPool.put(connection, true);
        }
        connectionPool.put(connection, false);
        return connection;
    }

    private void releaseConnection(Connection connection) throws SQLException {
        if(connectionPool.containsKey(connection)) connectionPool.put(connection, true);
        while(connectionPool.size() > connectionPoolSize) {
            Connection connect = connectionPool.keySet().stream()
                    .filter(con -> !connectionPool.get(con))
                    .findFirst().orElse(null);
            if(connect == null) break;
            connectionPool.remove(connect);
        }
    }

    public <T> T executeQuery(String sql, ResultSetTransformer<T> transformer, Object...params) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            releaseConnection(connection);
            return transformer.transform(resultSet);
        } catch (SQLException exception)  {
            return null;
        }
    }

    public boolean execute(String sql, Object...params) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            boolean result = statement.execute();
            releaseConnection(connection);
            return result;
        } catch (SQLException exception)  {
            return false;
        }
    }
}
