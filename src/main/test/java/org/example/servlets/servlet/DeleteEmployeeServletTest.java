package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class DeleteEmployeeServletTest {

    @Test(expected = NumberFormatException.class)
    public void doPost() throws ServletException, IOException {
        DeleteBillServlet deleteBillServlet = new DeleteBillServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("empIdDelete")).thenReturn("1");
        when(request.getRequestURI()).thenReturn("/deleteEmployee");

        deleteBillServlet = spy(DeleteBillServlet.class);
        deleteBillServlet.init();
        verify(deleteBillServlet,times(1)).init();
        deleteBillServlet.doPost(request,response);
        verify(deleteBillServlet,times(1)).doPost(request,response);
        verify(response,times(1)).sendRedirect(any());
    }
}