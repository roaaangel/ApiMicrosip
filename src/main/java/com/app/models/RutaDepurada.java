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
public class RutaDepurada {
    @SerializedName(value = "idDoctoRuta")
    private int idDoctoRuta;
    @SerializedName(value = "folioDoctoRuta")
    private String folioDoctoRuta;    
    @SerializedName(value = "idDoctoCXC")
    private int idDoctoCXC;  
    @SerializedName(value = "folioDoctoCXC")
    private String folioDoctoCXC; 
    @SerializedName(value = "doctoCCId")
    private int doctoCCId; 
    @SerializedName(value = "fechaVencimiento")
    private String fechaVencimiento; 
    @SerializedName(value = "conceptoCCId")
    private int conceptoCCId; 
    @SerializedName(value = "folio")
    private String folio; 
    @SerializedName(value = "atraso")
    private int atraso; 
    @SerializedName(value = "importeCargo")
    private Double importeCargo; 
    @SerializedName(value = "saldoCargo")
    private Double saldoCargo; 
    @SerializedName(value = "estado")
    private String estado; 

    /**
     * @return the idDoctoRuta
     */
    public int getIdDoctoRuta() {
        return idDoctoRuta;
    }

    /**
     * @param idDoctoRuta the idDoctoRuta to set
     */
    public void setIdDoctoRuta(int idDoctoRuta) {
        this.idDoctoRuta = idDoctoRuta;
    }

    /**
     * @return the folioDoctoRuta
     */
    public String getFolioDoctoRuta() {
        return folioDoctoRuta;
    }

    /**
     * @param folioDoctoRuta the folioDoctoRuta to set
     */
    public void setFolioDoctoRuta(String folioDoctoRuta) {
        this.folioDoctoRuta = folioDoctoRuta;
    }

    /**
     * @return the idDoctoCXC
     */
    public int getIdDoctoCXC() {
        return idDoctoCXC;
    }

    /**
     * @param idDoctoCXC the idDoctoCXC to set
     */
    public void setIdDoctoCXC(int idDoctoCXC) {
        this.idDoctoCXC = idDoctoCXC;
    }

    /**
     * @return the folioDoctoCXC
     */
    public String getFolioDoctoCXC() {
        return folioDoctoCXC;
    }

    /**
     * @param folioDoctoCXC the folioDoctoCXC to set
     */
    public void setFolioDoctoCXC(String folioDoctoCXC) {
        this.folioDoctoCXC = folioDoctoCXC;
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
     * @return the fechaVencimiento
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
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
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
