/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.cobradores;

/**
 *
 * @author burtebony
 */
public class Cobrador {
    private int cobradorId;
    private String nombre;

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
