package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class ShowAllEmployeesAsTest {

    @Test
    public void doGet() throws ServletException, IOException {
        ShowAllEmployeesAs allEmployees = mock(ShowAllEmployeesAs.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("sort")).thenReturn("ACS");
        when(request.getParameter("page")).thenReturn("1");
        allEmployees.doGet(request,response);
        verify(allEmployees,times(1)).doGet(request,response);
    }
}