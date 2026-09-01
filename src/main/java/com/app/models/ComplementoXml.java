/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author angel
 */
public class ComplementoXml {
    private int doctoCCId;
    private int folioFiscalId;
    private String serieConceptoCC;
    private int folio;
    private int clienteId;
    private Date fechaDate;
    private XMLGregorianCalendar fechaHoraEnvioGregorian;
    private Timestamp fechaHoraEnvioTimestamp;
    private int formaCobroId;
    private Double importeTotal;
    private int cuentaBancariaEmpresaId;
    private List<ComplementoXmlDetalle> listaComplementoXmlDetalle;
    private String requiereComplementoDePagos;

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
     * @return the folioFiscalId
     */
    public int getFolioFiscalId() {
        return folioFiscalId;
    }

    /**
     * @param folioFiscalId the folioFiscalId to set
     */
    public void setFolioFiscalId(int folioFiscalId) {
        this.folioFiscalId = folioFiscalId;
    }

    /**
     * @return the serieConceptoCC
     */
    public String getSerieConceptoCC() {
        return serieConceptoCC;
    }

    /**
     * @param serieConceptoCC the serieConceptoCC to set
     */
    public void setSerieConceptoCC(String serieConceptoCC) {
        this.serieConceptoCC = serieConceptoCC;
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
     * @return the clienteId
     */
    public int getClienteId() {
        return clienteId;
    }

    /**
     * @param clienteId the clienteId to set
     */
    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * @return the fechaDate
     */
    public Date getFechaDate() {
        return fechaDate;
    }

    /**
     * @param fechaDate the fechaDate to set
     */
    public void setFechaDate(Date fechaDate) {
        this.fechaDate = fechaDate;
    }

    /**
     * @return the fechaHoraEnvioGregorian
     */
    public XMLGregorianCalendar getFechaHoraEnvioGregorian() {
        return fechaHoraEnvioGregorian;
    }

    /**
     * @param fechaHoraEnvioGregorian the fechaHoraEnvioGregorian to set
     */
    public void setFechaHoraEnvioGregorian(XMLGregorianCalendar fechaHoraEnvioGregorian) {
        this.fechaHoraEnvioGregorian = fechaHoraEnvioGregorian;
    }

    /**
     * @return the fechaHoraEnvioTimestamp
     */
    public Timestamp getFechaHoraEnvioTimestamp() {
        return fechaHoraEnvioTimestamp;
    }

    /**
     * @param fechaHoraEnvioTimestamp the fechaHoraEnvioTimestamp to set
     */
    public void setFechaHoraEnvioTimestamp(Timestamp fechaHoraEnvioTimestamp) {
        this.fechaHoraEnvioTimestamp = fechaHoraEnvioTimestamp;
    }

    /**
     * @return the formaCobroId
     */
    public int getFormaCobroId() {
        return formaCobroId;
    }

    /**
     * @param formaCobroId the formaCobroId to set
     */
    public void setFormaCobroId(int formaCobroId) {
        this.formaCobroId = formaCobroId;
    }

    /**
     * @return the importeTotal
     */
    public Double getImporteTotal() {
        return importeTotal;
    }

    /**
     * @param importeTotal the importeTotal to set
     */
    public void setImporteTotal(Double importeTotal) {
        this.importeTotal = importeTotal;
    }

    /**
     * @return the cuentaBancariaEmpresaId
     */
    public int getCuentaBancariaEmpresaId() {
        return cuentaBancariaEmpresaId;
    }

    /**
     * @param cuentaBancariaEmpresaId the cuentaBancariaEmpresaId to set
     */
    public void setCuentaBancariaEmpresaId(int cuentaBancariaEmpresaId) {
        this.cuentaBancariaEmpresaId = cuentaBancariaEmpresaId;
    }

    /**
     * @return the listaComplementoXmlDetalle
     */
    public List<ComplementoXmlDetalle> getListaComplementoXmlDetalle() {
        return listaComplementoXmlDetalle;
    }

    /**
     * @param listaComplementoXmlDetalle the listaComplementoXmlDetalle to set
     */
    public void setListaComplementoXmlDetalle(List<ComplementoXmlDetalle> listaComplementoXmlDetalle) {
        this.listaComplementoXmlDetalle = listaComplementoXmlDetalle;
    }

    /**
     * @return the requiereComplementoDePagos
     */
    public String getRequiereComplementoDePagos() {
        return requiereComplementoDePagos;
    }

    /**
     * @param requiereComplementoDePagos the requiereComplementoDePagos to set
     */
    public void setRequiereComplementoDePagos(String requiereComplementoDePagos) {
        this.requiereComplementoDePagos = requiereComplementoDePagos;
    }
}
