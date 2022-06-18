package org.example.dao;

import org.example.connection.CreateConnection;
import org.example.model.Bill;
import org.example.model.Employee;
import org.example.model.Warehouse;
import org.slf4j.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO implements DAO<Bill, Integer> {

    /*
    Layer interaction with database entity - Bill
     */
    private static final Logger logger = LoggerFactory.getLogger(BillDAO.class);
    private Statement st = null;
    Connection connection = null;
    PreparedStatement pst = null;



    @Override
    public void create(Bill bill) {
        try {
            connection = CreateConnection.createConnection();

            pst = connection.prepareStatement(SQLBill.CREATE_BILL.QUERY);
            pst.setInt(1, bill.getProductId());
            pst.setString(2, bill.getBody());
            pst.setInt(3, bill.getAmount());
            pst.setInt(4, bill.getPrice());
            pst.setBoolean(5, bill.isConfirmation());
            pst.executeUpdate();
            logger.debug("Create new bill {}", bill);
        } catch (SQLException e) {
            logger.error("Bill {} wasn't add", bill);
        } finally {
            close(connection);
            close(pst);
        }
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
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLBill.UPDATE.QUERY);
            pst.setInt(1, bill.getProductId());
            pst.setString(2, bill.getBody());
            pst.setInt(3, bill.getAmount());
            pst.setInt(4, bill.getPrice());
            pst.setBoolean(5, bill.isConfirmation());
            pst.setInt(6, bill.getId());
            int i = pst.executeUpdate();
            if (i > 0) {
                logger.debug("{} was updated", bill);
                return true;
            }
        } catch (SQLException e) {
            logger.error("Cant update {}.", bill, e);
        } finally {
            close(connection);
            close(pst);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLBill.DELETE_BILL_BY_ID.QUERY);
            pst.setInt(1, id);
            int i = pst.executeUpdate();
            if (i > 0) {
                logger.debug("Bill with {} id was removed", id);
                return true;
            }
        } catch (SQLException e) {
            logger.error("Can't delete bill with {} id", id);
        } finally {
            close(connection);
            close(pst);
        }
        return false;
    }

    @Override
    public void delete(Bill bill) {
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
            pst.executeUpdate();
            logger.debug("{} with id {} was removed", bill, bill.getId());
        } catch (SQLException e) {
            logger.error("Can't delete {} with id {}", bill, bill.getId());
        } finally {
            close(connection);
            close(pst);
        }
    }

    /*
    When senior cashier edit bill this data write to database
     */
    public boolean updateAmountAndPriceById(int amount, int price, int id) {
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLBill.UPDATE_AMOUNT_BY_ID.QUERY);
            pst.setInt(1, amount);
            pst.setInt(2, price);
            pst.setInt(3, id);
            int i = pst.executeUpdate();
            if (i > 0) {
                logger.debug("Bill with {} id was changed to {} amount amd {} price", id, amount, price);
                return true;
            }
        } catch (SQLException e) {
            logger.error("Can't update bill with {} id to {} amount and {} price", id, amount, price);
        } finally {
            close(connection);
            close(pst);
        }
        return false;
    }

    /*
    Remember in database when senior cashier confirm bill
     */
    public boolean updateConfirmationById(int id) {
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLBill.UPDATE_CONFIRMATION_BY_ID.QUERY);
            pst.setBoolean(1, true);
            pst.setInt(2, id);
            int i = pst.executeUpdate();
            if (i > 0) {
                logger.debug("Bill with {} id was confirmed", id);
                return true;
            }
        } catch (SQLException e) {
            logger.error("Can't confirm bill with {} id", id, e);
        } finally {
            close(connection);
            close(pst);
        }
        return false;
    }

    public List<Bill> findALlWithPagination(int start, int total) {
        List<Bill> bills = new ArrayList();
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement("select * from bill limit " + (start - 1) + "," + total);
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
        } catch (Exception e) {
            logger.error("Can't find all with pagination, List size is: {}, start: {}, total: {}", bills.size() , start, total);
        } finally {
            close(connection);
            close(pst);
        }
        return bills;
    }

    public int numberOfRows() {
        int numberOfRows = 0;
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement("SELECT COUNT(*) FROM bill");
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

    public List<Bill> sortingBy(String sorting, int start, int total){
        List<Bill> bills = new ArrayList();
        try {
            connection = CreateConnection.createConnection();
            String preQuery = ("select * from bill ");
            String postQuery = (" limit " + (start - 1) + "," + total);
            switch (sorting){
                case "id_ASC":
                    pst = connection.prepareStatement(preQuery + " ORDER BY id ASC " + postQuery);
                    break;
                case "id_DESC":
                    pst = connection.prepareStatement(preQuery + " ORDER BY id DESC " + postQuery);
                    break;
                case "productId_ASC":
                    pst = connection.prepareStatement(preQuery + " ORDER BY product_id ASC " + postQuery);
                    break;
                case "productId_DESC":
                    pst = connection.prepareStatement(preQuery+ " ORDER BY product_id DESC " + postQuery);
                    break;
                case "body_ASC":
                    pst = connection.prepareStatement(preQuery+ " ORDER BY body ASC " + postQuery);
                    break;
                case "body_DESC":
                    pst = connection.prepareStatement(preQuery+ " ORDER BY body DESC " + postQuery);
                    break;
                case "amount_ASC":
                    pst = connection.prepareStatement(preQuery + " ORDER BY amount ASC " + postQuery);
                    break;
                case "amount_DESC":
                    pst = connection.prepareStatement(preQuery+ " ORDER BY amount DESC " + postQuery);
                    break;
                case "price_ASC":
                    pst = connection.prepareStatement(preQuery + " ORDER BY price ASC " + postQuery);
                    break;
                case "price_DESC":
                    pst = connection.prepareStatement(preQuery+ " ORDER BY price DESC " + postQuery);
                    break;
                case "confirmation_ASC":
                    pst = connection.prepareStatement(preQuery + " ORDER BY confirmation ASC " + postQuery);
                    break;
                case "confirmation_DESC":
                    pst = connection.prepareStatement(preQuery+ " ORDER BY confirmation DESC " + postQuery);
                    break;
                default:
                    pst = connection.prepareStatement(preQuery + " ORDER BY id ASC " + postQuery);
            }
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
        } catch (Exception e) {
            logger.error("Can't find all with pagination, List size is: {}, start: {}, total: {}", bills.size() , start, total);
        } finally {
            close(connection);
            close(pst);
        }
        return bills;
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
