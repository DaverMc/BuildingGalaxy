package de.daver.build.hub.sql.driver;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseDriver {

    Connection createConnection() throws SQLException;

}
