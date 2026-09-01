/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

/**
 *
 * @author angel
 */
public class ArticuloMensaje {
    private int articuloId;
    private String mensaje;

    /**
     * @return the articuloId
     */
    public int getArticuloId() {
        return articuloId;
    }

    /**
     * @param articuloId the articuloId to set
     */
    public void setArticuloId(int articuloId) {
        this.articuloId = articuloId;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}
