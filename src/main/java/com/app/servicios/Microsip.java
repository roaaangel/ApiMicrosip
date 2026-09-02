package com.app.servicios;

import com.app.dao.Controlador;
import com.app.dao.RepositorioDAO;
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

@Path("/Microsip")
public class Microsip {
    
    private final RepositorioDAO controlador = new RepositorioDAO();
    
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
    @Path("/monedas")
    @Produces("application/json")
    public Response monedas() throws SQLException {        
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
    
    @GET  
    @Path("/vendedoresCobranza")
    @Produces("application/json")
    public Response vendedoresCobranza() throws SQLException {

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
    @Path("/motivosVisitas")
    @Produces("application/json")
    public Response motivosVisitas() throws SQLException {

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
    
    @GET  
    @Path("/articulosMensajes")
    @Produces("application/json")
    public Response articulosMensajes() {

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
    
    @GET
    @Path("/configuracionPrecios")
    @Produces("application/json")
    public Response configuracionPrecios() {

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
    @Path("/getArticulosPromVta45")
    @Produces("application/json")
    public Response getArticulosPromVta45() throws SQLException {

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
    @Path("/existenciaArticulosRefactor")
    @Produces("application/json")
    public Response existenciaArticulosRefactor() {

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
    
    @GET  
    @Path("/politicasPorVolumen")
    @Produces("application/json")
    public Response politicasPorVolumen() throws SQLException {

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
    
    @GET  
    @Path("/serieFolioCXC/{cobradorId}")
    @Produces("application/json")
    public Response serieFolioCXC(@PathParam("cobradorId") int cobradorId) throws SQLException {

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
    
    @POST  
    @Path("/detalleDocumentoCXC")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response detalleDocumentoCXC(String jsonString) throws SQLException {

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
    @Path("/clientesPoliticas/{vendedorId}")
    @Produces("application/json")
    public Response clientesPoliticas(@PathParam("vendedorId") int vendedorId) {

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
    
    @POST  
    @Path("/postPedidos")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response postPedidos(String jsonString) throws SQLException {

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
}