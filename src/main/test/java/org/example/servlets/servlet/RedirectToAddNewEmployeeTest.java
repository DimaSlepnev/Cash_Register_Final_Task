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

public class RedirectToAddNewEmployeeTest {

    @Test(expected = IllegalStateException.class)
    public void doGet() throws ServletException, IOException {
        RedirectToAddNewEmployee newEmployeeServlet = mock(RedirectToAddNewEmployee.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletConfig config = mock(ServletConfig.class);
        ServletContext context = mock(ServletContext.class);
        doReturn(context).when(request).getServletContext();
        newEmployeeServlet.init(config);
        newEmployeeServlet = spy(RedirectToAddNewEmployee.class);
        newEmployeeServlet.doGet(request,response);
        verify(request,times(1)).getRequestDispatcher("/WEB-INF/view/addNewEmployee.jsp");
    }
}