package com.app.dao;

import com.app.contants.ConnectionCliente;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public final class FirebirdConnector {

    private static final Logger logger = LoggerFactory.getLogger(FirebirdConnector.class);
    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(ConnectionCliente.CONNECTION_BD);
        config.setUsername(ConnectionCliente.USSER);
        config.setPassword(ConnectionCliente.PASSWORD);
        config.setDriverClassName("org.firebirdsql.jdbc.FBDriver");

        // 1. Eleva el pool para absorber el pico
        config.setMaximumPoolSize(30);      // En lugar de 10, permite responder a más peticiones a la vez

        // 2. Manten las conexiones base bajas cuando no hay tráfico
        config.setMinimumIdle(5);           

        // 3. Da un margen más amplio de espera para evitar errores de Timeout en momentos de alta carga
        config.setConnectionTimeout(10000);  // 10 segundos en lugar de 5s
        config.setIdleTimeout(60000);
        config.setMaxLifetime(1800000);
        config.setPoolName("FirebirdPool");

        dataSource = new HikariDataSource(config);
        logger.info("Pool de conexiones Firebird inicializado correctamente.");
    }

    private FirebirdConnector() {
        // clase utilitaria, no instanciable
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("No se pudo obtener conexión de la base de datos: {}", e.getMessage(), e);
            return null;
        }
    }

    public static void shutdown() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            logger.info("Pool de conexiones Firebird cerrado.");
        }
    }
}
