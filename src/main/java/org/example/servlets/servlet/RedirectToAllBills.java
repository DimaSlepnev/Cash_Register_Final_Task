package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Bill;
import org.example.services.BillService;

import java.io.IOException;
import java.util.List;
import java.util.logging.*;

@WebServlet("/redirectToAllBills")
public class RedirectToAllBills extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("bills", BillService.service().findAll());
        getServletContext().getRequestDispatcher("/WEB-INF/view/showAllBills.jsp").forward(req,resp);
    }
}
