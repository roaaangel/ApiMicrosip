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
public class Metadata {
    private int versionDB;
    private String fechaHoraCreacion;
    private int pasoActual;

    /**
     * @return the versionDB
     */
    public int getVersionDB() {
        return versionDB;
    }

    /**
     * @param versionDB the versionDB to set
     */
    public void setVersionDB(int versionDB) {
        this.versionDB = versionDB;
    }

    /**
     * @return the fechaHoraCreacion
     */
    public String getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    /**
     * @param fechaHoraCreacion the fechaHoraCreacion to set
     */
    public void setFechaHoraCreacion(String fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    /**
     * @return the pasoActual
     */
    public int getPasoActual() {
        return pasoActual;
    }

    /**
     * @param pasoActual the pasoActual to set
     */
    public void setPasoActual(int pasoActual) {
        this.pasoActual = pasoActual;
    }
}
