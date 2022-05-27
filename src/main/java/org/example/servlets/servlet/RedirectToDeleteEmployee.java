package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Employee;
import org.example.services.EmployeeService;

import java.io.IOException;

@WebServlet("/redirectToDeleteEmployee")
public class RedirectToDeleteEmployee extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("employeeIdDelete");
        int id = Integer.parseInt(idStr);
        Employee employee = EmployeeService.service().findModelById(id);
        req.setAttribute("emp",employee);
        getServletContext().getRequestDispatcher("/WEB-INF/view/deleteEmployee.jsp").forward(req,resp);
    }
}
