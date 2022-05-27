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
        String idStr = req.getParameter("id");
        if(EmployeeService.service().deleteById(Integer.parseInt(idStr))){
            req.setAttribute("employees", EmployeeService.service().findAll());
            Employee employee = (Employee) req.getSession().getAttribute("employee");
            req.setAttribute("eId",employee.getId());
            req.setAttribute("employeeDelete",1);
            getServletContext().getRequestDispatcher("/WEB-INF/view/showAllEmployees.jsp").forward(req,resp);
        }else {
            req.setAttribute("employees", EmployeeService.service().findAll());
            Employee employee = (Employee) req.getSession().getAttribute("employee");
            req.setAttribute("eId",employee.getId());
            req.setAttribute("employeeDeleteError",1);
            getServletContext().getRequestDispatcher("/WEB-INF/view/showAllEmployees.jsp").forward(req,resp);
        }
    }
}
