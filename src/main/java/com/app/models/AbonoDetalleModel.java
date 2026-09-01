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
public class AbonoDetalleModel {
    private int id;
    private int abonoMaestroId;
    private int doctoCCId;    
    private Double importeAbono;

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
     * @return the abonoMaestroId
     */
    public int getAbonoMaestroId() {
        return abonoMaestroId;
    }

    /**
     * @param abonoMaestroId the abonoMaestroId to set
     */
    public void setAbonoMaestroId(int abonoMaestroId) {
        this.abonoMaestroId = abonoMaestroId;
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
     * @return the importeAbono
     */
    public Double getImporteAbono() {
        return importeAbono;
    }

    /**
     * @param importeAbono the importeAbono to set
     */
    public void setImporteAbono(Double importeAbono) {
        this.importeAbono = importeAbono;
    }
}
