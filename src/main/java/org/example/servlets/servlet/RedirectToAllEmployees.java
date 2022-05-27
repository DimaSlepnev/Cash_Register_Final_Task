package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Employee;
import org.example.services.EmployeeService;

import java.io.IOException;

@WebServlet("/redirectToAllEmployees")
public class RedirectToAllEmployees extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("employees", EmployeeService.service().findAll());
        Employee employee = (Employee) req.getSession().getAttribute("employee");
        req.setAttribute("eId",employee.getId());
        getServletContext().getRequestDispatcher("/WEB-INF/view/showAllEmployees.jsp").forward(req,resp);
    }
}
