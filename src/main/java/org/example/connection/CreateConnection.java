package org.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateConnection {
    private static final ResourceBundle resource = ResourceBundle.getBundle("database");
    private static final String url = resource.getString("db.url");
    private static final String user = resource.getString("db.user");
    private static final String password = resource.getString("db.password");
    public static Connection createConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(url, user, password);
    }
}
