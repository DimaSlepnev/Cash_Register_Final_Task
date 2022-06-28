package org.example.servlets.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class LogoutServletTest {
    @Mock
    ServletContext context;

    @Test
    public void doGet() throws ServletException, IOException {
        LogoutServlet logoutServlet = new LogoutServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ServletConfig config = mock(ServletConfig.class);
        when(request.getSession()).thenReturn(session);
        when(request.getSession(false)).thenReturn(session);
        when(request.getRequestURI()).thenReturn("/logout");
        when(config.getServletContext()).thenReturn(context);
        logoutServlet = spy(logoutServlet.getClass());
        logoutServlet.init(config);
        verify(logoutServlet,times(1)).init(config);
    }
}