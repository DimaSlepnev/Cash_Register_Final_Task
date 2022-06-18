package org.example.dao;

import org.example.model.Employee;
import org.example.services.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDAOTest {
    @BeforeEach
    void setUp() {
        System.out.println("Run before");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Run after");
    }

    /*@Test*/
 /*   void create() {
        Employee employee = new Employee.Builder().
                withFirstName("name").
                withLastName("surname").
                withPosition("position").
                withLogin("login").
                withPass("password").built();
        assertEquals(true, EmployeeService.service().create(employee));
    }*/

    @Test
    void findAll() {
        assertEquals(5, EmployeeService.service().findAll().size());
    }

    @Test
    void findModelById() {
        Employee employee = EmployeeService.service().findModelById(10);
        assertEquals(10, employee.getId());
        assertEquals("senior cashier", employee.getPosition());
        assertEquals("seniorcash1", employee.getLogin());
        assertEquals("Tyler", employee.getFirstName());
        assertEquals("Creator", employee.getSecondName());
    }


    @Test
    void deleteById() {
        assertEquals(true, EmployeeService.service().deleteById(14));
    }


    @Test
    void findByLoginAndPass() {
        Employee employee = EmployeeService.service().findByLoginAndPass("expert1", "expert1");
        assertEquals("Rakim", employee.getFirstName());
        assertEquals("Mayers", employee.getSecondName());
        assertEquals(1, employee.getId());
        assertEquals("expert", employee.getPosition());
    }

    @Test
    void loginIsExist() {
        assertEquals(true, EmployeeService.service().loginIsExist("expert1"));
        assertEquals(false, EmployeeService.service().loginIsExist("123456789"));
    }
}