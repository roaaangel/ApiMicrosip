/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import com.app.models.ConceptoCuentaXCobrar;
import com.app.models.CondicionPago;
import com.app.models.ConfiguracionMobil;
import com.app.models.PedidoNuevo;
import com.app.models.PedidoNuevoDetalle;
import com.app.models.PrecioEmpresa;
import com.app.models.ReporteMovil;
import com.app.models.RolClaveArticulo;
import com.app.models.VentaUtilidad;
import com.app.models.ViaEmbarque;
import com.app.models.almacenes.Almacen;
import com.app.models.articuloalmacen.ArticuloAlmacen;
import com.app.models.articulos.Articulo;
import com.app.models.articulos.ArticuloVolumen;
import com.app.models.bancos.Banco;
import com.app.models.clientes.Cliente;
import com.app.models.cobradores.Cobrador;
import com.app.models.cobranza.Cobranza;
import com.app.models.datosempresa.DatosEmpresa;
import com.app.models.metodospago.MetodoPago;
import com.app.models.promociones.Promocion;
import com.app.models.sucursales.Sucursal;
import com.app.models.vendedores.Vendedor;
import com.app.models.vendedorescobradores.VendedorCobrador;
import com.app.servicios.Resources;
import com.app.utilerias.Utileria;
import com.app.utilerias.fecha.Fecha;
import com.app.utilerias.fecha.FechaHora;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
/**
 *
 * @author angel
 */
public class ControladorDistribuidora {
    protected Connection conexion;
    protected Statement instruccion;

    
    private Utileria utileria = new Utileria();
    private Gson gson;
    private FechaHora fechaHora;
    private Fecha fechaInicioFin;
    
    public ControladorDistribuidora() {
        try {            
            //Class.forName("org.firebirdsql.jdbc.FBDriver");                    
            
            conexion = DriverManager.getConnection(
            // Conexion ESTEBAN_VERACRUZ
            //"jdbc:firebirdsql:native:192.168.0.3/3050:E:/Microsip datos/ESTEBAN VILLASANTE ALVAREZ.fdb", "SYSDBA", "20kc34bX2");
            "jdbc:firebirdsql:native:192.168.0.3/3050:E:/Microsip datos/DISTRIBUIDORA FERESMAR.fdb", "SYSDBA", "20kc34bX2");
            instruccion = conexion.createStatement();
            
            gson = new GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("MM/dd/yyyy")
            //.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
            //gson = new Gson();
            fechaHora = new FechaHora();
            fechaInicioFin = fechaHora.fechaInicioFin();
        } catch (SQLException e) {
            System.out.println("Error SQLException: " + e);
        } catch (Exception e) {
            System.out.println("Error Exception: " + e.getMessage());
        }
    }
    
    private static void tryConfigureJNA() {
        String jnaPath = System.getProperty("jna.library.path");
        if (jnaPath == null || jnaPath.isEmpty()) {
            Path path = Paths.get("fb").toAbsolutePath();
            System.out.println("Attempting to set jna.library.path to: " + path);
            System.setProperty("jna.library.path", path.toString());
        }
    }
    
    public ConfiguracionMobil configuracionMicrosip() throws SQLException{
        try {                    
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT MICROSIP_2020, SUCURSAL_ID, PRECIO_EMPRESA_ID, CONCEPTO_CUENTA_X_COBRAR_ID, " +
                    "CONDICION_PAGO_ID, ROL_ART_CLAVE_PRINCIPAL_ID, ROL_ART_CLAVE_ALTERNA_ID, " +
	            "ROL_ART_CODIGO_BARRA_ID, ROL_ART_CODIGO_BARRA_INNER_ID, ROL_ART_CODIGO_BARRA_MASTER_ID, " +
                    "RESTRINGE_CLIENTES " +
                    "FROM CONFIGURACION_MOBIL"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            
            ConfiguracionMobil configuracionMobil = new ConfiguracionMobil();
            while (resultSet.next()) {
                configuracionMobil.setMicrosip2020(resultSet.getInt("MICROSIP_2020"));
                configuracionMobil.setSucursalId(resultSet.getInt("SUCURSAL_ID"));
                configuracionMobil.setPrecioEmpresaId(resultSet.getInt("PRECIO_EMPRESA_ID"));
                configuracionMobil.setConceptoCCId(resultSet.getInt("CONCEPTO_CUENTA_X_COBRAR_ID"));
                configuracionMobil.setCondicionPagoId(resultSet.getInt("CONDICION_PAGO_ID"));
                configuracionMobil.setRolArticuloClavePrincipalId(resultSet.getInt("ROL_ART_CLAVE_PRINCIPAL_ID"));
                configuracionMobil.setRolArticuloClaveAlternaId(resultSet.getInt("ROL_ART_CLAVE_ALTERNA_ID"));
                configuracionMobil.setRolArticuloCodigoBarraId(resultSet.getInt("ROL_ART_CODIGO_BARRA_ID"));
                configuracionMobil.setRolArticuloCodigoBarraInnerId(resultSet.getInt("ROL_ART_CODIGO_BARRA_INNER_ID"));
                configuracionMobil.setRolArticuloCodigoBarraMasterId(resultSet.getInt("ROL_ART_CODIGO_BARRA_MASTER_ID"));
                configuracionMobil.setMicrosip2020(resultSet.getInt("MICROSIP_2020"));
                configuracionMobil.setSucursalId(resultSet.getInt("SUCURSAL_ID"));
                //configuracionMobil.setRestringeClientes(resultSet.getInt("RESTRINGE_CLIENTES"));
            }            
            return configuracionMobil;           
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());           
            return null;
        } 
    }
    
    public void validaSerieVendedor(){
        /*
        SELECT * FROM VENDEDORES WHERE VENDEDOR_ID NOT IN (SELECT VENDEDOR_ID FROM VENDEDORES_SERIES_FOLIOS)

        
        SELECT VSF.VENDEDOR_ID, VSF.SERIE, VSF.FOLIO, V.NOMBRE FROM VENDEDORES_SERIES_FOLIOS VSF
        INNER JOIN VENDEDORES V ON V.VENDEDOR_ID = VSF.VENDEDOR_ID 
        WHERE VSF.SERIE = 'A'
        */
    }
    
    public int agregaMovimiento() throws SQLException {
        // instanciamos el objeto callable
        CallableStatement cstmt = conexion.prepareCall("{call GEN_DOCTO_ID()}");        
        // registramos el parametro de retorno (si fueran mas, repetimos la linea cambiando el nro de orden del parametro)
        cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
        // ejecutamos
        cstmt.execute();
        System.out.println("?????????????");
        // mostramos al usuario el codigo creado
        int movimientoVacio = cstmt.getInt(1);
        System.out.println("Se creo el Cliente con Codigo: " + movimientoVacio);
        cstmt.close();
        return movimientoVacio;        
    }
    
  
    
    /*public boolean insertMaeDtma02Movil(String jsonString, int numeroDeMovimiento) {
        Resources.logger.info("==================================================");
        Resources.logger.info("Insertando en la tabla detalle del pedido: " + jsonString);
        
        try {                                    
            JsonElement json = new JsonParser().parse(jsonString);
            JsonArray array = json.getAsJsonArray();
            Iterator iterator = array.iterator();
            List<MaeDtma02> details = new ArrayList<MaeDtma02>();

                                    
            int consecutivo = 0;
            while (iterator.hasNext()) {                                
                consecutivo++;
                JsonElement json2 = (JsonElement) iterator.next();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<MaeDtma02>() {}.getType();
                DetallePedido contact = gson.fromJson(json2, collectionType);
                 
                Resources.logger.info(consecutivo + " Detalle del pedido");
                //Actualiza la tabla/
                Utilerias utilerias = new Utilerias();
                PreparedStatement preparedStatementObj = conexion.prepareStatement(
                "INSERT INTO DOCTOS_VE_DET (DOCTO_VE_DET_ID, DOCTO_VE_ID, CLAVE_ARTICULO, ARTICULO_ID, "
                        + "UNIDADES, UNIDADES_COMPROM, "
                        + "UNIDADES_SURT_DEV, UNIDADES_A_SURTIR, "
                        + "PRECIO_UNITARIO, PCTJE_DSCTO, DSCTO_ART, PCTJE_DSCTO_CLI, "
                        + "DSCTO_EXTRA, PCTJE_DSCTO_VOL, PCTJE_DSCTO_PROMO, PRECIO_TOTAL_NETO,"
                        + " PCTJE_COMIS, ROL, POSICION)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                );
                preparedStatementObj.setInt(1, -1);//?????
                preparedStatementObj.setInt(2, numeroDeMovimiento);
                preparedStatementObj.setString(3, contact.getNumart());        //JALAR DE LA APP EL CODIGO        
                preparedStatementObj.setInt(4, contact.getArticuloId());
                preparedStatementObj.setDouble(5, contact.getCandtm());
                preparedStatementObj.setDouble(6, 0.00);                
                preparedStatementObj.setDouble(7, 0.00);
                preparedStatementObj.setDouble(8, 0.00);                                             
                preparedStatementObj.setDouble(9, contact.getPrudtm());//PRECIO SIN IVA
                preparedStatementObj.setDouble(10, contact.getDesdtm());//% POR PROMOION O POR VOLUMEN
                preparedStatementObj.setDouble(11, contact.getVivadtm()); //VALOR EN MONEDA DEL DESCUENTO   100-10%=10.00
                preparedStatementObj.setDouble(12, 0.00);                
                preparedStatementObj.setDouble(13, 0.00);
                preparedStatementObj.setDouble(14, 0.00);                
                preparedStatementObj.setDouble(15, contact.getDesdtm());                
                //preparedStatementObj.setDouble(16, contact.getPrudtm() * contact.getCandtm());                 //TODOS LOS VALORES SON SIN IMPUESTOS
                preparedStatementObj.setDouble(16, (contact.getPrudtm() - contact.getVivadtm()) * contact.getCandtm());                 //TODOS LOS VALORES SON SIN IMPUESTOS
                preparedStatementObj.setDouble(17, 0.00);
                preparedStatementObj.setString(18, "N");
                preparedStatementObj.setInt(19, consecutivo);
                preparedStatementObj.executeUpdate();                                
            }            
            return true;
        } catch (Exception e) {
            System.out.println(e);
            Resources.logger.error("Sucedio una excepcion al insertar el detalle del pedido: " + e.getMessage());
            return false;
        }
    }*/
    
    public boolean createAbonos (String jsonString) throws SQLException {
        Resources.logger.info("Entrando a crear la cobranza:" + jsonString);
        
        ConfiguracionMobil configuracionMobil = configuracionMicrosip();
        
        Utilerias utilerias = new Utilerias();
        try{                                                             
            JsonElement json = new JsonParser().parse(jsonString);
            JsonArray array = json.getAsJsonArray();
            Iterator iterator = array.iterator();
            while (iterator.hasNext()) {
                JsonElement json2 = (JsonElement) iterator.next();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<ReporteMovil>() {
                }.getType();
                ReporteMovil reporteMovil = gson.fromJson(json2, collectionType);                
                
                conexion.setAutoCommit(false);
                
                PreparedStatement preparedStatement = conexion.prepareStatement(
                        "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                );
                ResultSet resultSet = preparedStatement.executeQuery();
                int idAutoIncremental= 0;                
                while (resultSet.next()) {
                    idAutoIncremental = resultSet.getInt("ID");                    
                } 
                
                Resources.logger.info("ID GENERADO:" +idAutoIncremental);

                /*REFACTOR
                preparedStatement = conexion.prepareStatement(
                        "SELECT CONCEPTO_CC_ID FROM CONCEPTOS_CC " +
                        "WHERE NOMBRE LIKE 'Cobro'"
                );
                resultSet = preparedStatement.executeQuery();
                int conceptoCCID = 0;
                while (resultSet.next()) {
                    conceptoCCID = resultSet.getInt("CONCEPTO_CC_ID");
                }                                

                preparedStatement = conexion.prepareStatement("SELECT COND_PAGO_ID FROM CONDICIONES_PAGO "
                        + "WHERE NOMBRE LIKE'%CREDITO 31 DIAS%'");
                resultSet = preparedStatement.executeQuery();
                int condicionPagoId = 0;
                while (resultSet.next()) {
                    condicionPagoId = resultSet.getInt("COND_PAGO_ID");
                }
                */
                // CREO Y ANO SE USARA ELIMINAR SI ES ASI
                preparedStatement = conexion.prepareStatement("SELECT RIGHT(MAX(C.folio),8) AS FOLIO FROM DOCTOS_CC C WHERE C.ESTATUS ='P' AND C.FOLIO LIKE 'Z%'");
                resultSet = preparedStatement.executeQuery();
                int consecutivoFolio= 0;
                while (resultSet.next()) {
                    consecutivoFolio = resultSet.getInt("FOLIO") + 1;
                }   
                
               // Resources.logger.info("CONSECUTIVO FOLIO:" +consecutivoFolio);
                
                preparedStatement = conexion.prepareStatement("SELECT FORMA_COBRO_CC_ID FROM FORMAS_COBRO_CC WHERE CLAVE_FISCAL=?");
                preparedStatement.setString(1, reporteMovil.getFormaPago());
                resultSet = preparedStatement.executeQuery();
                int FORMA_COBRO_CC_ID = 0;
                while (resultSet.next()) {
                    FORMA_COBRO_CC_ID = resultSet.getInt("FORMA_COBRO_CC_ID");
                }
                
                
                preparedStatement = conexion.prepareStatement(
                    "SELECT GEN_ID(ID_FOLIO_TEMP,1)AS ID FROM RDB$DATABASE"
                );
                resultSet = preparedStatement.executeQuery();
                int folioUltimo= 0;                
                while (resultSet.next()) {
                    folioUltimo = resultSet.getInt("ID");                    
                }
                
                Resources.logger.info("Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0"));
                
                preparedStatement = conexion.prepareStatement(
                    "SELECT LUGAR_EXPEDICION_ID FROM LUGARES_EXPEDICION"
                );
                resultSet = preparedStatement.executeQuery();
                int LUGAR_EXPEDICION_ID= 0;                
                while (resultSet.next()) {
                    LUGAR_EXPEDICION_ID = resultSet.getInt("LUGAR_EXPEDICION_ID");                    
                }
                
                //========== Actualizo el Pedido en MAEMOVCA02 ==========                 
                /*PreparedStatement preparedStatementObj = conexion.prepareStatement(
                        "INSERT INTO DOCTOS_CC(DOCTO_CC_ID,CONCEPTO_CC_ID, FOLIO, NATURALEZA_CONCEPTO, FECHA, HORA, CLAVE_CLIENTE, "
                      + "IMPORTE_COBRO, CLIENTE_ID, TIPO_CAMBIO, CANCELADO, APLICADO, DESCRIPCION, COBRADOR_ID, FORMA_EMITIDA,"
                      + "CONTABILIZADO, CONTABILIZADO_GYP, COND_PAGO_ID, SISTEMA_ORIGEN, ESTATUS, ESTATUS_ANT, ES_CFD, TIENE_ANTICIPO,"
                      + "MODALIDAD_FACTURACION, ENVIADO, FECHA_HORA_ENVIO, CFDI_CERTIFICADO, INTEG_BA, CONTABILIZADO_BA, LUGAR_EXPEDICION_ID, FECHA_APLICACION)"
                      + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                ); */
                
                String querySegunVersion = "";
                if (configuracionMobil.getMicrosip2020() == 0) {
                    querySegunVersion = "INSERT INTO DOCTOS_CC(DOCTO_CC_ID,CONCEPTO_CC_ID, FOLIO, NATURALEZA_CONCEPTO, FECHA, HORA, CLAVE_CLIENTE, "
                      + "IMPORTE_COBRO, CLIENTE_ID, TIPO_CAMBIO, CANCELADO, APLICADO, DESCRIPCION, COBRADOR_ID, FORMA_EMITIDA,"
                      + "CONTABILIZADO, CONTABILIZADO_GYP, COND_PAGO_ID, SISTEMA_ORIGEN, ESTATUS, ESTATUS_ANT, ES_CFD, TIENE_ANTICIPO,"
                      + "MODALIDAD_FACTURACION, ENVIADO, FECHA_HORA_ENVIO, CFDI_CERTIFICADO, INTEG_BA, CONTABILIZADO_BA, LUGAR_EXPEDICION_ID, FECHA_APLICACION)"
                      + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                } else if (configuracionMobil.getMicrosip2020() != 0) {
                    querySegunVersion = "INSERT INTO DOCTOS_CC(DOCTO_CC_ID,CONCEPTO_CC_ID, FOLIO, NATURALEZA_CONCEPTO, FECHA, HORA, CLAVE_CLIENTE, "
                      + "IMPORTE_COBRO, CLIENTE_ID, TIPO_CAMBIO, CANCELADO, APLICADO, DESCRIPCION, COBRADOR_ID, FORMA_EMITIDA,"
                      + "CONTABILIZADO, CONTABILIZADO_GYP, COND_PAGO_ID, SISTEMA_ORIGEN, ESTATUS, ESTATUS_ANT, ES_CFD, TIENE_ANTICIPO,"
                      + "MODALIDAD_FACTURACION, ENVIADO, FECHA_HORA_ENVIO, CFDI_CERTIFICADO, INTEG_BA, CONTABILIZADO_BA, LUGAR_EXPEDICION_ID, FECHA_APLICACION, SUCURSAL_ID)"
                      + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                }                                               
                PreparedStatement preparedStatementObj = conexion.prepareStatement(querySegunVersion);                 
                preparedStatementObj.setInt(1, idAutoIncremental);
                //preparedStatementObj.setInt(2, conceptoCCID);                                               
                preparedStatementObj.setInt(2, configuracionMobil.getConceptoCCId());//REFCATOR
                preparedStatementObj.setString(3, "Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0"));
                //preparedStatementObj.setString(3, "");
                preparedStatementObj.setString(4, "R");
                preparedStatementObj.setDate(5, utilerias.convertStringToDate(reporteMovil.getFechaAbono()));
                preparedStatementObj.setTime(6, utilerias.convertStringToTime(reporteMovil.getHoraAbono()));
                preparedStatementObj.setString(7, reporteMovil.getClaveCliente());
                //preparedStatementObj.setDouble(8, reporteMovil.getImporteAbono());
                preparedStatementObj.setDouble(8, 0.00);
                preparedStatementObj.setInt(9, reporteMovil.getClienteId());
                preparedStatementObj.setDouble(10, 1.00);
                preparedStatementObj.setString(11, "N");
                preparedStatementObj.setString(12, "N");
                preparedStatementObj.setString(13, "Abono docto: "+reporteMovil.getSerieDocumento());
                preparedStatementObj.setInt(14, reporteMovil.getCobradorId());
                preparedStatementObj.setString(15, "N");
                preparedStatementObj.setString(16, "N");
                preparedStatementObj.setString(17, "N");
                //preparedStatementObj.setInt(18, condicionPagoId);//CREO DEBE NO IR LO OMITIMOS
                preparedStatementObj.setInt(18, configuracionMobil.getCondicionPagoId());//REFACTOR
                preparedStatementObj.setString(19, "CC");
                preparedStatementObj.setString(20, "P");
                preparedStatementObj.setString(21, "N");
                preparedStatementObj.setString(22, "N");
                preparedStatementObj.setString(23, "N");
                preparedStatementObj.setString(24, "PREIMP");
                preparedStatementObj.setString(25, "N");
                java.util.Date today = new java.util.Date();	
                preparedStatementObj.setTimestamp(26, new java.sql.Timestamp(today.getTime()));
                preparedStatementObj.setString(27, "N");
                preparedStatementObj.setString(28, "N");
                preparedStatementObj.setString(29, "N");
                preparedStatementObj.setInt(30, LUGAR_EXPEDICION_ID);
                preparedStatementObj.setDate(31, utilerias.convertStringToDate(reporteMovil.getFechaAbono()));
                if (configuracionMobil.getMicrosip2020() != 0) 
                    preparedStatementObj.setInt(32, configuracionMobil.getSucursalId());
                
                preparedStatementObj.executeUpdate();                             
                Resources.logger.info("Grabo en la tabla DOCTOS_CC");
                
                
                /**************************************************************/
                preparedStatementObj = conexion.prepareStatement(
                        "INSERT INTO FORMAS_COBRO_DOCTOS " +
                        "(FORMA_COBRO_DOC_ID, NOM_TABLA_DOCTOS, DOCTO_ID, FORMA_COBRO_ID, " +
                        "NUM_CTA_PAGO, CLAVE_SIS_FORMA_COB, REFERENCIA, IMPORTE)" +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
                );
                preparedStatementObj.setInt(1, -1);
                preparedStatementObj.setString(2, "DOCTOS_CC");
                preparedStatementObj.setInt(3, idAutoIncremental);
                //preparedStatementObj.setInt(4, FORMA_COBRO_CC_ID);
                preparedStatementObj.setInt(4, reporteMovil.getFormaCobroCCId());
                preparedStatementObj.setString(5, "");
                preparedStatementObj.setString(6, "CC");
                preparedStatementObj.setString(7, "Abono docto: " + reporteMovil.getSerieDocumento());
                preparedStatementObj.setDouble(8, 0.00);
                preparedStatementObj.executeUpdate();                 
                Resources.logger.info("Grabo en la tabla FORMAS_COBRO_DOCTOS");
                
                /*preparedStatementObj = conexion.prepareStatement(
                        "INSERT INTO FORMAS_COBRO_CLIENTES " +
                        "(FORMA_COBRO_CLI_ID,CLIENTE_ID,CLAVE_SISTEMA,FORMA_COBRO_ID, " +
                        //"NUM_CTA_PAGO, BANCO_ID, ULT_UTILIZADA) " +
                        "ULT_UTILIZADA) " +
                        "VALUES(?, ?, ?, ?, ?)"
                );
                preparedStatementObj.setInt(1, -1);
                preparedStatementObj.setInt(2, reporteMovil.getClienteId());
                preparedStatementObj.setString(3, "CC");
                preparedStatementObj.setInt(4, FORMA_COBRO_CC_ID);
                preparedStatementObj.setString(5, "S");                
                preparedStatementObj.executeUpdate(); 
                Resources.logger.info("Grabo en la tabla FORMAS_COBRO_CLIENTES");*/
                /**************************************************************/
                                
                preparedStatementObj = conexion.prepareStatement(
                        "INSERT INTO IMPORTES_DOCTOS_CC(IMPTE_DOCTO_CC_ID, DOCTO_CC_ID, FECHA, "
                      + "CANCELADO, APLICADO, ESTATUS, TIPO_IMPTE, DOCTO_CC_ACR_ID, IMPORTE, "
                      + "IMPUESTO, IVA_RETENIDO, ISR_RETENIDO, DSCTO_PPAG, PCTJE_COMIS_COB)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                );
                preparedStatementObj.setInt(1, -1);
                preparedStatementObj.setInt(2, idAutoIncremental);
                preparedStatementObj.setDate(3, utilerias.convertStringToDate(reporteMovil.getFechaAbono()));
                preparedStatementObj.setString(4, "N");
                preparedStatementObj.setString(5, "N");
                preparedStatementObj.setString(6, "P");
                preparedStatementObj.setString(7, "R");
                preparedStatementObj.setInt(8, reporteMovil.getDoctoCCId());                
                preparedStatementObj.setDouble(9, reporteMovil.getImporteAbono());// LO REVISARA DANTE
                preparedStatementObj.setDouble(10, 0.00); //LO REVISARA DANTE
                preparedStatementObj.setDouble(11, 0.00);
                preparedStatementObj.setDouble(12, 0.00);
                preparedStatementObj.setDouble(13, 0.00);
                preparedStatementObj.setDouble(14, 0.00);
                preparedStatementObj.executeUpdate();               
                Resources.logger.info("Grabo en la tabla IMPORTES_DOCTOS_CC");
                
                preparedStatementObj = conexion.prepareStatement(
                        "UPDATE DOCTOS_CC SET APLICADO = ? WHERE DOCTO_CC_ID = ?"
                );                
                preparedStatementObj.setString(1, "S");               
                preparedStatementObj.setInt(2, idAutoIncremental);               
                preparedStatementObj.executeUpdate();               
                Resources.logger.info("ACTUALIZO A DOCTOS_CC");
                
                conexion.commit();
            }
            return true;
        }catch(SQLException exception){
            Resources.logger.error("Sucedio una excepcion: " + exception.getMessage());
            
            try {
                System.out.println("Transaction failed.");
                conexion.rollback();
                exception.printStackTrace();
                return false;
            }
            catch (SQLException se) {
                se.printStackTrace();
                return false;
            }            
        }
    }
    
    public String vendedoresCobradores() throws SQLException {        
        try {
            VendedorCobrador vendedoresCobradores = new VendedorCobrador();
            
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT VENDEDOR_ID, NOMBRE FROM VENDEDORES"
            );
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Vendedor> listaVendedores = new ArrayList<Vendedor>();
            while (resultSet.next()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setVendedorId(resultSet.getInt("VENDEDOR_ID"));
                vendedor.setNombre(resultSet.getString("NOMBRE"));
                listaVendedores.add(vendedor);
            }
            
            preparedStatement = conexion.prepareStatement(
                    "SELECT COBRADOR_ID, NOMBRE FROM COBRADORES"
            );
            resultSet = preparedStatement.executeQuery();
            List<Cobrador> listaCobradores = new ArrayList<Cobrador>();
            while (resultSet.next()) {
                Cobrador cobrador = new Cobrador();
                cobrador.setCobradorId(resultSet.getInt("COBRADOR_ID"));
                cobrador.setNombre(resultSet.getString("NOMBRE"));
                listaCobradores.add(cobrador);
            }
            
            vendedoresCobradores.setListaVendedores(listaVendedores);
            vendedoresCobradores.setListaCobradores(listaCobradores);
            return gson.toJson(vendedoresCobradores);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }

    public String promociones() throws SQLException {
        try {
            List<Promocion> listaPromociones = new ArrayList<Promocion>();
                     
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT A.NOMBRE, DPA.DESCUENTO FROM POLITICAS_DSCTOS_PROMOCION PDP " +
                    "INNER JOIN DSCTOS_PROMO_ARTS DPA ON PDP.POLITICA_DSCTO_PROMO_ID = DPA.POLITICA_DSCTO_PROMO_ID " +
                    "INNER JOIN ARTICULOS A ON DPA.ARTICULO_ID=A.ARTICULO_ID " +
                    "WHERE PDP.FECHA_INI_VIGENCIA >= ? AND PDP.FECHA_FIN_VIGENCIA <= ? AND PDP.HABILITADA='S'"
            );
            
            Calendar fechaActual;
            fechaActual = Calendar.getInstance();
            int anio = fechaActual.get(Calendar.YEAR);
            int mes = fechaActual.get(Calendar.MONTH) + 1;

            Calendar calendar = new GregorianCalendar(anio, mes, 0);
            int diasDelMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            String fechaInicioMes = mes + "/" + "01" + "/" + anio;                                   
            String fechaFinMes = mes + "/" + diasDelMes + "/" + anio;                   
            
            Resources.logger.info("FECHA INICIO:"+fechaInicioMes);
            Resources.logger.info("FECHA FIN:"+fechaFinMes);
                    
            preparedStatement.setDate(1, convierteStringAFecha(fechaInicioMes));
            preparedStatement.setDate(2, convierteStringAFecha(fechaFinMes));
            ResultSet resultSet = preparedStatement.executeQuery();

            String nombre = "";
            while (resultSet.next()) {
                Promocion promocion = new Promocion();
                nombre = escapeCharacters(resultSet.getString("NOMBRE"));                                                    
                promocion.setNombre(nombre);                              
                promocion.setDescuento(resultSet.getDouble("DESCUENTO"));
                listaPromociones.add(promocion);
            }
            
            return gson.toJson(listaPromociones);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            Resources.logger.error("error:"+exception.getMessage());
            return null;
        }
    }
    
    public java.sql.Date convierteStringAFecha (String fechaString){        
        try {
            java.util.Date dateUtil;
            java.sql.Date dateSQL;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            dateUtil = simpleDateFormat.parse(fechaString);
            dateSQL = new java.sql.Date(dateUtil.getTime());    
            return dateSQL;
        } catch (ParseException ex) {
            Logger.getLogger(Utileria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String articulosAlmacenes(String articulo) throws SQLException {
        ArrayList<ArticuloAlmacen> listaArticulosAlmacenes = new ArrayList<ArticuloAlmacen>();
        try {
            System.out.println("A HUEVO");
            String[] arrayBusqueda = articulo.split(" ");
            String busqueda = "";
            int longitudCadena = arrayBusqueda.length;

            if (longitudCadena > 1) {
                for (int i = 0; i < arrayBusqueda.length; i++) {
                    if (!("".equals(arrayBusqueda[i]))) {
                        if (i == longitudCadena - 1) {
                            busqueda = busqueda + arrayBusqueda[i];
                        } else {
                            busqueda = busqueda + arrayBusqueda[i] + "%";
                        }
                    }
                }
                articulo = busqueda;
            }
            
            //HashMap<String, Integer> hashMapArticulosComprometidos = articulosComprometidos();            
            

            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT ARTICULO_ID, NOMBRE_ARTICULO, CODIGO_ARTICULO, CLAVE_ARTICULO," +
                    " ALMACEN_ID, NOMBRE_ALMACEN, EXISTENCIA FROM ARTICULOS_ALMACEN WHERE ALMACEN_ID IN (19,438052) AND " +
                    " (UPPER(NOMBRE_ARTICULO) LIKE '" + articulo.toUpperCase() + "%' )" +                 
                    " OR UPPER(CODIGO_ARTICULO) LIKE '" + articulo.toUpperCase() + "%'" +
                    " OR UPPER(CLAVE_ARTICULO) LIKE '" + articulo.toUpperCase() + "%'");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ArticuloAlmacen articuloAlmacen = new ArticuloAlmacen();
                articuloAlmacen.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                articuloAlmacen.setNombreArticulo(resultSet.getString("CODIGO_ARTICULO") + 
                        "   " + 
                        resultSet.getString("CLAVE_ARTICULO") + 
                        "   " +
                        resultSet.getString("NOMBRE_ARTICULO"));
                articuloAlmacen.setCodigo(resultSet.getString("CODIGO_ARTICULO"));
                articuloAlmacen.setClave(resultSet.getString("CLAVE_ARTICULO"));
                articuloAlmacen.setAlmacenId(resultSet.getInt("ALMACEN_ID"));
                /*
                Double existenciaReal = 
                        hashMapArticulosComprometidos.containsKey(resultQuery.getString("NUMART")) ? 
                        (resultQuery.getDouble("EXUALM") - hashMapArticulosComprometidos.get(resultQuery.getString("NUMART"))) 
                        : 
                        resultQuery.getDouble("EXUALM");*/
                articuloAlmacen.setNombreAlmacen(resultSet.getString("NOMBRE_ALMACEN"));                
                articuloAlmacen.setExistencia(resultSet.getDouble("EXISTENCIA") <= 0 ? 0 : resultSet.getDouble("EXISTENCIA"));
                listaArticulosAlmacenes.add(articuloAlmacen);
            }
            
            return gson.toJson(listaArticulosAlmacenes);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public String getArticuloPrecio(String articulo) {
        ArrayList<Articulo> listaArticuloPrecios = new ArrayList<Articulo>();
        try {
            String[] arrayBusqueda = articulo.split(" ");
            String busqueda = "";
            int longitudCadena = arrayBusqueda.length;

            if (longitudCadena > 1) {
                for (int i = 0; i < arrayBusqueda.length; i++) {
                    if (!("".equals(arrayBusqueda[i]))) {
                        if (i == longitudCadena - 1) {
                            busqueda = busqueda + arrayBusqueda[i];
                        } else {
                            busqueda = busqueda + arrayBusqueda[i] + "%";
                        }
                    }
                }
                articulo = busqueda;
            }


            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT ARTICULO_ID, NOMBRE_ARTICULO, PRECIO_NETO, CODIGO_ARTICULO, CLAVE_ARTICULO FROM ARTICULOS_MOBIL(?, ?) " +
                    " WHERE (UPPER(NOMBRE_ARTICULO) LIKE '" + articulo.toUpperCase() + "%' )" +                 
                    " OR UPPER(CODIGO_ARTICULO) LIKE '" + articulo.toUpperCase() + "%'" +
                    " OR UPPER(CLAVE_ARTICULO) LIKE '" + articulo.toUpperCase() + "%'");
            preparedStatement.setDate(1, fechaInicioFin.getFechaInicio());
            preparedStatement.setDate(2, fechaInicioFin.getFechaFin());            
            ResultSet resultSet = preparedStatement.executeQuery();
            String nombreArticulo = "";
            while (resultSet.next()) {
                Articulo articuloObject = new Articulo();
                articuloObject.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                nombreArticulo = resultSet.getString("NOMBRE_ARTICULO");
                nombreArticulo = nombreArticulo.replace("'", "\"");                
                articuloObject.setNombreArticulo(nombreArticulo);
                articuloObject.setPrecioNeto(resultSet.getDouble("PRECIO_NETO"));                
                String codigoArticulo = resultSet.getString("CODIGO_ARTICULO");
                codigoArticulo = codigoArticulo.replace("'", "\""); 
                codigoArticulo = escapeCharacters(codigoArticulo);                    
                articuloObject.setCodigoArticulo(codigoArticulo);
                String claveArticulo = resultSet.getString("CLAVE_ARTICULO");
                claveArticulo = claveArticulo.replace("'", "\""); 
                claveArticulo = escapeCharacters(claveArticulo);                                    
                articuloObject.setClaveArticulo(claveArticulo);
                
                /*if (hashMapArticulosVolumen.containsKey(resultSet.getInt("ARTICULO_ID"))) {                   
                    articuloObject.setPoliticaXVolumen(Boolean.TRUE);
                    articuloObject.setListaArticuloVolumen(hashMapArticulosVolumen.get(resultSet.getInt("ARTICULO_ID")));                    
                }
                else {
                    articuloObject.setPoliticaXVolumen(Boolean.FALSE);
                    articuloObject.setListaArticuloVolumen(null);
                }
                */
                listaArticuloPrecios.add(articuloObject);
            }
            return gson.toJson(listaArticuloPrecios);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public String almacenes() throws SQLException {
        try {
            List<Almacen> listaAlmacenes = new ArrayList<Almacen>();
                     
            PreparedStatement preparedStatement = conexion.prepareStatement(                    
                    "SELECT A.ALMACEN_ID, A.NOMBRE FROM ALMACENES A " +
                    "INNER JOIN CONFIGURACION_ALMACENES CA ON A.ALMACEN_ID =CA.ALMACEN_ID " +
                    "ORDER BY CA.ES_DEFAULT DESC"
            );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Almacen Almacen = new Almacen();
                Almacen.setAlmacenId(resultSet.getInt("ALMACEN_ID"));
                Almacen.setNombre(resultSet.getString("NOMBRE"));
                listaAlmacenes.add(Almacen);
            }
            
            return gson.toJson(listaAlmacenes);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    public String almacenesConfiguracion() throws SQLException {
        try {
            List<Almacen> listaAlmacenes = new ArrayList<Almacen>();
                     
            PreparedStatement preparedStatement = conexion.prepareStatement(                    
                    "SELECT ALMACEN_ID, NOMBRE FROM ALMACENES"
            );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Almacen Almacen = new Almacen();
                Almacen.setAlmacenId(resultSet.getInt("ALMACEN_ID"));
                Almacen.setNombre(resultSet.getString("NOMBRE"));
                listaAlmacenes.add(Almacen);
            }
            
            return gson.toJson(listaAlmacenes);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    public HashMap<Integer, List<ArticuloVolumen>> articulosXVolumen() throws SQLException{
        try {                    
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT DVA.ARTICULO_ID, DVA.UNIDADES, DVA.DESCUENTO FROM DSCTOS_VOL_ARTS DVA " +
                    "INNER JOIN POLITICAS_DSCTOS_VOLUMEN PDV ON PDV.POLITICA_DSCTO_VOLUMEN_ID=DVA.POLITICA_DSCTO_VOLUMEN_ID " +
                    "INNER JOIN DSCTOS_VOL_GLOBALES DVG ON DVG.POLITICA_DSCTO_VOLUMEN_ID=PDV.POLITICA_DSCTO_VOLUMEN_ID " +
                    "WHERE PDV.HABILITADA='S' AND PDV.FECHA_INI_VIGENCIA>='06/01/2019' AND PDV.FECHA_FIN_VIGENCIA<='06/30/2019'"
                    //"WHERE PDV.HABILITADA='S' AND PDV.FECHA_INI_VIGENCIA>=? AND PDV.FECHA_FIN_VIGENCIA<=?"
            );
            //preparedStatement.setDate(1, fechaInicioFin.getFechaInicio());
            //preparedStatement.setDate(2, fechaInicioFin.getFechaFin());
            ResultSet resultSet = preparedStatement.executeQuery();
            
            HashMap<Integer, List<ArticuloVolumen>> hashMapArticulosVolumen = new HashMap<Integer, List<ArticuloVolumen>>();
            while (resultSet.next()) {
                ArticuloVolumen articuloVolumen = new ArticuloVolumen();
                articuloVolumen.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                articuloVolumen.setUnidades(resultSet.getDouble("UNIDADES"));
                articuloVolumen.setPorcentajeDescuento(resultSet.getDouble("DESCUENTO"));               
                if (hashMapArticulosVolumen.containsKey(resultSet.getInt("ARTICULO_ID"))){//CONTIENE YA EL UUID                   
                    List<ArticuloVolumen> listaArticulosVolumenTmp = hashMapArticulosVolumen.get(resultSet.getInt("ARTICULO_ID"));                    
                    listaArticulosVolumenTmp.add(articuloVolumen);
                    
                    hashMapArticulosVolumen.put(resultSet.getInt("ARTICULO_ID"), listaArticulosVolumenTmp);                    
                }else {                   
                    List<ArticuloVolumen> listaArticulosVolumenTmp = new ArrayList<ArticuloVolumen>();
                    listaArticulosVolumenTmp.add(articuloVolumen);
                    hashMapArticulosVolumen.put(resultSet.getInt("ARTICULO_ID"), listaArticulosVolumenTmp);                    
                }              
            }            
            return hashMapArticulosVolumen;
            /*List<ArticuloVolumen> listaFiltro = listaArticulosVolumen.stream()
                    .filter(p -> p.getArticuloId() == 58024)
                    .collect(Collectors.toList());*/
            //System.err.println(gson.toJson(listaFiltro));
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());           
            return null;
        } 
    }
    
    public String articulos() throws SQLException {
        HashMap<Integer, List<ArticuloVolumen>> hashMapArticulosVolumen = articulosXVolumen();        
        try {
            List<Articulo> listaArticulos = new ArrayList<Articulo>();
            
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT ARTICULO_ID, NOMBRE_ARTICULO, FACTOR_VENTA, UNIDAD_VENTA, PORCENTAJE_IMPUESTO," +
                    "PRECIO, DESCUENTO_PROMOCION, PRECIO_NETO, CODIGO_ARTICULO, CLAVE_ARTICULO, UNIDAD_MINIMA_VENTA, PIEZAS_X_INNER," +
                    "PIEZAS_X_MASTER, CODIGO_BARRAS, CODIGO_BARRAS_INNER, CODIGO_BARRAS_MASTER " +
                    "FROM ARTICULOS_MOBIL(?, ?)"
            );
            
            Calendar fechaActual;
            fechaActual = Calendar.getInstance();
            int anio = fechaActual.get(Calendar.YEAR);
            int mes = fechaActual.get(Calendar.MONTH) + 1;

            Calendar calendar = new GregorianCalendar(anio, mes, 0);
            int diasDelMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            String fechaInicioMes = mes + "/" + "01" + "/" + anio;                                   
            String fechaFinMes = mes + "/" + diasDelMes + "/" + anio;                   
            
            Resources.logger.info("FECHA INICIO:"+fechaInicioMes);
            Resources.logger.info("FECHA FIN:"+fechaFinMes);
                    
            preparedStatement.setDate(1, convierteStringAFecha(fechaInicioMes));
            preparedStatement.setDate(2, convierteStringAFecha(fechaFinMes));
            ResultSet resultSet = preparedStatement.executeQuery();

            String nombreArticulo;
            while (resultSet.next()) {
                Articulo articulo = new Articulo();
                articulo.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                nombreArticulo = resultSet.getString("NOMBRE_ARTICULO");
                //nombreArticulo = nombreArticulo.replace("'", "\"");                
                nombreArticulo = escapeCharacters(nombreArticulo.trim());                    
                articulo.setNombreArticulo(nombreArticulo);
                articulo.setFactorVenta(resultSet.getDouble("FACTOR_VENTA"));
                articulo.setUnidadVenta(resultSet.getString("UNIDAD_VENTA") == null ? "NA" : resultSet.getString("UNIDAD_VENTA"));
                //articulo.setPorcentajeImpuesto(resultSet.getDouble("PORCENTAJE_IMPUESTO"));
                articulo.setPrecio(resultSet.getDouble("PRECIO"));
                articulo.setPorcentajePromocion(resultSet.getDouble("DESCUENTO_PROMOCION"));
                articulo.setPrecioNeto(resultSet.getDouble("PRECIO_NETO"));                
                String codigoArticulo = resultSet.getString("CODIGO_ARTICULO");
                codigoArticulo = codigoArticulo.replace("'", "\""); 
                codigoArticulo = escapeCharacters(codigoArticulo.trim());                    
                articulo.setCodigoArticulo(codigoArticulo);
                String claveArticulo = resultSet.getString("CLAVE_ARTICULO");
                claveArticulo = claveArticulo.replace("'", "\""); 
                claveArticulo = escapeCharacters(claveArticulo.trim());                                    
                articulo.setClaveArticulo(claveArticulo);
                articulo.setUnidadMinimaVenta(resultSet.getDouble("UNIDAD_MINIMA_VENTA"));
                articulo.setPiezasXInner(resultSet.getDouble("PIEZAS_X_INNER"));
                articulo.setPiezasXMaster(resultSet.getDouble("PIEZAS_X_MASTER"));
                articulo.setCodigoBarras(resultSet.getString("CODIGO_BARRAS").trim());                
                articulo.setCodigoBarrasInner(resultSet.getString("CODIGO_BARRAS_INNER").trim());
                articulo.setCodigoBarrasMaster(resultSet.getString("CODIGO_BARRAS_MASTER").trim());
                if (hashMapArticulosVolumen.containsKey(resultSet.getInt("ARTICULO_ID"))) {                   
                    articulo.setPoliticaXVolumen(Boolean.TRUE);
                    articulo.setListaArticuloVolumen(hashMapArticulosVolumen.get(resultSet.getInt("ARTICULO_ID")));                    
                }
                else {
                    articulo.setPoliticaXVolumen(Boolean.FALSE);
                    articulo.setListaArticuloVolumen(null);
                }
                listaArticulos.add(articulo);
            }
            System.out.println(gson.toJson(listaArticulos));
            return gson.toJson(listaArticulos);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    private String escapeCharacters(String cadena){
        //handling xml special character & in Java String
        //String xmlWithSpecial = "BROCA ROUTER CORTE EN \"V\" 90ø DE 1/2\""; //xml String with & as special characters
        //String xmlWithSpecial = "xxxxxxxxxx C/MOL 1«\" C/BAL"; //xml String with & as special characters
        String xmlWithSpecial = cadena;
        System.out.println("Original unescaped XML String: " + xmlWithSpecial);
        String scape = StringEscapeUtils.escapeXml(StringEscapeUtils.escapeJava(xmlWithSpecial));
        System.out.println("Escaped String in Java: "+ scape);
        
        /*String unescape = StringEscapeUtils.unescapeXml(StringEscapeUtils.unescapeJava(scape));
        System.out.println("Unescaped String in Java: "+ unescape);*/
        return scape;
    }
    
    public String bancos() {

        Resources.logger.info("==================================================");
        Resources.logger.info("LLamando a Bancos");
            
        
            
        String bancos = "[\n" +
"        {\"claveBanco\": \"002\",\"descripcion\": \"BANAMEX\",\"nombreORazonSocial\": \"Banco Nacional de México, S.A., Institución de Banca Múltiple, Grupo Financiero Banamex\"},\n" +
"        {\"claveBanco\": \"006\",\"descripcion\": \"BANCOMEXT\",\"nombreORazonSocial\": \"Banco Nacional de Comercio Exterior, Sociedad Nacional de Crédito, Institución de Banca de Desarrollo\"},\n" +
"        {\"claveBanco\": \"009\",\"descripcion\": \"BANOBRAS\",\"nombreORazonSocial\": \"Banco Nacional de Obras y Servicios Públicos, Sociedad Nacional de Crédito, Institución de Banca de Desarrollo\"},\n" +
"        {\"claveBanco\": \"012\",\"descripcion\": \"BBVA BANCOMER\",\"nombreORazonSocial\": \"BBVA Bancomer, S.A., Institución de Banca Múltiple, Grupo Financiero BBVA Bancomer\"},\n" +
"        {\"claveBanco\": \"014\",\"descripcion\": \"SANTANDER\",\"nombreORazonSocial\": \"Banco Santander (México), S.A., Institución de Banca Múltiple, Grupo Financiero Santander\"},\n" +
"        {\"claveBanco\": \"019\",\"descripcion\": \"BANJERCITO\",\"nombreORazonSocial\": \"Banco Nacional del Ejército, Fuerza Aérea y Armada, Sociedad Nacional de Crédito, Institución de Banca de Desarrollo\"},\n" +
"        {\"claveBanco\": \"021\",\"descripcion\": \"HSBC\",\"nombreORazonSocial\": \"HSBC México, S.A., institución De Banca Múltiple, Grupo Financiero HSBC\"},\n" +
"        {\"claveBanco\": \"030\",\"descripcion\": \"BAJIO\",\"nombreORazonSocial\": \"Banco del Bajío, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"032\",\"descripcion\": \"IXE\",\"nombreORazonSocial\": \"IXE Banco, S.A., Institución de Banca Múltiple, IXE Grupo Financiero\"},\n" +
"        {\"claveBanco\": \"036\",\"descripcion\": \"INBURSA\",\"nombreORazonSocial\": \"Banco Inbursa, S.A., Institución de Banca Múltiple, Grupo Financiero Inbursa\"},\n" +
"        {\"claveBanco\": \"037\",\"descripcion\": \"INTERACCIONES\",\"nombreORazonSocial\": \"Banco Interacciones, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"042\",\"descripcion\": \"MIFEL\",\"nombreORazonSocial\": \"Banca Mifel, S.A., Institución de Banca Múltiple, Grupo Financiero Mifel\"},\n" +
"        {\"claveBanco\": \"044\",\"descripcion\": \"SCOTIABANK\",\"nombreORazonSocial\": \"Scotiabank Inverlat, S.A.\"},\n" +
"        {\"claveBanco\": \"058\",\"descripcion\": \"BANREGIO\",\"nombreORazonSocial\": \"Banco Regional de Monterrey, S.A., Institución de Banca Múltiple, Banregio Grupo Financiero\"},\n" +
"        {\"claveBanco\": \"059\",\"descripcion\": \"INVEX\",\"nombreORazonSocial\": \"Banco Invex, S.A., Institución de Banca Múltiple, Invex Grupo Financiero\"},\n" +
"        {\"claveBanco\": \"060\",\"descripcion\": \"BANSI\",\"nombreORazonSocial\": \"Bansi, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"062\",\"descripcion\": \"AFIRME\",\"nombreORazonSocial\": \"Banca Afirme, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"072\",\"descripcion\": \"BANORTE/IXE\",\"nombreORazonSocial\": \"Banco Mercantil del Norte, S.A., Institución de Banca Múltiple, Grupo Financiero Banorte\"},\n" +
"        {\"claveBanco\": \"102\",\"descripcion\": \"THE ROYAL BANK\",\"nombreORazonSocial\": \"The Royal Bank of Scotland México, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"103\",\"descripcion\": \"AMERICAN EXPRESS\",\"nombreORazonSocial\": \"American Express Bank (México), S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"106\",\"descripcion\": \"BAMSA\",\"nombreORazonSocial\": \"Bank of America México, S.A., Institución de Banca Múltiple, Grupo Financiero Bank of America\"},\n" +
"        {\"claveBanco\": \"108\",\"descripcion\": \"TOKYO\",\"nombreORazonSocial\": \"Bank of Tokyo-Mitsubishi UFJ (México), S.A.\"},\n" +
"        {\"claveBanco\": \"110\",\"descripcion\": \"JP MORGAN\",\"nombreORazonSocial\": \"Banco J.P. Morgan, S.A., Institución de Banca Múltiple, J.P. Morgan Grupo Financiero\"},\n" +
"        {\"claveBanco\": \"112\",\"descripcion\": \"BMONEX\",\"nombreORazonSocial\": \"Banco Monex, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"113\",\"descripcion\": \"VE POR MAS\",\"nombreORazonSocial\": \"Banco Ve Por Mas, S.A. Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"116\",\"descripcion\": \"ING\",\"nombreORazonSocial\": \"ING Bank (México), S.A., Institución de Banca Múltiple, ING Grupo Financiero\"},\n" +
"        {\"claveBanco\": \"124\",\"descripcion\": \"DEUTSCHE\",\"nombreORazonSocial\": \"Deutsche Bank México, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"126\",\"descripcion\": \"CREDIT SUISSE\",\"nombreORazonSocial\": \"Banco Credit Suisse (México), S.A. Institución de Banca Múltiple, Grupo Financiero Credit Suisse (México)\"},\n" +
"        {\"claveBanco\": \"127\",\"descripcion\": \"AZTECA\",\"nombreORazonSocial\": \"Banco Azteca, S.A. Institución de Banca Múltiple.\"},\n" +
"        {\"claveBanco\": \"128\",\"descripcion\": \"AUTOFIN\",\"nombreORazonSocial\": \"Banco Autofin México, S.A. Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"129\",\"descripcion\": \"BARCLAYS\",\"nombreORazonSocial\": \"Barclays Bank México, S.A., Institución de Banca Múltiple, Grupo Financiero Barclays México\"},\n" +
"        {\"claveBanco\": \"130\",\"descripcion\": \"COMPARTAMOS\",\"nombreORazonSocial\": \"Banco Compartamos, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"131\",\"descripcion\": \"BANCO FAMSA\",\"nombreORazonSocial\": \"Banco Ahorro Famsa, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"132\",\"descripcion\": \"BMULTIVA\",\"nombreORazonSocial\": \"Banco Multiva, S.A., Institución de Banca Múltiple, Multivalores Grupo Financiero\"},\n" +
"        {\"claveBanco\": \"133\",\"descripcion\": \"ACTINVER\",\"nombreORazonSocial\": \"Banco Actinver, S.A. Institución de Banca Múltiple, Grupo Financiero Actinver\"},\n" +
"        {\"claveBanco\": \"134\",\"descripcion\": \"WAL-MART\",\"nombreORazonSocial\": \"Banco Wal-Mart de México Adelante, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"135\",\"descripcion\": \"NAFIN\",\"nombreORazonSocial\": \"Nacional Financiera, Sociedad Nacional de Crédito, Institución de Banca de Desarrollo\"},\n" +
"        {\"claveBanco\": \"136\",\"descripcion\": \"INTERCAM BANCO\",\"nombreORazonSocial\": \"Intercam Banco, S.A., Institución de Banca Múltiple, Intercam Grupo Financiero\"},\n" +
"        {\"claveBanco\": \"137\",\"descripcion\": \"BANCOPPEL\",\"nombreORazonSocial\": \"BanCoppel, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"138\",\"descripcion\": \"ABC CAPITAL\",\"nombreORazonSocial\": \"ABC Capital, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"139\",\"descripcion\": \"UBS BANK\",\"nombreORazonSocial\": \"UBS Bank México, S.A., Institución de Banca Múltiple, UBS Grupo Financiero\"},\n" +
"        {\"claveBanco\": \"140\",\"descripcion\": \"CONSUBANCO\",\"nombreORazonSocial\": \"Consubanco, S.A. Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"141\",\"descripcion\": \"VOLKSWAGEN\",\"nombreORazonSocial\": \"Volkswagen Bank, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"143\",\"descripcion\": \"CIBANCO\",\"nombreORazonSocial\": \"CIBanco, S.A.\"},\n" +
"        {\"claveBanco\": \"145\",\"descripcion\": \"BBASE\",\"nombreORazonSocial\": \"Banco Base, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"147\",\"descripcion\": \"BANKAOOL\",\"nombreORazonSocial\": \"Bankaool, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"148\",\"descripcion\": \"PAGATODO\",\"nombreORazonSocial\": \"Banco PagaTodo, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"149\",\"descripcion\": \"FORJADORES\",\"nombreORazonSocial\": \"Banco Forjadores, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"150\",\"descripcion\": \"INMOBILIARIO\",\"nombreORazonSocial\": \"Banco Inmobiliario Mexicano, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"151\",\"descripcion\": \"DONDÉ\",\"nombreORazonSocial\": \"Fundación Dondé Banco, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"152\",\"descripcion\": \"BANCREA\",\"nombreORazonSocial\": \"Banco Bancrea, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"153\",\"descripcion\": \"PROGRESO\",\"nombreORazonSocial\": \"Banco Progreso Chihuahua, S.A.\"},\n" +
"        {\"claveBanco\": \"154\",\"descripcion\": \"BANCO FINTERRA\",\"nombreORazonSocial\": \"Banco Finterra, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"155\",\"descripcion\": \"ICBC\",\"nombreORazonSocial\": \"Industrial and Commercial Bank of China México, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"156\",\"descripcion\": \"SABADELL\",\"nombreORazonSocial\": \"Banco Sabadell, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"157\",\"descripcion\": \"SHINHAN\",\"nombreORazonSocial\": \"Banco Shinhan de México, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"158\",\"descripcion\": \"MIZUHO BANK\",\"nombreORazonSocial\": \"Mizuho Bank México, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"159\",\"descripcion\": \"BANK OF CHINA\",\"nombreORazonSocial\": \"Bank of China México, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"160\",\"descripcion\": \"BANCO S3\",\"nombreORazonSocial\": \"Banco S3 México, S.A., Institución de Banca Múltiple\"},\n" +
"        {\"claveBanco\": \"166\",\"descripcion\": \"BANSEFI\",\"nombreORazonSocial\": \"Banco del Ahorro Nacional y Servicios Financieros, Sociedad Nacional de Crédito, Institución de Banca de Desarrollo\"},\n" +
"        {\"claveBanco\": \"168\",\"descripcion\": \"HIPOTECARIA FEDERAL\",\"nombreORazonSocial\": \"Sociedad Hipotecaria Federal, Sociedad Nacional de Crédito, Institución de Banca de Desarrollo\"},\n" +
"        {\"claveBanco\": \"600\",\"descripcion\": \"MONEXCB\",\"nombreORazonSocial\": \"Monex Casa de Bolsa, S.A. de C.V. Monex Grupo Financiero\"},\n" +
"        {\"claveBanco\": \"601\",\"descripcion\": \"GBM\",\"nombreORazonSocial\": \"GBM Grupo Bursátil Mexicano, S.A. de C.V. Casa de Bolsa\"},\n" +
"        {\"claveBanco\": \"602\",\"descripcion\": \"MASARI\",\"nombreORazonSocial\": \"Masari Casa de Bolsa, S.A.\"},\n" +
"        {\"claveBanco\": \"605\",\"descripcion\": \"VALUE\",\"nombreORazonSocial\": \"Value, S.A. de C.V. Casa de Bolsa\"},\n" +
"        {\"claveBanco\": \"606\",\"descripcion\": \"ESTRUCTURADORES\",\"nombreORazonSocial\": \"Estructuradores del Mercado de Valores Casa de Bolsa, S.A. de C.V.\"},\n" +
"        {\"claveBanco\": \"607\",\"descripcion\": \"TIBER\",\"nombreORazonSocial\": \"Casa de Cambio Tiber, S.A. de C.V.\"},\n" +
"        {\"claveBanco\": \"608\",\"descripcion\": \"VECTOR\",\"nombreORazonSocial\": \"Vector Casa de Bolsa, S.A. de C.V.\"},\n" +
"        {\"claveBanco\": \"610\",\"descripcion\": \"B&B\",\"nombreORazonSocial\": \"B y B, Casa de Cambio, S.A. de C.V.\"},\n" +
"        {\"claveBanco\": \"614\",\"descripcion\": \"ACCIVAL\",\"nombreORazonSocial\": \"Acciones y Valores Banamex, S.A. de C.V., Casa de Bolsa\"},\n" +
"        {\"claveBanco\": \"615\",\"descripcion\": \"MERRILL LYNCH\",\"nombreORazonSocial\": \"Merrill Lynch México, S.A. de C.V. Casa de Bolsa\"},\n" +
"        {\"claveBanco\": \"616\",\"descripcion\": \"FINAMEX\",\"nombreORazonSocial\": \"Casa de Bolsa Finamex, S.A. de C.V.\"},\n" +
"        {\"claveBanco\": \"617\",\"descripcion\": \"VALMEX\",\"nombreORazonSocial\": \"Valores Mexicanos Casa de Bolsa, S.A. de C.V.\"},\n" +
"        {\"claveBanco\": \"618\",\"descripcion\": \"UNICA\",\"nombreORazonSocial\": \"Unica Casa de Cambio, S.A. de C.V.\"},\n" +
"        {\"claveBanco\": \"619\",\"descripcion\": \"MAPFRE\",\"nombreORazonSocial\": \"MAPFRE Tepeyac, S.A.\"},\n" +
"        {\"claveBanco\": \"620\",\"descripcion\": \"PROFUTURO\",\"nombreORazonSocial\": \"Profuturo G.N.P., S.A. de C.V., Afore\"},\n" +
"        {\"claveBanco\": \"621\",\"descripcion\": \"CB ACTINVER\",\"nombreORazonSocial\": \"Actinver Casa de Bolsa, S.A. de C.V.\"},\n" +
"        {\"claveBanco\": \"622\",\"descripcion\": \"OACTIN\",\"nombreORazonSocial\": \"OPERADORA ACTINVER, S.A. DE C.V.\"},\n" +
"        {\"claveBanco\": \"623\",\"descripcion\": \"SKANDIA\",\"nombreORazonSocial\": \"Skandia Vida, S.A. de C.V.\"},\n" +
"        {\"claveBanco\": \"626\",\"descripcion\": \"CBDEUTSCHE\",\"nombreORazonSocial\": \"Deutsche Securities, S.A. de C.V. CASA DE BOLSA\"},\n" +
"        {\"claveBanco\": \"627\",\"descripcion\": \"ZURICH\",\"nombreORazonSocial\": \"Zurich Compañía de Seguros, S.A.\"},\n" +
"        {\"claveBanco\": \"628\",\"descripcion\": \"ZURICHVI\",\"nombreORazonSocial\": \"Zurich Vida, Compañía de Seguros, S.A.\"},\n" +
"        {\"claveBanco\": \"629\",\"descripcion\": \"SU CASITA\",\"nombreORazonSocial\": \"Hipotecaria Su Casita, S.A. de C.V. SOFOM ENR\"},\n" +
"        {\"claveBanco\": \"630\",\"descripcion\": \"CB INTERCAM\",\"nombreORazonSocial\": \"Intercam Casa de Bolsa, S.A. de C.V.\"},\n" +
"        {\"claveBanco\": \"631\",\"descripcion\": \"CI BOLSA\",\"nombreORazonSocial\": \"CI Casa de Bolsa, S.A. de C.V.\"},\n" +
"        {\"claveBanco\": \"632\",\"descripcion\": \"BULLTICK CB\",\"nombreORazonSocial\": \"Bulltick Casa de Bolsa, S.A., de C.V.\"},\n" +
"        {\"claveBanco\": \"633\",\"descripcion\": \"STERLING\",\"nombreORazonSocial\": \"Sterling Casa de Cambio, S.A. de C.V.\"},\n" +
"        {\"claveBanco\": \"634\",\"descripcion\": \"FINCOMUN\",\"nombreORazonSocial\": \"Fincomún, Servicios Financieros Comunitarios, S.A. de C.V.\"},\n" +
"        {\"claveBanco\": \"636\",\"descripcion\": \"HDI SEGUROS\",\"nombreORazonSocial\": \"HDI Seguros, S.A. de C.V.\"},\n" +
"        {\"claveBanco\": \"637\",\"descripcion\": \"ORDER\",\"nombreORazonSocial\": \"Order Express Casa de Cambio, S.A. de C.V\"},\n" +
"        {\"claveBanco\": \"638\",\"descripcion\": \"AKALA\",\"nombreORazonSocial\": \"Akala, S.A. de C.V., Sociedad Financiera Popular\"},\n" +
"        {\"claveBanco\": \"640\",\"descripcion\": \"CB JPMORGAN\",\"nombreORazonSocial\": \"J.P. Morgan Casa de Bolsa, S.A. de C.V. J.P. Morgan Grupo Financiero\"},\n" +
"        {\"claveBanco\": \"642\",\"descripcion\": \"REFORMA\",\"nombreORazonSocial\": \"Operadora de Recursos Reforma, S.A. de C.V., S.F.P.\"},\n" +
"        {\"claveBanco\": \"646\",\"descripcion\": \"STP\",\"nombreORazonSocial\": \"Sistema de Transferencias y Pagos STP, S.A. de C.V.SOFOM ENR\"},\n" +
"        {\"claveBanco\": \"647\",\"descripcion\": \"TELECOMM\",\"nombreORazonSocial\": \"Telecomunicaciones de México\"},\n" +
"        {\"claveBanco\": \"648\",\"descripcion\": \"EVERCORE\",\"nombreORazonSocial\": \"Evercore Casa de Bolsa, S.A. de C.V.\"},\n" +
"        {\"claveBanco\": \"649\",\"descripcion\": \"SKANDIA\",\"nombreORazonSocial\": \"Skandia Operadora de Fondos, S.A. de C.V.\"},\n" +
"        {\"claveBanco\": \"651\",\"descripcion\": \"SEGMTY\",\"nombreORazonSocial\": \"Seguros Monterrey New York Life, S.A de C.V\"},\n" +
"        {\"claveBanco\": \"652\",\"descripcion\": \"ASEA\",\"nombreORazonSocial\": \"Solución Asea, S.A. de C.V., Sociedad Financiera Popular\"},\n" +
"        {\"claveBanco\": \"653\",\"descripcion\": \"KUSPIT\",\"nombreORazonSocial\": \"Kuspit Casa de Bolsa, S.A. de C.V.\"},\n" +
"        {\"claveBanco\": \"655\",\"descripcion\": \"SOFIEXPRESS\",\"nombreORazonSocial\": \"J.P. SOFIEXPRESS, S.A. de C.V., S.F.P.\"},\n" +
"        {\"claveBanco\": \"656\",\"descripcion\": \"UNAGRA\",\"nombreORazonSocial\": \"UNAGRA, S.A. de C.V., S.F.P.\"},\n" +
"        {\"claveBanco\": \"659\",\"descripcion\": \"OPCIONES EMPRESARIALES DEL NOROESTE\",\"nombreORazonSocial\": \"OPCIONES EMPRESARIALES DEL NORESTE, S.A. DE C.V., S.F.P.\"},\n" +
"        {\"claveBanco\": \"670\",\"descripcion\": \"LIBERTAD\",\"nombreORazonSocial\": \"Libertad Servicios Financieros, S.A. De C.V.\"},\n" +
"        {\"claveBanco\": \"901\",\"descripcion\": \"CLS\",\"nombreORazonSocial\": \"Cls Bank International\"},\n" +
"        {\"claveBanco\": \"902\",\"descripcion\": \"INDEVAL\",\"nombreORazonSocial\": \"SD. Indeval, S.A. de C.V.\"}\n" +
"    ]";
        
        List<Banco> listaBancos = gson.fromJson(bancos, new TypeToken<List<Banco>>() {}.getType());
        return gson.toJson(listaBancos);
    }
    
    public String metodosDePago() throws SQLException {
        try {
            List<MetodoPago> listaMetodoPago = new ArrayList<MetodoPago>();
                     
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT FORMA_COBRO_CC_ID, CLAVE_FISCAL, NOMBRE FROM FORMAS_COBRO_CC"
            );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MetodoPago metodoPago = new MetodoPago();
                metodoPago.setFormaCobroCCId(resultSet.getInt("FORMA_COBRO_CC_ID"));
                metodoPago.setClave(resultSet.getString("CLAVE_FISCAL"));
                metodoPago.setConcepto(resultSet.getString("NOMBRE"));
                listaMetodoPago.add(metodoPago);
            }
            
            return gson.toJson(listaMetodoPago);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    public String clientes(int vendedorId) throws SQLException {
        try {
            List<Cliente> listaCliente = new ArrayList<Cliente>();
                
            ConfiguracionMobil configuracionMobil = configuracionMicrosip();
            PreparedStatement preparedStatement = null;
            preparedStatement = conexion.prepareStatement(
                    "SELECT C.CLIENTE_ID, CC.CLAVE_CLIENTE, C.NOMBRE AS NOMBRE_CLIENTE, " +
                    "C.TIPO_CLIENTE_ID, TC.NOMBRE AS TIPO_CLIENTE_NOMBRE, " +
                    "C.ZONA_CLIENTE_ID, ZC.NOMBRE AS ZONA_CLIENTE_NOMBRE, " +
                    "C.COBRADOR_ID, " +
                    "CB.NOMBRE AS COBRADOR_NOMBRE, " +
                    "C.VENDEDOR_ID, " +
                    "V.NOMBRE AS VENDEDOR_NOMBRE, " +
                    "DC.DIR_CLI_ID, DC.RFC_CURP, DC.CALLE, DC.NUM_EXTERIOR, DC.NUM_INTERIOR, DC.COLONIA, DC.POBLACION, DC.CODIGO_POSTAL, DC.TELEFONO1, DC.TELEFONO2 " +
                    "FROM CLIENTES C " +
                    "INNER JOIN DIRS_CLIENTES DC ON DC.CLIENTE_ID=C.CLIENTE_ID " +
                    "INNER JOIN TIPOS_CLIENTES TC ON TC.TIPO_CLIENTE_ID=C.TIPO_CLIENTE_ID " +
                    "INNER JOIN ZONAS_CLIENTES ZC ON ZC.ZONA_CLIENTE_ID=C.ZONA_CLIENTE_ID " +
                    "INNER JOIN COBRADORES CB ON CB.COBRADOR_ID=C.COBRADOR_ID " +
                    "INNER JOIN VENDEDORES V ON V.VENDEDOR_ID=C.VENDEDOR_ID " +
                    "INNER JOIN CLAVES_CLIENTES CC ON CC.CLIENTE_ID=C.CLIENTE_ID " +            
                    "WHERE C.ESTATUS IN (SELECT ESTATUS FROM CONFIGURACION_CLIENTES) AND C.VENDEDOR_ID = " + vendedorId +
                    " AND DC.NOMBRE_CONSIG LIKE '%principal%'"
                );
            /*if (configuracionMobil.getRestringeClientes() == 1)
                preparedStatement = conexion.prepareStatement(
                    "SELECT C.CLIENTE_ID, CC.CLAVE_CLIENTE, C.NOMBRE AS NOMBRE_CLIENTE, " +
                    "C.TIPO_CLIENTE_ID, TC.NOMBRE AS TIPO_CLIENTE_NOMBRE, " +
                    "C.ZONA_CLIENTE_ID, ZC.NOMBRE AS ZONA_CLIENTE_NOMBRE, " +
                    "C.COBRADOR_ID, " +
                    "CB.NOMBRE AS COBRADOR_NOMBRE, " +
                    "C.VENDEDOR_ID, " +
                    "V.NOMBRE AS VENDEDOR_NOMBRE, " +
                    "DC.DIR_CLI_ID, DC.RFC_CURP, DC.CALLE, DC.NUM_EXTERIOR, DC.NUM_INTERIOR, DC.COLONIA, DC.POBLACION, DC.CODIGO_POSTAL, DC.TELEFONO1, DC.TELEFONO2 " +
                    "FROM CLIENTES C " +
                    "INNER JOIN DIRS_CLIENTES DC ON DC.CLIENTE_ID=C.CLIENTE_ID " +
                    "INNER JOIN TIPOS_CLIENTES TC ON TC.TIPO_CLIENTE_ID=C.TIPO_CLIENTE_ID " +
                    "INNER JOIN ZONAS_CLIENTES ZC ON ZC.ZONA_CLIENTE_ID=C.ZONA_CLIENTE_ID " +
                    "INNER JOIN COBRADORES CB ON CB.COBRADOR_ID=C.COBRADOR_ID " +
                    "INNER JOIN VENDEDORES V ON V.VENDEDOR_ID=C.VENDEDOR_ID " +
                    "INNER JOIN CLAVES_CLIENTES CC ON CC.CLIENTE_ID=C.CLIENTE_ID " +            
                    "WHERE C.ESTATUS = 'A' AND C.VENDEDOR_ID = " + vendedorId +
                    " AND DC.NOMBRE_CONSIG LIKE '%principal%'"
                );
            else
                preparedStatement = conexion.prepareStatement(
                    "SELECT C.CLIENTE_ID, CC.CLAVE_CLIENTE, C.NOMBRE AS NOMBRE_CLIENTE, " +
                    "C.TIPO_CLIENTE_ID, TC.NOMBRE AS TIPO_CLIENTE_NOMBRE, " +
                    "C.ZONA_CLIENTE_ID, ZC.NOMBRE AS ZONA_CLIENTE_NOMBRE, " +
                    "C.COBRADOR_ID, " +
                    "CB.NOMBRE AS COBRADOR_NOMBRE, " +
                    "C.VENDEDOR_ID, " +
                    "V.NOMBRE AS VENDEDOR_NOMBRE, " +
                    "DC.DIR_CLI_ID, DC.RFC_CURP, DC.CALLE, DC.NUM_EXTERIOR, DC.NUM_INTERIOR, DC.COLONIA, DC.POBLACION, DC.CODIGO_POSTAL, DC.TELEFONO1, DC.TELEFONO2 " +
                    "FROM CLIENTES C " +
                    "INNER JOIN DIRS_CLIENTES DC ON DC.CLIENTE_ID=C.CLIENTE_ID " +
                    "INNER JOIN TIPOS_CLIENTES TC ON TC.TIPO_CLIENTE_ID=C.TIPO_CLIENTE_ID " +
                    "INNER JOIN ZONAS_CLIENTES ZC ON ZC.ZONA_CLIENTE_ID=C.ZONA_CLIENTE_ID " +
                    "INNER JOIN COBRADORES CB ON CB.COBRADOR_ID=C.COBRADOR_ID " +
                    "INNER JOIN VENDEDORES V ON V.VENDEDOR_ID=C.VENDEDOR_ID " +
                    "INNER JOIN CLAVES_CLIENTES CC ON CC.CLIENTE_ID=C.CLIENTE_ID " +                    
                    "WHERE C.VENDEDOR_ID = " + vendedorId +
                    " AND DC.NOMBRE_CONSIG LIKE '%principal%'"
                );
            */
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setClienteId(resultSet.getInt("CLIENTE_ID"));
                cliente.setClaveCliente(resultSet.getString("CLAVE_CLIENTE"));
                String nombreCliente = resultSet.getString("NOMBRE_CLIENTE");
                nombreCliente = nombreCliente.replace("'", "\""); 
                nombreCliente = escapeCharacters(nombreCliente);                                    
                cliente.setNombreCliente(nombreCliente);
                cliente.setTipoClienteId(resultSet.getInt("TIPO_CLIENTE_ID"));
                cliente.setTipoClienteNombre(resultSet.getString("TIPO_CLIENTE_NOMBRE"));
                cliente.setZonaClienteId(resultSet.getInt("ZONA_CLIENTE_ID"));
                cliente.setZonaClienteNombre(resultSet.getString("ZONA_CLIENTE_NOMBRE"));
                cliente.setCobradorId(resultSet.getInt("COBRADOR_ID"));
                cliente.setCobradorNombre(resultSet.getString("COBRADOR_NOMBRE"));
                cliente.setVendedorId(resultSet.getInt("VENDEDOR_ID"));
                cliente.setVendedorNombre(resultSet.getString("VENDEDOR_NOMBRE"));
                cliente.setRfcCurp(resultSet.getString("RFC_CURP"));
                cliente.setDireccionClienteId(resultSet.getInt("DIR_CLI_ID"));
                cliente.setCalle(resultSet.getString("CALLE"));
                cliente.setNumeroExterior(resultSet.getString("NUM_EXTERIOR"));
                cliente.setNumeroInterior(resultSet.getString("NUM_INTERIOR"));
                cliente.setColonia(resultSet.getString("COLONIA"));
                cliente.setPoblacion(resultSet.getString("POBLACION"));
                cliente.setCodigoPostal(resultSet.getString("CODIGO_POSTAL"));
                cliente.setTelefono1(resultSet.getString("TELEFONO1"));
                cliente.setTelefono2(resultSet.getString("TELEFONO2"));
                listaCliente.add(cliente);
            }
            
            return gson.toJson(listaCliente);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    public String clientes() throws SQLException {
        try {
            List<Cliente> listaCliente = new ArrayList<Cliente>();
                     
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT C.CLIENTE_ID, CC.CLAVE_CLIENTE, C.NOMBRE AS NOMBRE_CLIENTE, " +
                "C.TIPO_CLIENTE_ID, TC.NOMBRE AS TIPO_CLIENTE_NOMBRE, " +
                "C.ZONA_CLIENTE_ID, ZC.NOMBRE AS ZONA_CLIENTE_NOMBRE, " +
                "C.COBRADOR_ID, " +
                "CB.NOMBRE AS COBRADOR_NOMBRE, " +
                "C.VENDEDOR_ID, " +
                "V.NOMBRE AS VENDEDOR_NOMBRE, " +
                "DC.DIR_CLI_ID, DC.RFC_CURP, DC.CALLE, DC.NUM_EXTERIOR, DC.NUM_INTERIOR, DC.COLONIA, DC.POBLACION, DC.CODIGO_POSTAL, DC.TELEFONO1, DC.TELEFONO2 " +
                "FROM CLIENTES C " +
                "INNER JOIN DIRS_CLIENTES DC ON DC.CLIENTE_ID=C.CLIENTE_ID " +
                "INNER JOIN TIPOS_CLIENTES TC ON TC.TIPO_CLIENTE_ID=C.TIPO_CLIENTE_ID " +
                "INNER JOIN ZONAS_CLIENTES ZC ON ZC.ZONA_CLIENTE_ID=C.ZONA_CLIENTE_ID " +
                "INNER JOIN COBRADORES CB ON CB.COBRADOR_ID=C.COBRADOR_ID " +
                "INNER JOIN VENDEDORES V ON V.VENDEDOR_ID=C.VENDEDOR_ID " +
                "INNER JOIN CLAVES_CLIENTES CC ON CC.CLIENTE_ID=C.CLIENTE_ID " +                
		"WHERE C.ESTATUS = 'A'" +
                " AND DC.NOMBRE_CONSIG LIKE '%principal%'"
            );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setClienteId(resultSet.getInt("CLIENTE_ID"));
                cliente.setClaveCliente(resultSet.getString("CLAVE_CLIENTE"));
                String nombreCliente = resultSet.getString("NOMBRE_CLIENTE");
                nombreCliente = nombreCliente.replace("'", "\""); 
                nombreCliente = escapeCharacters(nombreCliente);                                    
                cliente.setNombreCliente(nombreCliente);
                cliente.setTipoClienteId(resultSet.getInt("TIPO_CLIENTE_ID"));
                cliente.setTipoClienteNombre(resultSet.getString("TIPO_CLIENTE_NOMBRE"));
                cliente.setZonaClienteId(resultSet.getInt("ZONA_CLIENTE_ID"));
                cliente.setZonaClienteNombre(resultSet.getString("ZONA_CLIENTE_NOMBRE"));
                cliente.setCobradorId(resultSet.getInt("COBRADOR_ID"));
                cliente.setCobradorNombre(resultSet.getString("COBRADOR_NOMBRE"));
                cliente.setVendedorId(resultSet.getInt("VENDEDOR_ID"));
                cliente.setVendedorNombre(resultSet.getString("VENDEDOR_NOMBRE"));
                cliente.setRfcCurp(resultSet.getString("RFC_CURP"));
                cliente.setDireccionClienteId(resultSet.getInt("DIR_CLI_ID"));
                cliente.setCalle(resultSet.getString("CALLE"));
                cliente.setNumeroExterior(resultSet.getString("NUM_EXTERIOR"));
                cliente.setNumeroInterior(resultSet.getString("NUM_INTERIOR"));
                cliente.setColonia(resultSet.getString("COLONIA"));
                cliente.setPoblacion(resultSet.getString("POBLACION"));
                cliente.setCodigoPostal(resultSet.getString("CODIGO_POSTAL"));
                cliente.setTelefono1(resultSet.getString("TELEFONO1"));
                cliente.setTelefono2(resultSet.getString("TELEFONO2"));
                listaCliente.add(cliente);
            }
            
            return gson.toJson(listaCliente);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    /*
    public String cobranza() throws SQLException {        
        try {
            List<Cobranza> listaCobranza = new ArrayList<Cobranza>();
            
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT C.CLIENTE_ID, C.NOMBRE, B.DOCTO_CC_ID, B.FOLIO, DCC.FECHA AS FECHA_ELABORACION, " +
                "B.FECHA_VENCIMIENTO, B.IMPORTE_CARGO, B.SALDO_CARGO, B.ATRASO, B.CONCEPTO_CC_ID " +
                "FROM CLIENTES C " +
                "LEFT JOIN CARGOS_CLIENTE(C.cliente_id,current_date,current_date,'N','S')B on 1=1 " +
                "INNER JOIN DOCTOS_CC DCC ON DCC.DOCTO_CC_ID=B.DOCTO_CC_ID " +
                "WHERE B.DOCTO_CC_ID IS NOT NULL " +                
                "ORDER BY B.ATRASO"
            );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cobranza cobranza = new Cobranza();
                cobranza.setClienteId(resultSet.getInt("CLIENTE_ID"));
                cobranza.setNombreCliente(resultSet.getString("NOMBRE"));
                cobranza.setDoctoCCId(resultSet.getInt("DOCTO_CC_ID"));
                cobranza.setFolio(resultSet.getString("FOLIO"));
                cobranza.setFechaElaboracion(resultSet.getDate("FECHA_ELABORACION"));
                cobranza.setFechaVencimiento(resultSet.getDate("FECHA_VENCIMIENTO"));
                cobranza.setImporteCargo(resultSet.getDouble("IMPORTE_CARGO"));
                cobranza.setSaldoCargo(resultSet.getDouble("SALDO_CARGO"));
                cobranza.setAtraso(resultSet.getInt("ATRASO"));
                cobranza.setConceptoCCId(resultSet.getInt("CONCEPTO_CC_ID"));
                listaCobranza.add(cobranza);
            }
            return gson.toJson(listaCobranza);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    */
    public String cobranza(int vendedorId) throws SQLException {        
        try {
            List<Cobranza> listaCobranza = new ArrayList<Cobranza>();
            
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT C.CLIENTE_ID, C.NOMBRE, B.DOCTO_CC_ID, B.FOLIO, DCC.FECHA AS FECHA_ELABORACION, " +
                "B.FECHA_VENCIMIENTO, B.IMPORTE_CARGO, B.SALDO_CARGO, B.ATRASO, B.CONCEPTO_CC_ID " +
                "FROM CLIENTES C " +
                "LEFT JOIN CARGOS_CLIENTE(C.cliente_id,current_date,current_date,'N','S')B on 1=1 " +
                "INNER JOIN DOCTOS_CC DCC ON DCC.DOCTO_CC_ID=B.DOCTO_CC_ID " +
                "WHERE B.DOCTO_CC_ID IS NOT NULL " +
                "AND C.VENDEDOR_ID = " + vendedorId + 
                "ORDER BY B.ATRASO"
            );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cobranza cobranza = new Cobranza();
                cobranza.setClienteId(resultSet.getInt("CLIENTE_ID"));
                cobranza.setNombreCliente(resultSet.getString("NOMBRE"));
                cobranza.setDoctoCCId(resultSet.getInt("DOCTO_CC_ID"));
                cobranza.setFolio(resultSet.getString("FOLIO"));
                cobranza.setFechaElaboracion(resultSet.getDate("FECHA_ELABORACION"));
                cobranza.setFechaVencimiento(resultSet.getDate("FECHA_VENCIMIENTO"));
                cobranza.setImporteCargo(resultSet.getDouble("IMPORTE_CARGO"));
                cobranza.setSaldoCargo(resultSet.getDouble("SALDO_CARGO"));
                cobranza.setAtraso(resultSet.getInt("ATRASO"));
                cobranza.setConceptoCCId(resultSet.getInt("CONCEPTO_CC_ID"));
                listaCobranza.add(cobranza);
            }
            return gson.toJson(listaCobranza);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    
    
    
    
    
    
    
    public String ventaUtilidades(java.sql.Date fechaInicial, java.sql.Date fechaFinal) throws SQLException {
        try {
            List<VentaUtilidad> listaVentaUtilidad = new ArrayList<VentaUtilidad>();

            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT FECHA,ID_CLIENTE,NOMBRE_CLIENTE,AGENTE,LINEA,FAMILIA,NUMART,NOMART,NUMALM,NOMBREALM,"
                    + " SUM( SUM_OF_COSTO_CON_IVA  )AS COSTODEVENTA,"
                    + " SUM( UTILIDAD  ) AS UTILIDAD, "
                    + " SUM( SUM_OF_CANTIDAD) AS CANTIDAD,"
                    + " SUM( SUM_OF_PRECIO_CON_IVA  )AS PRECIOVENTA,"
                    + " SUM( PRECIOUNO  )AS PRECIOUNO,"
                    + " SUM( CANTIDADUNO  )AS CANTIDADUNO,"
                    + " SUM( PRECIODOS  )AS PRECIODOS,"
                    + " SUM( CANTIDADDOS  )AS CANTIDADDOS,"
                    + " SUM( PRECIOTRES  )AS PRECIOTRES,"
                    + " SUM( CANTIDADTRES  )AS CANTIDADTRES,"
                    + " SUM( PRECIOCUATRO  )AS PRECIOCUATRO,"
                    + " SUM( CANTIDADCUATRO  )AS CANTIDADCUATRO,"
                    + " SUM( PRECIOCINCO  )AS PRECIOCINCO,"
                    + " SUM( CANTIDADCINCO  )AS CANTIDADCINCO"
                    + " FROM RESUMENDIARIOVENTAUTILIDADES(?, ?)"
                    + " GROUP BY 1,2,3,4,5,6,7,8,9,10"
                    + " ORDER BY 1,2,3,4,5,6,7,8,9,10"
            );
            preparedStatement.setDate(1, fechaInicial);
            preparedStatement.setDate(2, fechaFinal);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                VentaUtilidad ventaUtilidad = new VentaUtilidad();
                ventaUtilidad.setFecha(resultSet.getDate("FECHA"));
                ventaUtilidad.setIdCliente(resultSet.getString("ID_CLIENTE"));
                ventaUtilidad.setNombreCliente(resultSet.getString("NOMBRE_CLIENTE"));
                ventaUtilidad.setAgente(resultSet.getString("AGENTE"));
                ventaUtilidad.setLinea(resultSet.getString("LINEA"));
                ventaUtilidad.setFamilia(resultSet.getString("FAMILIA"));
                ventaUtilidad.setCodigoArticulo(resultSet.getString("NUMART"));
                ventaUtilidad.setNombreArticulo(resultSet.getString("NOMART"));
                ventaUtilidad.setClaveAlmacen(resultSet.getString("NUMALM"));
                ventaUtilidad.setNombreAlmacen(resultSet.getString("NOMBREALM"));
                ventaUtilidad.setCostoVenta(resultSet.getDouble("COSTODEVENTA"));
                ventaUtilidad.setUtilidad(resultSet.getDouble("UTILIDAD"));
                ventaUtilidad.setCantidad(resultSet.getDouble("CANTIDAD"));
                ventaUtilidad.setPrecioVenta(resultSet.getDouble("PRECIOVENTA"));                
                ventaUtilidad.setPrecioUno(resultSet.getDouble("PRECIOUNO"));
                ventaUtilidad.setCantidadUno(resultSet.getDouble("CANTIDADUNO"));
                ventaUtilidad.setPrecioDos(resultSet.getDouble("PRECIODOS"));
                ventaUtilidad.setCantidadDos(resultSet.getDouble("CANTIDADDOS"));
                ventaUtilidad.setPrecioTres(resultSet.getDouble("PRECIOTRES"));
                ventaUtilidad.setCantidadTres(resultSet.getDouble("CANTIDADTRES"));
                ventaUtilidad.setPrecioCuatro(resultSet.getDouble("PRECIOCUATRO"));
                ventaUtilidad.setCantidadCuatro(resultSet.getDouble("CANTIDADCUATRO"));
                ventaUtilidad.setPrecioCinco(resultSet.getDouble("PRECIOCINCO"));
                ventaUtilidad.setCantidadCinco(resultSet.getDouble("CANTIDADCINCO"));                
                listaVentaUtilidad.add(ventaUtilidad);
            }

            Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("dd/MM/yyyy")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
            return gson.toJson(listaVentaUtilidad);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    public String pedidosNuevos() throws SQLException {
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT COUNT(*) AS PEDIDOSNUEVOS, SUM(IMPORTEG) AS TOTALMONTOPEDIDO FROM CABPEDIDOS WHERE ESTADO='RECIBIDO' "         
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            PedidoNuevo pedidoNuevo = new PedidoNuevo();
            while (resultSet.next()) {                
                pedidoNuevo.setPedidosNuevos(resultSet.getInt("PEDIDOSNUEVOS")); 
                pedidoNuevo.setTotalMontoPedido(resultSet.getDouble("TOTALMONTOPEDIDO")); 
            }

            Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("dd/MM/yyyy")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
            return gson.toJson(pedidoNuevo);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    public String pedidosNuevosDetalle() {
        try {
            List<PedidoNuevoDetalle> listaPedidosNuevosDetalle = new ArrayList<PedidoNuevoDetalle>();

            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT P.NSEMOV,P.FOLMOV,P.FECMOV,P.NUMCTE,C.NOMCTE,P.IMPORTEG, P.NUMAGT, MAGT.NOMBREAGT " +
                    "FROM CABPEDIDOS P " +
                    "INNER JOIN MAECTE C ON C.NUMCTE=P.NUMCTE " +
                    "INNER JOIN MAEAGT MAGT ON MAGT.NUMAGT = P.NUMAGT " +
                    "WHERE P.ESTADO='RECIBIDO' "
            );
            
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PedidoNuevoDetalle pedido = new PedidoNuevoDetalle();
                pedido.setSerie(resultSet.getString("NSEMOV"));
                pedido.setFolio(resultSet.getInt("FOLMOV"));
                pedido.setFecha(resultSet.getDate("FECMOV"));
                pedido.setCodigoCliente(resultSet.getString("NUMCTE"));
                pedido.setNombreCliente(resultSet.getString("NOMCTE"));
                pedido.setMontoPedido(resultSet.getDouble("IMPORTEG"));
                pedido.setCodigoAgente(resultSet.getString("NUMAGT"));
                pedido.setNombreAgente(resultSet.getString("NOMBREAGT"));
                listaPedidosNuevosDetalle.add(pedido);
            }

            Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("dd/MM/yyyy")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
            return gson.toJson(listaPedidosNuevosDetalle);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    public String datosEmpresa() throws SQLException {
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT NOMBRE, CALLE, NOMBRE_CALLE, NUM_EXTERIOR, NUM_INTERIOR, COLONIA, POBLACION," +
                    "CIUDAD, ESTADO, CODIGO_POSTAL, PAIS, TELEFONO1, TELEFONO2, EMAIL, RFC FROM DATOS_EMPRESA"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();

            DatosEmpresa datosEmpresa = new DatosEmpresa();
            while (resultSet.next()) {               
                datosEmpresa.setNombre(resultSet.getString("NOMBRE"));                                                    
                datosEmpresa.setCalle(resultSet.getString("CALLE"));                                                    
                datosEmpresa.setNombreCalle(resultSet.getString("NOMBRE_CALLE"));                                                    
                datosEmpresa.setNumeroExterior(resultSet.getString("NUM_EXTERIOR"));                                                    
                datosEmpresa.setNumeroInterior(resultSet.getString("NUM_INTERIOR"));                                                    
                datosEmpresa.setColonia(resultSet.getString("COLONIA"));                                                    
                datosEmpresa.setPoblacion(resultSet.getString("POBLACION"));                                                    
                datosEmpresa.setCiudad(resultSet.getString("CIUDAD"));                                                    
                datosEmpresa.setEstado(resultSet.getString("ESTADO"));                                                    
                datosEmpresa.setCodigoPostal(resultSet.getInt("CODIGO_POSTAL"));
                datosEmpresa.setPais(resultSet.getString("PAIS"));
                datosEmpresa.setTelefono1(resultSet.getString("TELEFONO1"));
                datosEmpresa.setTelefono2(resultSet.getString("TELEFONO2"));
                datosEmpresa.seteMail(resultSet.getString("EMAIL"));
                datosEmpresa.setRfc(resultSet.getString("RFC"));                
            }
            
            return gson.toJson(datosEmpresa);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            Resources.logger.error("error:"+exception.getMessage());
            return null;
        }
    }
    
    public String viasEmbarque() throws SQLException {
        List<ViaEmbarque> listaViasEmbarques = new ArrayList<ViaEmbarque>();
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT VIA_EMBARQUE_ID, NOMBRE FROM VIAS_EMBARQUE"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {               
                ViaEmbarque viaEmbarque = new ViaEmbarque();
                viaEmbarque.setViaEmbarqueId(resultSet.getInt("VIA_EMBARQUE_ID"));                                                    
                viaEmbarque.setNombre(resultSet.getString("NOMBRE"));                                                                    
                listaViasEmbarques.add(viaEmbarque);
            }
            
            return gson.toJson(listaViasEmbarques);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            Resources.logger.error("error:"+exception.getMessage());
            return null;
        }
    }
    
    public String rolesClavesArticulos() throws SQLException {
        List<RolClaveArticulo> listaRolesClavesArticulos = new ArrayList<RolClaveArticulo>();
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT ROL_CLAVE_ART_ID, NOMBRE FROM ROLES_CLAVES_ARTICULOS"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {               
                RolClaveArticulo rolClaveArticulo = new RolClaveArticulo();
                rolClaveArticulo.setRolClaveArticuloId(resultSet.getInt("ROL_CLAVE_ART_ID"));                                                    
                rolClaveArticulo.setNombre(resultSet.getString("NOMBRE"));                                                                    
                listaRolesClavesArticulos.add(rolClaveArticulo);
            }
            
            return gson.toJson(listaRolesClavesArticulos);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            Resources.logger.error("error:"+exception.getMessage());
            return null;
        }
    }
    
    public String preciosEmpresa() throws SQLException {
        List<PrecioEmpresa> listaPreciosEmpresa = new ArrayList<PrecioEmpresa>();
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT PRECIO_EMPRESA_ID, NOMBRE FROM PRECIOS_EMPRESA"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {               
                PrecioEmpresa precioEmpresa = new PrecioEmpresa();
                precioEmpresa.setPrecioEmpresaId(resultSet.getInt("PRECIO_EMPRESA_ID"));                                                    
                precioEmpresa.setNombre(resultSet.getString("NOMBRE"));                                                                    
                listaPreciosEmpresa.add(precioEmpresa);
            }
            
            return gson.toJson(listaPreciosEmpresa);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            Resources.logger.error("error:"+exception.getMessage());
            return null;
        }
    }
    
    public String sucursalesEmpresa() throws SQLException {
        List<Sucursal> listaSucursalesEmpresa = new ArrayList<Sucursal>();
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT SUCURSAL_ID, NOMBRE FROM SUCURSALES"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {               
                Sucursal sucursal = new Sucursal();
                sucursal.setSucursalId(resultSet.getInt("SUCURSAL_ID"));                                                    
                sucursal.setNombre(resultSet.getString("NOMBRE"));                                                                    
                listaSucursalesEmpresa.add(sucursal);
            }
            
            return gson.toJson(listaSucursalesEmpresa);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            Resources.logger.error("error:"+exception.getMessage());
            return null;
        }
    }
    
    public String conceptosCuentasXCobrar() throws SQLException {
        List<ConceptoCuentaXCobrar> listaConceptosCuentasXCobrar = new ArrayList<ConceptoCuentaXCobrar>();
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT CONCEPTO_CC_ID, NOMBRE FROM CONCEPTOS_CC WHERE NATURALEZA = 'R'"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {               
                ConceptoCuentaXCobrar conceptoCuentaXCobrar = new ConceptoCuentaXCobrar();
                conceptoCuentaXCobrar.setConceptoCCId(resultSet.getInt("CONCEPTO_CC_ID"));                                                    
                conceptoCuentaXCobrar.setNombre(resultSet.getString("NOMBRE"));                                                                    
                listaConceptosCuentasXCobrar.add(conceptoCuentaXCobrar);
            }
            
            return gson.toJson(listaConceptosCuentasXCobrar);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            Resources.logger.error("error:"+exception.getMessage());
            return null;
        }
    }
    
    public String condicionesDePago() throws SQLException {
        List<CondicionPago> listaCondicionesDePago = new ArrayList<CondicionPago>();
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT COND_PAGO_ID, NOMBRE FROM CONDICIONES_PAGO"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {               
                CondicionPago CondicionPago = new CondicionPago();
                CondicionPago.setCondicionPagoId(resultSet.getInt("COND_PAGO_ID"));                                                    
                CondicionPago.setNombre(resultSet.getString("NOMBRE"));                                                                    
                listaCondicionesDePago.add(CondicionPago);
            }
            
            return gson.toJson(listaCondicionesDePago);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            Resources.logger.error("error:"+exception.getMessage());
            return null;
        }
    }
    
    public String configuracionMobil() throws SQLException {        
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT MICROSIP_2020, SUCURSAL_ID, PRECIO_EMPRESA_ID, CONCEPTO_CUENTA_X_COBRAR_ID, "
                            + "CONDICION_PAGO_ID, ROL_ART_CLAVE_PRINCIPAL_ID, "
                            + "ROL_ART_CLAVE_ALTERNA_ID, ROL_ART_CODIGO_BARRA_ID, ROL_ART_CODIGO_BARRA_INNER_ID, ROL_ART_CODIGO_BARRA_MASTER_ID FROM CONFIGURACION_MOBIL"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            ConfiguracionMobil configuracionMobil = new ConfiguracionMobil();
            while (resultSet.next()) {               
                configuracionMobil.setMicrosip2020(resultSet.getInt("MICROSIP_2020"));
                configuracionMobil.setSucursalId(resultSet.getInt("VIA_EMBARQUE_ID"));                                                    
                configuracionMobil.setPrecioEmpresaId(resultSet.getInt("PRECIO_EMPRESA_ID"));                                                    
                configuracionMobil.setConceptoCCId(resultSet.getInt("CONCEPTO_CUENTA_X_COBRAR_ID"));                                                    
                configuracionMobil.setCondicionPagoId(resultSet.getInt("CONDICION_PAGO_ID"));                                                    
                configuracionMobil.setRolArticuloClavePrincipalId(resultSet.getInt("ROL_ART_CLAVE_PRINCIPAL_ID"));                                                    
                configuracionMobil.setRolArticuloClaveAlternaId(resultSet.getInt("ROL_ART_CLAVE_ALTERNA_ID"));                                                    
                configuracionMobil.setRolArticuloCodigoBarraId(resultSet.getInt("ROL_ART_CODIGO_BARRA_ID"));                                                    
                configuracionMobil.setRolArticuloCodigoBarraInnerId(resultSet.getInt("ROL_ART_CODIGO_BARRA_INNER_ID"));                                                    
                configuracionMobil.setRolArticuloCodigoBarraMasterId(resultSet.getInt("ROL_ART_CODIGO_BARRA_MASTER_ID"));                                                                    
            }
            
            return gson.toJson(configuracionMobil);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            Resources.logger.error("error:"+exception.getMessage());
            return null;
        }
    }
    
    public Boolean creaConfiguracionMobil(String jsonConfiguracion) {
        System.out.println("Json sin analizar: " + jsonConfiguracion);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            JsonElement jsonElement = new JsonParser().parse(jsonConfiguracion);
            //JsonObject jsonObject = jsonElement.getAsJsonObject();
            Gson gson = new Gson();
            Type collectionType = new TypeToken<ConfiguracionMobil>() {
            }.getType();
            ConfiguracionMobil configuracionMobil = gson.fromJson(jsonElement, collectionType);
          
            conexion.setAutoCommit(false);

            PreparedStatement preparedStatementObj = conexion.prepareStatement(
                    "DELETE FROM CONFIGURACION_MOBIL"
            );
            preparedStatementObj.executeUpdate();
            
            preparedStatementObj = conexion.prepareStatement(
                    "INSERT INTO CONFIGURACION_MOBIL "
                    + "(MICROSIP_2020, SUCURSAL_ID, PRECIO_EMPRESA_ID, CONCEPTO_CUENTA_X_COBRAR_ID, CONDICION_PAGO_ID, ROL_ART_CLAVE_PRINCIPAL_ID, ROL_ART_CLAVE_ALTERNA_ID, ROL_ART_CODIGO_BARRA_ID, ROL_ART_CODIGO_BARRA_INNER_ID, ROL_ART_CODIGO_BARRA_MASTER_ID) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStatementObj.setInt(1, configuracionMobil.getMicrosip2020());
            preparedStatementObj.setInt(2, configuracionMobil.getSucursalId());
            preparedStatementObj.setInt(3, configuracionMobil.getPrecioEmpresaId());
            preparedStatementObj.setInt(4, configuracionMobil.getConceptoCCId());
            preparedStatementObj.setInt(5, configuracionMobil.getCondicionPagoId());
            preparedStatementObj.setInt(6, configuracionMobil.getRolArticuloClavePrincipalId());
            preparedStatementObj.setInt(7, configuracionMobil.getRolArticuloClaveAlternaId());
            preparedStatementObj.setInt(8, configuracionMobil.getRolArticuloCodigoBarraId());
            preparedStatementObj.setInt(9, configuracionMobil.getRolArticuloCodigoBarraInnerId());
            preparedStatementObj.setInt(10, configuracionMobil.getRolArticuloCodigoBarraMasterId());
            preparedStatementObj.executeUpdate();
            System.out.println("Grabando en la cabecera");

            conexion.commit();
            
            return true;
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            System.out.println("SUCEDIO UNA EXEPCION en la cabecera del pedido: " + exception.getMessage());
            try {
                System.out.println("Transaction failed.");
                conexion.rollback();
                exception.printStackTrace();
                return false;
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return false;
    }
   
}