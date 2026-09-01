/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.models;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tron
 */
@XmlRootElement
public class PedidoGrabado {
    private int id;
    private int nummov;
    private String folio;

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
     * @return the nummov
     */
    public int getNummov() {
        return nummov;
    }

    /**
     * @param nummov the nummov to set
     */
    public void setNummov(int nummov) {
        this.nummov = nummov;
    }

    /**
     * @return the folio
     */
    public String getFolio() {
        return folio;
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }
}
