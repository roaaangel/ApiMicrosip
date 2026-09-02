package com.app.contants;

public final class ConnectionCliente {
    public static final String CONNECTION_BD =
            "jdbc:firebirdsql:localhost/3050:/usr/local/firebird/databases/Bryka.fdb?encoding=WIN1252";
    public static final String USER = "SYSDBA";
    public static final String PASSWORD = "masterkey";
   

    private ConnectionCliente() {
        // clase utilitaria, no instanciable
    }
}
