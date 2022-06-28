package org.example.servlets.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ShowWarehouseAsTest {

    @Test
    public void doGet() throws ServletException, IOException {
        ShowWarehouseAs showWarehouse = mock(ShowWarehouseAs.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("sort")).thenReturn("ACS");
        when(request.getParameter("page")).thenReturn("1");
        showWarehouse.doGet(request,response);
        verify(showWarehouse,times(1)).doGet(request,response);
    }
}