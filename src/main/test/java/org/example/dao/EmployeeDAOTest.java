package org.example.dao;

import org.example.model.Employee;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EmployeeDAOTest {

    @Test
    public void EmployeeDAOTest(){
        Employee employee = mock(Employee.class);
        EmployeeDAO employeeDAO = mock(EmployeeDAO.class);
        when(employeeDAO.findAll()).thenReturn(mock(List.class));
        when(employeeDAO.findModelById(0)).thenReturn(mock(Employee.class));
        when(employeeDAO.update(employee)).thenReturn(true);
        when(employeeDAO.deleteById(0)).thenReturn(false);
        when(employeeDAO.findByLoginAndPass("login","pass")).thenReturn(mock(Employee.class));
        when(employeeDAO.loginIsExist("login")).thenReturn(false);
        when(employeeDAO.sortingBy("sort",1,5)).thenReturn(mock(List.class));


        employeeDAO = spy(EmployeeDAO.class);
        employeeDAO.create(employee);
        verify(employeeDAO,times(1)).create(employee);
        employeeDAO.findAll();
        verify(employeeDAO,times(1)).findAll();
        employeeDAO.findModelById(0);
        verify(employeeDAO,times(1)).findModelById(0);
        employeeDAO.update(employee);
        verify(employeeDAO,times(1)).update(employee);
        employeeDAO.deleteById(0);
        verify(employeeDAO,times(1)).deleteById(0);
        employeeDAO.delete(employee);
        verify(employeeDAO,times(1)).delete(employee);
        employeeDAO.findByLoginAndPass("login","pass");
        verify(employeeDAO,times(1)).findByLoginAndPass("login","pass");
        employeeDAO.loginIsExist("login");
        verify(employeeDAO,times(1)).loginIsExist("login");
        employeeDAO.sortingBy("sorting",1,5);
        verify(employeeDAO,times(1)).sortingBy("sorting",1,5);
    }

}