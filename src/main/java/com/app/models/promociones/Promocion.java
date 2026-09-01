/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.promociones;

/**
 *
 * @author burtebony
 */
public class Promocion {
    private String clavePrincipal;
    private String claveAlterna;
    private String nombre;
    private Double descuento;

    /**
     * @return the clavePrincipal
     */
    public String getClavePrincipal() {
        return clavePrincipal;
    }

    /**
     * @param clavePrincipal the clavePrincipal to set
     */
    public void setClavePrincipal(String clavePrincipal) {
        this.clavePrincipal = clavePrincipal;
    }

    /**
     * @return the claveAlterna
     */
    public String getClaveAlterna() {
        return claveAlterna;
    }

    /**
     * @param claveAlterna the claveAlterna to set
     */
    public void setClaveAlterna(String claveAlterna) {
        this.claveAlterna = claveAlterna;
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

    /**
     * @return the descuento
     */
    public Double getDescuento() {
        return descuento;
    }

    /**
     * @param descuento the descuento to set
     */
    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }
}
