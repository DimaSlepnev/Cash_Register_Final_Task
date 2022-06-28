package org.example.servlets.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConfirmBillServletTest {

    @Test
    public void doPost() throws ServletException, IOException {
        ConfirmBillServlet confirmBillServlet = new ConfirmBillServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("confirmation")).thenReturn("false");
        when(request.getParameter("id")).thenReturn("1");
        confirmBillServlet.doPost(request,response);
        verify(request,times(1)).setAttribute(any(),any());
        verify(response,times(1)).sendRedirect(any());
    }
}