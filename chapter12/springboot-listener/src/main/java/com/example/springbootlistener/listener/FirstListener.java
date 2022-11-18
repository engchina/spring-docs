package com.example.springbootlistener.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Slf4j
public class FirstListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("### in FirstListener.contextInitialized() ###");
        log.info(sce.getServletContext().getServerInfo());
//        ServletContextListener.super.contextInitialized(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("### in FirstListener.contextDestroyed() ###");
//        ServletContextListener.super.contextDestroyed(sce);
    }
}
