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
    private final Map<Connection, Boolean> connectionPool; // True if usable
    private final int connectionPoolSize;

    public DatabaseConnectionImpl(DatabaseDriver driver, int connectionPoolSize) {
        this.driver = driver;
        this.connectionPool = new HashMap<>();
        this.connectionPoolSize = connectionPoolSize;
    }

    private Connection getConnection() throws SQLException {
        Connection connection = findAvailableConnection();
        if (connection == null) {
            connection = createNewConnection();
        }
        markConnectionAsInUse(connection);
        return connection;
    }

    private Connection findAvailableConnection() {
        return connectionPool.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    private Connection createNewConnection() throws SQLException {
        Connection connection = driver.createConnection();
        connectionPool.put(connection, true);
        return connection;
    }

    private void markConnectionAsInUse(Connection connection) {
        connectionPool.put(connection, false);
    }

    private void releaseConnection(Connection connection) {
        if (connectionPool.containsKey(connection))
            connectionPool.put(connection, true);
        maintainConnectionPoolSize();
    }

    private void maintainConnectionPoolSize() {
        while (connectionPool.size() > connectionPoolSize) {
            connectionPool.entrySet().stream()
                    .filter(Map.Entry::getValue)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .ifPresent(connectionPool::remove);
        }
    }

    public <T> T executeQuery(String sql, ResultSetTransformer<T> transformer, Object... params) {
        Connection connection = null;
        try {
            connection = getConnection();
            try (PreparedStatement statement = prepareStatement(connection, sql, params);
                 ResultSet resultSet = statement.executeQuery()) {
                return transformer.transform(resultSet);
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }
    }

    public boolean execute(String sql, Object... params) {
        Connection connection = null;
        try {
            connection = getConnection();
            try (PreparedStatement statement = prepareStatement(connection, sql, params)) {
                return statement.execute();
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            if (connection != null) {
                releaseConnection(connection);
            }
        }
    }


    private PreparedStatement prepareStatement(Connection connection, String sql, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        return statement;
    }
}
