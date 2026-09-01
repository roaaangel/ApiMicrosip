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
public class ComplementoXmlDetalle {
    private int doctoCCPadreId;
    private Double importeAbono;

    /**
     * @return the doctoCCPadreId
     */
    public int getDoctoCCPadreId() {
        return doctoCCPadreId;
    }

    /**
     * @param doctoCCPadreId the doctoCCPadreId to set
     */
    public void setDoctoCCPadreId(int doctoCCPadreId) {
        this.doctoCCPadreId = doctoCCPadreId;
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
