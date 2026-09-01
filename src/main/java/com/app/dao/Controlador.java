package com.app.dao;

import com.app.contants.ConnectionClient;
import com.app.contants.Constants;
import com.app.models.AbonoDetalleEntity;
import com.app.models.AbonoDetalleModel;
import com.app.models.AbonoMaestroEntity;
import com.app.models.AbonoMaestroModel;
import com.app.models.AgenteCobranza;
import com.app.models.ArticuloAlmacenRefactor;
import com.app.models.ArticuloMensaje;
import com.app.models.ArticuloPromedioVenta45;
import com.app.models.ArticuloPromedioVenta45POST;
import com.app.models.CatalogosConfiguracionMobil;
import com.app.models.Chofer;
import com.app.models.ClienteConsignatario;
import com.app.models.ClienteDireccionPrincipal;
import com.app.models.ClienteEmiteFactura;
import com.app.models.ClientePOPSencillo;
import com.app.models.CobradorSucursal;
import com.app.models.CobroXDepositarEntity;
import com.app.models.CobroXDepositarModel;
import com.app.models.ComplementoXml;
import com.app.models.ComplementoXmlDetalle;
import com.app.models.ConceptoCuentaXCobrar;
import com.app.models.CondicionPago;
import com.app.models.ConfiguracionAlmacen;
import com.app.models.ConfiguracionCliente;
import com.app.models.ConfiguracionMobil;
import com.app.models.ConfiguracionPrecio;
import com.app.models.CuentaBancaria;
import com.app.models.DepositoDetalleEntity;
import com.app.models.cobrosxdepositar.CobroXDepositarGrabado;
import com.app.models.DepositoMaestroEntity;
import com.app.models.DetalleDocumentoCXC;
import com.app.models.DetallePedido;
import com.app.models.EquivalenciaAbonoMicrosip;
import com.app.models.ExistenciaArticulo;
import com.app.models.GiroComercial;
import com.app.models.HistoriaCambiaria;
import com.app.models.Localizacion;
import com.app.models.LocalizacionMaps;
import com.app.models.MaeMovCa02;
import com.app.models.MaestroPedido;
import com.app.models.Metadata;
import com.app.models.Moneda;
import com.app.models.MonedaHistoriaCambiaria;
import com.app.models.Motivo;
import com.app.models.PedidoGrabado;
import com.app.models.PedidoNuevo;
import com.app.models.PedidoNuevoDetalle;
import com.app.models.PoliticaCliente;
import com.app.models.PoliticaDescuentoArticuloCliente;
import com.app.models.PoliticaDescuentoArticuloClienteEntity;
import com.app.models.PoliticaDescuentoArticuloClienteRefactor;
import com.app.models.PoliticaXVolumen;
import com.app.models.PorcentajeDiaPLazo;
import com.app.models.PorcentajeTrimestral;
import com.app.models.PrecioEmpresa;
import com.app.models.ProcesaPoliticas;
import com.app.models.ReporteMovil;
import com.app.models.RolClaveArticulo;
import com.app.models.RutaDTO;
import com.app.models.RutaDepurada;
import com.app.models.RutaOrdenadaConMaps;
import com.app.models.SerieFolioCXC;
import com.app.models.SerieFolioVendedor;
import com.app.models.VentaUtilidad;
import com.app.models.ViaEmbarque;
import com.app.models.VisitaClienteGrabado;
import com.app.models.VisitaEfectivaInefectiva;
import com.app.models.almacenes.Almacen;
import com.app.models.articuloalmacen.ArticuloAlmacen;
import com.app.models.articulos.Articulo;
import com.app.models.articulos.ArticuloRefactor;
import com.app.models.articulos.ArticuloVolumen;
import com.app.models.bancos.Banco;
import com.app.models.clientes.Cliente;
import com.app.models.clientes.ClienteRefactor;
import com.app.models.cobradores.Cobrador;
import com.app.models.cobranza.Cobranza;
import com.app.models.cobranza.CobranzaRefactor;
import com.app.models.cobrosmicrosip.CobroMicrosip;
import com.app.models.cobrosxdepositar.CobroXDepositar;
import com.app.models.cobrosxdepositar.CobroXDepositarEnviado;
import com.app.models.datosempresa.DatosEmpresa;
import com.app.models.depositos.Deposito;
import com.app.models.depositos.DepositoDetalle;
import com.app.models.depositos.DepositoGrabado;
import com.app.models.depositos.DepositoMaestro;
import com.app.models.metodospago.MetodoPago;
import com.app.models.pop.ClientePOP;
import com.app.models.pop.POPDetalle;
import com.app.models.pop.Trimestre;
import com.app.models.promociones.Promocion;
import com.app.models.sucursales.Sucursal;
import com.app.models.vendedores.Vendedor;
import com.app.models.vendedorescobradores.VendedorCobrador;
import com.app.servicios.Resources;
import com.app.utilerias.ResponseRequest;
import com.app.utilerias.Utileria;
import com.app.utilerias.fecha.Fecha;
import com.app.utilerias.fecha.FechaHora;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.commons.lang3.StringEscapeUtils;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import java.util.Locale;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.lang3.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author angel
 */
public class Controlador {
    protected Connection conexion;
    protected Statement instruccion;

    
    private Utileria utileria = new Utileria();
    private Gson gson;
    private FechaHora fechaHora;
    private Fecha fechaInicioFin;
    
    private ConfiguracionMobil configuracionMobil;
    
    public Controlador() {
        try {            
            //tryConfigureJNA();           
            Class.forName("org.firebirdsql.jdbc.FBDriver");            
            conexion = DriverManager.getConnection(
                    ConnectionClient.CONNECTION_BD, ConnectionClient.USSER, ConnectionClient.PASSWORD
            );
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
    
    /*public String executeScript(String script) {
        System.out.println("TRATANDO DE EJECUTAR SCRIPT");
        PreparedStatement stmt = null;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(script);
            preparedStatement.executeUpdate();
         
            return ("Script creado correctamente");
        } catch (SQLException e) {
            return ("Script fallido: " + e.getMessage());
        }
    }*/  
    
    public String executeScript(String script) {
        //System.out.println("TRATANDO DE EJECUTAR SCRIPT "+ script);

        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(script);
            //System.out.println("Script creado correctamente");
            return "Script creado correctamente";
        } catch (SQLException e) {
            return "Script fallido: " + e.getMessage();
        }
    }

    
    public ResponseRequest seriesFoliosVendedores() throws SQLException {                      
        ResponseRequest responseRequest = new ResponseRequest();      
        
        ArrayList<SerieFolioVendedor> listaSeriesFoliosVendedores = new ArrayList<SerieFolioVendedor>();
        Utilerias utilerias = new Utilerias();  
        try {       
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT V.VENDEDOR_ID, V.NOMBRE, VSF.SERIE, VSF.FOLIO FROM VENDEDORES V " +
                "INNER JOIN VENDEDORES_SERIES_FOLIOS VSF ON VSF.VENDEDOR_ID = V.VENDEDOR_ID"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SerieFolioVendedor serieFolioVendedor = new SerieFolioVendedor();
                serieFolioVendedor.setVendedorId(resultSet.getInt("VENDEDOR_ID"));
                serieFolioVendedor.setNombre(resultSet.getString("NOMBRE"));
                serieFolioVendedor.setSerie(resultSet.getString("SERIE"));
                serieFolioVendedor.setFolio(resultSet.getInt("FOLIO"));
                listaSeriesFoliosVendedores.add(serieFolioVendedor);
            }
            
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaSeriesFoliosVendedores, "Series folios vendedores consultados correctamente");            
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en la cabecera seriesFoliosVendedores: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar seriesFoliosVendedores " + exception.getMessage());
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
                datosEmpresa.setRfc(resultSet.getString("RFC").trim());                
            }
            
            return gson.toJson(datosEmpresa);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            Resources.logger.error("error:"+exception.getMessage());
            return null;
        }
    }
    
    public ConfiguracionMobil configuracionMicrosip() throws SQLException{
        try {                    
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT PRECIO_EMPRESA_ID, CONCEPTO_CUENTA_X_COBRAR_ID, " +
                    "CONDICION_PAGO_ID, ROL_ART_CLAVE_PRINCIPAL_ID, ROL_ART_CLAVE_ALTERNA_ID, " +
	            "ROL_ART_CODIGO_BARRA_ID, ROL_ART_CODIGO_BARRA_INNER_ID, ROL_ART_CODIGO_BARRA_MASTER_ID, " +
                    "MICROSIP_2020, SUCURSAL_ID, APPLY_POLITICA_PRECIO_CLIENTES, COMPORTAMIENTO_CAPTURA, OPERA_DEPOSITOS, " +
                    "TIPO_POLITICA_A_OPERAR, DIAS_HISTORIA, COMPORTAMIENTO_ALMACEN, FOLIO_FISCAL_ID, SERIE_CONCEPTO_CC, OPERA_CONSIGNATARIOS," +
                    "APPLY_DESC_ARTS_CTES_PROMO, REGLA_GPS, DIAS_GRACIA_ID, OPERA_POLITICAS_X_VOLUMEN, OPERA_POLITICAS_X_PROMOCION, " +
                    "CONTROLA_SERIE_FOLIO_CXC, SINC_EXISTENCIA_ARTS, SINC_ART_CONDICIONADOS, SINC_EXIST_ARTS_CONDICIONADOS, SINC_CXC_X_RUTA, OPERA_SUCURSAL_ALMACEN, " +
                    "FORMA_CAPTURA_PARTIDA, OPERA_MONEDA_EXTRANJERA, DISMINUYE_ABONO_PARA_SALDO, SINCRONIZA_PEDIDO_TR, " +
                    "SINCRONIZA_ABONO_TR, ENVIA_SMS, OPERA_POP FROM CONFIGURACION_MOBIL"
                    //"APPLY_DESC_ARTS_CTES_PROMO, REGLA_GPS, DIAS_GRACIA_ID FROM CONFIGURACION_MOBIL"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            
            configuracionMobil = new ConfiguracionMobil();
            while (resultSet.next()) {
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
                configuracionMobil.setApplyPoliticaPrecioClientes(resultSet.getInt("APPLY_POLITICA_PRECIO_CLIENTES"));
                configuracionMobil.setComportamientoCaptura(resultSet.getString("COMPORTAMIENTO_CAPTURA"));                
                configuracionMobil.setOperaDepositos(resultSet.getInt("OPERA_DEPOSITOS"));                
                configuracionMobil.setTipoPoliticaAOperar(resultSet.getString("TIPO_POLITICA_A_OPERAR"));
                configuracionMobil.setDiasHistoria(resultSet.getInt("DIAS_HISTORIA"));
                if ((resultSet.getString("COMPORTAMIENTO_ALMACEN") == null)||(resultSet.getString("COMPORTAMIENTO_ALMACEN").equals("")))
                    configuracionMobil.setComportamientoAlmacen("FINAL");
                else
                    configuracionMobil.setComportamientoAlmacen(resultSet.getString("COMPORTAMIENTO_ALMACEN"));
                configuracionMobil.setFolioFiscalId(resultSet.getInt("FOLIO_FISCAL_ID"));
                configuracionMobil.setSerieConceptoCC(resultSet.getString("SERIE_CONCEPTO_CC"));
                configuracionMobil.setOperaConsignatarios(resultSet.getInt("OPERA_CONSIGNATARIOS"));
                configuracionMobil.setApplyDescArtsCtsPromo(resultSet.getInt("APPLY_DESC_ARTS_CTES_PROMO"));
                configuracionMobil.setReglaGPS(resultSet.getString("REGLA_GPS"));  
                configuracionMobil.setDiasGraciaId(resultSet.getInt("DIAS_GRACIA_ID"));
                configuracionMobil.setOperaPoliticasXVolumen(resultSet.getInt("OPERA_POLITICAS_X_VOLUMEN"));
                configuracionMobil.setOperaPoliticasXPromocion(resultSet.getInt("OPERA_POLITICAS_X_PROMOCION"));            
                configuracionMobil.setControlaSerieFolioCXC(resultSet.getInt("CONTROLA_SERIE_FOLIO_CXC"));  
                configuracionMobil.setSincExistenciaArts(resultSet.getInt("SINC_EXISTENCIA_ARTS"));  
                configuracionMobil.setSincArtsCondicionados(resultSet.getInt("SINC_ART_CONDICIONADOS"));  
                configuracionMobil.setSincExistArtsCondicionados(resultSet.getInt("SINC_EXIST_ARTS_CONDICIONADOS"));                  
                configuracionMobil.setSincCXCXRuta(resultSet.getInt("SINC_CXC_X_RUTA")); 
                configuracionMobil.setOperaSucursalAlmacen(resultSet.getInt("OPERA_SUCURSAL_ALMACEN"));
                configuracionMobil.setFormaCapturaPartida(resultSet.getString("FORMA_CAPTURA_PARTIDA"));
                configuracionMobil.setOperaMonedaExtranjera(resultSet.getInt("OPERA_MONEDA_EXTRANJERA"));
                configuracionMobil.setDisminuyeAbonoParaSaldo(resultSet.getInt("DISMINUYE_ABONO_PARA_SALDO"));                
                configuracionMobil.setDisminuyeAbonoParaSaldo(resultSet.getInt("DISMINUYE_ABONO_PARA_SALDO"));                
                configuracionMobil.setSincronizaPedidoTR(resultSet.getInt("SINCRONIZA_PEDIDO_TR"));                
                configuracionMobil.setSincronizaAbonoTR(resultSet.getInt("SINCRONIZA_ABONO_TR"));                
                configuracionMobil.setEnviaSMS(resultSet.getInt("ENVIA_SMS"));                
                configuracionMobil.setOperaPop(resultSet.getInt("OPERA_POP"));                
            }            
            configuracionMobil.setDiasGraciaId(configuracionMobil.getDiasGraciaId() == 0 ? 0 : diasGraciaFacturasVencidas(configuracionMobil.getDiasGraciaId()));
            configuracionMobil.setConfiguracionAlmacenes(configuracionAlmacenes());                
            configuracionMobil.setConfiguracionClientes(configuracionClientes());
            return configuracionMobil;           
        } catch (SQLException exception) {
            Resources.logger.error("errro==================" + exception.getMessage());           
            return null;
        } 
    }
    
    private List<ConfiguracionAlmacen> configuracionAlmacenes() {
        try {                         
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT ALMACEN_ID, ES_DEFAULT FROM CONFIGURACION_ALMACENES"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            List<ConfiguracionAlmacen> listaAlmacenes = new ArrayList<ConfiguracionAlmacen>();
            Double porcentajeDescuento = 0.00;
            while (resultSet.next()) {
                ConfiguracionAlmacen configuracionAlmacen = new ConfiguracionAlmacen();
                configuracionAlmacen.setAlmacenId(resultSet.getInt("ALMACEN_ID"));
                configuracionAlmacen.setEsDefault(resultSet.getInt("ES_DEFAULT"));
                listaAlmacenes.add(configuracionAlmacen);
            }            
            //System.out.println(gson.toJson(listaAlmacenes));
            return listaAlmacenes;           
        } catch (SQLException exception) {
            System.out.println("alamacenes==================" + exception.getMessage());           
            return null;
        }
    }
    
    private List<ConfiguracionCliente> configuracionClientes() {
        try {                                
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT ESTATUS FROM CONFIGURACION_CLIENTES"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            List<ConfiguracionCliente> listaConfiguracionClientes = new ArrayList<ConfiguracionCliente>();
            Double porcentajeDescuento = 0.00;
            while (resultSet.next()) {
                ConfiguracionCliente configuracionCliente = new ConfiguracionCliente();
                configuracionCliente.setEstatus(resultSet.getString("ESTATUS"));                
                listaConfiguracionClientes.add(configuracionCliente);
            }            
            //System.out.println(gson.toJson(listaConfiguracionClientes));
            return listaConfiguracionClientes;           
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());           
            return null;
        }
    }
    
    private int diasGraciaFacturasVencidas(int diasGraciaId) {
        try {             
            Resources.logger.info("-----" + diasGraciaId);
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT VALOR FROM REGISTRY WHERE ELEMENTO_ID = ?"
            );            
            preparedStatement.setInt(1, diasGraciaId);
            ResultSet resultSet = preparedStatement.executeQuery();            
            
            int diasDeGracia = 0;
            while (resultSet.next()) {
                diasDeGracia = resultSet.getInt("VALOR");
            }
            return diasDeGracia;           
        } catch (SQLException exception) {
            System.out.println("alamacenes==================" + exception.getMessage());           
            return 0;
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
    
    public ResponseRequest vendedoresCobranza() throws SQLException {                      
        ResponseRequest responseRequest = new ResponseRequest();      
        
        ArrayList<AgenteCobranza> listaAgentesCobranza = new ArrayList<AgenteCobranza>();        
        try {       
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT VENDEDOR_ID, VISUALIZA_COBRANZA FROM VENDEDORES_COBRANZA"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AgenteCobranza agenteCobranza = new AgenteCobranza();
                agenteCobranza.setVendedorId(resultSet.getInt("VENDEDOR_ID"));
                agenteCobranza.setVisualizaCobranza(resultSet.getString("VISUALIZA_COBRANZA"));
                listaAgentesCobranza.add(agenteCobranza);
            }
            
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaAgentesCobranza, "Agentes cobranza consultados correctamente");            
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en Agentes cobranza: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar Agentes cobranza " + exception.getMessage());
        }
    }
    
    public String almacenes() throws SQLException {
        try {
            List<Almacen> listaAlmacenes = new ArrayList<Almacen>();
                     
            PreparedStatement preparedStatement = conexion.prepareStatement(                    
                    "SELECT A.ALMACEN_ID, A.NOMBRE, CA.SUCURSAL_ID FROM ALMACENES A " +
                    "INNER JOIN CONFIGURACION_ALMACENES CA ON A.ALMACEN_ID =CA.ALMACEN_ID " +
                    "ORDER BY CA.ES_DEFAULT DESC"
            );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Almacen Almacen = new Almacen();
                Almacen.setAlmacenId(resultSet.getInt("ALMACEN_ID"));
                Almacen.setNombre(resultSet.getString("NOMBRE"));
                Almacen.setSucursalId(resultSet.getInt("SUCURSAL_ID"));
                listaAlmacenes.add(Almacen);
            }
            
            return gson.toJson(listaAlmacenes);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
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
    
    public String cuentasBancariasRefactor() throws SQLException {
        try {
            List<CuentaBancaria> listaCuentasBancarias = new ArrayList<CuentaBancaria>();
                
            String query = "";
            if (cuantasCuentasBancariasFiltro() > 0)
                query = "SELECT CB.CUENTA_BAN_ID, CB.BANCO_ID, B.NOMBRE, CB.NUM_CUENTA FROM CUENTAS_BANCARIAS CB " +
                "INNER JOIN BANCOS B ON B.BANCO_ID = CB.BANCO_ID " +
                "WHERE CB.CUENTA_BAN_ID IN (SELECT CUENTA_BAN_ID FROM CONFIGURACION_CUENTAS_BANCARIAS) ";
            else
                query = "SELECT CB.CUENTA_BAN_ID, CB.BANCO_ID, B.NOMBRE, CB.NUM_CUENTA FROM CUENTAS_BANCARIAS CB " +
                "INNER JOIN BANCOS B ON B.BANCO_ID = CB.BANCO_ID ";
            
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CuentaBancaria cuentaBancaria = new CuentaBancaria();
                cuentaBancaria.setCuentaBancariaId(resultSet.getInt("CUENTA_BAN_ID"));
                cuentaBancaria.setBancoId(resultSet.getInt("BANCO_ID"));
                cuentaBancaria.setNombreBanco(resultSet.getString("NOMBRE"));
                cuentaBancaria.setNumeroCuenta(resultSet.getString("NUM_CUENTA"));
                listaCuentasBancarias.add(cuentaBancaria);
            }
            
            return gson.toJson(listaCuentasBancarias);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    public int cuantasCuentasBancariasFiltro() throws SQLException {
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT COUNT(*) AS CUANTAS FROM CONFIGURACION_CUENTAS_BANCARIAS"
            );
            ResultSet resultSet = preparedStatement.executeQuery();

            int cuantasCuentasFiltro = 0;
            while (resultSet.next()) {
                CuentaBancaria cuentaBancaria = new CuentaBancaria();
                cuantasCuentasFiltro = resultSet.getInt("CUANTAS");
            }
            
            return cuantasCuentasFiltro;
        } catch (SQLException exception) {
            Resources.logger.error("==================" + exception.getMessage());
            return 0;
        }
    }
    
    public String cobradoresSucursales() throws SQLException {
        try {
            List<CobradorSucursal> listaCobradorSucursal = new ArrayList<CobradorSucursal>();
                     
            PreparedStatement preparedStatement = conexion.prepareStatement(                    
                    "SELECT COBRADOR_ID, SUCURSAL_ID FROM COBRADORES_SUCURSALES"
            );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CobradorSucursal cobradorSucursal = new CobradorSucursal();
                cobradorSucursal.setCobradorId(resultSet.getInt("COBRADOR_ID"));
                cobradorSucursal.setSucursalId(resultSet.getInt("SUCURSAL_ID"));
                listaCobradorSucursal.add(cobradorSucursal);
            }
            
            return gson.toJson(listaCobradorSucursal);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    public ResponseRequest motivosVisitas() throws SQLException {                      
        ResponseRequest responseRequest = new ResponseRequest();      
        
        ArrayList<Motivo> listaMotivos = new ArrayList<Motivo>();        
        try {       
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT ID, MOTIVO FROM MOTIVOS"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Motivo motivo = new Motivo();
                motivo.setId(resultSet.getInt("ID"));
                motivo.setMotivo(resultSet.getString("MOTIVO"));
                listaMotivos.add(motivo);
            }
            
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaMotivos, "Motivos consultados correctamente");            
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en Motivos: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar Motivos " + exception.getMessage());
        }
    }
    
    /**************************************************************************/
    
    public ResponseRequest monedas() throws SQLException {        
        Resources.logger.info("monedas");        
        ResponseRequest responseRequest = new ResponseRequest();      
        
        MonedaHistoriaCambiaria monedaHistoriaCambiaria = new MonedaHistoriaCambiaria();
        Utilerias utilerias = new Utilerias();  
        try {       
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT MONEDA_ID, NOMBRE, TEXTO_IMPTE_LETRA, SIMBOLO, CLAVE_FISCAL, DECIMALES_SOPORTADOS FROM MONEDAS"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Moneda> listaMonedas = new ArrayList<Moneda>();
            while (resultSet.next()) {
                Moneda moneda = new Moneda();
                moneda.setMonedaId(resultSet.getInt("MONEDA_ID"));
                moneda.setNombre(resultSet.getString("NOMBRE"));
                moneda.setTextoImporteNombre(resultSet.getString("TEXTO_IMPTE_LETRA"));
                moneda.setSimbolo(resultSet.getString("SIMBOLO"));
                moneda.setClaveFiscal(resultSet.getString("CLAVE_FISCAL"));
                moneda.setDecimalesSoportados(resultSet.getInt("DECIMALES_SOPORTADOS"));
                listaMonedas.add(moneda);
            }
            
            
            preparedStatement = conexion.prepareStatement(
                "SELECT MONEDA_ID, TIPO_CAMBIO_COBROS, TIPO_CAMBIO FROM HISTORIA_CAMBIARIA WHERE FECHA = CURRENT_DATE"
            );
            ArrayList<HistoriaCambiaria> listaHistoriasCambiarias = new ArrayList<HistoriaCambiaria>();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                HistoriaCambiaria historiaCambiaria = new HistoriaCambiaria();
                historiaCambiaria.setMonedaId(resultSet.getInt("MONEDA_ID"));
                historiaCambiaria.setTipoCambioCobros(resultSet.getDouble("TIPO_CAMBIO_COBROS"));
                historiaCambiaria.setTipoCambio(resultSet.getDouble("TIPO_CAMBIO"));
                listaHistoriasCambiarias.add(historiaCambiaria);
            }
            
            monedaHistoriaCambiaria.setMonedas(listaMonedas);
            monedaHistoriaCambiaria.setHistoriasCambiarias(listaHistoriasCambiarias);
            return responseRequest.response(ResponseRequest.DataStatus.OK, monedaHistoriaCambiaria, "Monedas consultadas correctamente");            
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en la cabecera Monedas: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error consultar Monedas " + exception.getMessage());
        }
    }
    
    /**************************************************************************/
    
    public String articulosRefactor() {        
        try {            
            List<ArticuloRefactor> listaArticulos = new ArrayList<ArticuloRefactor>();
            configuracionMicrosip();
            String queryArticulos = "";
            if (configuracionMobil.getSincArtsCondicionados() == 1) {
                queryArticulos = "SELECT ARTICULO_ID, NOMBRE_ARTICULO, CODIGO_ARTICULO, CLAVE_ARTICULO, ES_JUEGO, FACTOR_VENTA, UNIDAD_VENTA, PORCENTAJE_IVA, PORCENTAJE_IEPS, IMPUESTO_USAR, " +
                    "PRECIO, PRECIO_NETO, MONEDA_ID, UNIDAD_MINIMA_VENTA, PIEZAS_X_INNER," +
                    "PIEZAS_X_MASTER, CODIGO_BARRAS, CODIGO_BARRAS_INNER, CODIGO_BARRAS_MASTER, " +
                    "TIENE_DESCUENTO_PROMOCION, DESCUENTO_PROMOCION, ES_EXCLUSIVO_PROMOCION, " +
                    //"TIENE_DESCUENTO_VOLUMEN, DSCTO_VOL_APL_VOLUMEN, DESCUENTO_VOLUMEN, UNIDADES, ES_EXCLUSIVO_VOLUMEN " +
                    "TIENE_DESCUENTO_VOLUMEN, 'N' AS ES_POP " +
                    "FROM POLS_ARTS_PROMO_VOL_COND_AH(?, ?, ?)";
            } else if (configuracionMobil.getOperaPop()== 1) {
                queryArticulos = "SELECT ARTICULO_ID, NOMBRE_ARTICULO, CODIGO_ARTICULO, CLAVE_ARTICULO, ES_JUEGO, FACTOR_VENTA, UNIDAD_VENTA, PORCENTAJE_IVA, PORCENTAJE_IEPS, IMPUESTO_USAR, " +
                    "PRECIO, PRECIO_NETO, MONEDA_ID, UNIDAD_MINIMA_VENTA, PIEZAS_X_INNER," +
                    "PIEZAS_X_MASTER, CODIGO_BARRAS, CODIGO_BARRAS_INNER, CODIGO_BARRAS_MASTER, " +
                    "TIENE_DESCUENTO_PROMOCION, DESCUENTO_PROMOCION, ES_EXCLUSIVO_PROMOCION, " +
                    //"TIENE_DESCUENTO_VOLUMEN, DSCTO_VOL_APL_VOLUMEN, DESCUENTO_VOLUMEN, UNIDADES, ES_EXCLUSIVO_VOLUMEN " +
                    "TIENE_DESCUENTO_VOLUMEN, ES_POP " +
                    "FROM POLS_ARTS_PROMO_VOL_POP_AH(?, ?, ?)";
            } else {
                queryArticulos = "SELECT ARTICULO_ID, NOMBRE_ARTICULO, CODIGO_ARTICULO, CLAVE_ARTICULO, ES_JUEGO, FACTOR_VENTA, UNIDAD_VENTA, PORCENTAJE_IVA, PORCENTAJE_IEPS, IMPUESTO_USAR, " +
                    "PRECIO, PRECIO_NETO, MONEDA_ID, UNIDAD_MINIMA_VENTA, PIEZAS_X_INNER," +
                    "PIEZAS_X_MASTER, CODIGO_BARRAS, CODIGO_BARRAS_INNER, CODIGO_BARRAS_MASTER, " +
                    "TIENE_DESCUENTO_PROMOCION, DESCUENTO_PROMOCION, ES_EXCLUSIVO_PROMOCION, " +
                    //"TIENE_DESCUENTO_VOLUMEN, DSCTO_VOL_APL_VOLUMEN, DESCUENTO_VOLUMEN, UNIDADES, ES_EXCLUSIVO_VOLUMEN " +
                    "TIENE_DESCUENTO_VOLUMEN, 'N' AS ES_POP " +
                    "FROM POLITICAS_ARTS_PROMO_VOL_AH(?, ?, ?)";
            }
            /*PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT ARTICULO_ID, NOMBRE_ARTICULO, CODIGO_ARTICULO, CLAVE_ARTICULO, ES_JUEGO, FACTOR_VENTA, UNIDAD_VENTA, PORCENTAJE_IVA, PORCENTAJE_IEPS, IMPUESTO_USAR, " +
                    "PRECIO, PRECIO_NETO, UNIDAD_MINIMA_VENTA, PIEZAS_X_INNER," +
                    "PIEZAS_X_MASTER, CODIGO_BARRAS, CODIGO_BARRAS_INNER, CODIGO_BARRAS_MASTER, " +
                    "TIENE_DESCUENTO_PROMOCION, DESCUENTO_PROMOCION, ES_EXCLUSIVO_PROMOCION, " +
                    //"TIENE_DESCUENTO_VOLUMEN, DSCTO_VOL_APL_VOLUMEN, DESCUENTO_VOLUMEN, UNIDADES, ES_EXCLUSIVO_VOLUMEN " +
                    "TIENE_DESCUENTO_VOLUMEN " +
                    "FROM POLITICAS_ARTS_PROMO_VOL_AH(?, ?, ?)"
            );*/
            PreparedStatement preparedStatement = conexion.prepareStatement(
                queryArticulos
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
                    
            preparedStatement.setInt(1, configuracionMobil.getPrecioEmpresaId());
            preparedStatement.setDate(2, convierteStringAFecha(fechaInicioMes));
            preparedStatement.setDate(3, convierteStringAFecha(fechaFinMes));
            ResultSet resultSet = preparedStatement.executeQuery();

            String nombreArticulo;
            while (resultSet.next()) {
                ArticuloRefactor articulo = new ArticuloRefactor();
                articulo.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                nombreArticulo = resultSet.getString("NOMBRE_ARTICULO");
                //nombreArticulo = escapeCharacters(nombreArticulo.trim());                    
                articulo.setNombreArticulo(nombreArticulo);
                String codigoArticulo = resultSet.getString("CODIGO_ARTICULO");
                codigoArticulo = codigoArticulo.replace("'", "\""); 
                codigoArticulo = escapeCharacters(codigoArticulo.trim());                    
                articulo.setCodigoArticulo(codigoArticulo);
                String claveArticulo = resultSet.getString("CLAVE_ARTICULO");
                claveArticulo = claveArticulo.replace("'", "\""); 
                claveArticulo = escapeCharacters(claveArticulo.trim());                                    
                articulo.setClaveArticulo(claveArticulo); 
                articulo.setEsJuego(resultSet.getString("ES_JUEGO"));                 
                articulo.setFactorVenta(resultSet.getDouble("FACTOR_VENTA"));
                articulo.setUnidadVenta(resultSet.getString("UNIDAD_VENTA") == null ? "NA" : resultSet.getString("UNIDAD_VENTA"));
                articulo.setPorcentajeIva(resultSet.getDouble("PORCENTAJE_IVA"));
                articulo.setPorcentajeIeps(resultSet.getDouble("PORCENTAJE_IEPS"));
                articulo.setImpuestoUsar(resultSet.getString("IMPUESTO_USAR"));
                articulo.setPrecio(resultSet.getDouble("PRECIO"));                
                articulo.setPrecioNeto(resultSet.getDouble("PRECIO_NETO"));                
                articulo.setUnidadMinimaVenta(resultSet.getInt("UNIDAD_MINIMA_VENTA"));
                articulo.setPiezasXInner(resultSet.getInt("PIEZAS_X_INNER"));
                articulo.setPiezasXMaster(resultSet.getInt("PIEZAS_X_MASTER"));
                articulo.setCodigoBarras(resultSet.getString("CODIGO_BARRAS").trim());                
                articulo.setCodigoBarrasInner(resultSet.getString("CODIGO_BARRAS_INNER").trim());
                articulo.setCodigoBarrasMaster(resultSet.getString("CODIGO_BARRAS_MASTER").trim());                
                articulo.setTieneDescuentoPromocion(resultSet.getBoolean("TIENE_DESCUENTO_PROMOCION"));
                articulo.setDescuentoPromocion(resultSet.getDouble("DESCUENTO_PROMOCION"));
                articulo.setEsExclusivoPromocion(resultSet.getString("ES_EXCLUSIVO_PROMOCION"));               
                articulo.setTieneDescuentoVolumen(resultSet.getBoolean("TIENE_DESCUENTO_VOLUMEN"));
                articulo.setMonedaId(resultSet.getInt("MONEDA_ID"));
                articulo.setEsPop(resultSet.getString("ES_POP"));
                /*articulo.setDescuentoVolumenAplicadoVolumen(resultSet.getBoolean("DSCTO_VOL_APL_VOLUMEN"));
                articulo.setDescuentoVolumen(resultSet.getDouble("DESCUENTO_VOLUMEN"));
                articulo.setUnidades(resultSet.getInt("UNIDADES"));
                articulo.setEsExclusivoVolumen(resultSet.getString("ES_EXCLUSIVO_VOLUMEN"));*/
                
                listaArticulos.add(articulo);
            }
            //System.out.println(gson.toJson(listaArticulos));
            return gson.toJson(listaArticulos);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
        
    public ResponseRequest configuracionPrecios() {        
        ResponseRequest responseRequest = new ResponseRequest();      
        try {                
            List<ConfiguracionPrecio> listaConfiguracionPrecios = new ArrayList<ConfiguracionPrecio>();
            configuracionMicrosip();
            String queryArticulos = "";
            
            queryArticulos = 
                "SELECT PRECIO_EMPRESA_ID FROM CONFIGURACION_PRECIOS";
            PreparedStatement preparedStatement = conexion.prepareStatement(
                queryArticulos
            );                                            
            ResultSet resultSet = preparedStatement.executeQuery();            
            while (resultSet.next()) {
                ConfiguracionPrecio configuracionPrecio = new ConfiguracionPrecio();
                configuracionPrecio.setPrecioEmpresaId(resultSet.getInt("PRECIO_EMPRESA_ID"));
                                
                listaConfiguracionPrecios.add(configuracionPrecio);
            }
     
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaConfiguracionPrecios, "Configuracion precios consultados correctamente");            
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en configuracionPrecios: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar configuracionPrecios " + exception.getMessage());
        }
    }
    
    public ResponseRequest articulosMultiPrecios(int precioEmpresaId) {        
        ResponseRequest responseRequest = new ResponseRequest();      
        try {            
            List<ArticuloRefactor> listaArticulos = new ArrayList<ArticuloRefactor>();
            configuracionMicrosip();
            String queryArticulos = "";
            
            queryArticulos = 
                "SELECT ARTICULO_ID, NOMBRE_ARTICULO, CODIGO_ARTICULO, CLAVE_ARTICULO, ES_JUEGO, " +
                "FACTOR_VENTA, UNIDAD_VENTA, PORCENTAJE_IVA, PORCENTAJE_IEPS, IMPUESTO_USAR, " +
                "PRECIO, PRECIO_NETO, PRECIO_EMPRESA_ID, MONEDA_ID, UNIDAD_MINIMA_VENTA, PIEZAS_X_INNER," +
                "PIEZAS_X_MASTER, ES_POP " +
                "FROM ARTICULOS_PRECIOS(?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(
                queryArticulos
            );
                                
            preparedStatement.setInt(1, precioEmpresaId);
            ResultSet resultSet = preparedStatement.executeQuery();

            String nombreArticulo;
            while (resultSet.next()) {
                ArticuloRefactor articulo = new ArticuloRefactor();
                articulo.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                nombreArticulo = resultSet.getString("NOMBRE_ARTICULO");                
                articulo.setNombreArticulo(nombreArticulo);
                /*String codigoArticulo = resultSet.getString("CODIGO_ARTICULO");
                codigoArticulo = codigoArticulo.replace("'", "\""); 
                codigoArticulo = escapeCharacters(codigoArticulo.trim()); */                   
                articulo.setCodigoArticulo(resultSet.getString("CODIGO_ARTICULO"));
                /*String claveArticulo = resultSet.getString("CLAVE_ARTICULO");
                claveArticulo = claveArticulo.replace("'", "\""); 
                claveArticulo = escapeCharacters(claveArticulo.trim());  */                                  
                articulo.setClaveArticulo(resultSet.getString("CLAVE_ARTICULO")); 
                articulo.setEsJuego(resultSet.getString("ES_JUEGO"));                 
                articulo.setFactorVenta(resultSet.getDouble("FACTOR_VENTA"));
                articulo.setUnidadVenta(resultSet.getString("UNIDAD_VENTA") == null ? "NA" : resultSet.getString("UNIDAD_VENTA"));
                articulo.setPorcentajeIva(resultSet.getDouble("PORCENTAJE_IVA"));
                articulo.setPorcentajeIeps(resultSet.getDouble("PORCENTAJE_IEPS"));
                articulo.setImpuestoUsar(resultSet.getString("IMPUESTO_USAR"));
                articulo.setPrecio(resultSet.getDouble("PRECIO"));                
                articulo.setPrecioNeto(resultSet.getDouble("PRECIO_NETO"));                
                articulo.setPrecioEmpresaId(resultSet.getInt("PRECIO_EMPRESA_ID"));
                articulo.setMonedaId(resultSet.getInt("MONEDA_ID"));
                articulo.setUnidadMinimaVenta(resultSet.getInt("UNIDAD_MINIMA_VENTA"));
                articulo.setPiezasXInner(resultSet.getInt("PIEZAS_X_INNER"));
                articulo.setPiezasXMaster(resultSet.getInt("PIEZAS_X_MASTER"));
                articulo.setEsPop(resultSet.getString("ES_POP"));
                listaArticulos.add(articulo);
            }
     
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaArticulos, "Artículos consultados correctamente");            
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en articulosMultiPrecios: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar artículos " + exception.getMessage());
        }
    }
    
    public ResponseRequest clientesPoliticas(int vendedorId) {        
        ResponseRequest responseRequest = new ResponseRequest();      
        try {            
            List<PoliticaCliente> listaPoliticasClientes = new ArrayList<PoliticaCliente>();
            configuracionMicrosip();
            String queryArticulos = "";
            
            queryArticulos = 
                "SELECT A.CLIENTE_ID, A.CLAVE_CLIENTE, A.POLITICA_PRECIOS_CLI_ID, A.NOMBRE_POLITICA, " +
                "A.POLITICA_DSCTO_ART_CLI_ID, A.PRECIO_EMPRESA_ID, A.ORIGEN, B.NOMBRE " +
                "FROM POLITICAS_CLIENTES(?) A " +
                "INNER JOIN PRECIOS_EMPRESA B ON A.PRECIO_EMPRESA_ID = B.PRECIO_EMPRESA_ID ";
            PreparedStatement preparedStatement = conexion.prepareStatement(
                queryArticulos
            );
                                
            preparedStatement.setInt(1, vendedorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            String nombreArticulo;
            while (resultSet.next()) {
                PoliticaCliente politicaCliente = new PoliticaCliente();
                politicaCliente.setClienteId(resultSet.getInt("CLIENTE_ID"));               
                politicaCliente.setClaveCliente(resultSet.getString("CLAVE_CLIENTE"));                                                                          
                politicaCliente.setPoliticaPreciosClienteId(resultSet.getInt("POLITICA_PRECIOS_CLI_ID"));
                politicaCliente.setNombrePolitica(resultSet.getString("NOMBRE_POLITICA"));
                politicaCliente.setPoliticaDescuentoArticuloClienteId(resultSet.getInt("POLITICA_DSCTO_ART_CLI_ID"));                
                politicaCliente.setPrecioEmpresaId(resultSet.getInt("PRECIO_EMPRESA_ID"));
                politicaCliente.setOrigen(resultSet.getString("ORIGEN"));                
                politicaCliente.setNombrePrecio(resultSet.getString("NOMBRE"));
                               
                listaPoliticasClientes.add(politicaCliente);
            }
     
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaPoliticasClientes, "Politicas clientes consultados correctamente");            
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en clientesPoliticas: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar Politicas clientes " + exception.getMessage());
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
    
    /**************************************************************************/
    
    public ResponseRequest getArticulosPromVta45() throws SQLException {        
        Resources.logger.info("getArticulosPromVta45");        
        ResponseRequest responseRequest = new ResponseRequest();      
        
        ArrayList<ArticuloPromedioVenta45> listaArticuloPromedioVenta45 = new ArrayList<ArticuloPromedioVenta45>();
        Utilerias utilerias = new Utilerias();  
        try {       
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT ARTICULO_ID, CODIGO_ARTICULO, PROMEDIO_VENTA FROM ARTS_PROM_VTA_45"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ArticuloPromedioVenta45 articuloPromedioVenta45 = new ArticuloPromedioVenta45();
                articuloPromedioVenta45.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                articuloPromedioVenta45.setCodigoArticulo(resultSet.getString("CODIGO_ARTICULO"));
                articuloPromedioVenta45.setPromedioVenta(resultSet.getDouble("PROMEDIO_VENTA"));
                listaArticuloPromedioVenta45.add(articuloPromedioVenta45);
            }
            
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaArticuloPromedioVenta45, "Visitas a clientes grabadas correctamente");            
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en la cabecera createVisitasClientes: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al crear visitas - clientes " + exception.getMessage());
        }
    }
    
    public ResponseRequest depurarRutas() throws SQLException {        
        Resources.logger.info("Entrando a depurarRutas");        
        ResponseRequest responseRequest = new ResponseRequest();      
        
        ArrayList<RutaDepurada> listaRutaDepurada = new ArrayList<RutaDepurada>();
        Utilerias utilerias = new Utilerias();  
        try {       
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT ID_DOCTO_RUTA, FOLIO_DOCTO_RUTA, ID_DOCTO_CXC, FOLIO_DOCTO_CXC, DOCTO_CC_ID, FECHA_VENCIMIENTO, "  +
                "CONCEPTO_CC_ID, FOLIO, ATRASO, IMPORTE_CARGO, SALDO_CARGO, ESTADO FROM DEPURA_RUTAS"
            );             
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RutaDepurada rutaDepurada = new RutaDepurada();
                rutaDepurada.setIdDoctoRuta(resultSet.getInt("ID_DOCTO_RUTA"));
                rutaDepurada.setFolioDoctoRuta(resultSet.getString("FOLIO_DOCTO_RUTA"));
                rutaDepurada.setIdDoctoCXC(resultSet.getInt("ID_DOCTO_CXC"));
                rutaDepurada.setFolioDoctoCXC(resultSet.getString("FOLIO_DOCTO_CXC"));
                rutaDepurada.setDoctoCCId(resultSet.getInt("DOCTO_CC_ID"));
                rutaDepurada.setFechaVencimiento(resultSet.getString("FECHA_VENCIMIENTO"));
                rutaDepurada.setConceptoCCId(resultSet.getInt("CONCEPTO_CC_ID"));
                rutaDepurada.setFolio(resultSet.getString("FOLIO"));
                rutaDepurada.setAtraso(resultSet.getInt("ATRASO"));
                rutaDepurada.setImporteCargo(resultSet.getDouble("IMPORTE_CARGO"));
                rutaDepurada.setSaldoCargo(resultSet.getDouble("SALDO_CARGO"));
                rutaDepurada.setEstado(resultSet.getString("ESTADO"));
                listaRutaDepurada.add(rutaDepurada);
            }
            
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaRutaDepurada, "Depuración de rutas ejecutada correctamente");
            //return listaVisitaClienteGrabado;
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION al depurarRutas: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al crear visitas - clientes " + exception.getMessage());
        }
    }
    
    public ResponseRequest choferes() {    
        ResponseRequest responseRequest = new ResponseRequest();    
        try {
            List<Chofer> listaChoferes = new ArrayList<Chofer>();
            configuracionMicrosip();
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT CHOFER_ID, CLAVE, NOMBRE FROM MG_CHOFERES"
            );
                 
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Chofer chofer = new Chofer();
                chofer.setChoferId(resultSet.getInt("CHOFER_ID"));            
                chofer.setClave(resultSet.getString("CLAVE"));
                chofer.setNombre(resultSet.getString("NOMBRE"));
                
                listaChoferes.add(chofer);
            }
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaChoferes, "Choferes consultados correctamente");            
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en Choferes: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar Choferes" + exception.getMessage());
        }
    }
    
    /**************************************************************************/
    public ResponseRequest existenciaArticulos() {    
        ResponseRequest responseRequest = new ResponseRequest();    
        try {
            List<ExistenciaArticulo> listaExistenciaArticulos = new ArrayList<ExistenciaArticulo>();
            configuracionMicrosip();
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT ARTICULO_ID, EXISTENCIA, ALMACEN_ID " +
                "FROM EXISTENCIA_ARTICULOS WHERE ESTATUS IN ('A','C')"
            );
                 
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ExistenciaArticulo existenciaArticulo = new ExistenciaArticulo();
                existenciaArticulo.setArticuloId(resultSet.getInt("ARTICULO_ID"));            
                existenciaArticulo.setExistencia(resultSet.getInt("EXISTENCIA"));
                existenciaArticulo.setAlmacenId(resultSet.getInt("ALMACEN_ID"));  
                
                listaExistenciaArticulos.add(existenciaArticulo);
            }
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaExistenciaArticulos, "Existencia artículos consultados correctamente");            
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en politicasPorVolumen: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar existencia artículos " + exception.getMessage());
        }
    }
    
    public ResponseRequest existenciaArticulosRefactor() {    
        ResponseRequest responseRequest = new ResponseRequest();    
        try {
            List<ExistenciaArticulo> listaExistenciaArticulos = new ArrayList<ExistenciaArticulo>();
            configuracionMicrosip();
            int sincronizaExistenciaArticulosCondicionados = configuracionMobil.getSincExistArtsCondicionados();
            String tipoPoliticaAOperar = configuracionMobil.getTipoPoliticaAOperar();
            int precioEmpresaId = configuracionMobil.getPrecioEmpresaId();

            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT ARTICULO_ID, EXISTENCIA, NOMBRE_ALMACEN, ALMACEN_ID " +
                "FROM EXISTENCIA_ARTS_MULT_ALMS(?, ?, ?) ORDER BY ARTICULO_ID"
            );
                 
            preparedStatement.setInt(1, sincronizaExistenciaArticulosCondicionados);
            preparedStatement.setString(2, tipoPoliticaAOperar);
            preparedStatement.setInt(3, precioEmpresaId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                ExistenciaArticulo existenciaArticulo = new ExistenciaArticulo();
                existenciaArticulo.setArticuloId(resultSet.getInt("ARTICULO_ID"));            
                existenciaArticulo.setExistencia(resultSet.getInt("EXISTENCIA"));
                existenciaArticulo.setNombreAlmacen(resultSet.getString("NOMBRE_ALMACEN"));
                existenciaArticulo.setAlmacenId(resultSet.getInt("ALMACEN_ID"));
                
                listaExistenciaArticulos.add(existenciaArticulo);
            }
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaExistenciaArticulos, "Existencia artículos consultados correctamente");            
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en politicasPorVolumen: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar existencia artículos " + exception.getMessage());
        }
    }
    
    public ResponseRequest existenciaArticulos2024() {    
        ResponseRequest responseRequest = new ResponseRequest();    
        try {
            List<ExistenciaArticulo> listaExistenciaArticulos = new ArrayList<ExistenciaArticulo>();
            configuracionMicrosip();
            int sincronizaExistenciaArticulosCondicionados = configuracionMobil.getSincExistArtsCondicionados();
            String tipoPoliticaAOperar = configuracionMobil.getTipoPoliticaAOperar();
            int precioEmpresaId = configuracionMobil.getPrecioEmpresaId();

            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT ARTICULO_ID, EXISTENCIA, NOMBRE_ALMACEN " +
                "FROM EXISTENCIA_ARTS_MULT_ALMS(?, ?, ?) ORDER BY ARTICULO_ID"
            );
                 
            preparedStatement.setInt(1, sincronizaExistenciaArticulosCondicionados);
            preparedStatement.setString(2, tipoPoliticaAOperar);
            preparedStatement.setInt(3, precioEmpresaId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            int existenciaTotal = 0;
            String  existenciaAlmacen = "";
            while (resultSet.next()) {
                int articuloId = resultSet.getInt("ARTICULO_ID");
                ExistenciaArticulo existenciaArticulo = new ExistenciaArticulo();
                
                ExistenciaArticulo existenciaArticuloEncontrado = listaExistenciaArticulos.stream()                        
                        .filter(p -> p.getArticuloId() == articuloId)
                        .findAny()
                        .orElse(null);
                if (existenciaArticuloEncontrado != null) {
                    existenciaArticulo.setArticuloId(resultSet.getInt("ARTICULO_ID"));            
                    existenciaTotal = existenciaTotal + resultSet.getInt("EXISTENCIA");
                    existenciaArticulo.setExistencia(existenciaTotal);
                    //existenciaArticulo.setNombreAlmacen(resultSet.getString("NOMBRE_ALMACEN"));
                    existenciaAlmacen = existenciaAlmacen + ", " + resultSet.getString("NOMBRE_ALMACEN") + " = " + resultSet.getInt("EXISTENCIA");
                    existenciaArticulo.setNombreAlmacen(existenciaAlmacen);
                    //Eliminamos el nodo encontrado de la lista e insertamos el nuevo
                    //int index = listaExistenciaArticulos.indexOf(existenciaArticuloEncontrado);
                    listaExistenciaArticulos.remove(existenciaArticuloEncontrado);
                } else if (existenciaArticuloEncontrado == null) {                    
                    existenciaArticulo.setArticuloId(resultSet.getInt("ARTICULO_ID"));            
                    existenciaArticulo.setExistencia(resultSet.getInt("EXISTENCIA"));
                    //existenciaArticulo.setNombreAlmacen(resultSet.getString("NOMBRE_ALMACEN"));
                    existenciaArticulo.setNombreAlmacen(resultSet.getString("NOMBRE_ALMACEN") + " = " + resultSet.getInt("EXISTENCIA"));
                }
                
                listaExistenciaArticulos.add(existenciaArticulo);
            }
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaExistenciaArticulos, "Existencia artículos consultados correctamente");            
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en politicasPorVolumen: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar existencia artículos " + exception.getMessage());
        }
    }
    /**************************************************************************/
    public String paginarPoliticaDescuentoArticuloCliente() throws SQLException {
        // 1. OBTENER LA FECHA ACTUAL DE FORMA LIMPIA (Sin horas, minutos ni segundos)
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date fechaActual = calendar.getTime();

        System.out.println("INICIANDO");
        Date fechaValidacion = null;

        // 2. PRIMERA CONSULTA: OBTENER FECHA DE VALIDACIÓN (Aislada en su propio bloque try)
        String queryValidacion = "SELECT DISTINCT(FECHA) FROM POLITICAS_DESC_ART_CLI_AH";
        try (PreparedStatement stmtValidar = conexion.prepareStatement(queryValidacion);
             ResultSet rsValidar = stmtValidar.executeQuery()) {

            if (rsValidar.next()) {
                fechaValidacion = rsValidar.getDate("FECHA");
            }
        } // Aquí se cierran de forma segura stmtValidar y rsValidar

        // Variables para el control de flujo posterior
        boolean ejecutarProcedimiento = false;

        // 3. COMPARACIÓN LOGICA DE FECHAS
        if (fechaValidacion != null) {  
            System.out.println("Si hay datos");
            if (fechaActual.compareTo(fechaValidacion) != 0) {
                eliminaPoliticasDescuentosArticulos();
                ejecutarProcedimiento = true;
            }
        } else {
            System.out.println("No hay datos");
            ejecutarProcedimiento = true;
        }

        // 4. SEGUNDA CONSULTA: EJECUCIÓN SEGÚN EL FLUJO
        ProcesaPoliticas procesaPoliticas = new ProcesaPoliticas();

        try {
            if (ejecutarProcedimiento) {
                // Nota: Para procedimientos que devuelven valores en Firebird se prefiere CALL o SELECT
                String proc = "EXECUTE PROCEDURE POLITICAS_ARTICULOS_AH(CURRENT_DATE)";
                try (PreparedStatement stmtProc = conexion.prepareStatement(proc);
                     ResultSet rsProc = stmtProc.executeQuery()) {
                    if (rsProc.next()) {
                        procesaPoliticas.setCuantasPoliticas(rsProc.getInt(1)); // O "CUANTAS_POLITICAS"
                    }
                }
            } else {
                String countQuery = "SELECT COUNT(ID) AS CUANTAS_POLITICAS FROM POLITICAS_DESC_ART_CLI_AH";
                try (PreparedStatement stmtCount = conexion.prepareStatement(countQuery);
                     ResultSet rsCount = stmtCount.executeQuery()) {
                    if (rsCount.next()) {
                        procesaPoliticas.setCuantasPoliticas(rsCount.getInt("CUANTAS_POLITICAS"));
                    }
                }
            }

            return gson.toJson(procesaPoliticas);

        } catch (SQLException exception) {
            System.out.println("SQLException en bloque de ejecución: " + exception.getMessage());           
            return null;
        } catch (Exception exception) {
            System.out.println("Exception general: " + exception.getMessage());           
            return null;
        }
    }
    /*public String paginarPoliticaDescuentoArticuloCliente() throws SQLException{
        try {  
            //PRIMERO OBTENER LA HORA ACTUAL
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdformat = new SimpleDateFormat("MM/dd/yyyy");               
            Date fechaActual = calendar.getTime();
            String stringFechaActual= sdformat.format(fechaActual);
            try {
                fechaActual = sdformat.parse(stringFechaActual);
            } catch (ParseException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("INICIANDO");
            //SE CONSULTA LA FECHA EN LA TABLA DE POLITICAS
            Date fechaValidacion = null;
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT DISTINCT(FECHA) FROM POLITICAS_DESC_ART_CLI_AH "
            );            
            ResultSet resultSetValidacion = preparedStatement.executeQuery(); 
            // Si la tabla no tiene datos, .next() es false y no entra al bloque if
            if (resultSetValidacion.next()) {
                fechaValidacion = resultSetValidacion.getDate("FECHA");
            }
            
            ResultSet resultSet = null;
            
            //COMPARAMOS LAS DOS 
            if (fechaValidacion != null){  
                System.out.println("Si hay datos");
                if ((fechaActual.compareTo(fechaValidacion) != 0)){
                    eliminaPoliticasDescuentosArticulos();
                    
                    //preparedStatement = conexion.prepareStatement(
                    //    "SELECT CUANTAS_POLITICAS FROM POLITICAS_ARTICULOS_AH(CURRENT_DATE)"
                    //);
                    preparedStatement = conexion.prepareStatement(
                        "EXECUTE PROCEDURE POLITICAS_ARTICULOS_AH(CURRENT_DATE)"
                    );
                    //preparedStatement.setDate(1, convierteStringAFecha(stringFechaActual));
                } else
                    preparedStatement = conexion.prepareStatement(
                        "SELECT COUNT(ID) AS CUANTAS_POLITICAS FROM POLITICAS_DESC_ART_CLI_AH"
                    );                
            } else {
                System.out.println("No hay datos");
                //eliminaPoliticasDescuentosArticulos();
                
               //preparedStatement = conexion.prepareStatement(
               //         "SELECT CUANTAS_POLITICAS FROM POLITICAS_ARTICULOS_AH(CURRENT_DATE)"
                //    );                              
                preparedStatement = conexion.prepareStatement(
                    "EXECUTE PROCEDURE POLITICAS_ARTICULOS_AH(CURRENT_DATE)"
                );
                
                //preparedStatement.setDate(1, convierteStringAFecha(stringFechaActual));
            }
            
            resultSet = preparedStatement.executeQuery();
            ProcesaPoliticas procesaPoliticas = null;
            if (resultSetValidacion.next()) {
                procesaPoliticas = new ProcesaPoliticas();
                procesaPoliticas.setCuantasPoliticas(resultSet.getInt("CUANTAS_POLITICAS"));
            }

            return gson.toJson(procesaPoliticas);
        } catch (SQLException exception) {
            System.out.println("SQLException: " + exception.getMessage());           
            return null;
        } catch (Exception exception) {
            System.out.println("Exception:" + exception.getMessage());           
            return null;
        } 
        
    }*/
    
    public String politicaDescuentoArticuloClienteRefactor(int pagina) throws SQLException{
        try {              
            List<PoliticaDescuentoArticuloClienteRefactor> listaPoliticaDescuentoArticuloCliente = new ArrayList<PoliticaDescuentoArticuloClienteRefactor>();
          
            PreparedStatement preparedStatement = conexion.prepareStatement(
               "SELECT POLITICA_DSCTO_ART_CLI_ID, NOMBRE_POLITICA, ARTICULO_ID, DESCUENTO, ES_EXCLUSIVO FROM POLITICAS_DESC_ART_CLI_AH " +
               "WHERE PAGINA = ?"
            );
            preparedStatement.setInt(1, pagina);  
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PoliticaDescuentoArticuloClienteRefactor politicaDescuentoArticuloCliente = new PoliticaDescuentoArticuloClienteRefactor();
                politicaDescuentoArticuloCliente.setPoliticaDescuentoArticuloClienteId(resultSet.getInt("POLITICA_DSCTO_ART_CLI_ID"));
                politicaDescuentoArticuloCliente.setNombrePolitica(resultSet.getString("NOMBRE_POLITICA"));
                politicaDescuentoArticuloCliente.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                politicaDescuentoArticuloCliente.setDescuento(resultSet.getDouble("DESCUENTO"));
                politicaDescuentoArticuloCliente.setEsExclusivo(resultSet.getString("ES_EXCLUSIVO") == null ? "N" : resultSet.getString("ES_EXCLUSIVO"));                
                listaPoliticaDescuentoArticuloCliente.add(politicaDescuentoArticuloCliente);
            }
            
            return gson.toJson(listaPoliticaDescuentoArticuloCliente);
        } catch (SQLException exception) {
            System.out.println("politicaDescuentoArticuloClienteRefactor==================" + exception.getMessage());           
            return null;
        } 
    }
    
    public Boolean eliminaPoliticasDescuentosArticulos() {
        try {
            PreparedStatement preparedStatementObj = conexion.prepareStatement(
                "DELETE FROM POLITICAS_DESC_ART_CLI_AH"
            );
            preparedStatementObj.executeUpdate();       
            return true;
        } catch (SQLException exception) {
            return false;
        }        
    }  
    
    /**************************************************************************/
    
    public ResponseRequest politicasPorVolumen() throws SQLException {                      
        ResponseRequest responseRequest = new ResponseRequest();      
        
        ArrayList<PoliticaXVolumen> listaPoliticasXVolumen = new ArrayList<PoliticaXVolumen>();
        Utilerias utilerias = new Utilerias();  
        try {     
            configuracionMicrosip();
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT POLITICA_ID, NOMBRE_POLITICA, ARTICULO_ID, UNIDADES, DESCUENTO_VOLUMEN, ES_EXCLUSIVO_VOLUMEN FROM POLS_DSCTOS_ARTS_VOLUMENES (?, ?)"
            );
            preparedStatement.setInt(1, configuracionMobil.getPrecioEmpresaId());
            preparedStatement.setInt(2, 0);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PoliticaXVolumen politicaXVolumen = new PoliticaXVolumen();
                politicaXVolumen.setPoliticaId(resultSet.getInt("POLITICA_ID"));
                politicaXVolumen.setNombrePolitica("");
                //politicaXVolumen.setNombrePolitica(resultSet.getString("NOMBRE_POLITICA"));
                politicaXVolumen.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                politicaXVolumen.setUnidades(resultSet.getInt("UNIDADES"));
                politicaXVolumen.setDescuentoVolumen(resultSet.getDouble("DESCUENTO_VOLUMEN"));
                politicaXVolumen.setEsExclusivo(resultSet.getString("ES_EXCLUSIVO_VOLUMEN") == null ? "N" : resultSet.getString("ES_EXCLUSIVO_VOLUMEN"));  
                listaPoliticasXVolumen.add(politicaXVolumen);
            }
            
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaPoliticasXVolumen, "Politicas por volumen consultados correctamente");            
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en politicasPorVolumen: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar politicasPorVolumen " + exception.getMessage());
        }
    }
    
    public ResponseRequest politicasPorVolumenMultiprecios(int precioEmpresaId) throws SQLException {                      
        ResponseRequest responseRequest = new ResponseRequest();      
        
        ArrayList<PoliticaXVolumen> listaPoliticasXVolumen = new ArrayList<PoliticaXVolumen>();
        Utilerias utilerias = new Utilerias();  
        try {     
            configuracionMicrosip();
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT POLITICA_ID, NOMBRE_POLITICA, ARTICULO_ID, UNIDADES, DESCUENTO_VOLUMEN, ES_EXCLUSIVO_VOLUMEN, PRECIO_EMPRESA_ID FROM POLS_DSCTOS_ARTS_VOLS_M (?, ?)"
            );
            preparedStatement.setInt(1, precioEmpresaId);
            preparedStatement.setInt(2, 0);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PoliticaXVolumen politicaXVolumen = new PoliticaXVolumen();
                politicaXVolumen.setPoliticaId(resultSet.getInt("POLITICA_ID"));
                politicaXVolumen.setNombrePolitica("");
                //politicaXVolumen.setNombrePolitica(resultSet.getString("NOMBRE_POLITICA"));
                politicaXVolumen.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                politicaXVolumen.setUnidades(resultSet.getInt("UNIDADES"));
                politicaXVolumen.setDescuentoVolumen(resultSet.getDouble("DESCUENTO_VOLUMEN"));
                politicaXVolumen.setEsExclusivo(resultSet.getString("ES_EXCLUSIVO_VOLUMEN") == null ? "N" : resultSet.getString("ES_EXCLUSIVO_VOLUMEN"));  
                politicaXVolumen.setPrecioEmpresaId(resultSet.getInt("PRECIO_EMPRESA_ID"));
                listaPoliticasXVolumen.add(politicaXVolumen);
            }
            
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaPoliticasXVolumen, "Politicas por volumen consultados correctamente");            
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en politicasPorVolumen: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar politicasPorVolumen " + exception.getMessage());
        }
    }
    
    
    /**************************************************************************/
    
    public ResponseRequest serieFolioCXC(int cobradorId) throws SQLException {                      
        ResponseRequest responseRequest = new ResponseRequest();      
        
        try {     
            configuracionMicrosip();
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT ID, COBRADOR_ID, SERIE, FOLIO FROM SERIES_FOLIOS_CXC WHERE COBRADOR_ID = ?"
            );
            preparedStatement.setInt(1, cobradorId);           
            ResultSet resultSet = preparedStatement.executeQuery();
            SerieFolioCXC serieFolioCXC = new SerieFolioCXC();
            while (resultSet.next()) {

                serieFolioCXC.setId(resultSet.getInt("ID"));
                serieFolioCXC.setCobradorId(resultSet.getInt("COBRADOR_ID"));                
                serieFolioCXC.setSerie(resultSet.getString("SERIE"));
                serieFolioCXC.setFolio(resultSet.getInt("FOLIO"));                
            }
            
            return responseRequest.response(ResponseRequest.DataStatus.OK, serieFolioCXC, "Series folios consultados correctamente");            
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en politicasPorVolumen: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar politicasPorVolumen " + exception.getMessage());
        }
    }
    
    public String clientesRefactor(int vendedorId) throws SQLException {
        try {
            List<ClienteRefactor> listaCliente = new ArrayList<ClienteRefactor>();
                
            configuracionMicrosip();
                        
            String query = "";
            if (configuracionMobil.getOperaPop() == 1) {
                LocalDate fecha = LocalDate.now(); // Fecha actual
                String nombreMes = fecha.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase();
                            
                PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT NUMERO FROM TRIMESTRES WHERE NOMBRE LIKE '%" + nombreMes + "%'"
                );
                ResultSet resultSet = preparedStatement.executeQuery();
                int numeroTrimestre = 0;
                int anio = 0;
                while (resultSet.next()) {
                    numeroTrimestre = resultSet.getInt("NUMERO");
                    anio = fecha.getYear();
                    if (numeroTrimestre == 1) {
                        numeroTrimestre = 4;
                        anio = fecha.getYear() - 1;
                    } else
                        numeroTrimestre -= 1;
                }
                                           
                if ((configuracionMobil.getTipoPoliticaAOperar().equals("ARTICULO_CLIENTE")) || (configuracionMobil.getTipoPoliticaAOperar().equals("MULTIPRECIOS")))
                    query = 
                        "SELECT C.CLIENTE_ID, C.CLAVE_CLIENTE, C.NOMBRE_CLIENTE, " +
                        "C.TIPO_CLIENTE_ID, C.TIPO_CLIENTE_NOMBRE, " +
                        "C.ZONA_CLIENTE_ID, C.ZONA_CLIENTE_NOMBRE, " +
                        "C.COBRADOR_ID, " +
                        "C.COBRADOR_NOMBRE, " +
                        "C.VENDEDOR_ID, " +
                        "C.VENDEDOR_NOMBRE, " +
                        "C.DIR_CLI_ID, C.RFC_CURP, C.CALLE, C.NUM_EXTERIOR, C.NUM_INTERIOR, C.COLONIA, C.POBLACION, C.CODIGO_POSTAL, C.TELEFONO1, C.TELEFONO2, " +
                        "CPAH.POLITICA_DSCTO_ART_CLI_ID, CPAH.NOMBRE_POLITICA, C.LIMITE_CREDITO, C.ESTATUS, C.MONEDA_ID, COALESCE(PT.IMPORTE_POP_GANADO_CON_IMP, 0.00) AS IMPORTE_POP_GANADO_CON_IMP, " +
                        "COALESCE(PT.ESTATUS_POP, '') AS ESTATUS_POP, COALESCE(PT.MONTO_MINIMO_VENTA, 0.00) AS MONTO_MINIMO_VENTA " +                        
                        "FROM CLIENTES_AH C " +
                        "LEFT JOIN CLIENTES_POLITICAS_AH(?, C.CLIENTE_ID) CPAH ON 1 = 1 " + 
                        "LEFT JOIN POP_TRIMESTRAL PT ON PT.CLIENTE_ID = C.CLIENTE_ID AND PT.ANIO = " + anio + " AND PT.TRIMESTRE = " + numeroTrimestre +    
                        " WHERE C.VENDEDOR_ID = ? ";
                else
                    query = 
                        "SELECT C.CLIENTE_ID, C.CLAVE_CLIENTE, C.NOMBRE_CLIENTE, " +
                        "C.TIPO_CLIENTE_ID, C.TIPO_CLIENTE_NOMBRE, " +
                        "C.ZONA_CLIENTE_ID, C.ZONA_CLIENTE_NOMBRE, " +
                        "C.COBRADOR_ID, " +
                        "C.COBRADOR_NOMBRE, " +
                        "C.VENDEDOR_ID, " +
                        "C.VENDEDOR_NOMBRE, " +
                        "C.DIR_CLI_ID, C.RFC_CURP, C.CALLE, C.NUM_EXTERIOR, C.NUM_INTERIOR, C.COLONIA, C.POBLACION, C.CODIGO_POSTAL, C.TELEFONO1, C.TELEFONO2, " +
                        "0 AS POLITICA_DSCTO_ART_CLI_ID, '' AS NOMBRE_POLITICA, C.LIMITE_CREDITO, C.ESTATUS, C.MONEDA_ID, COALESCE(PT.IMPORTE_POP_GANADO_CON_IMP, 0.00) AS IMPORTE_POP_GANADO_CON_IMP, " +
                        "COALESCE(PT.ESTATUS_POP, '') AS ESTATUS_POP, COALESCE(PT.MONTO_MINIMO_VENTA, 0.00) AS MONTO_MINIMO_VENTA " +                        
                        "FROM CLIENTES_AH C " + 
                        "LEFT JOIN POP_TRIMESTRAL PT ON PT.CLIENTE_ID = C.CLIENTE_ID AND PT.ANIO = " + anio + " AND PT.TRIMESTRE = " + numeroTrimestre +    
                        " WHERE C.VENDEDOR_ID = ? ";
            } else {
                if ((configuracionMobil.getTipoPoliticaAOperar().equals("ARTICULO_CLIENTE")) || (configuracionMobil.getTipoPoliticaAOperar().equals("MULTIPRECIOS")))
                    query = 
                        "SELECT C.CLIENTE_ID, C.CLAVE_CLIENTE, C.NOMBRE_CLIENTE, " +
                        "C.TIPO_CLIENTE_ID, C.TIPO_CLIENTE_NOMBRE, " +
                        "C.ZONA_CLIENTE_ID, C.ZONA_CLIENTE_NOMBRE, " +
                        "C.COBRADOR_ID, " +
                        "C.COBRADOR_NOMBRE, " +
                        "C.VENDEDOR_ID, " +
                        "C.VENDEDOR_NOMBRE, " +
                        "C.DIR_CLI_ID, C.RFC_CURP, C.CALLE, C.NUM_EXTERIOR, C.NUM_INTERIOR, C.COLONIA, C.POBLACION, C.CODIGO_POSTAL, C.TELEFONO1, C.TELEFONO2, " +
                        "CPAH.POLITICA_DSCTO_ART_CLI_ID, CPAH.NOMBRE_POLITICA, C.LIMITE_CREDITO, C.ESTATUS, C.MONEDA_ID, 0.00 AS IMPORTE_POP_GANADO_CON_IMP, " +
                        "'' AS ESTATUS_POP, 0.00 AS MONTO_MINIMO_VENTA " +                        
                        "FROM CLIENTES_AH C " +
                        "LEFT JOIN CLIENTES_POLITICAS_AH(?, C.CLIENTE_ID) CPAH ON 1 = 1 " + 
                        "WHERE C.VENDEDOR_ID = ?";
                else
                    query = 
                        "SELECT C.CLIENTE_ID, C.CLAVE_CLIENTE, C.NOMBRE_CLIENTE, " +
                        "C.TIPO_CLIENTE_ID, C.TIPO_CLIENTE_NOMBRE, " +
                        "C.ZONA_CLIENTE_ID, C.ZONA_CLIENTE_NOMBRE, " +
                        "C.COBRADOR_ID, " +
                        "C.COBRADOR_NOMBRE, " +
                        "C.VENDEDOR_ID, " +
                        "C.VENDEDOR_NOMBRE, " +
                        "C.DIR_CLI_ID, C.RFC_CURP, C.CALLE, C.NUM_EXTERIOR, C.NUM_INTERIOR, C.COLONIA, C.POBLACION, C.CODIGO_POSTAL, C.TELEFONO1, C.TELEFONO2, " +
                        "0 AS POLITICA_DSCTO_ART_CLI_ID, '' AS NOMBRE_POLITICA, C.LIMITE_CREDITO, C.ESTATUS, C.MONEDA_ID, 0.00 AS IMPORTE_POP_GANADO_CON_IMP, " +
                        "'' AS ESTATUS_POP, 0.00 AS MONTO_MINIMO_VENTA " +                        
                        "FROM CLIENTES_AH C " +                         
                        "WHERE C.VENDEDOR_ID = ?";
            }
                                                        
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            if ((configuracionMobil.getTipoPoliticaAOperar().equals("ARTICULO_CLIENTE")) || (configuracionMobil.getTipoPoliticaAOperar().equals("MULTIPRECIOS"))){
                preparedStatement.setInt(1, configuracionMobil.getPrecioEmpresaId());
                preparedStatement.setInt(2, vendedorId);
            } else
                preparedStatement.setInt(1, vendedorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ClienteRefactor cliente = new ClienteRefactor();
                cliente.setClienteId(resultSet.getInt("CLIENTE_ID"));
                cliente.setClaveCliente(resultSet.getString("CLAVE_CLIENTE"));
                String nombreCliente = resultSet.getString("NOMBRE_CLIENTE");
                //nombreCliente = nombreCliente.replace("'", "\""); 
                //nombreCliente = escapeCharacters(nombreCliente);                                    
                cliente.setNombreCliente(nombreCliente.trim());
                cliente.setTipoClienteId(resultSet.getInt("TIPO_CLIENTE_ID"));
                //cliente.setTipoClienteNombre(resultSet.getString("TIPO_CLIENTE_NOMBRE"));
                cliente.setZonaClienteId(resultSet.getInt("ZONA_CLIENTE_ID"));
                //cliente.setZonaClienteNombre(resultSet.getString("ZONA_CLIENTE_NOMBRE"));
                cliente.setCobradorId(resultSet.getInt("COBRADOR_ID"));
                //cliente.setCobradorNombre(resultSet.getString("COBRADOR_NOMBRE"));
                cliente.setVendedorId(resultSet.getInt("VENDEDOR_ID"));
                //cliente.setVendedorNombre(resultSet.getString("VENDEDOR_NOMBRE"));
                cliente.setRfcCurp(resultSet.getString("RFC_CURP"));
                cliente.setDireccionClienteId(resultSet.getInt("DIR_CLI_ID"));
                //cliente.setCalle(resultSet.getString("CALLE"));
                //cliente.setNumeroExterior(resultSet.getString("NUM_EXTERIOR"));
                //cliente.setNumeroInterior(resultSet.getString("NUM_INTERIOR"));
                //cliente.setColonia(resultSet.getString("COLONIA"));
                //cliente.setPoblacion(resultSet.getString("POBLACION") != null ? resultSet.getString("POBLACION") : "SP");
                //cliente.setCodigoPostal(resultSet.getString("CODIGO_POSTAL"));
                cliente.setTelefono1(resultSet.getString("TELEFONO1") != null ? resultSet.getString("TELEFONO1") : "0000");
                //cliente.setTelefono2(resultSet.getString("TELEFONO2") != null ? resultSet.getString("TELEFONO2") : "0000");
                //REFACTOR PARA EL CASO FERREGAMA AUQNEU TENGA POLITICA EL CTE, HAY QU ELLEVARLA EN CERO YA QUE SE APLICO DESDE EL ARTICULO 
                if (configuracionMobil.getApplyPoliticaPrecioClientes() == 1)
                    cliente.setPoliticaDescuentoArticuloClienteId(0);
                else     
                    cliente.setPoliticaDescuentoArticuloClienteId(resultSet.getInt("POLITICA_DSCTO_ART_CLI_ID"));                
                //cliente.setNombrePolitica(resultSet.getString("NOMBRE_POLITICA") == null ? "SIN_POLITICA" : resultSet.getString("NOMBRE_POLITICA"));                
                
                cliente.setLimiteCredito(resultSet.getDouble("LIMITE_CREDITO"));
                cliente.setEstatus(resultSet.getString("ESTATUS"));
                cliente.setMonedaId(resultSet.getInt("MONEDA_ID"));
                cliente.setSaldoPOP(resultSet.getDouble("IMPORTE_POP_GANADO_CON_IMP"));
                cliente.setEstatusPOP(resultSet.getString("ESTATUS_POP"));
                cliente.setMontoMinimoVenta(resultSet.getDouble("MONTO_MINIMO_VENTA"));
                listaCliente.add(cliente);
            }
            
            return gson.toJson(listaCliente);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    public String clientesRefactor() throws SQLException {
        try {
            List<ClienteRefactor> listaCliente = new ArrayList<ClienteRefactor>();
                
            configuracionMicrosip();
                        
            String query = "";
            
            if (configuracionMobil.getOperaPop() == 1) {
                LocalDate fecha = LocalDate.now(); // Fecha actual
                String nombreMes = fecha.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase();
                            
                PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT NUMERO FROM TRIMESTRES WHERE NOMBRE LIKE '%" + nombreMes + "%'"
                );
                ResultSet resultSet = preparedStatement.executeQuery();
                int numeroTrimestre = 0;
                int anio = 0;
                while (resultSet.next()) {
                    numeroTrimestre = resultSet.getInt("NUMERO");
                    anio = fecha.getYear();
                    if (numeroTrimestre == 1) {
                        numeroTrimestre = 4;
                        anio = fecha.getYear() - 1;
                    } else
                        numeroTrimestre -= 1;
                }                
                if ((configuracionMobil.getTipoPoliticaAOperar().equals("ARTICULO_CLIENTE")) || (configuracionMobil.getTipoPoliticaAOperar().equals("MULTIPRECIOS")))    
                    query = 
                        "SELECT C.CLIENTE_ID, C.CLAVE_CLIENTE, C.NOMBRE_CLIENTE, " +
                        "C.TIPO_CLIENTE_ID, C.TIPO_CLIENTE_NOMBRE, " +
                        "C.ZONA_CLIENTE_ID, C.ZONA_CLIENTE_NOMBRE, " +
                        "C.COBRADOR_ID, " +
                        "C.COBRADOR_NOMBRE, " +
                        "C.VENDEDOR_ID, " +
                        "C.VENDEDOR_NOMBRE, " +
                        "C.DIR_CLI_ID, C.RFC_CURP, C.CALLE, C.NUM_EXTERIOR, C.NUM_INTERIOR, C.COLONIA, C.POBLACION, C.CODIGO_POSTAL, C.TELEFONO1, C.TELEFONO2, " +
                        "CPAH.POLITICA_DSCTO_ART_CLI_ID, CPAH.NOMBRE_POLITICA, C.LIMITE_CREDITO, C.ESTATUS, C.MONEDA_ID, COALESCE(PT.IMPORTE_POP_GANADO_CON_IMP, 0.00) AS IMPORTE_POP_GANADO_CON_IMP, " +
                        "COALESCE(PT.ESTATUS_POP, '') AS ESTATUS_POP, COALESCE(PT.MONTO_MINIMO_VENTA, 0.00) AS MONTO_MINIMO_VENTA " +                            
                        "FROM CLIENTES_AH C " +
                        "LEFT JOIN CLIENTES_POLITICAS_AH(?, C.CLIENTE_ID) CPAH ON 1 = 1 " +
                        "LEFT JOIN POP_TRIMESTRAL PT ON PT.CLIENTE_ID = C.CLIENTE_ID AND PT.ANIO = " + anio + " AND PT.TRIMESTRE = " + numeroTrimestre;
                else
                    query = 
                        "SELECT C.CLIENTE_ID, C.CLAVE_CLIENTE, C.NOMBRE_CLIENTE, " +
                        "C.TIPO_CLIENTE_ID, C.TIPO_CLIENTE_NOMBRE, " +
                        "C.ZONA_CLIENTE_ID, C.ZONA_CLIENTE_NOMBRE, " +
                        "C.COBRADOR_ID, " +
                        "C.COBRADOR_NOMBRE, " +
                        "C.VENDEDOR_ID, " +
                        "C.VENDEDOR_NOMBRE, " +
                        "C.DIR_CLI_ID, C.RFC_CURP, C.CALLE, C.NUM_EXTERIOR, C.NUM_INTERIOR, C.COLONIA, C.POBLACION, C.CODIGO_POSTAL, C.TELEFONO1, C.TELEFONO2, " +
                        "0 AS POLITICA_DSCTO_ART_CLI_ID, '' AS NOMBRE_POLITICA, C.LIMITE_CREDITO, C.ESTATUS, C.MONEDA_ID, COALESCE(PT.IMPORTE_POP_GANADO_CON_IMP, 0.00), " +
                        "COALESCE(PT.ESTATUS_POP, '') AS ESTATUS_POP, COALESCE(PT.MONTO_MINIMO_VENTA, 0.00) AS MONTO_MINIMO_VENTA " +                            
                        "FROM CLIENTES_AH C " +
                        "LEFT JOIN POP_TRIMESTRAL PT ON PT.CLIENTE_ID = C.CLIENTE_ID AND PT.ANIO = " + anio + " AND PT.TRIMESTRE = " + numeroTrimestre;   
            } else {
                if ((configuracionMobil.getTipoPoliticaAOperar().equals("ARTICULO_CLIENTE")) || (configuracionMobil.getTipoPoliticaAOperar().equals("MULTIPRECIOS")))
                    query = 
                        "SELECT C.CLIENTE_ID, C.CLAVE_CLIENTE, C.NOMBRE_CLIENTE, " +
                        "C.TIPO_CLIENTE_ID, C.TIPO_CLIENTE_NOMBRE, " +
                        "C.ZONA_CLIENTE_ID, C.ZONA_CLIENTE_NOMBRE, " +
                        "C.COBRADOR_ID, " +
                        "C.COBRADOR_NOMBRE, " +
                        "C.VENDEDOR_ID, " +
                        "C.VENDEDOR_NOMBRE, " +
                        "C.DIR_CLI_ID, C.RFC_CURP, C.CALLE, C.NUM_EXTERIOR, C.NUM_INTERIOR, C.COLONIA, C.POBLACION, C.CODIGO_POSTAL, C.TELEFONO1, C.TELEFONO2, " +
                        "CPAH.POLITICA_DSCTO_ART_CLI_ID, CPAH.NOMBRE_POLITICA, C.LIMITE_CREDITO, C.ESTATUS, C.MONEDA_ID, 0.00 AS IMPORTE_POP_GANADO_CON_IMP, " +
                        "'' AS ESTATUS_POP, 0.00 AS MONTO_MINIMO_VENTA " +                            
                        "FROM CLIENTES_AH C " +
                        "LEFT JOIN CLIENTES_POLITICAS_AH(?, C.CLIENTE_ID) CPAH ON 1 = 1 "; //+
                        //"LEFT JOIN POP_TRIMESTRAL PT ON PT.CLIENTE_ID = C.CLIENTE_ID ";
                else
                    query = 
                        "SELECT C.CLIENTE_ID, C.CLAVE_CLIENTE, C.NOMBRE_CLIENTE, " +
                        "C.TIPO_CLIENTE_ID, C.TIPO_CLIENTE_NOMBRE, " +
                        "C.ZONA_CLIENTE_ID, C.ZONA_CLIENTE_NOMBRE, " +
                        "C.COBRADOR_ID, " +
                        "C.COBRADOR_NOMBRE, " +
                        "C.VENDEDOR_ID, " +
                        "C.VENDEDOR_NOMBRE, " +
                        "C.DIR_CLI_ID, C.RFC_CURP, C.CALLE, C.NUM_EXTERIOR, C.NUM_INTERIOR, C.COLONIA, C.POBLACION, C.CODIGO_POSTAL, C.TELEFONO1, C.TELEFONO2, " +
                        "0 AS POLITICA_DSCTO_ART_CLI_ID, '' AS NOMBRE_POLITICA, C.LIMITE_CREDITO, C.ESTATUS, C.MONEDA_ID, 0.00 AS IMPORTE_POP_GANADO_CON_IMP, " +
                        "'' AS ESTATUS_POP, 0.00 AS MONTO_MINIMO_VENTA " +                                              
                        "FROM CLIENTES_AH C ";// +
                        //"LEFT JOIN POP_TRIMESTRAL PT ON PT.CLIENTE_ID = C.CLIENTE_ID ";  
            }
                                                        
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            if ((configuracionMobil.getTipoPoliticaAOperar().equals("ARTICULO_CLIENTE")) || (configuracionMobil.getTipoPoliticaAOperar().equals("MULTIPRECIOS")))
                preparedStatement.setInt(1, configuracionMobil.getPrecioEmpresaId());            
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ClienteRefactor cliente = new ClienteRefactor();
                cliente.setClienteId(resultSet.getInt("CLIENTE_ID"));
                cliente.setClaveCliente(resultSet.getString("CLAVE_CLIENTE"));
                String nombreCliente = resultSet.getString("NOMBRE_CLIENTE");
                //nombreCliente = nombreCliente.replace("'", "\""); 
                //nombreCliente = escapeCharacters(nombreCliente);                                    
                cliente.setNombreCliente(nombreCliente.trim());
                cliente.setTipoClienteId(resultSet.getInt("TIPO_CLIENTE_ID"));
                //cliente.setTipoClienteNombre(resultSet.getString("TIPO_CLIENTE_NOMBRE"));
                cliente.setZonaClienteId(resultSet.getInt("ZONA_CLIENTE_ID"));
                //cliente.setZonaClienteNombre(resultSet.getString("ZONA_CLIENTE_NOMBRE"));
                cliente.setCobradorId(resultSet.getInt("COBRADOR_ID"));
                //cliente.setCobradorNombre(resultSet.getString("COBRADOR_NOMBRE"));
                cliente.setVendedorId(resultSet.getInt("VENDEDOR_ID"));
                //cliente.setVendedorNombre(resultSet.getString("VENDEDOR_NOMBRE"));
                cliente.setRfcCurp(resultSet.getString("RFC_CURP"));
                cliente.setDireccionClienteId(resultSet.getInt("DIR_CLI_ID"));
                //cliente.setCalle(resultSet.getString("CALLE"));
                //cliente.setNumeroExterior(resultSet.getString("NUM_EXTERIOR"));
                //cliente.setNumeroInterior(resultSet.getString("NUM_INTERIOR"));
                //cliente.setColonia(resultSet.getString("COLONIA"));
                //cliente.setPoblacion(resultSet.getString("POBLACION") != null ? resultSet.getString("POBLACION") : "SP");
                //cliente.setCodigoPostal(resultSet.getString("CODIGO_POSTAL"));
                cliente.setTelefono1(resultSet.getString("TELEFONO1") != null ? resultSet.getString("TELEFONO1") : "0000");
                //cliente.setTelefono2(resultSet.getString("TELEFONO2") != null ? resultSet.getString("TELEFONO2") : "0000");
                                
                //REFACTOR PARA EL CASO FERREGAMA AUQNEU TENGA POLITICA EL CTE, HAY QU ELLEVARLA EN CERO YA QUE SE APLICO DESDE EL ARTICULO 
                if (configuracionMobil.getApplyPoliticaPrecioClientes() == 1)
                    cliente.setPoliticaDescuentoArticuloClienteId(0);
                else
                    cliente.setPoliticaDescuentoArticuloClienteId(resultSet.getInt("POLITICA_DSCTO_ART_CLI_ID"));                
                //cliente.setNombrePolitica(resultSet.getString("NOMBRE_POLITICA") == null ? "SIN_POLITICA" : resultSet.getString("NOMBRE_POLITICA"));                
                
                cliente.setLimiteCredito(resultSet.getDouble("LIMITE_CREDITO"));
                cliente.setEstatus(resultSet.getString("ESTATUS"));
                cliente.setMonedaId(resultSet.getInt("MONEDA_ID"));
                cliente.setSaldoPOP(resultSet.getDouble("IMPORTE_POP_GANADO_CON_IMP"));
                cliente.setEstatusPOP(resultSet.getString("ESTATUS_POP"));
                cliente.setMontoMinimoVenta(resultSet.getDouble("MONTO_MINIMO_VENTA"));
                listaCliente.add(cliente);
            }
            
            return gson.toJson(listaCliente);
        } catch (SQLException exception) {
            System.out.println("ERROR: Clientes:" + exception.getMessage());
            return null;
        }
    }
    
    public ResponseRequest clientesDireccionPrincipal(int vendedorId) throws SQLException {
        ResponseRequest responseRequest = new ResponseRequest();      
        try {            
            List<ClienteDireccionPrincipal> listaClientesDireccionPrincipal = new ArrayList<ClienteDireccionPrincipal>();
                        
            String query = 
                    "SELECT C.CLIENTE_ID,  DC.CALLE, DC.NUM_EXTERIOR, DC.NUM_INTERIOR, DC.COLONIA, DC.POBLACION, DC.CODIGO_POSTAL, DC.TELEFONO1, DC.TELEFONO2 FROM CLIENTES C " +
                    "INNER JOIN DIRS_CLIENTES DC ON DC.CLIENTE_ID=C.CLIENTE_ID " +
                    "WHERE DC.ES_DIR_PPAL = 'S' AND C.VENDEDOR_ID = ?";                    
                                                        
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1, vendedorId);            
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ClienteDireccionPrincipal clienteDireccionPrincipal = new ClienteDireccionPrincipal();
                clienteDireccionPrincipal.setClienteId(resultSet.getInt("CLIENTE_ID"));                
                clienteDireccionPrincipal.setCalle(resultSet.getString("CALLE"));
                clienteDireccionPrincipal.setNumeroExterior(resultSet.getString("NUM_EXTERIOR"));
                clienteDireccionPrincipal.setNumeroInterior(resultSet.getString("NUM_INTERIOR"));
                clienteDireccionPrincipal.setColonia(resultSet.getString("COLONIA"));
                clienteDireccionPrincipal.setPoblacion(resultSet.getString("POBLACION") != null ? resultSet.getString("POBLACION") : "SP");
                clienteDireccionPrincipal.setCodigoPostal(resultSet.getString("CODIGO_POSTAL"));               
                clienteDireccionPrincipal.setTelefono1(resultSet.getString("TELEFONO1"));    
                clienteDireccionPrincipal.setTelefono2(resultSet.getString("TELEFONO2"));    
                listaClientesDireccionPrincipal.add(clienteDireccionPrincipal);
            }
            
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaClientesDireccionPrincipal, "Direcciones clientes consultadas correctamente");  
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION al consultar direcciones clientes: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error consultar direcciones clientes " + exception.getMessage());
        }
    }
    
    public ResponseRequest clientesDireccionPrincipal() throws SQLException {
        ResponseRequest responseRequest = new ResponseRequest();      
        try {            
            List<ClienteDireccionPrincipal> listaClientesDireccionPrincipal = new ArrayList<ClienteDireccionPrincipal>();
                        
            String query = 
                    "SELECT C.CLIENTE_ID,  DC.CALLE, DC.NUM_EXTERIOR, DC.NUM_INTERIOR, DC.COLONIA, DC.POBLACION, DC.CODIGO_POSTAL, DC.TELEFONO1, DC.TELEFONO2 FROM CLIENTES C " +
                    "INNER JOIN DIRS_CLIENTES DC ON DC.CLIENTE_ID=C.CLIENTE_ID " +
                    "WHERE DC.ES_DIR_PPAL = 'S'";                    
                                                        
            PreparedStatement preparedStatement = conexion.prepareStatement(query);           
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ClienteDireccionPrincipal clienteDireccionPrincipal = new ClienteDireccionPrincipal();
                clienteDireccionPrincipal.setClienteId(resultSet.getInt("CLIENTE_ID"));                
                clienteDireccionPrincipal.setCalle(resultSet.getString("CALLE"));
                clienteDireccionPrincipal.setNumeroExterior(resultSet.getString("NUM_EXTERIOR"));
                clienteDireccionPrincipal.setNumeroInterior(resultSet.getString("NUM_INTERIOR"));
                clienteDireccionPrincipal.setColonia(resultSet.getString("COLONIA"));
                clienteDireccionPrincipal.setPoblacion(resultSet.getString("POBLACION") != null ? resultSet.getString("POBLACION") : "SP");
                clienteDireccionPrincipal.setCodigoPostal(resultSet.getString("CODIGO_POSTAL"));               
                clienteDireccionPrincipal.setTelefono1(resultSet.getString("TELEFONO1"));    
                clienteDireccionPrincipal.setTelefono2(resultSet.getString("TELEFONO2"));    
                listaClientesDireccionPrincipal.add(clienteDireccionPrincipal);
            }
            
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaClientesDireccionPrincipal, "Direcciones clientes consultadas correctamente");  
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION al consultar direcciones clientes: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error consultar direcciones clientes " + exception.getMessage());
        }
    }
    
    public String clientesEmitenFactura(int vendedorId) throws SQLException {        
        try {
            List<ClienteEmiteFactura> listaClienteEmiteFactura = new ArrayList<ClienteEmiteFactura>();
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT LC.CLIENTE_ID, B.VALOR_DESPLEGADO " +
                "FROM LIBRES_CLIENTES LC " +
                "JOIN LISTAS_ATRIBUTOS B ON B.LISTA_ATRIB_ID = LC.EMITE_FACTURA " +                      
                "INNER JOIN CLIENTES C ON C.CLIENTE_ID = LC.CLIENTE_ID " +
                "WHERE C.VENDEDOR_ID = ?"
            );    
            preparedStatement.setInt(1, vendedorId);
            ResultSet resultSet = preparedStatement.executeQuery();           
                        
            while (resultSet.next()) {                
                ClienteEmiteFactura clienteEmiteFactura = new ClienteEmiteFactura();
                clienteEmiteFactura.setClienteId(resultSet.getInt("CLIENTE_ID"));
                clienteEmiteFactura.setValorDesplegado(resultSet.getString("VALOR_DESPLEGADO"));
                listaClienteEmiteFactura.add(clienteEmiteFactura);
            }            
            System.out.println(new Gson().toJson(listaClienteEmiteFactura));
            return new Gson().toJson(listaClienteEmiteFactura);
        } catch (SQLException e) {
            System.out.println("sql" + e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public String clientesEmitenFactura() throws SQLException {        
        try {
            List<ClienteEmiteFactura> listaClienteEmiteFactura = new ArrayList<ClienteEmiteFactura>();
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT LC.CLIENTE_ID, B.VALOR_DESPLEGADO " +
                "FROM LIBRES_CLIENTES LC " +
                "JOIN LISTAS_ATRIBUTOS B ON B.LISTA_ATRIB_ID = LC.EMITE_FACTURA"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();           
                        
            while (resultSet.next()) {                
                ClienteEmiteFactura clienteEmiteFactura = new ClienteEmiteFactura();
                clienteEmiteFactura.setClienteId(resultSet.getInt("CLIENTE_ID"));
                clienteEmiteFactura.setValorDesplegado(resultSet.getString("VALOR_DESPLEGADO"));
                listaClienteEmiteFactura.add(clienteEmiteFactura);
            }            
            System.out.println(new Gson().toJson(listaClienteEmiteFactura));
            return new Gson().toJson(listaClienteEmiteFactura);
        } catch (SQLException e) {
            System.out.println("sql" + e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public String clientesConsignatariosAGO2022(int vendedorId) throws SQLException {
        try {
            List<ClienteConsignatario> listaClientesConsignatarios = new ArrayList<ClienteConsignatario>();
                
            configuracionMicrosip();
                        
            String query = 
                "SELECT DC.CLIENTE_ID, DC.DIR_CLI_ID, DC.NOMBRE_CONSIG, DC.USAR_PARA_ENVIOS, DC.USAR_PARA_FACTURAR FROM CLIENTES C " +
                "INNER JOIN DIRS_CLIENTES DC ON DC.CLIENTE_ID=C.CLIENTE_ID " +
                "WHERE DC.ES_DIR_PPAL = 'N' AND C.VENDEDOR_ID = ?";
                                                        
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1, vendedorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ClienteConsignatario clienteConsignatario = new ClienteConsignatario();
                clienteConsignatario.setClienteId(resultSet.getInt("CLIENTE_ID"));
                clienteConsignatario.setDireccionClienteId(resultSet.getInt("DIR_CLI_ID"));
                String nombreConsignatario = resultSet.getString("NOMBRE_CONSIG");                                   
                clienteConsignatario.setNombreConsignatario(nombreConsignatario.trim());
                clienteConsignatario.setUsarParaEnvio(resultSet.getString("USAR_PARA_ENVIOS"));
                clienteConsignatario.setUsarParaFacturar(resultSet.getString("USAR_PARA_FACTURAR"));
                listaClientesConsignatarios.add(clienteConsignatario);
            }
            
            return gson.toJson(listaClientesConsignatarios);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    public String clientesConsignatariosAGO2022() throws SQLException {
        try {
            List<ClienteConsignatario> listaClientesConsignatarios = new ArrayList<ClienteConsignatario>();
                
            configuracionMicrosip();
                        
            String query = 
                    "SELECT DC.CLIENTE_ID, DC.DIR_CLI_ID, DC.NOMBRE_CONSIG, DC.USAR_PARA_ENVIOS, DC.USAR_PARA_FACTURAR FROM CLIENTES C " +
                    "INNER JOIN DIRS_CLIENTES DC ON DC.CLIENTE_ID=C.CLIENTE_ID " +
                    "WHERE DC.ES_DIR_PPAL = 'N'";
                                                        
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ClienteConsignatario clienteConsignatario = new ClienteConsignatario();
                clienteConsignatario.setClienteId(resultSet.getInt("CLIENTE_ID"));
                clienteConsignatario.setDireccionClienteId(resultSet.getInt("DIR_CLI_ID"));
                String nombreConsignatario = resultSet.getString("NOMBRE_CONSIG");                                   
                clienteConsignatario.setNombreConsignatario(nombreConsignatario.trim());
                clienteConsignatario.setUsarParaEnvio(resultSet.getString("USAR_PARA_ENVIOS"));
                clienteConsignatario.setUsarParaFacturar(resultSet.getString("USAR_PARA_FACTURAR"));
                
                listaClientesConsignatarios.add(clienteConsignatario);
            }
            
            return gson.toJson(listaClientesConsignatarios);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    public String cobranzaRefactor(int vendedorId) throws SQLException {        
        try {
            List<CobranzaRefactor> listaCobranza = new ArrayList<CobranzaRefactor>();
                        
            
            PreparedStatement preparedStatement = conexion.prepareStatement(
                /*"SELECT C.CLIENTE_ID, C.NOMBRE, B.DOCTO_CC_ID, B.FOLIO, DCC.FECHA AS FECHA_ELABORACION, " +
                "B.FECHA_VENCIMIENTO, B.IMPORTE_CARGO, B.SALDO_CARGO, B.ATRASO, B.CONCEPTO_CC_ID, " +
                "DC.NOMBRE_CONSIG " +
                "FROM CLIENTES C " +
                "LEFT JOIN CARGOS_CLIENTE(C.cliente_id,current_date,current_date,'N','S')B on 1=1 " +
                "INNER JOIN DOCTOS_CC DCC ON DCC.DOCTO_CC_ID=B.DOCTO_CC_ID " +
                "INNER JOIN DOCTOS_VE DVE ON DVE.FOLIO = B.FOLIO " +
                "INNER JOIN DIRS_CLIENTES DC ON DC.DIR_CLI_ID = DVE.DIR_CLI_ID " +
                "WHERE B.DOCTO_CC_ID IS NOT NULL " +
                "AND C.VENDEDOR_ID = " + vendedorId + 
                "ORDER BY B.ATRASO"*/
                //"SELECT C.CLIENTE_ID, C.NOMBRE, B.DOCTO_CC_ID, B.FOLIO, DCC.FECHA AS FECHA_ELABORACION, " +
                "SELECT C.CLIENTE_ID, C.NOMBRE, B.DOCTO_CC_ID, B.FOLIO, B.FECHA_ELABORACION, " +
                "B.FECHA_VENCIMIENTO, B.IMPORTE_CARGO, B.SALDO_CARGO, B.ATRASO, B.CONCEPTO_CC_ID, " +
                "coalesce(DC.NOMBRE_CONSIG, 'Dirección principal') NOMBRE_CONSIG, DVE.DOCTO_VE_ID " +
                "FROM CLIENTES C " +
                "LEFT JOIN CARGOS_CLIENTE_AH(C.cliente_id,current_date,current_date,'N','S')B on 1=1 " +
                //"INNER JOIN DOCTOS_CC DCC ON DCC.DOCTO_CC_ID=B.DOCTO_CC_ID " +
                "left join doctos_entre_sis ds on (ds.docto_dest_id=B.docto_cc_id and CLAVE_SIS_DEST='CC' and  CLAVE_SIS_FTE='VE' and ds.tipo_docto='C') " +                 
                "left JOIN DOCTOS_VE DVE ON (DVE.docto_ve_id = ds.docto_fte_id) " +
                "left JOIN DIRS_CLIENTES DC ON DC.DIR_CLI_ID = DVE.DIR_CLI_ID " +
                "WHERE B.DOCTO_CC_ID IS NOT NULL " +
                "AND C.VENDEDOR_ID = " + vendedorId +
                " ORDER BY B.ATRASO"
            );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CobranzaRefactor cobranza = new CobranzaRefactor();
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
                if (resultSet.getString("NOMBRE_CONSIG") == null)
                    cobranza.setNombreConsignatario("");
                else
                    cobranza.setNombreConsignatario(resultSet.getString("NOMBRE_CONSIG"));
                cobranza.setDoctoVEId(resultSet.getInt("DOCTO_VE_ID"));
                listaCobranza.add(cobranza);
            }
            return gson.toJson(listaCobranza);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    /**************************************************************************/
    
    public ResponseRequest createPedidos(String jsonPedidos) throws SQLException {
        Resources.logger.info("Se ha llamado al recurso REST mediante una peticion POST (createPedidos)");
        Resources.logger.info("Lista de pedidos: " + jsonPedidos);
        ResponseRequest responseRequest = new ResponseRequest();
        ArrayList<PedidoGrabado> listaPedidosGrabados = new ArrayList<PedidoGrabado>();
      
        configuracionMicrosip();
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        JsonElement jsonElement = new JsonParser().parse(jsonPedidos);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        Iterator iterator = jsonArray.iterator();  
        
        try {
            Utilerias utilerias = new Utilerias();
            while (iterator.hasNext()) {
                JsonElement JsonElementTmp = (JsonElement) iterator.next();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<MaestroPedido>() {}.getType();
                MaestroPedido objectMaestroPedido = gson.fromJson(JsonElementTmp, collectionType);
                
                Resources.logger.info("Json item: " + gson.toJson(objectMaestroPedido));
                
                conexion.setAutoCommit(false);
                
                //---------- Verificar que el pedido no haya sido guardado
                PedidoExistente pedidoExistente = existePedidoGuardado(objectMaestroPedido.getUuid());                
                String serieFolio = pedidoExistente.getFolio();
                if (pedidoExistente.getNumeroMovimiento() == 0) {
                    preparedStatement = conexion.prepareStatement(
                        "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                    );
                    resultSet = preparedStatement.executeQuery();
                    int idAutoIncremental= 0;                
                    while (resultSet.next()) {
                        idAutoIncremental = resultSet.getInt("ID");                    
                    } 
                    Resources.logger.info("idAutoIncremental: " + idAutoIncremental);

                    preparedStatement = conexion.prepareStatement(
                        "SELECT SERIE, MAX(FOLIO) + 1 AS FOLIO FROM VENDEDORES_SERIES_FOLIOS " +
                        " WHERE VENDEDOR_ID = " + objectMaestroPedido.getVendedorId() +
                        " GROUP BY 1"
                    );
                    resultSet = preparedStatement.executeQuery();
                    int consecutivoFolio= 0;
                    String serie = "";
                    while (resultSet.next()) {
                        consecutivoFolio = resultSet.getInt("FOLIO");
                        serie = resultSet.getString("SERIE");
                    }   

                    Resources.logger.info("consecutivoFolio: " + consecutivoFolio + " serie: " + serie);

                    preparedStatement = conexion.prepareStatement(
                        "SELECT COND_PAGO_ID FROM CLIENTES WHERE CLIENTE_ID = " + objectMaestroPedido.getClienteId()
                    );
                    resultSet = preparedStatement.executeQuery();
                    int condicionPagoId= 0;                
                    while (resultSet.next()) {
                        condicionPagoId = resultSet.getInt("COND_PAGO_ID");                    
                    }                              

                    Resources.logger.info("condicionPagoId: " + condicionPagoId);

                    int direccionCliente = 0;
                    if ((objectMaestroPedido.getDireccionConsignatarioId()!= 0) && (objectMaestroPedido.getDireccionConsignatarioEnvioId()!= 0)) //CAMBIO FACTURA Y ENVIO                                        
                        direccionCliente = objectMaestroPedido.getDireccionConsignatarioEnvioId();
                    else if ((objectMaestroPedido.getDireccionConsignatarioId()== 0) && (objectMaestroPedido.getDireccionConsignatarioEnvioId()!= 0))  //CAMBIO ENVIO NADA MAS
                        direccionCliente = objectMaestroPedido.getDireccionConsignatarioEnvioId();
                    else if ((objectMaestroPedido.getDireccionConsignatarioId()!= 0) && (objectMaestroPedido.getDireccionConsignatarioEnvioId()== 0))  //CAMBIO FACTURA NADA MAS
                        direccionCliente = objectMaestroPedido.getDireccionConsignatarioId();
                    else //NO CAMBIO NADA 
                        direccionCliente = objectMaestroPedido.getDireccionClienteId();
                    
                    preparedStatement = conexion.prepareStatement(                            
                        "SELECT VIA_EMBARQUE_ID FROM DIRS_CLIENTES" +
                        " WHERE DIR_CLI_ID = " + direccionCliente                             
                        //" WHERE DIR_CLI_ID = " + objectMaestroPedido.getDireccionClienteId()
                    );
                    resultSet = preparedStatement.executeQuery();
                    int viaEmbarqueId= 0;                
                    while (resultSet.next()) {
                        viaEmbarqueId = resultSet.getInt("VIA_EMBARQUE_ID");                    
                    }
                    Resources.logger.info("viaEmbarqueId: " + viaEmbarqueId);

                    Resources.logger.info("VAMOS A GRABAR CABECERA");

                    String querySegunVersion = "";
                    if (configuracionMobil.getMicrosip2020() == 0) {
                        querySegunVersion = "INSERT INTO DOCTOS_VE (DOCTO_VE_ID, TIPO_DOCTO, SUBTIPO_DOCTO, FOLIO, FECHA, HORA, CLAVE_CLIENTE, CLIENTE_ID, "
                            + "DIR_CLI_ID, DIR_CONSIG_ID, ALMACEN_ID, "
                            + "MONEDA_ID, TIPO_CAMBIO, TIPO_DSCTO, "
                            + "DSCTO_PCTJE, DSCTO_IMPORTE, ESTATUS, APLICADO, FECHA_VIGENCIA_ENTREGA, "
                            + "DESCRIPCION, "                        
                            + "IMPORTE_NETO, FLETES, OTROS_CARGOS, TOTAL_IMPUESTOS, TOTAL_RETENCIONES, TOTAL_ANTICIPOS, "
                            + "PESO_EMBARQUE, FORMA_EMITIDA, CONTABILIZADO, ACREDITAR_CXC, SISTEMA_ORIGEN, COND_PAGO_ID, "                        
                            + "PCTJE_DSCTO_PPAG, VENDEDOR_ID, PCTJE_COMIS, VIA_EMBARQUE_ID, IMPORTE_COBRO,"
                            + "USUARIO_CREADOR, ES_CFD, ENVIADO, "
                            + "CFD_ENVIO_ESPECIAL, CFDI_CERTIFICADO, FECHA_HORA_CREACION, "
                            + "CARGAR_SUN)" +
                            "VALUES (";
                    } else if (configuracionMobil.getMicrosip2020() != 0) {
                        querySegunVersion = "INSERT INTO DOCTOS_VE (DOCTO_VE_ID, TIPO_DOCTO, SUBTIPO_DOCTO, FOLIO, FECHA, HORA, CLAVE_CLIENTE, CLIENTE_ID, "
                            + "DIR_CLI_ID, DIR_CONSIG_ID, ALMACEN_ID, "
                            + "MONEDA_ID, TIPO_CAMBIO, TIPO_DSCTO, "
                            + "DSCTO_PCTJE, DSCTO_IMPORTE, ESTATUS, APLICADO, FECHA_VIGENCIA_ENTREGA, "
                            + "DESCRIPCION, "                        
                            + "IMPORTE_NETO, FLETES, OTROS_CARGOS, TOTAL_IMPUESTOS, TOTAL_RETENCIONES, TOTAL_ANTICIPOS, "
                            + "PESO_EMBARQUE, FORMA_EMITIDA, CONTABILIZADO, ACREDITAR_CXC, SISTEMA_ORIGEN, COND_PAGO_ID, "                        
                            + "PCTJE_DSCTO_PPAG, VENDEDOR_ID, PCTJE_COMIS, VIA_EMBARQUE_ID, IMPORTE_COBRO,"
                            + "USUARIO_CREADOR, ES_CFD, ENVIADO, "
                            + "CFD_ENVIO_ESPECIAL, CFDI_CERTIFICADO, FECHA_HORA_CREACION, "
                            + "CARGAR_SUN, SUCURSAL_ID)" +
                            "VALUES (";
                    }

                    //PreparedStatement preparedStatementObj = conexion.prepareStatement(querySegunVersion);                 
                    querySegunVersion = querySegunVersion + idAutoIncremental + ", ";

                    if (configuracionMobil.getComportamientoCaptura().equals(Constants.COTIZACION))                        
                        querySegunVersion = querySegunVersion + "'" + Constants.COTIZACION + "', ";
                    else
                        querySegunVersion = querySegunVersion + "'" + Constants.PEDIDO + "', ";                        
                        
                    querySegunVersion = querySegunVersion + "'N', ";  
                    int posiciones =  9 - serie.length();//Son 9 la longitud del campo FOLIO
                    serieFolio = serie + StringUtils.leftPad(String.valueOf(consecutivoFolio), posiciones, "0");
                     
                    querySegunVersion = querySegunVersion + "'" + serieFolio + "', ";  
                    querySegunVersion = querySegunVersion + "'" + objectMaestroPedido.getFechaPedido() + "', ";  
                    querySegunVersion = querySegunVersion + "'" + objectMaestroPedido.getHoraPedido() + "', ";  
                    querySegunVersion = querySegunVersion + "'" + objectMaestroPedido.getClaveCliente() + "', ";  
                    querySegunVersion = querySegunVersion + objectMaestroPedido.getClienteId() + ", ";                      
                                                                                    
                    
                    if (objectMaestroPedido.getDireccionConsignatarioId()!= 0)                        
                        querySegunVersion = querySegunVersion + objectMaestroPedido.getDireccionConsignatarioId() + ", ";
                    else
                        querySegunVersion = querySegunVersion + objectMaestroPedido.getDireccionClienteId() + ", ";                                                                                                                                   
                   
                    //REFACTOR CONSIGNATARIOS ENVIO
                    if (objectMaestroPedido.getDireccionConsignatarioEnvioId()!= 0) 
                        querySegunVersion = querySegunVersion + objectMaestroPedido.getDireccionConsignatarioEnvioId() + ", ";                        
                    else                        
                        querySegunVersion = querySegunVersion + objectMaestroPedido.getDireccionClienteId() + ", ";                                                                                                                                                                               
                    
                    querySegunVersion = querySegunVersion + objectMaestroPedido.getAlmacenId() + ", ";
                    querySegunVersion = querySegunVersion + "1, ";
                    querySegunVersion = querySegunVersion + "1.00, ";
                    querySegunVersion = querySegunVersion + "'P', ";  
                    querySegunVersion = querySegunVersion + "0.00, ";
                    querySegunVersion = querySegunVersion + "0.00, ";               
                    querySegunVersion = querySegunVersion + "'P', ";  
                    querySegunVersion = querySegunVersion + "'S', ";  
                    querySegunVersion = querySegunVersion + "'" + objectMaestroPedido.getFechaPedido() + "', ";          
                    querySegunVersion = querySegunVersion + "'" + objectMaestroPedido.getObservaciones() + "', ";          
                    querySegunVersion = querySegunVersion + objectMaestroPedido.getImporteNeto() + ", ";     
                    querySegunVersion = querySegunVersion + "0.00, ";               
                    querySegunVersion = querySegunVersion + "0.00, ";               
                    querySegunVersion = querySegunVersion + objectMaestroPedido.getTotalImpuestos() + ", ";     
                    querySegunVersion = querySegunVersion + "0.00, ";               
                    querySegunVersion = querySegunVersion + "0.00, ";               
                    querySegunVersion = querySegunVersion + "0.00, ";               
                    querySegunVersion = querySegunVersion + "'N', ";  
                    querySegunVersion = querySegunVersion + "'N', ";  
                    querySegunVersion = querySegunVersion + "'N', ";  
                    querySegunVersion = querySegunVersion + "'VE', ";  
                    querySegunVersion = querySegunVersion + condicionPagoId + ", ";
                    querySegunVersion = querySegunVersion + "0.00, ";               
                    querySegunVersion = querySegunVersion + objectMaestroPedido.getVendedorId() + ", ";     
                    querySegunVersion = querySegunVersion + "0.00, ";               
                    querySegunVersion = querySegunVersion + viaEmbarqueId + ", ";
                    querySegunVersion = querySegunVersion + "0.00, ";    
                    querySegunVersion = querySegunVersion + "'" + Constants.SYSDBA + "', ";          
                    querySegunVersion = querySegunVersion + "'N', ";  
                    querySegunVersion = querySegunVersion + "'N', ";  
                    querySegunVersion = querySegunVersion + "'N', ";        
                    querySegunVersion = querySegunVersion + "'N', ";
                                                       
                    querySegunVersion = querySegunVersion + "CURRENT_TIMESTAMP, ";
                    querySegunVersion = querySegunVersion + "'S' ";

                    if (configuracionMobil.getMicrosip2020() != 0) {
                        if (configuracionMobil.getOperaSucursalAlmacen() == 1) {
                            preparedStatement = conexion.prepareStatement(
                                "SELECT SC.SUCURSAL_ID FROM SUCURSALES_CATALOGOS SC " +
                                "INNER JOIN SUCURSALES_CATALOGOS_DET D ON SC.SUCURSAL_CATALOGO_ID = D.SUCURSAL_CATALOGO_ID " +
                                "INNER JOIN CONFIGURACION_ALMACENES CA ON CA.ALMACEN_ID = D.ELEMENTO_ID " +
                                "WHERE SC.NOM_TABLA = 'ALMACENES' AND CA.ALMACEN_ID = " +  objectMaestroPedido.getAlmacenId()
                            );
                            resultSet = preparedStatement.executeQuery();
                            int sucursalId= 0;                
                            while (resultSet.next()) {
                                sucursalId = resultSet.getInt("SUCURSAL_ID");                    
                            }
                            Resources.logger.info("CALCULADA sucursalId: " + sucursalId);
                            
                            querySegunVersion = querySegunVersion + "," + sucursalId + ") ";
                        } else {
                            Resources.logger.info("CONFIGURACION MOBIL sucursalId: " + configuracionMobil.getSucursalId());
                            
                            querySegunVersion = querySegunVersion + "," + configuracionMobil.getSucursalId() + ") ";
                        }
                    } else
                        querySegunVersion = querySegunVersion + ")";

                    //Resources.logger.info("QUERY PRINCIPAL: " + querySegunVersion);
                    PreparedStatement preparedStatementObj = conexion.prepareStatement(querySegunVersion);  
                    preparedStatementObj.executeUpdate();
                    Resources.logger.info("Grabando en la cabecera");

                    // REFACTOR
                    preparedStatement = conexion.prepareStatement(
                            "UPDATE VENDEDORES_SERIES_FOLIOS SET " +
                            " FOLIO = " + consecutivoFolio + 
                            " WHERE SERIE = '" + serie + "'"
                    );                             
                    preparedStatement.executeUpdate();
                    Resources.logger.info("Actualizando la serie: " + serie + " con el folio: " + consecutivoFolio);

                    // Cambio para el cliente de AFASY 20-NOV-2020
                    if (configuracionMobil.getComportamientoCaptura().equals(Constants.COTIZACION)) {
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO LIBRES_COT_VE (DOCTO_VE_ID, EMITE_FACTURA)" +
                            "VALUES (?,?)"
                        );
                        preparedStatementObj.setInt(1, idAutoIncremental);
                        preparedStatementObj.setString(2, objectMaestroPedido.getCotizacionEmiteFactura());
                        preparedStatementObj.executeUpdate();                                
                        
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO VIT_COTIZ_PROCESADAS (DOCTO_VE_ID, ESTATUS)" +
                            "VALUES (?,?)"
                        );
                        preparedStatementObj.setInt(1, idAutoIncremental);
                        preparedStatementObj.setString(2, "P");
                        preparedStatementObj.executeUpdate();                                                        
                    }                                
                    // REFACTOR
                    //==============================================================================
                    // ========== Se inserta el detalle del pedido en MAEDTMA02 ====================
                    //==============================================================================
                           
                    JsonElement json = new JsonParser().parse(new Gson().toJson(objectMaestroPedido.getListaDetallePedido()));
                    JsonArray array = json.getAsJsonArray();
                    Iterator iterator2 = array.iterator();
                    List<DetallePedido> details = new ArrayList<DetallePedido>();

                    int consecutivo = 0;
                    while (iterator2.hasNext()) {                                
                        consecutivo++;
                        JsonElement json2 = (JsonElement) iterator2.next();                    
                        Type collectionType2 = new TypeToken<DetallePedido>() {}.getType();
                        DetallePedido detallePedido = gson.fromJson(json2, collectionType2);

                        Resources.logger.info(consecutivo + " Detalle del pedido");

                        preparedStatementObj = conexion.prepareStatement(
                        "INSERT INTO DOCTOS_VE_DET (DOCTO_VE_DET_ID, DOCTO_VE_ID, CLAVE_ARTICULO, ARTICULO_ID, "
                        + "UNIDADES, UNIDADES_COMPROM, "
                        + "UNIDADES_SURT_DEV, UNIDADES_A_SURTIR, "
                        + "PRECIO_UNITARIO, PCTJE_DSCTO, DSCTO_ART, PCTJE_DSCTO_CLI, "
                        + "DSCTO_EXTRA, PCTJE_DSCTO_VOL, PCTJE_DSCTO_PROMO, PRECIO_TOTAL_NETO, "
                        + "PCTJE_COMIS, ROL, POSICION)" +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                        );
                        //preparedStatementObj.setInt(1, -1);//REFACTOR JUNIO 2022
                        PreparedStatement preparedStatementDetalle = conexion.prepareStatement(
                            "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                        );
                        ResultSet resultSetDetalle = preparedStatementDetalle.executeQuery();
                        int idAutoIncrementalDetalle= 0;                
                        while (resultSetDetalle.next()) {
                            idAutoIncrementalDetalle = resultSetDetalle.getInt("ID");                    
                        } 
                        preparedStatementObj.setInt(1, idAutoIncrementalDetalle);
                        preparedStatementObj.setInt(2, idAutoIncremental);
                        preparedStatementObj.setString(3, detallePedido.getClave_articulo());
                        preparedStatementObj.setInt(4, detallePedido.getArticulo_id());
                        preparedStatementObj.setDouble(5, detallePedido.getUnidades());
                        preparedStatementObj.setDouble(6, 0.00);                
                        preparedStatementObj.setDouble(7, 0.00);
                        preparedStatementObj.setDouble(8, 0.00);                                                                
                        /**********************************************************/                                           
                        Double precioUnitarioSinImpuesto = detallePedido.getPrecio_unitario_sin_impuestos();
                        preparedStatementObj.setDouble(9, precioUnitarioSinImpuesto);//PRECIO SIN IVA                    
                        Double porcentajeDescuentoTotal = porcentajeDescuentoTotalXArticulo(detallePedido, false);
                        preparedStatementObj.setDouble(10, porcentajeDescuentoTotal);
                        preparedStatementObj.setDouble(11, (precioUnitarioSinImpuesto * detallePedido.getUnidades()) * (porcentajeDescuentoTotal / 100)); //VALOR EN MONEDA DEL DESCUENTO   100-10%=10.00
                        preparedStatementObj.setDouble(12, detallePedido.getPorcentaje_descuento_articulo_cliente());                                          
                        preparedStatementObj.setDouble(13, 0.00);
                        preparedStatementObj.setDouble(14, detallePedido.getTipo_politica().equals("VOLUMEN") ? detallePedido.getPorcentaje_descuento_promocion_volumen() : 0.00);                                          
                        preparedStatementObj.setDouble(15, detallePedido.getTipo_politica().equals("PROMOCION") ? detallePedido.getPorcentaje_descuento_promocion_volumen() : 0.00);                
                        /**********************************************************/
                        preparedStatementObj.setDouble(16, (detallePedido.getPrecio_unitario_sin_impuestos() - detallePedido.getPrecio_unitario_con_descuento_sin_impuestos()) * detallePedido.getUnidades());                 //TODOS LOS VALORES SON SIN IMPUESTOS
                        preparedStatementObj.setDouble(17, 0.00);
                        preparedStatementObj.setString(18, detallePedido.getEs_juego().equals("S") ? "J" : "N");
                        preparedStatementObj.setInt(19, consecutivo);
                        preparedStatementObj.executeUpdate();  
                        
                        //PARA CUANDO EL ARTICULO SEA JUEGO, METER EL DETALLE DEL JUEGO
                        if (detallePedido.getEs_juego().equals("S"))
                            creteDetalleJuego(idAutoIncremental, detallePedido.getArticulo_id());
                    }

                    //---------- SE GRABA EL MOVIMIENTO CON EL DEL MOBIL
                    createPedidoGuardado(objectMaestroPedido.getUuid(), idAutoIncremental, serieFolio);
                }                                                               
                // Este objeto es para agregar el id que se generp en ANDROID y retornarlo en una lista
                PedidoGrabado pedidoGrabadoObject = new PedidoGrabado();
                pedidoGrabadoObject.setId(objectMaestroPedido.getId());
                pedidoGrabadoObject.setNummov(0);                
                pedidoGrabadoObject.setFolio(serieFolio);                
                listaPedidosGrabados.add(pedidoGrabadoObject); 
                //}    
                
                conexion.commit();
            }
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaPedidosGrabados, "Pedidos grabados correctamente");          
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en la cabecera del pedido: " + exception.getMessage());       
            conexion.rollback();
            exception.printStackTrace();  
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al procesar los pedidos " + exception.getMessage());
        }
    }

    public ResponseRequest createPedidosPOP(String jsonPedidos) throws SQLException {
        Resources.logger.info("Se ha llamado al recurso REST mediante una peticion POST (createPedidos)");
        Resources.logger.info("Lista de pedidos: " + jsonPedidos);
        ResponseRequest responseRequest = new ResponseRequest();
        ArrayList<PedidoGrabado> listaPedidosGrabados = new ArrayList<PedidoGrabado>();
      
        configuracionMicrosip();
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        JsonElement jsonElement = new JsonParser().parse(jsonPedidos);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        Iterator iterator = jsonArray.iterator();  
        
        try {
            Utilerias utilerias = new Utilerias();
            while (iterator.hasNext()) {
                JsonElement JsonElementTmp = (JsonElement) iterator.next();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<MaestroPedido>() {}.getType();
                MaestroPedido objectMaestroPedido = gson.fromJson(JsonElementTmp, collectionType);
                
                Resources.logger.info("Json item: " + gson.toJson(objectMaestroPedido));
                
                conexion.setAutoCommit(false);
                
                //---------- Verificar que el pedido no haya sido guardado
                PedidoExistente pedidoExistente = existePedidoGuardado(objectMaestroPedido.getUuid());                
                String serieFolio = pedidoExistente.getFolio();
                if (pedidoExistente.getNumeroMovimiento() == 0) {
                    preparedStatement = conexion.prepareStatement(
                        "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                    );
                    resultSet = preparedStatement.executeQuery();
                    int idAutoIncremental= 0;                
                    while (resultSet.next()) {
                        idAutoIncremental = resultSet.getInt("ID");                    
                    } 
                    Resources.logger.info("idAutoIncremental: " + idAutoIncremental);

                    preparedStatement = conexion.prepareStatement(
                        "SELECT SERIE, MAX(FOLIO) + 1 AS FOLIO FROM VENDEDORES_SERIES_FOLIOS " +
                        " WHERE VENDEDOR_ID = " + objectMaestroPedido.getVendedorId() +
                        " GROUP BY 1"
                    );
                    resultSet = preparedStatement.executeQuery();
                    int consecutivoFolio= 0;
                    String serie = "";
                    while (resultSet.next()) {
                        consecutivoFolio = resultSet.getInt("FOLIO");
                        serie = resultSet.getString("SERIE");
                    }   

                    Resources.logger.info("consecutivoFolio: " + consecutivoFolio + " serie: " + serie);

                    preparedStatement = conexion.prepareStatement(
                        "SELECT COND_PAGO_ID FROM CLIENTES WHERE CLIENTE_ID = " + objectMaestroPedido.getClienteId()
                    );
                    resultSet = preparedStatement.executeQuery();
                    int condicionPagoId= 0;                
                    while (resultSet.next()) {
                        condicionPagoId = resultSet.getInt("COND_PAGO_ID");                    
                    }                              

                    Resources.logger.info("condicionPagoId: " + condicionPagoId);

                    int direccionCliente = 0;
                    if ((objectMaestroPedido.getDireccionConsignatarioId()!= 0) && (objectMaestroPedido.getDireccionConsignatarioEnvioId()!= 0)) //CAMBIO FACTURA Y ENVIO                                        
                        direccionCliente = objectMaestroPedido.getDireccionConsignatarioEnvioId();
                    else if ((objectMaestroPedido.getDireccionConsignatarioId()== 0) && (objectMaestroPedido.getDireccionConsignatarioEnvioId()!= 0))  //CAMBIO ENVIO NADA MAS
                        direccionCliente = objectMaestroPedido.getDireccionConsignatarioEnvioId();
                    else if ((objectMaestroPedido.getDireccionConsignatarioId()!= 0) && (objectMaestroPedido.getDireccionConsignatarioEnvioId()== 0))  //CAMBIO FACTURA NADA MAS
                        direccionCliente = objectMaestroPedido.getDireccionConsignatarioId();
                    else //NO CAMBIO NADA 
                        direccionCliente = objectMaestroPedido.getDireccionClienteId();
                    
                    preparedStatement = conexion.prepareStatement(                            
                        "SELECT VIA_EMBARQUE_ID FROM DIRS_CLIENTES" +
                        " WHERE DIR_CLI_ID = " + direccionCliente                             
                        //" WHERE DIR_CLI_ID = " + objectMaestroPedido.getDireccionClienteId()
                    );
                    resultSet = preparedStatement.executeQuery();
                    int viaEmbarqueId= 0;                
                    while (resultSet.next()) {
                        viaEmbarqueId = resultSet.getInt("VIA_EMBARQUE_ID");                    
                    }
                    Resources.logger.info("viaEmbarqueId: " + viaEmbarqueId);

                    Resources.logger.info("VAMOS A GRABAR CABECERA");

                    String querySegunVersion = "";
                    if (configuracionMobil.getMicrosip2020() == 0) {
                        querySegunVersion = "INSERT INTO DOCTOS_VE (DOCTO_VE_ID, TIPO_DOCTO, SUBTIPO_DOCTO, FOLIO, FECHA, HORA, CLAVE_CLIENTE, CLIENTE_ID, "
                            + "DIR_CLI_ID, DIR_CONSIG_ID, ALMACEN_ID, "
                            + "MONEDA_ID, TIPO_CAMBIO, TIPO_DSCTO, "
                            + "DSCTO_PCTJE, DSCTO_IMPORTE, ESTATUS, APLICADO, FECHA_VIGENCIA_ENTREGA, "
                            + "DESCRIPCION, "                        
                            + "IMPORTE_NETO, FLETES, OTROS_CARGOS, TOTAL_IMPUESTOS, TOTAL_RETENCIONES, TOTAL_ANTICIPOS, "
                            + "PESO_EMBARQUE, FORMA_EMITIDA, CONTABILIZADO, ACREDITAR_CXC, SISTEMA_ORIGEN, COND_PAGO_ID, "                        
                            + "PCTJE_DSCTO_PPAG, VENDEDOR_ID, PCTJE_COMIS, VIA_EMBARQUE_ID, IMPORTE_COBRO,"
                            + "USUARIO_CREADOR, ES_CFD, ENVIADO, "
                            + "CFD_ENVIO_ESPECIAL, CFDI_CERTIFICADO, FECHA_HORA_CREACION, "
                            + "CARGAR_SUN)" +
                            "VALUES (";
                    } else if (configuracionMobil.getMicrosip2020() != 0) {
                        querySegunVersion = "INSERT INTO DOCTOS_VE (DOCTO_VE_ID, TIPO_DOCTO, SUBTIPO_DOCTO, FOLIO, FECHA, HORA, CLAVE_CLIENTE, CLIENTE_ID, "
                            + "DIR_CLI_ID, DIR_CONSIG_ID, ALMACEN_ID, "
                            + "MONEDA_ID, TIPO_CAMBIO, TIPO_DSCTO, "
                            + "DSCTO_PCTJE, DSCTO_IMPORTE, ESTATUS, APLICADO, FECHA_VIGENCIA_ENTREGA, "
                            + "DESCRIPCION, "                        
                            + "IMPORTE_NETO, FLETES, OTROS_CARGOS, TOTAL_IMPUESTOS, TOTAL_RETENCIONES, TOTAL_ANTICIPOS, "
                            + "PESO_EMBARQUE, FORMA_EMITIDA, CONTABILIZADO, ACREDITAR_CXC, SISTEMA_ORIGEN, COND_PAGO_ID, "                        
                            + "PCTJE_DSCTO_PPAG, VENDEDOR_ID, PCTJE_COMIS, VIA_EMBARQUE_ID, IMPORTE_COBRO,"
                            + "USUARIO_CREADOR, ES_CFD, ENVIADO, "
                            + "CFD_ENVIO_ESPECIAL, CFDI_CERTIFICADO, FECHA_HORA_CREACION, "
                            + "CARGAR_SUN, SUCURSAL_ID)" +
                            "VALUES (";
                    }

                    //PreparedStatement preparedStatementObj = conexion.prepareStatement(querySegunVersion);                 
                    querySegunVersion = querySegunVersion + idAutoIncremental + ", ";

                    if (configuracionMobil.getComportamientoCaptura().equals(Constants.COTIZACION))                        
                        querySegunVersion = querySegunVersion + "'" + Constants.COTIZACION + "', ";
                    else
                        querySegunVersion = querySegunVersion + "'" + Constants.PEDIDO + "', ";                        
                        
                    querySegunVersion = querySegunVersion + "'N', ";  
                    int posiciones =  9 - serie.length();//Son 9 la longitud del campo FOLIO
                    serieFolio = serie + StringUtils.leftPad(String.valueOf(consecutivoFolio), posiciones, "0");
                     
                    querySegunVersion = querySegunVersion + "'" + serieFolio + "', ";  
                    querySegunVersion = querySegunVersion + "'" + objectMaestroPedido.getFechaPedido() + "', ";  
                    querySegunVersion = querySegunVersion + "'" + objectMaestroPedido.getHoraPedido() + "', ";  
                    querySegunVersion = querySegunVersion + "'" + objectMaestroPedido.getClaveCliente() + "', ";  
                    querySegunVersion = querySegunVersion + objectMaestroPedido.getClienteId() + ", ";                      
                                                                                    
                    
                    if (objectMaestroPedido.getDireccionConsignatarioId()!= 0)                        
                        querySegunVersion = querySegunVersion + objectMaestroPedido.getDireccionConsignatarioId() + ", ";
                    else
                        querySegunVersion = querySegunVersion + objectMaestroPedido.getDireccionClienteId() + ", ";                                                                                                                                   
                   
                    //REFACTOR CONSIGNATARIOS ENVIO
                    if (objectMaestroPedido.getDireccionConsignatarioEnvioId()!= 0) 
                        querySegunVersion = querySegunVersion + objectMaestroPedido.getDireccionConsignatarioEnvioId() + ", ";                        
                    else                        
                        querySegunVersion = querySegunVersion + objectMaestroPedido.getDireccionClienteId() + ", ";                                                                                                                                                                               
                    
                    querySegunVersion = querySegunVersion + objectMaestroPedido.getAlmacenId() + ", ";
                    querySegunVersion = querySegunVersion + "1, ";
                    querySegunVersion = querySegunVersion + "1.00, ";
                    querySegunVersion = querySegunVersion + "'P', ";  
                    querySegunVersion = querySegunVersion + "0.00, ";
                    querySegunVersion = querySegunVersion + "0.00, ";               
                    querySegunVersion = querySegunVersion + "'P', ";  
                    querySegunVersion = querySegunVersion + "'S', ";  
                    querySegunVersion = querySegunVersion + "'" + objectMaestroPedido.getFechaPedido() + "', ";          
                    querySegunVersion = querySegunVersion + "'" + objectMaestroPedido.getObservaciones() + "', ";          
                    querySegunVersion = querySegunVersion + objectMaestroPedido.getImporteNeto() + ", ";     
                    querySegunVersion = querySegunVersion + "0.00, ";               
                    querySegunVersion = querySegunVersion + "0.00, ";               
                    querySegunVersion = querySegunVersion + objectMaestroPedido.getTotalImpuestos() + ", ";     
                    querySegunVersion = querySegunVersion + "0.00, ";               
                    querySegunVersion = querySegunVersion + "0.00, ";               
                    querySegunVersion = querySegunVersion + "0.00, ";               
                    querySegunVersion = querySegunVersion + "'N', ";  
                    querySegunVersion = querySegunVersion + "'N', ";  
                    querySegunVersion = querySegunVersion + "'N', ";  
                    querySegunVersion = querySegunVersion + "'VE', ";  
                    querySegunVersion = querySegunVersion + condicionPagoId + ", ";
                    querySegunVersion = querySegunVersion + "0.00, ";               
                    querySegunVersion = querySegunVersion + objectMaestroPedido.getVendedorId() + ", ";     
                    querySegunVersion = querySegunVersion + "0.00, ";               
                    querySegunVersion = querySegunVersion + viaEmbarqueId + ", ";
                    querySegunVersion = querySegunVersion + "0.00, ";    
                    querySegunVersion = querySegunVersion + "'" + Constants.SYSDBA + "', ";          
                    querySegunVersion = querySegunVersion + "'N', ";  
                    querySegunVersion = querySegunVersion + "'N', ";  
                    querySegunVersion = querySegunVersion + "'N', ";        
                    querySegunVersion = querySegunVersion + "'N', ";
                                                       
                    querySegunVersion = querySegunVersion + "CURRENT_TIMESTAMP, ";
                    querySegunVersion = querySegunVersion + "'S' ";

                    if (configuracionMobil.getMicrosip2020() != 0) {
                        if (configuracionMobil.getOperaSucursalAlmacen() == 1) {
                            preparedStatement = conexion.prepareStatement(
                                "SELECT SC.SUCURSAL_ID FROM SUCURSALES_CATALOGOS SC " +
                                "INNER JOIN SUCURSALES_CATALOGOS_DET D ON SC.SUCURSAL_CATALOGO_ID = D.SUCURSAL_CATALOGO_ID " +
                                "INNER JOIN CONFIGURACION_ALMACENES CA ON CA.ALMACEN_ID = D.ELEMENTO_ID " +
                                "WHERE SC.NOM_TABLA = 'ALMACENES' AND CA.ALMACEN_ID = " +  objectMaestroPedido.getAlmacenId()
                            );
                            resultSet = preparedStatement.executeQuery();
                            int sucursalId= 0;                
                            while (resultSet.next()) {
                                sucursalId = resultSet.getInt("SUCURSAL_ID");                    
                            }
                            Resources.logger.info("CALCULADA sucursalId: " + sucursalId);
                            
                            querySegunVersion = querySegunVersion + "," + sucursalId + ") ";
                        } else {
                            Resources.logger.info("CONFIGURACION MOBIL sucursalId: " + configuracionMobil.getSucursalId());
                            
                            querySegunVersion = querySegunVersion + "," + configuracionMobil.getSucursalId() + ") ";
                        }
                    } else
                        querySegunVersion = querySegunVersion + ")";

                    //Resources.logger.info("QUERY PRINCIPAL: " + querySegunVersion);
                    PreparedStatement preparedStatementObj = conexion.prepareStatement(querySegunVersion);  
                    preparedStatementObj.executeUpdate();
                    Resources.logger.info("Grabando en la cabecera");

                    // REFACTOR
                    preparedStatement = conexion.prepareStatement(
                            "UPDATE VENDEDORES_SERIES_FOLIOS SET " +
                            " FOLIO = " + consecutivoFolio + 
                            " WHERE SERIE = '" + serie + "'"
                    );                             
                    preparedStatement.executeUpdate();
                    Resources.logger.info("Actualizando la serie: " + serie + " con el folio: " + consecutivoFolio);
                    
                    if (objectMaestroPedido.getEsPOP()) {                                                                                               
                        Resources.logger.info("SE ACTUALIZA EL STATUS POP DEL CLIENTE A BLOQUEADO");
                        LocalDate fecha = LocalDate.now(); // Fecha actual
                        String nombreMes = fecha.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase();
                            
                        preparedStatementObj = conexion.prepareStatement(
                            "SELECT NUMERO FROM TRIMESTRES WHERE NOMBRE LIKE '%" + nombreMes + "%'"
                        );
                        resultSet = preparedStatementObj.executeQuery();
                        int numeroTrimestre = 0;
                        int anio = 0;
                        while (resultSet.next()) {
                            numeroTrimestre = resultSet.getInt("NUMERO");
                            anio = fecha.getYear();
                            if (numeroTrimestre == 1) {
                                numeroTrimestre = 4;
                                anio = fecha.getYear() - 1;
                            } else
                                numeroTrimestre -= 1;
                        }
                        
                        Double importePOP = objectMaestroPedido.getListaDetallePedido().stream()
                        .filter(p -> "S".equals(p.getEsPOP()) && p.getPorcentajeDescuentoPOP() > 0)
                        .mapToDouble(p -> p.getUnidades() * p.getPrecioUnitarioConImpuestos() * (p.getPorcentajeDescuentoPOP() / 100))
                        .sum();
                                          
                        Resources.logger.info("Insertaremos en Pedidos POP " + importePOP);
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO PEDIDOS_POP (DOCTO_VE_ID, CLIENTE_ID, FOLIO, IMPORTE_POP, ESTADO, ANIO, TRIMESTRE)" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)"
                        );                   
                        preparedStatementObj.setInt(1, idAutoIncremental);
                        preparedStatementObj.setInt(2, objectMaestroPedido.getClienteId());
                        preparedStatementObj.setString(3, serieFolio);
                        preparedStatementObj.setDouble(4, importePOP);
                        preparedStatementObj.setString(5, "PEDIDO");
                        preparedStatementObj.setInt(6, anio);
                        preparedStatementObj.setInt(7, numeroTrimestre);
                        preparedStatementObj.executeUpdate(); 
                                                        
                        preparedStatementObj = conexion.prepareStatement("UPDATE POP_TRIMESTRAL SET ESTATUS_POP = ?, FECHA_BLOQUEO_POP = ? WHERE CLIENTE_ID = ? AND ANIO = ? AND TRIMESTRE = ?");        
                        preparedStatementObj.setString(1, "BLOQUEADO");            
                        preparedStatementObj.setDate(2, utilerias.convertStringToDate(objectMaestroPedido.getFechaPedido()));
                        preparedStatementObj.setInt(3, objectMaestroPedido.getClienteId());
                        preparedStatementObj.setInt(4, anio);
                        preparedStatementObj.setInt(5, numeroTrimestre);
                        preparedStatementObj.executeUpdate(); 
                    }

                    // Cambio para el cliente de AFASY 20-NOV-2020
                    if (configuracionMobil.getComportamientoCaptura().equals(Constants.COTIZACION)) {
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO LIBRES_COT_VE (DOCTO_VE_ID, EMITE_FACTURA)" +
                            "VALUES (?,?)"
                        );
                        preparedStatementObj.setInt(1, idAutoIncremental);
                        preparedStatementObj.setString(2, objectMaestroPedido.getCotizacionEmiteFactura());
                        preparedStatementObj.executeUpdate();                                
                        
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO VIT_COTIZ_PROCESADAS (DOCTO_VE_ID, ESTATUS)" +
                            "VALUES (?,?)"
                        );
                        preparedStatementObj.setInt(1, idAutoIncremental);
                        preparedStatementObj.setString(2, "P");
                        preparedStatementObj.executeUpdate();                                                        
                    }                                
                    // REFACTOR
                    //==============================================================================
                    // ========== Se inserta el detalle del pedido en MAEDTMA02 ====================
                    //==============================================================================
                           
                    JsonElement json = new JsonParser().parse(new Gson().toJson(objectMaestroPedido.getListaDetallePedido()));
                    JsonArray array = json.getAsJsonArray();
                    Iterator iterator2 = array.iterator();
                    List<DetallePedido> details = new ArrayList<DetallePedido>();

                    int consecutivo = 0;
                    while (iterator2.hasNext()) {                                
                        consecutivo++;
                        JsonElement json2 = (JsonElement) iterator2.next();                    
                        Type collectionType2 = new TypeToken<DetallePedido>() {}.getType();
                        DetallePedido detallePedido = gson.fromJson(json2, collectionType2);

                        Resources.logger.info(consecutivo + " Detalle del pedido");

                        preparedStatementObj = conexion.prepareStatement(
                        "INSERT INTO DOCTOS_VE_DET (DOCTO_VE_DET_ID, DOCTO_VE_ID, CLAVE_ARTICULO, ARTICULO_ID, "
                        + "UNIDADES, UNIDADES_COMPROM, "
                        + "UNIDADES_SURT_DEV, UNIDADES_A_SURTIR, "
                        + "PRECIO_UNITARIO, PCTJE_DSCTO, DSCTO_ART, PCTJE_DSCTO_CLI, "
                        + "DSCTO_EXTRA, PCTJE_DSCTO_VOL, PCTJE_DSCTO_PROMO, PRECIO_TOTAL_NETO, "
                        + "PCTJE_COMIS, ROL, POSICION)" +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                        );
                        //preparedStatementObj.setInt(1, -1);//REFACTOR JUNIO 2022
                        PreparedStatement preparedStatementDetalle = conexion.prepareStatement(
                            "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                        );
                        ResultSet resultSetDetalle = preparedStatementDetalle.executeQuery();
                        int idAutoIncrementalDetalle= 0;                
                        while (resultSetDetalle.next()) {
                            idAutoIncrementalDetalle = resultSetDetalle.getInt("ID");                    
                        } 
                        preparedStatementObj.setInt(1, idAutoIncrementalDetalle);
                        preparedStatementObj.setInt(2, idAutoIncremental);
                        preparedStatementObj.setString(3, detallePedido.getClave_articulo());
                        preparedStatementObj.setInt(4, detallePedido.getArticulo_id());
                        preparedStatementObj.setDouble(5, detallePedido.getUnidades());
                        preparedStatementObj.setDouble(6, 0.00);                
                        preparedStatementObj.setDouble(7, 0.00);
                        preparedStatementObj.setDouble(8, 0.00);                                                                
                        /**********************************************************/                                           
                        Double precioUnitarioSinImpuesto = detallePedido.getPrecio_unitario_sin_impuestos();
                        preparedStatementObj.setDouble(9, precioUnitarioSinImpuesto);//PRECIO SIN IVA                    
                        Double porcentajeDescuentoTotal = porcentajeDescuentoTotalXArticulo(detallePedido, objectMaestroPedido.getEsPOP());
                        preparedStatementObj.setDouble(10, porcentajeDescuentoTotal);
                        preparedStatementObj.setDouble(11, (precioUnitarioSinImpuesto * detallePedido.getUnidades()) * (porcentajeDescuentoTotal / 100)); //VALOR EN MONEDA DEL DESCUENTO   100-10%=10.00
                        preparedStatementObj.setDouble(12, !objectMaestroPedido.getEsPOP() ? detallePedido.getPorcentaje_descuento_articulo_cliente() : 0.00);
                        preparedStatementObj.setDouble(13, 0.00);
                        
                        
                        preparedStatementObj.setDouble(14, (detallePedido.getTipo_politica().equals("VOLUMEN") && !objectMaestroPedido.getEsPOP()) ? detallePedido.getPorcentaje_descuento_promocion_volumen() : 0.00);                                          
                        preparedStatementObj.setDouble(15, (detallePedido.getTipo_politica().equals("PROMOCION") && !objectMaestroPedido.getEsPOP()) ? detallePedido.getPorcentaje_descuento_promocion_volumen() : 0.00);                
                        /**********************************************************/
                        preparedStatementObj.setDouble(16, (detallePedido.getPrecio_unitario_sin_impuestos() - detallePedido.getPrecio_unitario_con_descuento_sin_impuestos()) * detallePedido.getUnidades());                 //TODOS LOS VALORES SON SIN IMPUESTOS
                        preparedStatementObj.setDouble(17, 0.00);
                        preparedStatementObj.setString(18, detallePedido.getEs_juego().equals("S") ? "J" : "N");
                        preparedStatementObj.setInt(19, consecutivo);
                        preparedStatementObj.executeUpdate();  
                        
                        //PARA CUANDO EL ARTICULO SEA JUEGO, METER EL DETALLE DEL JUEGO
                        if (detallePedido.getEs_juego().equals("S"))
                            creteDetalleJuego(idAutoIncremental, detallePedido.getArticulo_id());
                    }

                    //---------- SE GRABA EL MOVIMIENTO CON EL DEL MOBIL
                    createPedidoGuardado(objectMaestroPedido.getUuid(), idAutoIncremental, serieFolio);
                }                                                               
                // Este objeto es para agregar el id que se generp en ANDROID y retornarlo en una lista
                PedidoGrabado pedidoGrabadoObject = new PedidoGrabado();
                pedidoGrabadoObject.setId(objectMaestroPedido.getId());
                pedidoGrabadoObject.setNummov(0);                
                pedidoGrabadoObject.setFolio(serieFolio);                
                listaPedidosGrabados.add(pedidoGrabadoObject); 
                //}    
                
                conexion.commit();
            }
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaPedidosGrabados, "Pedidos grabados correctamente");          
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en la cabecera del pedido: " + exception.getMessage());       
            conexion.rollback();
            exception.printStackTrace();  
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al procesar los pedidos " + exception.getMessage());
        }
    }

    /*
     public ResponseRequest createPedidos(String jsonPedidos) throws SQLException {
        Resources.logger.info("Se ha llamado al recurso REST mediante una peticion POST (createPedidos)");
        //Resources.logger.info("Lista de pedidos: " + jsonPedidos);
        ResponseRequest responseRequest = new ResponseRequest();
        ArrayList<PedidoGrabado> listaPedidosGrabados = new ArrayList<PedidoGrabado>();
      
        configuracionMicrosip();
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        JsonElement jsonElement = new JsonParser().parse(jsonPedidos);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        Iterator iterator = jsonArray.iterator();  
        
        try {
            Utilerias utilerias = new Utilerias();
            while (iterator.hasNext()) {
                JsonElement JsonElementTmp = (JsonElement) iterator.next();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<MaestroPedido>() {}.getType();
                MaestroPedido objectMaestroPedido = gson.fromJson(JsonElementTmp, collectionType);
                
                //Resources.logger.info("Json item: " + gson.toJson(objectMaeMovCa02));
                
                conexion.setAutoCommit(false);
                
                //---------- Verificar que el pedido no haya sido guardado
                PedidoExistente pedidoExistente = existePedidoGuardado(objectMaestroPedido.getUuid());                
                String serieFolio = pedidoExistente.getFolio();
                if (pedidoExistente.getNumeroMovimiento() == 0) {
                    preparedStatement = conexion.prepareStatement(
                        "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                    );
                    resultSet = preparedStatement.executeQuery();
                    int idAutoIncremental= 0;                
                    while (resultSet.next()) {
                        idAutoIncremental = resultSet.getInt("ID");                    
                    } 
                    Resources.logger.info("idAutoIncremental: " + idAutoIncremental);

                    preparedStatement = conexion.prepareStatement(
                        "SELECT SERIE, MAX(FOLIO) + 1 AS FOLIO FROM VENDEDORES_SERIES_FOLIOS " +
                        " WHERE VENDEDOR_ID = " + objectMaestroPedido.getVendedorId() +
                        " GROUP BY 1"
                    );
                    resultSet = preparedStatement.executeQuery();
                    int consecutivoFolio= 0;
                    String serie = "";
                    while (resultSet.next()) {
                        consecutivoFolio = resultSet.getInt("FOLIO");
                        serie = resultSet.getString("SERIE");
                    }   

                    Resources.logger.info("consecutivoFolio: " + consecutivoFolio + " serie: " + serie);

                    preparedStatement = conexion.prepareStatement(
                        "SELECT COND_PAGO_ID FROM CLIENTES WHERE CLIENTE_ID = " + objectMaestroPedido.getClienteId()
                    );
                    resultSet = preparedStatement.executeQuery();
                    int condicionPagoId= 0;                
                    while (resultSet.next()) {
                        condicionPagoId = resultSet.getInt("COND_PAGO_ID");                    
                    }                              

                    Resources.logger.info("condicionPagoId: " + condicionPagoId);

                    int direccionCliente = 0;
                    if ((objectMaestroPedido.getDireccionConsignatarioId()!= 0) && (objectMaestroPedido.getDireccionConsignatarioEnvioId()!= 0)) //CAMBIO FACTURA Y ENVIO                                        
                        direccionCliente = objectMaestroPedido.getDireccionConsignatarioEnvioId();
                    else if ((objectMaestroPedido.getDireccionConsignatarioId()== 0) && (objectMaestroPedido.getDireccionConsignatarioEnvioId()!= 0))  //CAMBIO ENVIO NADA MAS
                        direccionCliente = objectMaestroPedido.getDireccionConsignatarioEnvioId();
                    else if ((objectMaestroPedido.getDireccionConsignatarioId()!= 0) && (objectMaestroPedido.getDireccionConsignatarioEnvioId()== 0))  //CAMBIO FACTURA NADA MAS
                        direccionCliente = objectMaestroPedido.getDireccionConsignatarioId();
                    else //NO CAMBIO NADA 
                        direccionCliente = objectMaestroPedido.getDireccionClienteId();
                    
                    preparedStatement = conexion.prepareStatement(                            
                        "SELECT VIA_EMBARQUE_ID FROM DIRS_CLIENTES" +
                        " WHERE DIR_CLI_ID = " + direccionCliente                             
                        //" WHERE DIR_CLI_ID = " + objectMaestroPedido.getDireccionClienteId()
                    );
                    resultSet = preparedStatement.executeQuery();
                    int viaEmbarqueId= 0;                
                    while (resultSet.next()) {
                        viaEmbarqueId = resultSet.getInt("VIA_EMBARQUE_ID");                    
                    }
                    Resources.logger.info("viaEmbarqueId: " + viaEmbarqueId);

                    Resources.logger.info("VAMOS A GRABAR CABECERA");

                    String querySegunVersion = "";
                    if (configuracionMobil.getMicrosip2020() == 0) {
                        querySegunVersion = "INSERT INTO DOCTOS_VE (DOCTO_VE_ID, TIPO_DOCTO, SUBTIPO_DOCTO, FOLIO, FECHA, HORA, CLAVE_CLIENTE, CLIENTE_ID, "
                            + "DIR_CLI_ID, DIR_CONSIG_ID, ALMACEN_ID, "
                            + "MONEDA_ID, TIPO_CAMBIO, TIPO_DSCTO, "
                            + "DSCTO_PCTJE, DSCTO_IMPORTE, ESTATUS, APLICADO, FECHA_VIGENCIA_ENTREGA, "
                            + "DESCRIPCION, "                        
                            + "IMPORTE_NETO, FLETES, OTROS_CARGOS, TOTAL_IMPUESTOS, TOTAL_RETENCIONES, TOTAL_ANTICIPOS, "
                            + "PESO_EMBARQUE, FORMA_EMITIDA, CONTABILIZADO, ACREDITAR_CXC, SISTEMA_ORIGEN, COND_PAGO_ID, "                        
                            + "PCTJE_DSCTO_PPAG, VENDEDOR_ID, PCTJE_COMIS, VIA_EMBARQUE_ID, IMPORTE_COBRO,"
                            + "USUARIO_CREADOR, ES_CFD, ENVIADO, "
                            + "CFD_ENVIO_ESPECIAL, CFDI_CERTIFICADO, FECHA_HORA_CREACION, "
                            + "CARGAR_SUN)" +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    } else if (configuracionMobil.getMicrosip2020() != 0) {
                        querySegunVersion = "INSERT INTO DOCTOS_VE (DOCTO_VE_ID, TIPO_DOCTO, SUBTIPO_DOCTO, FOLIO, FECHA, HORA, CLAVE_CLIENTE, CLIENTE_ID, "
                            + "DIR_CLI_ID, DIR_CONSIG_ID, ALMACEN_ID, "
                            + "MONEDA_ID, TIPO_CAMBIO, TIPO_DSCTO, "
                            + "DSCTO_PCTJE, DSCTO_IMPORTE, ESTATUS, APLICADO, FECHA_VIGENCIA_ENTREGA, "
                            + "DESCRIPCION, "                        
                            + "IMPORTE_NETO, FLETES, OTROS_CARGOS, TOTAL_IMPUESTOS, TOTAL_RETENCIONES, TOTAL_ANTICIPOS, "
                            + "PESO_EMBARQUE, FORMA_EMITIDA, CONTABILIZADO, ACREDITAR_CXC, SISTEMA_ORIGEN, COND_PAGO_ID, "                        
                            + "PCTJE_DSCTO_PPAG, VENDEDOR_ID, PCTJE_COMIS, VIA_EMBARQUE_ID, IMPORTE_COBRO,"
                            + "USUARIO_CREADOR, ES_CFD, ENVIADO, "
                            + "CFD_ENVIO_ESPECIAL, CFDI_CERTIFICADO, FECHA_HORA_CREACION, "
                            + "CARGAR_SUN, SUCURSAL_ID)" +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    }

                    PreparedStatement preparedStatementObj = conexion.prepareStatement(querySegunVersion);                 
                    preparedStatementObj.setInt(1, idAutoIncremental);

                    if (configuracionMobil.getComportamientoCaptura().equals(Constants.COTIZACION))
                        preparedStatementObj.setString(2, Constants.COTIZACION);
                    else
                        preparedStatementObj.setString(2, Constants.PEDIDO);

                    preparedStatementObj.setString(3, "N");                
                    int posiciones =  9 - serie.length();//Son 9 la longitud del campo FOLIO
                    serieFolio = serie + StringUtils.leftPad(String.valueOf(consecutivoFolio), posiciones, "0");
                    preparedStatementObj.setString(4, serieFolio);                    
                    preparedStatementObj.setDate(5, utilerias.convertStringToDate(objectMaestroPedido.getFechaPedido()));
                    preparedStatementObj.setTime(6, utilerias.convertStringToTime(objectMaestroPedido.getHoraPedido()));                      
                    preparedStatementObj.setString(7, objectMaestroPedido.getClaveCliente());
                    preparedStatementObj.setInt(8, objectMaestroPedido.getClienteId());                                                                   
                    
                    if (objectMaestroPedido.getDireccionConsignatarioId()!= 0)
                        preparedStatementObj.setInt(9, objectMaestroPedido.getDireccionConsignatarioId());
                    else
                        preparedStatementObj.setInt(9, objectMaestroPedido.getDireccionClienteId());                                                                                                           
                   
                    //REFACTOR CONSIGNATARIOS ENVIO
                    if (objectMaestroPedido.getDireccionConsignatarioEnvioId()!= 0)                         
                        preparedStatementObj.setInt(10, objectMaestroPedido.getDireccionConsignatarioEnvioId());
                    else                        
                        preparedStatementObj.setInt(10, objectMaestroPedido.getDireccionClienteId());                                                                                                                                
                    
                    preparedStatementObj.setInt(11, objectMaestroPedido.getAlmacenId());                
                    preparedStatementObj.setInt(12, 1);
                    preparedStatementObj.setDouble(13, 1.00);
                    preparedStatementObj.setString(14, "P");                                  
                    preparedStatementObj.setDouble(15, 0.00);                
                    preparedStatementObj.setDouble(16, 0.00);                
                    preparedStatementObj.setString(17, "P");
                    preparedStatementObj.setString(18, "S");
                    preparedStatementObj.setDate(19, utilerias.convertStringToDate(objectMaestroPedido.getFechaPedido()));//PUEDE SER LA DE HOY                
                    preparedStatementObj.setString(20, objectMaestroPedido.getObservaciones());                               
                    preparedStatementObj.setDouble(21, objectMaestroPedido.getImporteNeto());//CALCULARLO EN LA APP TOTAL NETO
                    preparedStatementObj.setDouble(22, 0.00);
                    preparedStatementObj.setDouble(23, 0.00);
                    preparedStatementObj.setDouble(24, objectMaestroPedido.getTotalImpuestos());
                    preparedStatementObj.setDouble(25, 0.00);
                    preparedStatementObj.setDouble(26, 0.00);
                    preparedStatementObj.setDouble(27, 0.00);
                    preparedStatementObj.setString(28, "N");
                    preparedStatementObj.setString(29, "N");
                    preparedStatementObj.setString(30, "N");
                    preparedStatementObj.setString(31, "VE");
                    preparedStatementObj.setInt(32, condicionPagoId);
                    preparedStatementObj.setDouble(33, 0.00);
                    preparedStatementObj.setInt(34, objectMaestroPedido.getVendedorId());
                    preparedStatementObj.setDouble(35, 0.00);
                    preparedStatementObj.setInt(36, viaEmbarqueId);
                    preparedStatementObj.setDouble(37, 0.00);                                             
                    preparedStatementObj.setString(38, Constants.SYSDBA);
                    preparedStatementObj.setString(39, "N");
                    preparedStatementObj.setString(40, "N");                
                    preparedStatementObj.setString(41, "N");
                    preparedStatementObj.setString(42, "N");                
                    java.util.Date today = new java.util.Date();
                    Timestamp xxx = new Timestamp(System.currentTimeMillis());
                    
                    preparedStatementObj.setTimestamp(43, new java.sql.Timestamp(today.getTime()));
                    preparedStatementObj.setString(44, "S");

                    if (configuracionMobil.getMicrosip2020() != 0) {
                        if (configuracionMobil.getOperaSucursalAlmacen() == 1) {
                            preparedStatement = conexion.prepareStatement(
                                "SELECT SC.SUCURSAL_ID FROM SUCURSALES_CATALOGOS SC " +
                                "INNER JOIN SUCURSALES_CATALOGOS_DET D ON SC.SUCURSAL_CATALOGO_ID = D.SUCURSAL_CATALOGO_ID " +
                                "INNER JOIN CONFIGURACION_ALMACENES CA ON CA.ALMACEN_ID = D.ELEMENTO_ID " +
                                "WHERE SC.NOM_TABLA = 'ALMACENES' AND CA.ALMACEN_ID = " +  objectMaestroPedido.getAlmacenId()
                            );
                            resultSet = preparedStatement.executeQuery();
                            int sucursalId= 0;                
                            while (resultSet.next()) {
                                sucursalId = resultSet.getInt("SUCURSAL_ID");                    
                            }
                            Resources.logger.info("CALCULADA sucursalId: " + sucursalId);
                            preparedStatementObj.setInt(45, sucursalId);
                        } else {
                            Resources.logger.info("CONFIGURACION MOBIL sucursalId: " + configuracionMobil.getSucursalId());
                            preparedStatementObj.setInt(45, configuracionMobil.getSucursalId());
                        }
                    }                                 

                    preparedStatementObj.executeUpdate();
                    Resources.logger.info("Grabando en la cabecera");

                    // REFACTOR
                    preparedStatement = conexion.prepareStatement(
                            "UPDATE VENDEDORES_SERIES_FOLIOS SET " +
                            " FOLIO = " + consecutivoFolio + 
                            " WHERE SERIE = '" + serie + "'"
                    );                             
                    preparedStatement.executeUpdate();
                    Resources.logger.info("Actualizando la serie: " + serie + " con el folio: " + consecutivoFolio);

                    // Cambio para el cliente de AFASY 20-NOV-2020
                    if (configuracionMobil.getComportamientoCaptura().equals(Constants.COTIZACION)) {
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO LIBRES_COT_VE (DOCTO_VE_ID, EMITE_FACTURA)" +
                            "VALUES (?,?)"
                        );
                        preparedStatementObj.setInt(1, idAutoIncremental);
                        preparedStatementObj.setString(2, objectMaestroPedido.getCotizacionEmiteFactura());
                        preparedStatementObj.executeUpdate();                                
                        
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO VIT_COTIZ_PROCESADAS (DOCTO_VE_ID, ESTATUS)" +
                            "VALUES (?,?)"
                        );
                        preparedStatementObj.setInt(1, idAutoIncremental);
                        preparedStatementObj.setString(2, "P");
                        preparedStatementObj.executeUpdate();                                                        
                    }                                
                    // REFACTOR
                    //==============================================================================
                    // ========== Se inserta el detalle del pedido en MAEDTMA02 ====================
                    //==============================================================================
                           
                    JsonElement json = new JsonParser().parse(new Gson().toJson(objectMaestroPedido.getListaDetallePedido()));
                    JsonArray array = json.getAsJsonArray();
                    Iterator iterator2 = array.iterator();
                    List<DetallePedido> details = new ArrayList<DetallePedido>();

                    int consecutivo = 0;
                    while (iterator2.hasNext()) {                                
                        consecutivo++;
                        JsonElement json2 = (JsonElement) iterator2.next();                    
                        Type collectionType2 = new TypeToken<DetallePedido>() {}.getType();
                        DetallePedido detallePedido = gson.fromJson(json2, collectionType2);

                        Resources.logger.info(consecutivo + " Detalle del pedido");

                        preparedStatementObj = conexion.prepareStatement(
                        "INSERT INTO DOCTOS_VE_DET (DOCTO_VE_DET_ID, DOCTO_VE_ID, CLAVE_ARTICULO, ARTICULO_ID, "
                        + "UNIDADES, UNIDADES_COMPROM, "
                        + "UNIDADES_SURT_DEV, UNIDADES_A_SURTIR, "
                        + "PRECIO_UNITARIO, PCTJE_DSCTO, DSCTO_ART, PCTJE_DSCTO_CLI, "
                        + "DSCTO_EXTRA, PCTJE_DSCTO_VOL, PCTJE_DSCTO_PROMO, PRECIO_TOTAL_NETO, "
                        + "PCTJE_COMIS, ROL, POSICION)" +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                        );
                        //preparedStatementObj.setInt(1, -1);//REFACTOR JUNIO 2022
                        PreparedStatement preparedStatementDetalle = conexion.prepareStatement(
                            "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                        );
                        ResultSet resultSetDetalle = preparedStatementDetalle.executeQuery();
                        int idAutoIncrementalDetalle= 0;                
                        while (resultSetDetalle.next()) {
                            idAutoIncrementalDetalle = resultSetDetalle.getInt("ID");                    
                        } 
                        preparedStatementObj.setInt(1, idAutoIncrementalDetalle);
                        preparedStatementObj.setInt(2, idAutoIncremental);
                        preparedStatementObj.setString(3, detallePedido.getClave_articulo());
                        preparedStatementObj.setInt(4, detallePedido.getArticulo_id());
                        preparedStatementObj.setDouble(5, detallePedido.getUnidades());
                        preparedStatementObj.setDouble(6, 0.00);                
                        preparedStatementObj.setDouble(7, 0.00);
                        preparedStatementObj.setDouble(8, 0.00);                                                                
                        
                        Double precioUnitarioSinImpuesto = detallePedido.getPrecio_unitario_sin_impuestos();
                        preparedStatementObj.setDouble(9, precioUnitarioSinImpuesto);//PRECIO SIN IVA                    
                        Double porcentajeDescuentoTotal = porcentajeDescuentoTotalXArticulo(detallePedido);
                        preparedStatementObj.setDouble(10, porcentajeDescuentoTotal);
                        preparedStatementObj.setDouble(11, (precioUnitarioSinImpuesto * detallePedido.getUnidades()) * (porcentajeDescuentoTotal / 100)); //VALOR EN MONEDA DEL DESCUENTO   100-10%=10.00
                        preparedStatementObj.setDouble(12, detallePedido.getPorcentaje_descuento_articulo_cliente());                                          
                        preparedStatementObj.setDouble(13, 0.00);
                        preparedStatementObj.setDouble(14, detallePedido.getTipo_politica().equals("VOLUMEN") ? detallePedido.getPorcentaje_descuento_promocion_volumen() : 0.00);                                          
                        preparedStatementObj.setDouble(15, detallePedido.getTipo_politica().equals("PROMOCION") ? detallePedido.getPorcentaje_descuento_promocion_volumen() : 0.00);                
                        
                        preparedStatementObj.setDouble(16, (detallePedido.getPrecio_unitario_sin_impuestos() - detallePedido.getPrecio_unitario_con_descuento_sin_impuestos()) * detallePedido.getUnidades());                 //TODOS LOS VALORES SON SIN IMPUESTOS
                        preparedStatementObj.setDouble(17, 0.00);
                        preparedStatementObj.setString(18, detallePedido.getEs_juego().equals("S") ? "J" : "N");
                        preparedStatementObj.setInt(19, consecutivo);
                        preparedStatementObj.executeUpdate();  
                        
                        //PARA CUANDO EL ARTICULO SEA JUEGO, METER EL DETALLE DEL JUEGO
                        if (detallePedido.getEs_juego().equals("S"))
                            creteDetalleJuego(idAutoIncremental, detallePedido.getArticulo_id());
                    }

                    //---------- SE GRABA EL MOVIMIENTO CON EL DEL MOBIL
                    createPedidoGuardado(objectMaestroPedido.getUuid(), idAutoIncremental, serieFolio);
                }                                                               
                // Este objeto es para agregar el id que se generp en ANDROID y retornarlo en una lista
                PedidoGrabado pedidoGrabadoObject = new PedidoGrabado();
                pedidoGrabadoObject.setId(objectMaestroPedido.getId());
                pedidoGrabadoObject.setNummov(0);                
                pedidoGrabadoObject.setFolio(serieFolio);                
                listaPedidosGrabados.add(pedidoGrabadoObject); 
                //}    
                
                conexion.commit();
            }
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaPedidosGrabados, "Pedidos grabados correctamente");          
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en la cabecera del pedido: " + exception.getMessage());       
            conexion.rollback();
            exception.printStackTrace();  
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al procesar los pedidos " + exception.getMessage());
        }
    }
     */
    private Double porcentajeDescuentoTotalXArticulo(DetallePedido detallePedido, Boolean esPedidoPOP) {
        Double porcentajeDescuentoTotal = 0.00;
        if ((esPedidoPOP) && (detallePedido.getEsPOP().equals("S")) && (detallePedido.getPorcentajeDescuentoPOP() != 0.00))
            porcentajeDescuentoTotal = detallePedido.getPorcentajeDescuentoPOP();
        else if (detallePedido.getPorcentaje_descuento_promocion_volumen() != 0) { //Trae descuento por promocion o por volumen
            Double descuentoPromocionVolumen = 1 - (detallePedido.getPorcentaje_descuento_promocion_volumen() / 100);
            Double descuentoArticuloCliente = 1 - (detallePedido.getPorcentaje_descuento_articulo_cliente() / 100);
            porcentajeDescuentoTotal = (1 - (descuentoArticuloCliente * descuentoPromocionVolumen)) * 100;
        } else             
            porcentajeDescuentoTotal = detallePedido.getPorcentaje_descuento_articulo_cliente();
        
        return porcentajeDescuentoTotal;
    } 
    
    private Boolean creteDetalleJuego(int doctoVeId, int articuloIdJuego) {
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT DOCTO_VE_DET_ID FROM DOCTOS_VE_DET WHERE DOCTO_VE_ID = ? AND ARTICULO_ID = ? "
            );
            preparedStatement.setInt(1, doctoVeId);            
            preparedStatement.setInt(2, articuloIdJuego);   
            ResultSet resultSet = preparedStatement.executeQuery();    
            int DOCTO_VE_DET_ID = 0;
            while (resultSet.next()) {
                DOCTO_VE_DET_ID = resultSet.getInt("DOCTO_VE_DET_ID");                    
            }
            
            preparedStatement = conexion.prepareStatement(
                "EXECUTE PROCEDURE ALTA_COMPONENTES_VE(?)"
            );        
            preparedStatement.setInt(1, DOCTO_VE_DET_ID);       
            preparedStatement.executeQuery();
            
            
            /*ArrayList<ArticuloJuego> listaArticuloJuego = new ArrayList<ArticuloJuego>();
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT CA.CLAVE_ARTICULO, CA.ARTICULO_ID, JD.UNIDADES " + 
                "FROM JUEGOS_DET JD " +
                "INNER JOIN CLAVES_ARTICULOS CA ON CA.CLAVE_ARTICULO_ID = JD.CLAVE_ARTICULO_ID " + 
                "WHERE JD.ARTICULO_ID = ?"
            );
            preparedStatement.setInt(1, articuloIdJuego);            
            ResultSet resultSet = preparedStatement.executeQuery();                           
            while (resultSet.next()) {
                ArticuloJuego articuloJuego = new ArticuloJuego();
                articuloJuego.setClaveArticulo(resultSet.getString("CLAVE_ARTICULO"));    
                articuloJuego.setArticuloId(resultSet.getInt("ARTICULO_ID"));    
                articuloJuego.setUnidades(resultSet.getDouble("UNIDADES"));    
                articuloJuego.setRol("C");    
                articuloJuego.setPosicion(0);    
                listaArticuloJuego.add(articuloJuego);
            }
            
            for (ArticuloJuego articuloJuego : listaArticuloJuego) {                                
                Resources.logger.info(" Detalle del pedido");

                preparedStatement = conexion.prepareStatement(
                    "INSERT INTO DOCTOS_VE_DET (DOCTO_VE_DET_ID, DOCTO_VE_ID, CLAVE_ARTICULO, ARTICULO_ID, "
                    + "UNIDADES, UNIDADES_COMPROM, "
                    + "UNIDADES_SURT_DEV, UNIDADES_A_SURTIR, "
                    + "PRECIO_UNITARIO, PCTJE_DSCTO, DSCTO_ART, PCTJE_DSCTO_CLI, "
                    + "DSCTO_EXTRA, PCTJE_DSCTO_VOL, PCTJE_DSCTO_PROMO, PRECIO_TOTAL_NETO, "
                    + "PCTJE_COMIS, ROL, POSICION)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                );
                preparedStatement.setInt(1, -1);
                preparedStatement.setInt(2, doctoVeId);
                preparedStatement.setString(3, articuloJuego.getClaveArticulo());
                preparedStatement.setInt(4, articuloJuego.getArticuloId());
                preparedStatement.setDouble(5, articuloJuego.getUnidades());
                preparedStatement.setDouble(6, 0.00);
                preparedStatement.setDouble(7, 0.00);
                preparedStatement.setDouble(8, 0.00);                        
                preparedStatement.setDouble(9, 0.00);                
                preparedStatement.setDouble(10, 0.00);
                preparedStatement.setDouble(11, 0.00);
                preparedStatement.setDouble(12, 0.00);
                preparedStatement.setDouble(13, 0.00);
                preparedStatement.setDouble(14, 0.00);
                preparedStatement.setDouble(15, 0.00);
               
                preparedStatement.setDouble(16, 0.00);
                preparedStatement.setDouble(17, 0.00);
                preparedStatement.setString(18, articuloJuego.getRol());
                preparedStatement.setInt(19, 0);
                preparedStatement.executeUpdate();
            }*/
            return true;
        } catch (SQLException exception) {
            Resources.logger.error("SUCEDIO UNA EXEPCION AL GRABAR EL DETALLE DEL KIT: " + exception.getMessage());   
            return false;
        }
    }
    
    public Boolean createPedidoGuardado(String uuid, int numeroMovimiento, String folio){
        PreparedStatement preparedStatement = null;                
        try{            
            Date fecha = new Date();           
            Utilerias utilerias = new Utilerias();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");                
                
            preparedStatement = conexion.prepareStatement(
                "INSERT INTO PEDIDOS_TRANSMITIDOS (UUID, DOCTO_VE_ID, FECHA, FOLIO) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, uuid);
            preparedStatement.setInt(2, numeroMovimiento);
            preparedStatement.setDate(3, utilerias.convertStringToDate(sdf.format(fecha)));
            preparedStatement.setString(4, folio);
            preparedStatement.executeUpdate();            
            Resources.logger.info("PEDIDOS_TRANSMITIDOS");
            return true;
        }catch(Exception exception){
             Resources.logger.info(exception.getMessage() + " ERRRO EN CREATE PEDIDOS_TRANSMITIDOS");
            return false;
        }        
    }
     
    public PedidoExistente existePedidoGuardado(String uuid) throws SQLException {        
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT UUID, DOCTO_VE_ID, FECHA, FOLIO FROM PEDIDOS_TRANSMITIDOS WHERE UUID = ?"
            );
            preparedStatement.setString(1, uuid);                        
            ResultSet resultSet = preparedStatement.executeQuery();           
            PedidoExistente pedidoExistente = new PedidoExistente();            
            while (resultSet.next()) {                
                pedidoExistente.setUuid(resultSet.getString("UUID"));
                pedidoExistente.setNumeroMovimiento(resultSet.getInt("DOCTO_VE_ID"));
                pedidoExistente.setFecha(resultSet.getDate("FECHA"));                                
                pedidoExistente.setFolio(resultSet.getString("FOLIO"));
            }            
            System.out.println(new Gson().toJson(pedidoExistente));
            return pedidoExistente;
        } catch (SQLException e) {
            System.out.println("sql" + e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    /**************************************************************************/
    
    public ResponseRequest createCobrosXDepositarIndividual (String jsonString) throws SQLException {
        Resources.logger.info("Entrando a crear la cobranza:");
        Resources.logger.info("Estos abonos se reciben crear: " + jsonString);
                
        ResponseRequest responseRequest = new ResponseRequest();
        List<CobroXDepositarEnviado> listaCobroXDepositarEnviados = new ArrayList<>();

        configuracionMicrosip();
     
        Utilerias utilerias = new Utilerias();
        try {            
            Type type = new TypeToken<CobroXDepositar>(){}.getType();
            CobroXDepositar cobroXDepositar = gson.fromJson(jsonString, type);                    
                        
            List<ComplementoXml> listaComplementoXml = new ArrayList<>();                        
                                     
            for (AbonoMaestroEntity abonoMaestroEntity : cobroXDepositar.getListaAbonosParaMicrosip()) {
                    
                conexion.setAutoCommit(false);
                
                ResponseRequest responseRequestItem = createCobroXDepositar(abonoMaestroEntity);
                
                CobroXDepositarEnviado cobroXDepositarEnviado = new CobroXDepositarEnviado();
                cobroXDepositarEnviado.setId(abonoMaestroEntity.getId());
                if (responseRequestItem.getStatus() == ResponseRequest.DataStatus.OK){   
                    cobroXDepositarEnviado.setStatus(ResponseRequest.DataStatus.OK);
                    cobroXDepositarEnviado.setMensaje("");  
                    
                    conexion.commit();
                } else if (responseRequestItem.getStatus() == ResponseRequest.DataStatus.ERROR){            
                    String data = (String)responseRequestItem.getData();
                    
                    cobroXDepositarEnviado.setStatus(ResponseRequest.DataStatus.ERROR);
                    cobroXDepositarEnviado.setMensaje(data);
                    
                    conexion.rollback(); 
                }       
                listaCobroXDepositarEnviados.add(cobroXDepositarEnviado);       
            }
            
            /*List<CobroXDepositarEnviado> listaCobrosXDepositarEnviado = listaCobroXDepositarEnviados.stream()
                    .filter(p -> p.getStatus() == ResponseRequest.DataStatus.ERROR)
                    .collect(Collectors.toList()); */          
            
            //if (listaCobrosXDepositarEnviado.size() == 0) {
                if (configuracionMobil.getControlaSerieFolioCXC() == 1) {                
                    PreparedStatement preparedStatement = conexion.prepareStatement(
                        "UPDATE SERIES_FOLIOS_CXC SET FOLIO = ?, SERIE = ? WHERE COBRADOR_ID = ?"
                    );
                    preparedStatement.setInt(1, cobroXDepositar.getSerieFolioCXC().getFolio());
                    preparedStatement.setString(2, cobroXDepositar.getSerieFolioCXC().getSerie());
                    preparedStatement.setInt(3, cobroXDepositar.getSerieFolioCXC().getCobradorId());
                    preparedStatement.executeUpdate();            
                }

                
            /*} else {
                listaCobroXDepositarEnviados.removeAll(listaCobroXDepositarEnviados.stream()
                    .filter(p -> p.getStatus() == ResponseRequest.DataStatus.OK)
                    .collect(Collectors.toList()));
                
            }*/
            
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaCobroXDepositarEnviados, "Cobros x depositar enviados al servidor");  
        }catch(JsonSyntaxException | SQLException exception){
            conexion.rollback();
            
            Resources.logger.error("Excepcion en createCobrosXDepositar: " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al procesar Cobros x depositar " + exception.getMessage());         
        }
    }
    
    private ResponseRequest createCobroXDepositar(AbonoMaestroEntity abonoMaestroEntity) {
        String errorMessage = "CTE: " + abonoMaestroEntity.getClaveCliente();
        
        Utilerias utilerias = new Utilerias();
        ResponseRequest responseRequest = new ResponseRequest();
                               
        ComplementoXml complementoXml = new ComplementoXml();                
        
        PreparedStatement preparedStatement = null; 
        ResultSet resultSet = null;
        int idAutoIncremental= 0; 
        int folioUltimo = 0;
        int LUGAR_EXPEDICION_ID = 0;
        
        try {/**************GENERO EL ID QUE TOMARA DOCTOS_CC***************/
            preparedStatement = conexion.prepareStatement(
                "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
            );
            resultSet = preparedStatement.executeQuery();                         
            while (resultSet.next()) {
                idAutoIncremental = resultSet.getInt("ID");                    
            }            
        } catch(SQLException exception){
            Resources.logger.error(errorMessage + " EN GEN_ID(ID_DOCTOS,1)" + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, errorMessage + " EN GEN_ID(ID_DOCTOS,1)" + exception.getMessage(), exception.getMessage());           
        }       
                
        try {/**********GENERO EL folio ÚLTIMO QUE TOMARA DOCTOS_CC*********/            
            preparedStatement = conexion.prepareStatement(
                "SELECT GEN_ID(ID_FOLIO_TEMP,1)AS ID FROM RDB$DATABASE"
            );
            resultSet = preparedStatement.executeQuery();            
            while (resultSet.next()) {
                folioUltimo = resultSet.getInt("ID");
            }            
        } catch(SQLException exception){
            Resources.logger.error(errorMessage + " EN GEN_ID(ID_FOLIO_TEMP,1)" + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, errorMessage + " EN GEN_ID(ID_FOLIO_TEMP,1)" + exception.getMessage(), exception.getMessage());      
        }
        
        try {/*********************LUGAR_EXPEDICION*************************/
            preparedStatement = conexion.prepareStatement(
                "SELECT LUGAR_EXPEDICION_ID FROM SUCURSALES WHERE SUCURSAL_ID = " + configuracionMobil.getSucursalId()
            );
            resultSet = preparedStatement.executeQuery();            
            while (resultSet.next()) {
                LUGAR_EXPEDICION_ID = resultSet.getInt("LUGAR_EXPEDICION_ID");
            }
        } catch(SQLException exception){
            Resources.logger.error(errorMessage + " EN SELECT LUGAR_EXPEDICION_ID" + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, errorMessage + " EN SELECT LUGAR_EXPEDICION_ID" + exception.getMessage(), exception.getMessage());            
        }
        
        PreparedStatement preparedStatementObj = null;
        try {
            String queryDoctosCC =            
                "INSERT INTO DOCTOS_CC(DOCTO_CC_ID, CONCEPTO_CC_ID, FOLIO, NATURALEZA_CONCEPTO, FECHA, HORA, FECHA_HORA_PAGO, CLAVE_CLIENTE, " +
                "IMPORTE_COBRO, CLIENTE_ID, TIPO_CAMBIO, CANCELADO, APLICADO, DESCRIPCION, COBRADOR_ID, FORMA_EMITIDA," +
                "CONTABILIZADO, CONTABILIZADO_GYP, COND_PAGO_ID, SISTEMA_ORIGEN, ESTATUS, ESTATUS_ANT, ES_CFD, TIENE_ANTICIPO," +
                "MODALIDAD_FACTURACION, ENVIADO, FECHA_HORA_ENVIO, CFDI_CERTIFICADO, INTEG_BA, CONTABILIZADO_BA, LUGAR_EXPEDICION_ID, FECHA_APLICACION, SUCURSAL_ID)" +
                "VALUES(";       
         
            queryDoctosCC = queryDoctosCC + idAutoIncremental + ", ";
            queryDoctosCC = queryDoctosCC + configuracionMobil.getConceptoCCId() + ", ";
            queryDoctosCC = queryDoctosCC + "'Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0") + "', ";
            queryDoctosCC = queryDoctosCC + "'R', ";
            queryDoctosCC = queryDoctosCC + "'" + abonoMaestroEntity.getFechaAbono() + "', ";
            queryDoctosCC = queryDoctosCC + "'" + abonoMaestroEntity.getHoraAbono() + "', ";
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String fechaCRPRaw = abonoMaestroEntity.getFechaCRP(); // "1786838400000"

            String fechaFormateada = null;
            if (fechaCRPRaw != null && !fechaCRPRaw.trim().isEmpty()) {
                long millis = Long.parseLong(fechaCRPRaw);
                Date fecha = new Date(millis);
                fechaFormateada = sdf.format(fecha);
            }

            String fechaCRP = (fechaFormateada == null)
                                ? "NULL, "
                                : "'" + fechaFormateada + "', ";

            queryDoctosCC = queryDoctosCC + fechaCRP;
            
            queryDoctosCC = queryDoctosCC + "'" + abonoMaestroEntity.getClaveCliente() + "', ";
            queryDoctosCC = queryDoctosCC + "0.00, ";
            queryDoctosCC = queryDoctosCC + abonoMaestroEntity.getClienteId() + ", ";
            queryDoctosCC = queryDoctosCC + "1.00, ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "'" + abonoMaestroEntity.getDescripcion() + "', ";
            queryDoctosCC = queryDoctosCC + abonoMaestroEntity.getCobradorId() + ", ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + configuracionMobil.getCondicionPagoId() + ", ";
            queryDoctosCC = queryDoctosCC + "'CC', ";
            queryDoctosCC = queryDoctosCC + "'P', ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "'PREIMP', ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "CURRENT_TIMESTAMP, ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + LUGAR_EXPEDICION_ID + ", ";
            queryDoctosCC = queryDoctosCC + "'" + abonoMaestroEntity.getFechaAbono() + "', ";                                        
            
                     
            if (configuracionMobil.getOperaSucursalAlmacen() == 1) {
                Resources.logger.info("MOBIL sucursalId: " + abonoMaestroEntity.getSucursalId());
                queryDoctosCC = queryDoctosCC + abonoMaestroEntity.getSucursalId() + ") ";                
            } else {
                Resources.logger.info("CONFIGURACION MOBIL sucursalId: " + configuracionMobil.getSucursalId());
                queryDoctosCC = queryDoctosCC + configuracionMobil.getSucursalId() + ") ";                
            }
            
            preparedStatementObj = conexion.prepareStatement(queryDoctosCC);                      
            preparedStatementObj.executeUpdate();
            Resources.logger.info("Save table [DOCTOS_CC] id: " + idAutoIncremental + " folio: " + "Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0"));                
                
            complementoXml.setDoctoCCId(idAutoIncremental);
            complementoXml.setClienteId(abonoMaestroEntity.getClienteId());
            complementoXml.setFechaHoraEnvioTimestamp(utilerias.getNowDateHourTimestamp());
            complementoXml.setFechaDate(convertTimestampToDate(utilerias.getNowDateHourTimestamp()));                
        } catch(SQLException exception){
            Resources.logger.error(errorMessage + " EN DOCTOS_CC " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, errorMessage + " EN DOCTOS_CC " + exception.getMessage(), exception.getMessage());            
        }
        
        try {         
            preparedStatementObj = conexion.prepareStatement(
                "INSERT INTO FORMAS_COBRO_DOCTOS " +
                "(FORMA_COBRO_DOC_ID, NOM_TABLA_DOCTOS, DOCTO_ID, FORMA_COBRO_ID, " +
                "NUM_CTA_PAGO, CLAVE_SIS_FORMA_COB, REFERENCIA, IMPORTE) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStatementObj.setInt(1, -1);
            preparedStatementObj.setString(2, "DOCTOS_CC");
            preparedStatementObj.setInt(3, idAutoIncremental);
            preparedStatementObj.setInt(4, abonoMaestroEntity.getFormaCobroCCId());
            preparedStatementObj.setString(5, "");
            preparedStatementObj.setString(6, "CC");
            preparedStatementObj.setString(7, "Abono: $" + abonoMaestroEntity.getAbonoTotal());
            preparedStatementObj.setDouble(8, 0.00);
            preparedStatementObj.executeUpdate();
            
            complementoXml.setFormaCobroId(abonoMaestroEntity.getFormaCobroCCId());
            
            Resources.logger.info("Save tabla [FORMAS_COBRO_DOCTOS]");
        } catch(SQLException exception){
            Resources.logger.error(errorMessage + " EN FORMAS_COBRO_DOCTOS " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, errorMessage + " EN FORMAS_COBRO_DOCTOS " + exception.getMessage(), exception.getMessage());                     
        }       
            
             
        List<ComplementoXmlDetalle> listaComplementoXmlDetalle = new ArrayList<>();
        try {
            for (AbonoDetalleEntity abonoDetalleEntity : abonoMaestroEntity.getAbonoDetalleEntity()) {
                ComplementoXmlDetalle complementoXmlDetalle = new ComplementoXmlDetalle();
                try {
                    preparedStatementObj = conexion.prepareStatement(
                        "INSERT INTO IMPORTES_DOCTOS_CC(IMPTE_DOCTO_CC_ID, DOCTO_CC_ID, FECHA, " +
                        "CANCELADO, APLICADO, ESTATUS, TIPO_IMPTE, DOCTO_CC_ACR_ID, IMPORTE, " +
                        "IMPUESTO, IVA_RETENIDO, ISR_RETENIDO, DSCTO_PPAG, PCTJE_COMIS_COB)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                    );
                    preparedStatementObj.setInt(1, -1);
                    preparedStatementObj.setInt(2, idAutoIncremental);
                    preparedStatementObj.setDate(3, utilerias.convertStringToDate(abonoMaestroEntity.getFechaAbono()));
                    preparedStatementObj.setString(4, "N");
                    preparedStatementObj.setString(5, "N");
                    preparedStatementObj.setString(6, "P");
                    preparedStatementObj.setString(7, "R");
                    preparedStatementObj.setInt(8, abonoDetalleEntity.getDoctoCCId());                
                    preparedStatementObj.setDouble(9, abonoDetalleEntity.getImporteAbono());
                    preparedStatementObj.setDouble(10, 0.00);
                    preparedStatementObj.setDouble(11, 0.00);
                    preparedStatementObj.setDouble(12, 0.00);
                    preparedStatementObj.setDouble(13, 0.00);
                    preparedStatementObj.setDouble(14, 0.00);
                    preparedStatementObj.executeUpdate();               
                    Resources.logger.info("Save tabla [IMPORTES_DOCTOS_CC]");             
                } catch(SQLException exception){
                    Resources.logger.error(errorMessage + " EN IMPORTES_DOCTOS_CC " + abonoDetalleEntity.getDoctoCCId() + 
                            " " + abonoDetalleEntity.getAbono() + ": " + exception.getMessage());            
                    return responseRequest.response(
                            ResponseRequest.DataStatus.ERROR, 
                            errorMessage + " EN IMPORTES_DOCTOS_CC " + abonoDetalleEntity.getDoctoCCId() + 
                            " " + abonoDetalleEntity.getAbono() + ": " + exception.getMessage(),
                            exception.getMessage());          
                }        

                String requiereComplementoPagos = cargoRequiereComplementoPagos(abonoDetalleEntity.getDoctoCCId());
                Resources.logger.info("[cargoRequiereComplementoPagos]" + abonoDetalleEntity.getDoctoCCId() + "  " + requiereComplementoPagos);
                if ("S".equals(requiereComplementoPagos.trim())) {
                    complementoXmlDetalle.setDoctoCCPadreId(abonoDetalleEntity.getDoctoCCId());
                    complementoXmlDetalle.setImporteAbono(abonoDetalleEntity.getImporteAbono());
                    listaComplementoXmlDetalle.add(complementoXmlDetalle);
                }
            }            
                
            Double importeTotal = 0.00;
            if (listaComplementoXmlDetalle.size() > 0)                    
                importeTotal = listaComplementoXmlDetalle.stream().mapToDouble(pojo -> pojo.getImporteAbono()).sum();                

            if (importeTotal > 0){
                try {
                    preparedStatementObj = conexion.prepareStatement(
                        "UPDATE DOCTOS_CC SET APLICADO = ?, MODALIDAD_FACTURACION = ?, USO_CFDI = ? WHERE DOCTO_CC_ID = ?"
                    );
                    preparedStatementObj.setString(1, "S");
                    preparedStatementObj.setString(2, "CFDI");                                                            
                    //preparedStatementObj.setString(3, "P01");
                    preparedStatementObj.setString(3, "CP01");
                    preparedStatementObj.setInt(4, idAutoIncremental);
                    preparedStatementObj.executeUpdate();                                           
                } catch(SQLException exception){
                    Resources.logger.error(errorMessage + " EN UPDATE DOCTOS_CC " + exception.getMessage());            
                    return responseRequest.response(
                            ResponseRequest.DataStatus.ERROR, 
                            errorMessage + " EN UPDATE DOCTOS_CC " + exception.getMessage(),
                            exception.getMessage());          
                }  
            } else {
                try {
                    preparedStatementObj = conexion.prepareStatement(
                        "UPDATE DOCTOS_CC SET APLICADO = ? WHERE DOCTO_CC_ID = ?"
                    );
                    preparedStatementObj.setString(1, "S");
                    preparedStatementObj.setInt(2, idAutoIncremental);
                    preparedStatementObj.executeUpdate();
                } catch(SQLException exception){
                    Resources.logger.error(errorMessage + " EN UPDATE DOCTOS_CC " + exception.getMessage());            
                    return responseRequest.response(
                            ResponseRequest.DataStatus.ERROR, 
                            errorMessage + " EN UPDATE DOCTOS_CC " + exception.getMessage(),
                            exception.getMessage());             
                }  
            }
            Resources.logger.info("Update table [DOCTOS_CC]");      

            return responseRequest.response(ResponseRequest.DataStatus.OK, abonoMaestroEntity, "Cobro por depositar grabados correctamente");  
        } catch(Exception exception) {
            Resources.logger.info("Error CXC" + exception.getMessage());      
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, abonoMaestroEntity, exception.getMessage()); 
        }
    }
    
    
    public ResponseRequest createCobroXDepositarMicrosip (String jsonString) throws SQLException {
        Resources.logger.info("Entrando a crear la cobranza:");
                
        ResponseRequest responseRequest = new ResponseRequest();
        List<CobroXDepositarEnviado> listaCobroXDepositarEnviados = new ArrayList<>();

        configuracionMicrosip();
     
        Utilerias utilerias = new Utilerias();
        try {            
            Type type = new TypeToken<CobroXDepositarModel>(){}.getType();
            CobroXDepositarModel cobroXDepositar = gson.fromJson(jsonString, type);                    
                        
            List<ComplementoXml> listaComplementoXml = new ArrayList<>();                        
            
              
            
            for (AbonoMaestroModel abonoMaestroEntity : cobroXDepositar.getListaAbonosParaMicrosip()) {
                    
                conexion.setAutoCommit(false);
                
                ResponseRequest responseRequestItem = createCobrosXDepositarMaestroDetalle(abonoMaestroEntity);
                
                CobroXDepositarEnviado cobroXDepositarEnviado = new CobroXDepositarEnviado();
                cobroXDepositarEnviado.setId(abonoMaestroEntity.getId());
                if (responseRequestItem.getStatus() == ResponseRequest.DataStatus.OK){   
                    cobroXDepositarEnviado.setStatus(ResponseRequest.DataStatus.OK);
                    cobroXDepositarEnviado.setMensaje("");  
                    
                    conexion.commit();
                } else if (responseRequestItem.getStatus() == ResponseRequest.DataStatus.ERROR){            
                    String data = (String)responseRequestItem.getData();
                    
                    cobroXDepositarEnviado.setStatus(ResponseRequest.DataStatus.ERROR);
                    cobroXDepositarEnviado.setMensaje(data);
                    
                    conexion.rollback(); 
                }       
                listaCobroXDepositarEnviados.add(cobroXDepositarEnviado);       
            }
            
            /*List<CobroXDepositarEnviado> listaCobrosXDepositarEnviado = listaCobroXDepositarEnviados.stream()
                    .filter(p -> p.getStatus() == ResponseRequest.DataStatus.ERROR)
                    .collect(Collectors.toList()); */          
            
            //if (listaCobrosXDepositarEnviado.size() == 0) {
                if (configuracionMobil.getControlaSerieFolioCXC() == 1) {                
                    PreparedStatement preparedStatement = conexion.prepareStatement(
                        "UPDATE SERIES_FOLIOS_CXC SET FOLIO = ?, SERIE = ? WHERE COBRADOR_ID = ?"
                    );
                    preparedStatement.setInt(1, cobroXDepositar.getSerieFolioCXC().getFolio());
                    preparedStatement.setString(2, cobroXDepositar.getSerieFolioCXC().getSerie());
                    preparedStatement.setInt(3, cobroXDepositar.getSerieFolioCXC().getCobradorId());
                    preparedStatement.executeUpdate();            
                }

                
            /*} else {
                listaCobroXDepositarEnviados.removeAll(listaCobroXDepositarEnviados.stream()
                    .filter(p -> p.getStatus() == ResponseRequest.DataStatus.OK)
                    .collect(Collectors.toList()));
                
            }*/
            
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaCobroXDepositarEnviados, "Cobros x depositar enviados al servidor");  
        }catch(JsonSyntaxException | SQLException exception){
            conexion.rollback();
            
            Resources.logger.error("Excepcion en createCobrosXDepositar: " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al procesar Cobros x depositar " + exception.getMessage());         
        }
    }
    
    private ResponseRequest createCobrosXDepositarMaestroDetalle(AbonoMaestroModel abonoMaestroEntity) {
        String errorMessage = "CTE: " + abonoMaestroEntity.getClaveCliente();
        
        Utilerias utilerias = new Utilerias();
        ResponseRequest responseRequest = new ResponseRequest();
                               
        ComplementoXml complementoXml = new ComplementoXml();                
        
        PreparedStatement preparedStatement = null; 
        ResultSet resultSet = null;
        int idAutoIncremental= 0; 
        int folioUltimo = 0;
        int LUGAR_EXPEDICION_ID = 0;
        
        try {/**************GENERO EL ID QUE TOMARA DOCTOS_CC***************/
            preparedStatement = conexion.prepareStatement(
                "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
            );
            resultSet = preparedStatement.executeQuery();                         
            while (resultSet.next()) {
                idAutoIncremental = resultSet.getInt("ID");                    
            }            
        } catch(SQLException exception){
            Resources.logger.error(errorMessage + " EN GEN_ID(ID_DOCTOS,1)" + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, errorMessage + " EN GEN_ID(ID_DOCTOS,1)" + exception.getMessage(), exception.getMessage());           
        }       
                
        try {/**********GENERO EL folio ÚLTIMO QUE TOMARA DOCTOS_CC*********/            
            preparedStatement = conexion.prepareStatement(
                "SELECT GEN_ID(ID_FOLIO_TEMP,1)AS ID FROM RDB$DATABASE"
            );
            resultSet = preparedStatement.executeQuery();            
            while (resultSet.next()) {
                folioUltimo = resultSet.getInt("ID");
            }            
        } catch(SQLException exception){
            Resources.logger.error(errorMessage + " EN GEN_ID(ID_FOLIO_TEMP,1)" + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, errorMessage + " EN GEN_ID(ID_FOLIO_TEMP,1)" + exception.getMessage(), exception.getMessage());      
        }
        
        try {/*********************LUGAR_EXPEDICION*************************/
            preparedStatement = conexion.prepareStatement(
                "SELECT LUGAR_EXPEDICION_ID FROM SUCURSALES WHERE SUCURSAL_ID = " + configuracionMobil.getSucursalId()
            );
            resultSet = preparedStatement.executeQuery();            
            while (resultSet.next()) {
                LUGAR_EXPEDICION_ID = resultSet.getInt("LUGAR_EXPEDICION_ID");
            }
        } catch(SQLException exception){
            Resources.logger.error(errorMessage + " EN SELECT LUGAR_EXPEDICION_ID" + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, errorMessage + " EN SELECT LUGAR_EXPEDICION_ID" + exception.getMessage(), exception.getMessage());            
        }
        
        PreparedStatement preparedStatementObj = null;
        try {
            String queryDoctosCC =            
                "INSERT INTO DOCTOS_CC(DOCTO_CC_ID, CONCEPTO_CC_ID, FOLIO, NATURALEZA_CONCEPTO, FECHA, HORA, CLAVE_CLIENTE, " +
                "IMPORTE_COBRO, CLIENTE_ID, TIPO_CAMBIO, CANCELADO, APLICADO, DESCRIPCION, COBRADOR_ID, FORMA_EMITIDA," +
                "CONTABILIZADO, CONTABILIZADO_GYP, COND_PAGO_ID, SISTEMA_ORIGEN, ESTATUS, ESTATUS_ANT, ES_CFD, TIENE_ANTICIPO," +
                "MODALIDAD_FACTURACION, ENVIADO, FECHA_HORA_ENVIO, CFDI_CERTIFICADO, INTEG_BA, CONTABILIZADO_BA, LUGAR_EXPEDICION_ID, FECHA_APLICACION, SUCURSAL_ID)" +
                "VALUES(";       
         
            queryDoctosCC = queryDoctosCC + idAutoIncremental + ", ";
            queryDoctosCC = queryDoctosCC + configuracionMobil.getConceptoCCId() + ", ";
            queryDoctosCC = queryDoctosCC + "'Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0") + "', ";
            queryDoctosCC = queryDoctosCC + "'R', ";
            queryDoctosCC = queryDoctosCC + "'" + abonoMaestroEntity.getFechaAbono() + "', ";
            queryDoctosCC = queryDoctosCC + "'" + abonoMaestroEntity.getHoraAbono() + "', ";
            queryDoctosCC = queryDoctosCC + "'" + abonoMaestroEntity.getClaveCliente() + "', ";
            queryDoctosCC = queryDoctosCC + "0.00, ";
            queryDoctosCC = queryDoctosCC + abonoMaestroEntity.getClienteId() + ", ";
            queryDoctosCC = queryDoctosCC + "1.00, ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "'" + abonoMaestroEntity.getDescripcion() + "', ";
            queryDoctosCC = queryDoctosCC + abonoMaestroEntity.getCobradorId() + ", ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + configuracionMobil.getCondicionPagoId() + ", ";
            queryDoctosCC = queryDoctosCC + "'CC', ";
            queryDoctosCC = queryDoctosCC + "'P', ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "'PREIMP', ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "CURRENT_TIMESTAMP, ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + "'N', ";
            queryDoctosCC = queryDoctosCC + LUGAR_EXPEDICION_ID + ", ";
            queryDoctosCC = queryDoctosCC + "'" + abonoMaestroEntity.getFechaAbono() + "', ";                                        
            
                     
            if (configuracionMobil.getOperaSucursalAlmacen() == 1) {
                Resources.logger.info("MOBIL sucursalId: " + abonoMaestroEntity.getSucursalId());
                queryDoctosCC = queryDoctosCC + abonoMaestroEntity.getSucursalId() + ") ";                
            } else {
                Resources.logger.info("CONFIGURACION MOBIL sucursalId: " + configuracionMobil.getSucursalId());
                queryDoctosCC = queryDoctosCC + configuracionMobil.getSucursalId() + ") ";                
            }
            
            preparedStatementObj = conexion.prepareStatement(queryDoctosCC);                      
            preparedStatementObj.executeUpdate();
            Resources.logger.info("Save table [DOCTOS_CC] id: " + idAutoIncremental + " folio: " + "Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0"));                
                
            complementoXml.setDoctoCCId(idAutoIncremental);
            complementoXml.setClienteId(abonoMaestroEntity.getClienteId());
            complementoXml.setFechaHoraEnvioTimestamp(utilerias.getNowDateHourTimestamp());
            complementoXml.setFechaDate(convertTimestampToDate(utilerias.getNowDateHourTimestamp()));                
        } catch(SQLException exception){
            Resources.logger.error(errorMessage + " EN DOCTOS_CC " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, errorMessage + " EN DOCTOS_CC " + exception.getMessage(), exception.getMessage());            
        }
        
        try {         
            preparedStatementObj = conexion.prepareStatement(
                "INSERT INTO FORMAS_COBRO_DOCTOS " +
                "(FORMA_COBRO_DOC_ID, NOM_TABLA_DOCTOS, DOCTO_ID, FORMA_COBRO_ID, " +
                "NUM_CTA_PAGO, CLAVE_SIS_FORMA_COB, REFERENCIA, IMPORTE) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStatementObj.setInt(1, -1);
            preparedStatementObj.setString(2, "DOCTOS_CC");
            preparedStatementObj.setInt(3, idAutoIncremental);
            preparedStatementObj.setInt(4, abonoMaestroEntity.getFormaCobroCCId());
            preparedStatementObj.setString(5, "");
            preparedStatementObj.setString(6, "CC");
            preparedStatementObj.setString(7, "Abono: $" + abonoMaestroEntity.getAbonoTotal());
            preparedStatementObj.setDouble(8, 0.00);
            preparedStatementObj.executeUpdate();
            
            complementoXml.setFormaCobroId(abonoMaestroEntity.getFormaCobroCCId());
            
            Resources.logger.info("Save tabla [FORMAS_COBRO_DOCTOS]");
        } catch(SQLException exception){
            Resources.logger.error(errorMessage + " EN FORMAS_COBRO_DOCTOS " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, errorMessage + " EN FORMAS_COBRO_DOCTOS " + exception.getMessage(), exception.getMessage());                     
        }       
            
             
        List<ComplementoXmlDetalle> listaComplementoXmlDetalle = new ArrayList<>();
        try {
            for (AbonoDetalleModel abonoDetalleEntity : abonoMaestroEntity.getListaAbonoDetalle()) {
                ComplementoXmlDetalle complementoXmlDetalle = new ComplementoXmlDetalle();
                try {
                    preparedStatementObj = conexion.prepareStatement(
                        "INSERT INTO IMPORTES_DOCTOS_CC(IMPTE_DOCTO_CC_ID, DOCTO_CC_ID, FECHA, " +
                        "CANCELADO, APLICADO, ESTATUS, TIPO_IMPTE, DOCTO_CC_ACR_ID, IMPORTE, " +
                        "IMPUESTO, IVA_RETENIDO, ISR_RETENIDO, DSCTO_PPAG, PCTJE_COMIS_COB)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                    );
                    preparedStatementObj.setInt(1, -1);
                    preparedStatementObj.setInt(2, idAutoIncremental);
                    preparedStatementObj.setDate(3, utilerias.convertStringToDate(abonoMaestroEntity.getFechaAbono()));
                    preparedStatementObj.setString(4, "N");
                    preparedStatementObj.setString(5, "N");
                    preparedStatementObj.setString(6, "P");
                    preparedStatementObj.setString(7, "R");
                    preparedStatementObj.setInt(8, abonoDetalleEntity.getDoctoCCId());                
                    preparedStatementObj.setDouble(9, abonoDetalleEntity.getImporteAbono());
                    preparedStatementObj.setDouble(10, 0.00);
                    preparedStatementObj.setDouble(11, 0.00);
                    preparedStatementObj.setDouble(12, 0.00);
                    preparedStatementObj.setDouble(13, 0.00);
                    preparedStatementObj.setDouble(14, 0.00);
                    preparedStatementObj.executeUpdate();               
                    Resources.logger.info("Save tabla [IMPORTES_DOCTOS_CC]");             
                } catch(SQLException exception){
                    Resources.logger.error(errorMessage + " EN IMPORTES_DOCTOS_CC " + abonoDetalleEntity.getDoctoCCId() + 
                            ": " + exception.getMessage());            
                    return responseRequest.response(
                            ResponseRequest.DataStatus.ERROR, 
                            errorMessage + " EN IMPORTES_DOCTOS_CC " + abonoDetalleEntity.getDoctoCCId() + 
                            ": " + exception.getMessage(),
                            exception.getMessage());          
                }        

                String requiereComplementoPagos = cargoRequiereComplementoPagos(abonoDetalleEntity.getDoctoCCId());
                Resources.logger.info("[cargoRequiereComplementoPagos]" + abonoDetalleEntity.getDoctoCCId() + "  " + requiereComplementoPagos);
                if ("S".equals(requiereComplementoPagos.trim())) {
                    complementoXmlDetalle.setDoctoCCPadreId(abonoDetalleEntity.getDoctoCCId());
                    complementoXmlDetalle.setImporteAbono(abonoDetalleEntity.getImporteAbono());
                    listaComplementoXmlDetalle.add(complementoXmlDetalle);
                }
            }            
                
            Double importeTotal = 0.00;
            if (listaComplementoXmlDetalle.size() > 0)                    
                importeTotal = listaComplementoXmlDetalle.stream().mapToDouble(pojo -> pojo.getImporteAbono()).sum();                

            if (importeTotal > 0){
                try {
                    preparedStatementObj = conexion.prepareStatement(
                        "UPDATE DOCTOS_CC SET APLICADO = ?, MODALIDAD_FACTURACION = ?, USO_CFDI = ? WHERE DOCTO_CC_ID = ?"
                    );
                    preparedStatementObj.setString(1, "S");
                    preparedStatementObj.setString(2, "CFDI");                                                            
                    //preparedStatementObj.setString(3, "P01");
                    preparedStatementObj.setString(3, "CP01");
                    preparedStatementObj.setInt(4, idAutoIncremental);
                    preparedStatementObj.executeUpdate();                                           
                } catch(SQLException exception){
                    Resources.logger.error(errorMessage + " EN UPDATE DOCTOS_CC " + exception.getMessage());            
                    return responseRequest.response(
                            ResponseRequest.DataStatus.ERROR, 
                            errorMessage + " EN UPDATE DOCTOS_CC " + exception.getMessage(),
                            exception.getMessage());          
                }  
            } else {
                try {
                    preparedStatementObj = conexion.prepareStatement(
                        "UPDATE DOCTOS_CC SET APLICADO = ? WHERE DOCTO_CC_ID = ?"
                    );
                    preparedStatementObj.setString(1, "S");
                    preparedStatementObj.setInt(2, idAutoIncremental);
                    preparedStatementObj.executeUpdate();
                } catch(SQLException exception){
                    Resources.logger.error(errorMessage + " EN UPDATE DOCTOS_CC " + exception.getMessage());            
                    return responseRequest.response(
                            ResponseRequest.DataStatus.ERROR, 
                            errorMessage + " EN UPDATE DOCTOS_CC " + exception.getMessage(),
                            exception.getMessage());             
                }  
            }
            Resources.logger.info("Update table [DOCTOS_CC]");      

            return responseRequest.response(ResponseRequest.DataStatus.OK, abonoMaestroEntity, "Cobro por depositar grabados correctamente");  
        } catch(Exception exception) {
            Resources.logger.info("Error CXC" + exception.getMessage());      
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, abonoMaestroEntity, exception.getMessage()); 
        }
    }
    
    public ResponseRequest estatusRuta (String jsonString) throws SQLException {
        Resources.logger.info("Entrando a actualizar la ruta " + jsonString);
                
        ResponseRequest responseRequest = new ResponseRequest();      
     
        try {            
            Type type = new TypeToken<ArrayList<RutaDTO>>(){}.getType();
            ArrayList<RutaDTO> listaRutas = gson.fromJson(jsonString, type);              
            
            for (RutaDTO ruta : listaRutas) {
                PreparedStatement preparedStatement = conexion.prepareStatement(
                    "UPDATE RUTAS_MAPS_ORDEN " +
                    "SET ESTATUS_CLIENTE = ?, LATITUD_PE = ?, LONGITUD_PE = ?, ES_MOCK = ?, GPS_PRECISION = ? WHERE ID = ?"
                );
                preparedStatement.setString(1, ruta.getEstatusCliente());
                preparedStatement.setDouble(2, ruta.getLatitudPedidoEntregado());
                preparedStatement.setDouble(3, ruta.getLongitudPedidoEntregado());
                preparedStatement.setBoolean(4, ruta.isEsMock());
                preparedStatement.setDouble(5, ruta.getPrecision());
                preparedStatement.setInt(6, ruta.getRutaMapsOrdenId());
                
                preparedStatement.executeUpdate();       
            }
                
                
            return responseRequest.response(ResponseRequest.DataStatus.OK, "OK", "Visitas actualizadas");  
        }catch(JsonSyntaxException | SQLException exception){
            
            
            Resources.logger.error("Excepcion en estatusRuta: " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al procesar Cobros x depositar " + exception.getMessage());         
        }
    }
    /*
     private ResponseRequest createCobroXDepositar(AbonoMaestroEntity abonoMaestroEntity) {
        String errorMessage = "CTE: " + abonoMaestroEntity.getClaveCliente();
        
        Utilerias utilerias = new Utilerias();
        ResponseRequest responseRequest = new ResponseRequest();
                               
        ComplementoXml complementoXml = new ComplementoXml();                
        
        PreparedStatement preparedStatement = null; 
        ResultSet resultSet = null;
        int idAutoIncremental= 0; 
        int folioUltimo = 0;
        int LUGAR_EXPEDICION_ID = 0;
        
        try {
            preparedStatement = conexion.prepareStatement(
                "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
            );
            resultSet = preparedStatement.executeQuery();                         
            while (resultSet.next()) {
                idAutoIncremental = resultSet.getInt("ID");                    
            }            
        } catch(SQLException exception){
            Resources.logger.error(errorMessage + " EN GEN_ID(ID_DOCTOS,1)" + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, errorMessage + " EN GEN_ID(ID_DOCTOS,1)" + exception.getMessage(), exception.getMessage());           
        }       
                
        try {          
            preparedStatement = conexion.prepareStatement(
                "SELECT GEN_ID(ID_FOLIO_TEMP,1)AS ID FROM RDB$DATABASE"
            );
            resultSet = preparedStatement.executeQuery();            
            while (resultSet.next()) {
                folioUltimo = resultSet.getInt("ID");
            }            
        } catch(SQLException exception){
            Resources.logger.error(errorMessage + " EN GEN_ID(ID_FOLIO_TEMP,1)" + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, errorMessage + " EN GEN_ID(ID_FOLIO_TEMP,1)" + exception.getMessage(), exception.getMessage());      
        }
        
        try {
            preparedStatement = conexion.prepareStatement(
                "SELECT LUGAR_EXPEDICION_ID FROM SUCURSALES WHERE SUCURSAL_ID = " + configuracionMobil.getSucursalId()
            );
            resultSet = preparedStatement.executeQuery();            
            while (resultSet.next()) {
                LUGAR_EXPEDICION_ID = resultSet.getInt("LUGAR_EXPEDICION_ID");
            }
        } catch(SQLException exception){
            Resources.logger.error(errorMessage + " EN SELECT LUGAR_EXPEDICION_ID" + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, errorMessage + " EN SELECT LUGAR_EXPEDICION_ID" + exception.getMessage(), exception.getMessage());            
        }
        
        PreparedStatement preparedStatementObj = null;
        try {
            preparedStatementObj = conexion.prepareStatement(
                "INSERT INTO DOCTOS_CC(DOCTO_CC_ID, CONCEPTO_CC_ID, FOLIO, NATURALEZA_CONCEPTO, FECHA, HORA, CLAVE_CLIENTE, " +
                "IMPORTE_COBRO, CLIENTE_ID, TIPO_CAMBIO, CANCELADO, APLICADO, DESCRIPCION, COBRADOR_ID, FORMA_EMITIDA," +
                "CONTABILIZADO, CONTABILIZADO_GYP, COND_PAGO_ID, SISTEMA_ORIGEN, ESTATUS, ESTATUS_ANT, ES_CFD, TIENE_ANTICIPO," +
                "MODALIDAD_FACTURACION, ENVIADO, FECHA_HORA_ENVIO, CFDI_CERTIFICADO, INTEG_BA, CONTABILIZADO_BA, LUGAR_EXPEDICION_ID, FECHA_APLICACION, SUCURSAL_ID)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"        
            );
            preparedStatementObj.setInt(1, idAutoIncremental);
            preparedStatementObj.setInt(2, configuracionMobil.getConceptoCCId());
            preparedStatementObj.setString(3, "Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0"));                
            preparedStatementObj.setString(4, "R");
            preparedStatementObj.setDate(5, utilerias.convertStringToDate(abonoMaestroEntity.getFechaAbono()));
            preparedStatementObj.setTime(6, utilerias.convertStringToTime(abonoMaestroEntity.getHoraAbono()));
            preparedStatementObj.setString(7, abonoMaestroEntity.getClaveCliente());
            preparedStatementObj.setDouble(8, 0.00);
            preparedStatementObj.setInt(9, abonoMaestroEntity.getClienteId());
            preparedStatementObj.setDouble(10, 1.00);
            preparedStatementObj.setString(11, "N");
            preparedStatementObj.setString(12, "N");
            preparedStatementObj.setString(13, abonoMaestroEntity.getDescripcion());
            preparedStatementObj.setInt(14, abonoMaestroEntity.getCobradorId());
            preparedStatementObj.setString(15, "N");
            preparedStatementObj.setString(16, "N");
            preparedStatementObj.setString(17, "N");
            preparedStatementObj.setInt(18, configuracionMobil.getCondicionPagoId());
            preparedStatementObj.setString(19, "CC");
            preparedStatementObj.setString(20, "P");
            preparedStatementObj.setString(21, "N");
            preparedStatementObj.setString(22, "N");
            preparedStatementObj.setString(23, "N");
            preparedStatementObj.setString(24, "PREIMP");
            preparedStatementObj.setString(25, "N");                                        
            preparedStatementObj.setTimestamp(26, utilerias.getNowDateHourTimestamp());
            preparedStatementObj.setString(27, "N");
            preparedStatementObj.setString(28, "N");
            preparedStatementObj.setString(29, "N");
            preparedStatementObj.setInt(30, LUGAR_EXPEDICION_ID);
            preparedStatementObj.setDate(31, utilerias.convertStringToDate(abonoMaestroEntity.getFechaAbono()));
            if (configuracionMobil.getOperaSucursalAlmacen() == 1) {
                Resources.logger.info("MOBIL sucursalId: " + abonoMaestroEntity.getSucursalId());
                preparedStatementObj.setInt(32, abonoMaestroEntity.getSucursalId());
            } else {
                Resources.logger.info("CONFIGURACION MOBIL sucursalId: " + configuracionMobil.getSucursalId());
                preparedStatementObj.setInt(32, configuracionMobil.getSucursalId());
            }
            preparedStatementObj.executeUpdate();
            Resources.logger.info("Save table [DOCTOS_CC] id: " + idAutoIncremental + " folio: " + "Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0"));                
                
            complementoXml.setDoctoCCId(idAutoIncremental);
            complementoXml.setClienteId(abonoMaestroEntity.getClienteId());
            complementoXml.setFechaHoraEnvioTimestamp(utilerias.getNowDateHourTimestamp());
            complementoXml.setFechaDate(convertTimestampToDate(utilerias.getNowDateHourTimestamp()));                
        } catch(SQLException exception){
            Resources.logger.error(errorMessage + " EN DOCTOS_CC " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, errorMessage + " EN DOCTOS_CC " + exception.getMessage(), exception.getMessage());            
        }
        
        try {         
            preparedStatementObj = conexion.prepareStatement(
                "INSERT INTO FORMAS_COBRO_DOCTOS " +
                "(FORMA_COBRO_DOC_ID, NOM_TABLA_DOCTOS, DOCTO_ID, FORMA_COBRO_ID, " +
                "NUM_CTA_PAGO, CLAVE_SIS_FORMA_COB, REFERENCIA, IMPORTE) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStatementObj.setInt(1, -1);
            preparedStatementObj.setString(2, "DOCTOS_CC");
            preparedStatementObj.setInt(3, idAutoIncremental);
            preparedStatementObj.setInt(4, abonoMaestroEntity.getFormaCobroCCId());
            preparedStatementObj.setString(5, "");
            preparedStatementObj.setString(6, "CC");
            preparedStatementObj.setString(7, "Abono: $" + abonoMaestroEntity.getAbonoTotal());
            preparedStatementObj.setDouble(8, 0.00);
            preparedStatementObj.executeUpdate();
            
            complementoXml.setFormaCobroId(abonoMaestroEntity.getFormaCobroCCId());
            
            Resources.logger.info("Save tabla [FORMAS_COBRO_DOCTOS]");
        } catch(SQLException exception){
            Resources.logger.error(errorMessage + " EN FORMAS_COBRO_DOCTOS " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, errorMessage + " EN FORMAS_COBRO_DOCTOS " + exception.getMessage(), exception.getMessage());                     
        }       
            
             
        List<ComplementoXmlDetalle> listaComplementoXmlDetalle = new ArrayList<>();
        for (AbonoDetalleEntity abonoDetalleEntity : abonoMaestroEntity.getAbonoDetalleEntity()) {
            ComplementoXmlDetalle complementoXmlDetalle = new ComplementoXmlDetalle();
            try {
                preparedStatementObj = conexion.prepareStatement(
                    "INSERT INTO IMPORTES_DOCTOS_CC(IMPTE_DOCTO_CC_ID, DOCTO_CC_ID, FECHA, " +
                    "CANCELADO, APLICADO, ESTATUS, TIPO_IMPTE, DOCTO_CC_ACR_ID, IMPORTE, " +
                    "IMPUESTO, IVA_RETENIDO, ISR_RETENIDO, DSCTO_PPAG, PCTJE_COMIS_COB)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                );
                preparedStatementObj.setInt(1, -1);
                preparedStatementObj.setInt(2, idAutoIncremental);
                preparedStatementObj.setDate(3, utilerias.convertStringToDate(abonoMaestroEntity.getFechaAbono()));
                preparedStatementObj.setString(4, "N");
                preparedStatementObj.setString(5, "N");
                preparedStatementObj.setString(6, "P");
                preparedStatementObj.setString(7, "R");
                preparedStatementObj.setInt(8, abonoDetalleEntity.getDoctoCCId());                
                preparedStatementObj.setDouble(9, abonoDetalleEntity.getImporteAbono());
                preparedStatementObj.setDouble(10, 0.00);
                preparedStatementObj.setDouble(11, 0.00);
                preparedStatementObj.setDouble(12, 0.00);
                preparedStatementObj.setDouble(13, 0.00);
                preparedStatementObj.setDouble(14, 0.00);
                preparedStatementObj.executeUpdate();               
                Resources.logger.info("Save tabla [IMPORTES_DOCTOS_CC]");             
            } catch(SQLException exception){
                Resources.logger.error(errorMessage + " EN IMPORTES_DOCTOS_CC " + abonoDetalleEntity.getDoctoCCId() + 
                        " " + abonoDetalleEntity.getAbono() + ": " + exception.getMessage());            
                return responseRequest.response(
                        ResponseRequest.DataStatus.ERROR, 
                        errorMessage + " EN IMPORTES_DOCTOS_CC " + abonoDetalleEntity.getDoctoCCId() + 
                        " " + abonoDetalleEntity.getAbono() + ": " + exception.getMessage(),
                        exception.getMessage());          
            }        
                
            String requiereComplementoPagos = cargoRequiereComplementoPagos(abonoDetalleEntity.getDoctoCCId());
            Resources.logger.info("[cargoRequiereComplementoPagos]" + abonoDetalleEntity.getDoctoCCId() + "  " + requiereComplementoPagos);
            if ("S".equals(requiereComplementoPagos.trim())) {
                complementoXmlDetalle.setDoctoCCPadreId(abonoDetalleEntity.getDoctoCCId());
                complementoXmlDetalle.setImporteAbono(abonoDetalleEntity.getImporteAbono());
                listaComplementoXmlDetalle.add(complementoXmlDetalle);
            }
        }
                
        Double importeTotal = 0.00;
        if (listaComplementoXmlDetalle.size() > 0)                    
            importeTotal = listaComplementoXmlDetalle.stream().mapToDouble(pojo -> pojo.getImporteAbono()).sum();                
                
        if (importeTotal > 0){
            try {
                preparedStatementObj = conexion.prepareStatement(
                    "UPDATE DOCTOS_CC SET APLICADO = ?, MODALIDAD_FACTURACION = ?, USO_CFDI = ? WHERE DOCTO_CC_ID = ?"
                );
                preparedStatementObj.setString(1, "S");
                preparedStatementObj.setString(2, "CFDI");                                                            
                //preparedStatementObj.setString(3, "P01");
                preparedStatementObj.setString(3, "CP01");
                preparedStatementObj.setInt(4, idAutoIncremental);
                preparedStatementObj.executeUpdate();                                           
            } catch(SQLException exception){
                Resources.logger.error(errorMessage + " EN UPDATE DOCTOS_CC " + exception.getMessage());            
                return responseRequest.response(
                        ResponseRequest.DataStatus.ERROR, 
                        errorMessage + " EN UPDATE DOCTOS_CC " + exception.getMessage(),
                        exception.getMessage());          
            }  
        } else {
            try {
                preparedStatementObj = conexion.prepareStatement(
                    "UPDATE DOCTOS_CC SET APLICADO = ? WHERE DOCTO_CC_ID = ?"
                );
                preparedStatementObj.setString(1, "S");
                preparedStatementObj.setInt(2, idAutoIncremental);
                preparedStatementObj.executeUpdate();
            } catch(SQLException exception){
                Resources.logger.error(errorMessage + " EN UPDATE DOCTOS_CC " + exception.getMessage());            
                return responseRequest.response(
                        ResponseRequest.DataStatus.ERROR, 
                        errorMessage + " EN UPDATE DOCTOS_CC " + exception.getMessage(),
                        exception.getMessage());             
            }  
        }
        Resources.logger.info("Update table [DOCTOS_CC]");      
                                   
        return responseRequest.response(ResponseRequest.DataStatus.OK, abonoMaestroEntity, "Cobro por depositar grabados correctamente");         
    }
     */
    
    private String cargoRequiereComplementoPagos(int cargoId) {
        String requiereComplemento = "N";
        try{            
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT REQUIERE_COMPLEMENTO FROM CARGO_REQUIERE_COMPL_PAGOS(?, ?)"
            );
            preparedStatement.setInt(1, cargoId);
            preparedStatement.setString(2, "S");
            ResultSet resultSet = preparedStatement.executeQuery();
                        
            while (resultSet.next()) {
                requiereComplemento = resultSet.getString("REQUIERE_COMPLEMENTO");
            }            
            return requiereComplemento;           
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());           
            return requiereComplemento;
        }
    }

    public int siguienteFolioConceptoCC() throws SQLException {  
        configuracionMobil.getSerieConceptoCC();
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "EXECUTE PROCEDURE GET_SIGFOL_CONCEPTO(?, ?, ?, ?)"
            );        
            preparedStatement.setString(1, "CC");
            preparedStatement.setInt(2, configuracionMobil.getConceptoCCId());
            preparedStatement.setString(3, configuracionMobil.getSerieConceptoCC());
            preparedStatement.setInt(4, 0);        
            ResultSet resultSet = preparedStatement.executeQuery();                        
            int folio = 0;
            while (resultSet.next()) {
                folio = resultSet.getInt(1);
            }

            folio++;
            System.out.println("Se obtuvo el FOLIO: " + folio);                

            preparedStatement = conexion.prepareStatement(
                "EXECUTE PROCEDURE GET_SIGFOL_CONCEPTO(?, ?, ?, ?)"
            );        
            preparedStatement.setString(1, "CC");
            preparedStatement.setInt(2, configuracionMobil.getConceptoCCId());
            preparedStatement.setString(3, configuracionMobil.getSerieConceptoCC());
            preparedStatement.setInt(4, folio);        
            preparedStatement.executeQuery();

            return folio;        
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());           
            return 0;
        }
    }
    
    /**************************************************************************/
    private Boolean estaOperandoAppChoferes() throws SQLException {
        String sql = "SELECT FIRST 1 1 FROM AH_PEDIDOS_ENRUTADOS";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            return rs.next();  // más limpio
        }
    }
    
    public String cobrosMicrosip(int cobradorId) throws SQLException {        
        try {
            Boolean estaOperandoAppChoferes = estaOperandoAppChoferes();
            String sqlQuery = "";
            if (estaOperandoAppChoferes) 
                sqlQuery = 
                        "SELECT " +
                        "DCC.DOCTO_CC_ID, DCC.FECHA, DCC.HORA, C.NOMBRE, FCD.FORMA_COBRO_ID, " +
                        "COALESCE(( " +
                        "   SELECT SUM(I2.IMPORTE) FROM IMPORTES_DOCTOS_CC I2 " +
                        "   WHERE I2.DOCTO_CC_ID = DCC.DOCTO_CC_ID " +
                        "), 0) AS ABONO_TOTAL " +
                        "FROM DOCTOS_CC DCC " +
                        "LEFT JOIN CLIENTES C ON C.CLIENTE_ID = DCC.CLIENTE_ID " +
                        "LEFT JOIN FORMAS_COBRO_DOCTOS FCD ON FCD.DOCTO_ID = DCC.DOCTO_CC_ID " +
                        "WHERE DCC.FOLIO STARTING WITH 'Z' AND DCC.ESTATUS = 'P' AND DCC.COBRADOR_ID = ? " +
                        /* 🔥 Reemplazo de los INNER JOIN problemáticos, acá excluimos los FOLIOS que se encuentra enrutados
                            haciendo relación primero con IMPORTES_DOCTOS_CC y DOCTOS_CC con DCC.DOCTO_CC_ID de arriba
                            condicionando los FOLIOS que están enrutados con las condiicones especificadas*/
                        "AND NOT EXISTS (" +
                        "   SELECT 1" +
                        "   FROM IMPORTES_DOCTOS_CC IDC " +
                        "   JOIN DOCTOS_CC DCCC ON DCCC.DOCTO_CC_ID = IDC.DOCTO_CC_ACR_ID " +
                        "   JOIN AH_PEDIDOS_ENRUTADOS PE ON PE.FOLIO_FACTURA = DCCC.FOLIO " +
                        "   WHERE IDC.DOCTO_CC_ID = DCC.DOCTO_CC_ID " +
                        "   AND PE.ESTATUS <> 'CERRADO' " +
                        ") " +                
                        /* 🔥 Evita duplicados por depósitos */
                        "AND NOT EXISTS ( " +
                        "   SELECT 1 " +
                        "   FROM DEPOSITOS_CC_DET DCCD " +
                        "   WHERE DCCD.DOCTO_CC_ID = DCC.DOCTO_CC_ID " +
                        ") " +
                        "ORDER BY DCC.DOCTO_CC_ID";
            else
                sqlQuery = 
                        "SELECT DCC.DOCTO_CC_ID, DCC.FECHA, DCC.HORA, C.NOMBRE, FCD.FORMA_COBRO_ID, SUM(IDCC.IMPORTE) AS ABONO_TOTAL " +
                        "FROM DOCTOS_CC DCC " +
                        "LEFT JOIN FORMAS_COBRO_DOCTOS FCD ON FCD.DOCTO_ID = DCC.DOCTO_CC_ID " +
                        "LEFT JOIN IMPORTES_DOCTOS_CC IDCC ON IDCC.DOCTO_CC_ID = DCC.DOCTO_CC_ID " +
                        "LEFT JOIN DEPOSITOS_CC_DET DCCD on DCCD.DOCTO_CC_ID = DCC.DOCTO_CC_ID " +
                        "LEFT JOIN CLIENTES C ON C.CLIENTE_ID = DCC.CLIENTE_ID " +
                        "WHERE SUBSTRING(DCC.FOLIO FROM 1 FOR 1) = 'Z' " +
                        "AND DCCD.DEPOSITO_CC_ID IS NULL " +
                        "AND DCC.ESTATUS = 'P' " +
                        "AND DCC.COBRADOR_ID = ? " +
                        "GROUP BY 1,2,3,4,5 " +
                        "ORDER BY 1";

            List<CobroMicrosip> listaCobroMicrosip = new ArrayList<CobroMicrosip>();
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT DCC.DOCTO_CC_ID, DCC.FECHA, DCC.HORA, C.NOMBRE, FCD.FORMA_COBRO_ID, SUM(IDCC.IMPORTE) AS ABONO_TOTAL " +
                "FROM DOCTOS_CC DCC " +
                "LEFT JOIN FORMAS_COBRO_DOCTOS FCD ON FCD.DOCTO_ID = DCC.DOCTO_CC_ID " +
                "LEFT JOIN IMPORTES_DOCTOS_CC IDCC ON IDCC.DOCTO_CC_ID = DCC.DOCTO_CC_ID " +
                "LEFT JOIN DEPOSITOS_CC_DET DCCD on DCCD.DOCTO_CC_ID = DCC.DOCTO_CC_ID " +
                "LEFT JOIN CLIENTES C ON C.CLIENTE_ID = DCC.CLIENTE_ID " +
                "WHERE SUBSTRING(DCC.FOLIO FROM 1 FOR 1) = 'Z' " +
                "AND DCCD.DEPOSITO_CC_ID IS NULL " +
                "AND DCC.ESTATUS = 'P' " +
                "AND DCC.COBRADOR_ID = ? " +
                "GROUP BY 1,2,3,4,5 " +
                "ORDER BY 1"
            );
            preparedStatement.setInt(1, cobradorId);                        
            ResultSet resultSet = preparedStatement.executeQuery();           
                        
            while (resultSet.next()) {                
                CobroMicrosip cobroMicrosip = new CobroMicrosip();
                cobroMicrosip.setDoctoCCId(resultSet.getInt("DOCTO_CC_ID"));
                cobroMicrosip.setFechaAbono(resultSet.getDate("FECHA").toString());
                cobroMicrosip.setHoraAbono(resultSet.getTime("HORA").toString());                                
                cobroMicrosip.setNombreCliente(resultSet.getString("NOMBRE"));                                
                cobroMicrosip.setFormaCobroCCId(resultSet.getInt("FORMA_COBRO_ID"));
                cobroMicrosip.setAbonoTotal(resultSet.getDouble("ABONO_TOTAL"));
                listaCobroMicrosip.add(cobroMicrosip);
            }            
            System.out.println(new Gson().toJson(listaCobroMicrosip));
            return new Gson().toJson(listaCobroMicrosip);
        } catch (SQLException e) {
            System.out.println("sql" + e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public ResponseRequest cobrosMicrosipChoferes(Long choferId, String cobradoresJsonIds) throws SQLException {
        Resources.logger.info("cobrosMicrosipChoferes:" + cobradoresJsonIds);
        ResponseRequest responseRequest = new ResponseRequest();
        Type type = new TypeToken<List<Long>>(){}.getType();
        List<Long> cobradoresIds = gson.fromJson(cobradoresJsonIds, type);
               
        if (cobradoresIds == null || cobradoresIds.isEmpty()) {            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "No se encontrarón resultados");
        }

        List<CobroMicrosip> listaCobroMicrosip = new ArrayList<>();

        // Construir los ? dinámicamente
        String inClause = cobradoresIds.stream()
        .map(String::valueOf)
        .collect(Collectors.joining(","));
        

        String sql = 
            /*"SELECT DCC.DOCTO_CC_ID, DCC.FECHA, DCC.HORA, C.NOMBRE, FCD.FORMA_COBRO_ID, " +
            "SUM(IDCC.IMPORTE) AS ABONO_TOTAL " +
            "FROM DOCTOS_CC DCC " +
            "LEFT JOIN FORMAS_COBRO_DOCTOS FCD ON FCD.DOCTO_ID = DCC.DOCTO_CC_ID " +
            "LEFT JOIN IMPORTES_DOCTOS_CC IDCC ON IDCC.DOCTO_CC_ID = DCC.DOCTO_CC_ID " +
            "LEFT JOIN DEPOSITOS_CC_DET DCCD ON DCCD.DOCTO_CC_ID = DCC.DOCTO_CC_ID " +
            "LEFT JOIN CLIENTES C ON C.CLIENTE_ID = DCC.CLIENTE_ID " +
            "INNER JOIN AH_PEDIDOS_ENRUTADOS PE ON PE.FOLIO_FACTURA = DCC.FOLIO  AND PE.ESTATUS <> 'CERRADO' AND PE.CHOFER_ID = " + choferId +  
            " WHERE SUBSTRING(DCC.FOLIO FROM 1 FOR 1) = 'Z' " +
            "AND DCCD.DEPOSITO_CC_ID IS NULL " +
            "AND DCC.ESTATUS = 'P' " +
            "AND DCC.COBRADOR_ID IN (" + inClause.toString() + ") " +
            "GROUP BY 1,2,3,4,5 " +
            "ORDER BY 1";*/
        
            "SELECT " +
            "DCC.DOCTO_CC_ID, DCC.FECHA, DCC.HORA, C.NOMBRE, FCD.FORMA_COBRO_ID, PCP.DIAS_PLAZO, " +
            "COALESCE(( " +
            "   SELECT SUM(I2.IMPORTE) FROM IMPORTES_DOCTOS_CC I2 " +
            "   WHERE I2.DOCTO_CC_ID = DCC.DOCTO_CC_ID " +
            "), 0) AS ABONO_TOTAL " +
            "FROM DOCTOS_CC DCC " +
            "LEFT JOIN CLIENTES C ON C.CLIENTE_ID = DCC.CLIENTE_ID " +
            "LEFT JOIN FORMAS_COBRO_DOCTOS FCD ON FCD.DOCTO_ID = DCC.DOCTO_CC_ID " +
            "INNER JOIN PLAZOS_COND_PAG PCP ON DCC.COND_PAGO_ID = PCP.COND_PAGO_ID " +    
            "WHERE DCC.FOLIO STARTING WITH 'Z' AND DCC.ESTATUS = 'P' AND DCC.COBRADOR_ID IN (" + inClause.toString() + ")" +
            /* 🔥 Reemplazo de los INNER JOIN problemáticos, acá buscamos el FOLIO que se encuentra enrutado
                haciendo relación primero con IMPORTES_DOCTOS_CC y DOCTOS_CC con DCC.DOCTO_CC_ID de arriba
                condicionando los FOLIOs que están enrutados con als condiicones especificadas*/
            "AND EXISTS (" +
            "   SELECT 1" +
            "   FROM IMPORTES_DOCTOS_CC IDC " +
            "   JOIN DOCTOS_CC DCCC ON DCCC.DOCTO_CC_ID = IDC.DOCTO_CC_ACR_ID " +
            "   JOIN AH_PEDIDOS_ENRUTADOS PE ON PE.FOLIO_FACTURA = DCCC.FOLIO " +
            "   WHERE IDC.DOCTO_CC_ID = DCC.DOCTO_CC_ID " +
            "   AND PE.ESTATUS <> 'CERRADO' " +
            "   AND PE.CHOFER_ID = " + choferId +
            ") " +                
            /* 🔥 Evita duplicados por depósitos */
            "AND NOT EXISTS ( " +
            "   SELECT 1 " +
            "   FROM DEPOSITOS_CC_DET DCCD " +
            "   WHERE DCCD.DOCTO_CC_ID = DCC.DOCTO_CC_ID " +
            ") " +
            "ORDER BY DCC.DOCTO_CC_ID";


        /*PCP.DIAS_PLAZO
        INNER JOIN PLAZOS_COND_PAG PCP ON DCC.COND_PAGO_ID = PCP.COND_PAGO_ID*/
                
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            // Asignar parámetros
            /*for (int i = 0; i < cobradoresIds.size(); i++) {
                preparedStatement.setLong(i + 1, cobradoresIds.get(i));
            }*/

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    CobroMicrosip cobroMicrosip = new CobroMicrosip();
                    cobroMicrosip.setDoctoCCId(resultSet.getInt("DOCTO_CC_ID"));
                    cobroMicrosip.setFechaAbono(resultSet.getDate("FECHA").toString());
                    cobroMicrosip.setHoraAbono(resultSet.getTime("HORA").toString());
                    cobroMicrosip.setNombreCliente(resultSet.getString("NOMBRE"));
                    cobroMicrosip.setFormaCobroCCId(resultSet.getInt("FORMA_COBRO_ID"));
                    cobroMicrosip.setAbonoTotal(resultSet.getDouble("ABONO_TOTAL"));

                    listaCobroMicrosip.add(cobroMicrosip);
                }
            }
        }

        Gson gson = new Gson();
        return responseRequest.response(ResponseRequest.DataStatus.OK, listaCobroMicrosip, "Cobros para microsip");                  
    }
    
    
    /**************************************************************************/
    
    public ResponseRequest createDepositosRefactor (String jsonString) throws SQLException {
        Resources.logger.info("Entrando a crear los depositos:" + jsonString);
        
        ResponseRequest responseRequest = new ResponseRequest();
        
        List<DepositoGrabado> listaDepositoGrabado = new ArrayList<DepositoGrabado>();
        
        Gson gson = new Gson();
        
        configuracionMicrosip();
        
        Utilerias utilerias = new Utilerias();
        try {                    
            Type type = new TypeToken<Deposito>(){}.getType();
            Deposito deposito = gson.fromJson(jsonString, type);
            
            conexion.setAutoCommit(false);                        
            
            if (configuracionMobil.getOperaDepositos()== 1) {
                //---------INSERTAR DEPOSITOS CHECAR LA CONFIGURACION --------------

                for(DepositoMaestro depositoMaestro : deposito.getListaDepositosParaMicrosip()) {
                    PreparedStatement preparedStatement = conexion.prepareStatement(
                        "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                    );
                    ResultSet resultSet = preparedStatement.executeQuery();
                    int idAutoIncremental= 0;                
                    while (resultSet.next()) {
                        idAutoIncremental = resultSet.getInt("ID");                    
                    }                
                    Resources.logger.info("ID GENERADO: " +idAutoIncremental);

                    String query = "INSERT INTO DEPOSITOS_CC " +
                            "(DEPOSITO_CC_ID, FECHA, FORMA_COBRO_CC_ID, SUCURSAL_ID, CUENTA_BAN_ID, " +
                            "REFER_MOVTO_BANCARIO, DESCRIPCION, IMPORTE, TIPO_CAMBIO, APLICADO, ESTATUS, FORMA_EMITIDA, " +
                            "USUARIO_CREADOR, FECHA_HORA_CREACION, USUARIO_AUT_CREACION, FECHA_HORA_ULT_MODIF, FECHA_HORA_CANCELACION) " +
                            "VALUES(";
          
                    query = query + idAutoIncremental + ", ";                                       
                    
                    query = query + "'" + depositoMaestro.getFecha() + "', ";                    
                    query = query + depositoMaestro.getFormaCobroCCId() + ", ";
                                        
                    if (configuracionMobil.getOperaSucursalAlmacen() == 1) {
                        Resources.logger.info("MOBIL sucursalId: " + depositoMaestro.getSucursalId());                        
                        query = query + depositoMaestro.getSucursalId() + ", ";
                    } else {
                        Resources.logger.info("CONFIGURACION MOBIL sucursalId: " + configuracionMobil.getSucursalId());
                        query = query + configuracionMobil.getSucursalId() + ", ";                        
                    }
                    
                    query = query + depositoMaestro.getCuentaBancariaId() + ", ";
                    query = query + "'" + depositoMaestro.getReferencia() + "', ";                    
                    query = query + "'" + depositoMaestro.getDescripcion() + "', ";                    
                    query = query + depositoMaestro.getImporte() + ", ";             
                    query = query + "1.00, ";
                    query = query + "'N', ";
                    query = query + "'P', ";
                    query = query + "'N', ";
                    query = query + "'SYSDBA', ";
                    query = query + "CURRENT_TIMESTAMP, ";
                    query = query + "'SYSDBA', ";
                    query = query + "CURRENT_TIMESTAMP, ";
                    query = query + "CURRENT_TIMESTAMP) ";
            
                    PreparedStatement preparedStatementObj = conexion.prepareStatement(query);
                    preparedStatementObj.executeUpdate();                
                    Resources.logger.info("Save table [DEPOSITOS_CC] id: " + idAutoIncremental);                

                    for (DepositoDetalle detalle : depositoMaestro.getDepositoDetalle()) {
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO DEPOSITOS_CC_DET(DEPOSITO_CC_DET_ID, DEPOSITO_CC_ID, DOCTO_CC_ID)VALUES(?, ?, ?)"
                        );
                        preparedStatementObj.setInt(1, -1);
                        preparedStatementObj.setInt(2, idAutoIncremental);
                        preparedStatementObj.setInt(3, detalle.getDoctoCCId());
                        preparedStatementObj.executeUpdate();               
                        Resources.logger.info("Save table [DEPOSITOS_CC_DET]");                                               
                    }
                    DepositoGrabado depositoGrabado = new DepositoGrabado();
                    depositoGrabado.setId(depositoMaestro.getId());
                    listaDepositoGrabado.add(depositoGrabado);
                }
            }
            
            conexion.commit();
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaDepositoGrabado, "Depósitos grabados correctamente");  
        }catch(SQLException exception){
            Resources.logger.error("SUCEDIO UNA EXEPCION al grabar depósitos: " + exception.getMessage());                       
            conexion.rollback();
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al procesar los depósitos " + exception.getMessage());                            
        }
    }
    
    /*
     public ResponseRequest createDepositosRefactor (String jsonString) throws SQLException {
        Resources.logger.info("Entrando a crear la cobranza:" + jsonString);
        
        ResponseRequest responseRequest = new ResponseRequest();
        
        List<DepositoGrabado> listaDepositoGrabado = new ArrayList<DepositoGrabado>();
        
        Gson gson = new Gson();
        
        configuracionMicrosip();
        
        
        Utilerias utilerias = new Utilerias();
        try {            
            Type type = new TypeToken<Deposito>(){}.getType();
            Deposito deposito = gson.fromJson(jsonString, type);
            
            conexion.setAutoCommit(false);                        
            
            if (configuracionMobil.getOperaDepositos()== 1) {
                //---------INSERTAR DEPOSITOS CHECAR LA CONFIGURACION --------------

                for(DepositoMaestro depositoMaestro : deposito.getListaDepositosParaMicrosip()) {
                    PreparedStatement preparedStatement = conexion.prepareStatement(
                        "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                    );
                    ResultSet resultSet = preparedStatement.executeQuery();
                    int idAutoIncremental= 0;                
                    while (resultSet.next()) {
                        idAutoIncremental = resultSet.getInt("ID");                    
                    }                
                    //Resources.logger.info("ID GENERADO: " +idAutoIncremental);

                    String query = "INSERT INTO DEPOSITOS_CC " +
                            "(DEPOSITO_CC_ID, FECHA, FORMA_COBRO_CC_ID, SUCURSAL_ID, CUENTA_BAN_ID, " +
                            "REFER_MOVTO_BANCARIO, DESCRIPCION, IMPORTE, TIPO_CAMBIO, APLICADO, ESTATUS, FORMA_EMITIDA, " +
                            "USUARIO_CREADOR, FECHA_HORA_CREACION, USUARIO_AUT_CREACION, FECHA_HORA_ULT_MODIF, FECHA_HORA_CANCELACION) " +
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatementObj = conexion.prepareStatement(query);
                    preparedStatementObj.setInt(1, idAutoIncremental);
                    preparedStatementObj.setDate(2, utilerias.convertStringToDate2(depositoMaestro.getFecha()));
                    preparedStatementObj.setInt(3, depositoMaestro.getFormaCobroCCId());
                    if (configuracionMobil.getOperaSucursalAlmacen() == 1) {
                        Resources.logger.info("MOBIL sucursalId: " + depositoMaestro.getSucursalId());
                        preparedStatementObj.setInt(4, depositoMaestro.getSucursalId());
                    } else {
                        Resources.logger.info("CONFIGURACION MOBIL sucursalId: " + configuracionMobil.getSucursalId());
                        preparedStatementObj.setInt(4, configuracionMobil.getSucursalId());
                    }
                    
                    preparedStatementObj.setInt(5, depositoMaestro.getCuentaBancariaId());
                    preparedStatementObj.setString(6, depositoMaestro.getReferencia());                
                    preparedStatementObj.setString(7, depositoMaestro.getDescripcion());                                               
                    preparedStatementObj.setDouble(8, depositoMaestro.getImporte());
                    preparedStatementObj.setDouble(9, 1.00);
                    preparedStatementObj.setString(10, "N");
                    preparedStatementObj.setString(11, "P");
                    preparedStatementObj.setString(12, "N");                
                    preparedStatementObj.setString(13, "SYSDBA");
                    //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
                    Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());                
                    preparedStatementObj.setTimestamp(14, timestamp);
                    preparedStatementObj.setString(15, "SYSDBA"); 
                    preparedStatementObj.setTimestamp(16, timestamp); 
                    preparedStatementObj.setTimestamp(17, timestamp); 

                    preparedStatementObj.executeUpdate();                
                    Resources.logger.info("Save table [DEPOSITOS_CC] id: " + idAutoIncremental);                

                    for (DepositoDetalle detalle : depositoMaestro.getDepositoDetalle()) {
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO DEPOSITOS_CC_DET(DEPOSITO_CC_DET_ID, DEPOSITO_CC_ID, DOCTO_CC_ID)VALUES(?, ?, ?)"
                        );
                        preparedStatementObj.setInt(1, -1);
                        preparedStatementObj.setInt(2, idAutoIncremental);
                        preparedStatementObj.setInt(3, detalle.getDoctoCCId());
                        preparedStatementObj.executeUpdate();               
                        Resources.logger.info("Save table [DEPOSITOS_CC_DET]");                                               
                    }
                    DepositoGrabado depositoGrabado = new DepositoGrabado();
                    depositoGrabado.setId(depositoMaestro.getId());
                    listaDepositoGrabado.add(depositoGrabado);
                }
            }
            
            conexion.commit();
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaDepositoGrabado, "Depósitos grabados correctamente");  
        }catch(SQLException exception){
            Resources.logger.error("SUCEDIO UNA EXEPCION al grabar depósitos: " + exception.getMessage());                       
            conexion.rollback();
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al procesar los depósitos " + exception.getMessage());                            
        }
    }
     */
    
    /**************************************************************************/
    
    public ResponseRequest createVisitasClientes(String jsonVisitasClientes) throws SQLException {        
        Resources.logger.info("Se ha llamado al recurso REST mediante una peticion POST (createVisitasClientes)" + jsonVisitasClientes);        
        ResponseRequest responseRequest = new ResponseRequest();
        
        
        ArrayList<VisitaClienteGrabado> listaVisitaClienteGrabado = new ArrayList<VisitaClienteGrabado>();
                
        try {       
            Type type = new TypeToken<List<Localizacion>>(){}.getType();
            List<Localizacion> listaLocalizacion = gson.fromJson(jsonVisitasClientes, type);
            Utilerias utilerias = new Utilerias();
            for(Localizacion localizacion : listaLocalizacion){                                                                 
                PreparedStatement preparedStatementObj = conexion.prepareStatement( 
                    "INSERT INTO GEOLOCALIZACION_CLIENTES (CLIENTE_ID, LATITUD, LONGITUD, FECHA, HORA, PROCESO, VENDEDOR_ID, MONTO_REALIZADO)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
                );          
                preparedStatementObj.setInt(1, localizacion.getCliente_id());                  
                preparedStatementObj.setDouble(2, localizacion.getLatitud());                  
                preparedStatementObj.setDouble(3, localizacion.getLongitud());                  
                preparedStatementObj.setDate(4, utilerias.convertStringToDate(localizacion.getFecha()));                  
                preparedStatementObj.setTime(5, utilerias.convertStringToTime(localizacion.getHora()));  
                preparedStatementObj.setString(6, localizacion.getProceso());  
                preparedStatementObj.setInt(7, localizacion.getVendedor_id());  
                preparedStatementObj.setDouble(8, localizacion.getMonto_realizado());  
                preparedStatementObj.executeUpdate();

                // Este objeto es para agregar el id que se generp en ANDROID y retornarlo en una lista
                VisitaClienteGrabado visitaClienteGrabadoObject = new VisitaClienteGrabado();
                visitaClienteGrabadoObject.setId(localizacion.getId());
                visitaClienteGrabadoObject.setIdGenerado(localizacion.getId());                                
                listaVisitaClienteGrabado.add(visitaClienteGrabadoObject); 
            }
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaVisitaClienteGrabado, "Visitas a clientes grabadas correctamente");
            //return listaVisitaClienteGrabado;
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en la cabecera createVisitasClientes: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al crear visitas - clientes " + exception.getMessage());
        }/*finally {  
            return listaVisitaClienteGrabado;
        }*/
    }
    
    /**************************************************************************/
    public ResponseRequest visitasEfectivasInefectivas(String jsonVisitasClientes) throws SQLException {        
        Resources.logger.info("Se ha llamado al recurso REST mediante una peticion POST (visitasEfectivasInefectivas)" + jsonVisitasClientes);        
        ResponseRequest responseRequest = new ResponseRequest();
        
        
        ArrayList<VisitaClienteGrabado> listaVisitaClienteGrabado = new ArrayList<VisitaClienteGrabado>();
                
        try {       
            Type type = new TypeToken<List<VisitaEfectivaInefectiva>>(){}.getType();
            List<VisitaEfectivaInefectiva> listaVisitasEfectivasInefectivas = gson.fromJson(jsonVisitasClientes, type);
            Utilerias utilerias = new Utilerias();
            for(VisitaEfectivaInefectiva visitaEfectivaInefectiva : listaVisitasEfectivasInefectivas){                                                                 
                PreparedStatement preparedStatementObj = conexion.prepareStatement( 
                    "INSERT INTO VISITAS_EFECTIVAS_INEFECTIVAS (VENDEDOR_ID, CLIENTE_ID, VISITA, FECHA, HORA, MOTIVO_ID)" +
                    "VALUES (?, ?, ?, ?, ?, ?)"
                );          
                preparedStatementObj.setInt(1, visitaEfectivaInefectiva.getVendedorId());                  
                preparedStatementObj.setInt(2, visitaEfectivaInefectiva.getClienteId()); 
                preparedStatementObj.setString(3, visitaEfectivaInefectiva.getVisita()); 
                preparedStatementObj.setDate(4, utilerias.convertStringToDate(visitaEfectivaInefectiva.getFecha()));                  
                preparedStatementObj.setTime(5, utilerias.convertStringToTime(visitaEfectivaInefectiva.getHora()));                  
                preparedStatementObj.setInt(6, visitaEfectivaInefectiva.getMotivoId());                                   
                preparedStatementObj.executeUpdate();

                // Este objeto es para agregar el id que se generp en ANDROID y retornarlo en una lista
                VisitaClienteGrabado visitaClienteGrabadoObject = new VisitaClienteGrabado();
                visitaClienteGrabadoObject.setId(visitaEfectivaInefectiva.getId());
                visitaClienteGrabadoObject.setIdGenerado(visitaEfectivaInefectiva.getId());                                
                listaVisitaClienteGrabado.add(visitaClienteGrabadoObject); 
            }
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaVisitaClienteGrabado, "Visitas a clientes grabadas correctamente");
            //return listaVisitaClienteGrabado;
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en la cabecera visitasEfectivasInefectivas: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al crear visitas - clientes " + exception.getMessage());
        }/*finally {  
            return listaVisitaClienteGrabado;
        }*/
    }
    
    /**************************************************************************/
    
    public String cobranzaRutas(String chofer) throws SQLException {        
        try {                                                
            
            PreparedStatement preparedStatement = conexion.prepareStatement(   
                "SELECT FOLIO_DOCTO_CXC FROM AH_FOLIOS_CXC_RUTAS(?)"                    
                /*"SELECT FOLIO FROM doctos_ve WHERE DOCTO_VE_ID IN ( " +
                    "SELECT DOCTO_VE_DEST_ID FROM doctos_ve_ligas WHERE DOCTO_VE_FTE_ID IN " +
                    "(" +
                        "SELECT DOCTO_VE_ID FROM doctos_ve WHERE FOLIO IN " +
                        "( " +
                            "SELECT REMISION FROM DUNAS_RUTAMAPS WHERE NOMBRE = ? " +
                        ") " +
                    ") " +
                ")"*/
            );
            preparedStatement.setString(1, chofer);
            ResultSet resultSet = preparedStatement.executeQuery();            
            String foliosRutas = "";
            while (resultSet.next()) {  
                foliosRutas = foliosRutas + "'";
                foliosRutas = foliosRutas + resultSet.getString("FOLIO_DOCTO_CXC");                
                foliosRutas = foliosRutas + "',";
            }      
            foliosRutas = foliosRutas.substring(0, foliosRutas.length() - 1); 
            
            
            List<CobranzaRefactor> listaCobranza = new ArrayList<CobranzaRefactor>();
                                    
            preparedStatement = conexion.prepareStatement(     
                //"SELECT C.CLIENTE_ID, C.NOMBRE, B.DOCTO_CC_ID, B.FOLIO, DCC.FECHA AS FECHA_ELABORACION, " +
                "SELECT C.CLIENTE_ID, C.NOMBRE, B.DOCTO_CC_ID, B.FOLIO, B.FECHA_ELABORACION, " +
                "B.FECHA_VENCIMIENTO, B.IMPORTE_CARGO, B.SALDO_CARGO, B.ATRASO, B.CONCEPTO_CC_ID, " +
                "coalesce(DC.NOMBRE_CONSIG, 'Dirección principal') NOMBRE_CONSIG " +
                "FROM CLIENTES C " +
                "LEFT JOIN CARGOS_CLIENTE_AH(C.cliente_id,current_date,current_date,'N','S')B on 1=1 " +
                //"INNER JOIN DOCTOS_CC DCC ON DCC.DOCTO_CC_ID=B.DOCTO_CC_ID " +
                "left join doctos_entre_sis ds on (ds.docto_dest_id=B.docto_cc_id and CLAVE_SIS_DEST='CC' and  CLAVE_SIS_FTE='VE' and ds.tipo_docto='C') " +
                "left JOIN DOCTOS_VE DVE ON (DVE.docto_ve_id = ds.docto_fte_id) " +
                "left JOIN DIRS_CLIENTES DC ON DC.DIR_CLI_ID = DVE.DIR_CLI_ID " +
                "WHERE B.DOCTO_CC_ID IS NOT NULL " +
                "AND B.FOLIO IN (" + foliosRutas + ")"+
                " ORDER BY B.ATRASO"
            );

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CobranzaRefactor cobranza = new CobranzaRefactor();
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
                if (resultSet.getString("NOMBRE_CONSIG") == null)
                    cobranza.setNombreConsignatario("");
                else
                    cobranza.setNombreConsignatario(resultSet.getString("NOMBRE_CONSIG"));
                listaCobranza.add(cobranza);
            }
            return gson.toJson(listaCobranza);
        } catch (SQLException exception) {
            Resources.logger.error("SUCEDIO UNA EXEPCION al recuperar cobranza por chofer: " + exception.getMessage());     
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    public String cobranzaRutasRefactor(int choferId) throws SQLException {        
        try {
            System.out.println(choferId);
            List<CobranzaRefactor> listaCobranza = new ArrayList();
                                    
            PreparedStatement preparedStatement = conexion.prepareStatement(    
                "SELECT " +
                "    C.CLIENTE_ID, " +
                "    C.NOMBRE, " +
                "    B.DOCTO_CC_ID, " +
                "    B.FOLIO, " +
                "    B.FECHA_ELABORACION, " +        
                //"    DCC.FECHA AS FECHA_ELABORACION, " +
                "    B.FECHA_VENCIMIENTO, " +
                "    B.IMPORTE_CARGO, " +
                "    B.SALDO_CARGO, " +
                "    B.ATRASO, " +
                "    B.CONCEPTO_CC_ID, " +
                "    COALESCE(DC.NOMBRE_CONSIG, 'Dirección principal') AS NOMBRE_CONSIG " +
                "FROM AH_PEDIDOS_ENRUTADOS P " +
                "INNER JOIN CLIENTES C " +
                "    ON C.CLIENTE_ID = P.CLIENTE_ID " +
                "    AND P.ESTATUS <> 'CERRADO' " +
                "    AND P.CHOFER_ID = " + choferId +  
                " LEFT JOIN CARGOS_CLIENTE_AH(C.cliente_id,current_date,current_date,'N','S')B on 1=1 " +  
                " left join doctos_entre_sis ds on (ds.docto_dest_id=B.docto_cc_id and CLAVE_SIS_DEST='CC' and  CLAVE_SIS_FTE='VE' and ds.tipo_docto='C') " +
                //"LEFT JOIN CARGOS_CLIENTE(C.CLIENTE_ID, CURRENT_DATE, CURRENT_DATE, 'N', 'S') B " +
                //"    ON B.FOLIO = P.FOLIO_FACTURA " +
                //"INNER JOIN DOCTOS_CC DCC " +
                //"    ON DCC.DOCTO_CC_ID = B.DOCTO_CC_ID " +
                //"LEFT JOIN DOCTOS_ENTRE_SIS DS " +
                //"    ON DS.DOCTO_DEST_ID = DCC.DOCTO_CC_ID " +
                //"    AND DS.CLAVE_SIS_DEST = 'CC' " +
                //"    AND DS.CLAVE_SIS_FTE = 'VE' " +
                //"    AND DS.TIPO_DOCTO = 'C' " +
                "LEFT JOIN DOCTOS_VE DVE " +
                "    ON DVE.DOCTO_VE_ID = DS.DOCTO_FTE_ID " +
                "LEFT JOIN DIRS_CLIENTES DC " +
                "    ON DC.DIR_CLI_ID = DVE.DIR_CLI_ID " +
                "INNER JOIN AH_PEDIDOS_ENRUTADOS PE ON PE.FOLIO_FACTURA = B.FOLIO  AND PE.ESTATUS <> 'CERRADO' AND PE.CHOFER_ID = " + choferId +  
                "WHERE " +
                "    B.DOCTO_CC_ID IS NOT NULL " +
                "ORDER BY " +
                "    B.ATRASO"   
                    
                /*"SELECT C.CLIENTE_ID, C.NOMBRE, B.DOCTO_CC_ID, B.FOLIO, DCC.FECHA AS FECHA_ELABORACION, " +
                "B.FECHA_VENCIMIENTO, B.IMPORTE_CARGO, B.SALDO_CARGO, B.ATRASO, B.CONCEPTO_CC_ID, " +
                "coalesce(DC.NOMBRE_CONSIG, 'Dirección principal') NOMBRE_CONSIG " +
                "FROM CLIENTES C " +
                "LEFT JOIN CARGOS_CLIENTE(C.cliente_id,current_date,current_date,'N','S')B on 1=1 " +
                "INNER JOIN DOCTOS_CC DCC ON DCC.DOCTO_CC_ID=B.DOCTO_CC_ID " +
                "left join doctos_entre_sis ds on (ds.docto_dest_id=dcc.docto_cc_id and CLAVE_SIS_DEST='CC' and  CLAVE_SIS_FTE='VE' and ds.tipo_docto='C') " +
                "left JOIN DOCTOS_VE DVE ON (DVE.docto_ve_id = ds.docto_fte_id) " +
                "left JOIN DIRS_CLIENTES DC ON DC.DIR_CLI_ID = DVE.DIR_CLI_ID " +
                "WHERE B.DOCTO_CC_ID IS NOT NULL " +
                "AND B.FOLIO IN (" +
                "SELECT FOLIO_FACTURA FROM AH_PEDIDOS_ENRUTADOS WHERE ESTATUS <> 'CERRADO' AND CHOFER_ID = "+ choferId +        
                ")"+
                " ORDER BY B.ATRASO"*/
            );
            //preparedStatement.setInt(1, choferId);  
            
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CobranzaRefactor cobranza = new CobranzaRefactor();
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
                if (resultSet.getString("NOMBRE_CONSIG") == null)
                    cobranza.setNombreConsignatario("");
                else
                    cobranza.setNombreConsignatario(resultSet.getString("NOMBRE_CONSIG"));
                listaCobranza.add(cobranza);
            }
             System.out.println( gson.toJson(listaCobranza));
            return gson.toJson(listaCobranza);
        } catch (SQLException exception) {
            Resources.logger.error("SUCEDIO UNA EXEPCION al recuperar cobranza por chofer: " + exception.getMessage());     
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    public String cobranzaRutasXAgenteDiario(int vendedorId) throws SQLException {        
        try {                                                            
            PreparedStatement preparedStatement = conexion.prepareStatement(   
                "SELECT DOCTO_CC_ID FROM CXC_EN_RUTA_AGENTE_DIARIO WHERE VENDEDOR_ID = ? " +
                " AND FECHA_ASIGNACION = CURRENT_DATE"        
            );
            preparedStatement.setInt(1, vendedorId);
            ResultSet resultSet = preparedStatement.executeQuery();    
            Boolean cxcEncontardas = false;
            String doctosCCId = "";
            while (resultSet.next()) {                  
                doctosCCId = doctosCCId + resultSet.getInt("DOCTO_CC_ID");                
                doctosCCId = doctosCCId + ",";
                cxcEncontardas = true;
            }  
            
            List<CobranzaRefactor> listaCobranza = new ArrayList<CobranzaRefactor>();
            
            if (cxcEncontardas) {
                doctosCCId = doctosCCId.substring(0, doctosCCId.length() - 1); 

                preparedStatement = conexion.prepareStatement(     
                    //"SELECT C.CLIENTE_ID, C.NOMBRE, B.DOCTO_CC_ID, B.FOLIO, DCC.FECHA AS FECHA_ELABORACION, " +
                    "SELECT C.CLIENTE_ID, C.NOMBRE, B.DOCTO_CC_ID, B.FOLIO, B.FECHA_ELABORACION, " +
                    "B.FECHA_VENCIMIENTO, B.IMPORTE_CARGO, B.SALDO_CARGO, B.ATRASO, B.CONCEPTO_CC_ID, " +
                    "coalesce(DC.NOMBRE_CONSIG, 'Dirección principal') NOMBRE_CONSIG " +
                    "FROM CLIENTES C " +
                    "LEFT JOIN CARGOS_CLIENTE_AH(C.cliente_id,current_date,current_date,'N','S')B on 1=1 " +
                    //"INNER JOIN DOCTOS_CC DCC ON DCC.DOCTO_CC_ID=B.DOCTO_CC_ID " +
                    "left join doctos_entre_sis ds on (ds.docto_dest_id=B.docto_cc_id and CLAVE_SIS_DEST='CC' and  CLAVE_SIS_FTE='VE' and ds.tipo_docto='C') " +
                    "left JOIN DOCTOS_VE DVE ON (DVE.docto_ve_id = ds.docto_fte_id) " +
                    "left JOIN DIRS_CLIENTES DC ON DC.DIR_CLI_ID = DVE.DIR_CLI_ID " +
                    "WHERE B.DOCTO_CC_ID IS NOT NULL " +
                    "AND B.DOCTO_CC_ID IN (" + doctosCCId + ") " +                
                    " ORDER BY B.ATRASO"
                );
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    CobranzaRefactor cobranza = new CobranzaRefactor();
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
                    if (resultSet.getString("NOMBRE_CONSIG") == null)
                        cobranza.setNombreConsignatario("");
                    else
                        cobranza.setNombreConsignatario(resultSet.getString("NOMBRE_CONSIG"));
                    listaCobranza.add(cobranza);
                }
            }
            return gson.toJson(listaCobranza);
        } catch (SQLException exception) {
            Resources.logger.error("SUCEDIO UNA EXEPCION al recuperar cobranza por agente por día: " + exception.getMessage());     
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    public ResponseRequest createArticulosPromVta45(String jsonArticulosVtaProm) {        
        ResponseRequest responseRequest = new ResponseRequest();  
        Type collectionType = new TypeToken<ArrayList<ArticuloPromedioVenta45POST>>() {}.getType();
        Resources.logger.info("HASTA ACA TODO VA BIEN");
        ArrayList<ArticuloPromedioVenta45POST> listaArticuloPromedioVenta45 = gson.fromJson(jsonArticulosVtaProm, collectionType);
        Resources.logger.info("HASTA ACA TODO VA BIEN" + new Gson().toJson(listaArticuloPromedioVenta45));
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "DELETE FROM ARTS_PROM_VTA_45"
            );   
            preparedStatement.executeUpdate();
            for (ArticuloPromedioVenta45POST articulo : listaArticuloPromedioVenta45) {
                preparedStatement = conexion.prepareStatement(
                    "SELECT CA.ARTICULO_ID FROM CLAVES_ARTICULOS CA " +
                    "INNER JOIN ROLES_CLAVES_ARTICULOS RCA ON RCA.ROL_CLAVE_ART_ID=CA.ROL_CLAVE_ART_ID " +
                    "WHERE CA.CLAVE_ARTICULO = ? AND RCA.ROL_CLAVE_ART_ID = 17"
                );                
                preparedStatement.setString(1, articulo.getCodigoArticulo());    
                int articuloId = 0;
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    articuloId = resultSet.getInt("ARTICULO_ID");
                }
                
                preparedStatement = conexion.prepareStatement(
                    "INSERT INTO ARTS_PROM_VTA_45 (ARTICULO_ID, CODIGO_ARTICULO, PROMEDIO_VENTA) VALUES" + 
                    "(?, ?, ?)"
                );            
                preparedStatement.setInt(1, articuloId);                  
                preparedStatement.setString(2, articulo.getCodigoArticulo());                   
                preparedStatement.setDouble(3, articulo.getPromedioVenta());   
                preparedStatement.executeUpdate();
            }
             
            Resources.logger.info("SE TERMINO D EINSERTAR TODO");
            return responseRequest.response(ResponseRequest.DataStatus.OK, null, "Articulos venta promedio grabados correctamente");            
        }catch(Exception exception){
            Resources.logger.error("SUCEDIO UNA EXEPCION en la cabecera createVisitasClientes: " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al crear createArticulosPromVta45 " + exception.getMessage());
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
   
    public ResponseRequest getLocalizacionMaps(int vendedorId, String fechaInicial, String fechaFinal) throws SQLException {        
        Resources.logger.info("createLocalizacionMaps");        
        ResponseRequest responseRequest = new ResponseRequest();      
        
        ArrayList<LocalizacionMaps> listaLocalizacionMaps = new ArrayList<LocalizacionMaps>();
        Utilerias utilerias = new Utilerias();  
        try {       
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT GC.ID, GC.CLIENTE_ID, C.NOMBRE, GC.LATITUD, GC.LONGITUD, GC.FECHA, GC.HORA, GC.PROCESO, GC.VENDEDOR_ID, V.NOMBRE AS NOMBRE_VENDEDOR, GC.MONTO_REALIZADO " +
                "FROM GEOLOCALIZACION_CLIENTES GC " +
                "LEFT JOIN CLIENTES C ON C.CLIENTE_ID = GC.CLIENTE_ID " +       
                "LEFT JOIN VENDEDORES V ON V.VENDEDOR_ID = GC.VENDEDOR_ID " +
                "WHERE GC.VENDEDOR_ID = ? AND (GC.FECHA BETWEEN ? AND ?)"
                //"WHERE GC.VENDEDOR_ID = ? AND (GC.FECHA <= ? AND GC.FECHA >= ?)"
            );            
            preparedStatement.setInt(1, vendedorId);                  
            preparedStatement.setDate(2, utilerias.convertStringToDate(fechaInicial));                   
            preparedStatement.setDate(3, utilerias.convertStringToDate(fechaFinal));   
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LocalizacionMaps localizacionMaps = new LocalizacionMaps();
                localizacionMaps.setId(resultSet.getInt("ID"));
                localizacionMaps.setCliente_id(resultSet.getInt("CLIENTE_ID"));
                localizacionMaps.setNombre_cliente(resultSet.getString("NOMBRE"));
                localizacionMaps.setLatitud(resultSet.getDouble("LATITUD"));
                localizacionMaps.setLongitud(resultSet.getDouble("LONGITUD"));
                localizacionMaps.setFecha(resultSet.getString("FECHA"));
                localizacionMaps.setHora(resultSet.getString("HORA"));
                localizacionMaps.setProceso(resultSet.getString("PROCESO"));
                localizacionMaps.setVendedor_id(resultSet.getInt("VENDEDOR_ID"));
                localizacionMaps.setNombre_vendedor(resultSet.getString("NOMBRE_VENDEDOR"));
                localizacionMaps.setMonto_realizado(resultSet.getDouble("MONTO_REALIZADO"));
                listaLocalizacionMaps.add(localizacionMaps);
            }
            
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaLocalizacionMaps, "Visitas a clientes grabadas correctamente");
            //return listaVisitaClienteGrabado;
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en la cabecera createVisitasClientes: " + exception.getMessage());                   
            exception.printStackTrace();
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al crear visitas - clientes " + exception.getMessage());
        }
    }
    
    public enum TipoPolitica {
        TIPO_CLIENTE, ZONA, CLIENTE, PROMOCION, VOLUMEN;
    }
    
    private String escapeCharacters(String cadena){
        //handling xml special character & in Java String
        //String xmlWithSpecial = "BROCA ROUTER CORTE EN \"V\" 90ø DE 1/2\""; //xml String with & as special characters
        //String xmlWithSpecial = "xxxxxxxxxx C/MOL 1«\" C/BAL"; //xml String with & as special characters
        String xmlWithSpecial = cadena;
        //System.out.println("Original unescaped XML String: " + xmlWithSpecial);
        String scape = StringEscapeUtils.escapeXml(StringEscapeUtils.escapeJava(xmlWithSpecial));
        //System.out.println("Escaped String in Java: "+ scape);
        
        /*String unescape = StringEscapeUtils.unescapeXml(StringEscapeUtils.unescapeJava(scape));
        System.out.println("Unescaped String in Java: "+ unescape);*/
        return scape;
    }
    
    private XMLGregorianCalendar getXMLGregorianCalendar(Timestamp ts){      
        try {            
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(new Date(ts.getTime()));
            XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
            return xmlGregorianCalendar;
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private java.sql.Date convertTimestampToDate(Timestamp ts) {  
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date date = new Date(ts.getTime());
            date = simpleDateFormat.parse(simpleDateFormat.format(date));
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            Resources.logger.info("FECHA:" + ts + "   "  +sqlDate);
            return sqlDate;                       
        } catch (ParseException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    
    
    
    
    /**************************************************************************/
    /********************************D E P R E C A D O S***********************/
    /**************************************************************************/
    
    public String cuentasBancarias() throws SQLException {
        try {
            List<CuentaBancaria> listaCuentasBancarias = new ArrayList<CuentaBancaria>();
                     
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT CB.CUENTA_BAN_ID, CB.BANCO_ID, B.NOMBRE, CB.NUM_CUENTA FROM CUENTAS_BANCARIAS CB " +
                "INNER JOIN BANCOS B ON B.BANCO_ID = CB.BANCO_ID "
            );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CuentaBancaria cuentaBancaria = new CuentaBancaria();
                cuentaBancaria.setCuentaBancariaId(resultSet.getInt("CUENTA_BAN_ID"));
                cuentaBancaria.setBancoId(resultSet.getInt("BANCO_ID"));
                cuentaBancaria.setNombreBanco(resultSet.getString("NOMBRE"));
                cuentaBancaria.setNumeroCuenta(resultSet.getString("NUM_CUENTA"));
                listaCuentasBancarias.add(cuentaBancaria);
            }
            
            return gson.toJson(listaCuentasBancarias);
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
                    "SELECT ARTICULO_ID, NOMBRE_ARTICULO, FACTOR_VENTA, UNIDAD_VENTA, PORCENTAJE_IVA, PORCENTAJE_IEPS, IMPUESTO_USAR, " +
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
                articulo.setPorcentajeIva(resultSet.getDouble("PORCENTAJE_IVA"));
                articulo.setPorcentajeIeps(resultSet.getDouble("PORCENTAJE_IEPS"));
                articulo.setImpuestoUsar(resultSet.getString("IMPUESTO_USAR"));
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
    
    public String politicaDescuentoArticuloCliente() throws SQLException{
        try {                                                        
            List<PoliticaDescuentoArticuloCliente> listaPoliticaDescuentoArticuloCliente = new ArrayList<PoliticaDescuentoArticuloCliente>();
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT PDAC.POLITICA_DSCTO_ART_CLI_ID, PDAC.NOMBRE, DAA.ARTICULO_ID, DAA.DESCUENTO FROM POLITICAS_DSCTOS_ART_CLI PDAC " +
                    "INNER JOIN DSCTOS_ARTCLI_ARTS DAA ON DAA.POLITICA_DSCTO_ART_CLI_ID = PDAC.POLITICA_DSCTO_ART_CLI_ID"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
                        
            while (resultSet.next()) {
                PoliticaDescuentoArticuloCliente politicaDescuentoArticuloCliente = new PoliticaDescuentoArticuloCliente();
                politicaDescuentoArticuloCliente.setPoliticaDescuentoArticuloClienteId(resultSet.getInt("POLITICA_DSCTO_ART_CLI_ID"));
                politicaDescuentoArticuloCliente.setNombre(resultSet.getString("NOMBRE"));
                politicaDescuentoArticuloCliente.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                politicaDescuentoArticuloCliente.setDescuento(resultSet.getDouble("DESCUENTO"));
                
                listaPoliticaDescuentoArticuloCliente.add(politicaDescuentoArticuloCliente);
            }            
            return gson.toJson(listaPoliticaDescuentoArticuloCliente);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());           
            return null;
        } 
    }
    
    public String clientes(int vendedorId) throws SQLException {
        try {
            List<Cliente> listaCliente = new ArrayList<Cliente>();
                
            //ConfiguracionMobil configuracionMobil = configuracionMicrosip();
            configuracionMicrosip();
                        
            String query = "";
            TipoPolitica tipoPolitica = TipoPolitica.valueOf(configuracionMobil.getTipoPoliticaAOperar());
            switch (tipoPolitica)
            {
                case TIPO_CLIENTE:
                    query = "SELECT CLIENTE_ID, CLAVE_CLIENTE, NOMBRE_CLIENTE, " +
                    "TIPO_CLIENTE_ID, TIPO_CLIENTE_NOMBRE, " +
                    "ZONA_CLIENTE_ID, ZONA_CLIENTE_NOMBRE, " +
                    "COBRADOR_ID, " +
                    "COBRADOR_NOMBRE, " +
                    "VENDEDOR_ID, " +
                    "VENDEDOR_NOMBRE, " +
                    "DIR_CLI_ID, RFC_CURP, CALLE, NUM_EXTERIOR, NUM_INTERIOR, COLONIA, POBLACION, CODIGO_POSTAL, TELEFONO1, TELEFONO2 " +
                    ",DESCUENTO, POLITICA_DSCTO_ART_CLI_ID, NOMBRE, LIMITE_CREDITO " +
                    "FROM CLIENTES_POLITICAS(?, ?)";      
                    
                    break;
                case ZONA:
                    break;
                case CLIENTE:
                    break;
                case PROMOCION: //IGUAL QUE VOLUMEN
                    query = "SELECT C.CLIENTE_ID, CC.CLAVE_CLIENTE, C.NOMBRE AS NOMBRE_CLIENTE, " +
                    "C.TIPO_CLIENTE_ID, TC.NOMBRE AS TIPO_CLIENTE_NOMBRE, " +
                    "C.ZONA_CLIENTE_ID, ZC.NOMBRE AS ZONA_CLIENTE_NOMBRE, " +
                    "C.COBRADOR_ID, " +
                    "CB.NOMBRE AS COBRADOR_NOMBRE, " +
                    "C.VENDEDOR_ID, " +
                    "V.NOMBRE AS VENDEDOR_NOMBRE, " +
                    "DC.DIR_CLI_ID, DC.RFC_CURP, DC.CALLE, DC.NUM_EXTERIOR, DC.NUM_INTERIOR, DC.COLONIA, DC.POBLACION, DC.CODIGO_POSTAL, DC.TELEFONO1, DC.TELEFONO2 " +
                    ",PDAC.DESCUENTO, PDAC.POLITICA_DSCTO_ART_CLI_ID, PDAC.NOMBRE, C.LIMITE_CREDITO " +
                    "FROM CLIENTES C " +
                    "INNER JOIN DIRS_CLIENTES DC ON DC.CLIENTE_ID=C.CLIENTE_ID " +
                    "INNER JOIN TIPOS_CLIENTES TC ON TC.TIPO_CLIENTE_ID=C.TIPO_CLIENTE_ID " +
                    "INNER JOIN ZONAS_CLIENTES ZC ON ZC.ZONA_CLIENTE_ID=C.ZONA_CLIENTE_ID " +
                    "INNER JOIN COBRADORES CB ON CB.COBRADOR_ID=C.COBRADOR_ID " +
                    "INNER JOIN VENDEDORES V ON V.VENDEDOR_ID=C.VENDEDOR_ID " +
                    "INNER JOIN CLAVES_CLIENTES CC ON CC.CLIENTE_ID=C.CLIENTE_ID AND CC.ROL_CLAVE_CLI_ID = 2 " +
                    " LEFT JOIN PRECIOS_CLI_CLI PCC ON PCC.CLIENTE_ID=C.CLIENTE_ID AND PCC.PRECIO_EMPRESA_ID = ? " +
                    " LEFT JOIN POLITICAS_DSCTOS_ART_CLI PDAC ON PDAC.POLITICA_DSCTO_ART_CLI_ID = PCC.POLITICA_DSCTO_ART_CLI_ID " +
                    " WHERE C.ESTATUS IN (SELECT ESTATUS FROM CONFIGURACION_CLIENTES) AND C.VENDEDOR_ID = ?" +
                    " AND DC.NOMBRE_CONSIG LIKE '%principal%'";                    
                    break;
                case VOLUMEN: //IGUAL QUE PROMOCION
                    query = "SELECT C.CLIENTE_ID, CC.CLAVE_CLIENTE, C.NOMBRE AS NOMBRE_CLIENTE, " +
                    "C.TIPO_CLIENTE_ID, TC.NOMBRE AS TIPO_CLIENTE_NOMBRE, " +
                    "C.ZONA_CLIENTE_ID, ZC.NOMBRE AS ZONA_CLIENTE_NOMBRE, " +
                    "C.COBRADOR_ID, " +
                    "CB.NOMBRE AS COBRADOR_NOMBRE, " +
                    "C.VENDEDOR_ID, " +
                    "V.NOMBRE AS VENDEDOR_NOMBRE, " +
                    "DC.DIR_CLI_ID, DC.RFC_CURP, DC.CALLE, DC.NUM_EXTERIOR, DC.NUM_INTERIOR, DC.COLONIA, DC.POBLACION, DC.CODIGO_POSTAL, DC.TELEFONO1, DC.TELEFONO2 " +
                    ",PDAC.DESCUENTO, PDAC.POLITICA_DSCTO_ART_CLI_ID, PDAC.NOMBRE, C.LIMITE_CREDITO " +
                    "FROM CLIENTES C " +
                    "INNER JOIN DIRS_CLIENTES DC ON DC.CLIENTE_ID=C.CLIENTE_ID " +
                    "INNER JOIN TIPOS_CLIENTES TC ON TC.TIPO_CLIENTE_ID=C.TIPO_CLIENTE_ID " +
                    "INNER JOIN ZONAS_CLIENTES ZC ON ZC.ZONA_CLIENTE_ID=C.ZONA_CLIENTE_ID " +
                    "INNER JOIN COBRADORES CB ON CB.COBRADOR_ID=C.COBRADOR_ID " +
                    "INNER JOIN VENDEDORES V ON V.VENDEDOR_ID=C.VENDEDOR_ID " +
                    "INNER JOIN CLAVES_CLIENTES CC ON CC.CLIENTE_ID=C.CLIENTE_ID AND CC.ROL_CLAVE_CLI_ID = 2 " +
                    " LEFT JOIN PRECIOS_CLI_CLI PCC ON PCC.CLIENTE_ID=C.CLIENTE_ID AND PCC.PRECIO_EMPRESA_ID = ? " +
                    " LEFT JOIN POLITICAS_DSCTOS_ART_CLI PDAC ON PDAC.POLITICA_DSCTO_ART_CLI_ID = PCC.POLITICA_DSCTO_ART_CLI_ID " +
                    " WHERE C.ESTATUS IN (SELECT ESTATUS FROM CONFIGURACION_CLIENTES) AND C.VENDEDOR_ID = ?" +
                    " AND DC.NOMBRE_CONSIG LIKE '%principal%'";                    
                    break;
            }                                           
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1, configuracionMobil.getPrecioEmpresaId());
            preparedStatement.setInt(2, vendedorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setClienteId(resultSet.getInt("CLIENTE_ID"));
                cliente.setClaveCliente(resultSet.getString("CLAVE_CLIENTE"));
                String nombreCliente = resultSet.getString("NOMBRE_CLIENTE");
                nombreCliente = nombreCliente.replace("'", "\""); 
                nombreCliente = escapeCharacters(nombreCliente);                                    
                cliente.setNombreCliente(nombreCliente.trim());
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
                cliente.setPoblacion(resultSet.getString("POBLACION") != null ? resultSet.getString("POBLACION") : "SP");
                cliente.setCodigoPostal(resultSet.getString("CODIGO_POSTAL"));
                cliente.setTelefono1(resultSet.getString("TELEFONO1") != null ? resultSet.getString("TELEFONO1") : "0000");
                cliente.setTelefono2(resultSet.getString("TELEFONO2") != null ? resultSet.getString("TELEFONO2") : "0000");
                
                cliente.setPorcentajeDescuentoXPolitica(resultSet.getDouble("DESCUENTO"));
                cliente.setPoliticaDescuentoArticuloClienteId(resultSet.getInt("POLITICA_DSCTO_ART_CLI_ID"));                
                cliente.setNombrePolitica(resultSet.getString("NOMBRE") == null ? "SIN_POLITICA" : resultSet.getString("NOMBRE"));                
                
                cliente.setLimiteCredito(resultSet.getDouble("LIMITE_CREDITO"));
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
            
            configuracionMicrosip();              
            String query = "";                         
            TipoPolitica tipoPolitica = TipoPolitica.valueOf(configuracionMobil.getTipoPoliticaAOperar());
            switch (tipoPolitica)
            {
                case TIPO_CLIENTE:
                    query = "SELECT CLIENTE_ID, CLAVE_CLIENTE, NOMBRE_CLIENTE, " +
                    "TIPO_CLIENTE_ID, TIPO_CLIENTE_NOMBRE, " +
                    "ZONA_CLIENTE_ID, ZONA_CLIENTE_NOMBRE, " +
                    "COBRADOR_ID, " +
                    "COBRADOR_NOMBRE, " +
                    "VENDEDOR_ID, " +
                    "VENDEDOR_NOMBRE, " +
                    "DIR_CLI_ID, RFC_CURP, CALLE, NUM_EXTERIOR, NUM_INTERIOR, COLONIA, POBLACION, CODIGO_POSTAL, TELEFONO1, TELEFONO2, " +
                    "DESCUENTO, POLITICA_DSCTO_ART_CLI_ID, NOMBRE, LIMITE_CREDITO " +
                    "FROM CLIENTES_POLITICAS("+ configuracionMobil.getPrecioEmpresaId() +", 0)";                                              
                    break;
                case ZONA:
                    break;
                case CLIENTE:
                    break;
                case PROMOCION://IGUAL A VOLUMEN
                    query = "SELECT C.CLIENTE_ID, CC.CLAVE_CLIENTE, C.NOMBRE AS NOMBRE_CLIENTE, " +
                    "C.TIPO_CLIENTE_ID, TC.NOMBRE AS TIPO_CLIENTE_NOMBRE, " +
                    "C.ZONA_CLIENTE_ID, ZC.NOMBRE AS ZONA_CLIENTE_NOMBRE, " +
                    "C.COBRADOR_ID, " +
                    "CB.NOMBRE AS COBRADOR_NOMBRE, " +
                    "C.VENDEDOR_ID, " +
                    "V.NOMBRE AS VENDEDOR_NOMBRE, " +
                    "DC.DIR_CLI_ID, DC.RFC_CURP, DC.CALLE, DC.NUM_EXTERIOR, DC.NUM_INTERIOR, DC.COLONIA, DC.POBLACION, DC.CODIGO_POSTAL, DC.TELEFONO1, DC.TELEFONO2 " +
                    ",PDAC.DESCUENTO, PDAC.POLITICA_DSCTO_ART_CLI_ID, PDAC.NOMBRE, C.LIMITE_CREDITO " +
                    "FROM CLIENTES C " +
                    "INNER JOIN DIRS_CLIENTES DC ON DC.CLIENTE_ID=C.CLIENTE_ID " +
                    "INNER JOIN TIPOS_CLIENTES TC ON TC.TIPO_CLIENTE_ID=C.TIPO_CLIENTE_ID " +
                    "INNER JOIN ZONAS_CLIENTES ZC ON ZC.ZONA_CLIENTE_ID=C.ZONA_CLIENTE_ID " +
                    "INNER JOIN COBRADORES CB ON CB.COBRADOR_ID=C.COBRADOR_ID " +
                    "INNER JOIN VENDEDORES V ON V.VENDEDOR_ID=C.VENDEDOR_ID " +
                    "INNER JOIN CLAVES_CLIENTES CC ON CC.CLIENTE_ID=C.CLIENTE_ID AND CC.ROL_CLAVE_CLI_ID = 2 " +
                    " LEFT JOIN PRECIOS_CLI_CLI PCC ON PCC.CLIENTE_ID=C.CLIENTE_ID AND PCC.PRECIO_EMPRESA_ID = " + configuracionMobil.getPrecioEmpresaId() +
                    " LEFT JOIN POLITICAS_DSCTOS_ART_CLI PDAC ON PDAC.POLITICA_DSCTO_ART_CLI_ID = PCC.POLITICA_DSCTO_ART_CLI_ID " +
                    " WHERE C.ESTATUS IN (SELECT ESTATUS FROM CONFIGURACION_CLIENTES) " + 
                    " AND DC.NOMBRE_CONSIG LIKE '%principal%'";                    
                    break;
                case VOLUMEN://IGUAL A PROMOCION
                    query = "SELECT C.CLIENTE_ID, CC.CLAVE_CLIENTE, C.NOMBRE AS NOMBRE_CLIENTE, " +
                    "C.TIPO_CLIENTE_ID, TC.NOMBRE AS TIPO_CLIENTE_NOMBRE, " +
                    "C.ZONA_CLIENTE_ID, ZC.NOMBRE AS ZONA_CLIENTE_NOMBRE, " +
                    "C.COBRADOR_ID, " +
                    "CB.NOMBRE AS COBRADOR_NOMBRE, " +
                    "C.VENDEDOR_ID, " +
                    "V.NOMBRE AS VENDEDOR_NOMBRE, " +
                    "DC.DIR_CLI_ID, DC.RFC_CURP, DC.CALLE, DC.NUM_EXTERIOR, DC.NUM_INTERIOR, DC.COLONIA, DC.POBLACION, DC.CODIGO_POSTAL, DC.TELEFONO1, DC.TELEFONO2 " +
                    ",PDAC.DESCUENTO, PDAC.POLITICA_DSCTO_ART_CLI_ID, PDAC.NOMBRE, C.LIMITE_CREDITO " +
                    "FROM CLIENTES C " +
                    "INNER JOIN DIRS_CLIENTES DC ON DC.CLIENTE_ID=C.CLIENTE_ID " +
                    "INNER JOIN TIPOS_CLIENTES TC ON TC.TIPO_CLIENTE_ID=C.TIPO_CLIENTE_ID " +
                    "INNER JOIN ZONAS_CLIENTES ZC ON ZC.ZONA_CLIENTE_ID=C.ZONA_CLIENTE_ID " +
                    "INNER JOIN COBRADORES CB ON CB.COBRADOR_ID=C.COBRADOR_ID " +
                    "INNER JOIN VENDEDORES V ON V.VENDEDOR_ID=C.VENDEDOR_ID " +
                    "INNER JOIN CLAVES_CLIENTES CC ON CC.CLIENTE_ID=C.CLIENTE_ID AND CC.ROL_CLAVE_CLI_ID = 2 " +
                    " LEFT JOIN PRECIOS_CLI_CLI PCC ON PCC.CLIENTE_ID=C.CLIENTE_ID AND PCC.PRECIO_EMPRESA_ID = " + configuracionMobil.getPrecioEmpresaId() +
                    " LEFT JOIN POLITICAS_DSCTOS_ART_CLI PDAC ON PDAC.POLITICA_DSCTO_ART_CLI_ID = PCC.POLITICA_DSCTO_ART_CLI_ID " +
                    " WHERE C.ESTATUS IN (SELECT ESTATUS FROM CONFIGURACION_CLIENTES) " + 
                    " AND DC.NOMBRE_CONSIG LIKE '%principal%'";                    
                    break;
            }            
            PreparedStatement preparedStatement = conexion.prepareStatement(query);            
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setClienteId(resultSet.getInt("CLIENTE_ID"));
                cliente.setClaveCliente(resultSet.getString("CLAVE_CLIENTE"));
                String nombreCliente = resultSet.getString("NOMBRE_CLIENTE");
                nombreCliente = nombreCliente.replace("'", "\""); 
                nombreCliente = escapeCharacters(nombreCliente);                                    
                cliente.setNombreCliente(nombreCliente.trim());
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
                cliente.setPoblacion(resultSet.getString("POBLACION") != null ? resultSet.getString("POBLACION") : "SP");
                cliente.setCodigoPostal(resultSet.getString("CODIGO_POSTAL"));
                cliente.setTelefono1(resultSet.getString("TELEFONO1") != null ? resultSet.getString("TELEFONO1") : "0000");
                cliente.setTelefono2(resultSet.getString("TELEFONO2") != null ? resultSet.getString("TELEFONO2") : "0000");
                
                cliente.setPorcentajeDescuentoXPolitica(resultSet.getDouble("DESCUENTO"));
                cliente.setPoliticaDescuentoArticuloClienteId(resultSet.getInt("POLITICA_DSCTO_ART_CLI_ID"));                
                cliente.setNombrePolitica(resultSet.getString("NOMBRE") == null ? "SIN_POLITICA" : resultSet.getString("NOMBRE"));                
                
                cliente.setLimiteCredito(resultSet.getDouble("LIMITE_CREDITO"));
                listaCliente.add(cliente);
            }
            
            return gson.toJson(listaCliente);
        } catch (SQLException exception) {
            System.out.println("ERROR: Clientes:" + exception.getMessage());
            return null;
        }
    }        
    
    public String cobranza(int vendedorId) throws SQLException {        
        try {
            List<Cobranza> listaCobranza = new ArrayList<Cobranza>();
            
            PreparedStatement preparedStatement = conexion.prepareStatement(
                //"SELECT C.CLIENTE_ID, C.NOMBRE, B.DOCTO_CC_ID, B.FOLIO, DCC.FECHA AS FECHA_ELABORACION, " +
                "SELECT C.CLIENTE_ID, C.NOMBRE, B.DOCTO_CC_ID, B.FOLIO, B.FECHA_ELABORACION, " +    
                "B.FECHA_VENCIMIENTO, B.IMPORTE_CARGO, B.SALDO_CARGO, B.ATRASO, B.CONCEPTO_CC_ID " +
                "FROM CLIENTES C " +
                "LEFT JOIN CARGOS_CLIENTE_AH(C.cliente_id,current_date,current_date,'N','S')B on 1=1 " +
                //"INNER JOIN DOCTOS_CC DCC ON DCC.DOCTO_CC_ID=B.DOCTO_CC_ID " +
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
   
    //ESTE ES EL NUEVO METODO REFACTORIZADO CON ANDROID, NUEVA VERSION
    public ResponseRequest createPedidosDEPRECATEDCONSIGNATARIOS(String jsonPedidos) throws SQLException {
        Resources.logger.info("Se ha llamado al recurso REST mediante una peticion POST (createPedidos)");
        //Resources.logger.info("Lista de pedidos: " + jsonPedidos);
        ResponseRequest responseRequest = new ResponseRequest();
        ArrayList<PedidoGrabado> listaPedidosGrabados = new ArrayList<PedidoGrabado>();
      
        configuracionMicrosip();
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        JsonElement jsonElement = new JsonParser().parse(jsonPedidos);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        Iterator iterator = jsonArray.iterator();  
        
        try {
            Utilerias utilerias = new Utilerias();
            while (iterator.hasNext()) {
                JsonElement JsonElementTmp = (JsonElement) iterator.next();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<MaestroPedido>() {}.getType();
                MaestroPedido objectMaestroPedido = gson.fromJson(JsonElementTmp, collectionType);
                
                //Resources.logger.info("Json item: " + gson.toJson(objectMaeMovCa02));
                
                conexion.setAutoCommit(false);
                
                //---------- Verificar que el pedido no haya sido guardado
                PedidoExistente pedidoExistente = existePedidoGuardado(objectMaestroPedido.getUuid());                
                String serieFolio = pedidoExistente.getFolio();
                if (pedidoExistente.getNumeroMovimiento() == 0) {
                    preparedStatement = conexion.prepareStatement(
                        "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                    );
                    resultSet = preparedStatement.executeQuery();
                    int idAutoIncremental= 0;                
                    while (resultSet.next()) {
                        idAutoIncremental = resultSet.getInt("ID");                    
                    } 
                    Resources.logger.info("idAutoIncremental: " + idAutoIncremental);

                    preparedStatement = conexion.prepareStatement(
                        "SELECT SERIE, MAX(FOLIO) + 1 AS FOLIO FROM VENDEDORES_SERIES_FOLIOS " +
                        " WHERE VENDEDOR_ID = " + objectMaestroPedido.getVendedorId() +
                        " GROUP BY 1"
                    );
                    resultSet = preparedStatement.executeQuery();
                    int consecutivoFolio= 0;
                    String serie = "";
                    while (resultSet.next()) {
                        consecutivoFolio = resultSet.getInt("FOLIO");
                        serie = resultSet.getString("SERIE");
                    }   

                    Resources.logger.info("consecutivoFolio: " + consecutivoFolio + " serie: " + serie);

                    preparedStatement = conexion.prepareStatement(
                        "SELECT COND_PAGO_ID FROM CLIENTES WHERE CLIENTE_ID = " + objectMaestroPedido.getClienteId()
                    );
                    resultSet = preparedStatement.executeQuery();
                    int condicionPagoId= 0;                
                    while (resultSet.next()) {
                        condicionPagoId = resultSet.getInt("COND_PAGO_ID");                    
                    }                              

                    Resources.logger.info("condicionPagoId: " + condicionPagoId);

                    preparedStatement = conexion.prepareStatement(
                        "SELECT VIA_EMBARQUE_ID FROM DIRS_CLIENTES" +
                        " WHERE DIR_CLI_ID = " + objectMaestroPedido.getDireccionClienteId()
                    );
                    resultSet = preparedStatement.executeQuery();
                    int viaEmbarqueId= 0;                
                    while (resultSet.next()) {
                        viaEmbarqueId = resultSet.getInt("VIA_EMBARQUE_ID");                    
                    }
                    Resources.logger.info("viaEmbarqueId: " + viaEmbarqueId);

                    Resources.logger.info("VAMOS A GRABAR CABECERA");

                    String querySegunVersion = "";
                    if (configuracionMobil.getMicrosip2020() == 0) {
                        querySegunVersion = "INSERT INTO DOCTOS_VE (DOCTO_VE_ID, TIPO_DOCTO, SUBTIPO_DOCTO, FOLIO, FECHA, HORA, CLAVE_CLIENTE, CLIENTE_ID, "
                            + "DIR_CLI_ID, DIR_CONSIG_ID, ALMACEN_ID, "
                            + "MONEDA_ID, TIPO_CAMBIO, TIPO_DSCTO, "
                            + "DSCTO_PCTJE, DSCTO_IMPORTE, ESTATUS, APLICADO, FECHA_VIGENCIA_ENTREGA, "
                            + "DESCRIPCION, "                        
                            + "IMPORTE_NETO, FLETES, OTROS_CARGOS, TOTAL_IMPUESTOS, TOTAL_RETENCIONES, TOTAL_ANTICIPOS, "
                            + "PESO_EMBARQUE, FORMA_EMITIDA, CONTABILIZADO, ACREDITAR_CXC, SISTEMA_ORIGEN, COND_PAGO_ID, "                        
                            + "PCTJE_DSCTO_PPAG, VENDEDOR_ID, PCTJE_COMIS, VIA_EMBARQUE_ID, IMPORTE_COBRO,"
                            + "USUARIO_CREADOR, ES_CFD, ENVIADO, "
                            + "CFD_ENVIO_ESPECIAL, CFDI_CERTIFICADO, FECHA_HORA_CREACION, "
                            + "CARGAR_SUN)" +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    } else if (configuracionMobil.getMicrosip2020() != 0) {
                        querySegunVersion = "INSERT INTO DOCTOS_VE (DOCTO_VE_ID, TIPO_DOCTO, SUBTIPO_DOCTO, FOLIO, FECHA, HORA, CLAVE_CLIENTE, CLIENTE_ID, "
                            + "DIR_CLI_ID, DIR_CONSIG_ID, ALMACEN_ID, "
                            + "MONEDA_ID, TIPO_CAMBIO, TIPO_DSCTO, "
                            + "DSCTO_PCTJE, DSCTO_IMPORTE, ESTATUS, APLICADO, FECHA_VIGENCIA_ENTREGA, "
                            + "DESCRIPCION, "                        
                            + "IMPORTE_NETO, FLETES, OTROS_CARGOS, TOTAL_IMPUESTOS, TOTAL_RETENCIONES, TOTAL_ANTICIPOS, "
                            + "PESO_EMBARQUE, FORMA_EMITIDA, CONTABILIZADO, ACREDITAR_CXC, SISTEMA_ORIGEN, COND_PAGO_ID, "                        
                            + "PCTJE_DSCTO_PPAG, VENDEDOR_ID, PCTJE_COMIS, VIA_EMBARQUE_ID, IMPORTE_COBRO,"
                            + "USUARIO_CREADOR, ES_CFD, ENVIADO, "
                            + "CFD_ENVIO_ESPECIAL, CFDI_CERTIFICADO, FECHA_HORA_CREACION, "
                            + "CARGAR_SUN, SUCURSAL_ID)" +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    }

                    PreparedStatement preparedStatementObj = conexion.prepareStatement(querySegunVersion);                 
                    preparedStatementObj.setInt(1, idAutoIncremental);

                    if (configuracionMobil.getComportamientoCaptura().equals(Constants.COTIZACION))
                        preparedStatementObj.setString(2, Constants.COTIZACION);
                    else
                        preparedStatementObj.setString(2, Constants.PEDIDO);

                    preparedStatementObj.setString(3, "N");                
                    int posiciones =  9 - serie.length();//Son 9 la longitud del campo FOLIO
                    serieFolio = serie + StringUtils.leftPad(String.valueOf(consecutivoFolio), posiciones, "0");
                    preparedStatementObj.setString(4, serieFolio);                    
                    preparedStatementObj.setDate(5, utilerias.convertStringToDate(objectMaestroPedido.getFechaPedido()));
                    preparedStatementObj.setTime(6, utilerias.convertStringToTime(objectMaestroPedido.getHoraPedido()));
                    preparedStatementObj.setString(7, objectMaestroPedido.getClaveCliente());
                    preparedStatementObj.setInt(8, objectMaestroPedido.getClienteId());                                                                   
                    if (objectMaestroPedido.getDireccionConsignatarioId()!= 0) {
                        preparedStatementObj.setInt(9, objectMaestroPedido.getDireccionConsignatarioId());  
                        preparedStatementObj.setInt(10, objectMaestroPedido.getDireccionClienteId());
                    } else {
                        preparedStatementObj.setInt(9, objectMaestroPedido.getDireccionClienteId());  
                        preparedStatementObj.setInt(10, objectMaestroPedido.getDireccionClienteId());                                                                                                            
                    }
                    preparedStatementObj.setInt(11, objectMaestroPedido.getAlmacenId());                
                    preparedStatementObj.setInt(12, 1);
                    preparedStatementObj.setDouble(13, 1.00);
                    preparedStatementObj.setString(14, "P");                                  
                    preparedStatementObj.setDouble(15, 0.00);                
                    preparedStatementObj.setDouble(16, 0.00);                
                    preparedStatementObj.setString(17, "P");
                    preparedStatementObj.setString(18, "S");
                    preparedStatementObj.setDate(19, utilerias.convertStringToDate(objectMaestroPedido.getFechaPedido()));//PUEDE SER LA DE HOY                
                    preparedStatementObj.setString(20, objectMaestroPedido.getObservaciones());                               
                    preparedStatementObj.setDouble(21, objectMaestroPedido.getImporteNeto());//CALCULARLO EN LA APP TOTAL NETO
                    preparedStatementObj.setDouble(22, 0.00);
                    preparedStatementObj.setDouble(23, 0.00);
                    preparedStatementObj.setDouble(24, objectMaestroPedido.getTotalImpuestos());
                    preparedStatementObj.setDouble(25, 0.00);
                    preparedStatementObj.setDouble(26, 0.00);
                    preparedStatementObj.setDouble(27, 0.00);
                    preparedStatementObj.setString(28, "N");
                    preparedStatementObj.setString(29, "N");
                    preparedStatementObj.setString(30, "N");
                    preparedStatementObj.setString(31, "VE");
                    preparedStatementObj.setInt(32, condicionPagoId);
                    preparedStatementObj.setDouble(33, 0.00);
                    preparedStatementObj.setInt(34, objectMaestroPedido.getVendedorId());
                    preparedStatementObj.setDouble(35, 0.00);
                    preparedStatementObj.setInt(36, viaEmbarqueId);
                    preparedStatementObj.setDouble(37, 0.00);                                             
                    preparedStatementObj.setString(38, Constants.SYSDBA);
                    preparedStatementObj.setString(39, "N");
                    preparedStatementObj.setString(40, "N");                
                    preparedStatementObj.setString(41, "N");
                    preparedStatementObj.setString(42, "N");                
                    java.util.Date today = new java.util.Date();
                    preparedStatementObj.setTimestamp(43, new java.sql.Timestamp(today.getTime()));
                    preparedStatementObj.setString(44, "S");

                    if (configuracionMobil.getMicrosip2020() != 0) {
                        if (configuracionMobil.getOperaSucursalAlmacen() == 1) {
                            preparedStatement = conexion.prepareStatement(
                                "SELECT SC.SUCURSAL_ID FROM SUCURSALES_CATALOGOS SC " +
                                "INNER JOIN SUCURSALES_CATALOGOS_DET D ON SC.SUCURSAL_CATALOGO_ID = D.SUCURSAL_CATALOGO_ID " +
                                "INNER JOIN CONFIGURACION_ALMACENES CA ON CA.ALMACEN_ID = D.ELEMENTO_ID " +
                                "WHERE SC.NOM_TABLA = 'ALMACENES' AND CA.ALMACEN_ID = " +  objectMaestroPedido.getAlmacenId()
                            );
                            resultSet = preparedStatement.executeQuery();
                            int sucursalId= 0;                
                            while (resultSet.next()) {
                                sucursalId = resultSet.getInt("SUCURSAL_ID");                    
                            }
                            Resources.logger.info("CALCULADA sucursalId: " + sucursalId);
                            preparedStatementObj.setInt(45, sucursalId);
                        } else {
                            Resources.logger.info("CONFIGURACION MOBIL sucursalId: " + configuracionMobil.getSucursalId());
                            preparedStatementObj.setInt(45, configuracionMobil.getSucursalId());
                        }
                    }                        

                    preparedStatementObj.executeUpdate();
                    Resources.logger.info("Grabando en la cabecera");

                    // REFACTOR
                    preparedStatement = conexion.prepareStatement(
                            "UPDATE VENDEDORES_SERIES_FOLIOS SET " +
                            " FOLIO = " + consecutivoFolio + 
                            " WHERE SERIE = '" + serie + "'"
                    );                             
                    preparedStatement.executeUpdate();
                    Resources.logger.info("Actualizando la serie: " + serie + " con el folio: " + consecutivoFolio);

                    // Cambio para el cliente de AFASY 20-NOV-2020
                    if (configuracionMobil.getComportamientoCaptura().equals(Constants.COTIZACION)) {
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO LIBRES_COT_VE (DOCTO_VE_ID, EMITE_FACTURA)" +
                            "VALUES (?,?)"
                        );
                        preparedStatementObj.setInt(1, idAutoIncremental);
                        preparedStatementObj.setString(2, objectMaestroPedido.getCotizacionEmiteFactura());
                        preparedStatementObj.executeUpdate();                                
                        
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO VIT_COTIZ_PROCESADAS (DOCTO_VE_ID, ESTATUS)" +
                            "VALUES (?,?)"
                        );
                        preparedStatementObj.setInt(1, idAutoIncremental);
                        preparedStatementObj.setString(2, "P");
                        preparedStatementObj.executeUpdate();                                                        
                    }                                
                    // REFACTOR
                    //==============================================================================
                    // ========== Se inserta el detalle del pedido en MAEDTMA02 ====================
                    //==============================================================================
                           
                    JsonElement json = new JsonParser().parse(new Gson().toJson(objectMaestroPedido.getListaDetallePedido()));
                    JsonArray array = json.getAsJsonArray();
                    Iterator iterator2 = array.iterator();
                    List<DetallePedido> details = new ArrayList<DetallePedido>();

                    int consecutivo = 0;
                    while (iterator2.hasNext()) {                                
                        consecutivo++;
                        JsonElement json2 = (JsonElement) iterator2.next();                    
                        Type collectionType2 = new TypeToken<DetallePedido>() {}.getType();
                        DetallePedido detallePedido = gson.fromJson(json2, collectionType2);

                        Resources.logger.info(consecutivo + " Detalle del pedido");

                        preparedStatementObj = conexion.prepareStatement(
                        "INSERT INTO DOCTOS_VE_DET (DOCTO_VE_DET_ID, DOCTO_VE_ID, CLAVE_ARTICULO, ARTICULO_ID, "
                        + "UNIDADES, UNIDADES_COMPROM, "
                        + "UNIDADES_SURT_DEV, UNIDADES_A_SURTIR, "
                        + "PRECIO_UNITARIO, PCTJE_DSCTO, DSCTO_ART, PCTJE_DSCTO_CLI, "
                        + "DSCTO_EXTRA, PCTJE_DSCTO_VOL, PCTJE_DSCTO_PROMO, PRECIO_TOTAL_NETO, "
                        + "PCTJE_COMIS, ROL, POSICION)" +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                        );
                        //preparedStatementObj.setInt(1, -1);//REFACTOR JUNIO 2022
                        PreparedStatement preparedStatementDetalle = conexion.prepareStatement(
                            "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                        );
                        ResultSet resultSetDetalle = preparedStatementDetalle.executeQuery();
                        int idAutoIncrementalDetalle= 0;                
                        while (resultSetDetalle.next()) {
                            idAutoIncrementalDetalle = resultSetDetalle.getInt("ID");                    
                        } 
                        preparedStatementObj.setInt(1, idAutoIncrementalDetalle);
                        preparedStatementObj.setInt(2, idAutoIncremental);
                        preparedStatementObj.setString(3, detallePedido.getClave_articulo());
                        preparedStatementObj.setInt(4, detallePedido.getArticulo_id());
                        preparedStatementObj.setDouble(5, detallePedido.getUnidades());
                        preparedStatementObj.setDouble(6, 0.00);                
                        preparedStatementObj.setDouble(7, 0.00);
                        preparedStatementObj.setDouble(8, 0.00);                                                                
                        /**********************************************************/                                           
                        Double precioUnitarioSinImpuesto = detallePedido.getPrecio_unitario_sin_impuestos();
                        preparedStatementObj.setDouble(9, precioUnitarioSinImpuesto);//PRECIO SIN IVA                    
                        Double porcentajeDescuentoTotal = porcentajeDescuentoTotalXArticulo(detallePedido, false);
                        preparedStatementObj.setDouble(10, porcentajeDescuentoTotal);
                        preparedStatementObj.setDouble(11, (precioUnitarioSinImpuesto * detallePedido.getUnidades()) * (porcentajeDescuentoTotal / 100)); //VALOR EN MONEDA DEL DESCUENTO   100-10%=10.00
                        preparedStatementObj.setDouble(12, detallePedido.getPorcentaje_descuento_articulo_cliente());                                          
                        preparedStatementObj.setDouble(13, 0.00);
                        preparedStatementObj.setDouble(14, detallePedido.getTipo_politica().equals("VOLUMEN") ? detallePedido.getPorcentaje_descuento_promocion_volumen() : 0.00);                                          
                        preparedStatementObj.setDouble(15, detallePedido.getTipo_politica().equals("PROMOCION") ? detallePedido.getPorcentaje_descuento_promocion_volumen() : 0.00);                
                        /**********************************************************/
                        preparedStatementObj.setDouble(16, (detallePedido.getPrecio_unitario_sin_impuestos() - detallePedido.getPrecio_unitario_con_descuento_sin_impuestos()) * detallePedido.getUnidades());                 //TODOS LOS VALORES SON SIN IMPUESTOS
                        preparedStatementObj.setDouble(17, 0.00);
                        preparedStatementObj.setString(18, detallePedido.getEs_juego().equals("S") ? "J" : "N");
                        preparedStatementObj.setInt(19, consecutivo);
                        preparedStatementObj.executeUpdate();  
                        
                        //PARA CUANDO EL ARTICULO SEA JUEGO, METER EL DETALLE DEL JUEGO
                        if (detallePedido.getEs_juego().equals("S"))
                            creteDetalleJuego(idAutoIncremental, detallePedido.getArticulo_id());
                    }

                    //---------- SE GRABA EL MOVIMIENTO CON EL DEL MOBIL
                    createPedidoGuardado(objectMaestroPedido.getUuid(), idAutoIncremental, serieFolio);
                }                                                               
                // Este objeto es para agregar el id que se generp en ANDROID y retornarlo en una lista
                PedidoGrabado pedidoGrabadoObject = new PedidoGrabado();
                pedidoGrabadoObject.setId(objectMaestroPedido.getId());
                pedidoGrabadoObject.setNummov(0);                
                pedidoGrabadoObject.setFolio(serieFolio);                
                listaPedidosGrabados.add(pedidoGrabadoObject); 
                //}    
                
                conexion.commit();
            }
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaPedidosGrabados, "Pedidos grabados correctamente");          
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en la cabecera del pedido: " + exception.getMessage());       
            conexion.rollback();
            exception.printStackTrace();  
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al procesar los pedidos " + exception.getMessage());
        }
    }       
    
    public ArrayList<PedidoGrabado> createPedidosRefactor(String jsonPedidos) throws SQLException {
        Resources.logger.info("Se ha llamado al recurso REST mediante una peticion POST (createPedidos)");
        Resources.logger.info("Lista de pedidos: " + jsonPedidos);
        ResponseRequest responseRequest = new ResponseRequest();
        ArrayList<PedidoGrabado> listaPedidosGrabados = new ArrayList<PedidoGrabado>();
      
        configuracionMicrosip();
        
        /*QUEDA DEPRECADO
        Double porcentajeDescuentoArticuloCliente = 0.00;
        if (configuracionMobil.getApplyPoliticaPrecioClientes()== 1)
            porcentajeDescuentoArticuloCliente = getPorcentajeDescuentoArticuloCliente(configuracionMobil.getPrecioEmpresaId());
        */
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        JsonElement jsonElement = new JsonParser().parse(jsonPedidos);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        Iterator iterator = jsonArray.iterator();  
        
        try {
            Utilerias utilerias = new Utilerias();
            while (iterator.hasNext()) {
                JsonElement JsonElementTmp = (JsonElement) iterator.next();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<MaeMovCa02>() {}.getType();
                MaeMovCa02 objectMaeMovCa02 = gson.fromJson(JsonElementTmp, collectionType);
                
                //Resources.logger.info("Json item: " + gson.toJson(objectMaeMovCa02));
                
                conexion.setAutoCommit(false);
                
                //---------- Verificar que el pedido no haya sido guardado
                PedidoExistente pedidoExistente = existePedidoGuardado(objectMaeMovCa02.getUuid());                
                String serieFolio = pedidoExistente.getFolio();
                if (pedidoExistente.getNumeroMovimiento() == 0) {
                    preparedStatement = conexion.prepareStatement(
                        "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                    );
                    resultSet = preparedStatement.executeQuery();
                    int idAutoIncremental= 0;                
                    while (resultSet.next()) {
                        idAutoIncremental = resultSet.getInt("ID");                    
                    } 
                    Resources.logger.info("idAutoIncremental: " + idAutoIncremental);

                    preparedStatement = conexion.prepareStatement(
                        "SELECT SERIE, MAX(FOLIO) + 1 AS FOLIO FROM VENDEDORES_SERIES_FOLIOS " +
                        " WHERE VENDEDOR_ID = " +objectMaeMovCa02.getVendedorId() +
                        " GROUP BY 1"
                    );
                    resultSet = preparedStatement.executeQuery();
                    int consecutivoFolio= 0;
                    String serie = "";
                    while (resultSet.next()) {
                        consecutivoFolio = resultSet.getInt("FOLIO");
                        serie = resultSet.getString("SERIE");
                    }   

                    Resources.logger.info("consecutivoFolio: " + consecutivoFolio + " serie: " + serie);

                    preparedStatement = conexion.prepareStatement(
                        "SELECT COND_PAGO_ID FROM CLIENTES WHERE CLIENTE_ID = " +  objectMaeMovCa02.getClienteId()
                    );
                    resultSet = preparedStatement.executeQuery();
                    int condicionPagoId= 0;                
                    while (resultSet.next()) {
                        condicionPagoId = resultSet.getInt("COND_PAGO_ID");                    
                    }                              

                    Resources.logger.info("condicionPagoId: " + condicionPagoId);

                    preparedStatement = conexion.prepareStatement(
                        "SELECT VIA_EMBARQUE_ID FROM DIRS_CLIENTES" +
                        " WHERE DIR_CLI_ID = " +  objectMaeMovCa02.getDireccionClienteId()
                    );
                    resultSet = preparedStatement.executeQuery();
                    int viaEmbarqueId= 0;                
                    while (resultSet.next()) {
                        viaEmbarqueId = resultSet.getInt("VIA_EMBARQUE_ID");                    
                    }
                    Resources.logger.info("viaEmbarqueId: " + viaEmbarqueId);

                    Resources.logger.info("VAMOS A GRABAR CABECERA");

                    String querySegunVersion = "";
                    if (configuracionMobil.getMicrosip2020() == 0) {
                        querySegunVersion = "INSERT INTO DOCTOS_VE (DOCTO_VE_ID, TIPO_DOCTO, SUBTIPO_DOCTO, FOLIO, FECHA, HORA, CLAVE_CLIENTE, CLIENTE_ID, "
                            + "DIR_CLI_ID, DIR_CONSIG_ID, ALMACEN_ID, "
                            + "MONEDA_ID, TIPO_CAMBIO, TIPO_DSCTO, "
                            + "DSCTO_PCTJE, DSCTO_IMPORTE, ESTATUS, APLICADO, FECHA_VIGENCIA_ENTREGA, "
                            + "DESCRIPCION, "                        
                            + "IMPORTE_NETO, FLETES, OTROS_CARGOS, TOTAL_IMPUESTOS, TOTAL_RETENCIONES, TOTAL_ANTICIPOS, "
                            + "PESO_EMBARQUE, FORMA_EMITIDA, CONTABILIZADO, ACREDITAR_CXC, SISTEMA_ORIGEN, COND_PAGO_ID, "                        
                            + "PCTJE_DSCTO_PPAG, VENDEDOR_ID, PCTJE_COMIS, VIA_EMBARQUE_ID, IMPORTE_COBRO,"
                            + "USUARIO_CREADOR, ES_CFD, ENVIADO, "
                            + "CFD_ENVIO_ESPECIAL, CFDI_CERTIFICADO, FECHA_HORA_CREACION, "
                            + "CARGAR_SUN)" +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    } else if (configuracionMobil.getMicrosip2020() != 0) {
                        querySegunVersion = "INSERT INTO DOCTOS_VE (DOCTO_VE_ID, TIPO_DOCTO, SUBTIPO_DOCTO, FOLIO, FECHA, HORA, CLAVE_CLIENTE, CLIENTE_ID, "
                            + "DIR_CLI_ID, DIR_CONSIG_ID, ALMACEN_ID, "
                            + "MONEDA_ID, TIPO_CAMBIO, TIPO_DSCTO, "
                            + "DSCTO_PCTJE, DSCTO_IMPORTE, ESTATUS, APLICADO, FECHA_VIGENCIA_ENTREGA, "
                            + "DESCRIPCION, "                        
                            + "IMPORTE_NETO, FLETES, OTROS_CARGOS, TOTAL_IMPUESTOS, TOTAL_RETENCIONES, TOTAL_ANTICIPOS, "
                            + "PESO_EMBARQUE, FORMA_EMITIDA, CONTABILIZADO, ACREDITAR_CXC, SISTEMA_ORIGEN, COND_PAGO_ID, "                        
                            + "PCTJE_DSCTO_PPAG, VENDEDOR_ID, PCTJE_COMIS, VIA_EMBARQUE_ID, IMPORTE_COBRO,"
                            + "USUARIO_CREADOR, ES_CFD, ENVIADO, "
                            + "CFD_ENVIO_ESPECIAL, CFDI_CERTIFICADO, FECHA_HORA_CREACION, "
                            + "CARGAR_SUN, SUCURSAL_ID)" +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    }

                    PreparedStatement preparedStatementObj = conexion.prepareStatement(querySegunVersion);                 
                    preparedStatementObj.setInt(1, idAutoIncremental);

                    if (configuracionMobil.getComportamientoCaptura().equals(Constants.COTIZACION))
                        preparedStatementObj.setString(2, Constants.COTIZACION);
                    else
                        preparedStatementObj.setString(2, Constants.PEDIDO);

                    preparedStatementObj.setString(3, "N");                
                    int posiciones =  9 - serie.length();//Son 9 la longitud del campo FOLIO
                    serieFolio = serie + StringUtils.leftPad(String.valueOf(consecutivoFolio), posiciones, "0");
                    preparedStatementObj.setString(4, serieFolio);                    
                    preparedStatementObj.setDate(5, utilerias.convertStringToDate(objectMaeMovCa02.getFcapmov()));
                    preparedStatementObj.setTime(6, utilerias.convertStringToTime(objectMaeMovCa02.getHoramov()));
                    Resources.logger.info("HORA: " + utilerias.convertStringToTime(objectMaeMovCa02.getHoramov()));
                    preparedStatementObj.setString(7, objectMaeMovCa02.getNumcte());
                    preparedStatementObj.setInt(8, objectMaeMovCa02.getClienteId());                                                                   
                    if (objectMaeMovCa02.getDirConsigId() != 0) {
                        preparedStatementObj.setInt(9, objectMaeMovCa02.getDirConsigId());  
                        preparedStatementObj.setInt(10, objectMaeMovCa02.getDireccionClienteId());
                    } else {
                        preparedStatementObj.setInt(9, objectMaeMovCa02.getDireccionClienteId());  
                        preparedStatementObj.setInt(10, objectMaeMovCa02.getDireccionClienteId());                                                                                                            
                    }
                    preparedStatementObj.setInt(11, objectMaeMovCa02.getNumalm());                
                    preparedStatementObj.setInt(12, 1);
                    preparedStatementObj.setDouble(13, 1.00);
                    preparedStatementObj.setString(14, "P");                                  
                    preparedStatementObj.setDouble(15, 0.00);                
                    preparedStatementObj.setDouble(16, 0.00);                
                    preparedStatementObj.setString(17, "P");
                    preparedStatementObj.setString(18, "S");
                    preparedStatementObj.setDate(19, utilerias.convertStringToDate(objectMaeMovCa02.getFcapmov()));//PUEDE SER LA DE HOY                
                    preparedStatementObj.setString(20, objectMaeMovCa02.getObservaciones());                               
                    preparedStatementObj.setDouble(21, objectMaeMovCa02.getImpmov());//CALCULARLO EN LA APP TOTAL NETO
                    preparedStatementObj.setDouble(22, 0.00);
                    preparedStatementObj.setDouble(23, 0.00);
                    preparedStatementObj.setDouble(24, objectMaeMovCa02.getIvatmov());
                    preparedStatementObj.setDouble(25, 0.00);
                    preparedStatementObj.setDouble(26, 0.00);
                    preparedStatementObj.setDouble(27, 0.00);
                    preparedStatementObj.setString(28, "N");
                    preparedStatementObj.setString(29, "N");
                    preparedStatementObj.setString(30, "N");
                    preparedStatementObj.setString(31, "VE");
                    preparedStatementObj.setInt(32, condicionPagoId);
                    preparedStatementObj.setDouble(33, 0.00);
                    preparedStatementObj.setInt(34, objectMaeMovCa02.getVendedorId());
                    preparedStatementObj.setDouble(35, 0.00);
                    preparedStatementObj.setInt(36, viaEmbarqueId);
                    preparedStatementObj.setDouble(37, 0.00);                                             
                    preparedStatementObj.setString(38, Constants.SYSDBA);
                    preparedStatementObj.setString(39, "N");
                    preparedStatementObj.setString(40, "N");                
                    preparedStatementObj.setString(41, "N");
                    preparedStatementObj.setString(42, "N");                
                    java.util.Date today = new java.util.Date();
                    preparedStatementObj.setTimestamp(43, new java.sql.Timestamp(today.getTime()));
                    preparedStatementObj.setString(44, "S");

                    if (configuracionMobil.getMicrosip2020() != 0) {
                        if (configuracionMobil.getOperaSucursalAlmacen() == 1) {
                            preparedStatement = conexion.prepareStatement(
                                "SELECT SC.SUCURSAL_ID FROM SUCURSALES_CATALOGOS SC " +
                                "INNER JOIN SUCURSALES_CATALOGOS_DET D ON SC.SUCURSAL_CATALOGO_ID = D.SUCURSAL_CATALOGO_ID " +
                                "INNER JOIN CONFIGURACION_ALMACENES CA ON CA.ALMACEN_ID = D.ELEMENTO_ID " +
                                "WHERE SC.NOM_TABLA = 'ALMACENES' AND CA.ALMACEN_ID = " +  objectMaeMovCa02.getNumalm()
                            );
                            resultSet = preparedStatement.executeQuery();
                            int sucursalId= 0;                
                            while (resultSet.next()) {
                                sucursalId = resultSet.getInt("SUCURSAL_ID");                    
                            }
                            Resources.logger.info("CALCULADA sucursalId: " + sucursalId);
                            preparedStatementObj.setInt(45, sucursalId);
                        } else {
                            Resources.logger.info("CONFIGURACION MOBIL sucursalId: " + configuracionMobil.getSucursalId());
                            preparedStatementObj.setInt(45, configuracionMobil.getSucursalId());
                        }
                    }

                    preparedStatementObj.executeUpdate();
                    Resources.logger.info("Grabando en la cabecera");

                    // REFACTOR
                    preparedStatement = conexion.prepareStatement(
                            "UPDATE VENDEDORES_SERIES_FOLIOS SET " +
                            " FOLIO = " + consecutivoFolio + 
                            " WHERE SERIE = '" + serie + "'"
                    );                             
                    preparedStatement.executeUpdate();
                    Resources.logger.info("Actualizando la serie: " + serie + " con el folio: " + consecutivoFolio);

                    // Cambio para el cliente de AFASY 20-NOV-2020
                    if (configuracionMobil.getComportamientoCaptura().equals(Constants.COTIZACION)) {
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO LIBRES_COT_VE (DOCTO_VE_ID, EMITE_FACTURA)" +
                            "VALUES (?,?)"
                        );
                        preparedStatementObj.setInt(1, idAutoIncremental);
                        preparedStatementObj.setString(2, objectMaeMovCa02.getCotizacionEmiteFactura());
                        preparedStatementObj.executeUpdate();                                
                        
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO VIT_COTIZ_PROCESADAS (DOCTO_VE_ID, ESTATUS)" +
                            "VALUES (?,?)"
                        );
                        preparedStatementObj.setInt(1, idAutoIncremental);
                        preparedStatementObj.setString(2, "P");
                        preparedStatementObj.executeUpdate();                                                        
                    }                                
                    // REFACTOR
                    //==============================================================================
                    // ========== Se inserta el detalle del pedido en MAEDTMA02 ====================
                    //==============================================================================
                           
                    JsonElement json = new JsonParser().parse(new Gson().toJson(objectMaeMovCa02.getListaDetallePedido()));
                    JsonArray array = json.getAsJsonArray();
                    Iterator iterator2 = array.iterator();
                    List<DetallePedido> details = new ArrayList<DetallePedido>();

                    int consecutivo = 0;
                    while (iterator2.hasNext()) {                                
                        consecutivo++;
                        JsonElement json2 = (JsonElement) iterator2.next();                    
                        Type collectionType2 = new TypeToken<DetallePedido>() {}.getType();
                        DetallePedido detallePedido = gson.fromJson(json2, collectionType2);

                        Resources.logger.info(consecutivo + " Detalle del pedido");

                        preparedStatementObj = conexion.prepareStatement(
                        "INSERT INTO DOCTOS_VE_DET (DOCTO_VE_DET_ID, DOCTO_VE_ID, CLAVE_ARTICULO, ARTICULO_ID, "
                        + "UNIDADES, UNIDADES_COMPROM, "
                        + "UNIDADES_SURT_DEV, UNIDADES_A_SURTIR, "
                        + "PRECIO_UNITARIO, PCTJE_DSCTO, DSCTO_ART, PCTJE_DSCTO_CLI, "
                        + "DSCTO_EXTRA, PCTJE_DSCTO_VOL, PCTJE_DSCTO_PROMO, PRECIO_TOTAL_NETO, "
                        + "PCTJE_COMIS, ROL, POSICION)" +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                        );                    
                        preparedStatementObj.setInt(1, -1);
                        preparedStatementObj.setInt(2, idAutoIncremental);
                        preparedStatementObj.setString(3, detallePedido.getClave_articulo());
                        preparedStatementObj.setInt(4, detallePedido.getArticulo_id());
                        preparedStatementObj.setDouble(5, detallePedido.getUnidades());
                        preparedStatementObj.setDouble(6, 0.00);                
                        preparedStatementObj.setDouble(7, 0.00);
                        preparedStatementObj.setDouble(8, 0.00);                                                                
                        /**********************************************************/                                           
                        Double precioUnitarioSinImpuesto = detallePedido.getPrecio_unitario_sin_impuestos();
                        preparedStatementObj.setDouble(9, precioUnitarioSinImpuesto);//PRECIO SIN IVA                    
                        Double porcentajeDescuentoTotal = porcentajeDescuentoTotalXArticulo(detallePedido, false);
                        preparedStatementObj.setDouble(10, porcentajeDescuentoTotal);
                        preparedStatementObj.setDouble(11, (precioUnitarioSinImpuesto * detallePedido.getUnidades()) * (porcentajeDescuentoTotal / 100)); //VALOR EN MONEDA DEL DESCUENTO   100-10%=10.00
                        preparedStatementObj.setDouble(12, detallePedido.getPorcentaje_descuento_articulo_cliente());                                          
                        preparedStatementObj.setDouble(13, 0.00);
                        preparedStatementObj.setDouble(14, detallePedido.getTipo_politica().equals("VOLUMEN") ? detallePedido.getPorcentaje_descuento_promocion_volumen() : 0.00);                                          
                        preparedStatementObj.setDouble(15, detallePedido.getTipo_politica().equals("PROMOCION") ? detallePedido.getPorcentaje_descuento_promocion_volumen() : 0.00);                
                        /**********************************************************/
                        preparedStatementObj.setDouble(16, (detallePedido.getPrecio_unitario_sin_impuestos() - detallePedido.getPrecio_unitario_con_descuento_sin_impuestos()) * detallePedido.getUnidades());                 //TODOS LOS VALORES SON SIN IMPUESTOS
                        preparedStatementObj.setDouble(17, 0.00);
                        preparedStatementObj.setString(18, detallePedido.getEs_juego().equals("S") ? "J" : "N");
                        preparedStatementObj.setInt(19, consecutivo);
                        preparedStatementObj.executeUpdate();  
                        
                        //PARA CUANDO EL ARTICULO SEA JUEGO, METER EL DETALLE DEL JUEGO
                        if (detallePedido.getEs_juego().equals("S"))
                            creteDetalleJuego(idAutoIncremental, detallePedido.getArticulo_id());
                    }

                    //---------- SE GRABA EL MOVIMIENTO CON EL DEL MOBIL
                    createPedidoGuardado(objectMaeMovCa02.getUuid(), idAutoIncremental, serieFolio);
                }                                                               
                // Este objeto es para agregar el id que se generp en ANDROID y retornarlo en una lista
                PedidoGrabado pedidoGrabadoObject = new PedidoGrabado();
                pedidoGrabadoObject.setId(objectMaeMovCa02.getId());
                pedidoGrabadoObject.setNummov(objectMaeMovCa02.getNummov());                
                pedidoGrabadoObject.setFolio(serieFolio);                
                listaPedidosGrabados.add(pedidoGrabadoObject); 
                //}    
                
                conexion.commit();
            }
            return listaPedidosGrabados;
            //return responseRequest.response(ResponseRequest.DataStatus.OK, listaPedidosGrabados, "Pedidos grabados correctamente");          
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en la cabecera del pedido: " + exception.getMessage());       
            conexion.rollback();
            exception.printStackTrace();  
            //return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al procesar los pedidos " + exception.getMessage());
        }/*finally {  
            return listaPedidosGrabados;
        }*/
        return null;
    }
  
    private Double getPorcentajeDescuentoArticuloCliente(int precioEmpresaId) {
        try {                    
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT PDAC.DESCUENTO FROM POLITICAS_PRECIOS_CLIENTES C " +
                "INNER JOIN POLITICAS_DSCTOS_ART_CLI PDAC ON C.politica_dscto_art_cli_id= PDAC.politica_dscto_art_cli_id " +
                "WHERE C.PRECIO_EMPRESA_ID = ?	"
            );
            preparedStatement.setInt(1, precioEmpresaId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            Double porcentajeDescuento = 0.00;
            while (resultSet.next()) {
                porcentajeDescuento = resultSet.getDouble("DESCUENTO");
            }            
            return porcentajeDescuento;           
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());           
            return null;
        }
    }
    
    public ResponseRequest createCobrosXDepositarRefactor (String jsonString) throws SQLException {
        Resources.logger.info("Entrando a crear la cobranza:");
                
        ResponseRequest responseRequest = new ResponseRequest();
        List<CobroXDepositarGrabado> listaCobroXDepositarGrabado = new ArrayList<CobroXDepositarGrabado>();
        
        Gson gson = new Gson();
        
        configuracionMicrosip();
     
        Utilerias utilerias = new Utilerias();
        try {            
            Type type = new TypeToken<CobroXDepositar>(){}.getType();
            CobroXDepositar cobroXDepositar = gson.fromJson(jsonString, type);
            
            conexion.setAutoCommit(false);            
                        
            List<ComplementoXml> listaComplementoXml = new ArrayList<>();                        
            for (AbonoMaestroEntity abonoMaestroEntity : cobroXDepositar.getListaAbonosParaMicrosip()) {
                ComplementoXml complementoXml = new ComplementoXml();                
                
                /**************GENERO EL ID QUE TOMARA DOCTOS_CC***************/
                PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                );
                ResultSet resultSet = preparedStatement.executeQuery();
                int idAutoIncremental= 0;                
                while (resultSet.next()) {
                    idAutoIncremental = resultSet.getInt("ID");                    
                }
                /**************GENERO EL ID QUE TOMARA DOCTOS_CC***************/
                
                /**********GENERO EL folio ÚLTIMO QUE TOMARA DOCTOS_CC*********/
                preparedStatement = conexion.prepareStatement(
                    "SELECT GEN_ID(ID_FOLIO_TEMP,1)AS ID FROM RDB$DATABASE"
                );
                resultSet = preparedStatement.executeQuery();
                int folioUltimo = 0;
                while (resultSet.next()) {
                    folioUltimo = resultSet.getInt("ID");
                }
                /**********GENERO EL folio ÚLTIMO QUE TOMARA DOCTOS_CC*********/

                /*********************LUGAR_EXPEDICION*************************/
                String lugarExpedicionQuery = "";
                if (configuracionMobil.getMicrosip2020() == 0)
                    lugarExpedicionQuery = "SELECT LUGAR_EXPEDICION_ID FROM LUGARES_EXPEDICION";
                else if (configuracionMobil.getMicrosip2020() != 0)
                    lugarExpedicionQuery = "SELECT LUGAR_EXPEDICION_ID FROM SUCURSALES WHERE SUCURSAL_ID = " + configuracionMobil.getSucursalId();
                
                preparedStatement = conexion.prepareStatement(lugarExpedicionQuery);
                resultSet = preparedStatement.executeQuery();
                int LUGAR_EXPEDICION_ID = 0;
                while (resultSet.next()) {
                    LUGAR_EXPEDICION_ID = resultSet.getInt("LUGAR_EXPEDICION_ID");
                }
                /*********************LUGAR_EXPEDICION*************************/

                String querySegunVersion = "";
                if (configuracionMobil.getMicrosip2020() == 0) {
                    querySegunVersion = "INSERT INTO DOCTOS_CC(DOCTO_CC_ID, CONCEPTO_CC_ID, FOLIO, NATURALEZA_CONCEPTO, FECHA, HORA, CLAVE_CLIENTE, "
                            + "IMPORTE_COBRO, CLIENTE_ID, TIPO_CAMBIO, CANCELADO, APLICADO, DESCRIPCION, COBRADOR_ID, FORMA_EMITIDA,"
                            + "CONTABILIZADO, CONTABILIZADO_GYP, COND_PAGO_ID, SISTEMA_ORIGEN, ESTATUS, ESTATUS_ANT, ES_CFD, TIENE_ANTICIPO,"
                            + "MODALIDAD_FACTURACION, ENVIADO, FECHA_HORA_ENVIO, CFDI_CERTIFICADO, INTEG_BA, CONTABILIZADO_BA, LUGAR_EXPEDICION_ID, FECHA_APLICACION)"
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                } else if (configuracionMobil.getMicrosip2020() != 0) {
                    querySegunVersion = "INSERT INTO DOCTOS_CC(DOCTO_CC_ID, CONCEPTO_CC_ID, FOLIO, NATURALEZA_CONCEPTO, FECHA, HORA, CLAVE_CLIENTE, "
                            + "IMPORTE_COBRO, CLIENTE_ID, TIPO_CAMBIO, CANCELADO, APLICADO, DESCRIPCION, COBRADOR_ID, FORMA_EMITIDA,"
                            + "CONTABILIZADO, CONTABILIZADO_GYP, COND_PAGO_ID, SISTEMA_ORIGEN, ESTATUS, ESTATUS_ANT, ES_CFD, TIENE_ANTICIPO,"
                            + "MODALIDAD_FACTURACION, ENVIADO, FECHA_HORA_ENVIO, CFDI_CERTIFICADO, INTEG_BA, CONTABILIZADO_BA, LUGAR_EXPEDICION_ID, FECHA_APLICACION, SUCURSAL_ID)"
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                }
                PreparedStatement preparedStatementObj = conexion.prepareStatement(querySegunVersion);
                preparedStatementObj.setInt(1, idAutoIncremental);
                preparedStatementObj.setInt(2, configuracionMobil.getConceptoCCId());
                preparedStatementObj.setString(3, "Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0"));                
                preparedStatementObj.setString(4, "R");
                preparedStatementObj.setDate(5, utilerias.convertStringToDate(abonoMaestroEntity.getFechaAbono()));
                preparedStatementObj.setTime(6, utilerias.convertStringToTime(abonoMaestroEntity.getHoraAbono()));
                preparedStatementObj.setString(7, abonoMaestroEntity.getClaveCliente());
                preparedStatementObj.setDouble(8, 0.00);
                preparedStatementObj.setInt(9, abonoMaestroEntity.getClienteId());
                preparedStatementObj.setDouble(10, 1.00);
                preparedStatementObj.setString(11, "N");
                preparedStatementObj.setString(12, "N");
                preparedStatementObj.setString(13, abonoMaestroEntity.getDescripcion());
                preparedStatementObj.setInt(14, abonoMaestroEntity.getCobradorId());
                preparedStatementObj.setString(15, "N");
                preparedStatementObj.setString(16, "N");
                preparedStatementObj.setString(17, "N");
                preparedStatementObj.setInt(18, configuracionMobil.getCondicionPagoId());
                preparedStatementObj.setString(19, "CC");
                preparedStatementObj.setString(20, "P");
                preparedStatementObj.setString(21, "N");
                preparedStatementObj.setString(22, "N");
                preparedStatementObj.setString(23, "N");
                preparedStatementObj.setString(24, "PREIMP");
                preparedStatementObj.setString(25, "N");
                java.util.Date today = new java.util.Date();
                Timestamp timestamp = new java.sql.Timestamp(today.getTime());
                XMLGregorianCalendar xmlGregorianCalendar = getXMLGregorianCalendar(timestamp);                  
                preparedStatementObj.setTimestamp(26, timestamp);
                preparedStatementObj.setString(27, "N");
                preparedStatementObj.setString(28, "N");
                preparedStatementObj.setString(29, "N");
                preparedStatementObj.setInt(30, LUGAR_EXPEDICION_ID);
                preparedStatementObj.setDate(31, utilerias.convertStringToDate(abonoMaestroEntity.getFechaAbono()));
                if (configuracionMobil.getMicrosip2020() != 0)
                    preparedStatementObj.setInt(32, configuracionMobil.getSucursalId());
                preparedStatementObj.executeUpdate();
                Resources.logger.info("Save table [DOCTOS_CC] id: " + idAutoIncremental + " folio: " + "Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0"));                
                
                complementoXml.setDoctoCCId(idAutoIncremental);
                complementoXml.setClienteId(abonoMaestroEntity.getClienteId());
                complementoXml.setFechaHoraEnvioTimestamp(timestamp);
                complementoXml.setFechaDate(convertTimestampToDate(timestamp));                
                
                preparedStatementObj = conexion.prepareStatement(
                    "INSERT INTO FORMAS_COBRO_DOCTOS " +
                    "(FORMA_COBRO_DOC_ID, NOM_TABLA_DOCTOS, DOCTO_ID, FORMA_COBRO_ID, " +
                    "NUM_CTA_PAGO, CLAVE_SIS_FORMA_COB, REFERENCIA, IMPORTE) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
                );
                preparedStatementObj.setInt(1, -1);
                preparedStatementObj.setString(2, "DOCTOS_CC");
                preparedStatementObj.setInt(3, idAutoIncremental);
                preparedStatementObj.setInt(4, abonoMaestroEntity.getFormaCobroCCId());
                preparedStatementObj.setString(5, "");
                preparedStatementObj.setString(6, "CC");
                preparedStatementObj.setString(7, "Abono: $" + abonoMaestroEntity.getAbonoTotal());
                preparedStatementObj.setDouble(8, 0.00);
                preparedStatementObj.executeUpdate();
                Resources.logger.info("Save tabla [FORMAS_COBRO_DOCTOS]");
                
                complementoXml.setFormaCobroId(abonoMaestroEntity.getFormaCobroCCId());
                
                List<ComplementoXmlDetalle> listaComplementoXmlDetalle = new ArrayList<>();
                for (AbonoDetalleEntity abonoDetalleEntity : abonoMaestroEntity.getAbonoDetalleEntity()) {
                    ComplementoXmlDetalle complementoXmlDetalle = new ComplementoXmlDetalle();
                    preparedStatementObj = conexion.prepareStatement(
                        "INSERT INTO IMPORTES_DOCTOS_CC(IMPTE_DOCTO_CC_ID, DOCTO_CC_ID, FECHA, " +
                        "CANCELADO, APLICADO, ESTATUS, TIPO_IMPTE, DOCTO_CC_ACR_ID, IMPORTE, " +
                        "IMPUESTO, IVA_RETENIDO, ISR_RETENIDO, DSCTO_PPAG, PCTJE_COMIS_COB)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                    );
                    preparedStatementObj.setInt(1, -1);
                    preparedStatementObj.setInt(2, idAutoIncremental);
                    preparedStatementObj.setDate(3, utilerias.convertStringToDate(abonoMaestroEntity.getFechaAbono()));
                    preparedStatementObj.setString(4, "N");
                    preparedStatementObj.setString(5, "N");
                    preparedStatementObj.setString(6, "P");
                    preparedStatementObj.setString(7, "R");
                    preparedStatementObj.setInt(8, abonoDetalleEntity.getDoctoCCId());                
                    preparedStatementObj.setDouble(9, abonoDetalleEntity.getImporteAbono());
                    preparedStatementObj.setDouble(10, 0.00);
                    preparedStatementObj.setDouble(11, 0.00);
                    preparedStatementObj.setDouble(12, 0.00);
                    preparedStatementObj.setDouble(13, 0.00);
                    preparedStatementObj.setDouble(14, 0.00);
                    preparedStatementObj.executeUpdate();               
                    Resources.logger.info("Save tabla [IMPORTES_DOCTOS_CC]");             
                    
                    String requiereComplementoPagos = cargoRequiereComplementoPagos(abonoDetalleEntity.getDoctoCCId());
                    Resources.logger.info("[cargoRequiereComplementoPagos]" + abonoDetalleEntity.getDoctoCCId() + "  " + requiereComplementoPagos);
                    if ("S".equals(requiereComplementoPagos.trim())) {
                        complementoXmlDetalle.setDoctoCCPadreId(abonoDetalleEntity.getDoctoCCId());
                        complementoXmlDetalle.setImporteAbono(abonoDetalleEntity.getImporteAbono());
                        listaComplementoXmlDetalle.add(complementoXmlDetalle);
                    }
                }
                
                Double importeTotal = 0.00;
                if (listaComplementoXmlDetalle.size() > 0)                    
                    importeTotal = listaComplementoXmlDetalle.stream().mapToDouble(pojo -> pojo.getImporteAbono()).sum();                
                
                if (importeTotal > 0){
                    complementoXml.setImporteTotal(importeTotal);
                    complementoXml.setListaComplementoXmlDetalle(listaComplementoXmlDetalle);
                    complementoXml.setFolioFiscalId(configuracionMobil.getFolioFiscalId());
                    complementoXml.setSerieConceptoCC(configuracionMobil.getSerieConceptoCC());
                    int folioConceptoCC = 0;
                    complementoXml.setFolio(folioConceptoCC);

                    preparedStatementObj = conexion.prepareStatement(
                        "UPDATE DOCTOS_CC SET APLICADO = ?, MODALIDAD_FACTURACION = ?,  USO_CFDI = ? WHERE DOCTO_CC_ID = ?"
                    );
                    preparedStatementObj.setString(1, "S");
                    preparedStatementObj.setString(2, "CFDI");                                                            
                    //preparedStatementObj.setString(3, "P01");
                    preparedStatementObj.setString(3, "CP01");
                    preparedStatementObj.setInt(4, idAutoIncremental);
                    preparedStatementObj.executeUpdate();
                    
                    listaComplementoXml.add(complementoXml);                
                } else {
                    preparedStatementObj = conexion.prepareStatement(
                        "UPDATE DOCTOS_CC SET APLICADO = ? WHERE DOCTO_CC_ID = ?"
                    );
                    preparedStatementObj.setString(1, "S");
                    preparedStatementObj.setInt(2, idAutoIncremental);
                    preparedStatementObj.executeUpdate();
                }
                Resources.logger.info("Update table [DOCTOS_CC]");                                               
                
                CobroXDepositarGrabado cobroXDepositarGrabado = new CobroXDepositarGrabado();
                cobroXDepositarGrabado.setId(abonoMaestroEntity.getId());
                listaCobroXDepositarGrabado.add(cobroXDepositarGrabado);
            }
            
            if (configuracionMobil.getControlaSerieFolioCXC() == 1) {                
                PreparedStatement preparedStatement = conexion.prepareStatement(
                    "UPDATE SERIES_FOLIOS_CXC SET FOLIO = ?, SERIE = ? WHERE COBRADOR_ID = ?"
                );
                preparedStatement.setInt(1, cobroXDepositar.getSerieFolioCXC().getFolio());
                preparedStatement.setString(2, cobroXDepositar.getSerieFolioCXC().getSerie());
                preparedStatement.setInt(3, cobroXDepositar.getSerieFolioCXC().getCobradorId());
                preparedStatement.executeUpdate();            
            }           
            
            conexion.commit();
            
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaCobroXDepositarGrabado, "Cobros x depositar grabados correctamente");  
        }catch(SQLException exception){
            Resources.logger.error("Excepcion en createCobrosXDepositar: " + exception.getMessage());            
            try {                
                listaCobroXDepositarGrabado = new ArrayList<>();
                conexion.rollback();
                exception.printStackTrace();
                return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al procesar Cobros x depositar " + exception.getMessage());
            }
            catch (SQLException se) {
                listaCobroXDepositarGrabado = new ArrayList<>();
                se.printStackTrace();
                return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al procesar Cobros x depositar " + se.getMessage());
            }            
        }
    }

    public List<CobroXDepositarGrabado> createCobrosXDepositarRefactorDeprecado (String jsonString) throws SQLException {
        Resources.logger.info("Entrando a crear la cobranza:");
                
        Gson gson = new Gson();
        
        configuracionMicrosip();
        
        List<CobroXDepositarGrabado> listaCobroXDepositarGrabado = new ArrayList<CobroXDepositarGrabado>();
                
        Utilerias utilerias = new Utilerias();
        try {            
            Type type = new TypeToken<CobroXDepositar>(){}.getType();
            CobroXDepositar cobroXDepositar = gson.fromJson(jsonString, type);
            
            conexion.setAutoCommit(false);            
                        
            List<ComplementoXml> listaComplementoXml = new ArrayList<>();                        
            for (AbonoMaestroEntity abonoMaestroEntity : cobroXDepositar.getListaAbonosParaMicrosip()) {
                ComplementoXml complementoXml = new ComplementoXml();                
                
                PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                );
                ResultSet resultSet = preparedStatement.executeQuery();
                int idAutoIncremental= 0;                
                while (resultSet.next()) {
                    idAutoIncremental = resultSet.getInt("ID");                    
                }     
                
                preparedStatement = conexion.prepareStatement(
                        "SELECT GEN_ID(ID_FOLIO_TEMP,1)AS ID FROM RDB$DATABASE"
                );
                resultSet = preparedStatement.executeQuery();
                int folioUltimo = 0;
                while (resultSet.next()) {
                    folioUltimo = resultSet.getInt("ID");
                }
                //Resources.logger.info("Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0"));

                String lugarExpedicionQuery = "";
                if (configuracionMobil.getMicrosip2020() == 0) {
                    lugarExpedicionQuery = "SELECT LUGAR_EXPEDICION_ID FROM LUGARES_EXPEDICION";
                } else if (configuracionMobil.getMicrosip2020() != 0) {
                    lugarExpedicionQuery = "SELECT LUGAR_EXPEDICION_ID FROM SUCURSALES WHERE SUCURSAL_ID = " + configuracionMobil.getSucursalId();
                }
                preparedStatement = conexion.prepareStatement(
                    lugarExpedicionQuery
                );
                resultSet = preparedStatement.executeQuery();
                int LUGAR_EXPEDICION_ID = 0;
                while (resultSet.next()) {
                    LUGAR_EXPEDICION_ID = resultSet.getInt("LUGAR_EXPEDICION_ID");
                }
            
                /*preparedStatement = conexion.prepareStatement(
                        "SELECT LUGAR_EXPEDICION_ID FROM LUGARES_EXPEDICION"
                );
                resultSet = preparedStatement.executeQuery();
                int LUGAR_EXPEDICION_ID = 0;
                while (resultSet.next()) {
                    LUGAR_EXPEDICION_ID = resultSet.getInt("LUGAR_EXPEDICION_ID");
                }*/

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
                preparedStatementObj.setInt(2, configuracionMobil.getConceptoCCId());
                preparedStatementObj.setString(3, "Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0"));                
                preparedStatementObj.setString(4, "R");
                preparedStatementObj.setDate(5, utilerias.convertStringToDate(abonoMaestroEntity.getFechaAbono()));
                preparedStatementObj.setTime(6, utilerias.convertStringToTime(abonoMaestroEntity.getHoraAbono()));
                preparedStatementObj.setString(7, abonoMaestroEntity.getClaveCliente());
                preparedStatementObj.setDouble(8, 0.00);
                preparedStatementObj.setInt(9, abonoMaestroEntity.getClienteId());
                preparedStatementObj.setDouble(10, 1.00);
                preparedStatementObj.setString(11, "N");
                preparedStatementObj.setString(12, "N");
                preparedStatementObj.setString(13, abonoMaestroEntity.getDescripcion());
                //preparedStatementObj.setString(13, "Abono: $" + abonoMaestroEntity.getAbonoTotal() + ". " + abonoMaestroEntity.getAbonoDetalleEntity().size() + " documentos");
                preparedStatementObj.setInt(14, abonoMaestroEntity.getCobradorId());
                preparedStatementObj.setString(15, "N");
                preparedStatementObj.setString(16, "N");
                preparedStatementObj.setString(17, "N");
                preparedStatementObj.setInt(18, configuracionMobil.getCondicionPagoId());//REFACTOR
                preparedStatementObj.setString(19, "CC");
                preparedStatementObj.setString(20, "P");
                preparedStatementObj.setString(21, "N");
                preparedStatementObj.setString(22, "N");
                preparedStatementObj.setString(23, "N");
                preparedStatementObj.setString(24, "PREIMP");
                preparedStatementObj.setString(25, "N");
                java.util.Date today = new java.util.Date();
                Timestamp timestamp = new java.sql.Timestamp(today.getTime());
                XMLGregorianCalendar xmlGregorianCalendar = getXMLGregorianCalendar(timestamp);                  
                preparedStatementObj.setTimestamp(26, timestamp);
                //preparedStatementObj.setTimestamp(26, new java.sql.Timestamp(today.getTime()));
                preparedStatementObj.setString(27, "N");
                preparedStatementObj.setString(28, "N");
                preparedStatementObj.setString(29, "N");
                preparedStatementObj.setInt(30, LUGAR_EXPEDICION_ID);
                preparedStatementObj.setDate(31, utilerias.convertStringToDate(abonoMaestroEntity.getFechaAbono()));
                if (configuracionMobil.getMicrosip2020() != 0) {
                    preparedStatementObj.setInt(32, configuracionMobil.getSucursalId());
                }
                preparedStatementObj.executeUpdate();
                Resources.logger.info("Save table [DOCTOS_CC] id: " + idAutoIncremental + " folio: " + "Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0"));                
                
                complementoXml.setDoctoCCId(idAutoIncremental);
                complementoXml.setClienteId(abonoMaestroEntity.getClienteId());
                //complementoXml.setFechaHoraEnvioGregorian(xmlGregorianCalendar);
                complementoXml.setFechaHoraEnvioTimestamp(timestamp);
                complementoXml.setFechaDate(convertTimestampToDate(timestamp));                
                
                preparedStatementObj = conexion.prepareStatement(
                        "INSERT INTO FORMAS_COBRO_DOCTOS "
                        + "(FORMA_COBRO_DOC_ID, NOM_TABLA_DOCTOS, DOCTO_ID, FORMA_COBRO_ID, "
                        + "NUM_CTA_PAGO, CLAVE_SIS_FORMA_COB, REFERENCIA, IMPORTE)"
                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
                );
                preparedStatementObj.setInt(1, -1);
                preparedStatementObj.setString(2, "DOCTOS_CC");
                preparedStatementObj.setInt(3, idAutoIncremental);
                preparedStatementObj.setInt(4, abonoMaestroEntity.getFormaCobroCCId());
                preparedStatementObj.setString(5, "");
                preparedStatementObj.setString(6, "CC");
                //preparedStatementObj.setString(7, "Abono: $" + abonoMaestroEntity.getAbonoTotal() + ". " + abonoMaestroEntity.getAbonoDetalleEntity().size() + " documentos");
                preparedStatementObj.setString(7, "Abono: $" + abonoMaestroEntity.getAbonoTotal());
                preparedStatementObj.setDouble(8, 0.00);
                preparedStatementObj.executeUpdate();
                Resources.logger.info("Save tabla [FORMAS_COBRO_DOCTOS]");
                
                complementoXml.setFormaCobroId(abonoMaestroEntity.getFormaCobroCCId());
                
                List<ComplementoXmlDetalle> listaComplementoXmlDetalle = new ArrayList<>();
                for (AbonoDetalleEntity abonoDetalleEntity : abonoMaestroEntity.getAbonoDetalleEntity()) {
                    
                    
                    ComplementoXmlDetalle complementoXmlDetalle = new ComplementoXmlDetalle();
                    preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO IMPORTES_DOCTOS_CC(IMPTE_DOCTO_CC_ID, DOCTO_CC_ID, FECHA, "
                          + "CANCELADO, APLICADO, ESTATUS, TIPO_IMPTE, DOCTO_CC_ACR_ID, IMPORTE, "
                          + "IMPUESTO, IVA_RETENIDO, ISR_RETENIDO, DSCTO_PPAG, PCTJE_COMIS_COB)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                    );
                    preparedStatementObj.setInt(1, -1);
                    preparedStatementObj.setInt(2, idAutoIncremental);
                    preparedStatementObj.setDate(3, utilerias.convertStringToDate(abonoMaestroEntity.getFechaAbono()));
                    preparedStatementObj.setString(4, "N");
                    preparedStatementObj.setString(5, "N");
                    preparedStatementObj.setString(6, "P");
                    preparedStatementObj.setString(7, "R");
                    preparedStatementObj.setInt(8, abonoDetalleEntity.getDoctoCCId());                
                    preparedStatementObj.setDouble(9, abonoDetalleEntity.getImporteAbono());// LO REVISARA DANTE
                    preparedStatementObj.setDouble(10, 0.00); //LO REVISARA DANTE
                    preparedStatementObj.setDouble(11, 0.00);
                    preparedStatementObj.setDouble(12, 0.00);
                    preparedStatementObj.setDouble(13, 0.00);
                    preparedStatementObj.setDouble(14, 0.00);
                    preparedStatementObj.executeUpdate();               
                    Resources.logger.info("Save tabla [IMPORTES_DOCTOS_CC]");                                
                    
                    
                    String requiereComplementoPagos = cargoRequiereComplementoPagos(abonoDetalleEntity.getDoctoCCId());
                    Resources.logger.info("[cargoRequiereComplementoPagos]" + abonoDetalleEntity.getDoctoCCId() + "  " + requiereComplementoPagos);
                    if ("S".equals(requiereComplementoPagos.trim())) {
                        complementoXmlDetalle.setDoctoCCPadreId(abonoDetalleEntity.getDoctoCCId());
                        complementoXmlDetalle.setImporteAbono(abonoDetalleEntity.getImporteAbono());
                        listaComplementoXmlDetalle.add(complementoXmlDetalle);
                    }
                }
                
                Double importeTotal = 0.00;
                if (listaComplementoXmlDetalle.size() > 0)                    
                    importeTotal = listaComplementoXmlDetalle.stream().mapToDouble(pojo -> pojo.getImporteAbono()).sum();                
                
                if (importeTotal > 0){
                    complementoXml.setImporteTotal(importeTotal);
                    complementoXml.setListaComplementoXmlDetalle(listaComplementoXmlDetalle);

                    //COMPLEMENTO DE PAGOS: VERIFICAR SI SE REQUEIRE COMPLEMENTO DE PAGOS
                    //complementoXml.setRequiereComplementoDePagos(cargoRequiereComplementoPagos(idAutoIncremental));

                    complementoXml.setFolioFiscalId(configuracionMobil.getFolioFiscalId());
                    complementoXml.setSerieConceptoCC(configuracionMobil.getSerieConceptoCC());
                    //int folioConceptoCC = siguienteFolioConceptoCC();
                    int folioConceptoCC = 0;
                    complementoXml.setFolio(folioConceptoCC);

                    //if ("S".equals(complementoXml.getRequiereComplementoDePagos().trim())){ FOLIO = ?,
                    preparedStatementObj = conexion.prepareStatement(
                        "UPDATE DOCTOS_CC SET APLICADO = ?, MODALIDAD_FACTURACION = ?,  USO_CFDI = ? WHERE DOCTO_CC_ID = ?"
                    );
                    /*preparedStatementObj.setString(1, "S");
                    preparedStatementObj.setString(2, "CFDI");
                                        
                    int posiciones =  9 - configuracionMobil.getSerieConceptoCC().length();//Son 9 la longitud del campo FOLIO
                    String serieFolioConceptoCC = configuracionMobil.getSerieConceptoCC() + StringUtils.leftPad(String.valueOf(folioConceptoCC), posiciones, "0");
                                        
                    preparedStatementObj.setString(3, serieFolioConceptoCC);
                    preparedStatementObj.setString(4, "P01");
                    preparedStatementObj.setInt(5, idAutoIncremental);*/
                    
                    
                    preparedStatementObj.setString(1, "S");
                    preparedStatementObj.setString(2, "CFDI");                                                            
                    //preparedStatementObj.setString(3, "P01");
                    preparedStatementObj.setString(3, "CP01");
                    preparedStatementObj.setInt(4, idAutoIncremental);
                    preparedStatementObj.executeUpdate();
                    
                    listaComplementoXml.add(complementoXml);
                
                } else {
                    preparedStatementObj = conexion.prepareStatement(
                        "UPDATE DOCTOS_CC SET APLICADO = ? WHERE DOCTO_CC_ID = ?"
                    );
                    preparedStatementObj.setString(1, "S");
                    preparedStatementObj.setInt(2, idAutoIncremental);
                    preparedStatementObj.executeUpdate();
                }
                //listaComplementoXml.add(complementoXml);
                
                /*preparedStatementObj = conexion.prepareStatement(
                    "UPDATE DOCTOS_CC SET APLICADO = ? WHERE DOCTO_CC_ID = ?"
                );
                preparedStatementObj.setString(1, "S");
                preparedStatementObj.setInt(2, idAutoIncremental);
                preparedStatementObj.executeUpdate();*/
                Resources.logger.info("Update table [DOCTOS_CC]");                                               
                
                CobroXDepositarGrabado cobroXDepositarGrabado = new CobroXDepositarGrabado();
                cobroXDepositarGrabado.setId(abonoMaestroEntity.getId());
                listaCobroXDepositarGrabado.add(cobroXDepositarGrabado);
            }
            
            if (configuracionMobil.getControlaSerieFolioCXC() == 1) {                
                PreparedStatement preparedStatement = conexion.prepareStatement(
                    "UPDATE SERIES_FOLIOS_CXC SET FOLIO = ?, SERIE = ? WHERE COBRADOR_ID = ?"
                );
                preparedStatement.setInt(1, cobroXDepositar.getSerieFolioCXC().getFolio());
                preparedStatement.setString(2, cobroXDepositar.getSerieFolioCXC().getSerie());
                preparedStatement.setInt(3, cobroXDepositar.getSerieFolioCXC().getCobradorId());
                preparedStatement.executeUpdate();            
            }
            
            /*if (listaComplementoXml.size() > 0) {
                String listaComplementosXmlSend = new Gson().toJson(listaComplementoXml);
                Resources.logger.info("Complemento pagos: " + listaComplementosXmlSend);
                ThreadComplementoPagoXml threadComplementoPagoXml = 
                        new ThreadComplementoPagoXml(listaComplementosXmlSend);
                threadComplementoPagoXml.start();
            }*/
            
            conexion.commit();
            
            return listaCobroXDepositarGrabado;
        }catch(SQLException exception){
            Resources.logger.error("Excepcion en createCobrosXDepositar: " + exception.getMessage());            
            try {                
                listaCobroXDepositarGrabado = new ArrayList<>();
                conexion.rollback();
                exception.printStackTrace();
                return listaCobroXDepositarGrabado;
            }
            catch (SQLException se) {
                listaCobroXDepositarGrabado = new ArrayList<>();
                se.printStackTrace();
                return listaCobroXDepositarGrabado;
            }            
        }
    }
    
    public List<DepositoGrabado> createDepositos (String jsonString) throws SQLException {
        Resources.logger.info("Entrando a crear la cobranza:" + jsonString);
                
        Gson gson = new Gson();
        
        configuracionMicrosip();
        
        List<DepositoGrabado> listaDepositoGrabado = new ArrayList<DepositoGrabado>();
        
        Utilerias utilerias = new Utilerias();
        try {            
            Type type = new TypeToken<Deposito>(){}.getType();
            Deposito deposito = gson.fromJson(jsonString, type);
            
            conexion.setAutoCommit(false);                        
            
            if (configuracionMobil.getOperaDepositos()== 1) {
                //---------INSERTAR DEPOSITOS CHECAR LA CONFIGURACION --------------

                for(DepositoMaestro depositoMaestro : deposito.getListaDepositosParaMicrosip()) {
                    PreparedStatement preparedStatement = conexion.prepareStatement(
                        "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                    );
                    ResultSet resultSet = preparedStatement.executeQuery();
                    int idAutoIncremental= 0;                
                    while (resultSet.next()) {
                        idAutoIncremental = resultSet.getInt("ID");                    
                    }                
                    //Resources.logger.info("ID GENERADO: " +idAutoIncremental);

                    String query = "INSERT INTO DEPOSITOS_CC " +
                            "(DEPOSITO_CC_ID, FECHA, FORMA_COBRO_CC_ID, SUCURSAL_ID, CUENTA_BAN_ID, " +
                            "REFER_MOVTO_BANCARIO, DESCRIPCION, IMPORTE, TIPO_CAMBIO, APLICADO, ESTATUS, FORMA_EMITIDA, " +
                            "USUARIO_CREADOR, FECHA_HORA_CREACION, USUARIO_AUT_CREACION, FECHA_HORA_ULT_MODIF, FECHA_HORA_CANCELACION) " +
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatementObj = conexion.prepareStatement(query);
                    preparedStatementObj.setInt(1, idAutoIncremental);
                    preparedStatementObj.setDate(2, utilerias.convertStringToDate2(depositoMaestro.getFecha()));
                    preparedStatementObj.setInt(3, depositoMaestro.getFormaCobroCCId());
                    preparedStatementObj.setInt(4, configuracionMobil.getSucursalId());
                    preparedStatementObj.setInt(5, depositoMaestro.getCuentaBancariaId());
                    preparedStatementObj.setString(6, depositoMaestro.getReferencia());                
                    preparedStatementObj.setString(7, depositoMaestro.getDescripcion());                                               
                    preparedStatementObj.setDouble(8, depositoMaestro.getImporte());
                    preparedStatementObj.setDouble(9, 1.00);
                    preparedStatementObj.setString(10, "N");
                    preparedStatementObj.setString(11, "P");
                    preparedStatementObj.setString(12, "N");                
                    preparedStatementObj.setString(13, "SYSDBA");
                    //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
                    Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());                
                    preparedStatementObj.setTimestamp(14, timestamp);
                    preparedStatementObj.setString(15, "SYSDBA"); 
                    preparedStatementObj.setTimestamp(16, timestamp); 
                    preparedStatementObj.setTimestamp(17, timestamp); 

                    preparedStatementObj.executeUpdate();                
                    Resources.logger.info("Save table [DEPOSITOS_CC] id: " + idAutoIncremental);                

                    for (DepositoDetalle detalle : depositoMaestro.getDepositoDetalle()) {
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO DEPOSITOS_CC_DET(DEPOSITO_CC_DET_ID, DEPOSITO_CC_ID, DOCTO_CC_ID)VALUES(?, ?, ?)"
                        );
                        preparedStatementObj.setInt(1, -1);
                        preparedStatementObj.setInt(2, idAutoIncremental);
                        preparedStatementObj.setInt(3, detalle.getDoctoCCId());
                        preparedStatementObj.executeUpdate();               
                        Resources.logger.info("Save table [DEPOSITOS_CC_DET]");                                               
                    }
                    DepositoGrabado depositoGrabado = new DepositoGrabado();
                    depositoGrabado.setId(depositoMaestro.getId());
                    listaDepositoGrabado.add(depositoGrabado);
                }
            }
            
            conexion.commit();
            
            return listaDepositoGrabado;
        }catch(SQLException exception){
            Resources.logger.error("Excepcion en createCobrosXDepositar: " + exception.getMessage());            
            try {                
                listaDepositoGrabado = new ArrayList<>();
                conexion.rollback();
                exception.printStackTrace();
                return listaDepositoGrabado;
            }
            catch (SQLException se) {
                listaDepositoGrabado = new ArrayList<>();
                se.printStackTrace();
                return listaDepositoGrabado;
            }            
        }
    }
    
    public String articulosAlmacenes(String articulo) throws SQLException {
        ArrayList<ArticuloAlmacen> listaArticulosAlmacenes = new ArrayList<ArticuloAlmacen>();
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
    
    public String getArticuloPrecio(String articulo) throws SQLException {
        configuracionMicrosip();
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
                    //"SELECT ARTICULO_ID, NOMBRE_ARTICULO, PRECIO_NETO, CODIGO_ARTICULO, CLAVE_ARTICULO FROM ARTICULOS_MOBIL(?, ?) " +
                    "SELECT ARTICULO_ID, NOMBRE_ARTICULO, PRECIO_NETO, CODIGO_ARTICULO, CLAVE_ARTICULO FROM POLITICAS_ARTS_PROMO_VOL_AH(?, ?, ?) " +
                    " WHERE (UPPER(NOMBRE_ARTICULO) LIKE '" + articulo.toUpperCase() + "%' )" +                 
                    " OR UPPER(CODIGO_ARTICULO) LIKE '" + articulo.toUpperCase() + "%'" +
                    " OR UPPER(CLAVE_ARTICULO) LIKE '" + articulo.toUpperCase() + "%'");
            //preparedStatement.setDate(1, fechaInicioFin.getFechaInicio());
            //preparedStatement.setDate(2, fechaInicioFin.getFechaFin());    
            preparedStatement.setInt(1, configuracionMobil.getPrecioEmpresaId());
            preparedStatement.setDate(2, fechaInicioFin.getFechaInicio());
            preparedStatement.setDate(3, fechaInicioFin.getFechaFin());            
            
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
    
    private PoliticaDescuentoArticuloClienteEntity getPoliticaDescuentoArticuloCliente(int precioEmpresaId, int tipoClienteId) throws SQLException {
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT PCT.POLITICA_DSCTO_ART_CLI_ID, PDAC.NOMBRE " +
                "FROM PRECIOS_CLI_TIPO PCT " +
                "INNER JOIN POLITICAS_PRECIOS_CLIENTES PPC ON PPC.POLITICA_PRECIOS_CLI_ID = PCT.POLITICA_PRECIOS_CLI_ID " +
                "INNER JOIN TIPOS_CLIENTES TC ON TC.TIPO_CLIENTE_ID = PCT.TIPO_CLIENTE_ID " +
                "INNER JOIN PRECIOS_EMPRESA PE ON PE.PRECIO_EMPRESA_ID = PCT.PRECIO_EMPRESA_ID " +
                "INNER JOIN POLITICAS_DSCTOS_ART_CLI PDAC ON PDAC.POLITICA_DSCTO_ART_CLI_ID = PCT.POLITICA_DSCTO_ART_CLI_ID " +
                "WHERE PDAC.ES_DSCTO_EXCLUSIVO = 'S'  AND PPC.HABILITADA = 'S' AND PE.PRECIO_EMPRESA_ID = ? AND TC.TIPO_CLIENTE_ID = ?"                    
            );
            preparedStatement.setInt(1, precioEmpresaId);
            preparedStatement.setInt(2, tipoClienteId);                        
            ResultSet resultSet = preparedStatement.executeQuery();
            PoliticaDescuentoArticuloClienteEntity politicaDescuentoArticuloClienteEntity = new PoliticaDescuentoArticuloClienteEntity();
            while (resultSet.next()) {               
                politicaDescuentoArticuloClienteEntity.setPoliticaDescuentoArticuloClienteId(resultSet.getInt("POLITICA_DSCTO_ART_CLI_ID"));
                politicaDescuentoArticuloClienteEntity.setNombre(resultSet.getString("NOMBRE"));                
            }            
            return politicaDescuentoArticuloClienteEntity;
        } catch (SQLException exception) {
            System.out.println("ERROR: getPoliticaDescuentoArticuloCliente =>" + exception.getMessage());
            Resources.logger.error("error:"+exception.getMessage());
            return null;
        }
    }
    
    public String clientesConsignatarios(int vendedorId) throws SQLException {
        try {
            List<ClienteConsignatario> listaClientesConsignatarios = new ArrayList<ClienteConsignatario>();
                
            configuracionMicrosip();
                        
            String query = 
                    "SELECT DC.CLIENTE_ID, DC.DIR_CLI_ID, DC.NOMBRE_CONSIG FROM CLIENTES C " +
                    "INNER JOIN DIRS_CLIENTES DC ON DC.CLIENTE_ID=C.CLIENTE_ID " +
                    "WHERE DC.ES_DIR_PPAL = 'N' AND C.VENDEDOR_ID = ?";
                                                        
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1, vendedorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ClienteConsignatario clienteConsignatario = new ClienteConsignatario();
                clienteConsignatario.setClienteId(resultSet.getInt("CLIENTE_ID"));
                clienteConsignatario.setDireccionClienteId(resultSet.getInt("DIR_CLI_ID"));
                String nombreConsignatario = resultSet.getString("NOMBRE_CONSIG");                                   
                clienteConsignatario.setNombreConsignatario(nombreConsignatario.trim());
                
                listaClientesConsignatarios.add(clienteConsignatario);
            }
            
            return gson.toJson(listaClientesConsignatarios);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }

    public String clientesConsignatarios() throws SQLException {
        try {
            List<ClienteConsignatario> listaClientesConsignatarios = new ArrayList<ClienteConsignatario>();
                
            configuracionMicrosip();
                        
            String query = 
                    "SELECT DC.CLIENTE_ID, DC.DIR_CLI_ID, DC.NOMBRE_CONSIG FROM CLIENTES C " +
                    "INNER JOIN DIRS_CLIENTES DC ON DC.CLIENTE_ID=C.CLIENTE_ID " +
                    "WHERE DC.ES_DIR_PPAL = 'N'";
                                                        
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ClienteConsignatario clienteConsignatario = new ClienteConsignatario();
                clienteConsignatario.setClienteId(resultSet.getInt("CLIENTE_ID"));
                clienteConsignatario.setDireccionClienteId(resultSet.getInt("DIR_CLI_ID"));
                String nombreConsignatario = resultSet.getString("NOMBRE_CONSIG");                                   
                clienteConsignatario.setNombreConsignatario(nombreConsignatario.trim());
                
                listaClientesConsignatarios.add(clienteConsignatario);
            }
            
            return gson.toJson(listaClientesConsignatarios);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
    
    public String articulosAlmacenes() throws SQLException{
        try {              
            List<ArticuloAlmacenRefactor> listaArticuloAlmacen = new ArrayList<ArticuloAlmacenRefactor>();
          
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT ARTICULO_ID, ALMACEN_ID FROM NIVELES_ARTICULOS "
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ArticuloAlmacenRefactor articuloAlmacenRefactor = new ArticuloAlmacenRefactor();
                articuloAlmacenRefactor.setArticulo_id(resultSet.getInt("ARTICULO_ID"));
                articuloAlmacenRefactor.setAlmacen_id(resultSet.getInt("ALMACEN_ID"));
                listaArticuloAlmacen.add(articuloAlmacenRefactor);
            }
            
            return gson.toJson(listaArticuloAlmacen);
        } catch (SQLException exception) {
            Resources.logger.info("Error al recuperar catálogo de ARTICULOS-ALMACENES: " + exception.getMessage());     
            return null;
        } 
    }
    
    public String vendedores() throws SQLException {        
        try {
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
                        
            return gson.toJson(listaVendedores);
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            return null;
        }
    }
      
    /***************************************************************************
     * 
     * @CATALOGOS PARA LA CONFIGURACION DE MICROSIP
     *
     **************************************************************************/
    public String catalogosConfiguracionEmpresa() {
        CatalogosConfiguracionMobil catalogosConfiguracionMobil = new CatalogosConfiguracionMobil();
        try {
            catalogosConfiguracionMobil.setSucursalesEmpresa(catalogoSucursalesEmpresa());
            catalogosConfiguracionMobil.setPreciosEmpresa(catalogoPreciosEmpresa());
            catalogosConfiguracionMobil.setConceptosCuentasXCobrar(catalogoConceptosCuentasXCobrar());
            catalogosConfiguracionMobil.setCondicionesDePago(catalogoCondicionesDePago());
            catalogosConfiguracionMobil.setRolesClavesArticulos(catalogoRolesClavesArticulos());
            catalogosConfiguracionMobil.setCatalogoAlmacenes(catalogoAlmacenes());
            return gson.toJson(catalogosConfiguracionMobil);            
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            Resources.logger.error("error:"+exception.getMessage());
            return null;
        }
    }
    
    /*1.-*/
    public List<Sucursal> catalogoSucursalesEmpresa() throws SQLException {
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
            
            //return gson.toJson(listaSucursalesEmpresa);
            return listaSucursalesEmpresa;
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            Resources.logger.error("error:"+exception.getMessage());
            return null;
        }
    }
    
    /*2.-*/
    public List<PrecioEmpresa> catalogoPreciosEmpresa() throws SQLException {
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
            
            //return gson.toJson(listaPreciosEmpresa);
            return listaPreciosEmpresa;
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            Resources.logger.error("error:"+exception.getMessage());
            return null;
        }
    }
    
    /*3*/        
    public List<ConceptoCuentaXCobrar> catalogoConceptosCuentasXCobrar() throws SQLException {
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
            
            //return gson.toJson(listaConceptosCuentasXCobrar);
            return listaConceptosCuentasXCobrar;
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            Resources.logger.error("error:"+exception.getMessage());
            return null;
        }
    }
    
    /*4*/
    public List<CondicionPago> catalogoCondicionesDePago() throws SQLException {
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
            
            //return gson.toJson(listaCondicionesDePago);
            return listaCondicionesDePago;
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            Resources.logger.error("error:"+exception.getMessage());
            return null;
        }
    }
      
    /*5*/
    public List<RolClaveArticulo> catalogoRolesClavesArticulos() throws SQLException {
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
            
            //return gson.toJson(listaRolesClavesArticulos);
            return listaRolesClavesArticulos;
        } catch (SQLException exception) {
            System.out.println("==================" + exception.getMessage());
            Resources.logger.error("error:"+exception.getMessage());
            return null;
        }
    }
    
    /*6*/
    public List<Almacen> catalogoAlmacenes() throws SQLException {
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
            
            //return gson.toJson(listaAlmacenes);
            return listaAlmacenes;
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
    
    public Boolean creaConfiguracionMobil(String jsonConfiguracion) {
        System.out.println("Json sin analizar: " + jsonConfiguracion);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            JsonElement jsonElement = new JsonParser().parse(jsonConfiguracion);            
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
                    + "(PRECIO_EMPRESA_ID, CONCEPTO_CUENTA_X_COBRAR_ID, CONDICION_PAGO_ID,"
                    + "ROL_ART_CLAVE_PRINCIPAL_ID, ROL_ART_CLAVE_ALTERNA_ID, ROL_ART_CODIGO_BARRA_ID, ROL_ART_CODIGO_BARRA_INNER_ID, ROL_ART_CODIGO_BARRA_MASTER_ID,"
                    + "MICROSIP_2020, SUCURSAL_ID, APPLY_POLITICA_PRECIO_CLIENTES, COMPORTAMIENTO_CAPTURA, OPERA_DEPOSITOS, TIPO_POLITICA_A_OPERAR, DIAS_HISTORIA, "
                    + "COMPORTAMIENTO_ALMACEN, FOLIO_FISCAL_ID, SERIE_CONCEPTO_CC, OPERA_CONSIGNATARIOS, APPLY_DESC_ARTS_CTES_PROMO, REGLA_GPS, DIAS_GRACIA_ID, "
                    + "OPERA_POLITICAS_X_VOLUMEN, OPERA_POLITICAS_X_PROMOCION, CONTROLA_SERIE_FOLIO_CXC, SINC_EXISTENCIA_ARTS, SINC_ART_CONDICIONADOS, SINC_EXIST_ARTS_CONDICIONADOS, SINC_CXC_X_RUTA, "
                    + "OPERA_SUCURSAL_ALMACEN, FORMA_CAPTURA_PARTIDA, OPERA_MONEDA_EXTRANJERA, DISMINUYE_ABONO_PARA_SALDO, SINCRONIZA_PEDIDO_TR, SINCRONIZA_ABONO_TR, ENVIA_SMS, OPERA_POP) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStatementObj.setInt(1, configuracionMobil.getPrecioEmpresaId());
            preparedStatementObj.setInt(2, configuracionMobil.getConceptoCCId());
            preparedStatementObj.setInt(3, configuracionMobil.getCondicionPagoId());
            preparedStatementObj.setInt(4, configuracionMobil.getRolArticuloClavePrincipalId());
            preparedStatementObj.setInt(5, configuracionMobil.getRolArticuloClaveAlternaId());
            preparedStatementObj.setInt(6, configuracionMobil.getRolArticuloCodigoBarraId());
            preparedStatementObj.setInt(7, configuracionMobil.getRolArticuloCodigoBarraInnerId());
            preparedStatementObj.setInt(8, configuracionMobil.getRolArticuloCodigoBarraMasterId());
            preparedStatementObj.setInt(9, configuracionMobil.getMicrosip2020());
            preparedStatementObj.setInt(10, configuracionMobil.getSucursalId());
            preparedStatementObj.setInt(11, configuracionMobil.getApplyPoliticaPrecioClientes());
            preparedStatementObj.setString(12, configuracionMobil.getComportamientoCaptura());
            preparedStatementObj.setInt(13, configuracionMobil.getOperaDepositos());
            preparedStatementObj.setString(14, configuracionMobil.getTipoPoliticaAOperar());
            preparedStatementObj.setInt(15, configuracionMobil.getDiasHistoria());
            preparedStatementObj.setString(16, configuracionMobil.getComportamientoAlmacen());
            preparedStatementObj.setInt(17, configuracionMobil.getFolioFiscalId());
            preparedStatementObj.setString(18, configuracionMobil.getSerieConceptoCC());
            preparedStatementObj.setInt(19, configuracionMobil.getOperaConsignatarios());
            preparedStatementObj.setInt(20, configuracionMobil.getApplyDescArtsCtsPromo());
            preparedStatementObj.setString(21, configuracionMobil.getReglaGPS());
            preparedStatementObj.setInt(22, configuracionMobil.getDiasGraciaId());
            preparedStatementObj.setInt(23, configuracionMobil.getOperaPoliticasXVolumen());
            preparedStatementObj.setInt(24, configuracionMobil.getOperaPoliticasXPromocion());
            preparedStatementObj.setInt(25, configuracionMobil.getSincExistenciaArts());
            preparedStatementObj.setInt(26, configuracionMobil.getSincArtsCondicionados());
            preparedStatementObj.setInt(27, configuracionMobil.getSincExistArtsCondicionados());
            preparedStatementObj.setInt(28, configuracionMobil.getSincCXCXRuta());
            preparedStatementObj.setInt(29, configuracionMobil.getOperaSucursalAlmacen());
            preparedStatementObj.setString(30, configuracionMobil.getFormaCapturaPartida());
            preparedStatementObj.setInt(31, configuracionMobil.getOperaMonedaExtranjera());
            preparedStatementObj.setInt(32, configuracionMobil.getDisminuyeAbonoParaSaldo());            
            preparedStatementObj.setInt(33, configuracionMobil.getSincronizaPedidoTR());
            preparedStatementObj.setInt(34, configuracionMobil.getSincronizaAbonoTR());
            preparedStatementObj.setInt(35, configuracionMobil.getEnviaSMS());
            preparedStatementObj.setInt(36, configuracionMobil.getOperaPop());
            preparedStatementObj.executeUpdate();                        
            
            for (ConfiguracionAlmacen configuracionAlmacen : configuracionMobil.getConfiguracionAlmacenes()){
                preparedStatementObj = conexion.prepareStatement(
                        "INSERT INTO CONFIGURACION_ALMACENES "
                        + "(ALMACEN_ID, ES_DEFAULT) "
                        + "VALUES(?, ?)"
                );
                preparedStatementObj.setInt(1, configuracionAlmacen.getAlmacenId());
                preparedStatementObj.setInt(2, 0);
                preparedStatementObj.executeUpdate();            
            }
            
            for (ConfiguracionCliente configuracionCliente : configuracionMobil.getConfiguracionClientes()){
                preparedStatementObj = conexion.prepareStatement(
                        "INSERT INTO CONFIGURACION_CLIENTES "
                        + "(ESTATUS) "
                        + "VALUES(?)"
                );
                preparedStatementObj.setString(1, configuracionCliente.getEstatus());                
                preparedStatementObj.executeUpdate();            
            }
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
    
    /***************************************************************************
    ******************************P O P*****************************************
    ***************************************************************************/
    public ResponseRequest reporteClientesPOP(int anioSeleccionado, int numeroTrimestreSeleccionado) throws SQLException {     
        Resources.logger.info("Consultando clientes POP");
                
        ResponseRequest responseRequest = new ResponseRequest();
        List<ClientePOPSencillo> listaClientesPOP = new ArrayList();
        
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT ID, CLIENTE_ID, CLAVE_CLIENTE, NOMBRE_CLIENTE, IMPORTE_SIN_IMPUESTOS, IMPORTE_CON_IMPUESTOS, " +
                "IMPORTE_DOCTO_FTE_SIN_IMPUESTOS, TIPO_CLIENTE, " +
                "DIAS_PLAZO, SUMATORIA_DIAS_TARDADOS_DOCTOS, NUMERO_DOCUMENTOS, PROMEDIO_PONDERADO, " +
                "IMPORTE_POP_GANADO, IMPORTE_POP_GANADO_AJUSTES, ESTATUS_POP, FECHA_BLOQUEO_POP, MONTO_MINIMO_VENTA, ANIO, TRIMESTRE, NOMBRE_VENDEDOR FROM POP_TRIMESTRAL WHERE ANIO = ? AND TRIMESTRE = ?"
            );            
            preparedStatement.setInt(1, anioSeleccionado);
            preparedStatement.setInt(2, numeroTrimestreSeleccionado);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {         
                ClientePOPSencillo clientePOP = new ClientePOPSencillo();
                clientePOP.setId(resultSet.getInt("ID"));
                clientePOP.setClienteId(resultSet.getInt("CLIENTE_ID"));                                                    
                clientePOP.setClaveCliente(resultSet.getString("CLAVE_CLIENTE"));                                                    
                clientePOP.setNombreCliente(resultSet.getString("NOMBRE_CLIENTE"));                                                    
                clientePOP.setImporteSinImpuestos(resultSet.getDouble("IMPORTE_SIN_IMPUESTOS"));                                                    
                clientePOP.setImporteConImpuestos(resultSet.getDouble("IMPORTE_CON_IMPUESTOS"));                                                    
                clientePOP.setImporteDoctoFteSinImpuestos(resultSet.getDouble("IMPORTE_DOCTO_FTE_SIN_IMPUESTOS"));                                                    
                clientePOP.setTipoCliente(resultSet.getString("TIPO_CLIENTE"));                                                    
                clientePOP.setDiasPlazo(resultSet.getInt("DIAS_PLAZO"));                                                    
                clientePOP.setSumatoriaDiasTardadosDocumentos(resultSet.getInt("SUMATORIA_DIAS_TARDADOS_DOCTOS"));                                                    
                clientePOP.setNumeroDocumentos(resultSet.getInt("NUMERO_DOCUMENTOS"));                                                    
                clientePOP.setPromedioPonderado(resultSet.getDouble("PROMEDIO_PONDERADO"));    
                clientePOP.setImportePOPGanado(resultSet.getDouble("IMPORTE_POP_GANADO")); 
                clientePOP.setImportePOPGanadoAjustes(resultSet.getDouble("IMPORTE_POP_GANADO_AJUSTES")); 
                clientePOP.setEstatus(resultSet.getString("ESTATUS_POP")); 
                clientePOP.setFechaBloqueo(resultSet.getDate("FECHA_BLOQUEO_POP")); 
                clientePOP.setMontoMinimoVenta(resultSet.getDouble("MONTO_MINIMO_VENTA")); 
                clientePOP.setAnio(resultSet.getInt("ANIO")); 
                clientePOP.setTrimestre(resultSet.getInt("TRIMESTRE")); 
                clientePOP.setNombreVendedor(resultSet.getString("NOMBRE_VENDEDOR"));
                listaClientesPOP.add(clientePOP);
            }
                                
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaClientesPOP, "Clientes POP consultados correctamente");  
        }catch(SQLException exception){
            Resources.logger.error("Excepción en createCobrosXDepositar: " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar clientes - pop " + exception.getMessage());    
        }
    }
    
    public ResponseRequest clientesPOP(int anioSeleccionado, int numeroTrimestreSeleccionado) throws SQLException {     
        Resources.logger.info("Consultando clientes POP");
                
        ResponseRequest responseRequest = new ResponseRequest();
        List<ClientePOP> listaClientesPOP = new ArrayList();
        
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT ID, CLIENTE_ID, CLAVE_CLIENTE, NOMBRE_CLIENTE, IMPORTE_SIN_IMPUESTOS, IMPORTE_CON_IMPUESTOS, " +
                "IMPORTE_DOCTO_FTE_SIN_IMPUESTOS, TIPO_CLIENTE, " +
                "DIAS_PLAZO, SUMATORIA_DIAS_TARDADOS_DOCTOS, NUMERO_DOCUMENTOS, PROMEDIO_PONDERADO, " +
                "IMPORTE_POP_GANADO, NOMBRE_VENDEDOR FROM POP_TRIMESTRAL WHERE ANIO = ? AND TRIMESTRE = ?"
            );            
            preparedStatement.setInt(1, anioSeleccionado);
            preparedStatement.setInt(2, numeroTrimestreSeleccionado);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {         
                ClientePOP clientePOP = new ClientePOP();
                clientePOP.setId(resultSet.getInt("ID"));
                clientePOP.setClienteId(resultSet.getInt("CLIENTE_ID"));                                                    
                clientePOP.setClaveCliente(resultSet.getString("CLAVE_CLIENTE"));                                                    
                clientePOP.setNombreCliente(resultSet.getString("NOMBRE_CLIENTE"));                                                    
                clientePOP.setImporteSinImpuestos(resultSet.getDouble("IMPORTE_SIN_IMPUESTOS"));                                                    
                clientePOP.setImporteConImpuestos(resultSet.getDouble("IMPORTE_CON_IMPUESTOS"));                                                    
                clientePOP.setImporteDoctoFteSinImpuestos(resultSet.getDouble("IMPORTE_DOCTO_FTE_SIN_IMPUESTOS"));                                                    
                clientePOP.setTipoCliente(resultSet.getString("TIPO_CLIENTE"));                                                    
                clientePOP.setDiasPlazo(resultSet.getInt("DIAS_PLAZO"));                                                    
                clientePOP.setSumatoriaDiasTardadosDocumentos(resultSet.getInt("SUMATORIA_DIAS_TARDADOS_DOCTOS"));                                                    
                clientePOP.setNumeroDocumentos(resultSet.getInt("NUMERO_DOCUMENTOS"));                                                    
                clientePOP.setPromedioPonderado(resultSet.getDouble("PROMEDIO_PONDERADO"));    
                clientePOP.setImportePOPGanado(resultSet.getDouble("IMPORTE_POP_GANADO")); 
                clientePOP.setNombreVendedor(resultSet.getString("NOMBRE_VENDEDOR"));
                listaClientesPOP.add(clientePOP);
            }
            
            List<Integer> ids = listaClientesPOP.stream()
                                  .map(ClientePOP::getId)   // Extract the ID from each Person
                                  .collect(Collectors.toList()); // Collect the results into a new List
            // Convert the array of integers to a comma-separated string
            String queryIds = ids.stream()                       
                                .map(String::valueOf)          // Convert each int to a String
                                .collect(Collectors.joining(","));  // Join them with commas
            
            List<POPDetalle> listaPOPDetalleGeneral = popDetalle(queryIds);
            
            List<ClientePOP> listaClientesPOPCompleta = new ArrayList();
            for (ClientePOP clientePOP : listaClientesPOP) {                
                List<POPDetalle> listaPOPDetalleXCliente = listaPOPDetalleGeneral.stream()
                    .filter(p -> p.getPopTrimestralId() == clientePOP.getId())
                    .collect(Collectors.toList());                
                
                clientePOP.setListaCobranzaXCliente(listaPOPDetalleXCliente);       
                listaClientesPOPCompleta.add(clientePOP);
            }
                        
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaClientesPOPCompleta, "Clientes POP consultados correctamente");  
        }catch(SQLException exception){
            Resources.logger.error("Excepción en createCobrosXDepositar: " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar clientes - pop " + exception.getMessage());    
        }
    }
    
    public List<POPDetalle> popDetalle(String queryIds) throws SQLException {     
        Resources.logger.info("Consultando clientes POP");
                
        ResponseRequest responseRequest = new ResponseRequest();
        List<POPDetalle> listaPOPDetalle = new ArrayList();
        
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT ID, POP_TRIMESTRAL_ID, DOCTO_CC_ID, FOLIO_DOCTO_FUENTE, FECHA_ELABORACION, HORA_ELABORACION, " +
                "FECHA_ULTIMO_PAGO, IMPORTE_SIN_IMPUESTOS, IMPORTE_CON_IMPUESTOS, IMPORTE_DOCTO_FTE_SIN_IMPUESTOS FROM POP_DETALLE WHERE POP_TRIMESTRAL_ID IN (" + queryIds + ")"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {         
                POPDetalle popDetalle = new POPDetalle();
                popDetalle.setId(resultSet.getInt("ID"));
                popDetalle.setPopTrimestralId(resultSet.getInt("POP_TRIMESTRAL_ID"));                                                    
                popDetalle.setDoctoCCId(resultSet.getInt("DOCTO_CC_ID"));                                                    
                popDetalle.setFolioDoctofuente(resultSet.getString("FOLIO_DOCTO_FUENTE"));                                                    
                popDetalle.setFechaElaboracionDoctofuente(resultSet.getDate("FECHA_ELABORACION").toString());
                popDetalle.setHoraElaboracionDoctofuente(resultSet.getTime("HORA_ELABORACION").toString());                                                    
                popDetalle.setFechaUltimoPago(resultSet.getDate("FECHA_ULTIMO_PAGO").toString());                                                    
                popDetalle.setImporteSinImpuestos(resultSet.getDouble("IMPORTE_SIN_IMPUESTOS"));                                                    
                popDetalle.setImporteConImpuestos(resultSet.getDouble("IMPORTE_CON_IMPUESTOS"));                                                    
                popDetalle.setImporteDoctoFteSinImpuestos(resultSet.getDouble("IMPORTE_DOCTO_FTE_SIN_IMPUESTOS"));                                                    
                listaPOPDetalle.add(popDetalle);
            }
            
            return listaPOPDetalle;  
        }catch(SQLException exception){
            Resources.logger.error("Excepción en popDetalle: " + exception.getMessage());            
            return null;    
        }
    }
    
    public ResponseRequest trimestres() throws SQLException {     
        Resources.logger.info("Consultando trimestres");
                
        ResponseRequest responseRequest = new ResponseRequest();
        List<Trimestre> listaTrimestres = new ArrayList();
        
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT ID, NOMBRE, NUMERO, DIA_EJECUCION, MES_EJECUCION FROM TRIMESTRES"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {         
                Trimestre trimestre = new Trimestre();
                trimestre.setId(resultSet.getInt("ID"));
                trimestre.setNombre(resultSet.getString("NOMBRE"));                                                    
                trimestre.setNumero(resultSet.getInt("NUMERO"));                                                    
                trimestre.setDiaEjecucion(resultSet.getInt("DIA_EJECUCION"));                                                    
                trimestre.setMesEjecucion(resultSet.getInt("MES_EJECUCION"));                                                  
                listaTrimestres.add(trimestre);
            }
                
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaTrimestres, "Trimestres consultados correctamente");  
        }catch(SQLException exception){
            Resources.logger.error("Excepción en Trimestres: " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar Trimestres " + exception.getMessage());    
        }
    }
    
    public ResponseRequest porcentajesTrimestral() throws SQLException {     
        Resources.logger.info("Consultando porcentajesTrimestral");
                
        ResponseRequest responseRequest = new ResponseRequest();
        List<PorcentajeTrimestral> listaPorcentajesTrimestres = new ArrayList();
        
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT ID, RANGO_INICIAL, RANGO_FINAL, PORCENTAJE FROM COBRANZA_TRIMESTRAL"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {         
                PorcentajeTrimestral porcentajeTrimestral = new PorcentajeTrimestral();
                porcentajeTrimestral.setId(resultSet.getInt("ID"));
                porcentajeTrimestral.setRangoInicial(resultSet.getDouble("RANGO_INICIAL"));                                                    
                porcentajeTrimestral.setRangoFinal(resultSet.getDouble("RANGO_FINAL"));                                                    
                porcentajeTrimestral.setPorcentaje(resultSet.getDouble("PORCENTAJE"));                                                 
                listaPorcentajesTrimestres.add(porcentajeTrimestral);
            }
                
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaPorcentajesTrimestres, "Porcentajes Trimestres consultados correctamente");  
        }catch(SQLException exception){
            Resources.logger.error("Excepción en Porcentajes Trimestres: " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar Porcentajes Trimestres " + exception.getMessage());    
        }
    }
    
    public ResponseRequest actualizarPorcentajesTrimestres(String jsonString) {
        ResponseRequest responseRequest = new ResponseRequest();
        try {                      
            JSONObject jsonObject = new JSONObject(jsonString);
           
            //Actualiza la tabla
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "UPDATE COBRANZA_TRIMESTRAL SET RANGO_INICIAL=" + jsonObject.getDouble("rangoInicial") + 
                    ", RANGO_FINAL= " + jsonObject.getDouble("rangoFinal") + ", PORCENTAJE= " + jsonObject.getDouble("porcentaje") +
                    " WHERE ID = " + jsonObject.getInt("id") 
            );
            preparedStatement.executeUpdate();   
            Resources.logger.info("UPDATE COBRANZA_TRIMESTRAL SET RANGO_INICIAL=" + jsonObject.getDouble("rangoInicial") + 
                    ", RANGO_FINAL= " + jsonObject.getDouble("rangoFinal") + ", PORCENTAJE= " + jsonObject.getDouble("porcentaje") +
                    " WHERE ID = " + jsonObject.getInt("id") );
            return responseRequest.response(ResponseRequest.DataStatus.OK, "", "Porcentaje actualizado correctamente");   
        } catch (Exception exception) {
            Resources.logger.error("Excepción en actualizarPorcentajesTrimestres: " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al actualizar actualizarPorcentajesTrimestres " + exception.getMessage());  
        }
    }
    
    public ResponseRequest porcentajesDiasPlazo() throws SQLException {     
        Resources.logger.info("Consultando porcentajesDiasPlazo");
                
        ResponseRequest responseRequest = new ResponseRequest();
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT ID, PORCENTAJE FROM PORCENTAJES_DIAS_PLAZO"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            PorcentajeDiaPLazo porcentajeDiaPLazo = new PorcentajeDiaPLazo();
            while (resultSet.next()) {                        
                porcentajeDiaPLazo.setId(resultSet.getInt("ID"));
                porcentajeDiaPLazo.setPorcentaje(resultSet.getDouble("PORCENTAJE"));                                                                  
            }
                
            return responseRequest.response(ResponseRequest.DataStatus.OK, porcentajeDiaPLazo, "porcentajesDiasPlazo consultados correctamente");  
        }catch(SQLException exception){
            Resources.logger.error("Excepción en porcentajesDiasPlazo: " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar porcentajesDiasPlazo " + exception.getMessage());    
        }
    }
    
    public ResponseRequest actualizarPorcentajesDiasPlazo(String jsonString) {
        ResponseRequest responseRequest = new ResponseRequest();
        try {                      
            JSONObject jsonObject = new JSONObject(jsonString);
           
            //Actualiza la tabla
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "UPDATE PORCENTAJES_DIAS_PLAZO SET PORCENTAJE= " + jsonObject.getDouble("porcentaje") +
                    " WHERE ID = " + jsonObject.getInt("id") 
            );
            preparedStatement.executeUpdate();  
            return responseRequest.response(ResponseRequest.DataStatus.OK, "", "Porcentaje actualizado correctamente");   
        } catch (Exception exception) {
            Resources.logger.error("Excepción en actualizarPorcentajesTrimestres: " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al actualizar actualizarPorcentajesDiasPlazo " + exception.getMessage());  
        }
    }
    
    public ResponseRequest girosComerciales() throws SQLException {     
        Resources.logger.info("Consultando girosComerciales");
                
        ResponseRequest responseRequest = new ResponseRequest();
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT ID, NOMBRE FROM GIROS_COMERCIALES"
            );            
            ResultSet resultSet = preparedStatement.executeQuery();
            List<GiroComercial> listaGirosComerciales = new ArrayList();
            while (resultSet.next()) {   
                GiroComercial giroComercial = new GiroComercial();
                giroComercial.setId(resultSet.getInt("ID"));
                giroComercial.setNombre(resultSet.getString("NOMBRE"));                                                                  
                listaGirosComerciales.add(giroComercial);
            }
                
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaGirosComerciales, "girosComerciales consultados correctamente");  
        }catch(SQLException exception){
            Resources.logger.error("Excepción en girosComerciales: " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar girosComerciales " + exception.getMessage());    
        }
    }
    
    public ResponseRequest createGiroComercial(String jsonString) throws SQLException {     
        Resources.logger.info("Creando girosComerciales");

        ResponseRequest responseRequest = new ResponseRequest();
        try {
            JSONObject jsonObject;
            jsonObject = new JSONObject(jsonString);
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "INSERT INTO GIROS_COMERCIALES(NOMBRE) VALUES(?)"
            );
            preparedStatement.setString(1, jsonObject.getString("nombre"));
            preparedStatement.executeUpdate();
            return responseRequest.response(ResponseRequest.DataStatus.OK, "", "girosComerciales consultados correctamente");

        } catch (SQLException exception) {
            Resources.logger.error("Excepción en girosComerciales: " + exception.getMessage());
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al crear girosComerciales " + exception.getMessage());
        } catch (JSONException exception) {
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al crear girosComerciales " + exception.getMessage());
        } 
    }
    
    public ResponseRequest actualizaGiroComercial(String jsonString) {
        ResponseRequest responseRequest = new ResponseRequest();
        try {                      
            JSONObject jsonObject = new JSONObject(jsonString);
           
            //Actualiza la tabla
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "UPDATE GIROS_COMERCIALES SET NOMBRE= '" + jsonObject.getString("nombre") +
                    "' WHERE ID = " + jsonObject.getInt("id") 
            );
            preparedStatement.executeUpdate();  
            return responseRequest.response(ResponseRequest.DataStatus.OK, "", "Giro comercial actualizado correctamente");   
        } catch (Exception exception) {
            Resources.logger.error("Excepción en Giro comercial: " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al actualizar Giro comercial " + exception.getMessage());  
        }
    }
    
    public ResponseRequest eliminaGiroComercial(int id) {
        ResponseRequest responseRequest = new ResponseRequest();
        try {                      
            //Actualiza la tabla
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "DELETE FROM GIROS_COMERCIALES WHERE ID = " + id
            );
            preparedStatement.executeUpdate();  
            return responseRequest.response(ResponseRequest.DataStatus.OK, "", "eliminaGiroComercial eliminado correctamente");   
        } catch (Exception exception) {
            Resources.logger.error("Excepción en eliminaGiroComercial: " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al eliminar eliminaGiroComercial " + exception.getMessage());  
        }
    }
    
    public ResponseRequest detalleDocumentoCXC(String jsonString) throws SQLException {     
        Resources.logger.info("Consultando detalleDocumentoCXC"+ jsonString);
        
        Type type = new TypeToken<ArrayList<Long>>(){}.getType();
        ArrayList<Long> listaIds = new Gson().fromJson(jsonString, type);
        List<String> stringIds = listaIds.stream()
                                            .map(String::valueOf)
                                            .collect(Collectors.toList());
        String ids = String.join(", ", stringIds);
                
        ResponseRequest responseRequest = new ResponseRequest();
        List<DetalleDocumentoCXC> listaDetalleDocumentoCXC = new ArrayList();
        
        
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT DVD.DOCTO_VE_ID, DVD.CLAVE_ARTICULO, DVD.ARTICULO_ID, DVD.UNIDADES, DVD.PRECIO_UNITARIO, DVD.PRECIO_TOTAL_NETO FROM DOCTOS_VE_DET DVD " +
                "INNER JOIN DOCTOS_VE DV ON DV.DOCTO_VE_ID = DVD.DOCTO_VE_ID " +
                "WHERE DVD.DOCTO_VE_ID IN (" + ids + ")"
            );     

            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {         
                DetalleDocumentoCXC detalleDocumentoCXC = new DetalleDocumentoCXC();
                detalleDocumentoCXC.setDoctoVEId(resultSet.getInt("DOCTO_VE_ID"));
                detalleDocumentoCXC.setClaveArticulo(resultSet.getString("CLAVE_ARTICULO"));                                                    
                detalleDocumentoCXC.setArticuloId(resultSet.getInt("ARTICULO_ID"));                                                    
                detalleDocumentoCXC.setUnidades(resultSet.getInt("UNIDADES"));                                                    
                detalleDocumentoCXC.setPrecioUnitario(resultSet.getDouble("PRECIO_UNITARIO"));                                                  
                detalleDocumentoCXC.setPrecioTotalNeto(resultSet.getDouble("PRECIO_TOTAL_NETO"));                                                  
                listaDetalleDocumentoCXC.add(detalleDocumentoCXC);
            }
                
            return responseRequest.response(ResponseRequest.DataStatus.OK, listaDetalleDocumentoCXC, "Detalle Documento CXC consultados correctamente");  
        }catch(SQLException exception){
            Resources.logger.error("Excepción en detalleDocumentoCXC: " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar detalleDocumentoCXC " + exception.getMessage());    
        }
    }
    
    public ResponseRequest rutaAsignadaYOrdenadaConMaps(int choferId) {
        String sql =
                "SELECT RMO.ID, RMO.ORDEN,  " +
                "       RMO.ID_ORIGEN, RMO.NOMBRE_CLIENTE_ORIGEN, RMO.LATITUD_ORIGEN, RMO.LONGITUD_ORIGEN, " +
                "       RMO.ID_DESTINO, RMO.NOMBRE_CLIENTE_DESTINO, RMO.LATITUD_DESTINO, RMO.LONGITUD_DESTINO, " +
                "       RMO.DISTANCIA, RMO.DURACION, RMO.ESTATUS_CLIENTE, RMO.NUMERO_DOCUMENTOS  " +
                "FROM RUTAS_MAPS_ORDEN RMO " +
                "INNER JOIN RUTAS_MAPS RM ON RM.ID = RMO.RUTA_MAPS_ID " +
                "WHERE RM.ESTATUS <> 'CERRADO' AND RM.CHOFER_ID = ? " +
                "ORDER BY RMO.ORDEN";

        ResponseRequest responseRequest = new ResponseRequest();
        List<RutaOrdenadaConMaps> listaRutaOrdenadaConMaps = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, choferId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RutaOrdenadaConMaps ruta = new RutaOrdenadaConMaps();
                    ruta.setRutaMapsOrdenId(rs.getInt("ID"));                    
                    ruta.setOrden(rs.getInt("ORDEN"));
                    ruta.setIdOrigen(rs.getInt("ID_ORIGEN"));
                    ruta.setNombreClienteOrigen(rs.getString("NOMBRE_CLIENTE_ORIGEN"));
                    ruta.setLatitudOrigen(rs.getDouble("LATITUD_ORIGEN"));
                    ruta.setLongitudOrigen(rs.getDouble("LONGITUD_ORIGEN"));
                    ruta.setIdDestino(rs.getInt("ID_DESTINO"));
                    ruta.setNombreClienteDestino(rs.getString("NOMBRE_CLIENTE_DESTINO"));
                    ruta.setLatitudDestino(rs.getDouble("LATITUD_DESTINO"));
                    ruta.setLongitudDestino(rs.getDouble("LONGITUD_DESTINO"));
                    ruta.setDistancia(rs.getString("DISTANCIA"));
                    ruta.setDuracion(rs.getString("DURACION"));
                    ruta.setEstatusCliente(rs.getString("ESTATUS_CLIENTE"));
                    ruta.setNumeroDocumentos(rs.getInt("NUMERO_DOCUMENTOS"));
                    listaRutaOrdenadaConMaps.add(ruta);
                }
            }

            return responseRequest.response(ResponseRequest.DataStatus.OK, listaRutaOrdenadaConMaps,
                    "Detalle de rutas consultado correctamente.");

        } catch (SQLException exception) {

            Resources.logger.error("Excepción en rutaAsignadaYOrdenadaConMaps: " + exception.getMessage());

            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null,
                    "Error al consultar rutas: " + exception.getMessage());
        }
    }

    public ResponseRequest ultimaMetadataMicrosip() {
        String sql =
                "SELECT FIRST 1 * FROM VERSIONES_DB ORDER BY VERSION_DB DESC";

        ResponseRequest responseRequest = new ResponseRequest();
        Metadata metadata = new Metadata();

        Utilerias utilerias = new Utilerias();

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                metadata.setVersionDB(rs.getInt("VERSION_DB"));
                metadata.setFechaHoraCreacion(
                        utilerias.timeStampToString(
                                rs.getTimestamp("FECHA_HORA_CREACION")
                        )
                );
                metadata.setPasoActual(rs.getInt("PASO_ACTUAL"));
            }

            return responseRequest.response(
                    ResponseRequest.DataStatus.OK,
                    metadata,
                    "Metadata consultada correctamente."
            );

        } catch (SQLException exception) {

            Resources.logger.error(
                    "Excepción en ultimaMetadataMicrosip: " + exception.getMessage(),
                    exception
            );

            return responseRequest.response(
                    ResponseRequest.DataStatus.ERROR,
                    null,
                    "Error al consultar metadata: " + exception.getMessage()
            );
        }
    }
    
    public ResponseRequest articulosMensajes() {

        String sql =
                "SELECT A.ARTICULO_ID, A.NOMBRE, LA.MENSAJE FROM ARTICULOS A " +
                "INNER JOIN LIBRES_ARTICULOS LA ON LA.ARTICULO_ID = A.ARTICULO_ID " +
                "WHERE LA.MENSAJE IS NOT NULL";

        ResponseRequest responseRequest = new ResponseRequest();
        List<ArticuloMensaje> listaArticulosMensajes = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {  // <-- AQUÍ EL CAMBIO
                ArticuloMensaje articuloMensaje = new ArticuloMensaje();
                articuloMensaje.setArticuloId(rs.getInt("ARTICULO_ID"));
                articuloMensaje.setMensaje(rs.getString("MENSAJE"));

                listaArticulosMensajes.add(articuloMensaje);
            }

            return responseRequest.response(
                    ResponseRequest.DataStatus.OK,
                    listaArticulosMensajes,
                    "Articulos mensajes consultados correctamente."
            );

        } catch (SQLException exception) {

            Resources.logger.error(
                    "Excepción en articulosMensajes: " + exception.getMessage(),
                    exception
            );

            return responseRequest.response(
                    ResponseRequest.DataStatus.ERROR,
                    null,
                    "Error al consultar articulosMensajes: " + exception.getMessage()
            );
        }
    }
    /***************************************************************************
     **************************************************************************/

    //ES DE ALFA
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
    
    //ES DE ALFA    
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
    
    //ES DE ALFA
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
    
    //ES DE ALFA
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
    
    //ES DE ALFA
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
    
    /*DEPRECADO*/
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
    
    //DEPRECADO
    public boolean createAbonos (String jsonString) throws SQLException {
        Resources.logger.info("Entrando a crear la cobranza:" + jsonString);
                
        configuracionMicrosip();
        
        Utilerias utilerias = new Utilerias();
        try{                
            Gson gson = new Gson();
            Type type = new TypeToken<List<ReporteMovil>>(){}.getType();
            List<ReporteMovil> listaAbonos = gson.fromJson(jsonString, type);
            ReporteMovil reporteMovilObj = listaAbonos.get(0);
            
            double totalAbono = listaAbonos.stream()
                    .mapToDouble(pojo -> pojo.getImporteAbono()).sum();
     
            conexion.setAutoCommit(false);                
                        
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            int idAutoIncremental= 0;                
            while (resultSet.next()) {
                idAutoIncremental = resultSet.getInt("ID");                    
            }                
            Resources.logger.info("ID GENERADO: " +idAutoIncremental);
                
            preparedStatement = conexion.prepareStatement("SELECT FORMA_COBRO_CC_ID FROM FORMAS_COBRO_CC WHERE CLAVE_FISCAL=?");
            preparedStatement.setString(1, reporteMovilObj.getFormaPago());
            resultSet = preparedStatement.executeQuery();
            int FORMA_COBRO_CC_ID = 0;
            while (resultSet.next()) {
                FORMA_COBRO_CC_ID = resultSet.getInt("FORMA_COBRO_CC_ID");
            }

            preparedStatement = conexion.prepareStatement(
                    "SELECT GEN_ID(ID_FOLIO_TEMP,1)AS ID FROM RDB$DATABASE"
            );
            resultSet = preparedStatement.executeQuery();
            int folioUltimo = 0;
            while (resultSet.next()) {
                folioUltimo = resultSet.getInt("ID");
            }
            Resources.logger.info("Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0"));

            String lugarExpedicionQuery = "";
            if (configuracionMobil.getMicrosip2020() == 0) {
                lugarExpedicionQuery = "SELECT LUGAR_EXPEDICION_ID FROM LUGARES_EXPEDICION";
            } else if (configuracionMobil.getMicrosip2020() != 0) {
                lugarExpedicionQuery = "SELECT LUGAR_EXPEDICION_ID FROM SUCURSALES WHERE SUCURSAL_ID = " + configuracionMobil.getSucursalId();
            }
            preparedStatement = conexion.prepareStatement(
                lugarExpedicionQuery
            );
            resultSet = preparedStatement.executeQuery();
            int LUGAR_EXPEDICION_ID = 0;
            while (resultSet.next()) {
                LUGAR_EXPEDICION_ID = resultSet.getInt("LUGAR_EXPEDICION_ID");
            }

            String querySegunVersion = "";
            if (configuracionMobil.getMicrosip2020() == 0) {
                querySegunVersion = "INSERT INTO DOCTOS_CC(DOCTO_CC_ID,CONCEPTO_CC_ID, FOLIO, NATURALEZA_CONCEPTO, FECHA, HORA, CLAVE_CLIENTE, "
                        + "IMPORTE_COBRO, CLIENTE_ID, TIPO_CAMBIO, CANCELADO, APLICADO, DESCRIPCION, COBRADOR_ID, FORMA_EMITIDA,"
                        + "CONTABILIZADO, CONTABILIZADO_GYP, COND_PAGO_ID, SISTEMA_ORIGEN, ESTATUS, ESTATUS_ANT, ES_CFD, TIENE_ANTICIPO,"
                        + "MODALIDAD_FACTURACION, ENVIADO, FECHA_HORA_ENVIO, CFDI_CERTIFICADO, INTEG_BA, CONTABILIZADO_BA, LUGAR_EXPEDICION_ID, FECHA_APLICACION)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            } else if (configuracionMobil.getMicrosip2020() != 0) {
                querySegunVersion = "INSERT IN(DOCTO_CC_ID,CONCEPTO_CC_ID, FOLIO, NATURALEZA_CONCEPTO, FECHA, HORA, CLAVE_CLIENTE, "
                        + "IMPORTE_COBRO, CLIENTE_ID, TIPO_CAMBIO, CANCELADO, APLICADO, DESCRIPCION, COBRADOR_ID, FORMA_EMITIDA,"
                        + "CONTABILIZADO, CONTABILIZADO_GYP, COND_PAGO_ID, SISTEMA_ORIGEN, ESTATUS, ESTATUS_ANT, ES_CFD, TIENE_ANTICIPO,"
                        + "MODALIDAD_FACTURACION, ENVIADO, FECHA_HORA_ENVIO, CFDI_CERTIFICADO, INTEG_BA, CONTABILIZADO_BA, LUGAR_EXPEDICION_ID, FECHA_APLICACION, SUCURSAL_ID)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }
            PreparedStatement preparedStatementObj = conexion.prepareStatement(querySegunVersion);
            preparedStatementObj.setInt(1, idAutoIncremental);
            preparedStatementObj.setInt(2, configuracionMobil.getConceptoCCId());//REFCATOR
            preparedStatementObj.setString(3, "Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0"));
            preparedStatementObj.setString(4, "R");
            preparedStatementObj.setDate(5, utilerias.convertStringToDate(reporteMovilObj.getFechaAbono()));
            preparedStatementObj.setTime(6, utilerias.convertStringToTime(reporteMovilObj.getHoraAbono()));
            preparedStatementObj.setString(7, reporteMovilObj.getClaveCliente());
            preparedStatementObj.setDouble(8, 0.00);
            preparedStatementObj.setInt(9, reporteMovilObj.getClienteId());
            preparedStatementObj.setDouble(10, 1.00);
            preparedStatementObj.setString(11, "N");
            preparedStatementObj.setString(12, "N");
            preparedStatementObj.setString(13, "Abono: $" + totalAbono + ". " + listaAbonos.size() + " documentos");
            preparedStatementObj.setInt(14, reporteMovilObj.getCobradorId());
            preparedStatementObj.setString(15, "N");
            preparedStatementObj.setString(16, "N");
            preparedStatementObj.setString(17, "N");
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
            preparedStatementObj.setDate(31, utilerias.convertStringToDate(reporteMovilObj.getFechaAbono()));
            if (configuracionMobil.getMicrosip2020() != 0) {
                preparedStatementObj.setInt(32, configuracionMobil.getSucursalId());
            }
            preparedStatementObj.executeUpdate();
            Resources.logger.info("Grabo en la tabla DOCTOS_CC REFACTOR");

            preparedStatementObj = conexion.prepareStatement(
                    "INSERT INTO FORMAS_COBRO_DOCTOS "
                    + "(FORMA_COBRO_DOC_ID, NOM_TABLA_DOCTOS, DOCTO_ID, FORMA_COBRO_ID, "
                    + "NUM_CTA_PAGO, CLAVE_SIS_FORMA_COB, REFERENCIA, IMPORTE)"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStatementObj.setInt(1, -1);
            preparedStatementObj.setString(2, "DOCTOS_CC");
            preparedStatementObj.setInt(3, idAutoIncremental);
            preparedStatementObj.setInt(4, reporteMovilObj.getFormaCobroCCId());
            preparedStatementObj.setString(5, "");
            preparedStatementObj.setString(6, "CC");
            //preparedStatementObj.setString(7, "Abono: $" + totalAbono + ". " + listaAbonos.size() + " documentos");            
            preparedStatementObj.setString(7, "$" + totalAbono);
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
            /**
             * ***********************************************************
             */

            /******************************************************************/
            
            JsonElement json = new JsonParser().parse(jsonString);
            JsonArray array = json.getAsJsonArray();
            Iterator iterator = array.iterator();
            while (iterator.hasNext()) {
                JsonElement json2 = (JsonElement) iterator.next();              
                Type collectionType = new TypeToken<ReporteMovil>() {
                }.getType();
                ReporteMovil reporteMovil = gson.fromJson(json2, collectionType);                
               
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
                Resources.logger.info("Grabo en la tabla IMPORTES_DOCTOS_CC " + reporteMovil.getSerieDocumento() );                                               
            }
            preparedStatementObj = conexion.prepareStatement(
                "UPDATE DOCTOS_CC SET APLICADO = ? WHERE DOCTO_CC_ID = ?"
            );
            preparedStatementObj.setString(1, "S");
            preparedStatementObj.setInt(2, idAutoIncremental);
            preparedStatementObj.executeUpdate();
            Resources.logger.info("ACTUALIZO A DOCTOS_CC");
           
            conexion.commit();
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
    
    //DEPRECADO
    public List<CobroXDepositarGrabado> createCobrosXDepositar (String jsonString) throws SQLException {
        Resources.logger.info("Entrando a crear la cobranza:" + jsonString);
                
        Gson gson = new Gson();
        
        configuracionMicrosip();
        
        List<CobroXDepositarGrabado> listaCobroXDepositarGrabado = new ArrayList<CobroXDepositarGrabado>();
        
        Utilerias utilerias = new Utilerias();
        try {            
            Type type = new TypeToken<CobroXDepositarEntity>(){}.getType();
            CobroXDepositarEntity cobroXDepositar = gson.fromJson(jsonString, type);
            
            conexion.setAutoCommit(false);            
            
            List<EquivalenciaAbonoMicrosip> listaEquivalencias = new ArrayList<>();            
            for (AbonoMaestroEntity abonoMaestroEntity : cobroXDepositar.getListaAbonosParaMicrosip()) {                             
                PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                );
                ResultSet resultSet = preparedStatement.executeQuery();
                int idAutoIncremental= 0;                
                while (resultSet.next()) {
                    idAutoIncremental = resultSet.getInt("ID");                    
                }     
                EquivalenciaAbonoMicrosip equivalenciaAbonoMicrosip = new EquivalenciaAbonoMicrosip();
                equivalenciaAbonoMicrosip.setAbonoMaestroId(abonoMaestroEntity.getId());
                equivalenciaAbonoMicrosip.setDoctoCCId(idAutoIncremental);
                listaEquivalencias.add(equivalenciaAbonoMicrosip);
            
                preparedStatement = conexion.prepareStatement(
                        "SELECT GEN_ID(ID_FOLIO_TEMP,1)AS ID FROM RDB$DATABASE"
                );
                resultSet = preparedStatement.executeQuery();
                int folioUltimo = 0;
                while (resultSet.next()) {
                    folioUltimo = resultSet.getInt("ID");
                }
                //Resources.logger.info("Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0"));

                preparedStatement = conexion.prepareStatement(
                        "SELECT LUGAR_EXPEDICION_ID FROM LUGARES_EXPEDICION"
                );
                resultSet = preparedStatement.executeQuery();
                int LUGAR_EXPEDICION_ID = 0;
                while (resultSet.next()) {
                    LUGAR_EXPEDICION_ID = resultSet.getInt("LUGAR_EXPEDICION_ID");
                }

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
                preparedStatementObj.setInt(2, configuracionMobil.getConceptoCCId());
                preparedStatementObj.setString(3, "Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0"));                
                preparedStatementObj.setString(4, "R");
                preparedStatementObj.setDate(5, utilerias.convertStringToDate(abonoMaestroEntity.getFechaAbono()));
                preparedStatementObj.setTime(6, utilerias.convertStringToTime(abonoMaestroEntity.getHoraAbono()));
                preparedStatementObj.setString(7, abonoMaestroEntity.getClaveCliente());
                preparedStatementObj.setDouble(8, 0.00);
                preparedStatementObj.setInt(9, abonoMaestroEntity.getClienteId());
                preparedStatementObj.setDouble(10, 1.00);
                preparedStatementObj.setString(11, "N");
                preparedStatementObj.setString(12, "N");
                preparedStatementObj.setString(13, "Abono: $" + abonoMaestroEntity.getAbonoTotal() + ". " + abonoMaestroEntity.getAbonoDetalleEntity().size() + " documentos");
                preparedStatementObj.setInt(14, abonoMaestroEntity.getCobradorId());
                preparedStatementObj.setString(15, "N");
                preparedStatementObj.setString(16, "N");
                preparedStatementObj.setString(17, "N");
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
                preparedStatementObj.setDate(31, utilerias.convertStringToDate(abonoMaestroEntity.getFechaAbono()));
                if (configuracionMobil.getMicrosip2020() != 0) {
                    preparedStatementObj.setInt(32, configuracionMobil.getSucursalId());
                }
                preparedStatementObj.executeUpdate();
                Resources.logger.info("Save table [DOCTOS_CC] id: " + idAutoIncremental + " folio: " + "Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0"));                
                
                preparedStatementObj = conexion.prepareStatement(
                        "INSERT INTO FORMAS_COBRO_DOCTOS "
                        + "(FORMA_COBRO_DOC_ID, NOM_TABLA_DOCTOS, DOCTO_ID, FORMA_COBRO_ID, "
                        + "NUM_CTA_PAGO, CLAVE_SIS_FORMA_COB, REFERENCIA, IMPORTE)"
                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
                );
                preparedStatementObj.setInt(1, -1);
                preparedStatementObj.setString(2, "DOCTOS_CC");
                preparedStatementObj.setInt(3, idAutoIncremental);
                preparedStatementObj.setInt(4, abonoMaestroEntity.getFormaCobroCCId());
                preparedStatementObj.setString(5, "");
                preparedStatementObj.setString(6, "CC");
                //preparedStatementObj.setString(7, "Abono: $" + abonoMaestroEntity.getAbonoTotal() + ". " + abonoMaestroEntity.getAbonoDetalleEntity().size() + " documentos");
                preparedStatementObj.setString(7, "Abono: $" + abonoMaestroEntity.getAbonoTotal());
                preparedStatementObj.setDouble(8, 0.00);
                preparedStatementObj.executeUpdate();
                Resources.logger.info("Save tabla [FORMAS_COBRO_DOCTOS]");
                
                for (AbonoDetalleEntity abonoDetalleEntity : abonoMaestroEntity.getAbonoDetalleEntity()) {
                    preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO IMPORTES_DOCTOS_CC(IMPTE_DOCTO_CC_ID, DOCTO_CC_ID, FECHA, "
                          + "CANCELADO, APLICADO, ESTATUS, TIPO_IMPTE, DOCTO_CC_ACR_ID, IMPORTE, "
                          + "IMPUESTO, IVA_RETENIDO, ISR_RETENIDO, DSCTO_PPAG, PCTJE_COMIS_COB)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                    );
                    preparedStatementObj.setInt(1, -1);
                    preparedStatementObj.setInt(2, idAutoIncremental);
                    preparedStatementObj.setDate(3, utilerias.convertStringToDate(abonoMaestroEntity.getFechaAbono()));
                    preparedStatementObj.setString(4, "N");
                    preparedStatementObj.setString(5, "N");
                    preparedStatementObj.setString(6, "P");
                    preparedStatementObj.setString(7, "R");
                    preparedStatementObj.setInt(8, abonoDetalleEntity.getDoctoCCId());                
                    preparedStatementObj.setDouble(9, abonoDetalleEntity.getImporteAbono());// LO REVISARA DANTE
                    preparedStatementObj.setDouble(10, 0.00); //LO REVISARA DANTE
                    preparedStatementObj.setDouble(11, 0.00);
                    preparedStatementObj.setDouble(12, 0.00);
                    preparedStatementObj.setDouble(13, 0.00);
                    preparedStatementObj.setDouble(14, 0.00);
                    preparedStatementObj.executeUpdate();               
                    Resources.logger.info("Save tabla [IMPORTES_DOCTOS_CC]");                                               
                }
                
                preparedStatementObj = conexion.prepareStatement(
                    "UPDATE DOCTOS_CC SET APLICADO = ? WHERE DOCTO_CC_ID = ?"
                );
                preparedStatementObj.setString(1, "S");
                preparedStatementObj.setInt(2, idAutoIncremental);
                preparedStatementObj.executeUpdate();
                Resources.logger.info("Update table [DOCTOS_CC]");
                
                CobroXDepositarGrabado cobroXDepositarGrabado = new CobroXDepositarGrabado();
                cobroXDepositarGrabado.setId(abonoMaestroEntity.getId());
                listaCobroXDepositarGrabado.add(cobroXDepositarGrabado);
            }
            
            
            if (configuracionMobil.getOperaDepositos()== 1) {
                //---------INSERTAR DEPOSITOS CHECAR LA CONFIGURACION --------------

                for(DepositoMaestroEntity depositoMaestroEntity : cobroXDepositar.getListaDepositosParaMicrosip()) {                        
                    PreparedStatement preparedStatement = conexion.prepareStatement(
                        "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                    );
                    ResultSet resultSet = preparedStatement.executeQuery();
                    int idAutoIncremental= 0;                
                    while (resultSet.next()) {
                        idAutoIncremental = resultSet.getInt("ID");                    
                    }                
                    //Resources.logger.info("ID GENERADO: " +idAutoIncremental);

                    String query = "INSERT INTO DEPOSITOS_CC " +
                            "(DEPOSITO_CC_ID, FECHA, FORMA_COBRO_CC_ID, SUCURSAL_ID, CUENTA_BAN_ID, " +
                            "REFER_MOVTO_BANCARIO, DESCRIPCION, IMPORTE, TIPO_CAMBIO, APLICADO, ESTATUS, FORMA_EMITIDA, " +
                            "USUARIO_CREADOR, FECHA_HORA_CREACION, USUARIO_AUT_CREACION, FECHA_HORA_ULT_MODIF, FECHA_HORA_CANCELACION) " +
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatementObj = conexion.prepareStatement(query);
                    preparedStatementObj.setInt(1, idAutoIncremental);
                    preparedStatementObj.setDate(2, utilerias.convertStringToDate2(depositoMaestroEntity.getFecha()));
                    preparedStatementObj.setInt(3, depositoMaestroEntity.getFormaCobroCCId());
                    preparedStatementObj.setInt(4, configuracionMobil.getSucursalId());
                    preparedStatementObj.setInt(5, depositoMaestroEntity.getCuentaBancariaId());
                    preparedStatementObj.setString(6, depositoMaestroEntity.getReferencia());                
                    preparedStatementObj.setString(7, depositoMaestroEntity.getDescripcion());                                               
                    preparedStatementObj.setDouble(8, depositoMaestroEntity.getImporte());
                    preparedStatementObj.setDouble(9, 1.00);
                    preparedStatementObj.setString(10, "N");
                    preparedStatementObj.setString(11, "P");
                    preparedStatementObj.setString(12, "N");                
                    preparedStatementObj.setString(13, "SYSDBA");
                    //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
                    Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());                
                    preparedStatementObj.setTimestamp(14, timestamp);
                    preparedStatementObj.setString(15, "SYSDBA"); 
                    preparedStatementObj.setTimestamp(16, timestamp); 
                    preparedStatementObj.setTimestamp(17, timestamp); 

                    preparedStatementObj.executeUpdate();                
                    Resources.logger.info("Save table [DEPOSITOS_CC] id: " + idAutoIncremental);                

                    for (DepositoDetalleEntity detalle : depositoMaestroEntity.getDepositoDetalle()) {
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO DEPOSITOS_CC_DET(DEPOSITO_CC_DET_ID, DEPOSITO_CC_ID, DOCTO_CC_ID)VALUES(?, ?, ?)"
                        );
                        preparedStatementObj.setInt(1, -1);
                        preparedStatementObj.setInt(2, idAutoIncremental);

                        EquivalenciaAbonoMicrosip equivalenciaAbonoMicrosip = listaEquivalencias.stream()
                        .filter(p -> p.getAbonoMaestroId() == detalle.getAbonoMaestroId())
                        .findAny()
                        .orElse(null);
                        Resources.logger.info("[EQUIVALENCIA] ID MOBIL: " + detalle.getAbonoMaestroId()+ "ID MICROSIP: " + equivalenciaAbonoMicrosip.getDoctoCCId());
                        preparedStatementObj.setInt(3, equivalenciaAbonoMicrosip.getDoctoCCId());
                        preparedStatementObj.executeUpdate();               
                        Resources.logger.info("Save table [DEPOSITOS_CC_DET]");                                               
                    }                                
                }
            }
            
            conexion.commit();
            
            return listaCobroXDepositarGrabado;
        }catch(SQLException exception){
            Resources.logger.error("Excepcion en createCobrosXDepositar: " + exception.getMessage());            
            try {                
                listaCobroXDepositarGrabado = new ArrayList<>();
                conexion.rollback();
                exception.printStackTrace();
                return listaCobroXDepositarGrabado;
            }
            catch (SQLException se) {
                listaCobroXDepositarGrabado = new ArrayList<>();
                se.printStackTrace();
                return listaCobroXDepositarGrabado;
            }            
        }
    }
    
    
            
    
    /*public ArrayList<CobroXDepositarGrabado> createDepositos (String jsonString) throws SQLException, ParseException {
        Resources.logger.info("Entrando a crear depositos:" + jsonString);
        Gson gson = new Gson();            
        ArrayList<CobroXDepositarGrabado> listaDepositoGrabados = new ArrayList<CobroXDepositarGrabado>();
        Utilerias utilerias = new Utilerias();
        try{                               
            configuracionMicrosip();
            
            Type type = new TypeToken<List<DepositoMaestroEntity>>(){}.getType();
            List<DepositoMaestroEntity> listaDepositos = gson.fromJson(jsonString, type);
            for(DepositoMaestroEntity deposito : listaDepositos){
                conexion.setAutoCommit(false);                
                        
                PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                );
                ResultSet resultSet = preparedStatement.executeQuery();
                int idAutoIncremental= 0;                
                while (resultSet.next()) {
                    idAutoIncremental = resultSet.getInt("ID");                    
                }                
                Resources.logger.info("ID GENERADO: " +idAutoIncremental);
                                                                
                String query = "INSERT INTO DEPOSITOS_CC " +
                        "(DEPOSITO_CC_ID, FECHA, FORMA_COBRO_CC_ID, SUCURSAL_ID, CUENTA_BAN_ID, " +
                        "IMPORTE, TIPO_CAMBIO, APLICADO, ESTATUS, FORMA_EMITIDA, " +                                                                       
                        "USUARIO_CREADOR,  USUARIO_AUT_CREACION, REFER_MOVTO_BANCARIO, DESCRIPCION) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatementObj = conexion.prepareStatement(query);
                preparedStatementObj.setInt(1, idAutoIncremental);
                preparedStatementObj.setDate(2, utilerias.convertStringToDate(deposito.getFecha()));
                preparedStatementObj.setInt(3, deposito.getFormaCobroCCId());
                preparedStatementObj.setInt(4, configuracionMobil.getSucursalId());
                preparedStatementObj.setInt(5, deposito.getCuentaBancariaId());
                
                preparedStatementObj.setDouble(6, deposito.getImporte());
                preparedStatementObj.setDouble(7, 1.00);
                preparedStatementObj.setString(8, "N");
                preparedStatementObj.setString(9, "P");
                preparedStatementObj.setString(10, "N");
                
                preparedStatementObj.setString(11, "SYSDBA");
                //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                //Date parsedDate = dateFormat.parse(deposito.getFechaCreacion());                
                //Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                //System.out.print(timestamp);
        
                //preparedStatementObj.setTimestamp(12, timestamp);
                preparedStatementObj.setString(12, "SYSDBA");                
                preparedStatementObj.setString(13, deposito.getReferencia());                
                preparedStatementObj.setString(14, deposito.getDescripcion());                        
                preparedStatementObj.executeUpdate();

                for (DepositoDetalleEntity detalle : deposito.getDepositoDetalle()) {                                                                      
                    preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO DEPOSITOS_CC_DET(DEPOSITO_CC_DET_ID, DEPOSITO_CC_ID, DOCTO_CC_ID)VALUES(?, ?, ?)"
                    );
                    preparedStatementObj.setInt(1, -1);
                    preparedStatementObj.setInt(2, idAutoIncremental);
                    preparedStatementObj.setInt(3, detalle.getDoctoCCId());
                    preparedStatementObj.executeUpdate();               
                    Resources.logger.info("Grabo en la tabla DEPOSITOS_CC_DET ");                                               
                }
                
                CobroXDepositarGrabado depositoGrabado = new CobroXDepositarGrabado();
                depositoGrabado.setId(deposito.getId());
                listaDepositoGrabados.add(depositoGrabado);

                conexion.commit();
            }
            return listaDepositoGrabados;
        }catch(SQLException exception){
            Resources.logger.error("Sucedio una excepcion: " + exception.getMessage());
            
            try {
                System.out.println("Transaction failed.");
                conexion.rollback();
                exception.printStackTrace();
                return listaDepositoGrabados;
            }
            catch (SQLException se) {
                se.printStackTrace();
                return listaDepositoGrabados;
            }            
        }
    }*/
    
    
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
    
    /*public ResponseRequest createArticulosPromVta45(String jsonArticulosVtaProm) {        
        ResponseRequest responseRequest = new ResponseRequest();  
        Type collectionType = new TypeToken<ArrayList<ArticuloPromedioVenta45POST>>() {}.getType();
        Resources.logger.info("HASTA ACA TODO VA BIEN");
        ArrayList<ArticuloPromedioVenta45POST> listaArticuloPromedioVenta45 = gson.fromJson(jsonArticulosVtaProm, collectionType);
        Resources.logger.info("HASTA ACA TODO VA BIEN" + new Gson().toJson(listaArticuloPromedioVenta45));
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "DELETE FROM ARTS_PROM_VTA_45"
            );   
            preparedStatement.executeUpdate();
            for (ArticuloPromedioVenta45POST articulo : listaArticuloPromedioVenta45) {
                preparedStatement = conexion.prepareStatement(
                    "SELECT CA.ARTICULO_ID FROM CLAVES_ARTICULOS CA " +
                    "INNER JOIN ROLES_CLAVES_ARTICULOS RCA ON RCA.ROL_CLAVE_ART_ID=CA.ROL_CLAVE_ART_ID " +
                    "WHERE CA.CLAVE_ARTICULO = ? AND RCA.ROL_CLAVE_ART_ID = 17"
                );                
                preparedStatement.setString(1, articulo.getCodigoArticulo());    
                int articuloId = 0;
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    articuloId = resultSet.getInt("ARTICULO_ID");
                }
                
                preparedStatement = conexion.prepareStatement(
                    "INSERT INTO ARTS_PROM_VTA_45 (ARTICULO_ID, CODIGO_ARTICULO, PROMEDIO_VENTA) VALUES" + 
                    "(?, ?, ?)"
                );            
                preparedStatement.setInt(1, articulo.getArticuloId());                  
                preparedStatement.setString(2, articulo.getCodigoArticulo());                   
                preparedStatement.setDouble(3, articulo.getPromedioVenta());   
                preparedStatement.executeUpdate();
            }
             
            Resources.logger.info("SE TERMINO DE INSERTAR TODO");
            return responseRequest.response(ResponseRequest.DataStatus.OK, null, "Articulos venta promedio grabados correctamente");            
        }catch(Exception exception){
            Resources.logger.error("SUCEDIO UNA EXEPCION en la cabecera createArticulosPromVta45: " + exception.getMessage());            
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al crear createArticulosPromVta45 " + exception.getMessage());
        }
    }*/
    
    
    
    /*public ArrayList<PedidoGrabado> createPedidos(String jsonPedidos) throws SQLException {
        Resources.logger.info("==================================================");
        Resources.logger.info("Se ha llamado al recurso REST mediante una peticion POST (createPedidos)");
        Resources.logger.info("Lista de pedidos: " + jsonPedidos);
        
        ArrayList<PedidoGrabado> listaPedidosGrabados = new ArrayList<PedidoGrabado>();
        
        //ConfiguracionMobil configuracionMobil = configuracionMicrosip();
        configuracionMicrosip();
        
        Double porcentajeDescuentoArticuloCliente = 0.00;
        if (configuracionMobil.getApplyPoliticaPrecioClientes()== 1)
            porcentajeDescuentoArticuloCliente = getPorcentajeDescuentoArticuloCliente(configuracionMobil.getPrecioEmpresaId());
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        JsonElement jsonElement = new JsonParser().parse(jsonPedidos);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        Iterator iterator = jsonArray.iterator();  
        
        try {
            Utilerias utilerias = new Utilerias();
            while (iterator.hasNext()) {
                JsonElement JsonElementTmp = (JsonElement) iterator.next();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<MaeMovCa02>() {}.getType();
                MaeMovCa02 objectMaeMovCa02 = gson.fromJson(JsonElementTmp, collectionType);
                
                Resources.logger.info("Json item: " + gson.toJson(objectMaeMovCa02));
                
                conexion.setAutoCommit(false);
                
                //---------- Verificar que el pedido no haya sido guardado
                PedidoExistente pedidoExistente = existePedidoGuardado(objectMaeMovCa02.getUuid());                
                String serieFolio = pedidoExistente.getFolio();
                if (pedidoExistente.getNumeroMovimiento() == 0) {
                    preparedStatement = conexion.prepareStatement(
                            "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                    );
                    resultSet = preparedStatement.executeQuery();
                    int idAutoIncremental= 0;                
                    while (resultSet.next()) {
                        idAutoIncremental = resultSet.getInt("ID");                    
                    } 
                    Resources.logger.info("idAutoIncremental: " + idAutoIncremental);

                    preparedStatement = conexion.prepareStatement(
                            "SELECT SERIE, MAX(FOLIO) + 1 AS FOLIO FROM VENDEDORES_SERIES_FOLIOS " +
                            " WHERE VENDEDOR_ID = " +objectMaeMovCa02.getVendedorId() +
                            " GROUP BY 1"
                    );
                    resultSet = preparedStatement.executeQuery();
                    int consecutivoFolio= 0;
                    String serie = "";
                    while (resultSet.next()) {
                        consecutivoFolio = resultSet.getInt("FOLIO");
                        serie = resultSet.getString("SERIE");
                    }   

                    Resources.logger.info("consecutivoFolio: " + consecutivoFolio + " serie: " + serie);

                    preparedStatement = conexion.prepareStatement(
                            "SELECT COND_PAGO_ID FROM CLIENTES WHERE CLIENTE_ID = " +  objectMaeMovCa02.getClienteId()
                    );
                    resultSet = preparedStatement.executeQuery();
                    int condicionPagoId= 0;                
                    while (resultSet.next()) {
                        condicionPagoId = resultSet.getInt("COND_PAGO_ID");                    
                    }                              

                    Resources.logger.info("condicionPagoId: " + condicionPagoId);

                    preparedStatement = conexion.prepareStatement(
                            "SELECT VIA_EMBARQUE_ID FROM DIRS_CLIENTES" +
                            " WHERE DIR_CLI_ID = " +  objectMaeMovCa02.getDireccionClienteId()
                    );
                    resultSet = preparedStatement.executeQuery();
                    int viaEmbarqueId= 0;                
                    while (resultSet.next()) {
                        viaEmbarqueId = resultSet.getInt("VIA_EMBARQUE_ID");                    
                    }
                    Resources.logger.info("viaEmbarqueId: " + viaEmbarqueId);

                    Resources.logger.info("VAMOS A GRABAR CABECERA");

                    String querySegunVersion = "";
                    if (configuracionMobil.getMicrosip2020() == 0) {
                        querySegunVersion = "INSERT INTO DOCTOS_VE (DOCTO_VE_ID, TIPO_DOCTO, SUBTIPO_DOCTO, FOLIO, FECHA, HORA, CLAVE_CLIENTE, CLIENTE_ID, "
                            + "DIR_CLI_ID, DIR_CONSIG_ID, ALMACEN_ID, "
                            + "MONEDA_ID, TIPO_CAMBIO, TIPO_DSCTO, "
                            + "DSCTO_PCTJE, DSCTO_IMPORTE, ESTATUS, APLICADO, FECHA_VIGENCIA_ENTREGA, "
                            + "DESCRIPCION, "                        
                            + "IMPORTE_NETO, FLETES, OTROS_CARGOS, TOTAL_IMPUESTOS, TOTAL_RETENCIONES, TOTAL_ANTICIPOS, "
                            + "PESO_EMBARQUE, FORMA_EMITIDA, CONTABILIZADO, ACREDITAR_CXC, SISTEMA_ORIGEN, COND_PAGO_ID, "                        
                            + "PCTJE_DSCTO_PPAG, VENDEDOR_ID, PCTJE_COMIS, VIA_EMBARQUE_ID, IMPORTE_COBRO,"
                            + "USUARIO_CREADOR, ES_CFD, ENVIADO, "
                            + "CFD_ENVIO_ESPECIAL, CFDI_CERTIFICADO, FECHA_HORA_CREACION, "
                            + "CARGAR_SUN)" +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    } else if (configuracionMobil.getMicrosip2020() != 0) {
                        querySegunVersion = "INSERT INTO DOCTOS_VE (DOCTO_VE_ID, TIPO_DOCTO, SUBTIPO_DOCTO, FOLIO, FECHA, HORA, CLAVE_CLIENTE, CLIENTE_ID, "
                            + "DIR_CLI_ID, DIR_CONSIG_ID, ALMACEN_ID, "
                            + "MONEDA_ID, TIPO_CAMBIO, TIPO_DSCTO, "
                            + "DSCTO_PCTJE, DSCTO_IMPORTE, ESTATUS, APLICADO, FECHA_VIGENCIA_ENTREGA, "
                            + "DESCRIPCION, "                        
                            + "IMPORTE_NETO, FLETES, OTROS_CARGOS, TOTAL_IMPUESTOS, TOTAL_RETENCIONES, TOTAL_ANTICIPOS, "
                            + "PESO_EMBARQUE, FORMA_EMITIDA, CONTABILIZADO, ACREDITAR_CXC, SISTEMA_ORIGEN, COND_PAGO_ID, "                        
                            + "PCTJE_DSCTO_PPAG, VENDEDOR_ID, PCTJE_COMIS, VIA_EMBARQUE_ID, IMPORTE_COBRO,"
                            + "USUARIO_CREADOR, ES_CFD, ENVIADO, "
                            + "CFD_ENVIO_ESPECIAL, CFDI_CERTIFICADO, FECHA_HORA_CREACION, "
                            + "CARGAR_SUN, SUCURSAL_ID)" +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    }

                    PreparedStatement preparedStatementObj = conexion.prepareStatement(querySegunVersion);                 
                    preparedStatementObj.setInt(1, idAutoIncremental);

                    if (configuracionMobil.getComportamientoCaptura().equals(Constants.COTIZACION))
                        preparedStatementObj.setString(2, Constants.COTIZACION);
                    else
                        preparedStatementObj.setString(2, Constants.PEDIDO);

                    preparedStatementObj.setString(3, "N");                
                    int posiciones =  9 - serie.length();//Son 9 la longitud del campo FOLIO
                    serieFolio = serie + StringUtils.leftPad(String.valueOf(consecutivoFolio), posic.length()iones, "0");
                    preparedStatementObj.setString(4, serieFolio);
                    //preparedStatementObj.setString(4, serie + StringUtils.leftPad(String.valueOf(consecutivoFolio), 8, "0"));
                    preparedStatementObj.setDate(5, utilerias.convertStringToDate(objectMaeMovCa02.getFcapmov()));
                    preparedStatementObj.setTime(6, utilerias.convertStringToTime(objectMaeMovCa02.getHoramov()));
                    preparedStatementObj.setString(7, objectMaeMovCa02.getNumcte());
                    preparedStatementObj.setInt(8, objectMaeMovCa02.getClienteId());                                              
                    preparedStatementObj.setInt(9, objectMaeMovCa02.getDireccionClienteId());                                                                                                            
                    preparedStatementObj.setInt(10, objectMaeMovCa02.getDireccionClienteId());                                                                                                            
                    preparedStatementObj.setInt(11, objectMaeMovCa02.getNumalm());                
                    preparedStatementObj.setInt(12, 1);
                    preparedStatementObj.setDouble(13, 1.00);
                    preparedStatementObj.setString(14, "P");                                  
                    preparedStatementObj.setDouble(15, 0.00);                
                    preparedStatementObj.setDouble(16, 0.00);                
                    preparedStatementObj.setString(17, "P");
                    preparedStatementObj.setString(18, "S");
                    preparedStatementObj.setDate(19, utilerias.convertStringToDate(objectMaeMovCa02.getFcapmov()));//PUEDE SER LA DE HOY                
                    preparedStatementObj.setString(20, objectMaeMovCa02.getObservaciones());                               
                    preparedStatementObj.setDouble(21, objectMaeMovCa02.getImpmov());//CALCULARLO EN LA APP TOTAL NETO
                    preparedStatementObj.setDouble(22, 0.00);
                    preparedStatementObj.setDouble(23, 0.00);
                    preparedStatementObj.setDouble(24, objectMaeMovCa02.getIvatmov()); //CALCULARLO EN LA APP TOTAL IMPUESTOS
                    preparedStatementObj.setDouble(25, 0.00);
                    preparedStatementObj.setDouble(26, 0.00);
                    preparedStatementObj.setDouble(27, 0.00);
                    preparedStatementObj.setString(28, "N");
                    preparedStatementObj.setString(29, "N");
                    preparedStatementObj.setString(30, "N");
                    preparedStatementObj.setString(31, "VE");
                    preparedStatementObj.setInt(32, condicionPagoId);//TABLA CONDICIONES_PAGO POR EL CLIENTE
                    preparedStatementObj.setDouble(33, 0.00);
                    preparedStatementObj.setInt(34, objectMaeMovCa02.getVendedorId());
                    preparedStatementObj.setDouble(35, 0.00);
                    preparedStatementObj.setInt(36, viaEmbarqueId);
                    preparedStatementObj.setDouble(37, 0.00);                                             
                    preparedStatementObj.setString(38, Constants.SYSDBA);
                    preparedStatementObj.setString(39, "N");
                    preparedStatementObj.setString(40, "N");                
                    preparedStatementObj.setString(41, "N");
                    preparedStatementObj.setString(42, "N");                
                    java.util.Date today = new java.util.Date();
                    preparedStatementObj.setTimestamp(43, new java.sql.Timestamp(today.getTime()));
                    preparedStatementObj.setString(44, "S");

                    if (configuracionMobil.getMicrosip2020() != 0) 
                        preparedStatementObj.setInt(45, configuracionMobil.getSucursalId());

                    preparedStatementObj.executeUpdate();
                    Resources.logger.info("Grabando en la cabecera");

                    // REFACTOR
                    preparedStatement = conexion.prepareStatement(
                            "UPDATE VENDEDORES_SERIES_FOLIOS SET " +
                            " FOLIO = " + consecutivoFolio + 
                            " WHERE SERIE = '" + serie + "'"
                    );                             
                    preparedStatement.executeUpdate();
                    Resources.logger.info("Actualizando la serie: " + serie + " con el folio: " + consecutivoFolio);

                    // Cambio para el cliente de AFASY 20-NOV-2020
                    if (configuracionMobil.getComportamientoCaptura().equals(Constants.COTIZACION)) {
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO LIBRES_COT_VE (DOCTO_VE_ID, STATUS)" +
                            "VALUES (?,?)"
                        );
                        preparedStatementObj.setInt(1, idAutoIncremental);
                        preparedStatementObj.setString(2, objectMaeMovCa02.getCotizacionEmiteFactura());
                        preparedStatementObj.executeUpdate();                                
                        
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO VIT_COTIZ_PROCESADAS (DOCTO_VE_ID, ESTATUS)" +
                            "VALUES (?,?)"
                        );
                        preparedStatementObj.setInt(1, idAutoIncremental);
                        preparedStatementObj.setString(2, "P");
                        preparedStatementObj.executeUpdate();                                                        
                    }                                
                    // REFACTOR
    //==============================================================================
    // ========== Se inserta el detalle del pedido en MAEDTMA02 ====================
    //==============================================================================
                    Gson gsonMaeDtma02 = new Gson();                            
                    JsonElement json = new JsonParser().parse(gsonMaeDtma02.toJson(objectMaeMovCa02.getListaMaeDtma02()));
                    JsonArray array = json.getAsJsonArray();
                    Iterator iterator2 = array.iterator();
                    List<DetallePedidoOld> details = new ArrayList<DetallePedidoOld>();

                    int consecutivo = 0;
                    while (iterator2.hasNext()) {                                
                        consecutivo++;
                        JsonElement json2 = (JsonElement) iterator2.next();                    
                        Type collectionType2 = new TypeToken<DetallePedidoOld>() {}.getType();
                        DetallePedidoOld detallePedido = gson.fromJson(json2, collectionType2);

                        Resources.logger.info(consecutivo + " Detalle del pedido");

                        preparedStatementObj = conexion.prepareStatement(
                        "INSERT INTO DOCTOS_VE_DET (DOCTO_VE_DET_ID, DOCTO_VE_ID, CLAVE_ARTICULO, ARTICULO_ID, "
                        + "UNIDADES, UNIDADES_COMPROM, "
                        + "UNIDADES_SURT_DEV, UNIDADES_A_SURTIR, "
                        + "PRECIO_UNITARIO, PCTJE_DSCTO, DSCTO_ART, PCTJE_DSCTO_CLI, "
                        + "DSCTO_EXTRA, PCTJE_DSCTO_VOL, PCTJE_DSCTO_PROMO, PRECIO_TOTAL_NETO, "
                        + "PCTJE_COMIS, ROL, POSICION)" +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                        );
                        preparedStatementObj.setInt(1, -1);//?????
                        preparedStatementObj.setInt(2, idAutoIncremental);
                        preparedStatementObj.setString(3, detallePedido.getNumart());        //JALAR DE LA APP EL CODIGO        
                        preparedStatementObj.setInt(4, detallePedido.getArticuloId());
                        preparedStatementObj.setDouble(5, detallePedido.getCandtm());
                        preparedStatementObj.setDouble(6, 0.00);                
                        preparedStatementObj.setDouble(7, 0.00);
                        preparedStatementObj.setDouble(8, 0.00);                                                                
                        //                    
                        if (configuracionMobil.getApplyPoliticaPrecioClientes() == 1) {
                            Double precioUnitarioConImpuestos = detallePedido.getPrudtm() * (1 + (detallePedido.getTasaIva() / 100));
                            preparedStatementObj.setDouble(9, precioUnitarioConImpuestos);//PRECIO SIN IVA                    
                            Double porcentajeDescuentoTotal = porcentajeDescuentoTotalXArticulo(porcentajeDescuentoArticuloCliente, detallePedido);
                            preparedStatementObj.setDouble(10, porcentajeDescuentoTotal);
                            preparedStatementObj.setDouble(11, (precioUnitarioConImpuestos * detallePedido.getCandtm()) * (porcentajeDescuentoTotal / 100)); //VALOR EN MONEDA DEL DESCUENTO   100-10%=10.00
                            preparedStatementObj.setDouble(12, porcentajeDescuentoArticuloCliente);
                        }
                        else {
                            TipoPolitica tipoPolitica = TipoPolitica.valueOf(configuracionMobil.getTipoPoliticaAOperar());                                                
                            if (tipoPolitica == TipoPolitica.TIPO_CLIENTE) {
                                Double precioUnitarioSinImpuesto = detallePedido.getPrudtm();
                                preparedStatementObj.setDouble(9, precioUnitarioSinImpuesto);//PRECIO SIN IVA                    
                                Double porcentajeDescuentoTotal = porcentajeDescuentoTotalXArticulo(detallePedido.getPorcentajeDescuentoArticuloCliente(), detallePedido);
                                preparedStatementObj.setDouble(10, porcentajeDescuentoTotal);
                                preparedStatementObj.setDouble(11, (precioUnitarioSinImpuesto * detallePedido.getCandtm()) * (porcentajeDescuentoTotal / 100)); //VALOR EN MONEDA DEL DESCUENTO   100-10%=10.00
                                preparedStatementObj.setDouble(12, detallePedido.getPorcentajeDescuentoArticuloCliente());    
                            }else     //REFACTOR_HOLBERG_HAY _QUE _HACER                   
                            if (objectMaeMovCa02.getDescuentoCliente() > 0) {
                                Double precioUnitarioSinImpuesto = detallePedido.getPrudtm();
                                preparedStatementObj.setDouble(9, precioUnitarioSinImpuesto);//PRECIO SIN IVA                    
                                Double porcentajeDescuentoTotal = porcentajeDescuentoTotalXArticulo(detallePedido.getPorcentajeDescuentoArticuloCliente(), detallePedido);
                                preparedStatementObj.setDouble(10, porcentajeDescuentoTotal);
                                preparedStatementObj.setDouble(11, (precioUnitarioSinImpuesto * detallePedido.getCandtm()) * (porcentajeDescuentoTotal / 100)); //VALOR EN MONEDA DEL DESCUENTO   100-10%=10.00
                                preparedStatementObj.setDouble(12, detallePedido.getPorcentajeDescuentoArticuloCliente());    
                            } else {
                                preparedStatementObj.setDouble(9, detallePedido.getPrudtm());//PRECIO SIN IVA                    
                                preparedStatementObj.setDouble(10, detallePedido.getDesdtm());//% POR PROMOION O POR VOLUMEN
                                preparedStatementObj.setDouble(11, detallePedido.getVivadtm()); //VALOR EN MONEDA DEL DESCUENTO   100-10%=10.00
                                preparedStatementObj.setDouble(12, 0.00);
                            }
                        }                    
                        preparedStatementObj.setDouble(13, 0.00);
                        preparedStatementObj.setDouble(14, 0.00);                                          
                        preparedStatementObj.setDouble(15, detallePedido.getDesdtm());                
                        //
                        preparedStatementObj.setDouble(16, (detallePedido.getPrudtm() - detallePedido.getVivadtm()) * detallePedido.getCandtm());                 //TODOS LOS VALORES SON SIN IMPUESTOS
                        preparedStatementObj.setDouble(17, 0.00);
                        preparedStatementObj.setString(18, "N");
                        preparedStatementObj.setInt(19, consecutivo);
                        preparedStatementObj.executeUpdate();                                
                    }

                    //---------- SE GRABA EL MOVIMIENTO CON EL DEL MOBIL
                    createPedidoGuardado(objectMaeMovCa02.getUuid(), idAutoIncremental, serieFolio);
                }                                                               
                // Este objeto es para agregar el id que se generp en ANDROID y retornarlo en una lista
                PedidoGrabado pedidoGrabadoObject = new PedidoGrabado();
                pedidoGrabadoObject.setId(objectMaeMovCa02.getId());
                pedidoGrabadoObject.setNummov(objectMaeMovCa02.getNummov());                
                pedidoGrabadoObject.setFolio(serieFolio);                
                listaPedidosGrabados.add(pedidoGrabadoObject); 
                //}    
                
                conexion.commit();
            }
            return listaPedidosGrabados;
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en la cabecera del pedido: " + exception.getMessage());       
            //exception.getNextException(),
            try {
                System.out.println("Transaction failed.");
                conexion.rollback();
                exception.printStackTrace();                
            }
            catch (SQLException se) {
                se.printStackTrace();
            }
        }finally { 
  
            return listaPedidosGrabados;
        }
    }*/
    
    /*public ArrayList<PedidoGrabado> createPedidosRefactor(String jsonPedidos) throws SQLException {
        Resources.logger.info("==================================================");
        Resources.logger.info("Se ha llamado al recurso REST mediante una peticion POST (createPedidos)");
        Resources.logger.info("Lista de pedidos: " + jsonPedidos);
        
        ArrayList<PedidoGrabado> listaPedidosGrabados = new ArrayList<PedidoGrabado>();
                
        configuracionMicrosip();
        
        Double porcentajeDescuentoArticuloCliente = 0.00;
        if (configuracionMobil.getApplyPoliticaPrecioClientes()== 1)
            porcentajeDescuentoArticuloCliente = getPorcentajeDescuentoArticuloCliente(configuracionMobil.getPrecioEmpresaId());
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        JsonElement jsonElement = new JsonParser().parse(jsonPedidos);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        Iterator iterator = jsonArray.iterator();  
        
        try {
            Utilerias utilerias = new Utilerias();
            while (iterator.hasNext()) {
                JsonElement JsonElementTmp = (JsonElement) iterator.next();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<MaeMovCa02>() {}.getType();
                MaeMovCa02 objectMaeMovCa02 = gson.fromJson(JsonElementTmp, collectionType);
                
                Resources.logger.info("Json item: " + gson.toJson(objectMaeMovCa02));
                
                conexion.setAutoCommit(false);
                
                //---------- Verificar que el pedido no haya sido guardado
                PedidoExistente pedidoExistente = existePedidoGuardado(objectMaeMovCa02.getUuid());                
                String serieFolio = pedidoExistente.getFolio();
                if (pedidoExistente.getNumeroMovimiento() == 0) {
                    preparedStatement = conexion.prepareStatement(
                        "SELECT GEN_ID(ID_DOCTOS,1)AS ID FROM RDB$DATABASE"
                    );
                    resultSet = preparedStatement.executeQuery();
                    int idAutoIncremental= 0;                
                    while (resultSet.next()) {
                        idAutoIncremental = resultSet.getInt("ID");                    
                    } 
                    Resources.logger.info("idAutoIncremental: " + idAutoIncremental);

                    preparedStatement = conexion.prepareStatement(
                        "SELECT SERIE, MAX(FOLIO) + 1 AS FOLIO FROM VENDEDORES_SERIES_FOLIOS " +
                        " WHERE VENDEDOR_ID = " +objectMaeMovCa02.getVendedorId() +
                        " GROUP BY 1"
                    );
                    resultSet = preparedStatement.executeQuery();
                    int consecutivoFolio= 0;
                    String serie = "";
                    while (resultSet.next()) {
                        consecutivoFolio = resultSet.getInt("FOLIO");
                        serie = resultSet.getString("SERIE");
                    }   

                    Resources.logger.info("consecutivoFolio: " + consecutivoFolio + " serie: " + serie);

                    preparedStatement = conexion.prepareStatement(
                        "SELECT COND_PAGO_ID FROM CLIENTES WHERE CLIENTE_ID = " +  objectMaeMovCa02.getClienteId()
                    );
                    resultSet = preparedStatement.executeQuery();
                    int condicionPagoId= 0;                
                    while (resultSet.next()) {
                        condicionPagoId = resultSet.getInt("COND_PAGO_ID");                    
                    }                              

                    Resources.logger.info("condicionPagoId: " + condicionPagoId);

                    preparedStatement = conexion.prepareStatement(
                        "SELECT VIA_EMBARQUE_ID FROM DIRS_CLIENTES" +
                        " WHERE DIR_CLI_ID = " +  objectMaeMovCa02.getDireccionClienteId()
                    );
                    resultSet = preparedStatement.executeQuery();
                    int viaEmbarqueId= 0;                
                    while (resultSet.next()) {
                        viaEmbarqueId = resultSet.getInt("VIA_EMBARQUE_ID");                    
                    }
                    Resources.logger.info("viaEmbarqueId: " + viaEmbarqueId);

                    Resources.logger.info("VAMOS A GRABAR CABECERA");

                    String querySegunVersion = "";
                    if (configuracionMobil.getMicrosip2020() == 0) {
                        querySegunVersion = "INSERT INTO DOCTOS_VE (DOCTO_VE_ID, TIPO_DOCTO, SUBTIPO_DOCTO, FOLIO, FECHA, HORA, CLAVE_CLIENTE, CLIENTE_ID, "
                            + "DIR_CLI_ID, DIR_CONSIG_ID, ALMACEN_ID, "
                            + "MONEDA_ID, TIPO_CAMBIO, TIPO_DSCTO, "
                            + "DSCTO_PCTJE, DSCTO_IMPORTE, ESTATUS, APLICADO, FECHA_VIGENCIA_ENTREGA, "
                            + "DESCRIPCION, "                        
                            + "IMPORTE_NETO, FLETES, OTROS_CARGOS, TOTAL_IMPUESTOS, TOTAL_RETENCIONES, TOTAL_ANTICIPOS, "
                            + "PESO_EMBARQUE, FORMA_EMITIDA, CONTABILIZADO, ACREDITAR_CXC, SISTEMA_ORIGEN, COND_PAGO_ID, "                        
                            + "PCTJE_DSCTO_PPAG, VENDEDOR_ID, PCTJE_COMIS, VIA_EMBARQUE_ID, IMPORTE_COBRO,"
                            + "USUARIO_CREADOR, ES_CFD, ENVIADO, "
                            + "CFD_ENVIO_ESPECIAL, CFDI_CERTIFICADO, FECHA_HORA_CREACION, "
                            + "CARGAR_SUN)" +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    } else if (configuracionMobil.getMicrosip2020() != 0) {
                        querySegunVersion = "INSERT INTO DOCTOS_VE (DOCTO_VE_ID, TIPO_DOCTO, SUBTIPO_DOCTO, FOLIO, FECHA, HORA, CLAVE_CLIENTE, CLIENTE_ID, "
                            + "DIR_CLI_ID, DIR_CONSIG_ID, ALMACEN_ID, "
                            + "MONEDA_ID, TIPO_CAMBIO, TIPO_DSCTO, "
                            + "DSCTO_PCTJE, DSCTO_IMPORTE, ESTATUS, APLICADO, FECHA_VIGENCIA_ENTREGA, "
                            + "DESCRIPCION, "                        
                            + "IMPORTE_NETO, FLETES, OTROS_CARGOS, TOTAL_IMPUESTOS, TOTAL_RETENCIONES, TOTAL_ANTICIPOS, "
                            + "PESO_EMBARQUE, FORMA_EMITIDA, CONTABILIZADO, ACREDITAR_CXC, SISTEMA_ORIGEN, COND_PAGO_ID, "                        
                            + "PCTJE_DSCTO_PPAG, VENDEDOR_ID, PCTJE_COMIS, VIA_EMBARQUE_ID, IMPORTE_COBRO,"
                            + "USUARIO_CREADOR, ES_CFD, ENVIADO, "
                            + "CFD_ENVIO_ESPECIAL, CFDI_CERTIFICADO, FECHA_HORA_CREACION, "
                            + "CARGAR_SUN, SUCURSAL_ID)" +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    }

                    PreparedStatement preparedStatementObj = conexion.prepareStatement(querySegunVersion);                 
                    preparedStatementObj.setInt(1, idAutoIncremental);

                    if (configuracionMobil.getComportamientoCaptura().equals(Constants.COTIZACION))
                        preparedStatementObj.setString(2, Constants.COTIZACION);
                    else
                        preparedStatementObj.setString(2, Constants.PEDIDO);

                    preparedStatementObj.setString(3, "N");                
                    int posiciones =  9 - serie.length();//Son 9 la longitud del campo FOLIO
                    serieFolio = serie + StringUtils.leftPad(String.valueOf(consecutivoFolio), posiciones, "0");
                    preparedStatementObj.setString(4, serieFolio);                    
                    preparedStatementObj.setDate(5, utilerias.convertStringToDate(objectMaeMovCa02.getFcapmov()));
                    preparedStatementObj.setTime(6, utilerias.convertStringToTime(objectMaeMovCa02.getHoramov()));
                    preparedStatementObj.setString(7, objectMaeMovCa02.getNumcte());
                    preparedStatementObj.setInt(8, objectMaeMovCa02.getClienteId());                                              
                    preparedStatementObj.setInt(9, objectMaeMovCa02.getDireccionClienteId());                                                                                                            
                    preparedStatementObj.setInt(10, objectMaeMovCa02.getDireccionClienteId());                                                                                                            
                    preparedStatementObj.setInt(11, objectMaeMovCa02.getNumalm());                
                    preparedStatementObj.setInt(12, 1);
                    preparedStatementObj.setDouble(13, 1.00);
                    preparedStatementObj.setString(14, "P");                                  
                    preparedStatementObj.setDouble(15, 0.00);                
                    preparedStatementObj.setDouble(16, 0.00);                
                    preparedStatementObj.setString(17, "P");
                    preparedStatementObj.setString(18, "S");
                    preparedStatementObj.setDate(19, utilerias.convertStringToDate(objectMaeMovCa02.getFcapmov()));//PUEDE SER LA DE HOY                
                    preparedStatementObj.setString(20, objectMaeMovCa02.getObservaciones());                               
                    preparedStatementObj.setDouble(21, objectMaeMovCa02.getImpmov());//CALCULARLO EN LA APP TOTAL NETO
                    preparedStatementObj.setDouble(22, 0.00);
                    preparedStatementObj.setDouble(23, 0.00);
                    preparedStatementObj.setDouble(24, objectMaeMovCa02.getIvatmov());
                    preparedStatementObj.setDouble(25, 0.00);
                    preparedStatementObj.setDouble(26, 0.00);
                    preparedStatementObj.setDouble(27, 0.00);
                    preparedStatementObj.setString(28, "N");
                    preparedStatementObj.setString(29, "N");
                    preparedStatementObj.setString(30, "N");
                    preparedStatementObj.setString(31, "VE");
                    preparedStatementObj.setInt(32, condicionPagoId);
                    preparedStatementObj.setDouble(33, 0.00);
                    preparedStatementObj.setInt(34, objectMaeMovCa02.getVendedorId());
                    preparedStatementObj.setDouble(35, 0.00);
                    preparedStatementObj.setInt(36, viaEmbarqueId);
                    preparedStatementObj.setDouble(37, 0.00);                                             
                    preparedStatementObj.setString(38, Constants.SYSDBA);
                    preparedStatementObj.setString(39, "N");
                    preparedStatementObj.setString(40, "N");                
                    preparedStatementObj.setString(41, "N");
                    preparedStatementObj.setString(42, "N");                
                    java.util.Date today = new java.util.Date();
                    preparedStatementObj.setTimestamp(43, new java.sql.Timestamp(today.getTime()));
                    preparedStatementObj.setString(44, "S");

                    if (configuracionMobil.getMicrosip2020() != 0) 
                        preparedStatementObj.setInt(45, configuracionMobil.getSucursalId());

                    preparedStatementObj.executeUpdate();
                    Resources.logger.info("Grabando en la cabecera");

                    // REFACTOR
                    preparedStatement = conexion.prepareStatement(
                            "UPDATE VENDEDORES_SERIES_FOLIOS SET " +
                            " FOLIO = " + consecutivoFolio + 
                            " WHERE SERIE = '" + serie + "'"
                    );                             
                    preparedStatement.executeUpdate();
                    Resources.logger.info("Actualizando la serie: " + serie + " con el folio: " + consecutivoFolio);

                    // Cambio para el cliente de AFASY 20-NOV-2020
                    if (configuracionMobil.getComportamientoCaptura().equals(Constants.COTIZACION)) {
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO LIBRES_COT_VE (DOCTO_VE_ID, STATUS)" +
                            "VALUES (?,?)"
                        );
                        preparedStatementObj.setInt(1, idAutoIncremental);
                        preparedStatementObj.setString(2, objectMaeMovCa02.getCotizacionEmiteFactura());
                        preparedStatementObj.executeUpdate();                                
                        
                        preparedStatementObj = conexion.prepareStatement(
                            "INSERT INTO VIT_COTIZ_PROCESADAS (DOCTO_VE_ID, ESTATUS)" +
                            "VALUES (?,?)"
                        );
                        preparedStatementObj.setInt(1, idAutoIncremental);
                        preparedStatementObj.setString(2, "P");
                        preparedStatementObj.executeUpdate();                                                        
                    }                                
                    // REFACTOR
                    //==============================================================================
                    // ========== Se inserta el detalle del pedido en MAEDTMA02 ====================
                    //==============================================================================
                    Gson gsonMaeDtma02 = new Gson();                            
                    JsonElement json = new JsonParser().parse(gsonMaeDtma02.toJson(objectMaeMovCa02.getListaMaeDtma02()));
                    JsonArray array = json.getAsJsonArray();
                    Iterator iterator2 = array.iterator();
                    List<DetallePedidoOld> details = new ArrayList<DetallePedidoOld>();

                    int consecutivo = 0;
                    while (iterator2.hasNext()) {                                
                        consecutivo++;
                        JsonElement json2 = (JsonElement) iterator2.next();                    
                        Type collectionType2 = new TypeToken<DetallePedidoOld>() {}.getType();
                        DetallePedidoOld detallePedido = gson.fromJson(json2, collectionType2);

                        Resources.logger.info(consecutivo + " Detalle del pedido");

                        preparedStatementObj = conexion.prepareStatement(
                        "INSERT INTO DOCTOS_VE_DET (DOCTO_VE_DET_ID, DOCTO_VE_ID, CLAVE_ARTICULO, ARTICULO_ID, "
                        + "UNIDADES, UNIDADES_COMPROM, "
                        + "UNIDADES_SURT_DEV, UNIDADES_A_SURTIR, "
                        + "PRECIO_UNITARIO, PCTJE_DSCTO, DSCTO_ART, PCTJE_DSCTO_CLI, "
                        + "DSCTO_EXTRA, PCTJE_DSCTO_VOL, PCTJE_DSCTO_PROMO, PRECIO_TOTAL_NETO, "
                        + "PCTJE_COMIS, ROL, POSICION)" +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                        );
                        preparedStatementObj.setInt(1, -1);
                        preparedStatementObj.setInt(2, idAutoIncremental);
                        preparedStatementObj.setString(3, detallePedido.getNumart());
                        preparedStatementObj.setInt(4, detallePedido.getArticuloId());
                        preparedStatementObj.setDouble(5, detallePedido.getCandtm());
                        preparedStatementObj.setDouble(6, 0.00);                
                        preparedStatementObj.setDouble(7, 0.00);
                        preparedStatementObj.setDouble(8, 0.00);                                                                
                        /**********************************************************/                    
                        /*if (configuracionMobil.getApplyPoliticaPrecioClientes() == 1) {
                            Double precioUnitarioConImpuestos = detallePedido.getPrudtm() * (1 + (detallePedido.getTasaIva() / 100));
                            preparedStatementObj.setDouble(9, precioUnitarioConImpuestos);//PRECIO SIN IVA                    
                            Double porcentajeDescuentoTotal = porcentajeDescuentoTotalXArticulo(porcentajeDescuentoArticuloCliente, detallePedido);
                            preparedStatementObj.setDouble(10, porcentajeDescuentoTotal);
                            preparedStatementObj.setDouble(11, (precioUnitarioConImpuestos * detallePedido.getCandtm()) * (porcentajeDescuentoTotal / 100)); //VALOR EN MONEDA DEL DESCUENTO   100-10%=10.00
                            preparedStatementObj.setDouble(12, porcentajeDescuentoArticuloCliente);
                        }
                        else {
                            /*TipoPolitica tipoPolitica = TipoPolitica.valueOf(configuracionMobil.getTipoPoliticaAOperar());                                                
                            if (tipoPolitica == TipoPolitica.TIPO_CLIENTE) {
                                Double precioUnitarioSinImpuesto = detallePedido.getPrudtm();
                                preparedStatementObj.setDouble(9, precioUnitarioSinImpuesto);//PRECIO SIN IVA                    
                                Double porcentajeDescuentoTotal = porcentajeDescuentoTotalXArticulo(detallePedido.getPorcentajeDescuentoArticuloCliente(), detallePedido);
                                preparedStatementObj.setDouble(10, porcentajeDescuentoTotal);
                                preparedStatementObj.setDouble(11, (precioUnitarioSinImpuesto * detallePedido.getCandtm()) * (porcentajeDescuentoTotal / 100)); //VALOR EN MONEDA DEL DESCUENTO   100-10%=10.00
                                preparedStatementObj.setDouble(12, detallePedido.getPorcentajeDescuentoArticuloCliente());    
                            }else     //REFACTOR_HOLBERG_HAY _QUE _HACER                   
                            if (objectMaeMovCa02.getDescuentoCliente() > 0) {*/
                              /*  Double precioUnitarioSinImpuesto = detallePedido.getPrudtm();
                                preparedStatementObj.setDouble(9, precioUnitarioSinImpuesto);//PRECIO SIN IVA                    
                                Double porcentajeDescuentoTotal = porcentajeDescuentoTotalXArticulo(detallePedido.getPorcentajeDescuentoArticuloCliente(), detallePedido);
                                preparedStatementObj.setDouble(10, porcentajeDescuentoTotal);
                                preparedStatementObj.setDouble(11, (precioUnitarioSinImpuesto * detallePedido.getCandtm()) * (porcentajeDescuentoTotal / 100)); //VALOR EN MONEDA DEL DESCUENTO   100-10%=10.00
                                preparedStatementObj.setDouble(12, detallePedido.getPorcentajeDescuentoArticuloCliente());    
                            /*} else {
                                preparedStatementObj.setDouble(9, detallePedido.getPrudtm());//PRECIO SIN IVA                    
                                preparedStatementObj.setDouble(10, detallePedido.getDesdtm());//% POR PROMOION O POR VOLUMEN
                                preparedStatementObj.setDouble(11, detallePedido.getVivadtm()); //VALOR EN MONEDA DEL DESCUENTO   100-10%=10.00
                                preparedStatementObj.setDouble(12, 0.00);
                            }*/
                        /*}                    
                        preparedStatementObj.setDouble(13, 0.00);
                        preparedStatementObj.setDouble(14, 0.00);                                          
                        preparedStatementObj.setDouble(15, detallePedido.getDesdtm());                
                        /**********************************************************/
                        /*preparedStatementObj.setDouble(16, (detallePedido.getPrudtm() - detallePedido.getVivadtm()) * detallePedido.getCandtm());                 //TODOS LOS VALORES SON SIN IMPUESTOS
                        preparedStatementObj.setDouble(17, 0.00);
                        preparedStatementObj.setString(18, "N");
                        preparedStatementObj.setInt(19, consecutivo);
                        preparedStatementObj.executeUpdate();                                
                    }

                    //---------- SE GRABA EL MOVIMIENTO CON EL DEL MOBIL
                    createPedidoGuardado(objectMaeMovCa02.getUuid(), idAutoIncremental, serieFolio);
                }                                                               
                // Este objeto es para agregar el id que se generp en ANDROID y retornarlo en una lista
                PedidoGrabado pedidoGrabadoObject = new PedidoGrabado();
                pedidoGrabadoObject.setId(objectMaeMovCa02.getId());
                pedidoGrabadoObject.setNummov(objectMaeMovCa02.getNummov());                
                pedidoGrabadoObject.setFolio(serieFolio);                
                listaPedidosGrabados.add(pedidoGrabadoObject); 
                //}    
                
                conexion.commit();
            }
            return listaPedidosGrabados;
        } catch (SQLException exception) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, exception);
            Resources.logger.error("SUCEDIO UNA EXEPCION en la cabecera del pedido: " + exception.getMessage());       
            //exception.getNextException(),
            try {
                System.out.println("Transaction failed.");
                conexion.rollback();
                exception.printStackTrace();                
            }
            catch (SQLException se) {
                se.printStackTrace();
            }
        }finally { */
            /*try {
                conexion.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }*/
       /*     return listaPedidosGrabados;
        }
    }*/
 
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
}