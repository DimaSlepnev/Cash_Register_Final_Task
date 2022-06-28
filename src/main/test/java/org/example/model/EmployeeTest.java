package org.example.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EmployeeTest {

    @Test
    public void EmployeeTest(){
        Employee.Builder employeeBuilder = spy(Employee.Builder.class);
        employeeBuilder.withId(1);
        verify(employeeBuilder,times(1)).withId(1);

        employeeBuilder.withPosition("position");
        verify(employeeBuilder,times(1)).withPosition("position");

        employeeBuilder.withLogin("login");
        verify(employeeBuilder,times(1)).withLogin("login");

        employeeBuilder.withPass("pass");
        verify(employeeBuilder,times(1)).withPass("pass");

        employeeBuilder.withFirstName("name");
        verify(employeeBuilder,times(1)).withFirstName("name");

        employeeBuilder.withLastName("surname");
        verify(employeeBuilder,times(1)).withLastName("surname");

        Employee employee = spy(Employee.class);
        employee.setId(0);
        verify(employee,times(1)).setId(0);
        assertEquals(0,employee.getId());
        verify(employee,times(1)).getId();

        employee.setPosition("position");
        verify(employee,times(1)).setPosition("position");
        assertEquals("position",employee.getPosition());
        verify(employee,times(1)).getPosition();

        employee.setLogin("login");
        verify(employee,times(1)).setLogin("login");
        assertEquals("login",employee.getLogin());
        verify(employee,times(1)).getLogin();

        employee.setPass("pass");
        verify(employee,times(1)).setPass("pass");
        assertEquals("pass",employee.getPass());
        verify(employee,times(1)).getPass();

        employee.setFirstName("name");
        verify(employee,times(1)).setFirstName("name");
        assertEquals("name",employee.getFirstName());
        verify(employee,times(1)).getFirstName();

        employee.setSecondName("surname");
        verify(employee,times(1)).setSecondName("surname");
        assertEquals("surname",employee.getSecondName());
        verify(employee,times(1)).getSecondName();
    }

}