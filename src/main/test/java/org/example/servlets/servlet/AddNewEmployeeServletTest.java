package org.example.servlets.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AddNewEmployeeServletTest {

    @Test(expected = NullPointerException.class)
    public void doPost() throws ServletException, IOException {
        AddNewEmployeeServlet addNewEmployeeServlet = new AddNewEmployeeServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("firstName")).thenReturn("name");
        when(request.getParameter("secondName")).thenReturn("surname");
        when(request.getParameter("position")).thenReturn("position");
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("pass")).thenReturn("pass");
        when(request.getRequestURI()).thenReturn("/addNewEmployee");
        ServletConfig config = mock(ServletConfig.class);
        ServletContext context = mock(ServletContext.class);


        addNewEmployeeServlet.init(config);
        addNewEmployeeServlet.doPost(request,response);

        verify(request,times(1)).setAttribute(any(),any());
        verify(request,times(1)).getRequestDispatcher(any());
        verify(response,times(0)).sendRedirect(any());
    }
}