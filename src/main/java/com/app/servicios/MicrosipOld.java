package com.app.servicios;

import com.app.dao.Controlador;
import com.app.models.PedidoGrabado;
import com.app.models.depositos.DepositoGrabado;
import com.app.utilerias.ResponseRequest;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author burtebony
 */
@Path("/MicrosipOld")
public class MicrosipOld {
    private Controlador controlador = new Controlador();
    
    @GET
    @Path("/executeScriptsRefactor")
    @Produces("application/json")
    public Response executeScripts() {
        try {            
            ClassLoader classLoader = getClass().getClassLoader();
            System.out.println("Leer xslt: " + classLoader.toString());
            InputStream inputStream = classLoader.getResourceAsStream("serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .setDatabaseUrl("https://ahsw-814d4.firebaseio.com")
                    .build();

            FirebaseApp firebaseApp = null;
            List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
            if (firebaseApps != null && !firebaseApps.isEmpty()) {
                for (FirebaseApp app : firebaseApps) {
                    if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                        firebaseApp = app;
                    }
                }
            } else {
                firebaseApp = FirebaseApp.initializeApp(options);
            }
            Firestore db = FirestoreClient.getFirestore();

            ApiFuture<QuerySnapshot> future = db.collection("scripts_sql_microsip").get();
            List<QueryDocumentSnapshot> documents;
            try {
                documents = future.get().getDocuments();
                HashMap<String, String> scripts_sql = new HashMap<String, String>();
                for (DocumentSnapshot document : documents) {
                    //System.out.println(document.getId() + " => " + new Gson().toJson(document));
                    System.out.println(document.getId() + " => " + document.getData());
                    scripts_sql.put(
                            document.getId(),
                            controlador.executeScript(document.getData().get("script").toString()));
                }
                return Response.ok(new Gson().toJson(scripts_sql)).build();
            } catch (InterruptedException ex) {
                Logger.getLogger(Microsip.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(Microsip.class.getName()).log(Level.SEVERE, null, ex);
            }

            return Response.ok("lista").build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/agregaScripts")
    @Produces("application/json")
    public Response agregaScripts() {
        try {            
            ClassLoader classLoader = getClass().getClassLoader();
            System.out.println("Leer xslt: " + classLoader.toString());
            InputStream inputStream = classLoader.getResourceAsStream("serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .setDatabaseUrl("https://ahsw-814d4.firebaseio.com")
                    .build();

            FirebaseApp firebaseApp = null;
            List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
            if (firebaseApps != null && !firebaseApps.isEmpty()) {
                for (FirebaseApp app : firebaseApps) {
                    if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                        firebaseApp = app;
                    }
                }
            } else {
                firebaseApp = FirebaseApp.initializeApp(options);
            }
            Firestore db = FirestoreClient.getFirestore();
            

            ApiFuture<QuerySnapshot> future = db.collection("scripts_sql_microsip_refactor").get();
            List<QueryDocumentSnapshot> documents;
            try {
                documents = future.get().getDocuments();
                HashMap<String, String> scripts_sql = new HashMap<String, String>();
                for (DocumentSnapshot document : documents) {
                    //System.out.println(document.getId() + " => " + document.getData());
                    String key = document.getId();
                    if ((key.equals("A")) || (key.equals("B"))) {
                        String sql = document.getData().get("script").toString();
                        String[] sentencias = sql.split(";\\s*");
                        int contador = 1;
                        for (String sentencia : sentencias) {
                            sentencia = sentencia.trim();
                            //System.out.println("SENTENCIA => " + sentencia);
                            if (!sentencia.isEmpty()) {
                                scripts_sql.put(
                                    document.getId() + contador ++,
                                    controlador.executeScript(sentencia)
                                );
                            }
                        }
                    } else {
                        scripts_sql.put(
                                document.getId(),
                                controlador.executeScript(document.getData().get("script").toString())
                        );
                    }
                }
                Map<String, String> sortedMap = scripts_sql.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(
                    LinkedHashMap::new,
                    (m, e) -> m.put(e.getKey(), e.getValue()),
                    LinkedHashMap::putAll
                );
                
                return Response.ok(new Gson().toJson(sortedMap)).build();
            } catch (InterruptedException ex) {
                Logger.getLogger(Microsip.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Exception Error" + ex.getMessage());
            } catch (ExecutionException ex) {
                Logger.getLogger(Microsip.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Exception Error" + ex.getMessage());
            }

            return Response.ok("lista").build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/executeScriptsUpdate")
    @Produces("application/json")
    public Response executeScriptsUpdate() {
        try {            
            ClassLoader classLoader = getClass().getClassLoader();
            System.out.println("Leer xslt: " + classLoader.toString());
            InputStream inputStream = classLoader.getResourceAsStream("serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .setDatabaseUrl("https://ahsw-814d4.firebaseio.com")
                    .build();

            FirebaseApp firebaseApp = null;
            List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
            if (firebaseApps != null && !firebaseApps.isEmpty()) {
                for (FirebaseApp app : firebaseApps) {
                    if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                        firebaseApp = app;
                    }
                }
            } else {
                firebaseApp = FirebaseApp.initializeApp(options);
            }
            Firestore db = FirestoreClient.getFirestore();

            ApiFuture<QuerySnapshot> future = db.collection("scripts_sql_update").get();
            List<QueryDocumentSnapshot> documents;
            try {
                documents = future.get().getDocuments();
                HashMap<String, String> scripts_sql = new HashMap<String, String>();
                for (DocumentSnapshot document : documents) {
                    //System.out.println(document.getId() + " => " + new Gson().toJson(document));
                    System.out.println(document.getId() + " => " + document.getData());
                    scripts_sql.put(
                            document.getId(),
                            controlador.executeScript(document.getData().get("script").toString()));
                }
                return Response.ok(new Gson().toJson(scripts_sql)).build();
            } catch (InterruptedException ex) {
                Logger.getLogger(Microsip.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(Microsip.class.getName()).log(Level.SEVERE, null, ex);
            }

            return Response.ok("lista").build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET  
    @Path("/seriesFoliosVendedores")
    @Produces("application/json")
    public Response seriesFoliosVendedores() throws SQLException {
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.seriesFoliosVendedores();        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }     
    
    @GET
    @Path("/configuracionMobil")
    @Produces("application/json")
    public Response configuracionMobil() {
        try {            
            return Response.ok(new Gson().toJson(controlador.configuracionMicrosip())).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);            
        }

        return null;
    }
    
    @GET
    @Path("/agentesVendedoresCobradores")
    @Produces("application/json")
    public Response agentesVendedoresCobradores() {
        try {            
            return Response.ok(controlador.vendedoresCobradores()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET  
    @Path("/vendedoresCobranza")
    @Produces("application/json")
    public Response vendedoresCobranza() throws SQLException {
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.vendedoresCobranza();        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    @GET
    @Path("/almacenes")
    @Produces("application/json")
    public Response almacenes() {
        try {            
            return Response.ok(controlador.almacenes()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/metodosDePago")
    @Produces("application/json")
    public Response metodosDePago() {
        try {            
            return Response.ok(controlador.metodosDePago()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/cuentasBancariasRefactor")
    @Produces("application/json")
    public Response cuentasBancariasRefactor() {
        try {            
            return Response.ok(controlador.cuentasBancariasRefactor()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/cobradoresSucursales")
    @Produces("application/json")
    public Response cobradoresSucursales() {
        try {            
            return Response.ok(controlador.cobradoresSucursales()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET  
    @Path("/motivosVisitas")
    @Produces("application/json")
    public Response motivosVisitas() throws SQLException {
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.motivosVisitas();        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }     
    
    /**************************************************************************/
    
    @GET  
    @Path("/monedas")
    @Produces("application/json")
    public Response monedas() throws SQLException {
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.monedas();        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    /**************************************************************************/
    
    @GET
    @Path("/articulosRefactor")
    @Produces("application/json")
    public Response articulosRefactor() {
        try {            
            return Response.ok(controlador.articulosRefactor()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/configuracionPrecios")
    @Produces("application/json")
    public Response configuracionPrecios() {
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.configuracionPrecios();        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    @GET
    @Path("/articulosMultiPrecios/{precioEmpresaId}")
    @Produces("application/json")
    public Response articulosMultiPrecios(@PathParam("precioEmpresaId") int precioEmpresaId) {
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.articulosMultiPrecios(precioEmpresaId);        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    @GET
    @Path("/clientesPoliticas/{vendedorId}")
    @Produces("application/json")
    public Response clientesPoliticas(@PathParam("vendedorId") int vendedorId) {
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.clientesPoliticas(vendedorId);        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    @GET
    @Path("/articulosPromociones")
    @Produces("application/json")
    public Response articulosPromociones() {
        try {            
            return Response.ok(controlador.promociones()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    /**************************************************************************/
    
    @GET  
    @Path("/getArticulosPromVta45")
    @Produces("application/json")
    public Response getArticulosPromVta45() throws SQLException {
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.getArticulosPromVta45();        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    @GET
    @Path("/depurarRutas")
    @Produces("application/json")
    public Response depurarRutas() throws SQLException {
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.depurarRutas();        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    
    @GET
    @Path("/choferes")
    @Produces("application/json")
    public Response choferes() {
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.choferes();        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    /**************************************************************************/
    
    @GET
    @Path("/existenciaArticulos")
    @Produces("application/json")
    public Response existenciaArticulos() {
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.existenciaArticulos();        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    @GET
    @Path("/existenciaArticulosRefactor")
    @Produces("application/json")
    public Response existenciaArticulosRefactor() {
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.existenciaArticulosRefactor();        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    @GET
    @Path("/existenciaArticulos2024")
    @Produces("application/json")
    public Response existenciaArticulos2024() {
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.existenciaArticulos2024();        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    /**************************************************************************/
    
    @GET
    @Path("/procesaPoliticas")
    @Produces("application/json")
    public Response procesaPoliticas() {
        try {            
            return Response.ok(controlador.paginarPoliticaDescuentoArticuloCliente()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }
        return null;
    }
    
    @GET
    @Path("/politicaDescuentoArticuloClienteRefactor/{pagina}")
    @Produces("application/json")
    public Response politicaDescuentoArticuloClienteRefactor(@PathParam("pagina") int pagina) {
        try {            
            return Response.ok(controlador.politicaDescuentoArticuloClienteRefactor(pagina)).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    /**************************************************************************/
    
    @GET  
    @Path("/politicasPorVolumen")
    @Produces("application/json")
    public Response politicasPorVolumen() throws SQLException {
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.politicasPorVolumen();        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    @GET  
    @Path("/politicasPorVolumenMultiprecios/{precioEmpresaId}")
    @Produces("application/json")
    public Response politicasPorVolumenMultiprecios(@PathParam("precioEmpresaId") int precioEmpresaId) throws SQLException {
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.politicasPorVolumenMultiprecios(precioEmpresaId);        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    /**************************************************************************/
    
    @GET  
    @Path("/serieFolioCXC/{cobradorId}")
    @Produces("application/json")
    public Response serieFolioCXC(@PathParam("cobradorId") int cobradorId) throws SQLException {
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.serieFolioCXC(cobradorId);        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    @GET
    @Path("/clientesRefactor/{vendedorId}")
    @Produces("application/json")
    public Response clientesRefactor(@PathParam("vendedorId") int vendedorId) {
        try {            
            return Response.ok(controlador.clientesRefactor(vendedorId)).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/clientesRefactor")
    @Produces("application/json")
    public Response clientesRefactor() {
        try {            
            return Response.ok(controlador.clientesRefactor()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/clientesDireccionPrincipal/{vendedorId}")
    @Produces("application/json")
    public Response clientesDireccionPrincipal(@PathParam("vendedorId") int vendedorId) throws SQLException {
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.clientesDireccionPrincipal(vendedorId);        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    @GET
    @Path("/clientesDireccionPrincipal")
    @Produces("application/json")
    public Response clientesDireccionPrincipal() throws SQLException {
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.clientesDireccionPrincipal();        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    @GET
    @Path("/clientesEmitenFactura/{vendedorId}")
    @Produces("application/json")
    public Response clientesEmitenFactura(@PathParam("vendedorId") int vendedorId) {
        try {            
            return Response.ok(controlador.clientesEmitenFactura(vendedorId)).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/clientesEmitenFactura")
    @Produces("application/json")
    public Response clientesEmitenFactura() {
        try {            
            return Response.ok(controlador.clientesEmitenFactura()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    
    @GET
    @Path("/clientesConsignatariosAGO2022/{vendedorId}")
    @Produces("application/json")
    public Response clientesConsignatariosAGO2022(@PathParam("vendedorId") int vendedorId) {
        try {            
            return Response.ok(controlador.clientesConsignatariosAGO2022(vendedorId)).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/clientesConsignatariosAGO2022")
    @Produces("application/json")
    public Response clientesConsignatariosAGO2022() {
        try {            
            return Response.ok(controlador.clientesConsignatariosAGO2022()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/cobranzaRefactor/{vendedorId}")
    @Produces("application/json")
    public Response cobranzaRefactor(@PathParam("vendedorId") int vendedorId) {
        try {            
            return Response.ok(controlador.cobranzaRefactor(vendedorId)).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    /**************************************************************************/
    
    @POST  
    @Path("/postPedidos")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response postPedidos(String jsonString) throws SQLException {
        Controlador controlador = new Controlador();
        Gson gson = new Gson(); 
        ResponseRequest responseRequest = controlador.createPedidosPOP(jsonString);        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    /**************************************************************************/
    
    @POST  
    @Path("/createCobrosXDepositarIndividual")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response createCobrosXDepositarIndividual(String jsonString) throws SQLException {
        Controlador controlador = new Controlador();
        Gson gson = new Gson(); 
        ResponseRequest responseRequest = controlador.createCobrosXDepositarIndividual(jsonString);        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    @POST  
    @Path("/createCobroXDepositarMicrosip")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response createCobroXDepositarMicrosip(String jsonString) throws SQLException {
        Controlador controlador = new Controlador();
        Gson gson = new Gson(); 
        ResponseRequest responseRequest = controlador.createCobroXDepositarMicrosip(jsonString);        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    @POST  
    @Path("/estatusRuta")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response estatusRuta(String jsonString) throws SQLException {
        Controlador controlador = new Controlador();
        Gson gson = new Gson(); 
        ResponseRequest responseRequest = controlador.estatusRuta(jsonString);        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    /**************************************************************************/
    
    @GET
    @Path("/cobrosMicrosip/{cobradorId}")
    @Produces("application/json")
    public Response cobrosMicrosip(@PathParam("cobradorId") int cobradorId) {
        try {            
            return Response.ok(controlador.cobrosMicrosip(cobradorId)).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @POST  
    @Path("/cobrosMicrosipChoferes/{choferId}")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response cobrosMicrosipChoferes(@PathParam("choferId") Long choferId, String cobradoresJsonIds) throws SQLException {
        Controlador controlador = new Controlador();              
        Gson gson = new Gson(); 
        ResponseRequest responseRequest = controlador.cobrosMicrosipChoferes(choferId, cobradoresJsonIds);        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    /**************************************************************************/
    
    @POST  
    @Path("/createDepositosRefactor")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response createDepositosRefactor(String jsonString) throws SQLException {
        Controlador controlador = new Controlador();              
        Gson gson = new Gson(); 
        ResponseRequest responseRequest = controlador.createDepositosRefactor(jsonString);        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    /**************************************************************************/
    
    @POST  
    @Path("/createVisitasClientes")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response createVisitasClientes(String jsonString) throws SQLException {
        System.out.print(jsonString);
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.createVisitasClientes(jsonString);        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    /**************************************************************************/
   
    @POST  
    @Path("/visitasEfectivasInefectivas")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response visitasEfectivasInefectivas(String jsonString) throws SQLException {
        System.out.print(jsonString);
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.visitasEfectivasInefectivas(jsonString);        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    /**************************************************************************/    
    
    @GET
    @Path("/articulosAlmacenes")
    @Produces("application/json")
    public Response articulosAlmacenes() {
        try {            
            return Response.ok(controlador.articulosAlmacenes()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }       
    
    @GET
    @Path("/cuentasBancarias")
    @Produces("application/json")
    public Response cuentasBancarias() {
        try {            
            return Response.ok(controlador.cuentasBancarias()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/articulos")
    @Produces("application/json")
    public Response articulos() {
        try {            
            return Response.ok(controlador.articulos()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    

    @GET
    @Path("/politicaDescuentoArticuloCliente")
    @Produces("application/json")
    public Response politicaDescuentoArticuloCliente() {
        try {            
            return Response.ok(controlador.politicaDescuentoArticuloCliente()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/clientes/{vendedorId}")
    @Produces("application/json")
    public Response clientes(@PathParam("vendedorId") int vendedorId) {
        try {            
            return Response.ok(controlador.clientes(vendedorId)).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/clientes")
    @Produces("application/json")
    public Response clientes() {
        try {            
            return Response.ok(controlador.clientes()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }

    @GET
    @Path("/clientesConsignatarios/{vendedorId}")
    @Produces("application/json")
    public Response clientesConsignatarios(@PathParam("vendedorId") int vendedorId) {
        try {            
            return Response.ok(controlador.clientesConsignatarios(vendedorId)).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }

    @GET
    @Path("/clientesConsignatarios")
    @Produces("application/json")
    public Response clientesConsignatarios() {
        try {            
            return Response.ok(controlador.clientesConsignatarios()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/cobranza/{vendedorId}")
    @Produces("application/json")
    public Response cobranza(@PathParam("vendedorId") int vendedorId) {
        try {            
            return Response.ok(controlador.cobranza(vendedorId)).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }

    @GET
    @Path("/cobranzaRutas/{chofer}")
    @Produces("application/json")
    public Response cobranzaRutas(@PathParam("chofer") String chofer) {
        try {            
            return Response.ok(controlador.cobranzaRutas(chofer)).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/cobranzaRutasRefactor/{choferId}")
    @Produces("application/json")
    public Response cobranzaRutas(@PathParam("choferId") int choferId) {
        try {           
            
           
            return Response.ok(controlador.cobranzaRutasRefactor(choferId)).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/cobranzaRutasXAgenteDiario/{vendedorId}")
    @Produces("application/json")
    public Response cobranzaRutasXAgenteDiario(@PathParam("vendedorId") int vendedorId) {
        try {            
            return Response.ok(controlador.cobranzaRutasXAgenteDiario(vendedorId)).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }          
    
    @POST  
    @Path("/postPedidosRefactor")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response postPedidosRefactor(String jsonString) throws SQLException {
        Controlador controlador = new Controlador();
      
        ArrayList<PedidoGrabado> listaPedidosGrabados= null;
        listaPedidosGrabados = controlador.createPedidosRefactor(jsonString);
        if (listaPedidosGrabados.size() > 0)
        {
           Gson gson = new Gson();            
           String feeds = gson.toJson(listaPedidosGrabados);
           return Response.ok(feeds).build();            
        }else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();        
        }
    }
    
    @POST  
    @Path("/in")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response createCobrosXDepositar(String jsonString) throws SQLException {
        Controlador controlador = new Controlador();
              
        /*List<CobroXDepositarGrabado> listaCobroXDepositarGrabado = null;
        listaCobroXDepositarGrabado = controlador.createCobrosXDepositarRefactor(jsonString);
        if (listaCobroXDepositarGrabado.size() > 0)
        {
           Gson gson = new Gson();            
           String feeds = gson.toJson(listaCobroXDepositarGrabado);
           return Response.ok(feeds).build();            
        } else
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();        */
        Gson gson = new Gson(); 
        ResponseRequest responseRequest = controlador.createCobrosXDepositarRefactor(jsonString);        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
   
    @POST  
    @Path("/createDepositos")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response createDepositos(String jsonString) throws SQLException {
        Controlador controlador = new Controlador();
        
        //if (controlador.createAbonos(jsonString))        
        List<DepositoGrabado> listaDepositoGrabado = null;
        listaDepositoGrabado = controlador.createDepositos(jsonString);
        if (listaDepositoGrabado.size() > 0)
        {
           Gson gson = new Gson();            
           String feeds = gson.toJson(listaDepositoGrabado);
           return Response.ok(feeds).build();            
        } else
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();        
    }
   
    @GET
    @Path("/datosEmpresa")
    @Produces("application/json")
    public Response datosEmpresa() {
        try {            
            return Response.ok(controlador.datosEmpresa()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    
    
    /*DEPRECADOS*/
    @GET
    @Path("/sucursalesEmpresa")
    @Produces("application/json")
    public Response sucursalesEmpresa() {
        try {            
            return Response.ok(controlador.catalogoSucursalesEmpresa()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/preciosEmpresa")
    @Produces("application/json")
    public Response preciosEmpresa() {
        try {            
            return Response.ok(controlador.catalogoPreciosEmpresa()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/conceptosCuentasXCobrar")
    @Produces("application/json")
    public Response conceptosCuentasXCobrar() {
        try {            
            return Response.ok(controlador.catalogoConceptosCuentasXCobrar()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/condicionesDePago")
    @Produces("application/json")
    public Response condicionesDePago() {
        try {            
            return Response.ok(controlador.catalogoCondicionesDePago()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/rolesClavesArticulos")
    @Produces("application/json")
    public Response rolesClavesArticulos() {
        try {            
            return Response.ok(controlador.catalogoRolesClavesArticulos()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
          
    @POST  
    @Path("/creaConfiguracionMobil")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response creaConfiguracionMobil(String jsonString) {
        Controlador controlador = new Controlador();
            
        if (controlador.creaConfiguracionMobil(jsonString)) {
            System.out.println("okokokoko");
           return Response.ok("ok").build();            
        } else
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();                
    }
                   
    @GET
    @Path("/articulosAlmacenes/{articulo}")
    @Produces("application/json")
    public Response articulosAlmacenes(@PathParam("articulo") String articulo) {
        try {            
            return Response.ok(controlador.articulosAlmacenes(articulo)).build();            
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/articulosPrecios/{articulo}")
    @Produces("application/json")
    public Response articulosPrecios(@PathParam("articulo") String articulo) {
        try {            
            return Response.ok(controlador.getArticuloPrecio(articulo)).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }        
    
    @GET
    @Path("/bancos")
    @Produces("application/json")
    public Response bancos() {
        try {            
            return Response.ok(controlador.bancos()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
            
    @GET
    @Path("/almacenesConfiguracion")
    @Produces("application/json")
    public Response almacenesConfiguracion() {
        try {            
            return Response.ok(controlador.almacenesConfiguracion()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }     
    
    @POST  
    @Path("/detalleDocumentoCXC")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response detalleDocumentoCXC(String jsonString) throws SQLException {
        Controlador controlador = new Controlador();
        
        Gson gson = new Gson(); 
        ResponseRequest responseRequest = controlador.detalleDocumentoCXC(jsonString);        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    
    @GET  
    @Path("/rutaAsignadaYOrdenadaConMaps/{choferId}")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response rutaAsignadaYOrdenadaConMaps(@PathParam("choferId") int choferId) {
        Controlador controlador = new Controlador();
        
        Gson gson = new Gson(); 
        ResponseRequest responseRequest = controlador.rutaAsignadaYOrdenadaConMaps(choferId);        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    @GET  
    @Path("/ultimaMetadataMicrosip")
    @Produces("application/json")
    public Response ultimaMetadataMicrosip() {
        Controlador controlador = new Controlador();
        
        Gson gson = new Gson(); 
        ResponseRequest responseRequest = controlador.ultimaMetadataMicrosip();        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
    
    @GET  
    @Path("/articulosMensajes")
    @Produces("application/json")
    public Response articulosMensajes() {
        Controlador controlador = new Controlador();
        
        Gson gson = new Gson(); 
        ResponseRequest responseRequest = controlador.articulosMensajes();        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;
    }
            
    /*DEPRECADO*/
    @GET
    @Path("/viasEmbarque")
    @Produces("application/json")
    public Response viasEmbarque() {
        try {            
            return Response.ok(controlador.viasEmbarque()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }     
    
    /*@POST  
    @Path("/postPedidos")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response postPedidos(String jsonString) throws SQLException {
        Controlador controlador = new Controlador();
      
        ArrayList<PedidoGrabado> listaPedidosGrabados= null;
        listaPedidosGrabados = controlador.createPedidos(jsonString);
        if (listaPedidosGrabados.size() > 0)
        {
           Gson gson = new Gson();            
           String feeds = gson.toJson(listaPedidosGrabados);
           return Response.ok(feeds).build();            
        }else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();        
        }
    }*/
}