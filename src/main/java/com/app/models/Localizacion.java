/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author angel
 */
public class Localizacion {
    @SerializedName("id")
    private int id;

    @SerializedName("cliente_id")
    private int cliente_id;
    
    @SerializedName("latitud")
    private Double latitud;

    @SerializedName("longitud")
    private Double longitud;
    
    @SerializedName("fecha")
    private String fecha;
    
    @SerializedName("hora")
    private String hora;
    
    @SerializedName("operacion")
    private String proceso;
    
    @SerializedName("vendedor_id")
    private int vendedor_id;
    
    @SerializedName("monto_realizado")
    private Double monto_realizado;

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
     * @return the cliente_id
     */
    public int getCliente_id() {
        return cliente_id;
    }

    /**
     * @param cliente_id the cliente_id to set
     */
    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    /**
     * @return the latitud
     */
    public Double getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public Double getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
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
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the proceso
     */
    public String getProceso() {
        return proceso;
    }

    /**
     * @param proceso the proceso to set
     */
    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    /**
     * @return the vendedor_id
     */
    public int getVendedor_id() {
        return vendedor_id;
    }

    /**
     * @param vendedor_id the vendedor_id to set
     */
    public void setVendedor_id(int vendedor_id) {
        this.vendedor_id = vendedor_id;
    }

    /**
     * @return the monto_realizado
     */
    public Double getMonto_realizado() {
        return monto_realizado;
    }

    /**
     * @param monto_realizado the monto_realizado to set
     */
    public void setMonto_realizado(Double monto_realizado) {
        this.monto_realizado = monto_realizado;
    }
}
