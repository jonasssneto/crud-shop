package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class Database {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String url = dotenv.get("DB_URL");

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
