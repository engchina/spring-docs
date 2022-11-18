package com.example.springbootfilter.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author engchina
 */
@WebFilter(urlPatterns={"/*"})
@Slf4j
public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("### in FirstFilter.init() ###");
//        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        log.info("### in FirstFilter.destroy() ###");
//        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("### in FirstFilter.doFilter() ###");
        chain.doFilter(request, response);
    }
}
