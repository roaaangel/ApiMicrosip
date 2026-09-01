/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

import java.util.List;

/**
 *
 * @author burtebony
 */
public class CobroXDepositarEntity {
    private List<AbonoMaestroEntity> listaAbonosParaMicrosip;
    private List<DepositoMaestroEntity> listaDepositosParaMicrosip;  

    /**
     * @return the listaAbonosParaMicrosip
     */
    public List<AbonoMaestroEntity> getListaAbonosParaMicrosip() {
        return listaAbonosParaMicrosip;
    }

    /**
     * @param listaAbonosParaMicrosip the listaAbonosParaMicrosip to set
     */
    public void setListaAbonosParaMicrosip(List<AbonoMaestroEntity> listaAbonosParaMicrosip) {
        this.listaAbonosParaMicrosip = listaAbonosParaMicrosip;
    }

    /**
     * @return the listaDepositosParaMicrosip
     */
    public List<DepositoMaestroEntity> getListaDepositosParaMicrosip() {
        return listaDepositosParaMicrosip;
    }

    /**
     * @param listaDepositosParaMicrosip the listaDepositosParaMicrosip to set
     */
    public void setListaDepositosParaMicrosip(List<DepositoMaestroEntity> listaDepositosParaMicrosip) {
        this.listaDepositosParaMicrosip = listaDepositosParaMicrosip;
    }
}
