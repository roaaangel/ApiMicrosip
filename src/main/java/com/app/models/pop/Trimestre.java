/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.pop;

/**
 *
 * @author angel
 */
public class Trimestre {
    private int id;
    private String nombre;
    private int numero;
    private int diaEjecucion;
    private int mesEjecucion;

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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the diaEjecucion
     */
    public int getDiaEjecucion() {
        return diaEjecucion;
    }

    /**
     * @param diaEjecucion the diaEjecucion to set
     */
    public void setDiaEjecucion(int diaEjecucion) {
        this.diaEjecucion = diaEjecucion;
    }

    /**
     * @return the mesEjecucion
     */
    public int getMesEjecucion() {
        return mesEjecucion;
    }

    /**
     * @param mesEjecucion the mesEjecucion to set
     */
    public void setMesEjecucion(int mesEjecucion) {
        this.mesEjecucion = mesEjecucion;
    }
}
