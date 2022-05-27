package org.example.dao;

import org.apache.log4j.Logger;
import org.example.connection.ConnectionPool;
import org.example.connection.CreateConnection;
import org.example.loggerConfig.MyLogger;
import org.example.model.Warehouse;
import org.example.services.WarehouseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAO implements DAO<Warehouse, Integer> {
    //private static final Logger logger = MyLogger.getLogger(EmployeeDAO.class.getSimpleName());
    private static ConnectionPool cp;
    private Statement st = null;
    Connection connection = null;
    PreparedStatement pst = null;

    @Override
    public boolean create(Warehouse warehouse) {
        boolean result = false;
        /*Connection connection = null;
        PreparedStatement pst = null;*/
        /*Connection connection = cp.getConnection();*/
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLWarehouse.CREATE_PRODUCT.QUERY);
            pst.setString(1, warehouse.getProduct());
            pst.setInt(2, warehouse.getAmount());
            pst.setInt(3, warehouse.getExpertId());
            result = pst.execute();
        } catch (SQLException e) {
            //logger.error(MyLogger.exceptionMessage(e));
        } finally {
            close(connection);
            close(pst);
            /*cp.releaseConnection(connection);*/
        }
        return result;
    }

    @Override
    public List<Warehouse> findAll() {
        List<Warehouse> warehouseList = new ArrayList<>();
        /*Connection connection = null;
        PreparedStatement pst = null;*/
        /*Connection connection = cp.getConnection();*/
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLWarehouse.FIND_ALL.QUERY);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Warehouse warehouse = new Warehouse.Builder()
                        .withId(rs.getInt(1))
                        .withProduct(rs.getString(2))
                        .withAmount(rs.getInt(3))
                        .withExpertId(rs.getInt(4)).build();
                warehouseList.add(warehouse);
            }
        } catch (SQLException e) {
            //logger.error(MyLogger.exceptionMessage(e));
        } finally {
            close(connection);
            close(pst);
            /*cp.releaseConnection(connection);*/
        }
        return warehouseList;
    }

    @Override
    public Warehouse findModelById(Integer id) {
        Warehouse warehouse = null;
       /* Connection connection = null;
        PreparedStatement pst = null;*/
        /* Connection connection = cp.getConnection();*/
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLWarehouse.FIND_PRODUCT_BY_ID.QUERY);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                warehouse = new Warehouse.Builder()
                        .withId(rs.getInt(1))
                        .withProduct(rs.getString(2))
                        .withAmount(rs.getInt(3))
                        .withExpertId(rs.getInt(4)).build();
            }
        } catch (SQLException e) {
            //logger.error(MyLogger.exceptionMessage(e));
        } finally {
            close(connection);
            close(pst);
            /*cp.releaseConnection(connection);*/
        }

        return warehouse;
    }

    @Override
    public boolean update(Warehouse warehouse) {
        boolean result = false;
        /*Connection connection = null;
        PreparedStatement pst = null;*/
        /*Connection connection = cp.getConnection();*/
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLWarehouse.UPDATE.QUERY);
            pst.setString(1, warehouse.getProduct());
            pst.setInt(2, warehouse.getAmount());
            pst.setInt(3, warehouse.getExpertId());
            pst.setInt(4, warehouse.getId());
            result = pst.execute();
        } catch (SQLException e) {
            //logger.error(MyLogger.exceptionMessage(e));
        } finally {
            close(connection);
            close(pst);
            /*cp.releaseConnection(connection);*/
        }
        return result;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean result = false;
        /*Connection connection = null;
        PreparedStatement pst = null;*/
        /*Connection connection = cp.getConnection();*/
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLWarehouse.DELETE_PRODUCT_BY_ID.QUERY);
            pst.setInt(1, id);
            result = pst.execute();
        } catch (SQLException e) {
           // logger.error(MyLogger.exceptionMessage(e));
        } finally {
            close(connection);
            close(pst);
            /*cp.releaseConnection(connection);*/
        }
        return result;
    }

    @Override
    public boolean delete(Warehouse warehouse) {
        boolean result = false;
        /*Connection connection = null;
        PreparedStatement pst = null;*/
        /* Connection connection = cp.getConnection();*/
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLWarehouse.DELETE_PRODUCT.QUERY);
            pst.setInt(1, warehouse.getId());
            pst.setString(2, warehouse.getProduct());
            pst.setInt(3, warehouse.getAmount());
            pst.setInt(4, warehouse.getExpertId());
            result = pst.execute();
        } catch (SQLException e) {
           // logger.error(MyLogger.exceptionMessage(e));
        } finally {
            close(connection);
            close(pst);
            /* cp.releaseConnection(connection);*/
        }
        return result;
    }

    public Warehouse findByName(String productName) {
        Warehouse warehouse = null;
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLWarehouse.FIND_PRODUCT_BY_NAME.QUERY);
            pst.setString(1, productName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                warehouse = new Warehouse.Builder().
                        withId(rs.getInt(1)).
                        withProduct(rs.getString(2)).
                        withAmount(rs.getInt(3)).
                        withExpertId(rs.getInt(4)).build();

            }
        } catch (SQLException e) {
           // logger.error(MyLogger.exceptionMessage(e));
        } finally {
            close(connection);
            close(pst);
        }
        return warehouse;
    }

    public Warehouse isExist(String productName) {
        Warehouse warehouse = new Warehouse.Builder().withProduct("0").build();
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLWarehouse.IS_EXIST.QUERY);
            pst.setString(1, productName);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                warehouse = new Warehouse.Builder()
                        .withId(rs.getInt(1))
                        .withProduct(rs.getString(2))
                        .withAmount(rs.getInt(3))
                        .withExpertId(rs.getInt(4)).build();            }
        } catch (SQLException e) {
          //  logger.error(MyLogger.exceptionMessage(e));
        } finally {
            close(connection);
            close(pst);
        }
        return warehouse;
    }

    public boolean updateAmount(int amount,String productName) {
        boolean result = false;
        try {
            Warehouse warehouse = WarehouseService.service().findByName(productName);
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLWarehouse.UPDATE_AMOUNT.QUERY);
            pst.setInt(1, warehouse.getAmount() + amount);
            pst.setString(2, warehouse.getProduct());
            result = pst.execute();
        } catch (SQLException e) {
           // logger.error(MyLogger.exceptionMessage(e));
        } finally {
            close(connection);
            close(pst);
        }
        return result;
    }

    enum SQLWarehouse {
        CREATE_PRODUCT("INSERT INTO warehouse (product,amount,expert_id) VALUES ((?) , (?), (?) )"),
        FIND_ALL("SELECT * FROM warehouse"),
        FIND_PRODUCT_BY_ID("SELECT * FROM warehouse WHERE id = (?)"),
        UPDATE("UPDATE warehouse SET product = (?), amount = (?), expert_id = (?) WHERE id = (?)"),
        DELETE_PRODUCT_BY_ID("DELETE FROM warehouse WHERE id = (?)"),
        DELETE_PRODUCT("DELETE FROM warehouse WHERE id = (?) AND product = (?) AND amount = (?) AND expert_id  = (?)"),
        FIND_PRODUCT_BY_NAME("SELECT * FROM warehouse WHERE product = (?)"),
        IS_EXIST("SELECT * FROM warehouse WHERE product = (?)"),
        UPDATE_AMOUNT("UPDATE warehouse SET amount = (?) WHERE product = (?)");

        String QUERY;

        SQLWarehouse(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
