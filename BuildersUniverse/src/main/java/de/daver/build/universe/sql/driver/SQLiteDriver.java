package de.daver.build.universe.sql.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;

public class SQLiteDriver implements DatabaseDriver {

    private final String username;
    private final String password;
    private final String url;

    public SQLiteDriver(File file, String username, String password) {
        this.username = username;
        this.password = password;
        this.url = "jdbc:sqlite:" + file.getAbsolutePath();
    }

    @Override
    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
