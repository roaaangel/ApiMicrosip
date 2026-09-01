/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

import java.util.List;

/**
 *
 * @author angel
 */
public class MonedaHistoriaCambiaria {
    private List<Moneda> monedas;
    private List<HistoriaCambiaria> historiasCambiarias;

    /**
     * @return the monedas
     */
    public List<Moneda> getMonedas() {
        return monedas;
    }

    /**
     * @param monedas the monedas to set
     */
    public void setMonedas(List<Moneda> monedas) {
        this.monedas = monedas;
    }

    /**
     * @return the historiasCambiarias
     */
    public List<HistoriaCambiaria> getHistoriasCambiarias() {
        return historiasCambiarias;
    }

    /**
     * @param historiasCambiarias the historiasCambiarias to set
     */
    public void setHistoriasCambiarias(List<HistoriaCambiaria> historiasCambiarias) {
        this.historiasCambiarias = historiasCambiarias;
    }
}
