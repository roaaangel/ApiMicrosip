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
public class PrecioEmpresa {
    private int precioEmpresaId;
    private String nombre;

    /**
     * @return the precioEmpresaId
     */
    public int getPrecioEmpresaId() {
        return precioEmpresaId;
    }

    /**
     * @param precioEmpresaId the precioEmpresaId to set
     */
    public void setPrecioEmpresaId(int precioEmpresaId) {
        this.precioEmpresaId = precioEmpresaId;
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
