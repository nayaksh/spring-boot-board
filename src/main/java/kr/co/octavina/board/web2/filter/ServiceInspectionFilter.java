package kr.co.octavina.board.web2.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
@Order(0)
@Slf4j
public class ServiceInspectionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.info("Logging Request  {} : {}", request.getMethod(), request.getRequestURI());

        if (request.getRequestURI().contains("inspection")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("/inspection");
        }
    }
}
