package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Bill;
import org.example.services.BillService;

import java.io.IOException;

@WebServlet("/redirectToEditBill")
public class RedirectToEditBill extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("idEdit");
        int id = Integer.parseInt(idStr);
        Bill bill = BillService.service().findModelById(id);
        req.setAttribute("bill", bill);
        getServletContext().getRequestDispatcher("/WEB-INF/view/editBill.jsp").forward(req, resp);
    }
}
