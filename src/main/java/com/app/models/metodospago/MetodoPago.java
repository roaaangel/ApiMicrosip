/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.metodospago;

/**
 *
 * @author burtebony
 */
public class MetodoPago {
    private int formaCobroCCId;
    private String clave;
    private String concepto;

    /**
     * @return the formaCobroId
     */
    public int getFormaCobroCCId() {
        return formaCobroCCId;
    }

    /**
     * @param formaCobroId the formaCobroId to set
     */
    public void setFormaCobroCCId(int formaCobroCCId) {
        this.formaCobroCCId = formaCobroCCId;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the concepto
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * @param concepto the concepto to set
     */
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
}
