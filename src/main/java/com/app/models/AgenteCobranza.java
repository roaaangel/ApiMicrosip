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
public class AgenteCobranza {
    private int vendedorId;
    private String visualizaCobranza;

    /**
     * @return the vendedorId
     */
    public int getVendedorId() {
        return vendedorId;
    }

    /**
     * @param vendedorId the vendedorId to set
     */
    public void setVendedorId(int vendedorId) {
        this.vendedorId = vendedorId;
    }

    /**
     * @return the visualizaCobranza
     */
    public String getVisualizaCobranza() {
        return visualizaCobranza;
    }

    /**
     * @param visualizaCobranza the visualizaCobranza to set
     */
    public void setVisualizaCobranza(String visualizaCobranza) {
        this.visualizaCobranza = visualizaCobranza;
    }
}
