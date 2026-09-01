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
public class CuentaBancaria {
    private int cuentaBancariaId;
    private int bancoId;
    private String nombreBanco;
    private String numeroCuenta;

    /**
     * @return the cuentaBancariaId
     */
    public int getCuentaBancariaId() {
        return cuentaBancariaId;
    }

    /**
     * @param cuentaBancariaId the cuentaBancariaId to set
     */
    public void setCuentaBancariaId(int cuentaBancariaId) {
        this.cuentaBancariaId = cuentaBancariaId;
    }

    /**
     * @return the bancoId
     */
    public int getBancoId() {
        return bancoId;
    }

    /**
     * @param bancoId the bancoId to set
     */
    public void setBancoId(int bancoId) {
        this.bancoId = bancoId;
    }

    /**
     * @return the nombreBanco
     */
    public String getNombreBanco() {
        return nombreBanco;
    }

    /**
     * @param nombreBanco the nombreBanco to set
     */
    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    /**
     * @return the numeroCuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * @param numeroCuenta the numeroCuenta to set
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
}
