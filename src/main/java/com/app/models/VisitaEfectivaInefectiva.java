/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

import java.sql.Timestamp;

/**
 *
 * @author angel
 */
public class VisitaEfectivaInefectiva {
    private int id;
    private int vendedorId;
    private int clienteId;
    private String visita;
    private String fecha;
    private String hora;
    private int motivoId;

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
     * @return the vendedorId
     */
    public int getVendedorId() {
        return vendedorId;
    }

    /**
     * @param vendedorId the vendedorId to set
     */
    public void setVendedorId(int vendedorId) {
        this.vendedorId = vendedorId;
    }

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
     * @return the visita
     */
    public String getVisita() {
        return visita;
    }

    /**
     * @param visita the visita to set
     */
    public void setVisita(String visita) {
        this.visita = visita;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the motivoId
     */
    public int getMotivoId() {
        return motivoId;
    }

    /**
     * @param motivoId the motivoId to set
     */
    public void setMotivoId(int motivoId) {
        this.motivoId = motivoId;
    }
}
