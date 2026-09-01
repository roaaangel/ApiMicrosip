/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

import java.math.BigInteger;

/**
 *
 * @author angel
 */
public class DocumentoRelacionado {
    private BigInteger parcialidad;
    private String uuid;    
    private String serie;
    private int folio;            
    private Double importeSaldoAnterior;            
    private String numeroOperacion;
    private String ctaOrdenante;
    private String nomBancoOrdExt;
    private String rfcEmisorCtaOrd;
    private String ctaBeneficiario;
    private String rfcEmisorCtaBen;

    /**
     * @return the parcialidad
     */
    public BigInteger getParcialidad() {
        return parcialidad;
    }

    /**
     * @param parcialidad the parcialidad to set
     */
    public void setParcialidad(BigInteger parcialidad) {
        this.parcialidad = parcialidad;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    /**
     * @return the importeSaldoAnterior
     */
    public Double getImporteSaldoAnterior() {
        return importeSaldoAnterior;
    }

    /**
     * @param importeSaldoAnterior the importeSaldoAnterior to set
     */
    public void setImporteSaldoAnterior(Double importeSaldoAnterior) {
        this.importeSaldoAnterior = importeSaldoAnterior;
    }

    /**
     * @return the numeroOperacion
     */
    public String getNumeroOperacion() {
        return numeroOperacion;
    }

    /**
     * @param numeroOperacion the numeroOperacion to set
     */
    public void setNumeroOperacion(String numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    /**
     * @return the ctaOrdenante
     */
    public String getCtaOrdenante() {
        return ctaOrdenante;
    }

    /**
     * @param ctaOrdenante the ctaOrdenante to set
     */
    public void setCtaOrdenante(String ctaOrdenante) {
        this.ctaOrdenante = ctaOrdenante;
    }

    /**
     * @return the nomBancoOrdExt
     */
    public String getNomBancoOrdExt() {
        return nomBancoOrdExt;
    }

    /**
     * @param nomBancoOrdExt the nomBancoOrdExt to set
     */
    public void setNomBancoOrdExt(String nomBancoOrdExt) {
        this.nomBancoOrdExt = nomBancoOrdExt;
    }

    /**
     * @return the rfcEmisorCtaOrd
     */
    public String getRfcEmisorCtaOrd() {
        return rfcEmisorCtaOrd;
    }

    /**
     * @param rfcEmisorCtaOrd the rfcEmisorCtaOrd to set
     */
    public void setRfcEmisorCtaOrd(String rfcEmisorCtaOrd) {
        this.rfcEmisorCtaOrd = rfcEmisorCtaOrd;
    }

    /**
     * @return the ctaBeneficiario
     */
    public String getCtaBeneficiario() {
        return ctaBeneficiario;
    }

    /**
     * @param ctaBeneficiario the ctaBeneficiario to set
     */
    public void setCtaBeneficiario(String ctaBeneficiario) {
        this.ctaBeneficiario = ctaBeneficiario;
    }

    /**
     * @return the rfcEmisorCtaBen
     */
    public String getRfcEmisorCtaBen() {
        return rfcEmisorCtaBen;
    }

    /**
     * @param rfcEmisorCtaBen the rfcEmisorCtaBen to set
     */
    public void setRfcEmisorCtaBen(String rfcEmisorCtaBen) {
        this.rfcEmisorCtaBen = rfcEmisorCtaBen;
    }
}
