/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

/**
 *
 * @author burtebony
 */
public class CondicionPago {
    private int condicionPagoId;
    private String nombre;

    /**
     * @return the condicionPagoId
     */
    public int getCondicionPagoId() {
        return condicionPagoId;
    }

    /**
     * @param condicionPagoId the condicionPagoId to set
     */
    public void setCondicionPagoId(int condicionPagoId) {
        this.condicionPagoId = condicionPagoId;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
