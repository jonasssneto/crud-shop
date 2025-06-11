package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://admin:admin@localhost:3306/database";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
