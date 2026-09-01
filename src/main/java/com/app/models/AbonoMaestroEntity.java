/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

import java.util.List;

/**
 *
 * @author burtebony
 */
public class AbonoMaestroEntity {
    private List<AbonoDetalleEntity> AbonoDetalleEntity;
    private int id;
    private String horaAbono;
    private int formaCobroCCId;
    private String fechaAbono;
    private int cobradorId;
    private int clienteId;
    private String claveCliente;
    private Double abonoTotal;
    private String descripcion;
    private int sucursalId;
    private String fechaCRP;

    /**
     * @return the AbonoDetalleEntity
     */
    public List<AbonoDetalleEntity> getAbonoDetalleEntity() {
        return AbonoDetalleEntity;
    }

    /**
     * @param AbonoDetalleEntity the AbonoDetalleEntity to set
     */
    public void setAbonoDetalleEntity(List<AbonoDetalleEntity> AbonoDetalleEntity) {
        this.AbonoDetalleEntity = AbonoDetalleEntity;
    }

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
     * @return the horaAbono
     */
    public String getHoraAbono() {
        return horaAbono;
    }

    /**
     * @param horaAbono the horaAbono to set
     */
    public void setHoraAbono(String horaAbono) {
        this.horaAbono = horaAbono;
    }

    /**
     * @return the formaCobroCCId
     */
    public int getFormaCobroCCId() {
        return formaCobroCCId;
    }

    /**
     * @param formaCobroCCId the formaCobroCCId to set
     */
    public void setFormaCobroCCId(int formaCobroCCId) {
        this.formaCobroCCId = formaCobroCCId;
    }

    /**
     * @return the fechaAbono
     */
    public String getFechaAbono() {
        return fechaAbono;
    }

    /**
     * @param fechaAbono the fechaAbono to set
     */
    public void setFechaAbono(String fechaAbono) {
        this.fechaAbono = fechaAbono;
    }

    /**
     * @return the cobradorId
     */
    public int getCobradorId() {
        return cobradorId;
    }

    /**
     * @param cobradorId the cobradorId to set
     */
    public void setCobradorId(int cobradorId) {
        this.cobradorId = cobradorId;
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
     * @return the abonoTotal
     */
    public Double getAbonoTotal() {
        return abonoTotal;
    }

    /**
     * @param abonoTotal the abonoTotal to set
     */
    public void setAbonoTotal(Double abonoTotal) {
        this.abonoTotal = abonoTotal;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the sucursalId
     */
    public int getSucursalId() {
        return sucursalId;
    }

    /**
     * @param sucursalId the sucursalId to set
     */
    public void setSucursalId(int sucursalId) {
        this.sucursalId = sucursalId;
    }

    /**
     * @return the fechaCRP
     */
    public String getFechaCRP() {
        return fechaCRP;
    }

    /**
     * @param fechaCRP the fechaCRP to set
     */
    public void setFechaCRP(String fechaCRP) {
        this.fechaCRP = fechaCRP;
    }
}
