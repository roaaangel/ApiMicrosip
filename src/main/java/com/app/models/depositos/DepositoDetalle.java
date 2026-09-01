/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.depositos;

/**
 *
 * @author burtebony
 */
public class DepositoDetalle {
    private int doctoCCId;
    private int depositoId;    

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
}
