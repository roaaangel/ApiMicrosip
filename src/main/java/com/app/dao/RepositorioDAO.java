package com.app.dao;

import com.app.contants.Constants;
import com.app.models.AbonoDetalleEntity;
import com.app.models.AbonoMaestroEntity;
import com.app.models.AgenteCobranza;
import com.app.models.ArticuloMensaje;
import com.app.models.ArticuloPromedioVenta45;
import com.app.models.ClienteConsignatario;
import com.app.models.ClienteDireccionPrincipal;
import com.app.models.ClienteEmiteFactura;
import com.app.models.CobradorSucursal;
import com.app.models.ComplementoXml;
import com.app.models.ComplementoXmlDetalle;
import com.app.models.ConfiguracionAlmacen;
import com.app.models.ConfiguracionCliente;
import com.app.models.ConfiguracionMobil;
import com.app.models.ConfiguracionPrecio;
import com.app.models.CuentaBancaria;
import com.app.models.DetalleDocumentoCXC;
import com.app.models.DetallePedido;
import com.app.models.ExistenciaArticulo;
import com.app.models.FolioInfo;
import com.app.models.HistoriaCambiaria;
import com.app.models.MaestroPedido;
import com.app.models.Moneda;
import com.app.models.MonedaHistoriaCambiaria;
import com.app.models.Motivo;
import com.app.models.PedidoGrabado;
import com.app.models.PoliticaCliente;
import com.app.models.PoliticaDescuentoArticuloClienteRefactor;
import com.app.models.PoliticaXVolumen;
import com.app.models.ProcesaPoliticas;
import com.app.models.SerieFolioCXC;
import com.app.models.TrimestreAnioInfo;
import com.app.models.almacenes.Almacen;
import com.app.models.articulos.ArticuloRefactor;
import com.app.models.clientes.ClienteRefactor;
import com.app.models.cobradores.Cobrador;
import com.app.models.cobranza.CobranzaRefactor;
import com.app.models.cobrosmicrosip.CobroMicrosip;
import com.app.models.cobrosxdepositar.CobroXDepositar;
import com.app.models.cobrosxdepositar.CobroXDepositarEnviado;
import com.app.models.datosempresa.DatosEmpresa;
import com.app.models.metodospago.MetodoPago;
import com.app.models.promociones.Promocion;
import com.app.models.vendedores.Vendedor;
import com.app.models.vendedorescobradores.VendedorCobrador;
import com.app.servicios.Resources;
import com.app.utilerias.ResponseRequest;
import com.app.utilerias.Utileria;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RepositorioDAO {
    
    private static final Logger logger = LoggerFactory.getLogger(RepositorioDAO.class);
   
    public String executeScript(String script) {
        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión para ejecutar script.");
                return "Script fallido: no se pudo establecer conexión.";
            }

            try (Statement stmt = connection.createStatement()) {
                stmt.execute(script);
                logger.info("Script ejecutado correctamente.");
                return "Script creado correctamente";
            }

        } catch (SQLException e) {
            logger.error("Error al ejecutar script: {}", e.getMessage(), e);
            return "Script fallido: " + e.getMessage();
        }
    }


    public String datosEmpresa() {
        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión para consultar datos de la empresa.");
                return null;
            }
            
            String SQL_DATOS_EMPRESA =
            "SELECT NOMBRE, CALLE, NOMBRE_CALLE, NUM_EXTERIOR, NUM_INTERIOR, COLONIA, POBLACION, " +
            "CIUDAD, ESTADO, CODIGO_POSTAL, PAIS, TELEFONO1, TELEFONO2, EMAIL, RFC FROM DATOS_EMPRESA";

            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DATOS_EMPRESA);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

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

                    String rfc = resultSet.getString("RFC");
                    datosEmpresa.setRfc(rfc != null ? rfc.trim() : null);
                }

                return new Gson().toJson(datosEmpresa);
            }

        } catch (SQLException exception) {
            logger.error("Error al consultar datos de la empresa: {}", exception.getMessage(), exception);
            return null;
        }
    }
    
    public ConfiguracionMobil configuracionMicrosip() {
        String query = 
            "SELECT PRECIO_EMPRESA_ID, CONCEPTO_CUENTA_X_COBRAR_ID, " +
            "CONDICION_PAGO_ID, ROL_ART_CLAVE_PRINCIPAL_ID, ROL_ART_CLAVE_ALTERNA_ID, " +
            "ROL_ART_CODIGO_BARRA_ID, ROL_ART_CODIGO_BARRA_INNER_ID, ROL_ART_CODIGO_BARRA_MASTER_ID, " +
            "MICROSIP_2020, SUCURSAL_ID, APPLY_POLITICA_PRECIO_CLIENTES, COMPORTAMIENTO_CAPTURA, OPERA_DEPOSITOS, " +
            "TIPO_POLITICA_A_OPERAR, DIAS_HISTORIA, COMPORTAMIENTO_ALMACEN, FOLIO_FISCAL_ID, SERIE_CONCEPTO_CC, OPERA_CONSIGNATARIOS, " +
            "APPLY_DESC_ARTS_CTES_PROMO, REGLA_GPS, DIAS_GRACIA_ID, OPERA_POLITICAS_X_VOLUMEN, OPERA_POLITICAS_X_PROMOCION, " +
            "CONTROLA_SERIE_FOLIO_CXC, SINC_EXISTENCIA_ARTS, SINC_ART_CONDICIONADOS, SINC_EXIST_ARTS_CONDICIONADOS, SINC_CXC_X_RUTA, OPERA_SUCURSAL_ALMACEN, " +
            "FORMA_CAPTURA_PARTIDA, OPERA_MONEDA_EXTRANJERA, DISMINUYE_ABONO_PARA_SALDO, SINCRONIZA_PEDIDO_TR, " +
            "SINCRONIZA_ABONO_TR, ENVIA_SMS, OPERA_POP FROM CONFIGURACION_MOBIL";

        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return null;
            }                    

            ConfiguracionMobil configuracionMobil = null;

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    configuracionMobil = new ConfiguracionMobil();

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

                    // Optimización de la lectura de COMPORTAMIENTO_ALMACEN
                    String comportamientoAlmacen = resultSet.getString("COMPORTAMIENTO_ALMACEN");
                    configuracionMobil.setComportamientoAlmacen(
                        (comportamientoAlmacen == null || comportamientoAlmacen.trim().isEmpty()) ? "FINAL" : comportamientoAlmacen
                    );

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
                    configuracionMobil.setSincronizaPedidoTR(resultSet.getInt("SINCRONIZA_PEDIDO_TR"));                
                    configuracionMobil.setSincronizaAbonoTR(resultSet.getInt("SINCRONIZA_ABONO_TR"));                
                    configuracionMobil.setEnviaSMS(resultSet.getInt("ENVIA_SMS"));                
                    configuracionMobil.setOperaPop(resultSet.getInt("OPERA_POP"));                
                }

                if (configuracionMobil != null) {
                    int diasGraciaId = configuracionMobil.getDiasGraciaId();
                    configuracionMobil.setDiasGraciaId(diasGraciaId == 0 ? 0 : diasGraciaFacturasVencidas(diasGraciaId));
                    configuracionMobil.setConfiguracionAlmacenes(configuracionAlmacenes());                
                    configuracionMobil.setConfiguracionClientes(configuracionClientes());
                }
            }

            return configuracionMobil;            

        } catch (SQLException exception) {
            logger.error("Error al consultar configuración móvil: {}", exception.getMessage(), exception);
            return null;
        }
    }
    
    private int diasGraciaFacturasVencidas(int diasGraciaId) {
        String query = "SELECT VALOR FROM REGISTRY WHERE ELEMENTO_ID = ?";

        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return 0;
            }   

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, diasGraciaId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("VALOR");
                    }
                }
            }

        } catch (SQLException exception) {
            logger.error("Error al consultar días de gracia para ID {}: {}", diasGraciaId, exception.getMessage(), exception);
        }

        return 0; // Valor por defecto si no hay conexión, ocurre un error o no se encuentra el registro
    }
    
    private List<ConfiguracionAlmacen> configuracionAlmacenes() {
        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return Collections.emptyList();
            }

            String query = "SELECT ALMACEN_ID, ES_DEFAULT FROM CONFIGURACION_ALMACENES";
            List<ConfiguracionAlmacen> listaAlmacenes = new ArrayList<>();

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    ConfiguracionAlmacen configuracion = new ConfiguracionAlmacen();
                    configuracion.setAlmacenId(resultSet.getInt("ALMACEN_ID"));
                    configuracion.setEsDefault(resultSet.getInt("ES_DEFAULT"));
                    listaAlmacenes.add(configuracion);
                }
            }

            return listaAlmacenes;

        } catch (SQLException exception) {
            logger.error("Error al consultar la configuración de almacenes: {}", exception.getMessage(), exception);
            return Collections.emptyList();
        }
    }

    private List<ConfiguracionCliente> configuracionClientes() {
        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return Collections.emptyList();
            }

            String query = "SELECT ESTATUS FROM CONFIGURACION_CLIENTES";
            List<ConfiguracionCliente> listaConfiguracionClientes = new ArrayList<>();

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    ConfiguracionCliente configuracion = new ConfiguracionCliente();
                    configuracion.setEstatus(resultSet.getString("ESTATUS"));
                    listaConfiguracionClientes.add(configuracion);
                }
            }

            return listaConfiguracionClientes;

        } catch (SQLException exception) {
            logger.error("Error al consultar la configuración de clientes: {}", exception.getMessage(), exception);
            return Collections.emptyList();
        }
    }
        
    public String vendedoresCobradores() {        
        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return "{}"; // O maneja según la respuesta JSON esperada por tu API
            }                    

            List<Vendedor> listaVendedores = obtenerVendedores(connection);
            List<Cobrador> listaCobradores = obtenerCobradores(connection);

            VendedorCobrador dto = new VendedorCobrador();
            dto.setListaVendedores(listaVendedores);
            dto.setListaCobradores(listaCobradores);

            return new Gson().toJson(dto);

        } catch (SQLException exception) {
            logger.error("Error al consultar vendedores y cobradores: {}", exception.getMessage(), exception);
            return "{}";
        }
    }

    private List<Vendedor> obtenerVendedores(Connection connection) throws SQLException {
        List<Vendedor> lista = new ArrayList<>();
        String query = "SELECT VENDEDOR_ID, NOMBRE FROM VENDEDORES";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setVendedorId(resultSet.getInt("VENDEDOR_ID"));
                vendedor.setNombre(resultSet.getString("NOMBRE"));
                lista.add(vendedor);
            }
        }
        return lista;
    }

    private List<Cobrador> obtenerCobradores(Connection connection) throws SQLException {
        List<Cobrador> lista = new ArrayList<>();
        String query = "SELECT COBRADOR_ID, NOMBRE FROM COBRADORES";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Cobrador cobrador = new Cobrador();
                cobrador.setCobradorId(resultSet.getInt("COBRADOR_ID"));
                cobrador.setNombre(resultSet.getString("NOMBRE"));
                lista.add(cobrador);
            }
        }
        return lista;
    }  
    
    public String almacenes() {
        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return "[]"; // Retorna arreglo vacío en lugar de objeto para mantener la coherencia del tipo JSON
            }                    

            List<Almacen> listaAlmacenes = new ArrayList<>();

            String query = "SELECT A.ALMACEN_ID, A.NOMBRE, CA.SUCURSAL_ID FROM ALMACENES A " +
                           "INNER JOIN CONFIGURACION_ALMACENES CA ON A.ALMACEN_ID = CA.ALMACEN_ID " +
                           "ORDER BY CA.ES_DEFAULT DESC";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Almacen almacen = new Almacen();
                    almacen.setAlmacenId(resultSet.getInt("ALMACEN_ID"));
                    almacen.setNombre(resultSet.getString("NOMBRE"));
                    almacen.setSucursalId(resultSet.getInt("SUCURSAL_ID"));
                    listaAlmacenes.add(almacen);
                }
            }

            return new Gson().toJson(listaAlmacenes);

        } catch (SQLException exception) {
            logger.error("Error al consultar almacenes: {}", exception.getMessage(), exception);
            return "[]";
        }
    }
    
    public String metodosDePago() {
        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return "[]";
            }

            List<MetodoPago> listaMetodoPago = new ArrayList<>();
            String query = "SELECT FORMA_COBRO_CC_ID, CLAVE_FISCAL, NOMBRE FROM FORMAS_COBRO_CC";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    MetodoPago metodoPago = new MetodoPago();
                    metodoPago.setFormaCobroCCId(resultSet.getInt("FORMA_COBRO_CC_ID"));
                    metodoPago.setClave(resultSet.getString("CLAVE_FISCAL"));
                    metodoPago.setConcepto(resultSet.getString("NOMBRE"));
                    listaMetodoPago.add(metodoPago);
                }
            }

            return new Gson().toJson(listaMetodoPago);

        } catch (SQLException exception) {
            logger.error("Error al consultar métodos de pago: {}", exception.getMessage(), exception);
            return "[]";
        }
    }
    
    public String cuentasBancariasRefactor() {
        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return "[]";
            }

            List<CuentaBancaria> listaCuentasBancarias = new ArrayList<>();

            // Se define el query base para no duplicar código
            String query = "SELECT CB.CUENTA_BAN_ID, CB.BANCO_ID, B.NOMBRE, CB.NUM_CUENTA " +
                           "FROM CUENTAS_BANCARIAS CB " +
                           "INNER JOIN BANCOS B ON B.BANCO_ID = CB.BANCO_ID";

            // Se concatena la condición solo si aplica el filtro
            if (cuantasCuentasBancariasFiltro() > 0) {
                query += " WHERE CB.CUENTA_BAN_ID IN (SELECT CUENTA_BAN_ID FROM CONFIGURACION_CUENTAS_BANCARIAS)";
            }

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    CuentaBancaria cuentaBancaria = new CuentaBancaria();
                    cuentaBancaria.setCuentaBancariaId(resultSet.getInt("CUENTA_BAN_ID"));
                    cuentaBancaria.setBancoId(resultSet.getInt("BANCO_ID"));
                    cuentaBancaria.setNombreBanco(resultSet.getString("NOMBRE"));
                    cuentaBancaria.setNumeroCuenta(resultSet.getString("NUM_CUENTA"));
                    listaCuentasBancarias.add(cuentaBancaria);
                }
            }

            return new Gson().toJson(listaCuentasBancarias);

        } catch (SQLException exception) {
            logger.error("Error al consultar cuentas bancarias: {}", exception.getMessage(), exception);
            return "[]";
        }
    }
    
    public int cuantasCuentasBancariasFiltro() {
        String query = "SELECT COUNT(*) AS CUANTAS FROM CONFIGURACION_CUENTAS_BANCARIAS";

        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return 0;
            }

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getInt("CUANTAS");
                }
            }

        } catch (SQLException exception) {
            logger.error("Error al contar la configuración de cuentas bancarias: {}", exception.getMessage(), exception);
        }

        return 0;
    }
    
    public String cobradoresSucursales() {
        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return "[]";
            }

            List<CobradorSucursal> listaCobradorSucursal = new ArrayList<>();
            String query = "SELECT COBRADOR_ID, SUCURSAL_ID FROM COBRADORES_SUCURSALES";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    CobradorSucursal cobradorSucursal = new CobradorSucursal();
                    cobradorSucursal.setCobradorId(resultSet.getInt("COBRADOR_ID"));
                    cobradorSucursal.setSucursalId(resultSet.getInt("SUCURSAL_ID"));
                    listaCobradorSucursal.add(cobradorSucursal);
                }
            }

            return new Gson().toJson(listaCobradorSucursal);

        } catch (SQLException exception) {
            logger.error("Error al consultar cobradores por sucursal: {}", exception.getMessage(), exception);
            return "[]";
        }
    }
    
    public ResponseRequest monedas() {         
        logger.info("Iniciando consulta de monedas e historias cambiarias"); 
        ResponseRequest responseRequest = new ResponseRequest(); 

        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "No se pudo conectar a la base de datos");
            }

            List<Moneda> listaMonedas = obtenerMonedas(connection);
            List<HistoriaCambiaria> listaHistoriasCambiarias = obtenerHistoriasCambiarias(connection);

            MonedaHistoriaCambiaria monedaHistoriaCambiaria = new MonedaHistoriaCambiaria();
            monedaHistoriaCambiaria.setMonedas(listaMonedas);
            monedaHistoriaCambiaria.setHistoriasCambiarias(listaHistoriasCambiarias);

            return responseRequest.response(
                ResponseRequest.DataStatus.OK, 
                monedaHistoriaCambiaria, 
                "Monedas consultadas correctamente"
            );

        } catch (SQLException exception) {
            logger.error("Error al consultar monedas: {}", exception.getMessage(), exception);
            return responseRequest.response(
                ResponseRequest.DataStatus.ERROR, 
                null, 
                "Error al consultar Monedas: " + exception.getMessage()
            );
        }
    }

    private List<Moneda> obtenerMonedas(Connection connection) throws SQLException {
        List<Moneda> listaMonedas = new ArrayList<>();
        String query = "SELECT MONEDA_ID, NOMBRE, TEXTO_IMPTE_LETRA, SIMBOLO, CLAVE_FISCAL, DECIMALES_SOPORTADOS FROM MONEDAS";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

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
        }
        return listaMonedas;
    }

    private List<HistoriaCambiaria> obtenerHistoriasCambiarias(Connection connection) throws SQLException {
        List<HistoriaCambiaria> listaHistoriasCambiarias = new ArrayList<>();
        String query = "SELECT MONEDA_ID, TIPO_CAMBIO_COBROS, TIPO_CAMBIO FROM HISTORIA_CAMBIARIA WHERE FECHA = CURRENT_DATE";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                HistoriaCambiaria historiaCambiaria = new HistoriaCambiaria();
                historiaCambiaria.setMonedaId(resultSet.getInt("MONEDA_ID"));
                historiaCambiaria.setTipoCambioCobros(resultSet.getDouble("TIPO_CAMBIO_COBROS"));
                historiaCambiaria.setTipoCambio(resultSet.getDouble("TIPO_CAMBIO"));
                listaHistoriasCambiarias.add(historiaCambiaria);
            }
        }
        return listaHistoriasCambiarias;
    }
    
    public ResponseRequest vendedoresCobranza() { 
        ResponseRequest responseRequest = new ResponseRequest();

        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "No se pudo conectar a la base de datos");
            }

            List<AgenteCobranza> listaAgentesCobranza = new ArrayList<>();
            String query = "SELECT VENDEDOR_ID, VISUALIZA_COBRANZA FROM VENDEDORES_COBRANZA";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    AgenteCobranza agenteCobranza = new AgenteCobranza();
                    agenteCobranza.setVendedorId(resultSet.getInt("VENDEDOR_ID"));
                    agenteCobranza.setVisualizaCobranza(resultSet.getString("VISUALIZA_COBRANZA"));
                    listaAgentesCobranza.add(agenteCobranza);
                }
            }

            return responseRequest.response(
                ResponseRequest.DataStatus.OK, 
                listaAgentesCobranza, 
                "Agentes cobranza consultados correctamente"
            );

        } catch (SQLException exception) {
            logger.error("Error al consultar agentes de cobranza: {}", exception.getMessage(), exception);
            return responseRequest.response(
                ResponseRequest.DataStatus.ERROR, 
                null, 
                "Error al consultar Agentes cobranza: " + exception.getMessage()
            );
        }
    }
    
    public ResponseRequest motivosVisitas() { 
        ResponseRequest responseRequest = new ResponseRequest();

        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "No se pudo conectar a la base de datos");
            }

            List<Motivo> listaMotivos = new ArrayList<>();
            String query = "SELECT ID, MOTIVO FROM MOTIVOS";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Motivo motivo = new Motivo();
                    motivo.setId(resultSet.getInt("ID"));
                    motivo.setMotivo(resultSet.getString("MOTIVO"));
                    listaMotivos.add(motivo);
                }
            }

            return responseRequest.response(
                ResponseRequest.DataStatus.OK, 
                listaMotivos, 
                "Motivos consultados correctamente"
            );

        } catch (SQLException exception) {
            logger.error("Error al consultar motivos de visitas: {}", exception.getMessage(), exception);
            return responseRequest.response(
                ResponseRequest.DataStatus.ERROR, 
                null, 
                "Error al consultar Motivos " + exception.getMessage()
            );
        }
    }
    
    public ResponseRequest articulosMensajes() {
        ResponseRequest responseRequest = new ResponseRequest();

        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "No se pudo conectar a la base de datos");
            }

            List<ArticuloMensaje> listaArticulosMensajes = new ArrayList<>();
            String query = "SELECT A.ARTICULO_ID, A.NOMBRE, LA.MENSAJE FROM ARTICULOS A " +
                           "INNER JOIN LIBRES_ARTICULOS LA ON LA.ARTICULO_ID = A.ARTICULO_ID " +
                           "WHERE LA.MENSAJE IS NOT NULL";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    ArticuloMensaje articuloMensaje = new ArticuloMensaje();
                    articuloMensaje.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                    articuloMensaje.setMensaje(resultSet.getString("MENSAJE"));
                    listaArticulosMensajes.add(articuloMensaje);
                }
            }

            return responseRequest.response(
                ResponseRequest.DataStatus.OK, 
                listaArticulosMensajes, 
                "Articulos mensajes consultados correctamente."
            );

        } catch (SQLException exception) {
            logger.error("Error en articulosMensajes: {}", exception.getMessage(), exception);
            return responseRequest.response(
                ResponseRequest.DataStatus.ERROR, 
                null, 
                "Error al consultar articulosMensajes: " + exception.getMessage()
            );
        }
    }
    
    public String articulosRefactor() {
        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return "[]";
            }

            ConfiguracionMobil configuracionMobil = configuracionMicrosip();
            
            List<ArticuloRefactor> listaArticulos = new ArrayList<>();

            // 1. Determinar el procedimiento almacenado según la configuración
            String procedimientoBD;
            String seleccionPop = "'N' AS ES_POP";

            if (configuracionMobil.getSincArtsCondicionados() == 1) {
                procedimientoBD = "POLS_ARTS_PROMO_VOL_COND_AH";
            } else if (configuracionMobil.getOperaPop() == 1) {
                procedimientoBD = "POLS_ARTS_PROMO_VOL_POP_AH";
                seleccionPop = "ES_POP";
            } else {
                procedimientoBD = "POLITICAS_ARTS_PROMO_VOL_AH";
            }

            // 2. Construcción limpia del query dinámico sin duplicar columnas
            String queryArticulos = String.format(
                "SELECT ARTICULO_ID, NOMBRE_ARTICULO, CODIGO_ARTICULO, CLAVE_ARTICULO, ES_JUEGO, FACTOR_VENTA, " +
                "UNIDAD_VENTA, PORCENTAJE_IVA, PORCENTAJE_IEPS, IMPUESTO_USAR, PRECIO, PRECIO_NETO, MONEDA_ID, " +
                "UNIDAD_MINIMA_VENTA, PIEZAS_X_INNER, PIEZAS_X_MASTER, CODIGO_BARRAS, CODIGO_BARRAS_INNER, " +
                "CODIGO_BARRAS_MASTER, TIENE_DESCUENTO_PROMOCION, DESCUENTO_PROMOCION, ES_EXCLUSIVO_PROMOCION, " +
                "TIENE_DESCUENTO_VOLUMEN, %s FROM %s(?, ?, ?)", 
                seleccionPop, procedimientoBD
            );

    
            try (PreparedStatement statement = connection.prepareStatement(queryArticulos)) {
                
                Calendar fechaActual;
                fechaActual = Calendar.getInstance();
                int anio = fechaActual.get(Calendar.YEAR);
                int mes = fechaActual.get(Calendar.MONTH) + 1;

                Calendar calendar = new GregorianCalendar(anio, mes, 0);
                int diasDelMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                String fechaInicioMes = mes + "/" + "01" + "/" + anio;                                   
                String fechaFinMes = mes + "/" + diasDelMes + "/" + anio;  

                statement.setInt(1, configuracionMobil.getPrecioEmpresaId());
                statement.setDate(2, convierteStringAFecha(fechaInicioMes));
                statement.setDate(3, convierteStringAFecha(fechaFinMes));

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        ArticuloRefactor articulo = mapRowToArticulo(resultSet);
                        listaArticulos.add(articulo);
                    }
                }
            }

            return new Gson().toJson(listaArticulos);

        } catch (SQLException exception) {
            logger.error("Error al consultar artículos refactorizados: {}", exception.getMessage(), exception);
            return "[]";
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
            java.util.logging.Logger.getLogger(Utileria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }  

    private ArticuloRefactor mapRowToArticulo(ResultSet rs) throws SQLException {
        ArticuloRefactor articulo = new ArticuloRefactor();
        articulo.setArticuloId(rs.getInt("ARTICULO_ID"));
        articulo.setNombreArticulo(rs.getString("NOMBRE_ARTICULO"));
        articulo.setCodigoArticulo(sanitizarTexto(rs.getString("CODIGO_ARTICULO")));
        articulo.setClaveArticulo(sanitizarTexto(rs.getString("CLAVE_ARTICULO")));
        articulo.setEsJuego(rs.getString("ES_JUEGO"));
        articulo.setFactorVenta(rs.getDouble("FACTOR_VENTA"));

        String unidadVenta = rs.getString("UNIDAD_VENTA");
        articulo.setUnidadVenta(unidadVenta != null ? unidadVenta : "NA");

        articulo.setPorcentajeIva(rs.getDouble("PORCENTAJE_IVA"));
        articulo.setPorcentajeIeps(rs.getDouble("PORCENTAJE_IEPS"));
        articulo.setImpuestoUsar(rs.getString("IMPUESTO_USAR"));
        articulo.setPrecio(rs.getDouble("PRECIO"));
        articulo.setPrecioNeto(rs.getDouble("PRECIO_NETO"));
        articulo.setUnidadMinimaVenta(rs.getInt("UNIDAD_MINIMA_VENTA"));
        articulo.setPiezasXInner(rs.getInt("PIEZAS_X_INNER"));
        articulo.setPiezasXMaster(rs.getInt("PIEZAS_X_MASTER"));

        articulo.setCodigoBarras(limpiarTextoSeguro(rs.getString("CODIGO_BARRAS")));
        articulo.setCodigoBarrasInner(limpiarTextoSeguro(rs.getString("CODIGO_BARRAS_INNER")));
        articulo.setCodigoBarrasMaster(limpiarTextoSeguro(rs.getString("CODIGO_BARRAS_MASTER")));

        articulo.setTieneDescuentoPromocion(rs.getBoolean("TIENE_DESCUENTO_PROMOCION"));
        articulo.setDescuentoPromocion(rs.getDouble("DESCUENTO_PROMOCION"));
        articulo.setEsExclusivoPromocion(rs.getString("ES_EXCLUSIVO_PROMOCION"));
        articulo.setTieneDescuentoVolumen(rs.getBoolean("TIENE_DESCUENTO_VOLUMEN"));
        articulo.setMonedaId(rs.getInt("MONEDA_ID"));
        articulo.setEsPop(rs.getString("ES_POP"));
        return articulo;
    }

    private String sanitizarTexto(String valor) {
        if (valor == null) return "";
        String limpio = valor.replace("'", "\"").trim();
        return escapeCharacters(limpio);
    }

    private String escapeCharacters(String cadena){
        String xmlWithSpecial = cadena;
        String scape = StringEscapeUtils.escapeXml(StringEscapeUtils.escapeJava(xmlWithSpecial));
        
        return scape;
    }
    
    private String limpiarTextoSeguro(String valor) {
        return valor == null ? "" : valor.trim();
    }
    
    public String promociones() {
        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return "[]";
            }

            List<Promocion> listaPromociones = new ArrayList<>();
            String query = "SELECT A.NOMBRE, DPA.DESCUENTO FROM POLITICAS_DSCTOS_PROMOCION PDP " +
                           "INNER JOIN DSCTOS_PROMO_ARTS DPA ON PDP.POLITICA_DSCTO_PROMO_ID = DPA.POLITICA_DSCTO_PROMO_ID " +
                           "INNER JOIN ARTICULOS A ON DPA.ARTICULO_ID = A.ARTICULO_ID " +
                           "WHERE PDP.FECHA_INI_VIGENCIA >= ? AND PDP.FECHA_FIN_VIGENCIA <= ? AND PDP.HABILITADA = 'S'";

            // Cálculo moderno del rango del mes actual (API java.time)
            Calendar fechaActual;
            fechaActual = Calendar.getInstance();
            int anio = fechaActual.get(Calendar.YEAR);
            int mes = fechaActual.get(Calendar.MONTH) + 1;

            Calendar calendar = new GregorianCalendar(anio, mes, 0);
            int diasDelMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            String fechaInicioMes = mes + "/" + "01" + "/" + anio;                                   
            String fechaFinMes = mes + "/" + diasDelMes + "/" + anio;                     

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDate(1, convierteStringAFecha(fechaInicioMes));
                statement.setDate(2, convierteStringAFecha(fechaFinMes));

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Promocion promocion = new Promocion();

                        String nombreRaw = resultSet.getString("NOMBRE");
                        String nombreLimpio = (nombreRaw != null) ? escapeCharacters(nombreRaw.trim()) : "";

                        promocion.setNombre(nombreLimpio);
                        promocion.setDescuento(resultSet.getDouble("DESCUENTO"));
                        listaPromociones.add(promocion);
                    }
                }
            }

            return new Gson().toJson(listaPromociones);

        } catch (SQLException exception) {
            logger.error("Error al consultar promociones: {}", exception.getMessage(), exception);
            return "[]";
        }
    }
    
    public ResponseRequest configuracionPrecios() { 
        ResponseRequest responseRequest = new ResponseRequest();

        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "No se pudo conectar a la base de datos");
            }

            List<ConfiguracionPrecio> listaConfiguracionPrecios = new ArrayList<>();
            String query = "SELECT PRECIO_EMPRESA_ID FROM CONFIGURACION_PRECIOS";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    ConfiguracionPrecio configuracionPrecio = new ConfiguracionPrecio();
                    configuracionPrecio.setPrecioEmpresaId(resultSet.getInt("PRECIO_EMPRESA_ID"));
                    listaConfiguracionPrecios.add(configuracionPrecio);
                }
            }

            return responseRequest.response(
                ResponseRequest.DataStatus.OK, 
                listaConfiguracionPrecios, 
                "Configuracion precios consultados correctamente"
            );

        } catch (SQLException exception) {
            logger.error("Error en configuracionPrecios: {}", exception.getMessage(), exception);
            return responseRequest.response(
                ResponseRequest.DataStatus.ERROR, 
                null, 
                "Error al consultar configuracionPrecios: " + exception.getMessage()
            );
        }
    }
    
    public ResponseRequest articulosMultiPrecios(int precioEmpresaId) { 
        ResponseRequest responseRequest = new ResponseRequest();

        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return responseRequest.response(
                    ResponseRequest.DataStatus.ERROR, 
                    null, 
                    "No se pudo conectar a la base de datos"
                );
            }

            List<ArticuloRefactor> listaArticulos = new ArrayList<>();
            String query = "SELECT ARTICULO_ID, NOMBRE_ARTICULO, CODIGO_ARTICULO, CLAVE_ARTICULO, ES_JUEGO, " +
                           "FACTOR_VENTA, UNIDAD_VENTA, PORCENTAJE_IVA, PORCENTAJE_IEPS, IMPUESTO_USAR, " +
                           "PRECIO, PRECIO_NETO, PRECIO_EMPRESA_ID, MONEDA_ID, UNIDAD_MINIMA_VENTA, PIEZAS_X_INNER, " +
                           "PIEZAS_X_MASTER, ES_POP " +
                           "FROM ARTICULOS_PRECIOS(?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, precioEmpresaId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        ArticuloRefactor articulo = new ArticuloRefactor();

                        articulo.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                        articulo.setNombreArticulo(resultSet.getString("NOMBRE_ARTICULO"));
                        articulo.setCodigoArticulo(resultSet.getString("CODIGO_ARTICULO"));
                        articulo.setClaveArticulo(resultSet.getString("CLAVE_ARTICULO"));
                        articulo.setEsJuego(resultSet.getString("ES_JUEGO"));
                        articulo.setFactorVenta(resultSet.getDouble("FACTOR_VENTA"));

                        // Validación de nulos para la unidad de venta
                        String unidadVenta = resultSet.getString("UNIDAD_VENTA");
                        articulo.setUnidadVenta(unidadVenta != null ? unidadVenta : "NA");

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
                }
            }

            return responseRequest.response(
                ResponseRequest.DataStatus.OK, 
                listaArticulos, 
                "Artículos consultados correctamente"
            );

        } catch (SQLException exception) {
            logger.error("Error en articulosMultiPrecios: {}", exception.getMessage(), exception);
            return responseRequest.response(
                ResponseRequest.DataStatus.ERROR, 
                null, 
                "Error al consultar artículos: " + exception.getMessage()
            );
        }
    }
    
    public ResponseRequest getArticulosPromVta45() { 
        
        ResponseRequest responseRequest = new ResponseRequest();

        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return responseRequest.response(
                    ResponseRequest.DataStatus.ERROR, 
                    null, 
                    "No se pudo conectar a la base de datos"
                );
            }

            List<ArticuloPromedioVenta45> listaArticuloPromedioVenta45 = new ArrayList<>();
            String query = "SELECT ARTICULO_ID, CODIGO_ARTICULO, PROMEDIO_VENTA FROM ARTS_PROM_VTA_45";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    ArticuloPromedioVenta45 articuloPromedioVenta45 = new ArticuloPromedioVenta45();
                    articuloPromedioVenta45.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                    articuloPromedioVenta45.setCodigoArticulo(resultSet.getString("CODIGO_ARTICULO"));
                    articuloPromedioVenta45.setPromedioVenta(resultSet.getDouble("PROMEDIO_VENTA"));

                    listaArticuloPromedioVenta45.add(articuloPromedioVenta45);
                }
            }

            return responseRequest.response(
                ResponseRequest.DataStatus.OK, 
                listaArticuloPromedioVenta45, 
                "Artículos de promedio de venta 45 consultados correctamente"
            );

        } catch (SQLException exception) {
            logger.error("Error en getArticulosPromVta45: {}", exception.getMessage(), exception);
            return responseRequest.response(
                ResponseRequest.DataStatus.ERROR, 
                null, 
                "Error al consultar promedio de venta de artículos: " + exception.getMessage()
            );
        }
    }
    
    public ResponseRequest existenciaArticulosRefactor() { 
        ResponseRequest responseRequest = new ResponseRequest();

        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return responseRequest.response(
                    ResponseRequest.DataStatus.ERROR, 
                    null, 
                    "No se pudo conectar a la base de datos"
                );
            }
            
            ConfiguracionMobil configuracionMobil = configuracionMicrosip();            

            List<ExistenciaArticulo> listaExistenciaArticulos = new ArrayList<>();

            int sincronizaExistenciaArticulosCondicionados = configuracionMobil.getSincExistArtsCondicionados();
            String tipoPoliticaAOperar = configuracionMobil.getTipoPoliticaAOperar();
            int precioEmpresaId = configuracionMobil.getPrecioEmpresaId();

            String query = "SELECT ARTICULO_ID, EXISTENCIA, NOMBRE_ALMACEN, ALMACEN_ID " +
                           "FROM EXISTENCIA_ARTS_MULT_ALMS(?, ?, ?) ORDER BY ARTICULO_ID";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, sincronizaExistenciaArticulosCondicionados);
                statement.setString(2, tipoPoliticaAOperar);
                statement.setInt(3, precioEmpresaId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        ExistenciaArticulo existenciaArticulo = new ExistenciaArticulo();
                        existenciaArticulo.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                        existenciaArticulo.setExistencia(resultSet.getInt("EXISTENCIA"));
                        existenciaArticulo.setNombreAlmacen(resultSet.getString("NOMBRE_ALMACEN"));
                        existenciaArticulo.setAlmacenId(resultSet.getInt("ALMACEN_ID"));

                        listaExistenciaArticulos.add(existenciaArticulo);
                    }
                }
            }

            return responseRequest.response(
                ResponseRequest.DataStatus.OK, 
                listaExistenciaArticulos, 
                "Existencia de artículos consultada correctamente"
            );

        } catch (SQLException exception) {
            logger.error("Error en existenciaArticulosRefactor: {}", exception.getMessage(), exception);
            return responseRequest.response(
                ResponseRequest.DataStatus.ERROR, 
                null, 
                "Error al consultar existencia de artículos: " + exception.getMessage()
            );
        }
    }
    
    public String paginarPoliticaDescuentoArticuloCliente() {
        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return "{}";
            }  
            
            // 1. OBTENER LA FECHA ACTUAL DE FORMA LIMPIA (Sin horas, minutos ni segundos)
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date fechaActual = calendar.getTime();

            Date fechaValidacion = null;

            // 2. Primera consulta: Obtener fecha de validación
            String queryValidacion = "SELECT DISTINCT(FECHA) FROM POLITICAS_DESC_ART_CLI_AH";
            try (PreparedStatement stmtValidar = connection.prepareStatement(queryValidacion);
                 ResultSet rsValidar = stmtValidar.executeQuery()) {

                if (rsValidar.next()) {
                    fechaValidacion = rsValidar.getDate("FECHA");  
                }
            }

            // 3. Comparación lógica de fechas
            boolean ejecutarProcedimiento = false;
            if (fechaValidacion != null) {
                
                if (fechaActual.compareTo(fechaValidacion) != 0) {
                    eliminaPoliticasDescuentosArticulos();
                    ejecutarProcedimiento = true;
                }
            } else {                
                ejecutarProcedimiento = true;
            }

            // 4. Segunda consulta: Ejecución del procedimiento o conteo
            ProcesaPoliticas procesaPoliticas = new ProcesaPoliticas();

            if (ejecutarProcedimiento) {
                String proc = "EXECUTE PROCEDURE POLITICAS_ARTICULOS_AH(CURRENT_DATE)";
                try (PreparedStatement stmtProc = connection.prepareStatement(proc);
                     ResultSet rsProc = stmtProc.executeQuery()) {
                    if (rsProc.next()) {
                        procesaPoliticas.setCuantasPoliticas(rsProc.getInt(1));
                    }
                }
            } else {
                String countQuery = "SELECT COUNT(ID) AS CUANTAS_POLITICAS FROM POLITICAS_DESC_ART_CLI_AH";
                try (PreparedStatement stmtCount = connection.prepareStatement(countQuery);
                     ResultSet rsCount = stmtCount.executeQuery()) {
                    if (rsCount.next()) {
                        procesaPoliticas.setCuantasPoliticas(rsCount.getInt("CUANTAS_POLITICAS"));
                    }
                }
            }

            return new Gson().toJson(procesaPoliticas);

        } catch (SQLException exception) {
            logger.error("Error SQL en paginarPoliticaDescuentoArticuloCliente: {}", exception.getMessage(), exception);
            return "{}";
        } catch (Exception exception) {
            logger.error("Error general en paginarPoliticaDescuentoArticuloCliente: {}", exception.getMessage(), exception);
            return "{}";
        }
    }
    
    public Boolean eliminaPoliticasDescuentosArticulos() {
        String query = "DELETE FROM POLITICAS_DESC_ART_CLI_AH";

        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return false;
            }

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
                return true;
            }

        } catch (SQLException exception) {
            logger.error("Error al eliminar políticas de descuentos de artículos: {}", exception.getMessage(), exception);
            return false;
        }
    }

    
    public String politicaDescuentoArticuloClienteRefactor(int pagina) {
        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return "[]";
            }

            List<PoliticaDescuentoArticuloClienteRefactor> listaPoliticaDescuentoArticuloCliente = new ArrayList<>();
            String query = "SELECT POLITICA_DSCTO_ART_CLI_ID, NOMBRE_POLITICA, ARTICULO_ID, DESCUENTO, ES_EXCLUSIVO " +
                           "FROM POLITICAS_DESC_ART_CLI_AH WHERE PAGINA = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, pagina);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        PoliticaDescuentoArticuloClienteRefactor politica = new PoliticaDescuentoArticuloClienteRefactor();

                        politica.setPoliticaDescuentoArticuloClienteId(resultSet.getInt("POLITICA_DSCTO_ART_CLI_ID"));
                        politica.setNombrePolitica(resultSet.getString("NOMBRE_POLITICA"));
                        politica.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                        politica.setDescuento(resultSet.getDouble("DESCUENTO"));

                        // Evaluación de nulos evitando doble lectura al ResultSet
                        String esExclusivo = resultSet.getString("ES_EXCLUSIVO");
                        politica.setEsExclusivo(esExclusivo != null ? esExclusivo : "N");

                        listaPoliticaDescuentoArticuloCliente.add(politica);
                    }
                }
            }

            return new Gson().toJson(listaPoliticaDescuentoArticuloCliente);

        } catch (SQLException exception) {
            logger.error("Error en politicaDescuentoArticuloClienteRefactor: {}", exception.getMessage(), exception);
            return "[]";
        }
    }
    
    public ResponseRequest politicasPorVolumen() { 
        ResponseRequest responseRequest = new ResponseRequest();

        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return responseRequest.response(
                    ResponseRequest.DataStatus.ERROR, 
                    null, 
                    "No se pudo conectar a la base de datos"
                );
            }
            
            ConfiguracionMobil configuracionMobil = configuracionMicrosip();

            List<PoliticaXVolumen> listaPoliticasXVolumen = new ArrayList<>();
            String query = "SELECT POLITICA_ID, NOMBRE_POLITICA, ARTICULO_ID, UNIDADES, DESCUENTO_VOLUMEN, ES_EXCLUSIVO_VOLUMEN " +
                           "FROM POLS_DSCTOS_ARTS_VOLUMENES(?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, configuracionMobil.getPrecioEmpresaId());
                statement.setInt(2, 0);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        PoliticaXVolumen politica = new PoliticaXVolumen();

                        politica.setPoliticaId(resultSet.getInt("POLITICA_ID"));

                        // Si requieres leer el nombre real en el futuro, solo reemplaza la cadena vacía por resultSet.getString("NOMBRE_POLITICA")
                        politica.setNombrePolitica(""); 

                        politica.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                        politica.setUnidades(resultSet.getInt("UNIDADES"));
                        politica.setDescuentoVolumen(resultSet.getDouble("DESCUENTO_VOLUMEN"));

                        // Evaluación defensiva contra nulos en una sola lectura
                        String esExclusivo = resultSet.getString("ES_EXCLUSIVO_VOLUMEN");
                        politica.setEsExclusivo(esExclusivo != null ? esExclusivo : "N");

                        listaPoliticasXVolumen.add(politica);
                    }
                }
            }

            return responseRequest.response(
                ResponseRequest.DataStatus.OK, 
                listaPoliticasXVolumen, 
                "Políticas por volumen consultadas correctamente"
            );

        } catch (SQLException exception) {
            logger.error("Error en politicasPorVolumen: {}", exception.getMessage(), exception);
            return responseRequest.response(
                ResponseRequest.DataStatus.ERROR, 
                null, 
                "Error al consultar políticas por volumen: " + exception.getMessage()
            );
        }
    }
    
    public ResponseRequest politicasPorVolumenMultiprecios(int precioEmpresaId) { 
        ResponseRequest responseRequest = new ResponseRequest();

        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return responseRequest.response(
                    ResponseRequest.DataStatus.ERROR, 
                    null, 
                    "No se pudo conectar a la base de datos"
                );
            }

            List<PoliticaXVolumen> listaPoliticasXVolumen = new ArrayList<>();
            String query = "SELECT POLITICA_ID, NOMBRE_POLITICA, ARTICULO_ID, UNIDADES, DESCUENTO_VOLUMEN, " +
                           "ES_EXCLUSIVO_VOLUMEN, PRECIO_EMPRESA_ID " +
                           "FROM POLS_DSCTOS_ARTS_VOLS_M(?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, precioEmpresaId);
                statement.setInt(2, 0);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        PoliticaXVolumen politica = new PoliticaXVolumen();

                        politica.setPoliticaId(resultSet.getInt("POLITICA_ID"));
                        politica.setNombrePolitica("");
                        politica.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                        politica.setUnidades(resultSet.getInt("UNIDADES"));
                        politica.setDescuentoVolumen(resultSet.getDouble("DESCUENTO_VOLUMEN"));

                        // Evaluación de nulos optimizada en una sola lectura
                        String esExclusivo = resultSet.getString("ES_EXCLUSIVO_VOLUMEN");
                        politica.setEsExclusivo(esExclusivo != null ? esExclusivo : "N");

                        politica.setPrecioEmpresaId(resultSet.getInt("PRECIO_EMPRESA_ID"));

                        listaPoliticasXVolumen.add(politica);
                    }
                }
            }

            return responseRequest.response(
                ResponseRequest.DataStatus.OK, 
                listaPoliticasXVolumen, 
                "Políticas por volumen multiprecios consultadas correctamente"
            );

        } catch (SQLException exception) {            
            return responseRequest.response(
                ResponseRequest.DataStatus.ERROR, 
                null, 
                "Error al consultar políticas por volumen multiprecios: " + exception.getMessage()
            );
        }
    }
    
    public ResponseRequest serieFolioCXC(int cobradorId) { 
        ResponseRequest responseRequest = new ResponseRequest();

        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {                
                return responseRequest.response(
                    ResponseRequest.DataStatus.ERROR, 
                    null, 
                    "No se pudo conectar a la base de datos"
                );
            }

            SerieFolioCXC serieFolioCXC = null;
            String query = "SELECT ID, COBRADOR_ID, SERIE, FOLIO FROM SERIES_FOLIOS_CXC WHERE COBRADOR_ID = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, cobradorId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        serieFolioCXC = new SerieFolioCXC();
                        serieFolioCXC.setId(resultSet.getInt("ID"));
                        serieFolioCXC.setCobradorId(resultSet.getInt("COBRADOR_ID"));
                        serieFolioCXC.setSerie(resultSet.getString("SERIE"));
                        serieFolioCXC.setFolio(resultSet.getInt("FOLIO"));
                    }
                }
            }

            if (serieFolioCXC == null) {
                return responseRequest.response(
                    ResponseRequest.DataStatus.ERROR, 
                    null, 
                    "No se encontró serie y folio para el cobrador especificado"
                );
            }

            return responseRequest.response(
                ResponseRequest.DataStatus.OK, 
                serieFolioCXC, 
                "Serie y folio consultados correctamente"
            );

        } catch (SQLException exception) {
            logger.error("Error en serieFolioCXC: {}", exception.getMessage(), exception);
            return responseRequest.response(
                ResponseRequest.DataStatus.ERROR, 
                null, 
                "Error al consultar serie y folio CXC: " + exception.getMessage()
            );
        }
    }
    
    public String clientesRefactor(int vendedorId) {
        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return null;
            }

            ConfiguracionMobil configuracionMobil = configuracionMicrosip();
            
            boolean operaPop = configuracionMobil.getOperaPop() == 1;
            boolean aplicaPoliticaArticulo = "ARTICULO_CLIENTE".equals(configuracionMobil.getTipoPoliticaAOperar()) 
                                          || "MULTIPRECIOS".equals(configuracionMobil.getTipoPoliticaAOperar());   

            // Obtener la configuración del trimestre desde la base de datos
            TrimestreAnioInfo trimestreAnioInfo = new TrimestreAnioInfo();
            if (operaPop) {
                trimestreAnioInfo = obtenerTrimestreAnterior(connection);     
            }

            // Construcción dinámica de la consulta de clientes
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT C.CLIENTE_ID, C.CLAVE_CLIENTE, C.NOMBRE_CLIENTE, C.TIPO_CLIENTE_ID, C.ZONA_CLIENTE_ID, ")
               .append("C.COBRADOR_ID, C.VENDEDOR_ID, C.DIR_CLI_ID, C.RFC_CURP, C.TELEFONO1, C.LIMITE_CREDITO, ")
               .append("C.ESTATUS, C.MONEDA_ID, ");

            if (aplicaPoliticaArticulo) {
                sql.append("CPAH.POLITICA_DSCTO_ART_CLI_ID, ");
            } else {
                sql.append("0 AS POLITICA_DSCTO_ART_CLI_ID, ");
            }

            if (operaPop) {
                sql.append("COALESCE(PT.IMPORTE_POP_GANADO_CON_IMP, 0.00) AS IMPORTE_POP_GANADO_CON_IMP, ")
                   .append("COALESCE(PT.ESTATUS_POP, '') AS ESTATUS_POP, ")
                   .append("COALESCE(PT.MONTO_MINIMO_VENTA, 0.00) AS MONTO_MINIMO_VENTA ");
            } else {
                sql.append("0.00 AS IMPORTE_POP_GANADO_CON_IMP, '' AS ESTATUS_POP, 0.00 AS MONTO_MINIMO_VENTA ");
            }

            sql.append("FROM CLIENTES_AH C ");

            if (aplicaPoliticaArticulo) {
                sql.append("LEFT JOIN CLIENTES_POLITICAS_AH(?, C.CLIENTE_ID) CPAH ON 1 = 1 ");
            }

            if (operaPop) {
                sql.append("LEFT JOIN POP_TRIMESTRAL PT ON PT.CLIENTE_ID = C.CLIENTE_ID ")
                   .append("AND PT.ANIO = ? AND PT.TRIMESTRE = ? ");
            }

            sql.append("WHERE C.VENDEDOR_ID = ?");

            List<ClienteRefactor> listaCliente = new ArrayList<>();

            try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
                int paramIndex = 1;

                if (aplicaPoliticaArticulo) {
                    statement.setInt(paramIndex++, configuracionMobil.getPrecioEmpresaId());
                }
                
                if (operaPop) {
                    statement.setInt(paramIndex++, trimestreAnioInfo.getAnio());
                    statement.setInt(paramIndex++, trimestreAnioInfo.getTrimestre());
                }

                statement.setInt(paramIndex, vendedorId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    boolean ignoraPolitica = configuracionMobil.getApplyPoliticaPrecioClientes() == 1;

                    while (resultSet.next()) {
                        ClienteRefactor cliente = new ClienteRefactor();

                        cliente.setClienteId(resultSet.getInt("CLIENTE_ID"));
                        cliente.setClaveCliente(resultSet.getString("CLAVE_CLIENTE"));

                        String nombreCliente = resultSet.getString("NOMBRE_CLIENTE");
                        cliente.setNombreCliente(nombreCliente != null ? nombreCliente.trim() : "");

                        cliente.setTipoClienteId(resultSet.getInt("TIPO_CLIENTE_ID"));
                        cliente.setZonaClienteId(resultSet.getInt("ZONA_CLIENTE_ID"));
                        cliente.setCobradorId(resultSet.getInt("COBRADOR_ID"));
                        cliente.setVendedorId(resultSet.getInt("VENDEDOR_ID"));
                        cliente.setRfcCurp(resultSet.getString("RFC_CURP"));
                        cliente.setDireccionClienteId(resultSet.getInt("DIR_CLI_ID"));

                        String telefono = resultSet.getString("TELEFONO1");
                        cliente.setTelefono1(telefono != null ? telefono : "0000");

                        if (ignoraPolitica) {
                            cliente.setPoliticaDescuentoArticuloClienteId(0);
                        } else {
                            cliente.setPoliticaDescuentoArticuloClienteId(resultSet.getInt("POLITICA_DSCTO_ART_CLI_ID"));
                        }

                        cliente.setLimiteCredito(resultSet.getDouble("LIMITE_CREDITO"));
                        cliente.setEstatus(resultSet.getString("ESTATUS"));
                        cliente.setMonedaId(resultSet.getInt("MONEDA_ID"));
                        cliente.setSaldoPOP(resultSet.getDouble("IMPORTE_POP_GANADO_CON_IMP"));
                        cliente.setEstatusPOP(resultSet.getString("ESTATUS_POP"));
                        cliente.setMontoMinimoVenta(resultSet.getDouble("MONTO_MINIMO_VENTA"));

                        listaCliente.add(cliente);
                    }
                }
            }

            return new Gson().toJson(listaCliente);

        } catch (SQLException exception) {
            logger.error("Error en clientesRefactor para el vendedor {}: {}", vendedorId, exception.getMessage(), exception);
            return null;
        }
    }
    
    public String clientesRefactor() {
        try (Connection connection = FirebirdConnector.getConnection()) {

            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return null;
            }

            ConfiguracionMobil configuracionMobil = configuracionMicrosip();

            boolean operaPop = configuracionMobil.getOperaPop() == 1;
            boolean aplicaPoliticaArticulo = "ARTICULO_CLIENTE".equals(configuracionMobil.getTipoPoliticaAOperar()) 
                                          || "MULTIPRECIOS".equals(configuracionMobil.getTipoPoliticaAOperar());

            TrimestreAnioInfo trimestreAnioInfo = new TrimestreAnioInfo();

            // Obtener la configuración del trimestre desde la base de datos
            if (operaPop) {                
                trimestreAnioInfo = obtenerTrimestreAnterior(connection);                          
            }

            // Construcción dinámica de la consulta global (sin filtro de vendedor)
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT C.CLIENTE_ID, C.CLAVE_CLIENTE, C.NOMBRE_CLIENTE, C.TIPO_CLIENTE_ID, C.ZONA_CLIENTE_ID, ")
               .append("C.COBRADOR_ID, C.VENDEDOR_ID, C.DIR_CLI_ID, C.RFC_CURP, C.TELEFONO1, C.LIMITE_CREDITO, ")
               .append("C.ESTATUS, C.MONEDA_ID, ");

            if (aplicaPoliticaArticulo) {
                sql.append("CPAH.POLITICA_DSCTO_ART_CLI_ID, ");
            } else {
                sql.append("0 AS POLITICA_DSCTO_ART_CLI_ID, ");
            }

            if (operaPop) {
                sql.append("COALESCE(PT.IMPORTE_POP_GANADO_CON_IMP, 0.00) AS IMPORTE_POP_GANADO_CON_IMP, ")
                   .append("COALESCE(PT.ESTATUS_POP, '') AS ESTATUS_POP, ")
                   .append("COALESCE(PT.MONTO_MINIMO_VENTA, 0.00) AS MONTO_MINIMO_VENTA ");
            } else {
                sql.append("0.00 AS IMPORTE_POP_GANADO_CON_IMP, '' AS ESTATUS_POP, 0.00 AS MONTO_MINIMO_VENTA ");
            }

            sql.append("FROM CLIENTES_AH C ");

            if (aplicaPoliticaArticulo) {
                sql.append("LEFT JOIN CLIENTES_POLITICAS_AH(?, C.CLIENTE_ID) CPAH ON 1 = 1 ");
            }

            if (operaPop) {
                sql.append("LEFT JOIN POP_TRIMESTRAL PT ON PT.CLIENTE_ID = C.CLIENTE_ID ")
                   .append("AND PT.ANIO = ? AND PT.TRIMESTRE = ?");
            }

            List<ClienteRefactor> listaCliente = new ArrayList<>();

            try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
                int paramIndex = 1;

                if (aplicaPoliticaArticulo) {
                    statement.setInt(paramIndex++, configuracionMobil.getPrecioEmpresaId());
                }

                if (operaPop) {
                    statement.setInt(paramIndex++, trimestreAnioInfo.getAnio());
                    statement.setInt(paramIndex++, trimestreAnioInfo.getTrimestre());
                }

                try (ResultSet resultSet = statement.executeQuery()) {
                    boolean ignoraPolitica = configuracionMobil.getApplyPoliticaPrecioClientes() == 1;

                    while (resultSet.next()) {
                        ClienteRefactor cliente = new ClienteRefactor();

                        cliente.setClienteId(resultSet.getInt("CLIENTE_ID"));
                        cliente.setClaveCliente(resultSet.getString("CLAVE_CLIENTE"));

                        String nombreCliente = resultSet.getString("NOMBRE_CLIENTE");
                        cliente.setNombreCliente(nombreCliente != null ? nombreCliente.trim() : "");

                        cliente.setTipoClienteId(resultSet.getInt("TIPO_CLIENTE_ID"));
                        cliente.setZonaClienteId(resultSet.getInt("ZONA_CLIENTE_ID"));
                        cliente.setCobradorId(resultSet.getInt("COBRADOR_ID"));
                        cliente.setVendedorId(resultSet.getInt("VENDEDOR_ID"));
                        cliente.setRfcCurp(resultSet.getString("RFC_CURP"));
                        cliente.setDireccionClienteId(resultSet.getInt("DIR_CLI_ID"));

                        String telefono = resultSet.getString("TELEFONO1");
                        cliente.setTelefono1(telefono != null ? telefono : "0000");

                        if (ignoraPolitica) {
                            cliente.setPoliticaDescuentoArticuloClienteId(0);
                        } else {
                            cliente.setPoliticaDescuentoArticuloClienteId(resultSet.getInt("POLITICA_DSCTO_ART_CLI_ID"));
                        }

                        cliente.setLimiteCredito(resultSet.getDouble("LIMITE_CREDITO"));
                        cliente.setEstatus(resultSet.getString("ESTATUS"));
                        cliente.setMonedaId(resultSet.getInt("MONEDA_ID"));
                        cliente.setSaldoPOP(resultSet.getDouble("IMPORTE_POP_GANADO_CON_IMP"));
                        cliente.setEstatusPOP(resultSet.getString("ESTATUS_POP"));
                        cliente.setMontoMinimoVenta(resultSet.getDouble("MONTO_MINIMO_VENTA"));

                        listaCliente.add(cliente);
                    }
                }
            }

            return new Gson().toJson(listaCliente);

        } catch (SQLException exception) {
            logger.error("Error en clientesRefactor (general): {}", exception.getMessage(), exception);
            return null;
        }
    }
    
    public String clientesEmitenFactura(int vendedorId) {
        String sql = "SELECT LC.CLIENTE_ID, B.VALOR_DESPLEGADO " +
                     "FROM LIBRES_CLIENTES LC " +
                     "JOIN LISTAS_ATRIBUTOS B ON B.LISTA_ATRIB_ID = LC.EMITE_FACTURA " +
                     "INNER JOIN CLIENTES C ON C.CLIENTE_ID = LC.CLIENTE_ID " +
                     "WHERE C.VENDEDOR_ID = ?";

        try (Connection connection = FirebirdConnector.getConnection()) {
            if (connection == null) {
                System.out.println("ERROR: No se pudo obtener la conexión a Firebird.");
                return null;
            }

            List<ClienteEmiteFactura> listaClienteEmiteFactura = new ArrayList<>();

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, vendedorId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        ClienteEmiteFactura clienteEmiteFactura = new ClienteEmiteFactura();
                        clienteEmiteFactura.setClienteId(resultSet.getInt("CLIENTE_ID"));

                        String valorDesplegado = resultSet.getString("VALOR_DESPLEGADO");
                        clienteEmiteFactura.setValorDesplegado(valorDesplegado != null ? valorDesplegado.trim() : "");

                        listaClienteEmiteFactura.add(clienteEmiteFactura);
                    }
                }
            }

            String jsonResultado = new Gson().toJson(listaClienteEmiteFactura);
            System.out.println(jsonResultado);
            return jsonResultado;

        } catch (SQLException e) {
            System.out.println("Error SQL en clientesEmitenFactura por vendedor: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Error inesperado en clientesEmitenFactura por vendedor: " + e.getMessage());
            return null;
        }
    }
    
    public String clientesEmitenFactura() {
        String sql = "SELECT LC.CLIENTE_ID, B.VALOR_DESPLEGADO " +
                     "FROM LIBRES_CLIENTES LC " +
                     "JOIN LISTAS_ATRIBUTOS B ON B.LISTA_ATRIB_ID = LC.EMITE_FACTURA";

        try (Connection connection = FirebirdConnector.getConnection()) {
            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return null;
            }

            List<ClienteEmiteFactura> listaClienteEmiteFactura = new ArrayList<>();

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    ClienteEmiteFactura clienteEmiteFactura = new ClienteEmiteFactura();
                    clienteEmiteFactura.setClienteId(resultSet.getInt("CLIENTE_ID"));

                    String valorDesplegado = resultSet.getString("VALOR_DESPLEGADO");
                    clienteEmiteFactura.setValorDesplegado(valorDesplegado != null ? valorDesplegado.trim() : "");

                    listaClienteEmiteFactura.add(clienteEmiteFactura);
                }
            }

            String jsonResultado = new Gson().toJson(listaClienteEmiteFactura);
            return jsonResultado;

        } catch (SQLException e) {
            logger.error("Error SQL en clientesEmitenFactura general: {}", e.getMessage(), e);
            return null;
        } catch (Exception e) {
            logger.error("Error inesperado en clientesEmitenFactura general: {}", e.getMessage(), e);
            return null;
        }
    }
    
    public ResponseRequest clientesDireccionPrincipal(int vendedorId) {
        ResponseRequest responseRequest = new ResponseRequest();

        String query = "SELECT C.CLIENTE_ID, DC.CALLE, DC.NUM_EXTERIOR, DC.NUM_INTERIOR, DC.COLONIA, " +
                       "DC.POBLACION, DC.CODIGO_POSTAL, DC.TELEFONO1, DC.TELEFONO2 " +
                       "FROM CLIENTES C " +
                       "INNER JOIN DIRS_CLIENTES DC ON DC.CLIENTE_ID = C.CLIENTE_ID " +
                       "WHERE DC.ES_DIR_PPAL = 'S' AND C.VENDEDOR_ID = ?";

        try (Connection connection = FirebirdConnector.getConnection()) {
            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al conectar con la base de datos");
            }

            List<ClienteDireccionPrincipal> listaClientesDireccionPrincipal = new ArrayList<>();

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, vendedorId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        ClienteDireccionPrincipal clienteDireccionPrincipal = new ClienteDireccionPrincipal();
                        clienteDireccionPrincipal.setClienteId(resultSet.getInt("CLIENTE_ID"));
                        clienteDireccionPrincipal.setCalle(resultSet.getString("CALLE"));
                        clienteDireccionPrincipal.setNumeroExterior(resultSet.getString("NUM_EXTERIOR"));
                        clienteDireccionPrincipal.setNumeroInterior(resultSet.getString("NUM_INTERIOR"));
                        clienteDireccionPrincipal.setColonia(resultSet.getString("COLONIA"));

                        String poblacion = resultSet.getString("POBLACION");
                        clienteDireccionPrincipal.setPoblacion(poblacion != null ? poblacion.trim() : "SP");

                        clienteDireccionPrincipal.setCodigoPostal(resultSet.getString("CODIGO_POSTAL"));
                        clienteDireccionPrincipal.setTelefono1(resultSet.getString("TELEFONO1"));
                        clienteDireccionPrincipal.setTelefono2(resultSet.getString("TELEFONO2"));

                        listaClientesDireccionPrincipal.add(clienteDireccionPrincipal);
                    }
                }
            }

            return responseRequest.response(ResponseRequest.DataStatus.OK, listaClientesDireccionPrincipal, "Direcciones clientes consultadas correctamente");

        } catch (SQLException exception) {
            logger.error("SUCEDIO UNA EXCEPCION al consultar direcciones clientes: " + exception.getMessage(), exception);
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error consultar direcciones clientes " + exception.getMessage());
        } catch (Exception exception) {
            logger.error("Error inesperado al consultar direcciones clientes: " + exception.getMessage(), exception);
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error inesperado al consultar direcciones clientes " + exception.getMessage());
        }
    }
    
    public ResponseRequest clientesDireccionPrincipal() {
        ResponseRequest responseRequest = new ResponseRequest();

        String query = "SELECT C.CLIENTE_ID, DC.CALLE, DC.NUM_EXTERIOR, DC.NUM_INTERIOR, DC.COLONIA, " +
                       "DC.POBLACION, DC.CODIGO_POSTAL, DC.TELEFONO1, DC.TELEFONO2 " +
                       "FROM CLIENTES C " +
                       "INNER JOIN DIRS_CLIENTES DC ON DC.CLIENTE_ID = C.CLIENTE_ID " +
                       "WHERE DC.ES_DIR_PPAL = 'S'";

        try (Connection connection = FirebirdConnector.getConnection()) {
            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al conectar con la base de datos");
            }

            List<ClienteDireccionPrincipal> listaClientesDireccionPrincipal = new ArrayList<>();

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    ClienteDireccionPrincipal clienteDireccionPrincipal = new ClienteDireccionPrincipal();
                    clienteDireccionPrincipal.setClienteId(resultSet.getInt("CLIENTE_ID"));
                    clienteDireccionPrincipal.setCalle(resultSet.getString("CALLE"));
                    clienteDireccionPrincipal.setNumeroExterior(resultSet.getString("NUM_EXTERIOR"));
                    clienteDireccionPrincipal.setNumeroInterior(resultSet.getString("NUM_INTERIOR"));
                    clienteDireccionPrincipal.setColonia(resultSet.getString("COLONIA"));

                    String poblacion = resultSet.getString("POBLACION");
                    clienteDireccionPrincipal.setPoblacion(poblacion != null ? poblacion.trim() : "SP");

                    clienteDireccionPrincipal.setCodigoPostal(resultSet.getString("CODIGO_POSTAL"));
                    clienteDireccionPrincipal.setTelefono1(resultSet.getString("TELEFONO1"));
                    clienteDireccionPrincipal.setTelefono2(resultSet.getString("TELEFONO2"));

                    listaClientesDireccionPrincipal.add(clienteDireccionPrincipal);
                }
            }

            return responseRequest.response(ResponseRequest.DataStatus.OK, listaClientesDireccionPrincipal, "Direcciones clientes consultadas correctamente");

        } catch (SQLException exception) {
            logger.error("SUCEDIO UNA EXCEPCION al consultar direcciones clientes: " + exception.getMessage(), exception);
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error consultar direcciones clientes " + exception.getMessage());
        } catch (Exception exception) {
            logger.error("Error inesperado al consultar direcciones clientes: " + exception.getMessage(), exception);
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error inesperado al consultar direcciones clientes " + exception.getMessage());
        }
    }
    
    public String clientesConsignatariosAGO2022(int vendedorId) {
        String query = "SELECT DC.CLIENTE_ID, DC.DIR_CLI_ID, DC.NOMBRE_CONSIG, DC.USAR_PARA_ENVIOS, DC.USAR_PARA_FACTURAR " +
                       "FROM CLIENTES C " +
                       "INNER JOIN DIRS_CLIENTES DC ON DC.CLIENTE_ID = C.CLIENTE_ID " +
                       "WHERE DC.ES_DIR_PPAL = 'N' AND C.VENDEDOR_ID = ?";

        try (Connection connection = FirebirdConnector.getConnection()) {
            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return null;
            }

            List<ClienteConsignatario> listaClientesConsignatarios = new ArrayList<>();

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, vendedorId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        ClienteConsignatario clienteConsignatario = new ClienteConsignatario();
                        clienteConsignatario.setClienteId(resultSet.getInt("CLIENTE_ID"));
                        clienteConsignatario.setDireccionClienteId(resultSet.getInt("DIR_CLI_ID"));

                        String nombreConsignatario = resultSet.getString("NOMBRE_CONSIG");
                        clienteConsignatario.setNombreConsignatario(nombreConsignatario != null ? nombreConsignatario.trim() : "");

                        clienteConsignatario.setUsarParaEnvio(resultSet.getString("USAR_PARA_ENVIOS"));
                        clienteConsignatario.setUsarParaFacturar(resultSet.getString("USAR_PARA_FACTURAR"));

                        listaClientesConsignatarios.add(clienteConsignatario);
                    }
                }
            }

            return new Gson().toJson(listaClientesConsignatarios);

        } catch (SQLException exception) {
            logger.error("Error SQL al consultar consignatarios por vendedorId ({}): {}", vendedorId, exception.getMessage(), exception);
            return null;
        } catch (Exception exception) {
            logger.error("Error inesperado al consultar consignatarios por vendedorId ({}): {}", vendedorId, exception.getMessage(), exception);
            return null;
        }
    }
    
    public String clientesConsignatariosAGO2022() throws SQLException {
        String query = "SELECT DC.CLIENTE_ID, DC.DIR_CLI_ID, DC.NOMBRE_CONSIG, DC.USAR_PARA_ENVIOS, DC.USAR_PARA_FACTURAR " +
                       "FROM CLIENTES C " +
                       "INNER JOIN DIRS_CLIENTES DC ON DC.CLIENTE_ID = C.CLIENTE_ID " +
                       "WHERE DC.ES_DIR_PPAL = 'N'";

        try (Connection connection = FirebirdConnector.getConnection()) {
            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return null;
            }

            List<ClienteConsignatario> listaClientesConsignatarios = new ArrayList<>();

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    ClienteConsignatario clienteConsignatario = new ClienteConsignatario();
                    clienteConsignatario.setClienteId(resultSet.getInt("CLIENTE_ID"));
                    clienteConsignatario.setDireccionClienteId(resultSet.getInt("DIR_CLI_ID"));

                    String nombreConsignatario = resultSet.getString("NOMBRE_CONSIG");
                    clienteConsignatario.setNombreConsignatario(nombreConsignatario != null ? nombreConsignatario.trim() : "");

                    clienteConsignatario.setUsarParaEnvio(resultSet.getString("USAR_PARA_ENVIOS"));
                    clienteConsignatario.setUsarParaFacturar(resultSet.getString("USAR_PARA_FACTURAR"));

                    listaClientesConsignatarios.add(clienteConsignatario);
                }
            }

            return new Gson().toJson(listaClientesConsignatarios);

        } catch (SQLException exception) {
            logger.error("Error SQL al consultar consignatarios general: {}", exception.getMessage(), exception);
            return null;
        } catch (Exception exception) {
            logger.error("Error inesperado al consultar consignatarios general: {}", exception.getMessage(), exception);
            return null;
        }
    }
    
    public String cobranzaRefactor(int vendedorId) throws SQLException {
        String query = "SELECT C.CLIENTE_ID, C.NOMBRE, B.DOCTO_CC_ID, B.FOLIO, B.FECHA_ELABORACION, " +
                       "B.FECHA_VENCIMIENTO, B.IMPORTE_CARGO, B.SALDO_CARGO, B.ATRASO, B.CONCEPTO_CC_ID, " +
                       "COALESCE(DC.NOMBRE_CONSIG, 'Dirección principal') AS NOMBRE_CONSIG, DVE.DOCTO_VE_ID " +
                       "FROM CLIENTES C " +
                       "LEFT JOIN CARGOS_CLIENTE_AH(C.CLIENTE_ID, CURRENT_DATE, CURRENT_DATE, 'N', 'S') B ON 1=1 " +
                       "LEFT JOIN DOCTOS_ENTRE_SIS DS ON (DS.DOCTO_DEST_ID = B.DOCTO_CC_ID AND CLAVE_SIS_DEST = 'CC' AND CLAVE_SIS_FTE = 'VE' AND DS.TIPO_DOCTO = 'C') " +
                       "LEFT JOIN DOCTOS_VE DVE ON (DVE.DOCTO_VE_ID = DS.DOCTO_FTE_ID) " +
                       "LEFT JOIN DIRS_CLIENTES DC ON DC.DIR_CLI_ID = DVE.DIR_CLI_ID " +
                       "WHERE B.DOCTO_CC_ID IS NOT NULL AND C.VENDEDOR_ID = ? " +
                       "ORDER BY B.ATRASO";

        try (Connection connection = FirebirdConnector.getConnection()) {
            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return null;
            }

            List<CobranzaRefactor> listaCobranza = new ArrayList<>();

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, vendedorId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        CobranzaRefactor cobranza = new CobranzaRefactor();
                        cobranza.setClienteId(resultSet.getInt("CLIENTE_ID"));

                        String nombreCliente = resultSet.getString("NOMBRE");
                        cobranza.setNombreCliente(nombreCliente != null ? nombreCliente.trim() : "");

                        cobranza.setDoctoCCId(resultSet.getInt("DOCTO_CC_ID"));
                        cobranza.setFolio(resultSet.getString("FOLIO"));
                        cobranza.setFechaElaboracion(resultSet.getDate("FECHA_ELABORACION"));
                        cobranza.setFechaVencimiento(resultSet.getDate("FECHA_VENCIMIENTO"));
                        cobranza.setImporteCargo(resultSet.getDouble("IMPORTE_CARGO"));
                        cobranza.setSaldoCargo(resultSet.getDouble("SALDO_CARGO"));
                        cobranza.setAtraso(resultSet.getInt("ATRASO"));
                        cobranza.setConceptoCCId(resultSet.getInt("CONCEPTO_CC_ID"));

                        String nombreConsignatario = resultSet.getString("NOMBRE_CONSIG");
                        cobranza.setNombreConsignatario(nombreConsignatario != null ? nombreConsignatario.trim() : "Dirección principal");

                        cobranza.setDoctoVEId(resultSet.getInt("DOCTO_VE_ID"));

                        listaCobranza.add(cobranza);
                    }
                }
            }

            return new Gson().toJson(listaCobranza);

        } catch (SQLException exception) {
            logger.error("Error SQL al consultar cobranzaRefactor para vendedorId ({}): {}", vendedorId, exception.getMessage(), exception);
            return null;
        } catch (Exception exception) {
            logger.error("Error inesperado al consultar cobranzaRefactor para vendedorId ({}): {}", vendedorId, exception.getMessage(), exception);
            return null;
        }
    }
    
    public ResponseRequest detalleDocumentoCXC(String jsonString) throws SQLException {
        logger.info("Consultando detalleDocumentoCXC: " + jsonString);
        ResponseRequest responseRequest = new ResponseRequest();

        Type type = new TypeToken<ArrayList<Long>>(){}.getType();
        List<Long> listaIds = new Gson().fromJson(jsonString, type);

        if (listaIds == null || listaIds.isEmpty()) {
            return responseRequest.response(ResponseRequest.DataStatus.OK, new ArrayList<>(), "Lista de IDs vacía.");
        }

        String ids = listaIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        String query = "SELECT DVD.DOCTO_VE_ID, DVD.CLAVE_ARTICULO, DVD.ARTICULO_ID, DVD.UNIDADES, DVD.PRECIO_UNITARIO, DVD.PRECIO_TOTAL_NETO " +
                       "FROM DOCTOS_VE_DET DVD " +
                       "INNER JOIN DOCTOS_VE DV ON DV.DOCTO_VE_ID = DVD.DOCTO_VE_ID " +
                       "WHERE DVD.DOCTO_VE_ID IN (" + ids + ")";

        try (Connection connection = FirebirdConnector.getConnection()) {
            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al conectar con la base de datos");
            }

            List<DetalleDocumentoCXC> listaDetalleDocumentoCXC = new ArrayList<>();

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    DetalleDocumentoCXC detalleDocumentoCXC = new DetalleDocumentoCXC();
                    detalleDocumentoCXC.setDoctoVEId(resultSet.getInt("DOCTO_VE_ID"));

                    String claveArticulo = resultSet.getString("CLAVE_ARTICULO");
                    detalleDocumentoCXC.setClaveArticulo(claveArticulo != null ? claveArticulo.trim() : "");

                    detalleDocumentoCXC.setArticuloId(resultSet.getInt("ARTICULO_ID"));
                    detalleDocumentoCXC.setUnidades(resultSet.getInt("UNIDADES"));
                    detalleDocumentoCXC.setPrecioUnitario(resultSet.getDouble("PRECIO_UNITARIO"));
                    detalleDocumentoCXC.setPrecioTotalNeto(resultSet.getDouble("PRECIO_TOTAL_NETO"));

                    listaDetalleDocumentoCXC.add(detalleDocumentoCXC);
                }
            }

            return responseRequest.response(ResponseRequest.DataStatus.OK, listaDetalleDocumentoCXC, "Detalle Documento CXC consultados correctamente");

        } catch (SQLException exception) {
            logger.error("Excepción SQL en detalleDocumentoCXC: " + exception.getMessage(), exception);
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar detalleDocumentoCXC " + exception.getMessage());
        } catch (Exception exception) {
            logger.error("Excepción inesperada en detalleDocumentoCXC: " + exception.getMessage(), exception);
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error inesperado al consultar detalleDocumentoCXC " + exception.getMessage());
        }
    }
    
    public ResponseRequest clientesPoliticas(int vendedorId) {
        ResponseRequest responseRequest = new ResponseRequest();

        String queryArticulos = "SELECT A.CLIENTE_ID, A.CLAVE_CLIENTE, A.POLITICA_PRECIOS_CLI_ID, A.NOMBRE_POLITICA, " +
                                "A.POLITICA_DSCTO_ART_CLI_ID, A.PRECIO_EMPRESA_ID, A.ORIGEN, B.NOMBRE " +
                                "FROM POLITICAS_CLIENTES(?) A " +
                                "INNER JOIN PRECIOS_EMPRESA B ON A.PRECIO_EMPRESA_ID = B.PRECIO_EMPRESA_ID";

        try (Connection connection = FirebirdConnector.getConnection()) {
            if (connection == null) {
                logger.error("No se pudo establecer conexión a la base de datos.");
                return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al conectar con la base de datos");
            }            

            List<PoliticaCliente> listaPoliticasClientes = new ArrayList<>();

            try (PreparedStatement preparedStatement = connection.prepareStatement(queryArticulos)) {
                preparedStatement.setInt(1, vendedorId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        PoliticaCliente politicaCliente = new PoliticaCliente();
                        politicaCliente.setClienteId(resultSet.getInt("CLIENTE_ID"));

                        String claveCliente = resultSet.getString("CLAVE_CLIENTE");
                        politicaCliente.setClaveCliente(claveCliente != null ? claveCliente.trim() : "");

                        politicaCliente.setPoliticaPreciosClienteId(resultSet.getInt("POLITICA_PRECIOS_CLI_ID"));

                        String nombrePolitica = resultSet.getString("NOMBRE_POLITICA");
                        politicaCliente.setNombrePolitica(nombrePolitica != null ? nombrePolitica.trim() : "");

                        politicaCliente.setPoliticaDescuentoArticuloClienteId(resultSet.getInt("POLITICA_DSCTO_ART_CLI_ID"));
                        politicaCliente.setPrecioEmpresaId(resultSet.getInt("PRECIO_EMPRESA_ID"));

                        String origen = resultSet.getString("ORIGEN");
                        politicaCliente.setOrigen(origen != null ? origen.trim() : "");

                        String nombrePrecio = resultSet.getString("NOMBRE");
                        politicaCliente.setNombrePrecio(nombrePrecio != null ? nombrePrecio.trim() : "");

                        listaPoliticasClientes.add(politicaCliente);
                    }
                }
            }

            return responseRequest.response(ResponseRequest.DataStatus.OK, listaPoliticasClientes, "Politicas clientes consultados correctamente");

        } catch (SQLException exception) {
            logger.error("Excepción SQL en clientesPoliticas para vendedorId ({}): {}", vendedorId, exception.getMessage(), exception);
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al consultar Politicas clientes " + exception.getMessage());
        } catch (Exception exception) {
            logger.error("Excepción inesperada en clientesPoliticas para vendedorId ({}): {}", vendedorId, exception.getMessage(), exception);
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error inesperado al consultar Politicas clientes " + exception.getMessage());
        }
    }
    
    public ResponseRequest createPedidosPOP(String jsonPedidos) {
        logger.info("Solicitud POST recibida (createPedidos): " + jsonPedidos);
        ResponseRequest responseRequest = new ResponseRequest();
        List<PedidoGrabado> listaPedidosGrabados = new ArrayList<>();

        List<MaestroPedido> pedidos = parsearPedidosJson(jsonPedidos);

        // AQUÍ SE INSTANCIA Y PIDE LA CONEXIÓN AL POOL DE HIKARICP
        try (Connection conexion = FirebirdConnector.getConnection()) {

            // Comprobación de seguridad por si el pool no devolvió conexión
            if (conexion == null) {
                return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "No se pudo obtener conexión con Firebird");
            }

            ConfiguracionMobil configuracionMobil = configuracionMicrosip();
                       
            try {
                for (MaestroPedido pedido : pedidos) {
                    // Inicias la transacción en la conexión activa
                    conexion.setAutoCommit(false);
            
                    // Pasas la variable "conexion" local a los demás métodos
                    String folioAsignado = procesarPedido(conexion, configuracionMobil, pedido);

                    // Este objeto es para agregar el id que se generp en ANDROID y retornarlo en una lista                    
                    PedidoGrabado pedidoGrabado = new PedidoGrabado();
                    pedidoGrabado.setId(pedido.getId());
                    pedidoGrabado.setNummov(0);
                    pedidoGrabado.setFolio(folioAsignado);
                    listaPedidosGrabados.add(pedidoGrabado);
                    
                    conexion.commit(); // Si todo salió bien, guardas cambios
                }
                
                return responseRequest.response(ResponseRequest.DataStatus.OK, listaPedidosGrabados, "Pedidos grabados correctamente");

            } catch (SQLException e) {
                conexion.rollback(); // Si algo falla, deshaces cambios
                logger.error("Error en la transacción de pedidos: " + e.getMessage(), e);
                return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al procesar los pedidos: " + e.getMessage());
            }

        } catch (SQLException exception) {
            logger.error("Error en la conexión: " + exception.getMessage(), exception);
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error general: " + exception.getMessage());
        } 
        // AQUÍ AL CERRAR LA LLAVE, EL TRY-WITH-RESOURCES DE VUELVE LA CONEXIÓN AL POOL AUTOMÁTICAMENTE
    }
    
    private List<MaestroPedido> parsearPedidosJson(String jsonPedidos) {
        Type listType = new TypeToken<List<MaestroPedido>>() {}.getType();
        return new Gson().fromJson(jsonPedidos, listType);
    }

    /**
    * Orquesta la inserción individual del pedido si este no existe.
    */
    private String procesarPedido(Connection conexion, ConfiguracionMobil configuracionMobil, MaestroPedido pedido) throws SQLException {
        PedidoExistente pedidoExistente = existePedidoGuardado(conexion, pedido.getUuid());
        String serieFolio = pedidoExistente.getFolio();

        if (pedidoExistente.getNumeroMovimiento() == 0) {
            int doctoVeId = obtenerSiguienteIdGenerator(conexion, "ID_DOCTOS");
            logger.info("idAutoIncremental: " + doctoVeId);
            
            FolioInfo folioInfo = obtenerFolioSiguiente(conexion, pedido.getVendedorId());

            //serieFolio = construirSerieFolio(folioInfo.getSerie(), folioInfo.getConsecutivo());
            int condicionPagoId = obtenerCondicionPago(conexion, pedido.getClienteId());
            int direccionCliente = resolverDireccionCliente(pedido);
            int viaEmbarqueId = obtenerViaEmbarque(conexion, direccionCliente);

            insertarCabeceraPedido(conexion, configuracionMobil, doctoVeId, serieFolio, pedido, condicionPagoId, viaEmbarqueId);
            actualizarConsecutivoFolio(conexion, folioInfo.getSerie(), folioInfo.getConsecutivo());

            if (pedido.getEsPOP()) {
                procesarLogicaPOP(conexion, doctoVeId, serieFolio, pedido);
            }

            if (Constants.COTIZACION.equals(configuracionMobil.getComportamientoCaptura())) {
                procesarLogicaCotizacion(conexion, doctoVeId, pedido.getCotizacionEmiteFactura());
            }

            insertarDetallesPedido(conexion, doctoVeId, pedido);
            createPedidoGuardado(conexion, pedido.getUuid(), doctoVeId, serieFolio);
       }
       return serieFolio;
   }
    
    public PedidoExistente existePedidoGuardado(Connection conexion, String uuid) throws SQLException {
        String sql = "SELECT UUID, DOCTO_VE_ID, FECHA, FOLIO FROM PEDIDOS_TRANSMITIDOS WHERE UUID = ?";
        
        PedidoExistente pedidoExistente = new PedidoExistente();

        // try-with-resources cierra automáticamente el PreparedStatement y el ResultSet
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, uuid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    pedidoExistente.setUuid(resultSet.getString("UUID"));
                    pedidoExistente.setNumeroMovimiento(resultSet.getInt("DOCTO_VE_ID"));
                    pedidoExistente.setFecha(resultSet.getDate("FECHA"));
                    pedidoExistente.setFolio(resultSet.getString("FOLIO"));
                }
            }
        }

        Resources.logger.debug("Resultado existePedidoGuardado: " + new Gson().toJson(pedidoExistente));
        return pedidoExistente;
    }
   /**
    * Generador de IDs genérico mediante Secuencias/Generadores de Firebird.
    */
    private int obtenerSiguienteIdGenerator(Connection conexion, String nombreGenerador) throws SQLException {
       String sql = "SELECT GEN_ID(" + nombreGenerador + ", 1) AS ID FROM RDB$DATABASE";
       
       try (PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("ID");
            }
            throw new SQLException("No se pudo obtener el ID del generador: " + nombreGenerador);
       }
   }
   
    public FolioInfo obtenerFolioSiguiente(Connection conexion, int vendedorId) throws SQLException {
        String sql = "SELECT SERIE, COALESCE(MAX(FOLIO), 0) + 1 AS FOLIO " +
                     "FROM VENDEDORES_SERIES_FOLIOS " +
                     "WHERE VENDEDOR_ID = ? " +
                     "GROUP BY SERIE";

        FolioInfo folioInfo = new FolioInfo();

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, vendedorId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    folioInfo.setSerie(resultSet.getString("SERIE"));
                    folioInfo.setConsecutivo(resultSet.getInt("FOLIO"));
                }
            }
        }

        return folioInfo;
    }
    
    public int obtenerCondicionPago(Connection conexion, int clienteId) throws SQLException {
        String sql = "SELECT COND_PAGO_ID FROM CLIENTES WHERE CLIENTE_ID = ?";

        int condicionPagoId = 0; 
        
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, clienteId);
                
            try (ResultSet rs = preparedStatement.executeQuery()) {
            
                if (rs.next()) {
                    condicionPagoId = rs.getInt("COND_PAGO_ID");
                }            
            }
        }
        
        return condicionPagoId;
    }
    /**
    * Resuelve la dirección a utilizar según las reglas de consignatarios.
    */
    private int resolverDireccionCliente(MaestroPedido pedido) {
       /*if (pedido.getDireccionConsignatarioEnvioId() != 0) {
           return pedido.getDireccionConsignatarioEnvioId();
       } else if (pedido.getDireccionConsignatarioId() != 0) {
           return pedido.getDireccionConsignatarioId();
       }       
       return pedido.getDireccionClienteId();*/             
       int direccionClienteId = 0;
       if ((pedido.getDireccionConsignatarioId() != 0) && (pedido.getDireccionConsignatarioEnvioId() != 0)) //CAMBIO FACTURA Y ENVIO                                        
           direccionClienteId = pedido.getDireccionConsignatarioEnvioId();
       else if ((pedido.getDireccionConsignatarioId() == 0) && (pedido.getDireccionConsignatarioEnvioId() != 0))  //CAMBIO ENVIO NADA MAS
           direccionClienteId = pedido.getDireccionConsignatarioEnvioId();
       else if ((pedido.getDireccionConsignatarioId() != 0) && (pedido.getDireccionConsignatarioEnvioId() == 0))  //CAMBIO FACTURA NADA MAS
           direccionClienteId = pedido.getDireccionConsignatarioId();
       else //NO CAMBIO NADA 
           direccionClienteId = pedido.getDireccionClienteId();
       
       return direccionClienteId;
   }
   
    public int obtenerViaEmbarque(Connection conexion, int direccionClienteId) throws SQLException {
        String sql = "SELECT VIA_EMBARQUE_ID FROM DIRS_CLIENTES WHERE DIR_CLI_ID = ?";

        int condicionPagoId = 0; 
        
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, direccionClienteId);
                
            try (ResultSet rs = preparedStatement.executeQuery()) {
            
                if (rs.next()) {
                    condicionPagoId = rs.getInt("VIA_EMBARQUE_ID");
                }            
            }
        }
        
        return condicionPagoId;
    }

    /**
    * Inserta el registro maestro en DOCTOS_VE con sentencias preparadas (evita inyección SQL).
    */
    private void insertarCabeceraPedido(Connection conexion, ConfiguracionMobil configuracionMobil, int doctoVeId, String serieFolio, MaestroPedido pedido, 
                                        int condPagoId, int viaEmbarqueId) throws SQLException {

       String tipoDocto = Constants.COTIZACION.equals(configuracionMobil.getComportamientoCaptura()) ? Constants.COTIZACION : Constants.PEDIDO;

       StringBuilder sql = new StringBuilder();
       sql.append("INSERT INTO DOCTOS_VE (DOCTO_VE_ID, TIPO_DOCTO, SUBTIPO_DOCTO, FOLIO, FECHA, HORA, CLAVE_CLIENTE, CLIENTE_ID, ")
          .append("DIR_CLI_ID, DIR_CONSIG_ID, ALMACEN_ID, MONEDA_ID, TIPO_CAMBIO, TIPO_DSCTO, DSCTO_PCTJE, DSCTO_IMPORTE, ")
          .append("ESTATUS, APLICADO, FECHA_VIGENCIA_ENTREGA, DESCRIPCION, IMPORTE_NETO, FLETES, OTROS_CARGOS, TOTAL_IMPUESTOS, ")
          .append("TOTAL_RETENCIONES, TOTAL_ANTICIPOS, PESO_EMBARQUE, FORMA_EMITIDA, CONTABILIZADO, ACREDITAR_CXC, SISTEMA_ORIGEN, ")
          .append("COND_PAGO_ID, PCTJE_DSCTO_PPAG, VENDEDOR_ID, PCTJE_COMIS, VIA_EMBARQUE_ID, IMPORTE_COBRO, USUARIO_CREADOR, ")
          .append("ES_CFD, ENVIADO, CFD_ENVIO_ESPECIAL, CFDI_CERTIFICADO, FECHA_HORA_CREACION, CARGAR_SUN, SUCURSAL_ID");

       sql.append(") VALUES (?, ?, 'N', ?, ?, ?, ?, ?, ?, ?, ?, 1, 1.00, 'P', 0.00, 0.00, 'P', 'S', ?, ?, ?, 0.00, 0.00, ?, 0.00, 0.00, 0.00, 'N', 'N', 'N', 'VE', ?, 0.00, ?, 0.00, ?, 0.00, ?, 'N', 'N', 'N', 'N', CURRENT_TIMESTAMP, 'S', ?)");

       try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {
           int idx = 1;
           ps.setInt(idx++, doctoVeId);
           ps.setString(idx++, tipoDocto);
           ps.setString(idx++, serieFolio);
           ps.setString(idx++, pedido.getFechaPedido());
           ps.setString(idx++, pedido.getHoraPedido());
           ps.setString(idx++, pedido.getClaveCliente());
           ps.setInt(idx++, pedido.getClienteId());
           ps.setInt(idx++, (pedido.getDireccionConsignatarioId() != 0) ? pedido.getDireccionConsignatarioId() : pedido.getDireccionClienteId());
           ps.setInt(idx++, (pedido.getDireccionConsignatarioEnvioId() != 0) ? pedido.getDireccionConsignatarioEnvioId() : pedido.getDireccionClienteId());
           ps.setInt(idx++, pedido.getAlmacenId());
           ps.setString(idx++, pedido.getFechaPedido());
           ps.setString(idx++, pedido.getObservaciones());
           ps.setDouble(idx++, pedido.getImporteNeto());
           ps.setDouble(idx++, pedido.getTotalImpuestos());
           ps.setInt(idx++, condPagoId);
           ps.setInt(idx++, pedido.getVendedorId());
           ps.setInt(idx++, viaEmbarqueId);
           ps.setString(idx++, Constants.SYSDBA);

           int sucursalId = configuracionMobil.getSucursalId();
           if (configuracionMobil.getOperaSucursalAlmacen() == 1) {
               sucursalId = resolverSucursalId(conexion, pedido.getAlmacenId());
               ps.setInt(idx++, sucursalId);
           }

           ps.executeUpdate();
       }
   }  
    
    public int resolverSucursalId(Connection conexion, int almacenId) throws SQLException {
        String sql = "SELECT SC.SUCURSAL_ID FROM SUCURSALES_CATALOGOS SC " +
                                "INNER JOIN SUCURSALES_CATALOGOS_DET D ON SC.SUCURSAL_CATALOGO_ID = D.SUCURSAL_CATALOGO_ID " +
                                "INNER JOIN CONFIGURACION_ALMACENES CA ON CA.ALMACEN_ID = D.ELEMENTO_ID " +
                                "WHERE SC.NOM_TABLA = 'ALMACENES' AND CA.ALMACEN_ID = ?";

        int sucursalId = 0; 
        
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, almacenId);
                
            try (ResultSet rs = preparedStatement.executeQuery()) {
            
                if (rs.next()) {
                    sucursalId = rs.getInt("SUCURSAL_ID");
                }            
            }
        }
        
        return sucursalId;
    }
    
    public void actualizarConsecutivoFolio(Connection conexion, String serie, int consecutivoFolio) throws SQLException {
        
        String sql = "UPDATE VENDEDORES_SERIES_FOLIOS SET FOLIO = ? WHERE SERIE = ?";

        // try-with-resources asegura el cierre del PreparedStatement sin cerrar la conexion activa
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, consecutivoFolio);
            preparedStatement.setString(2, serie);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas == 0) {
                logger.warn("No se encontró ningún registro para actualizar en VENDEDORES_SERIES_FOLIOS con la serie: {}", serie);
            } else {
                logger.debug("Consecutivo de folio actualizado correctamente. Serie: {}, Folio: {}", serie, consecutivoFolio);
            }
        }
    }
    
    
    public TrimestreAnioInfo obtenerTrimestreAnterior(Connection conexion) throws SQLException {
       LocalDate fechaActual = LocalDate.now();
       String nombreMes = fechaActual.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase();

       String sql = "SELECT NUMERO FROM TRIMESTRES WHERE NOMBRE LIKE ?";
       int numeroTrimestre = 0;
       int anio = fechaActual.getYear();

       try (PreparedStatement ps = conexion.prepareStatement(sql)) {
           ps.setString(1, "%" + nombreMes + "%");

           try (ResultSet rs = ps.executeQuery()) {
               if (rs.next()) {
                   numeroTrimestre = rs.getInt("NUMERO");
                   if (numeroTrimestre == 1) {
                       numeroTrimestre = 4;
                       anio = fechaActual.getYear() - 1;
                   } else {
                       numeroTrimestre -= 1;
                       anio = fechaActual.getYear();
                   }
               }
           }
       }
  
       return new TrimestreAnioInfo(numeroTrimestre, anio);
   }
    
    public void procesarLogicaPOP(Connection conexion, int doctoVeId, String serieFolio, MaestroPedido pedido) throws SQLException {
        logger.info("SE ACTUALIZA EL STATUS POP DEL CLIENTE A BLOQUEADO");

        TrimestreAnioInfo trimestreInfo = obtenerTrimestreAnterior(conexion);
        int numeroTrimestre = trimestreInfo.getTrimestre();
        int anio = trimestreInfo.getAnio();

        // Cálculo del importe POP
        Double importePOP = pedido.getListaDetallePedido().stream()
                .filter(p -> "S".equals(p.getEsPOP()) && p.getPorcentajeDescuentoPOP() > 0)
                .mapToDouble(p -> p.getUnidades() * p.getPrecioUnitarioConImpuestos() * (p.getPorcentajeDescuentoPOP() / 100))
                .sum();

        logger.info("Insertaremos en Pedidos POP " + importePOP);

        // 1. Insertar en PEDIDOS_POP
        String sqlInsert = "INSERT INTO PEDIDOS_POP (DOCTO_VE_ID, CLIENTE_ID, FOLIO, IMPORTE_POP, ESTADO, ANIO, TRIMESTRE) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement psInsert = conexion.prepareStatement(sqlInsert)) {
            psInsert.setInt(1, doctoVeId);
            psInsert.setInt(2, pedido.getClienteId());
            psInsert.setString(3, serieFolio);
            psInsert.setDouble(4, importePOP);
            psInsert.setString(5, "PEDIDO");
            psInsert.setInt(6, anio);
            psInsert.setInt(7, numeroTrimestre);
            psInsert.executeUpdate();
        }

        Utilerias utilerias = new Utilerias();
        // 2. Actualizar en POP_TRIMESTRAL
        String sqlUpdate = "UPDATE POP_TRIMESTRAL SET ESTATUS_POP = ?, FECHA_BLOQUEO_POP = ? "
                + "WHERE CLIENTE_ID = ? AND ANIO = ? AND TRIMESTRE = ?";

        try (PreparedStatement psUpdate = conexion.prepareStatement(sqlUpdate)) {
            psUpdate.setString(1, "BLOQUEADO");
            psUpdate.setDate(2, utilerias.convertStringToDate(pedido.getFechaPedido()));
            psUpdate.setInt(3, pedido.getClienteId());
            psUpdate.setInt(4, anio);
            psUpdate.setInt(5, numeroTrimestre);
            psUpdate.executeUpdate();
        }
    }
    
    public void procesarLogicaCotizacion(Connection conexion, int doctoVeId, String cotizacionEmiteFactura) throws SQLException {

        // 1. Inserción en campos libres de cotizaciones
        String sqlLibres = "INSERT INTO LIBRES_COT_VE (DOCTO_VE_ID, EMITE_FACTURA) VALUES (?, ?)";

        try (PreparedStatement psLibres = conexion.prepareStatement(sqlLibres)) {
            psLibres.setInt(1, doctoVeId);
            psLibres.setString(2, cotizacionEmiteFactura);
            psLibres.executeUpdate();
        }

        // 2. Inserción en el control de cotizaciones procesadas
        String sqlProcesadas = "INSERT INTO VIT_COTIZ_PROCESADAS (DOCTO_VE_ID, ESTATUS) VALUES (?, ?)";

        try (PreparedStatement psProcesadas = conexion.prepareStatement(sqlProcesadas)) {
            psProcesadas.setInt(1, doctoVeId);
            psProcesadas.setString(2, "P");
            psProcesadas.executeUpdate();
        }

        logger.debug("Cotización procesada correctamente para DOCTO_VE_ID: {}", doctoVeId);
    }
    
    /**
    * Inserta todos los artículos en DOCTOS_VE_DET utilizando PreparedStatement parametrizado.
    */
    private void insertarDetallesPedido(Connection conexion, int doctoVeId, MaestroPedido pedido) throws SQLException {
       String sql = "INSERT INTO DOCTOS_VE_DET (DOCTO_VE_DET_ID, DOCTO_VE_ID, CLAVE_ARTICULO, ARTICULO_ID, UNIDADES, " +
                    "UNIDADES_COMPROM, UNIDADES_SURT_DEV, UNIDADES_A_SURTIR, PRECIO_UNITARIO, PCTJE_DSCTO, DSCTO_ART, " +
                    "PCTJE_DSCTO_CLI, DSCTO_EXTRA, PCTJE_DSCTO_VOL, PCTJE_DSCTO_PROMO, PRECIO_TOTAL_NETO, PCTJE_COMIS, ROL, POSICION) " +
                    "VALUES (?, ?, ?, ?, ?, 0.00, 0.00, 0.00, ?, ?, ?, ?, 0.00, ?, ?, ?, 0.00, ?, ?)";

       try (PreparedStatement ps = conexion.prepareStatement(sql)) {
           int consecutivo = 0;
           for (DetallePedido detalle : pedido.getListaDetallePedido()) {
               consecutivo++;
               
               int detalleId = obtenerSiguienteIdGenerator(conexion, "ID_DOCTOS");
               
               Double precioUnitarioSinImpuesto = detalle.getPrecio_unitario_sin_impuestos();
               Double porcentajeDescuentoTotal = porcentajeDescuentoTotalXArticulo(detalle, pedido.getEsPOP());
               Double dctoImporteArticulo = (precioUnitarioSinImpuesto * detalle.getUnidades()) * (porcentajeDescuentoTotal / 100);
               Double totalNeto = (precioUnitarioSinImpuesto - detalle.getPrecio_unitario_con_descuento_sin_impuestos()) * detalle.getUnidades();

               ps.setInt(1, detalleId);
               ps.setInt(2, doctoVeId);
               ps.setString(3, detalle.getClave_articulo());
               ps.setInt(4, detalle.getArticulo_id());
               ps.setDouble(5, detalle.getUnidades());
               ps.setDouble(6, precioUnitarioSinImpuesto);//PRECIO SIN IVA  
               ps.setDouble(7, porcentajeDescuentoTotal);
               ps.setDouble(8, dctoImporteArticulo);//VALOR EN MONEDA DEL DESCUENTO   100-10%=10.00
               ps.setDouble(9, !pedido.getEsPOP() ? detalle.getPorcentaje_descuento_articulo_cliente() : 0.00);
               ps.setDouble(10, ("VOLUMEN".equals(detalle.getTipo_politica()) && !pedido.getEsPOP()) ? detalle.getPorcentaje_descuento_promocion_volumen() : 0.00);
               ps.setDouble(11, ("PROMOCION".equals(detalle.getTipo_politica()) && !pedido.getEsPOP()) ? detalle.getPorcentaje_descuento_promocion_volumen() : 0.00);
               ps.setDouble(12, totalNeto);
               ps.setString(13, "S".equals(detalle.getEs_juego()) ? "J" : "N");
               ps.setInt(14, consecutivo);

               ps.executeUpdate();

               if ("S".equals(detalle.getEs_juego())) {
                   createDetalleJuego(conexion, detalleId, detalle.getArticulo_id());
               }
           }
       }
    }
    
    public void createDetalleJuego(Connection conexion, int doctoVeId, int articuloIdJuego) throws SQLException {
        String sqlSelect = "SELECT DOCTO_VE_DET_ID FROM DOCTOS_VE_DET WHERE DOCTO_VE_ID = ? AND ARTICULO_ID = ?";
        int doctoVeDetId = 0;

        // 1. Consultar el DOCTO_VE_DET_ID generado previamente
        try (PreparedStatement psSelect = conexion.prepareStatement(sqlSelect)) {
            psSelect.setInt(1, doctoVeId);
            psSelect.setInt(2, articuloIdJuego);

            try (ResultSet rs = psSelect.executeQuery()) {
                if (rs.next()) {
                    doctoVeDetId = rs.getInt("DOCTO_VE_DET_ID");
                }
            }
        }

        // 2. Ejecutar el Stored Procedure de Firebird solo si se encontró el detalle
        if (doctoVeDetId > 0) {
            String sqlProcedure = "EXECUTE PROCEDURE ALTA_COMPONENTES_VE(?)";

            try (PreparedStatement psProc = conexion.prepareStatement(sqlProcedure)) {
                psProc.setInt(1, doctoVeDetId);
                psProc.executeUpdate(); // Alternativamente psProc.executeUpdate()
            }

            Resources.logger.debug("Componentes de juego generados para DOCTO_VE_DET_ID: {}", doctoVeDetId);
        } else {
            Resources.logger.warn("No se encontró DOCTO_VE_DET_ID para el artículo juego ID: {} en el documento ID: {}", articuloIdJuego, doctoVeId);
        }
    }

    public void createPedidoGuardado(Connection conexion, String uuid, int numeroMovimiento, String folio) throws SQLException {
        Date fecha = new Date();           
        Utilerias utilerias = new Utilerias();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");  
            
        String sql = "INSERT INTO PEDIDOS_TRANSMITIDOS (UUID, DOCTO_VE_ID, FECHA, FOLIO) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, uuid);
            preparedStatement.setInt(2, numeroMovimiento);
            preparedStatement.setDate(3, utilerias.convertStringToDate(sdf.format(fecha))); 
            preparedStatement.setString(4, folio);

            preparedStatement.executeUpdate();
            Resources.logger.info("Pedido registrado correctamente en PEDIDOS_TRANSMITIDOS. UUID: {}, Folio: {}", uuid, folio);
        }
    }    
    
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
    
    public ResponseRequest createCobrosXDepositarIndividual(String jsonString) {
        logger.info("Entrando a crear la cobranza:");
        logger.info("Estos abonos se reciben crear: " + jsonString);

        ResponseRequest responseRequest = new ResponseRequest();
        List<CobroXDepositarEnviado> listaCobroXDepositarEnviados = new ArrayList<>();

        // Se pide la conexión al pool de HikariCP dentro del try-with-resources
        try (Connection conexion = FirebirdConnector.getConnection()) {

            if (conexion == null) {
                return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "No se pudo obtener conexión con Firebird");
            }

            ConfiguracionMobil configuracionMobil = configuracionMicrosip();

            Type type = new TypeToken<CobroXDepositar>(){}.getType();
            CobroXDepositar cobroXDepositar = new Gson().fromJson(jsonString, type);

            // Procesamiento individual por cada abono
            for (AbonoMaestroEntity abonoMaestroEntity : cobroXDepositar.getListaAbonosParaMicrosip()) {

                conexion.setAutoCommit(false); // Inicia transacción para el elemento actual

                // Pasamos la conexión activa al submétodo
                ResponseRequest responseRequestItem = createCobroXDepositar(conexion, configuracionMobil, abonoMaestroEntity);

                CobroXDepositarEnviado cobroXDepositarEnviado = new CobroXDepositarEnviado();
                cobroXDepositarEnviado.setId(abonoMaestroEntity.getId());

                if (responseRequestItem.getStatus() == ResponseRequest.DataStatus.OK) {
                    cobroXDepositarEnviado.setStatus(ResponseRequest.DataStatus.OK);
                    cobroXDepositarEnviado.setMensaje("");

                    conexion.commit(); // Confirma solo este abono
                } else {
                    String data = (String) responseRequestItem.getData();

                    cobroXDepositarEnviado.setStatus(ResponseRequest.DataStatus.ERROR);
                    cobroXDepositarEnviado.setMensaje(data);

                    conexion.rollback(); // Revierte solo este abono
                }

                listaCobroXDepositarEnviados.add(cobroXDepositarEnviado);
            }

            // Si se controla folio/serie, se actualiza en su propia transacción
            if (configuracionMobil.getControlaSerieFolioCXC() == 1) {                
                actualizarSerieFolioCXC(conexion, cobroXDepositar.getSerieFolioCXC());                    
            }

            return responseRequest.response(ResponseRequest.DataStatus.OK, listaCobroXDepositarEnviados, "Cobros x depositar enviados al servidor");

        } catch (JsonSyntaxException e) {
            logger.error("Error al deserializar JSON en createCobrosXDepositarIndividual: " + e.getMessage(), e);
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error en el formato del JSON recibido");
        } catch (SQLException e) {
            logger.error("Excepción SQL en createCobrosXDepositarIndividual: " + e.getMessage(), e);
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, null, "Error al procesar cobros por depositar: " + e.getMessage());
        }
    }
    
    private ResponseRequest createCobroXDepositar(Connection conexion, ConfiguracionMobil configuracionMobil, AbonoMaestroEntity abonoMaestroEntity) {
        String errorMessage = "CTE: " + abonoMaestroEntity.getClaveCliente();
        
        Utilerias utilerias = new Utilerias();
        ResponseRequest responseRequest = new ResponseRequest();
        ComplementoXml complementoXml = new ComplementoXml();

        int idAutoIncremental = 0;
        int folioUltimo = 0;
        int lugarExpedicionId = 0;

        try {
            // 1. Obtener ID autoincremental para DOCTOS_CC
            String sqlGenId = "SELECT GEN_ID(ID_DOCTOS, 1) AS ID FROM RDB$DATABASE";
            try (PreparedStatement ps = conexion.prepareStatement(sqlGenId);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idAutoIncremental = rs.getInt("ID");
                }
            }

            // 2. Obtener folio temporal
            String sqlGenFolio = "SELECT GEN_ID(ID_FOLIO_TEMP, 1) AS ID FROM RDB$DATABASE";
            try (PreparedStatement ps = conexion.prepareStatement(sqlGenFolio);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    folioUltimo = rs.getInt("ID");
                }
            }

            // 3. Obtener LUGAR_EXPEDICION_ID
            String sqlSucursal = "SELECT LUGAR_EXPEDICION_ID FROM SUCURSALES WHERE SUCURSAL_ID = ?";
            try (PreparedStatement ps = conexion.prepareStatement(sqlSucursal)) {
                ps.setInt(1, configuracionMobil.getSucursalId());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        lugarExpedicionId = rs.getInt("LUGAR_EXPEDICION_ID");
                    }
                }
            }

            // 4. Inserción parametrizada en DOCTOS_CC
            String queryDoctosCC = 
                "INSERT INTO DOCTOS_CC(" +
                "DOCTO_CC_ID, CONCEPTO_CC_ID, FOLIO, NATURALEZA_CONCEPTO, FECHA, HORA, FECHA_HORA_PAGO, CLAVE_CLIENTE, " +
                "IMPORTE_COBRO, CLIENTE_ID, TIPO_CAMBIO, CANCELADO, APLICADO, DESCRIPCION, COBRADOR_ID, FORMA_EMITIDA, " +
                "CONTABILIZADO, CONTABILIZADO_GYP, COND_PAGO_ID, SISTEMA_ORIGEN, ESTATUS, ESTATUS_ANT, ES_CFD, TIENE_ANTICIPO, " +
                "MODALIDAD_FACTURACION, ENVIADO, FECHA_HORA_ENVIO, CFDI_CERTIFICADO, INTEG_BA, CONTABILIZADO_BA, " +
                "LUGAR_EXPEDICION_ID, FECHA_APLICACION, SUCURSAL_ID) " +
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
            queryDoctosCC = queryDoctosCC + lugarExpedicionId + ", ";
            queryDoctosCC = queryDoctosCC + "'" + abonoMaestroEntity.getFechaAbono() + "', ";

            if (configuracionMobil.getOperaSucursalAlmacen() == 1) {
                logger.info("MOBIL sucursalId: " + abonoMaestroEntity.getSucursalId());
                queryDoctosCC = queryDoctosCC + abonoMaestroEntity.getSucursalId() + ") ";
            } else {
                logger.info("CONFIGURACION MOBIL sucursalId: " + configuracionMobil.getSucursalId());
                queryDoctosCC = queryDoctosCC + configuracionMobil.getSucursalId() + ") ";
            }
                             
            try (PreparedStatement ps = conexion.prepareStatement(queryDoctosCC)) {               
                int filasAfectadas = ps.executeUpdate();
            }

            logger.info("Save table [DOCTOS_CC] id: " + idAutoIncremental + " folio: " + "Z" + StringUtils.leftPad(String.valueOf(folioUltimo), 8, "0"));                

            complementoXml.setDoctoCCId(idAutoIncremental);
            complementoXml.setClienteId(abonoMaestroEntity.getClienteId());
            complementoXml.setFechaHoraEnvioTimestamp(utilerias.getNowDateHourTimestamp());
            complementoXml.setFechaDate(convertTimestampToDate(utilerias.getNowDateHourTimestamp()));

            // 5. Inserción en FORMAS_COBRO_DOCTOS
            String sqlInsertFormasCobro = 
                "INSERT INTO FORMAS_COBRO_DOCTOS " +
                "(FORMA_COBRO_DOC_ID, NOM_TABLA_DOCTOS, DOCTO_ID, FORMA_COBRO_ID, NUM_CTA_PAGO, CLAVE_SIS_FORMA_COB, REFERENCIA, IMPORTE) " +
                "VALUES(-1, 'DOCTOS_CC', ?, ?, '', 'CC', ?, 0.00)";

            try (PreparedStatement ps = conexion.prepareStatement(sqlInsertFormasCobro)) {
                ps.setInt(1, idAutoIncremental);
                ps.setInt(2, abonoMaestroEntity.getFormaCobroCCId());
                ps.setString(3, "Abono: $" + abonoMaestroEntity.getAbonoTotal());
                ps.executeUpdate();
            }

            complementoXml.setFormaCobroId(abonoMaestroEntity.getFormaCobroCCId());
            logger.info("Save tabla [FORMAS_COBRO_DOCTOS]");

            // 6. Inserción en IMPORTES_DOCTOS_CC y validación de complementos
            List<ComplementoXmlDetalle> listaComplementoXmlDetalle = new ArrayList<>();

            String sqlInsertImportes = 
                "INSERT INTO IMPORTES_DOCTOS_CC(IMPTE_DOCTO_CC_ID, DOCTO_CC_ID, FECHA, CANCELADO, APLICADO, ESTATUS, " +
                "TIPO_IMPTE, DOCTO_CC_ACR_ID, IMPORTE, IMPUESTO, IVA_RETENIDO, ISR_RETENIDO, DSCTO_PPAG, PCTJE_COMIS_COB) " +
                "VALUES(-1, ?, ?, 'N', 'N', 'P', 'R', ?, ?, 0.00, 0.00, 0.00, 0.00, 0.00)";

            try (PreparedStatement psImportes = conexion.prepareStatement(sqlInsertImportes)) {
                for (AbonoDetalleEntity abonoDetalleEntity : abonoMaestroEntity.getAbonoDetalleEntity()) {
                    psImportes.setInt(1, idAutoIncremental);
                    psImportes.setDate(2, utilerias.convertStringToDate(abonoMaestroEntity.getFechaAbono()));
                    psImportes.setInt(3, abonoDetalleEntity.getDoctoCCId());
                    psImportes.setDouble(4, abonoDetalleEntity.getImporteAbono());
                    psImportes.executeUpdate();

                    logger.info("Save tabla [IMPORTES_DOCTOS_CC]");

                    String requiereComplementoPagos = cargoRequiereComplementoPagos(conexion, abonoDetalleEntity.getDoctoCCId());
                    logger.info("[cargoRequiereComplementoPagos] {} {}", abonoDetalleEntity.getDoctoCCId(), requiereComplementoPagos);

                    if ("S".equals(requiereComplementoPagos != null ? requiereComplementoPagos.trim() : "")) {
                        ComplementoXmlDetalle complementoXmlDetalle = new ComplementoXmlDetalle();
                        complementoXmlDetalle.setDoctoCCPadreId(abonoDetalleEntity.getDoctoCCId());
                        complementoXmlDetalle.setImporteAbono(abonoDetalleEntity.getImporteAbono());
                        listaComplementoXmlDetalle.add(complementoXmlDetalle);
                    }
                }
            }

            // 7. Actualización de DOCTOS_CC
            double importeTotal = listaComplementoXmlDetalle.stream()
                    .mapToDouble(ComplementoXmlDetalle::getImporteAbono)
                    .sum();

            if (importeTotal > 0) {
                String sqlUpdateDoctos = "UPDATE DOCTOS_CC SET APLICADO = 'S', MODALIDAD_FACTURACION = 'CFDI', USO_CFDI = 'CP01' WHERE DOCTO_CC_ID = ?";
                try (PreparedStatement ps = conexion.prepareStatement(sqlUpdateDoctos)) {
                    ps.setInt(1, idAutoIncremental);
                    ps.executeUpdate();
                }
            } else {
                String sqlUpdateDoctos = "UPDATE DOCTOS_CC SET APLICADO = 'S' WHERE DOCTO_CC_ID = ?";
                try (PreparedStatement ps = conexion.prepareStatement(sqlUpdateDoctos)) {
                    ps.setInt(1, idAutoIncremental);
                    ps.executeUpdate();
                }
            }

            logger.info("Update table [DOCTOS_CC]");
            return responseRequest.response(ResponseRequest.DataStatus.OK, abonoMaestroEntity, "Cobro por depositar grabado correctamente");

        } catch (SQLException exception) {
            logger.error("{} EXCEPCION SQL: {}", errorMessage, exception.getMessage(), exception);
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, errorMessage + ": " + exception.getMessage(), exception.getMessage());
        } catch (Exception exception) {
            logger.error("{} EXCEPCION GENERAL: {}", errorMessage, exception.getMessage(), exception);
            return responseRequest.response(ResponseRequest.DataStatus.ERROR, abonoMaestroEntity, exception.getMessage());
        }
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
            java.util.logging.Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private String cargoRequiereComplementoPagos(Connection conexion, int cargoId) throws SQLException {
        String requiereComplemento = "N";
        String sql = "SELECT REQUIERE_COMPLEMENTO FROM CARGO_REQUIERE_COMPL_PAGOS(?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cargoId);
            ps.setString(2, "S");

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    requiereComplemento = rs.getString("REQUIERE_COMPLEMENTO");
                }
            }
        } catch (SQLException exception) {
            Resources.logger.error("Error al verificar complemento de pagos para cargoId: {} - {}", cargoId, exception.getMessage());
            throw exception; // Re-lanza la excepción para que el llamador gestione el rollback si es necesario
        }

        return requiereComplemento;
    }
    
    public void actualizarSerieFolioCXC(Connection conexion, SerieFolioCXC serieFolioCXC) throws SQLException {
        if (serieFolioCXC == null) {
            logger.warn("Se intentó actualizar la serie/folio CXC pero el objeto serieFolioCXC es nulo.");
            return;
        }

        String sql = "UPDATE SERIES_FOLIOS_CXC SET FOLIO = ?, SERIE = ? WHERE COBRADOR_ID = ?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, serieFolioCXC.getFolio());
            preparedStatement.setString(2, serieFolioCXC.getSerie());
            preparedStatement.setInt(3, serieFolioCXC.getCobradorId());

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                logger.info("Serie y Folio CXC actualizados correctamente. CobradorID: {}, Serie: {}, Folio: {}", 
                        serieFolioCXC.getCobradorId(), serieFolioCXC.getSerie(), serieFolioCXC.getFolio());
            } else {
                logger.warn("No se encontró ningún registro en SERIES_FOLIOS_CXC para el CobradorID: {}", 
                        serieFolioCXC.getCobradorId());
            }
        } catch (SQLException exception) {
            logger.error("Error al actualizar SERIES_FOLIOS_CXC para CobradorID: {} - {}", 
                    serieFolioCXC.getCobradorId(), exception.getMessage(), exception);
            throw exception;
        }
    }
       
    public String cobrosMicrosip(int cobradorId) throws SQLException {
        // La conexión se abre aquí y se cerrará automáticamente al finalizar el try
        try (Connection connection = FirebirdConnector.getConnection()) {

            boolean estaOperandoAppChoferes = estaOperandoAppChoferes(connection);
            String sqlQuery;

            if (estaOperandoAppChoferes) {
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
                        
                    "AND NOT EXISTS (" +
                    "   SELECT 1 " +
                    "   FROM IMPORTES_DOCTOS_CC IDC " +
                    "   JOIN DOCTOS_CC DCCC ON DCCC.DOCTO_CC_ID = IDC.DOCTO_CC_ACR_ID " +
                    "   JOIN AH_PEDIDOS_ENRUTADOS PE ON PE.FOLIO_FACTURA = DCCC.FOLIO " +
                    "   WHERE IDC.DOCTO_CC_ID = DCC.DOCTO_CC_ID " +
                    "   AND PE.ESTATUS <> 'CERRADO' " +
                    ") " +
                    "AND NOT EXISTS ( " +
                    "   SELECT 1 " +
                    "   FROM DEPOSITOS_CC_DET DCCD " +
                    "   WHERE DCCD.DOCTO_CC_ID = DCC.DOCTO_CC_ID " +
                    ") " +
                    "ORDER BY DCC.DOCTO_CC_ID";
            } else {
                sqlQuery = 
                    "SELECT DCC.DOCTO_CC_ID, DCC.FECHA, DCC.HORA, C.NOMBRE, FCD.FORMA_COBRO_ID, SUM(IDCC.IMPORTE) AS ABONO_TOTAL " +
                    "FROM DOCTOS_CC DCC " +
                    "LEFT JOIN FORMAS_COBRO_DOCTOS FCD ON FCD.DOCTO_ID = DCC.DOCTO_CC_ID " +
                    "LEFT JOIN IMPORTES_DOCTOS_CC IDCC ON IDCC.DOCTO_CC_ID = DCC.DOCTO_CC_ID " +
                    "LEFT JOIN DEPOSITOS_CC_DET DCCD ON DCCD.DOCTO_CC_ID = DCC.DOCTO_CC_ID " +
                    "LEFT JOIN CLIENTES C ON C.CLIENTE_ID = DCC.CLIENTE_ID " +
                    "WHERE SUBSTRING(DCC.FOLIO FROM 1 FOR 1) = 'Z' " +
                    "AND DCCD.DEPOSITO_CC_ID IS NULL " +
                    "AND DCC.ESTATUS = 'P' " +
                    "AND DCC.COBRADOR_ID = ? " +
                    "GROUP BY 1, 2, 3, 4, 5 " +
                    "ORDER BY 1";
            }
            
            String queryOriginal = "SELECT DCC.DOCTO_CC_ID, DCC.FECHA, DCC.HORA, C.NOMBRE, FCD.FORMA_COBRO_ID, SUM(IDCC.IMPORTE) AS ABONO_TOTAL " +
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

            List<CobroMicrosip> listaCobroMicrosip = new ArrayList<>();

            // Se ejecuta la consulta correcta usando sqlQuery
            try (PreparedStatement preparedStatement = connection.prepareStatement(queryOriginal)) {
                preparedStatement.setInt(1, cobradorId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        CobroMicrosip cobroMicrosip = new CobroMicrosip();
                        cobroMicrosip.setDoctoCCId(resultSet.getInt("DOCTO_CC_ID"));

                        java.sql.Date fecha = resultSet.getDate("FECHA");
                        cobroMicrosip.setFechaAbono(fecha != null ? fecha.toString() : "");

                        java.sql.Time hora = resultSet.getTime("HORA");
                        cobroMicrosip.setHoraAbono(hora != null ? hora.toString() : "");

                        cobroMicrosip.setNombreCliente(resultSet.getString("NOMBRE"));
                        cobroMicrosip.setFormaCobroCCId(resultSet.getInt("FORMA_COBRO_ID"));
                        cobroMicrosip.setAbonoTotal(resultSet.getDouble("ABONO_TOTAL"));

                        listaCobroMicrosip.add(cobroMicrosip);
                    }
                }
            }

            String jsonResult = new Gson().toJson(listaCobroMicrosip);
            logger.info("Cobros Microsip obtenida correctamente para CobradorID {}: {}", cobradorId, jsonResult);
            return jsonResult;

        } catch (SQLException e) {
            logger.error("Error SQL en cobrosMicrosip para CobradorID {}: {}", cobradorId, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Error general en cobrosMicrosip para CobradorID {}: {}", cobradorId, e.getMessage(), e);
            return null;
        }
    }    

    private boolean estaOperandoAppChoferes(Connection conexion) throws SQLException {
        String sql = "SELECT FIRST 1 1 FROM AH_PEDIDOS_ENRUTADOS";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            return rs.next();
        } catch (SQLException e) {
            Resources.logger.error("Error al consultar la operación de App Choferes: {}", e.getMessage(), e);
            throw e;
        }
    }
}