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
public class HistoriaCambiaria {
    private int monedaId;
    private Double tipoCambioCobros;
    private Double tipoCambio;   

    /**
     * @return the monedaId
     */
    public int getMonedaId() {
        return monedaId;
    }

    /**
     * @param monedaId the monedaId to set
     */
    public void setMonedaId(int monedaId) {
        this.monedaId = monedaId;
    }

    /**
     * @return the tipoCambioCobros
     */
    public Double getTipoCambioCobros() {
        return tipoCambioCobros;
    }

    /**
     * @param tipoCambioCobros the tipoCambioCobros to set
     */
    public void setTipoCambioCobros(Double tipoCambioCobros) {
        this.tipoCambioCobros = tipoCambioCobros;
    }

    /**
     * @return the tipoCambio
     */
    public Double getTipoCambio() {
        return tipoCambio;
    }

    /**
     * @param tipoCambio the tipoCambio to set
     */
    public void setTipoCambio(Double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
}
