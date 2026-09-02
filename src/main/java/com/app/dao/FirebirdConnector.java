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
        config.setUsername(ConnectionCliente.USER);
        config.setPassword(ConnectionCliente.PASSWORD);
        config.setDriverClassName("org.firebirdsql.jdbc.FBDriver");

        config.setMaximumPoolSize(10);      // ajusta según carga esperada
        config.setMinimumIdle(2);
        config.setConnectionTimeout(5000);  // ms
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
