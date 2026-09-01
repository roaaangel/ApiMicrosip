/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.servicios;

import com.app.dao.ControladorDistribuidora;
import java.sql.SQLException;
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
@Path("/MicrosipDistribuidora")
public class MicrosipDistribuidora {
    private ControladorDistribuidora controlador = new ControladorDistribuidora();
    
    /*@POST  
    @Path("/postPedidos")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response postPedidos(String jsonString) throws SQLException {
        ControladorDistribuidora controlador = new ControladorDistribuidora();
      
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
    
    @POST  
    @Path("/postAbonos")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response postAbonos(String jsonString) throws SQLException {
        ControladorDistribuidora controlador = new ControladorDistribuidora();
        
        if (controlador.createAbonos(jsonString))        
           return Response.ok("OK").build();            
        else
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
    
    @GET
    @Path("/rolesClavesArticulos")
    @Produces("application/json")
    public Response rolesClavesArticulos() {
        try {            
            return Response.ok(controlador.rolesClavesArticulos()).build();
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
            return Response.ok(controlador.preciosEmpresa()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }
    
    @GET
    @Path("/sucursalesEmpresa")
    @Produces("application/json")
    public Response sucursalesEmpresa() {
        try {            
            return Response.ok(controlador.sucursalesEmpresa()).build();
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
            return Response.ok(controlador.conceptosCuentasXCobrar()).build();
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
            return Response.ok(controlador.condicionesDePago()).build();
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
            return Response.ok(controlador.configuracionMobil()).build();
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
        ControladorDistribuidora controlador = new ControladorDistribuidora();
            
        if (controlador.creaConfiguracionMobil(jsonString)) {
            System.out.println("okokokoko");
           return Response.ok("ok").build();            
        } else
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();                
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
    
    /*@GET
    @Path("/cobranza")
    @Produces("application/json")
    public Response cobranza() {
        try {            
            return Response.ok(controlador.cobranza()).build();
        } catch (Exception e) {
            System.out.println("Exception Error" + e);
        }

        return null;
    }*/
    
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
}