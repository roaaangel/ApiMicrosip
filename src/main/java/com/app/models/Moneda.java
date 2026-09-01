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
public class Moneda {
    private int monedaId;
    private String nombre;
    private String textoImporteNombre;
    private String simbolo;
    private String claveFiscal;
    private int decimalesSoportados;

    /**
     * @return the monedaId
     */
    public int getMonedaId() {
        return monedaId;
    }

    /**
     * @param monedaId the monedaId to set
     */
    public void setMonedaId(int monedaId) {
        this.monedaId = monedaId;
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
     * @return the textoImporteNombre
     */
    public String getTextoImporteNombre() {
        return textoImporteNombre;
    }

    /**
     * @param textoImporteNombre the textoImporteNombre to set
     */
    public void setTextoImporteNombre(String textoImporteNombre) {
        this.textoImporteNombre = textoImporteNombre;
    }

    /**
     * @return the simbolo
     */
    public String getSimbolo() {
        return simbolo;
    }

    /**
     * @param simbolo the simbolo to set
     */
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    /**
     * @return the claveFiscal
     */
    public String getClaveFiscal() {
        return claveFiscal;
    }

    /**
     * @param claveFiscal the claveFiscal to set
     */
    public void setClaveFiscal(String claveFiscal) {
        this.claveFiscal = claveFiscal;
    }

    /**
     * @return the decimalesSoportados
     */
    public int getDecimalesSoportados() {
        return decimalesSoportados;
    }

    /**
     * @param decimalesSoportados the decimalesSoportados to set
     */
    public void setDecimalesSoportados(int decimalesSoportados) {
        this.decimalesSoportados = decimalesSoportados;
    }
}
