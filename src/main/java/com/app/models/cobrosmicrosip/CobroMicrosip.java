/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.cobrosmicrosip;

/**
 *
 * @author burtebony
 */
public class CobroMicrosip {
    private int doctoCCId;
    private String fechaAbono;
    private String horaAbono;
    private String nombreCliente;
    private int formaCobroCCId;
    private Double abonoTotal;

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
     * @return the fechaAbono
     */
    public String getFechaAbono() {
        return fechaAbono;
    }

    /**
     * @param fechaAbono the fechaAbono to set
     */
    public void setFechaAbono(String fechaAbono) {
        this.fechaAbono = fechaAbono;
    }

    /**
     * @return the horaAbono
     */
    public String getHoraAbono() {
        return horaAbono;
    }

    /**
     * @param horaAbono the horaAbono to set
     */
    public void setHoraAbono(String horaAbono) {
        this.horaAbono = horaAbono;
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
     * @return the formaCobroCCId
     */
    public int getFormaCobroCCId() {
        return formaCobroCCId;
    }

    /**
     * @param formaCobroCCId the formaCobroCCId to set
     */
    public void setFormaCobroCCId(int formaCobroCCId) {
        this.formaCobroCCId = formaCobroCCId;
    }

    /**
     * @return the abonoTotal
     */
    public Double getAbonoTotal() {
        return abonoTotal;
    }

    /**
     * @param abonoTotal the abonoTotal to set
     */
    public void setAbonoTotal(Double abonoTotal) {
        this.abonoTotal = abonoTotal;
    }
}
