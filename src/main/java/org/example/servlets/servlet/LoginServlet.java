package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.model.Employee;
import org.example.services.EmployeeService;

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/menu.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        HttpSession session = req.getSession();
        Employee employee = EmployeeService.service().findByLoginAndPass(login, pass);
        if (employee != null) {
            session.setAttribute("employee", employee);
            doGet(req,resp);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req,resp);
        }
    }

}
