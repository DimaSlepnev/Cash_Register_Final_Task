package org.example.servlets.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.jstl.core.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;
@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(LocaleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Initialization of {}", LocaleFilter.class.getName());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            HttpServletRequest req = (HttpServletRequest) servletRequest;
            HttpServletResponse resp = (HttpServletResponse) servletResponse;

            String locale = null;

            final Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("lang")) {
                        locale = cookie.getValue();
                    }
                }
            }
            if (req.getParameter("lang") != null) {
                locale = req.getParameter("lang");
                Cookie cookie = new Cookie("lang", locale);
                resp.addCookie(cookie);
            }
            if (locale == null) locale = "en";
            Locale localeObj = new Locale(locale);
            Config.set(req.getSession(), Config.FMT_LOCALE, localeObj);

            filterChain.doFilter(req, resp);
        }

    @Override
    public void destroy() {
        logger.debug("Destroy {}",LocaleFilter.class.getName());
    }
}
