package org.example.services;

import org.example.dao.EmployeeDAO;

public class EmployeeService {
    /*
   Provides connection with EmployeeDAO
    */
    public static EmployeeDAO service(){
        EmployeeDAO employeeDAO = new EmployeeDAO();
        return employeeDAO;
    }
}
