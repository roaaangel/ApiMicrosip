/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.servicios;

import com.app.dao.Controlador;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author angel
 */
@Path("/Administrador")
public class Administrador {
    private Controlador controlador = new Controlador();
    
    @GET
    @Path("/catalogosConfiguracionEmpresa")    
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response catalogosConfiguracionEmpresa() {
        try {            
            return Response.ok(controlador.catalogosConfiguracionEmpresa()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
}
