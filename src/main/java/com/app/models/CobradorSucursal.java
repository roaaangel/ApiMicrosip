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
public class CobradorSucursal {
    private int cobradorId;
    private int sucursalId;

    /**
     * @return the cobradorId
     */
    public int getCobradorId() {
        return cobradorId;
    }

    /**
     * @param cobradorId the cobradorId to set
     */
    public void setCobradorId(int cobradorId) {
        this.cobradorId = cobradorId;
    }

    /**
     * @return the sucursalId
     */
    public int getSucursalId() {
        return sucursalId;
    }

    /**
     * @param sucursalId the sucursalId to set
     */
    public void setSucursalId(int sucursalId) {
        this.sucursalId = sucursalId;
    }
}
