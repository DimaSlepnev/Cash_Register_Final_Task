package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Bill;
import org.example.model.Warehouse;
import org.example.services.BillService;
import org.example.services.WarehouseService;

import java.io.IOException;

@WebServlet("/createBill")
public class CreateBillServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String createBillPath = "/WEB-INF/view/createBill.jsp";
        String body = req.getParameter("body");
        String amountStr = req.getParameter("amount");
        String priceStr = req.getParameter("price");
        int amount = Integer.parseInt(amountStr);
        int price = Integer.parseInt(priceStr);
        try {
            int productId = Integer.parseInt(body);
            Warehouse warehouse = WarehouseService.service().findModelById(productId);
            if (warehouse != null && amount <= warehouse.getAmount()) {
                Bill bill = new Bill.Builder().
                        withProductId(productId).
                        withBody(warehouse.getProduct()).
                        withAmount(amount).
                        withPrice(price).
                        withConfirmation(false).build();
                if(amount == warehouse.getAmount()){
                    WarehouseService.service().deleteById(bill.getProductId());
                }else {
                    WarehouseService.service().updateAmount(-amount, warehouse.getProduct());
                }
                BillService.service().create(bill);
                req.setAttribute("createBill",1);
                getServletContext().getRequestDispatcher(createBillPath).forward(req, resp);
            } else {
                req.setAttribute("errorBill", 1);
                getServletContext().getRequestDispatcher(createBillPath).forward(req, resp);
            }
        } catch (Exception e) {
            Warehouse warehouse = WarehouseService.service().findByName(body);
            if (warehouse != null && amount <= warehouse.getAmount()) {
                Bill bill = new Bill.Builder().
                        withProductId(warehouse.getId()).
                        withBody(body).
                        withAmount(amount).
                        withPrice(price).
                        withConfirmation(false).build();
                if(amount == warehouse.getAmount()){
                    WarehouseService.service().deleteById(bill.getProductId());
                }else {
                    WarehouseService.service().updateAmount(-amount, warehouse.getProduct());
                }
                BillService.service().create(bill);
                req.setAttribute("createBill",1);
                getServletContext().getRequestDispatcher(createBillPath).forward(req, resp);
            } else {
                req.setAttribute("errorBill", 1);
                getServletContext().getRequestDispatcher(createBillPath).forward(req, resp);
            }
        }
    }
}

