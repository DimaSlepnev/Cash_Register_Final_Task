package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Bill;
import org.example.services.BillService;


import java.io.IOException;
@WebServlet("/confirmBill")
public class ConfirmBillServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String confirmation = req.getParameter("confirmation");
        String idStr = req.getParameter("id");
        Bill bill = BillService.service().findModelById(Integer.parseInt(idStr));
        if(confirmation.equals("yes") || confirmation.equals("так")) {
            BillService.service().updateConfirmationById(bill.getId());
            req.setAttribute("billConfirm", 1);
        }
        req.setAttribute("bills",BillService.service().findAll());
        getServletContext().getRequestDispatcher("/WEB-INF/view/showAllBills.jsp").forward(req,resp);
    }
}
