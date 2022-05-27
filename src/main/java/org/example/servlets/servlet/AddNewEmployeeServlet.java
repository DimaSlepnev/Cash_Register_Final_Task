package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Employee;
import org.example.services.EmployeeService;

import java.io.IOException;

@WebServlet("/addNewEmployee")
public class AddNewEmployeeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String position = req.getParameter("position");
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        if(!(EmployeeService.service().loginIsExist(login))) {
            Employee employee = new Employee.Builder().
                    withFirstName(firstName).
                    withLastName(secondName).
                    withPosition(position).
                    withLogin(login).
                    withPass(pass).built();
            EmployeeService.service().create(employee);
            req.setAttribute("addEmployee", 1);
            getServletContext().getRequestDispatcher("/WEB-INF/view/addNewEmployee.jsp").forward(req, resp);
        }else {
            req.setAttribute("addEmployeeError",1);
            getServletContext().getRequestDispatcher("/WEB-INF/view/addNewEmployee.jsp").forward(req, resp);
        }
    }
}
