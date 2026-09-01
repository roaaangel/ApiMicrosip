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
public class ArticuloJuego {
    private String claveArticulo;
    private int articuloId;
    private Double unidades; 
    private String rol;
    private int posicion; 

    /**
     * @return the claveArticulo
     */
    public String getClaveArticulo() {
        return claveArticulo;
    }

    /**
     * @param claveArticulo the claveArticulo to set
     */
    public void setClaveArticulo(String claveArticulo) {
        this.claveArticulo = claveArticulo;
    }

    /**
     * @return the articuloId
     */
    public int getArticuloId() {
        return articuloId;
    }

    /**
     * @param articuloId the articuloId to set
     */
    public void setArticuloId(int articuloId) {
        this.articuloId = articuloId;
    }

    /**
     * @return the unidades
     */
    public Double getUnidades() {
        return unidades;
    }

    /**
     * @param unidades the unidades to set
     */
    public void setUnidades(Double unidades) {
        this.unidades = unidades;
    }

    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * @return the posicion
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
