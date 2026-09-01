/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.almacenes;

/**
 *
 * @author burtebony
 */
public class Almacen {
    private int almacenId;
    private String nombre; 
    private int sucursalId;

    /**
     * @return the almacenId
     */
    public int getAlmacenId() {
        return almacenId;
    }

    /**
     * @param almacenId the almacenId to set
     */
    public void setAlmacenId(int almacenId) {
        this.almacenId = almacenId;
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
