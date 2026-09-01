/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

/**
 *
 * @author burtebony
 */
public class AbonoDetalleEntity {
    private int abonoMaestroId;
    private int doctoCCId;
    private int id;
    private Double importeAbono;
    private int idCobranza;    
    private String hora;
    private String formaPago;
    private int formaCobroCCId;
    private String fecha;       
    private Double abono;

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

    /**
     * @return the idCobranza
     */
    public int getIdCobranza() {
        return idCobranza;
    }

    /**
     * @param idCobranza the idCobranza to set
     */
    public void setIdCobranza(int idCobranza) {
        this.idCobranza = idCobranza;
    }

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
     * @return the formaPago
     */
    public String getFormaPago() {
        return formaPago;
    }

    /**
     * @param formaPago the formaPago to set
     */
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
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
     * @return the abono
     */
    public Double getAbono() {
        return abono;
    }

    /**
     * @param abono the abono to set
     */
    public void setAbono(Double abono) {
        this.abono = abono;
    }
}
