/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.depositos;

import java.util.List;

/**
 *
 * @author burtebony
 */
public class Deposito {
    private List<DepositoMaestro> listaDepositosParaMicrosip;

    /**
     * @return the listaDepositosParaMicrosip
     */
    public List<DepositoMaestro> getListaDepositosParaMicrosip() {
        return listaDepositosParaMicrosip;
    }

    /**
     * @param listaDepositosParaMicrosip the listaDepositosParaMicrosip to set
     */
    public void setListaDepositosParaMicrosip(List<DepositoMaestro> listaDepositosParaMicrosip) {
        this.listaDepositosParaMicrosip = listaDepositosParaMicrosip;
    }
}
