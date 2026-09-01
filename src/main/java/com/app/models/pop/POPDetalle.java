/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.pop;

public class POPDetalle {
    private int id;
    private int popTrimestralId;
    private int doctoCCId;
    private String folioDoctofuente;
    private String fechaElaboracionDoctofuente;
    private String horaElaboracionDoctofuente;
    private String fechaUltimoPago;
    private double importeSinImpuestos;
    private double importeConImpuestos;
    private double importeDoctoFteSinImpuestos;

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
     * @return the popTrimestralId
     */
    public int getPopTrimestralId() {
        return popTrimestralId;
    }

    /**
     * @param popTrimestralId the popTrimestralId to set
     */
    public void setPopTrimestralId(int popTrimestralId) {
        this.popTrimestralId = popTrimestralId;
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
     * @return the folioDoctofuente
     */
    public String getFolioDoctofuente() {
        return folioDoctofuente;
    }

    /**
     * @param folioDoctofuente the folioDoctofuente to set
     */
    public void setFolioDoctofuente(String folioDoctofuente) {
        this.folioDoctofuente = folioDoctofuente;
    }

    /**
     * @return the fechaElaboracionDoctofuente
     */
    public String getFechaElaboracionDoctofuente() {
        return fechaElaboracionDoctofuente;
    }

    /**
     * @param fechaElaboracionDoctofuente the fechaElaboracionDoctofuente to set
     */
    public void setFechaElaboracionDoctofuente(String fechaElaboracionDoctofuente) {
        this.fechaElaboracionDoctofuente = fechaElaboracionDoctofuente;
    }

    /**
     * @return the horaElaboracionDoctofuente
     */
    public String getHoraElaboracionDoctofuente() {
        return horaElaboracionDoctofuente;
    }

    /**
     * @param horaElaboracionDoctofuente the horaElaboracionDoctofuente to set
     */
    public void setHoraElaboracionDoctofuente(String horaElaboracionDoctofuente) {
        this.horaElaboracionDoctofuente = horaElaboracionDoctofuente;
    }

    /**
     * @return the fechaUltimoPago
     */
    public String getFechaUltimoPago() {
        return fechaUltimoPago;
    }

    /**
     * @param fechaUltimoPago the fechaUltimoPago to set
     */
    public void setFechaUltimoPago(String fechaUltimoPago) {
        this.fechaUltimoPago = fechaUltimoPago;
    }

    /**
     * @return the importeSinImpuestos
     */
    public double getImporteSinImpuestos() {
        return importeSinImpuestos;
    }

    /**
     * @param importeSinImpuestos the importeSinImpuestos to set
     */
    public void setImporteSinImpuestos(double importeSinImpuestos) {
        this.importeSinImpuestos = importeSinImpuestos;
    }

    /**
     * @return the importeConImpuestos
     */
    public double getImporteConImpuestos() {
        return importeConImpuestos;
    }

    /**
     * @param importeConImpuestos the importeConImpuestos to set
     */
    public void setImporteConImpuestos(double importeConImpuestos) {
        this.importeConImpuestos = importeConImpuestos;
    }

    /**
     * @return the importeDoctoFteSinImpuestos
     */
    public double getImporteDoctoFteSinImpuestos() {
        return importeDoctoFteSinImpuestos;
    }

    /**
     * @param importeDoctoFteSinImpuestos the importeDoctoFteSinImpuestos to set
     */
    public void setImporteDoctoFteSinImpuestos(double importeDoctoFteSinImpuestos) {
        this.importeDoctoFteSinImpuestos = importeDoctoFteSinImpuestos;
    }
}
