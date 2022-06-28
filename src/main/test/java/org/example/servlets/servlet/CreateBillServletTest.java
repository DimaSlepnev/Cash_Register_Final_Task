package org.example.servlets.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CreateBillServletTest {
    @Mock
    RequestDispatcher requestDispatcher;

    @Test(expected = NullPointerException.class)
    public void doPost() throws ServletException, IOException {
        CreateBillServlet createBillServlet = new CreateBillServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletConfig config = mock(ServletConfig.class);
        ServletContext context = mock(ServletContext.class);
        doReturn(context).when(request).getServletContext();
        when(request.getParameter("body")).thenReturn("body");
        when(request.getParameter("amount")).thenReturn("10");
        when(request.getParameter("price")).thenReturn("100");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getRequestURI()).thenReturn("/createBill");

        createBillServlet.init(config);
        createBillServlet.doPost(request,response);
        when(config.getServletContext()).thenReturn(context);
        request = spy(HttpServletRequest.class);
        verify(request,times(0)).setAttribute(any(),any());
        verify(request,times(0)).getRequestDispatcher(any());
    }
}