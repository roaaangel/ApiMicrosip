/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.cobrosxdepositar;

import com.app.utilerias.ResponseRequest;

/**
 *
 * @author angel
 */
public class CobroXDepositarEnviado {
    private int id;
    private ResponseRequest.DataStatus status;
    private String mensaje;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the status
     */
    public ResponseRequest.DataStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ResponseRequest.DataStatus status) {
        this.status = status;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
