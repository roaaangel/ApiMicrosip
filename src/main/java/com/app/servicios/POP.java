package com.app.servicios;

import com.app.dao.Controlador;
import com.app.dao.Utilerias;
import com.app.models.ClientePOPSencillo;
import com.app.utilerias.ResponseRequest;
import com.app.utilerias.excel.CreateExcel;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import org.json.JSONException;
import org.json.JSONObject;


@Path("/POP")
public class POP {
    @POST
    @Path("/generarExcelPOP")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public Response generarExcelPOP(String jsonClientePOP) {
        try {
            JSONObject jsonObject = new JSONObject(jsonClientePOP);            
            String tipoArchivo = jsonObject.getString("tipoArchivo");  
            int anioSeleccionado = jsonObject.getInt("anioSeleccionado");
            int numeroTrimestreSeleccionado = jsonObject.getInt("numeroTrimestreSeleccionado");
            // Simulación del servicio
            Controlador controlador = new Controlador();
            ResponseRequest response = controlador.reporteClientesPOP(anioSeleccionado, numeroTrimestreSeleccionado);

            if (response.getStatus() == ResponseRequest.DataStatus.OK) {
                List<ClientePOPSencillo> listaPOP = (List<ClientePOPSencillo>)response.getData();
                               
                CreateExcel excelWriter = new CreateExcel();
                byte[] excelBytes = excelWriter.generaExcelPOP(listaPOP);
                if (excelBytes == null) {
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("Se generó un error al generar el Excel")
                            .build();
                }

                // Crear archivo temporal
                File tempFile = File.createTempFile("pop", ".xlsx");
                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    fos.write(excelBytes);
                }

                // Preparar archivo como recurso descargable
                return Response.ok((Object) tempFile)
                        .header("Content-Disposition", "attachment; filename=pedido_sugerido.xlsx")
                        .header("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                        .build();
            } else if (response.getStatus() == ResponseRequest.DataStatus.ERROR) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(response.getMensaje())
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno: " + e.getMessage())
                    .build();
        }
    }
    
    @POST  
    @Path("/generarPDFPOP")
    @Consumes({"application/json"})
    @Produces({"application/pdf","application/vnd.ms-Excel"})
    public Response generarPDFPOP(String jsonClientePOP) throws SQLException {        
        try {                        
            JSONObject jsonObject = new JSONObject(jsonClientePOP);            
            String tipoArchivo = jsonObject.getString("tipoArchivo");  
            int anioSeleccionado = jsonObject.getInt("anioSeleccionado");
            int numeroTrimestreSeleccionado = jsonObject.getInt("numeroTrimestreSeleccionado");
            
            Gson gson = new Gson();            
            Utilerias utilerias = new Utilerias();
            String PATH = utilerias.getConfiguracion();            
            Resources.logger.info("RUTA: " + PATH);
            String NAME_POP = "POP_";
            String EXTENSION_ARCHIVO = tipoArchivo;            
            String nombreArchivo = String.format("%s%s", NAME_POP, EXTENSION_ARCHIVO);
            String pathFile = String.format("%s/%s", PATH, nombreArchivo);                      
            Resources.logger.info("RUTA: " + pathFile);
            Reporteador jasperReportClass = new Reporteador();
            
            jasperReportClass.generatePdfPOP(pathFile, tipoArchivo, anioSeleccionado, numeroTrimestreSeleccionado);
            java.nio.file.Path path = Paths.get(pathFile);
            File file = new File(pathFile);
    
            final InputStream responseStream = new FileInputStream(file);
            StreamingOutput output = new StreamingOutput() {
                @Override
                public void write(OutputStream out) throws IOException, WebApplicationException {
                    int length;
                    byte[] buffer = new byte[1024];
                    while((length = responseStream.read(buffer)) != -1) {
                        out.write(buffer, 0, length);
                    }
                    out.flush();
                    responseStream.close();
                    boolean isDeleted = file.delete();
                    //log.info(exportFile.getCanonicalPath()+":File is deleted:"+ isDeleted);
                }
            };  
            return Response.ok(output).header("Content-Disposition", "attachment; filename=rulset-" + file.getName()).build();
        } catch (FileNotFoundException ex) {
            Resources.logger.error("SUCEDIO UNA EXEPCION FileNotFoundException: " + ex.getMessage());                   
        } catch (JSONException ex) {
            Resources.logger.error("SUCEDIO UNA EXEPCION JSONException: " + ex.getMessage());                   
        } 
        return null;
    }
    
    @POST  
    @Path("/clientesConPOP")
    @Consumes({"application/json"})
    @Produces("application/json")
    public Response clientesConPOP(String jsonString) throws SQLException{         
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            
            int anioSeleccionado = jsonObject.getInt("anioSeleccionado");
            int numeroTrimestreSeleccionado = jsonObject.getInt("numeroTrimestreSeleccionado");        
   
            Controlador controlador = new Controlador();
            
            Gson gson = new Gson();
            ResponseRequest responseRequest = controlador.reporteClientesPOP(anioSeleccionado, numeroTrimestreSeleccionado);
            if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){
                String data = gson.toJson(responseRequest.getData());
                return Response.ok(data).build();
            }
            else if (responseRequest.getStatus() == ResponseRequest.DataStatus.NO_CONTENT){
                String data = responseRequest.getMensaje();                   
                return Response.noContent().build();
            }
            else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){
                String data = responseRequest.getMensaje();
                return Response.status(500).entity(data).build();
            }
            return null;
        } catch (JSONException ex) {
            Logger.getLogger(POP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;        
    }  
    
    @GET  
    @Path("/trimestres")
    @Produces("application/json") 
    public Response trimestres() throws SQLException{  
        Controlador controlador = new Controlador();
        Gson gson = new Gson();
        ResponseRequest responseRequest = controlador.trimestres();
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();
        }
        else if (responseRequest.getStatus() == ResponseRequest.DataStatus.NO_CONTENT){
            String data = responseRequest.getMensaje();
            return Response.noContent().build();
        }
        else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();
        }   
        return null;
    }
    
    @GET  
    @Path("/porcentajesTrimestral")
    @Produces("application/json") 
    public Response porcentajesTrimestral() throws SQLException{  
        Controlador controlador = new Controlador();
        Gson gson = new Gson();
        ResponseRequest responseRequest = controlador.porcentajesTrimestral();
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();
        }
        else if (responseRequest.getStatus() == ResponseRequest.DataStatus.NO_CONTENT){
            String data = responseRequest.getMensaje();
            return Response.noContent().build();
        }
        else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();
        }   
        return null;
    }
    
    @POST  
    @Path("/actualizarPorcentajesTrimestres")
    @Consumes({"application/json"})
    @Produces("application/json") 
    public Response actualizarPorcentajesTrimestres(String jsonString) throws SQLException{  
        Controlador controlador = new Controlador();
        Gson gson = new Gson();
        ResponseRequest responseRequest = controlador.actualizarPorcentajesTrimestres(jsonString);
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();
        }
        else if (responseRequest.getStatus() == ResponseRequest.DataStatus.NO_CONTENT){
            String data = responseRequest.getMensaje();
            return Response.noContent().build();
        }
        else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();
        }   
        return null;
    }
    
    @GET  
    @Path("/porcentajesDiasPlazo")
    @Produces("application/json") 
    public Response porcentajesDiasPlazo() throws SQLException{  
        Controlador controlador = new Controlador();
        Gson gson = new Gson();
        ResponseRequest responseRequest = controlador.porcentajesDiasPlazo();
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();
        }
        else if (responseRequest.getStatus() == ResponseRequest.DataStatus.NO_CONTENT){
            String data = responseRequest.getMensaje();
            return Response.noContent().build();
        }
        else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();
        }   
        return null;
    }
    
    @POST  
    @Path("/actualizarPorcentajesDiasPlazo")
    @Consumes({"application/json"})
    @Produces("application/json") 
    public Response actualizarPorcentajesDiasPlazo(String jsonString) throws SQLException{  
        Controlador controlador = new Controlador();
        Gson gson = new Gson();
        ResponseRequest responseRequest = controlador.actualizarPorcentajesDiasPlazo(jsonString);
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();
        }
        else if (responseRequest.getStatus() == ResponseRequest.DataStatus.NO_CONTENT){
            String data = responseRequest.getMensaje();
            return Response.noContent().build();
        }
        else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();
        }   
        return null;
    }
    
    @GET  
    @Path("/girosComerciales")
    @Produces("application/json") 
    public Response girosComerciales() throws SQLException{  
        Controlador controlador = new Controlador();
        Gson gson = new Gson();
        ResponseRequest responseRequest = controlador.girosComerciales();
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();
        }
        else if (responseRequest.getStatus() == ResponseRequest.DataStatus.NO_CONTENT){
            String data = responseRequest.getMensaje();
            return Response.noContent().build();
        }
        else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();
        }   
        return null;
    }
    
    @PUT  
    @Path("/createGiroComercial")
    @Consumes({"application/json"})
    @Produces("application/json") 
    public Response createGiroComercial(String jsonString) throws SQLException{  
        Controlador controlador = new Controlador();
        Gson gson = new Gson();
        ResponseRequest responseRequest = controlador.createGiroComercial(jsonString);
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();
        }
        else if (responseRequest.getStatus() == ResponseRequest.DataStatus.NO_CONTENT){
            String data = responseRequest.getMensaje();
            return Response.noContent().build();
        }
        else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();
        }   
        return null;
    }
    
    @POST  
    @Path("/actualizaGiroComercial")
    @Consumes({"application/json"})
    @Produces("application/json") 
    public Response actualizaGiroComercial(String jsonString) throws SQLException{  
        Controlador controlador = new Controlador();
        Gson gson = new Gson();
        ResponseRequest responseRequest = controlador.actualizaGiroComercial(jsonString);
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();
        }
        else if (responseRequest.getStatus() == ResponseRequest.DataStatus.NO_CONTENT){
            String data = responseRequest.getMensaje();
            return Response.noContent().build();
        }
        else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();
        }   
        return null;
    }
    
    @DELETE  
    @Path("/eliminaGiroComercial/{id}")
    @Consumes({"application/json"})
    @Produces("application/json") 
    public Response eliminaGiroComercial(@PathParam("id") int id) throws SQLException{  
        Controlador controlador = new Controlador();
        Gson gson = new Gson();
        ResponseRequest responseRequest = controlador.eliminaGiroComercial(id);
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK){
            String data = gson.toJson(responseRequest.getData());
            return Response.ok(data).build();
        }
        else if (responseRequest.getStatus() == ResponseRequest.DataStatus.NO_CONTENT){
            String data = responseRequest.getMensaje();
            return Response.noContent().build();
        }
        else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR){
            String data = responseRequest.getMensaje();
            return Response.status(500).entity(data).build();
        }   
        return null;
    }
}
