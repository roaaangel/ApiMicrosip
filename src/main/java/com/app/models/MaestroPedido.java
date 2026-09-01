/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 *
 * @author angel
 */
public class MaestroPedido {
    @SerializedName("A")
    private int id = 0;

    @SerializedName("B")
    private String uuid = "";

    @SerializedName("C")
    private String cotizacionEmiteFactura = "";

    @SerializedName("D")
    private String fechaPedido = "";

    @SerializedName("E")
    private String horaPedido = "";

    @SerializedName("F")
    private String claveCliente = "";

    @SerializedName("G")
    private int clienteId = 0;

    @SerializedName("H")
    private int direccionClienteId = 0;

    @SerializedName("I")
    private int direccionConsignatarioId = 0;

    @SerializedName("J")
    private int almacenId = 0;

    @SerializedName("K")
    private String observaciones = "";

    @SerializedName("L")
    private Double importeNeto = 0.00;

    @SerializedName("M")
    private Double totalImpuestos = 0.00;

    @SerializedName("N")
    private int condicionPagoId = 0;

    @SerializedName("O")
    private int vendedorId = 0;

    @SerializedName("P")
    private ArrayList<DetallePedido> listaDetallePedido;   

    @SerializedName("Q")
    private int direccionConsignatarioEnvioId;
    
    @SerializedName("R")
    private Boolean esPOP;
    
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
     * @return the cotizacionEmiteFactura
     */
    public String getCotizacionEmiteFactura() {
        return cotizacionEmiteFactura;
    }

    /**
     * @param cotizacionEmiteFactura the cotizacionEmiteFactura to set
     */
    public void setCotizacionEmiteFactura(String cotizacionEmiteFactura) {
        this.cotizacionEmiteFactura = cotizacionEmiteFactura;
    }

    /**
     * @return the fechaPedido
     */
    public String getFechaPedido() {
        return fechaPedido;
    }

    /**
     * @param fechaPedido the fechaPedido to set
     */
    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    /**
     * @return the horaPedido
     */
    public String getHoraPedido() {
        return horaPedido;
    }

    /**
     * @param horaPedido the horaPedido to set
     */
    public void setHoraPedido(String horaPedido) {
        this.horaPedido = horaPedido;
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
     * @return the direccionClienteId
     */
    public int getDireccionClienteId() {
        return direccionClienteId;
    }

    /**
     * @param direccionClienteId the direccionClienteId to set
     */
    public void setDireccionClienteId(int direccionClienteId) {
        this.direccionClienteId = direccionClienteId;
    }

    /**
     * @return the direccionConsignatarioId
     */
    public int getDireccionConsignatarioId() {
        return direccionConsignatarioId;
    }

    /**
     * @param direccionConsignatarioId the direccionConsignatarioId to set
     */
    public void setDireccionConsignatarioId(int direccionConsignatarioId) {
        this.direccionConsignatarioId = direccionConsignatarioId;
    }

    /**
     * @return the almacenId
     */
    public int getAlmacenId() {
        return almacenId;
    }

    /**
     * @param almacenId the almacenId to set
     */
    public void setAlmacenId(int almacenId) {
        this.almacenId = almacenId;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the importeNeto
     */
    public Double getImporteNeto() {
        return importeNeto;
    }

    /**
     * @param importeNeto the importeNeto to set
     */
    public void setImporteNeto(Double importeNeto) {
        this.importeNeto = importeNeto;
    }

    /**
     * @return the totalImpuestos
     */
    public Double getTotalImpuestos() {
        return totalImpuestos;
    }

    /**
     * @param totalImpuestos the totalImpuestos to set
     */
    public void setTotalImpuestos(Double totalImpuestos) {
        this.totalImpuestos = totalImpuestos;
    }

    /**
     * @return the condicionPagoId
     */
    public int getCondicionPagoId() {
        return condicionPagoId;
    }

    /**
     * @param condicionPagoId the condicionPagoId to set
     */
    public void setCondicionPagoId(int condicionPagoId) {
        this.condicionPagoId = condicionPagoId;
    }

    /**
     * @return the vendedorId
     */
    public int getVendedorId() {
        return vendedorId;
    }

    /**
     * @param vendedorId the vendedorId to set
     */
    public void setVendedorId(int vendedorId) {
        this.vendedorId = vendedorId;
    }

    /**
     * @return the listaDetallePedido
     */
    public ArrayList<DetallePedido> getListaDetallePedido() {
        return listaDetallePedido;
    }

    /**
     * @param listaDetallePedido the listaDetallePedido to set
     */
    public void setListaDetallePedido(ArrayList<DetallePedido> listaDetallePedido) {
        this.listaDetallePedido = listaDetallePedido;
    }

    /**
     * @return the direccionConsignatarioEnvioId
     */
    public int getDireccionConsignatarioEnvioId() {
        return direccionConsignatarioEnvioId;
    }

    /**
     * @param direccionConsignatarioEnvioId the direccionConsignatarioEnvioId to set
     */
    public void setDireccionConsignatarioEnvioId(int direccionConsignatarioEnvioId) {
        this.direccionConsignatarioEnvioId = direccionConsignatarioEnvioId;
    }

    /**
     * @return the esPOP
     */
    public Boolean getEsPOP() {
        return esPOP;
    }

    /**
     * @param esPOP the esPOP to set
     */
    public void setEsPOP(Boolean esPOP) {
        this.esPOP = esPOP;
    }
}
