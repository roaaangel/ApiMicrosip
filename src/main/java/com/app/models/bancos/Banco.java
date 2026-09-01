/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.bancos;

/**
 *
 * @author burtebony
 */
public class Banco {
    private String claveBanco;
    private String descripcion;
    private String nombreORazonSocial;    

    /**
     * @return the claveBanco
     */
    public String getClaveBanco() {
        return claveBanco;
    }

    /**
     * @param claveBanco the claveBanco to set
     */
    public void setClaveBanco(String claveBanco) {
        this.claveBanco = claveBanco;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the nombreORazonSocial
     */
    public String getNombreORazonSocial() {
        return nombreORazonSocial;
    }

    /**
     * @param nombreORazonSocial the nombreORazonSocial to set
     */
    public void setNombreORazonSocial(String nombreORazonSocial) {
        this.nombreORazonSocial = nombreORazonSocial;
    }
}
