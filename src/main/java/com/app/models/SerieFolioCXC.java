/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author angel
 */
public class SerieFolioCXC {
    @SerializedName("id")
    private int id;

    @SerializedName("cobradorId")
    private int cobradorId;
    
    @SerializedName("serie")
    private String serie;

    @SerializedName("folio")
    private int folio;

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
     * @return the cobradorId
     */
    public int getCobradorId() {
        return cobradorId;
    }

    /**
     * @param cobradorId the cobradorId to set
     */
    public void setCobradorId(int cobradorId) {
        this.cobradorId = cobradorId;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the folio
     */
    public int getFolio() {
        return folio;
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(int folio) {
        this.folio = folio;
    }
}