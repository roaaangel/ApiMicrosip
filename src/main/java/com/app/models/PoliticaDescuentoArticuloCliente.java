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
public class PoliticaDescuentoArticuloCliente {
    private int politicaDescuentoArticuloClienteId;
    private String nombre;
    private int articuloId; 
    private Double descuento;

    /**
     * @return the politicaDEscuentoArticuloClienteId
     */
    public int getPoliticaDescuentoArticuloClienteId() {
        return politicaDescuentoArticuloClienteId;
    }

    /**
     * @param politicaDEscuentoArticuloClienteId the politicaDEscuentoArticuloClienteId to set
     */
    public void setPoliticaDescuentoArticuloClienteId(int politicaDescuentoArticuloClienteId) {
        this.politicaDescuentoArticuloClienteId = politicaDescuentoArticuloClienteId;
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
}
