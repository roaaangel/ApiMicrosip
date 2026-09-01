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
public class PoliticaDescuentoArticuloClienteRefactor {
    private int politicaDescuentoArticuloClienteId;
    private String nombrePolitica;
    private int articuloId; 
    private Double descuento;
    private String esExclusivo;

    /**
     * @return the politicaDescuentoArticuloClienteId
     */
    public int getPoliticaDescuentoArticuloClienteId() {
        return politicaDescuentoArticuloClienteId;
    }

    /**
     * @param politicaDescuentoArticuloClienteId the politicaDescuentoArticuloClienteId to set
     */
    public void setPoliticaDescuentoArticuloClienteId(int politicaDescuentoArticuloClienteId) {
        this.politicaDescuentoArticuloClienteId = politicaDescuentoArticuloClienteId;
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
}
