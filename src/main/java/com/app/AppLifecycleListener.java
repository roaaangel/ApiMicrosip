package com.app;

import com.app.dao.FirebirdConnector;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppLifecycleListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // el pool ya se inicializa de forma estática (lazy) al primer uso
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        FirebirdConnector.shutdown();
    }
}
