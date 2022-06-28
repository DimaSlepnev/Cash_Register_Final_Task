package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EditBillServletTest {

    @Test(expected = NumberFormatException.class)
    public void doPost() throws ServletException, IOException {
        EditBillServlet editBillServlet = new EditBillServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("id")).thenReturn("0");
        when(request.getParameter("amount")).thenReturn("10");
        when(request.getParameter("price")).thenReturn("60");
        when(request.getRequestURI()).thenReturn("/editBill");
        editBillServlet.doPost(request,response);
        verify(request,times(1)).setAttribute(any(),any());
    }
}