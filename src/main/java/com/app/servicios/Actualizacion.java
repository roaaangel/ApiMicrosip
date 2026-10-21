package com.app.servicios;

import com.app.dao.RepositorioDAO;
import com.app.models.AbonoDetalleEntity;
import com.app.utilerias.StateRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/Actualizacion")
public class Actualizacion {
    
    private final RepositorioDAO controlador = new RepositorioDAO();
    
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
    
}
