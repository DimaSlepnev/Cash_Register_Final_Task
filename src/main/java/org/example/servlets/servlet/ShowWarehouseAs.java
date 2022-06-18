package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Employee;
import org.example.model.Warehouse;
import org.example.services.EmployeeService;
import org.example.services.WarehouseService;

import java.io.IOException;
import java.util.List;

@WebServlet("/showWarehouseAs")
public class ShowWarehouseAs extends HttpServlet {
    String temp;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sortingBy = req.getParameter("sort");

        int pageid = 0;
        try {
            String spageid = req.getParameter("page");
            pageid = Integer.parseInt(spageid);
        } catch (Exception e) {
            pageid = 1;
        }

        int total = 5;
        if (pageid == 1) {
        } else {
            pageid = pageid - 1;
            pageid = pageid * total + 1;
        }
        int totalPages = EmployeeService.service().numberOfRows() / total;
        if (EmployeeService.service().numberOfRows() % total != 0)
            totalPages++;
        if (sortingBy != null) {
            temp = sortingBy;
        }
        List<Warehouse> warehouses = WarehouseService.service().sortingBy(temp, pageid, total);
        req.setAttribute("warehouses", warehouses);
        req.setAttribute("numberOfPages", totalPages);
        getServletContext().getRequestDispatcher("/WEB-INF/view/showWarehouse.jsp").forward(req, resp);
    }
}
