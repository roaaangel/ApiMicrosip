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
public class DetalleDocumentoCXC {
    @SerializedName("A")
    private int doctoVEId;
    @SerializedName("B")
    private String claveArticulo;
    @SerializedName("C")
    private int articuloId;
    @SerializedName("D")
    private int unidades;
    @SerializedName("E")
    private Double precioUnitario;
    @SerializedName("F")
    private Double precioTotalNeto;

    /**
     * @return the doctoVEId
     */
    public int getDoctoVEId() {
        return doctoVEId;
    }

    /**
     * @param doctoVEId the doctoVEId to set
     */
    public void setDoctoVEId(int doctoVEId) {
        this.doctoVEId = doctoVEId;
    }

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
     * @return the precioUnitario
     */
    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * @param precioUnitario the precioUnitario to set
     */
    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * @return the precioTotalNeto
     */
    public Double getPrecioTotalNeto() {
        return precioTotalNeto;
    }

    /**
     * @param precioTotalNeto the precioTotalNeto to set
     */
    public void setPrecioTotalNeto(Double precioTotalNeto) {
        this.precioTotalNeto = precioTotalNeto;
    }
}
