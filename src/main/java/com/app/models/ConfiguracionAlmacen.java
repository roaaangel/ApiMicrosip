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
public class ConfiguracionAlmacen {
    private int almacenId;
    private int esDefault;

    /**
     * @return the almacenId
     */
    public int getAlmacenId() {
        return almacenId;
    }

    /**
     * @param almacenId the almacenId to set
     */
    public void setAlmacenId(int almacenId) {
        this.almacenId = almacenId;
    }

    /**
     * @return the esDefault
     */
    public int getEsDefault() {
        return esDefault;
    }

    /**
     * @param esDefault the esDefault to set
     */
    public void setEsDefault(int esDefault) {
        this.esDefault = esDefault;
    }
}
