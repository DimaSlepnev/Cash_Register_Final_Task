package org.example.dao;

import org.example.connection.ConnectionPool;
import org.example.connection.CreateConnection;
import org.example.model.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO implements DAO<Bill, Integer> {
    // private static final Logger logger = MyLogger.getLogger(EmployeeDAO.class.getSimpleName());
    private static ConnectionPool cp;
    private Statement st = null;
    Connection connection = null;
    PreparedStatement pst = null;


    @Override
    public boolean create(Bill bill) {
        boolean result = false;
       /* Connection connection = null;
        PreparedStatement pst = null;*/
        /* Connection connection = cp.getConnection();*/
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLBill.CREATE_BILL.QUERY);
            pst.setInt(1, bill.getProductId());
            pst.setString(2, bill.getBody());
            pst.setInt(3, bill.getAmount());
            pst.setInt(4, bill.getPrice());
            pst.setBoolean(5, bill.isConfirmation());
            result = pst.execute();
        } catch (SQLException e) {
            //   logger.error(MyLogger.exceptionMessage(e));
        } finally {
            close(connection);
            close(pst);
            /*cp.releaseConnection(connection);*/
        }
        return result;
    }

    @Override
    public List<Bill> findAll() {
        List<Bill> bills = new ArrayList<>();
        /*Connection connection = null;
        PreparedStatement pst = null;*/
        /*Connection connection = cp.getConnection();*/
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
            //   logger.error(MyLogger.exceptionMessage(e));
        } finally {
            close(connection);
            close(pst);
            /* cp.releaseConnection(connection);*/
        }
        return bills;
    }

    @Override
    public Bill findModelById(Integer id) {
        Bill bill = null;
       /* Connection connection = null;
        PreparedStatement pst = null;*/
        /* Connection connection = cp.getConnection();*/
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
            //   logger.error(MyLogger.exceptionMessage(e));
        } finally {
            close(connection);
            close(pst);
            /*cp.releaseConnection(connection);*/
        }
        return bill;
    }

    @Override
    public boolean update(Bill bill) {
        boolean result = false;
        /*Connection connection = null;
        PreparedStatement pst = null;*/
        /*Connection connection = cp.getConnection();*/
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
        } catch (SQLException e) {
            //  logger.error(MyLogger.exceptionMessage(e));
        } finally {
            close(connection);
            close(pst);
            /* cp.releaseConnection(connection);*/
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
            pst = connection.prepareStatement(SQLBill.DELETE_BILL_BY_ID.QUERY);
            pst.setInt(1, id);
            result = pst.execute();

        } catch (SQLException e) {
            //   logger.error(MyLogger.exceptionMessage(e));
        } finally {
            close(connection);
            close(pst);
            /*cp.releaseConnection(connection);*/
        }
        return result;
    }

    @Override
    public boolean delete(Bill bill) {
        boolean result = false;
       /* Connection connection = null;
        PreparedStatement pst = null;*/
        /*Connection connection = cp.getConnection();*/
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLBill.DELETE_BILL.QUERY);
            pst.setInt(1, bill.getId());
            pst.setInt(2, bill.getProductId());
            pst.setString(3, bill.getBody());
            pst.setInt(4, bill.getAmount());
            pst.setInt(5,bill.getPrice());
            pst.setBoolean(6, bill.isConfirmation());
            result = pst.execute();
        } catch (SQLException e) {
            //   logger.error(MyLogger.exceptionMessage(e));
        } finally {
            close(connection);
            close(pst);
            /*cp.releaseConnection(connection);*/
        }
        return result;
    }

    public boolean updateAmountAndPriceById(int amount, int price, int id) {
        boolean result = false;
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLBill.UPDATE_AMOUNT_BY_ID.QUERY);
            pst.setInt(1, amount);
            pst.setInt(2,price);
            pst.setInt(3, id);
            result = pst.execute();
        } catch (SQLException e) {
            //logger.error(MyLogger.exceptionMessage(e));
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
        } catch (SQLException e) {
            //logger.error(MyLogger.exceptionMessage(e));
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
