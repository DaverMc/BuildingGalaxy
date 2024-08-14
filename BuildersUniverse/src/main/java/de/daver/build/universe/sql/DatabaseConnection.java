package de.daver.build.universe.sql;

import de.daver.build.universe.sql.driver.DatabaseDriver;
import de.daver.build.universe.sql.transformer.ResultSetTransformer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConnection {

    private final DatabaseDriver driver;
    private final Map<Connection, Boolean> connectionPool; //True if usable
    private final int connectionPoolSize;

    public DatabaseConnection(DatabaseDriver driver, int connectionPoolSize) {
        this.driver = driver;
        this.connectionPool = new HashMap<>();
        this.connectionPoolSize = connectionPoolSize;
    }

    public Connection getConnection() throws SQLException {
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

    public void releaseConnection(Connection connection) throws SQLException {
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
            return transformer.transform(resultSet);
        } catch (SQLException exception)  {
            return null;
        }
    }

    public boolean execute(String sql, Object...params) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            return statement.execute();
        } catch (SQLException exception)  {
            return false;
        }
    }
}
