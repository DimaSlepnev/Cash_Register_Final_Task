package org.example.dao;

import org.example.connection.ConnectionPool;
import org.example.connection.CreateConnection;
import org.example.model.Bill;
import org.slf4j.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO implements DAO<Bill, Integer> {
    private static final Logger logger = LoggerFactory.getLogger(BillDAO.class);
    private static ConnectionPool cp;
    private Statement st = null;
    Connection connection = null;
    PreparedStatement pst = null;


    @Override
    public boolean create(Bill bill) {
        boolean result = false;
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLBill.CREATE_BILL.QUERY);
            pst.setInt(1, bill.getProductId());
            pst.setString(2, bill.getBody());
            pst.setInt(3, bill.getAmount());
            pst.setInt(4, bill.getPrice());
            pst.setBoolean(5, bill.isConfirmation());
            result = pst.execute();
            logger.debug("Create new bill {}", bill);
        } catch (SQLException e) {
            logger.error("Bill {} wasn't add", bill);
        } finally {
            close(connection);
            close(pst);
        }
        return result;
    }

    @Override
    public List<Bill> findAll() {
        List<Bill> bills = new ArrayList<>();
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLBill.FIND_ALL.QUERY);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill.Builder()
                        .withId(rs.getInt(1))
                        .withProductId(rs.getInt(2))
                        .withBody(rs.getString(3))
                        .withAmount(rs.getInt(4))
                        .withPrice(rs.getInt(5))
                        .withConfirmation(rs.getBoolean(6)).build();
                bills.add(bill);
            }
        } catch (SQLException e) {
            logger.error("Can't find all, List size is {}", bills.size());
        } finally {
            close(connection);
            close(pst);
        }
        return bills;
    }

    @Override
    public Bill findModelById(Integer id) {
        Bill bill = null;
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLBill.FIND_BILL_BY_ID.QUERY);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                bill = new Bill.Builder()
                        .withId(rs.getInt(1))
                        .withProductId(rs.getInt(2))
                        .withBody(rs.getString(3))
                        .withAmount(rs.getInt(4))
                        .withPrice(rs.getInt(5))
                        .withConfirmation(rs.getBoolean(6)).build();
            }

        } catch (SQLException e) {
            logger.error("Can't find bill by {} id. ", id, e);
        } finally {
            close(connection);
            close(pst);
        }
        return bill;
    }

    @Override
    public boolean update(Bill bill) {
        boolean result = false;
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLBill.UPDATE.QUERY);
            pst.setInt(1, bill.getProductId());
            pst.setString(2, bill.getBody());
            pst.setInt(3, bill.getAmount());
            pst.setInt(4, bill.getPrice());
            pst.setBoolean(5, bill.isConfirmation());
            pst.setInt(6, bill.getId());
            result = pst.execute();
            logger.debug("{} was updated", bill);
        } catch (SQLException e) {
            logger.error("Cant update {}.", bill, e);
        } finally {
            close(connection);
            close(pst);
        }
        return result;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean result = false;
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLBill.DELETE_BILL_BY_ID.QUERY);
            pst.setInt(1, id);
            result = pst.execute();
            logger.debug("Bill with {} id was removed", id);
        } catch (SQLException e) {
            logger.error("Can't delete bill with {} id", id);
        } finally {
            close(connection);
            close(pst);
        }
        return result;
    }

    @Override
    public boolean delete(Bill bill) {
        boolean result = false;
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLBill.DELETE_BILL.QUERY);
            pst.setInt(1, bill.getId());
            pst.setInt(2, bill.getProductId());
            pst.setString(3, bill.getBody());
            pst.setInt(4, bill.getAmount());
            pst.setInt(5, bill.getPrice());
            pst.setBoolean(6, bill.isConfirmation());
            result = pst.execute();
            logger.debug("{} with id {} was removed", bill, bill.getId());
        } catch (SQLException e) {
            logger.error("Can't delete {} with id {}", bill, bill.getId());
        } finally {
            close(connection);
            close(pst);
        }
        return result;
    }

    public boolean updateAmountAndPriceById(int amount, int price, int id) {
        boolean result = false;
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLBill.UPDATE_AMOUNT_BY_ID.QUERY);
            pst.setInt(1, amount);
            pst.setInt(2, price);
            pst.setInt(3, id);
            result = pst.execute();
            logger.debug("Bill with {} id was changed to {} amount amd {} price", id, amount, price);
        } catch (SQLException e) {
            logger.error("Can't update bill with {} id to {} amount and {} price", id, amount, price);
        } finally {
            close(connection);
            close(pst);
        }
        return result;
    }

    public boolean updateConfirmationById(int id) {
        boolean result = false;
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLBill.UPDATE_CONFIRMATION_BY_ID.QUERY);
            pst.setBoolean(1, true);
            pst.setInt(2, id);
            result = pst.execute();
            logger.debug("Bill with {} id was confirmed", id);
        } catch (SQLException e) {
            logger.error("Can't confirm bill with {} id", id, e);
        } finally {
            close(connection);
            close(pst);
        }
        return result;
    }

    enum SQLBill {
        CREATE_BILL("INSERT INTO bill (product_id, body, amount, price, confirmation) VALUES ((?) , (?), (?), (?), (?) )"),
        FIND_ALL("SELECT * FROM bill"),
        FIND_BILL_BY_ID("SELECT * FROM bill WHERE id = (?)"),
        UPDATE("UPDATE bill SET product_id = (?), body =(?), amount = (?), price = (?) confirmation =(?) WHERE id = (?)"),
        DELETE_BILL_BY_ID("DELETE FROM bill WHERE id = (?)"),
        DELETE_BILL("DELETE FROM bill WHERE id = (?) AND product_id = (?) AND body = (?) AND amount = (?) AND price = (?) AND confirmation = (?)"),
        UPDATE_AMOUNT_BY_ID("UPDATE bill SET amount = (?), price = (?) WHERE id = (?)"),
        UPDATE_CONFIRMATION_BY_ID("UPDATE bill SET confirmation = (?) WHERE id = (?)");
        String QUERY;

        SQLBill(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
