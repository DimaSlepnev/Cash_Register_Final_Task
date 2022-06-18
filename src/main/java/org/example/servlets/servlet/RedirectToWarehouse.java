package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Employee;
import org.example.services.EmployeeService;
import org.example.services.WarehouseService;
import org.example.model.Warehouse;

import java.io.IOException;
import java.util.List;

@WebServlet("/redirectToWarehouse")
public class RedirectToWarehouse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        int totalPages = WarehouseService.service().numberOfRows() / total;
        if (WarehouseService.service().numberOfRows() % total != 0)
            totalPages++;
        List<Warehouse> warehouse = WarehouseService.service().findALlWithPagination(pageid, total);
        req.setAttribute("warehouses", warehouse);
        req.setAttribute("numberOfPages", totalPages);
        getServletContext().getRequestDispatcher("/WEB-INF/view/showWarehouse.jsp").forward(req,resp);
    }
}
