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
public class EquivalenciaAbonoMicrosip {
    private int abonoMaestroId;
    private int doctoCCId;

    /**
     * @return the abonoMaestroId
     */
    public int getAbonoMaestroId() {
        return abonoMaestroId;
    }

    /**
     * @param abonoMaestroId the abonoMaestroId to set
     */
    public void setAbonoMaestroId(int abonoMaestroId) {
        this.abonoMaestroId = abonoMaestroId;
    }

    /**
     * @return the doctoCCId
     */
    public int getDoctoCCId() {
        return doctoCCId;
    }

    /**
     * @param doctoCCId the doctoCCId to set
     */
    public void setDoctoCCId(int doctoCCId) {
        this.doctoCCId = doctoCCId;
    }
}
