package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Employee;
import org.example.model.Warehouse;
import org.example.services.WarehouseService;

import java.io.IOException;

@WebServlet("/createProduct")
public class CreateProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String product = req.getParameter("product");
        String amountStr = req.getParameter("amount");
        Employee employee = (Employee) req.getSession().getAttribute("employee");
        int amount = Integer.parseInt(amountStr);
        int expertId = employee.getId();
        if (WarehouseService.service().isExist(product).getProduct().equals(product)) {
            WarehouseService.service().updateAmount(amount, product);
        } else {
            Warehouse warehouse = new Warehouse.Builder().withProduct(product).withAmount(amount).withExpertId(expertId).build();
            WarehouseService.service().create(warehouse);
        }
        req.setAttribute("createProduct",1);
        getServletContext().getRequestDispatcher("/WEB-INF/view/createProduct.jsp").forward(req, resp);
    }
}
