package org.example.dao;

import org.example.connection.CreateConnection;
import org.example.model.Warehouse;
import org.example.services.WarehouseService;
import org.slf4j.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAO implements DAO<Warehouse, Integer> {

    /*
   Layer interaction with database entity - Warehouse
    */
    private static final Logger logger = LoggerFactory.getLogger(WarehouseDAO.class);
    private Statement st = null;
    Connection connection = null;
    PreparedStatement pst = null;

    @Override
    public boolean create(Warehouse warehouse) {
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLWarehouse.CREATE_PRODUCT.QUERY);
            pst.setString(1, warehouse.getProduct());
            pst.setInt(2, warehouse.getAmount());
            pst.setInt(3, warehouse.getExpertId());
            int i = pst.executeUpdate();
            if (i > 0) {
                logger.debug("Add new product {} to warehouse", warehouse);
                return true;
            }
        } catch (SQLException e) {
            logger.error("Product {} wasn't added to warehouse", warehouse);
        } finally {
            close(connection);
            close(pst);
        }
        return false;
    }

    @Override
    public List<Warehouse> findAll() {
        List<Warehouse> warehouseList = new ArrayList<>();
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
            logger.error("Can't find all, List size is {}", warehouseList.size());
        } finally {
            close(connection);
            close(pst);
        }
        return warehouseList;
    }

    @Override
    public Warehouse findModelById(Integer id) {
        Warehouse warehouse = null;
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
            logger.error("Can't find product by {} id. ", id, e);
        } finally {
            close(connection);
            close(pst);
        }

        return warehouse;
    }

    @Override
    public boolean update(Warehouse warehouse) {
        boolean result = false;
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLWarehouse.UPDATE.QUERY);
            pst.setString(1, warehouse.getProduct());
            pst.setInt(2, warehouse.getAmount());
            pst.setInt(3, warehouse.getExpertId());
            pst.setInt(4, warehouse.getId());
            result = pst.execute();
            logger.debug("{} was updated", warehouse);
        } catch (SQLException e) {
            logger.error("Cant update {}", warehouse);
        } finally {
            close(connection);
            close(pst);
        }
        return result;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLWarehouse.DELETE_PRODUCT_BY_ID.QUERY);
            pst.setInt(1, id);
            int i = pst.executeUpdate();
            if (i > 0) {
                logger.debug("Product with {} id was removed from warehouse", id);
                return true;
            }
        } catch (SQLException e) {
            logger.error("Can't delete product with {} id", id);
        } finally {
            close(connection);
            close(pst);
        }
        return false;
    }

    @Override
    public boolean delete(Warehouse warehouse) {
        boolean result = false;
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLWarehouse.DELETE_PRODUCT.QUERY);
            pst.setInt(1, warehouse.getId());
            pst.setString(2, warehouse.getProduct());
            pst.setInt(3, warehouse.getAmount());
            pst.setInt(4, warehouse.getExpertId());
            result = pst.execute();
            logger.debug("{} with id {} was removed from warehouse", warehouse, warehouse.getId());
        } catch (SQLException e) {
            logger.error("Can't delete {} with id {}", warehouse, warehouse.getId());
        } finally {
            close(connection);
            close(pst);
        }
        return result;
    }

    /*
    Find product to avoid duplicates
     */
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
            logger.error("Can't find product in warehouse with name {}", productName);
        } finally {
            close(connection);
            close(pst);
        }
        return warehouse;
    }

    /*
    Find product to avoid duplicates
     */
    public Warehouse isExist(String productName) {
        Warehouse warehouse = new Warehouse.Builder().withProduct("0").build();
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLWarehouse.IS_EXIST.QUERY);
            pst.setString(1, productName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                warehouse = new Warehouse.Builder()
                        .withId(rs.getInt(1))
                        .withProduct(rs.getString(2))
                        .withAmount(rs.getInt(3))
                        .withExpertId(rs.getInt(4)).build();
            }
        } catch (SQLException e) {
            logger.error("Can't find product in warehouse with name {}", productName);
        } finally {
            close(connection);
            close(pst);
        }
        return warehouse;
    }

    /*
    When senior cashier delete bill we must come back amount to warehouse
     */
    public boolean updateAmount(int amount, String productName) {
        Warehouse warehouse = WarehouseService.service().findByName(productName);
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLWarehouse.UPDATE_AMOUNT.QUERY);
            pst.setInt(1, warehouse.getAmount() + amount);
            pst.setString(2, warehouse.getProduct());
            int i = pst.executeUpdate();
            if (i > 0) {
                logger.debug("Product {} was updated to amount {}", warehouse, warehouse.getAmount() + amount);
                return true;
            }
        } catch (SQLException e) {
            logger.error("Can't update amount of product {} in warehouse", warehouse);
        } finally {
            close(connection);
            close(pst);
        }
        return false;
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
