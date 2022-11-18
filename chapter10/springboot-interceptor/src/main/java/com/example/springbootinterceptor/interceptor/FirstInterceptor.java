package com.example.springbootinterceptor.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author engchina
 */
@Component
@Slf4j
public class FirstInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("### in FirstInterceptor.preHandle() ###");
        log.info("URI={} {}", request.getRequestURL().toString(), request.getRequestURI());
        return true;
//        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("### in FirstInterceptor.postHandle() ###");
        log.info("URI={} {}", request.getRequestURL().toString(), request.getRequestURI());
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("### in FirstInterceptor.afterCompletion() ###");
        log.info("URI={} {}", request.getRequestURL().toString(), request.getRequestURI());
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
