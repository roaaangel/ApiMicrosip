/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.sucursales;

/**
 *
 * @author burtebony
 */
public class Sucursal {
    private int sucursalId;
    private String nombre;   

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
