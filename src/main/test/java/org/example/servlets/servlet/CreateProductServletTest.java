package org.example.servlets.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.Employee;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CreateProductServletTest {

    @Test(expected = NullPointerException.class)
    public void doPost() throws ServletException, IOException {
        CreateProductServlet createProductServlet = new CreateProductServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ServletConfig config = mock(ServletConfig.class);
        ServletContext context = mock(ServletContext.class);
        doReturn(context).when(request).getServletContext();
        when(request.getParameter("product")).thenReturn("product");
        when(request.getParameter("amount")).thenReturn("amount");
        when(session.getAttribute("employee")).thenReturn(mock(Employee.class));
        when(request.getRequestURI()).thenReturn("/createProduct");

        createProductServlet = spy(CreateProductServlet.class);
        createProductServlet.init(config);
        createProductServlet.doPost(request,response);
        verify(createProductServlet,times(1)).doPost(request,response);

        request = spy(HttpServletRequest.class);
        request.setAttribute("createProduct",1);
        verify(request,times(1)).setAttribute(any(),any());
    }
}