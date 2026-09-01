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
public class ClienteEmiteFactura {
    private int clienteId;
    private String valorDesplegado;

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
     * @return the valorDesplegado
     */
    public String getValorDesplegado() {
        return valorDesplegado;
    }

    /**
     * @param valorDesplegado the valorDesplegado to set
     */
    public void setValorDesplegado(String valorDesplegado) {
        this.valorDesplegado = valorDesplegado;
    }
}
