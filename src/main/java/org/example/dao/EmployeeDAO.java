package org.example.dao;

import org.example.connection.CreateConnection;
import org.example.model.Employee;
import org.slf4j.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements DAO<Employee, Integer> {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);
    private Statement st = null;
    Connection connection = null;
    PreparedStatement pst = null;


    @Override
    public boolean create(Employee employee) {
        boolean result = false;
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLEmployee.CREATE_EMPLOYEE.QUERY);
            pst.setString(1, employee.getPosition());
            pst.setString(2, employee.getLogin());
            pst.setString(3, employee.getPass());
            pst.setString(4, employee.getFirstName());
            pst.setString(5, employee.getSecondName());
            logger.debug("Add new employee {}",employee);
            result = pst.execute();
        } catch (SQLException e) {
            logger.error("Employee {} wasn't added",employee);
        } finally {
            close(connection);
            close(pst);
        }
        return result;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLEmployee.FIND_ALL.QUERY);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee.Builder()
                        .withId(rs.getInt(1))
                        .withPosition(rs.getString(2))
                        .withLogin(rs.getString(3))
                        .withPass(rs.getString(4)).withFirstName(rs.getString(5))
                        .withLastName(rs.getString(6)).built();
                employees.add(employee);
            }
        } catch (SQLException e) {
            logger.error("Can't find all, List size is {}", employees.size());
        } finally {
            close(connection);
            close(pst);
        }
        return employees;
    }

    @Override
    public Employee findModelById(Integer id) {
        Employee employee = null;
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLEmployee.FIND_EMPLOYEE_BY_ID.QUERY);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
                employee = new Employee.Builder()
                        .withId(rs.getInt(1))
                        .withPosition(rs.getString(2))
                        .withLogin(rs.getString(3))
                        .withPass(rs.getString(4)).withFirstName(rs.getString(5))
                        .withLastName(rs.getString(6)).built();
        } catch (SQLException e) {
          logger.error("Can't find employee by {} id. ", id, e);
        } finally {
           close(connection);
            close(pst);
        }
        return employee;
    }

    @Override
    public boolean update(Employee employee) {
        boolean result = false;
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLEmployee.UPDATE.QUERY);
            pst.setString(1, employee.getPosition());
            pst.setString(2, employee.getLogin());
            pst.setString(3, employee.getPass());
            pst.setInt(4, employee.getId());
            pst.setString(5, employee.getFirstName());
            pst.setString(6, employee.getSecondName());
            result = pst.execute();
            logger.debug("{} was updated", employee);
        } catch (SQLException e) {
         logger.error("Cant update {}",employee);
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
            pst = connection.prepareStatement(SQLEmployee.DELETE_EMPLOYEE_BY_ID.QUERY);
            pst.setInt(1, id);
            result = pst.execute();
            logger.debug("Employee with id {} was removed",id);
        } catch (SQLException e) {
            logger.error("Can't delete employee with {} id", id);
        } finally {
            close(connection);
            close(pst);
        }
        return result;
    }

    @Override
    public boolean delete(Employee employee) {
        boolean result = false;
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLEmployee.DELETE_EMPLOYEE.QUERY);
            pst.setInt(1, employee.getId());
            pst.setString(2, employee.getPosition());
            pst.setString(3, employee.getLogin());
            pst.setString(4, employee.getPass());
            pst.setString(5, employee.getFirstName());
            pst.setString(6, employee.getSecondName());
            result = pst.execute();
            logger.debug("{} with id {} was removed", employee, employee.getId());
        } catch (SQLException e) {
           logger.error("Can't delete {} with id {}", employee, employee.getId());
        } finally {
            close(connection);
            close(pst);
        }
        return result;
    }

    public Employee findByLoginAndPass(String login, String pass) {
        Employee employee = null;
        try {
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLEmployee.FIND_EMPLOYEE_BY_LOGIN_AND_PASS.QUERY);
            pst.setString(1, login);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                employee = new Employee.Builder().
                        withId(rs.getInt(1))
                        .withPosition(rs.getString(2))
                        .withLogin(rs.getString(3))
                        .withPass(rs.getString(4))
                        .withFirstName(rs.getString(5))
                        .withLastName(rs.getString(6)).built();
            }
        } catch (SQLException e) {
          logger.error("Can't find employee with login {}", login);
        } finally {
            close(connection);
            close(pst);
        }
        return employee;
    }

    public boolean loginIsExist(String login){
        try{
            connection = CreateConnection.createConnection();
            pst = connection.prepareStatement(SQLEmployee.LOGIN_IS_EXIST.QUERY);
            pst.setString(1,login);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            logger.error("Cant find login {}. ",login, e);
        }finally {
            close(connection);
            close(pst);
        }
        return false;
    }

    enum SQLEmployee {
        CREATE_EMPLOYEE("INSERT INTO employee (position,login,pass,first_name,second_name) VALUES ((?) , (?), (MD5(?)), (?), (?) )"),
        FIND_ALL("SELECT * FROM employee"),
        FIND_EMPLOYEE_BY_ID("SELECT * FROM employee WHERE id = (?)"),
        UPDATE("UPDATE employee SET position = (?), login =(?), pass = (?), first_name = (?), second_name =(?) WHERE id = (?)"),
        DELETE_EMPLOYEE_BY_ID("DELETE FROM employee WHERE id = (?)"),
        DELETE_EMPLOYEE("DELETE FROM employee WHERE id = (?) AND position = (?) AND login = (?) AND pass = (MD5(?) AND first_name = (?), AND second_name = (?) )"),
        FIND_EMPLOYEE_BY_LOGIN_AND_PASS("SELECT * FROM employee WHERE login = (?) AND pass = (MD5(?))"),
        IS_EXIST("SELECT * FROM employee WHERE login = (?) AND pass = (MD5(?))"),
        LOGIN_IS_EXIST("SELECT * FROM employee WHERE login = (?)");
        String QUERY;

        SQLEmployee(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
