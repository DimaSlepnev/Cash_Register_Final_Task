package org.example.connection;

import org.example.dao.DAO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateConnectionTest {

    @Test
    public void getConnection() throws SQLException {
        Connection cpConnection = CreateConnection.createConnection();
        Assert.assertNotNull(cpConnection);
        cpConnection.close();
    }
}