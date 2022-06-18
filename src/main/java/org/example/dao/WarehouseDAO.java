package org.example.dao;

import org.example.connection.CreateConnection;
import org.example.model.Employee;
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
    public void create(Warehouse warehouse) {
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLWarehouse.CREATE_PRODUCT.QUERY);
            pst.setString(1, warehouse.getProduct());
            pst.setInt(2, warehouse.getAmount());
            pst.setInt(3, warehouse.getExpertId());
            pst.executeUpdate();
            logger.debug("Add new product {} to warehouse", warehouse);
        } catch (SQLException e) {
            logger.error("Product {} wasn't added to warehouse", warehouse);
        } finally {
            close(connection);
            close(pst);
        }
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
    public void delete(Warehouse warehouse) {
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLWarehouse.DELETE_PRODUCT.QUERY);
            pst.setInt(1, warehouse.getId());
            pst.setString(2, warehouse.getProduct());
            pst.setInt(3, warehouse.getAmount());
            pst.setInt(4, warehouse.getExpertId());
            pst.executeUpdate();
            logger.debug("{} with id {} was removed from warehouse", warehouse, warehouse.getId());
        } catch (SQLException e) {
            logger.error("Can't delete {} with id {}", warehouse, warehouse.getId());
        } finally {
            close(connection);
            close(pst);
        }
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
            System.out.println();
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

    public List<Warehouse> findALlWithPagination(int start, int total) {
        List<Warehouse> warehouseList = new ArrayList();
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement("select * from warehouse limit " + (start - 1) + "," + total);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Warehouse warehouse = new Warehouse.Builder()
                        .withId(rs.getInt(1))
                        .withProduct(rs.getString(2))
                        .withAmount(rs.getInt(3))
                        .withExpertId(rs.getInt(4)).build();
                warehouseList.add(warehouse);
            }
        } catch (Exception e) {
            logger.error("Can't find all with pagination, List size is: {}, start: {}, total: {}", warehouseList.size() , start, total);
        } finally {
            close(connection);
            close(pst);
        }
        return warehouseList;
    }

    public int numberOfRows() {
        int numberOfRows = 0;
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement("SELECT COUNT(*) FROM warehouse");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                numberOfRows = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(pst);
        }
        return  numberOfRows;
    }

    public List<Warehouse> sortingBy(String sorting, int start, int total) {
        List<Warehouse> warehouseList = new ArrayList();
        try {
            connection = CreateConnection.createConnection();
            String preQuery = ("select * from warehouse ");
            String postQuery = (" limit " + (start - 1) + "," + total);
            switch (sorting) {
                case "id_ASC":
                    pst = connection.prepareStatement(preQuery + " ORDER BY id ASC " + postQuery);
                    break;
                case "id_DESC":
                    pst = connection.prepareStatement(preQuery + " ORDER BY id DESC " + postQuery);
                    break;
                case "product_ASC":
                    pst = connection.prepareStatement(preQuery + " ORDER BY product ASC " + postQuery);
                    break;
                case "product_DESC":
                    pst = connection.prepareStatement(preQuery + " ORDER BY product DESC " + postQuery);
                    break;
                case "amount_ASC":
                    pst = connection.prepareStatement(preQuery + " ORDER BY amount ASC " + postQuery);
                    break;
                case "amount_DESC":
                    pst = connection.prepareStatement(preQuery + " ORDER BY amount DESC " + postQuery);
                    break;
                case "expertId_ASC":
                    pst = connection.prepareStatement(preQuery + " ORDER BY expert_id ASC " + postQuery);
                    break;
                case "surname_DESC":
                    pst = connection.prepareStatement(preQuery + " ORDER BY expert_id DESC " + postQuery);
                    break;
                default:
                    pst = connection.prepareStatement(preQuery + " ORDER BY id ASC " + postQuery);
            }
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Warehouse warehouse = new Warehouse.Builder()
                        .withId(rs.getInt(1))
                        .withProduct(rs.getString(2))
                        .withAmount(rs.getInt(3))
                        .withExpertId(rs.getInt(4)).build();
                warehouseList.add(warehouse);
            }
        } catch (Exception e) {
            logger.error("Can't find all with pagination, List size is: {}, start: {}, total: {}", warehouseList.size(), start, total);
        } finally {
            close(connection);
            close(pst);
        }
        return warehouseList;
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
