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

@WebServlet("/editBill")
public class EditBillServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String editBillPath = "/WEB-INF/view/editBill.jsp";
        final String allBillsPath = "/WEB-INF/view/showAllBills.jsp";
        String idStr = req.getParameter("id");
        String amountStr = req.getParameter("amount");
        String priceStr = req.getParameter("price");
        int amount = Integer.parseInt(amountStr);
        int id = Integer.parseInt(idStr);
        int price = Integer.parseInt(priceStr);
        int amountWas = Integer.parseInt(req.getParameter("amountWas"));
        Bill bill = BillService.service().findModelById(id);
        try {
            int productId = Integer.parseInt(bill.getBody());
            Warehouse warehouse = WarehouseService.service().findModelById(productId);
            if (warehouse != null && amount <= warehouse.getAmount()) {
                BillService.service().updateAmountAndPriceById(amount,price, bill.getId());
                if(amount > amountWas){
                    WarehouseService.service().updateAmount(-(amount - amountWas),bill.getBody());
                }else {
                    WarehouseService.service().updateAmount(amountWas - amount,bill.getBody());
                }
                req.setAttribute("editBill",1);
                req.setAttribute("bills", BillService.service().findAll());
                getServletContext().getRequestDispatcher(allBillsPath).forward(req, resp);
            } else {
                req.setAttribute("errorEditBill",1);
                req.setAttribute("bills", BillService.service().findAll());
                getServletContext().getRequestDispatcher(editBillPath).forward(req, resp);
            }
        } catch (Exception e) {
            Warehouse warehouse = WarehouseService.service().findByName(bill.getBody());
            if (warehouse != null && amount <= warehouse.getAmount()) {
                BillService.service().updateAmountAndPriceById(amount,price, bill.getId());
                if(amount > amountWas){
                    WarehouseService.service().updateAmount(-(amount - amountWas),bill.getBody());
                }else {
                    WarehouseService.service().updateAmount(amountWas - amount,bill.getBody());
                }
                req.setAttribute("editBill",1);
                req.setAttribute("bills", BillService.service().findAll());
                getServletContext().getRequestDispatcher(allBillsPath).forward(req, resp);
            } else {
                req.setAttribute("errorEditBill",1);
                req.setAttribute("bills", BillService.service().findAll());
                getServletContext().getRequestDispatcher(editBillPath).forward(req, resp);
            }
        }
    }
}