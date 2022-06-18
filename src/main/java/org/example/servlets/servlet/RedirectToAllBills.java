package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Bill;
import org.example.services.BillService;
import org.example.services.EmployeeService;

import java.io.IOException;
import java.util.List;

@WebServlet("/redirectToAllBills")
public class RedirectToAllBills extends HttpServlet {
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
        int totalPages = BillService.service().numberOfRows() / total;
        if (BillService.service().numberOfRows() % total != 0)
            totalPages++;
        List<Bill> bills = BillService.service().findALlWithPagination(pageid, total);
        req.setAttribute("bills", bills);
        req.setAttribute("numberOfPages", totalPages);
        getServletContext().getRequestDispatcher("/WEB-INF/view/showAllBills.jsp").forward(req,resp);
    }
}
