/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author angel
 */
public class Chofer {
    @SerializedName(value = "choferId")
    private int choferId;
    @SerializedName(value = "clave")
    private String clave;    
    @SerializedName(value = "nombre")
    private String nombre;  

    /**
     * @return the choferId
     */
    public int getChoferId() {
        return choferId;
    }

    /**
     * @param choferId the choferId to set
     */
    public void setChoferId(int choferId) {
        this.choferId = choferId;
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
