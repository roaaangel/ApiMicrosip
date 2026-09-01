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
public class CobroXDepositarModel {
    private List<AbonoMaestroModel> listaAbonosParaMicrosip;
    
    private SerieFolioCXC serieFolioCXC;

    /**
     * @return the listaAbonosParaMicrosip
     */
    public List<AbonoMaestroModel> getListaAbonosParaMicrosip() {
        return listaAbonosParaMicrosip;
    }

    /**
     * @param listaAbonosParaMicrosip the listaAbonosParaMicrosip to set
     */
    public void setListaAbonosParaMicrosip(List<AbonoMaestroModel> listaAbonosParaMicrosip) {
        this.listaAbonosParaMicrosip = listaAbonosParaMicrosip;
    }

    /**
     * @return the serieFolioCXC
     */
    public SerieFolioCXC getSerieFolioCXC() {
        return serieFolioCXC;
    }

    /**
     * @param serieFolioCXC the serieFolioCXC to set
     */
    public void setSerieFolioCXC(SerieFolioCXC serieFolioCXC) {
        this.serieFolioCXC = serieFolioCXC;
    }

}
