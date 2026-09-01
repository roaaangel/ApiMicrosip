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
public class ClienteConsignatario {
    @SerializedName(value = "A")
    private int clienteId;
    @SerializedName(value = "B")
    private int direccionClienteId;    
    @SerializedName(value = "C")
    private String nombreConsignatario;
    @SerializedName(value = "D")
    private String usarParaEnvio;
    @SerializedName(value = "E")
    private String usarParaFacturar;

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
     * @return the direccionClienteId
     */
    public int getDireccionClienteId() {
        return direccionClienteId;
    }

    /**
     * @param direccionClienteId the direccionClienteId to set
     */
    public void setDireccionClienteId(int direccionClienteId) {
        this.direccionClienteId = direccionClienteId;
    }

    /**
     * @return the nombreConsignatario
     */
    public String getNombreConsignatario() {
        return nombreConsignatario;
    }

    /**
     * @param nombreConsignatario the nombreConsignatario to set
     */
    public void setNombreConsignatario(String nombreConsignatario) {
        this.nombreConsignatario = nombreConsignatario;
    }

    /**
     * @return the usarParaEnvio
     */
    public String getUsarParaEnvio() {
        return usarParaEnvio;
    }

    /**
     * @param usarParaEnvio the usarParaEnvio to set
     */
    public void setUsarParaEnvio(String usarParaEnvio) {
        this.usarParaEnvio = usarParaEnvio;
    }

    /**
     * @return the usarParaFacturar
     */
    public String getUsarParaFacturar() {
        return usarParaFacturar;
    }

    /**
     * @param usarParaFacturar the usarParaFacturar to set
     */
    public void setUsarParaFacturar(String usarParaFacturar) {
        this.usarParaFacturar = usarParaFacturar;
    }
}
