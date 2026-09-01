/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.servicios;

import com.app.contants.ConnectionClient;
import com.app.dao.Controlador;
import com.app.models.AbonoDetalleEntity;
import com.app.models.FiltroGeolocalizacion;
import com.app.utilerias.StateRequest;
import com.app.utilerias.excel.ExcelWriter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

/**
 *
 * @author angel
 */
@Path("/Actualizacion")
public class Actualizacion {
    private Controlador controlador = new Controlador();
    
    @GET
    @Path("/actualizaPoliticas")
    @Produces("application/json")
    public Response actualizaPoliticas() {
        try {            
            if (controlador.eliminaPoliticasDescuentosArticulos())
                return Response.ok("Politicas eliminadas correctamente, solicita a tus agentes sincronizar nuevamente las politicas").build();
            else
                return Response.ok("Error al eliminar las Politicas").build();
        } catch (Exception e) {
            StateRequest stateRequest = new StateRequest();
            stateRequest.success(new AbonoDetalleEntity());
            System.out.println("Exception Error" + e);
        }

        return null;
    }
     
    @GET
    @Path("/vendedores")
    @Produces("application/json")
    public Response vendedores() {
        try {            
            return Response.ok(controlador.vendedores()).build();
        } catch (Exception e) {
            
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @POST
    @Path("/generateXLSX")
    @Produces("application/vnd.ms-excel")
    public Response generateXLSX(String jsonString) {
        try {  
            System.out.println(jsonString);
            Type collectionType = new TypeToken<FiltroGeolocalizacion>() {}.getType();
            FiltroGeolocalizacion filtroGeolocalizacion = new Gson().fromJson(jsonString, collectionType);
            ExcelWriter excelWriter = new ExcelWriter();
            excelWriter.excel(filtroGeolocalizacion.getVendedorId(), filtroGeolocalizacion.getFechaInicial(), filtroGeolocalizacion.getFechaFinal());
            File file = new File(ConnectionClient.RUTA_EXCEL);
            
            
            ResponseBuilder responseBuilder = Response.ok((Object) file);
            responseBuilder.header("Content-Disposition", "attachment; filename=\"MyJerseyExcelFile.xlsx\"");
            return responseBuilder.build();
            
            /*StateRequest stateRequest = new StateRequest();
            stateRequest.response(StateRequest.DataStatus.ERROR, null, "Sucedio un erro es el siguiente");
            return Response.ok(new Gson().toJson(stateRequest)).build();*/
        } catch (Exception e) {
            
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/responseData")
    public Response responseData() {
        try {            
            StateRequest stateRequest = new StateRequest();
            stateRequest.response(StateRequest.DataStatus.ERROR, null, "Sucedio un erro es el siguiente");
            return Response.ok(new Gson().toJson(stateRequest)).build();
        } catch (Exception e) {
            
            System.out.println("Exception Error" + e);
        }

        return null;
    }
}
