/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.pop;

import java.util.List;

public class ClientePOP {
    private int id;
    private int clienteId;
    private String claveCliente;
    private String nombreCliente;
    private double importeSinImpuestos;
    private double importeConImpuestos;
    private double importeDoctoFteSinImpuestos;
    private String tipoCliente;
    private int diasPlazo;
    private int sumatoriaDiasTardadosDocumentos;
    private int numeroDocumentos;
    private double promedioPonderado;
    private double importePOPGanado;
    private double montoMinimoVenta;
    private String nombreVendedor;
    private List<POPDetalle> listaCobranzaXCliente;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the claveCliente
     */
    public String getClaveCliente() {
        return claveCliente;
    }

    /**
     * @param claveCliente the claveCliente to set
     */
    public void setClaveCliente(String claveCliente) {
        this.claveCliente = claveCliente;
    }

    /**
     * @return the nombreCliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * @param nombreCliente the nombreCliente to set
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * @return the importeSinImpuestos
     */
    public double getImporteSinImpuestos() {
        return importeSinImpuestos;
    }

    /**
     * @param importeSinImpuestos the importeSinImpuestos to set
     */
    public void setImporteSinImpuestos(double importeSinImpuestos) {
        this.importeSinImpuestos = importeSinImpuestos;
    }

    /**
     * @return the importeConImpuestos
     */
    public double getImporteConImpuestos() {
        return importeConImpuestos;
    }

    /**
     * @param importeConImpuestos the importeConImpuestos to set
     */
    public void setImporteConImpuestos(double importeConImpuestos) {
        this.importeConImpuestos = importeConImpuestos;
    }

    /**
     * @return the importeDoctoFteSinImpuestos
     */
    public double getImporteDoctoFteSinImpuestos() {
        return importeDoctoFteSinImpuestos;
    }

    /**
     * @param importeDoctoFteSinImpuestos the importeDoctoFteSinImpuestos to set
     */
    public void setImporteDoctoFteSinImpuestos(double importeDoctoFteSinImpuestos) {
        this.importeDoctoFteSinImpuestos = importeDoctoFteSinImpuestos;
    }

    /**
     * @return the tipoCliente
     */
    public String getTipoCliente() {
        return tipoCliente;
    }

    /**
     * @param tipoCliente the tipoCliente to set
     */
    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    /**
     * @return the diasPlazo
     */
    public int getDiasPlazo() {
        return diasPlazo;
    }

    /**
     * @param diasPlazo the diasPlazo to set
     */
    public void setDiasPlazo(int diasPlazo) {
        this.diasPlazo = diasPlazo;
    }

    /**
     * @return the sumatoriaDiasTardadosDocumentos
     */
    public int getSumatoriaDiasTardadosDocumentos() {
        return sumatoriaDiasTardadosDocumentos;
    }

    /**
     * @param sumatoriaDiasTardadosDocumentos the sumatoriaDiasTardadosDocumentos to set
     */
    public void setSumatoriaDiasTardadosDocumentos(int sumatoriaDiasTardadosDocumentos) {
        this.sumatoriaDiasTardadosDocumentos = sumatoriaDiasTardadosDocumentos;
    }

    /**
     * @return the numeroDocumentos
     */
    public int getNumeroDocumentos() {
        return numeroDocumentos;
    }

    /**
     * @param numeroDocumentos the numeroDocumentos to set
     */
    public void setNumeroDocumentos(int numeroDocumentos) {
        this.numeroDocumentos = numeroDocumentos;
    }

    /**
     * @return the promedioPonderado
     */
    public double getPromedioPonderado() {
        return promedioPonderado;
    }

    /**
     * @param promedioPonderado the promedioPonderado to set
     */
    public void setPromedioPonderado(double promedioPonderado) {
        this.promedioPonderado = promedioPonderado;
    }

    /**
     * @return the importePOPGanado
     */
    public double getImportePOPGanado() {
        return importePOPGanado;
    }

    /**
     * @param importePOPGanado the importePOPGanado to set
     */
    public void setImportePOPGanado(double importePOPGanado) {
        this.importePOPGanado = importePOPGanado;
    }

    /**
     * @return the listaCobranzaXCliente
     */
    public List<POPDetalle> getListaCobranzaXCliente() {
        return listaCobranzaXCliente;
    }

    /**
     * @param listaCobranzaXCliente the listaCobranzaXCliente to set
     */
    public void setListaCobranzaXCliente(List<POPDetalle> listaCobranzaXCliente) {
        this.listaCobranzaXCliente = listaCobranzaXCliente;
    }

    /**
     * @return the montoMinimoVenta
     */
    public double getMontoMinimoVenta() {
        return montoMinimoVenta;
    }

    /**
     * @param montoMinimoVenta the montoMinimoVenta to set
     */
    public void setMontoMinimoVenta(double montoMinimoVenta) {
        this.montoMinimoVenta = montoMinimoVenta;
    }

    /**
     * @return the nombreVendedor
     */
    public String getNombreVendedor() {
        return nombreVendedor;
    }

    /**
     * @param nombreVendedor the nombreVendedor to set
     */
    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }
}