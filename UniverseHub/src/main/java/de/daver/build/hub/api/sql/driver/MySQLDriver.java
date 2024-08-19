package de.daver.build.hub.api.sql.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDriver implements DatabaseDriver {

    private final String username;
    private final String password;
    private final String url;

    public MySQLDriver(String host, int port, String database, String username, String password) {
        this.username = username;
        this.password = password;
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + database;
    }

    @Override
    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}

