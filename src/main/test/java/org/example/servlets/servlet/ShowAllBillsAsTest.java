package org.example.servlets.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowAllBillsAsTest {

    @Test
    public void doGet() {
        ShowAllBillsAs allBills = new ShowAllBillsAs();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("sort")).thenReturn("ACS");
        when(request.getParameter("page")).thenReturn("1");
    }
}