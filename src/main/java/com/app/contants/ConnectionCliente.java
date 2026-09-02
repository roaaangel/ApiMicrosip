package com.app.contants;

public final class ConnectionCliente {
    /*public static final String CONNECTION_BD =
            "jdbc:firebirdsql:localhost/3050:/usr/local/firebird/databases/Bryka.fdb?encoding=WIN1252";
    public static final String USSER = "SYSDBA";
    public static final String PASSWORD = "masterkey";*/
    
    // Conexion BRIKA SAN JUAN DEL RIO
    /*public static final String CONNECTION_BD = "jdbc:firebirdsql:localhost/3050:C:/Microsip datos/respaldo.fdb";
    public static final String USSER = "SYSDBA"; 
    public static final String PASSWORD = "PTC1640U";*/
    
    // Conexoón FERREMAYOREO BARBADO TOLUCA
    /*public static String CONNECTION_BD = "jdbc:firebirdsql:192.168.1.3/3050:C:/Microsip datos/FERREMAYOREO BARBADO.FDB";
    public static String USSER = "SYSDBA"; 
    public static String PASSWORD = "R3tr4.7c";*/
    
    // Conexoón FERREMAYOREO BARBADO QA
    public static String CONNECTION_BD = "jdbc:firebirdsql:192.168.1.242/3050:C:/Microsip datos/FERREMAYOREO QA.FDB";
    public static String USSER = "SYSDBA"; 
    public static String PASSWORD = "masterkey";
   

    private ConnectionCliente() {
        // clase utilitaria, no instanciable
    }
}
