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
public class ArticuloPromedioVenta45POST {
    private String codigoArticulo;
    private Double promedioVenta;

    /**
     * @return the codigoArticulo
     */
    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    /**
     * @param codigoArticulo the codigoArticulo to set
     */
    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    /**
     * @return the promedioVenta
     */
    public Double getPromedioVenta() {
        return promedioVenta;
    }

    /**
     * @param promedioVenta the promedioVenta to set
     */
    public void setPromedioVenta(Double promedioVenta) {
        this.promedioVenta = promedioVenta;
    }
}
