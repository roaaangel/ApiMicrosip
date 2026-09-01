/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

import java.util.List;

public class DepositoMaestroEntity {
    private int id;
    private String fecha;
    private int formaCobroCCId;
    private int cuentaBancariaId;
    private Double importe;
    private String fechaHoraCreacion;
    private String referencia;
    private String descripcion;
    private List<DepositoDetalleEntity> depositoDetalle;

    
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
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
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
     * @return the importe
     */
    public Double getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(Double importe) {
        this.importe = importe;
    }

    /**
     * @return the fechaCreacion
     */
    public String getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaHoraCreacion(String fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
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
     * @return the depositoDetalle
     */
    public List<DepositoDetalleEntity> getDepositoDetalle() {
        return depositoDetalle;
    }

    /**
     * @param depositoDetalle the depositoDetalle to set
     */
    public void setDepositoDetalle(List<DepositoDetalleEntity> depositoDetalle) {
        this.depositoDetalle = depositoDetalle;
    }
}
