/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

  
public class DepositoDetalleEntity {
    private int abonoId;
    private int depositoId;
    private int abonoMaestroId;    

    /**
     * @return the abonoId
     */
    public int getAbonoId() {
        return abonoId;
    }

    /**
     * @param abonoId the abonoId to set
     */
    public void setAbonoId(int abonoId) {
        this.abonoId = abonoId;
    }

    /**
     * @return the depositoId
     */
    public int getDepositoId() {
        return depositoId;
    }

    /**
     * @param depositoId the depositoId to set
     */
    public void setDepositoId(int depositoId) {
        this.depositoId = depositoId;
    }

    /**
     * @return the doctoCCId
     */
    public int getAbonoMaestroId() {
        return abonoMaestroId;
    }

    /**
     * @param doctoCCId the doctoCCId to set
     */
    public void setAbonoMaestroId(int abonoMaestroId) {
        this.abonoMaestroId = abonoMaestroId;
    }
}
