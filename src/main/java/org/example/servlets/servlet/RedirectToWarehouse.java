package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.services.WarehouseService;
import org.example.model.Warehouse;

import java.io.IOException;
import java.util.List;

@WebServlet("/redirectToWarehouse")
public class RedirectToWarehouse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("warehouses", WarehouseService.service().findAll());
        getServletContext().getRequestDispatcher("/WEB-INF/view/showWarehouse.jsp").forward(req,resp);
    }
}
