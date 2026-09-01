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
public class PorcentajeTrimestral {
    private int id;
    private Double rangoInicial;
    private Double rangoFinal;
    private Double porcentaje;

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
     * @return the rangoInicial
     */
    public Double getRangoInicial() {
        return rangoInicial;
    }

    /**
     * @param rangoInicial the rangoInicial to set
     */
    public void setRangoInicial(Double rangoInicial) {
        this.rangoInicial = rangoInicial;
    }

    /**
     * @return the rangoFinal
     */
    public Double getRangoFinal() {
        return rangoFinal;
    }

    /**
     * @param rangoFinal the rangoFinal to set
     */
    public void setRangoFinal(Double rangoFinal) {
        this.rangoFinal = rangoFinal;
    }

    /**
     * @return the porcentaje
     */
    public Double getPorcentaje() {
        return porcentaje;
    }

    /**
     * @param porcentaje the porcentaje to set
     */
    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }
}
