package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface DAO <Model,Key>{
    boolean create(Model model);
    List<Model> findAll();
    Model findModelById(Key id);
    boolean update(Model model);
    boolean deleteById(Key id);
    boolean delete(Model model);
    default void close(PreparedStatement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            // log
        }
    }
    default void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            //log
        }
    }
}
