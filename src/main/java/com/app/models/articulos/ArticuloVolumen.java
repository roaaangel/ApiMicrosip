/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.articulos;

/**
 *
 * @author burtebony
 */
public class ArticuloVolumen {
    private int articuloId;
    private Double unidades;
    private Double porcentajeDescuento;

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
     * @return the porcentajeDescuento
     */
    public Double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    /**
     * @param porcentajeDescuento the porcentajeDescuento to set
     */
    public void setPorcentajeDescuento(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
}
