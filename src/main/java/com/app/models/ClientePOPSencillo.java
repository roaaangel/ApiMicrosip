
package com.app.models;

import java.sql.Date;

public class ClientePOPSencillo {
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
    private double importePOPGanadoAjustes;
    private String estatus;
    private Date fechaBloqueo;
    private double montoMinimoVenta;
    private int anio;
    private int trimestre;  
    private String nombreVendedor;

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
     * @return the importePOPGanadoAjustes
     */
    public double getImportePOPGanadoAjustes() {
        return importePOPGanadoAjustes;
    }

    /**
     * @param importePOPGanadoAjustes the importePOPGanadoAjustes to set
     */
    public void setImportePOPGanadoAjustes(double importePOPGanadoAjustes) {
        this.importePOPGanadoAjustes = importePOPGanadoAjustes;
    }

    /**
     * @return the estatus
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    /**
     * @return the fechaBloqueo
     */
    public Date getFechaBloqueo() {
        return fechaBloqueo;
    }

    /**
     * @param fechaBloqueo the fechaBloqueo to set
     */
    public void setFechaBloqueo(Date fechaBloqueo) {
        this.fechaBloqueo = fechaBloqueo;
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
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * @return the trimestre
     */
    public int getTrimestre() {
        return trimestre;
    }

    /**
     * @param trimestre the trimestre to set
     */
    public void setTrimestre(int trimestre) {
        this.trimestre = trimestre;
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
