/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.cobranza;

import java.sql.Date;

/**
 *
 * @author burtebony
 */
public class Cobranza {
    private int clienteId;
    private String nombreCliente;
    private int doctoCCId;
    private String folio;
    private Date fechaElaboracion;
    private Date fechaVencimiento;
    private Double importeCargo;
    private Double saldoCargo;
    private int atraso;
    private int conceptoCCId;

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
     * @return the nombreCliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * @param nombreCliente the nombreCliente to set
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * @return the doctoCCId
     */
    public int getDoctoCCId() {
        return doctoCCId;
    }

    /**
     * @param doctoCCId the doctoCCId to set
     */
    public void setDoctoCCId(int doctoCCId) {
        this.doctoCCId = doctoCCId;
    }

    /**
     * @return the folio
     */
    public String getFolio() {
        return folio;
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * @return the fechaElaboracion
     */
    public Date getFechaElaboracion() {
        return fechaElaboracion;
    }

    /**
     * @param fechaElaboracion the fechaElaboracion to set
     */
    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * @return the importeCargo
     */
    public Double getImporteCargo() {
        return importeCargo;
    }

    /**
     * @param importeCargo the importeCargo to set
     */
    public void setImporteCargo(Double importeCargo) {
        this.importeCargo = importeCargo;
    }

    /**
     * @return the saldoCargo
     */
    public Double getSaldoCargo() {
        return saldoCargo;
    }

    /**
     * @param saldoCargo the saldoCargo to set
     */
    public void setSaldoCargo(Double saldoCargo) {
        this.saldoCargo = saldoCargo;
    }

    /**
     * @return the atraso
     */
    public int getAtraso() {
        return atraso;
    }

    /**
     * @param atraso the atraso to set
     */
    public void setAtraso(int atraso) {
        this.atraso = atraso;
    }

    /**
     * @return the conceptoCCId
     */
    public int getConceptoCCId() {
        return conceptoCCId;
    }

    /**
     * @param conceptoCCId the conceptoCCId to set
     */
    public void setConceptoCCId(int conceptoCCId) {
        this.conceptoCCId = conceptoCCId;
    }
}