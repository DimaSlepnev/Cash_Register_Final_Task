package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Employee;
import org.example.services.EmployeeService;

import java.io.IOException;

@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("empIdDelete");
        int id = Integer.parseInt(idStr);
        EmployeeService.service().deleteById(id);
        resp.sendRedirect("redirectToAllEmployees");
    }
}
