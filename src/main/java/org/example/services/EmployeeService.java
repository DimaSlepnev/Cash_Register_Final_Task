package org.example.services;

import org.example.dao.EmployeeDAO;

public class EmployeeService {
    public static EmployeeDAO service(){
        EmployeeDAO employeeDAO = new EmployeeDAO();
        return employeeDAO;
    }
}
