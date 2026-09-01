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
public class PoliticaCliente {
    @SerializedName("A")
    private int clienteId;
    @SerializedName("B")
    private String claveCliente;
    @SerializedName("C")
    private int politicaPreciosClienteId;
    @SerializedName("D")
    private String nombrePolitica;
    @SerializedName("E")
    private int politicaDescuentoArticuloClienteId;
    @SerializedName("F")
    private int precioEmpresaId;
    @SerializedName("G")
    private String origen;
    @SerializedName("H")
    private String nombrePrecio;

    /**
     * @return the clienteId
     */
    public int getClienteId() {
        return clienteId;
    }

    /**
     * @param clienteId the clienteId to set
     */
    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * @return the claveCliente
     */
    public String getClaveCliente() {
        return claveCliente;
    }

    /**
     * @param claveCliente the claveCliente to set
     */
    public void setClaveCliente(String claveCliente) {
        this.claveCliente = claveCliente;
    }

    /**
     * @return the politicaPreciosClienteId
     */
    public int getPoliticaPreciosClienteId() {
        return politicaPreciosClienteId;
    }

    /**
     * @param politicaPreciosClienteId the politicaPreciosClienteId to set
     */
    public void setPoliticaPreciosClienteId(int politicaPreciosClienteId) {
        this.politicaPreciosClienteId = politicaPreciosClienteId;
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
     * @return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * @return the nombrePrecio
     */
    public String getNombrePrecio() {
        return nombrePrecio;
    }

    /**
     * @param nombrePrecio the nombrePrecio to set
     */
    public void setNombrePrecio(String nombrePrecio) {
        this.nombrePrecio = nombrePrecio;
    }
}
