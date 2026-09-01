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
public class PoliticaXVolumen {
    @SerializedName("A")
    private int articuloId;
    @SerializedName("B")
    private int unidades;
    @SerializedName("C")
    private Double descuentoVolumen;
    @SerializedName("D")
    private String esExclusivo;
    @SerializedName("E")
    private int politicaId;
    @SerializedName("F")
    private String nombrePolitica;
    @SerializedName("G")
    private int precioEmpresaId;

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
    public int getUnidades() {
        return unidades;
    }

    /**
     * @param unidades the unidades to set
     */
    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    /**
     * @return the descuentoVolumen
     */
    public Double getDescuentoVolumen() {
        return descuentoVolumen;
    }

    /**
     * @param descuentoVolumen the descuentoVolumen to set
     */
    public void setDescuentoVolumen(Double descuentoVolumen) {
        this.descuentoVolumen = descuentoVolumen;
    }

    /**
     * @return the esExclusivo
     */
    public String getEsExclusivo() {
        return esExclusivo;
    }

    /**
     * @param esExclusivo the esExclusivo to set
     */
    public void setEsExclusivo(String esExclusivo) {
        this.esExclusivo = esExclusivo;
    }

    /**
     * @return the politicaId
     */
    public int getPoliticaId() {
        return politicaId;
    }

    /**
     * @param politicaId the politicaId to set
     */
    public void setPoliticaId(int politicaId) {
        this.politicaId = politicaId;
    }

    /**
     * @return the nombrePolitica
     */
    public String getNombrePolitica() {
        return nombrePolitica;
    }

    /**
     * @param nombrePolitica the nombrePolitica to set
     */
    public void setNombrePolitica(String nombrePolitica) {
        this.nombrePolitica = nombrePolitica;
    }

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
}
