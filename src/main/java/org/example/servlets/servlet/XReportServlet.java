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

@WebServlet("/xReport")
public class XReportServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int xReport = 0;
        List<Bill> bills = BillService.service().findAll();
        for (Bill bill: bills) {
            if(bill.isConfirmation()){
                xReport+=bill.getPrice();
            }
        }
        req.setAttribute("xReport",xReport);
        getServletContext().getRequestDispatcher("/WEB-INF/view/xReportPopUp.jsp").forward(req,resp);
    }
}
