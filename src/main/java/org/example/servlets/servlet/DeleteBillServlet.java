package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Bill;
import org.example.model.Employee;
import org.example.model.Warehouse;
import org.example.services.BillService;
import org.example.services.WarehouseService;

import java.io.IOException;

@WebServlet("/deleteBill")
public class DeleteBillServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        Bill bill = BillService.service().findModelById(Integer.parseInt(idStr));
        Warehouse warehouse = WarehouseService.service().findModelById(bill.getProductId());
        Employee employee = (Employee) req.getSession().getAttribute("employee");
        int empId = employee.getId();
        if(warehouse != null) {
            WarehouseService.service().updateAmount(bill.getAmount(), warehouse.getProduct());
        } else {
            Warehouse newWarehouse = new Warehouse.Builder()
                    .withProduct(bill.getBody())
                    .withAmount(bill.getAmount())
                    .withExpertId(empId).build();
          WarehouseService.service().create(newWarehouse);
        }
        BillService.service().deleteById(bill.getId());
        req.setAttribute("bills", BillService.service().findAll());
        req.setAttribute("deleteBill",1);
        resp.sendRedirect("redirectToAllBills");
    }
}
