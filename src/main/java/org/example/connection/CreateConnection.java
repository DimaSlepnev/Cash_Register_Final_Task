package org.example.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateConnection {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;
    /*
    Class create and return connection to database
    */
    static{
        ResourceBundle resource = ResourceBundle.getBundle("database");
        config.setJdbcUrl(resource.getString("db.url"));
        config.setUsername(resource.getString("db.user"));
        config.setPassword(resource.getString("db.password"));
        config.setDriverClassName(resource.getString("db.driver"));
        dataSource = new HikariDataSource(config);
    }
    public static Connection createConnection() throws SQLException {
      return dataSource.getConnection();
    }
}
