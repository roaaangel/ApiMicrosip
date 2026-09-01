/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.servicios;

import com.app.dao.Controlador;
import com.app.dao.Utilerias;
import com.app.utilerias.ResponseRequest;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author burtebony
 */
@Path("/Reporte")
public class Reporte {

    @POST
    @Path("/ventaUtilidad")
    @Produces("application/json")
    public Response ventaUtilidad(String jsonString) {
        System.out.println("---" + jsonString);
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            try {
                Utilerias utilerias = new Utilerias();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                java.sql.Date fechaInicial = utilerias.convertStringToDate(jsonObject.getString("fechaInicial"));
                java.sql.Date fechaFinal = utilerias.convertStringToDate(jsonObject.getString("fechaFinal"));
                Controlador controlador = new Controlador();

                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .setDateFormat("dd/MM/yyyy")
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create();
                return Response.ok(controlador.ventaUtilidades(fechaInicial, fechaFinal)).build();
            } catch (Exception e) {
                System.out.println("Exception Error" + e);
            }
        } catch (JSONException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception Error" + ex);
        }
        return null;
    }

    @GET
    @Path("/pedidosNuevos")
    @Produces("application/json")
    public Response pedidosNuevos() {
        try {
            Controlador controlador = new Controlador();

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .setDateFormat("dd/MM/yyyy")
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            return Response.ok(controlador.pedidosNuevos()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/pedidosNuevosDetalle")
    @Produces("application/json")
    public Response pedidosNuevosDetalle() {
        try {
            Controlador controlador = new Controlador();

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .setDateFormat("dd/MM/yyyy")
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            return Response.ok(controlador.pedidosNuevosDetalle()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @POST
    @Path("/createArticulosPromVta45")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response createArticulosPromVta45(String jsonString) {                        
        Controlador controlador = new Controlador();
      
        Gson gson = new Gson();  
        ResponseRequest responseRequest = controlador.createArticulosPromVta45(jsonString);        
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){            
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(responseRequest.getMensaje()).build();                  
        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){            
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();                   
        }
        return null;              
    }
}