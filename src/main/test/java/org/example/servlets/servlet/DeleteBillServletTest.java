package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Bill;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class DeleteBillServletTest {

    @Test(expected = NullPointerException.class)
    public void doPost() throws ServletException, IOException {
        DeleteBillServlet deleteBillServlet = mock(DeleteBillServlet.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("id")).thenReturn("1");
        when(request.getRequestURI()).thenReturn("/deleteBill");

        deleteBillServlet = spy(DeleteBillServlet.class);
        deleteBillServlet.init();
        verify(deleteBillServlet,times(1)).init();
        deleteBillServlet.doPost(request,response);
        verify(deleteBillServlet,times(1)).doPost(request,response);
    }
}