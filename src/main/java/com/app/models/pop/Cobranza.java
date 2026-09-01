package com.app.models.pop;

import java.util.Date;
import java.sql.Time;

public class Cobranza {
    private int doctoCCId;
    private int conceptoCCId;
    private String folioDoctofuente;
    private Date fechaElaboracionDoctofuente;
    private Time horaElaboracionDoctofuente;
    private Date fechaUltimoPago;
    private int clienteId;
    private String claveCliente;
    private String nombreCliente;
    private double importeSinImpuestos;
    private double importeConImpuestos;
    private double saldoCargo;
    private double toalImpuestos;
    private double toalRetenciones;
    private String giroPOP;
    private int condicionPagoCCId;
    private String tipoCliente;
    private int diasPlazo;
    private int diasTardadosDoctoLiquidado;
    private double importePOPGanado;

    // Getters and Setters
    public int getDoctoCCId() {
        return doctoCCId;
    }

    public void setDoctoCCId(int doctoCCId) {
        this.doctoCCId = doctoCCId;
    }

    public int getConceptoCCId() {
        return conceptoCCId;
    }

    public void setConceptoCCId(int conceptoCCId) {
        this.conceptoCCId = conceptoCCId;
    }

    public String getFolioDoctofuente() {
        return folioDoctofuente;
    }

    public void setFolioDoctofuente(String folioDoctofuente) {
        this.folioDoctofuente = folioDoctofuente;
    }

    public Date getFechaElaboracionDoctofuente() {
        return fechaElaboracionDoctofuente;
    }

    public void setFechaElaboracionDoctofuente(Date fechaElaboracionDoctofuente) {
        this.fechaElaboracionDoctofuente = fechaElaboracionDoctofuente;
    }

    public Time getHoraElaboracionDoctofuente() {
        return horaElaboracionDoctofuente;
    }

    public void setHoraElaboracionDoctofuente(Time horaElaboracionDoctofuente) {
        this.horaElaboracionDoctofuente = horaElaboracionDoctofuente;
    }

    public Date getFechaUltimoPago() {
        return fechaUltimoPago;
    }

    public void setFechaUltimoPago(Date fechaUltimoPago) {
        this.fechaUltimoPago = fechaUltimoPago;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getClaveCliente() {
        return claveCliente;
    }

    public void setClaveCliente(String claveCliente) {
        this.claveCliente = claveCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public double getImporteSinImpuestos() {
        return importeSinImpuestos;
    }

    public void setImporteSinImpuestos(double importeSinImpuestos) {
        this.importeSinImpuestos = importeSinImpuestos;
    }

    public double getImporteConImpuestos() {
        return importeConImpuestos;
    }

    public void setImporteConImpuestos(double importeConImpuestos) {
        this.importeConImpuestos = importeConImpuestos;
    }

    public double getSaldoCargo() {
        return saldoCargo;
    }

    public void setSaldoCargo(double saldoCargo) {
        this.saldoCargo = saldoCargo;
    }

    public double getToalImpuestos() {
        return toalImpuestos;
    }

    public void setToalImpuestos(double toalImpuestos) {
        this.toalImpuestos = toalImpuestos;
    }

    public double getToalRetenciones() {
        return toalRetenciones;
    }

    public void setToalRetenciones(double toalRetenciones) {
        this.toalRetenciones = toalRetenciones;
    }

    public String getGiroPOP() {
        return giroPOP;
    }

    public void setGiroPOP(String giroPOP) {
        this.giroPOP = giroPOP;
    }

    public int getCondicionPagoCCId() {
        return condicionPagoCCId;
    }

    public void setCondicionPagoCCId(int condicionPagoCCId) {
        this.condicionPagoCCId = condicionPagoCCId;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public int getDiasPlazo() {
        return diasPlazo;
    }

    public void setDiasPlazo(int diasPlazo) {
        this.diasPlazo = diasPlazo;
    }

    public int getDiasTardadosDoctoLiquidado() {
        return diasTardadosDoctoLiquidado;
    }

    public void setDiasTardadosDoctoLiquidado(int diasTardadosDoctoLiquidado) {
        this.diasTardadosDoctoLiquidado = diasTardadosDoctoLiquidado;
    }

    public double getImportePOPGanado() {
        return importePOPGanado;
    }

    public void setImportePOPGanado(double importePOPGanado) {
        this.importePOPGanado = importePOPGanado;
    }
}
