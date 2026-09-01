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
public class DetallePedido {
    @SerializedName("artId")
    private int articulo_id;

    @SerializedName("claArt")
    private String clave_articulo;

    @SerializedName("esJue")
    private String es_juego;

    @SerializedName("can")
    private Double unidades;

    @SerializedName("preUni")
    private Double precio_unitario_sin_impuestos;

    @SerializedName("porDesProVol")
    private Double porcentaje_descuento_promocion_volumen;

    @SerializedName("porDesArtCli")
    private Double porcentaje_descuento_articulo_cliente;
    
    @SerializedName("porIva")
    private Double porcentaje_iva;

    @SerializedName("porIeps")
    private Double porcentaje_ieps;


    @SerializedName("tipPol")
    private String tipo_politica;

    @SerializedName("preUniCDes")
    private Double precio_unitario_con_descuento_sin_impuestos;
    
    @SerializedName("esPOP")
    private String esPOP;
    
    @SerializedName("porDesPOP")
    private Double porcentajeDescuentoPOP;
    
    @SerializedName("precioUCI")
    private Double precioUnitarioConImpuestos;
    
    /**
     * @return the articulo_id
     */
    public int getArticulo_id() {
        return articulo_id;
    }

    /**
     * @param articulo_id the articulo_id to set
     */
    public void setArticulo_id(int articulo_id) {
        this.articulo_id = articulo_id;
    }

    /**
     * @return the clave_articulo
     */
    public String getClave_articulo() {
        return clave_articulo;
    }

    /**
     * @param clave_articulo the clave_articulo to set
     */
    public void setClave_articulo(String clave_articulo) {
        this.clave_articulo = clave_articulo;
    }

    /**
     * @return the es_juego
     */
    public String getEs_juego() {
        return es_juego;
    }

    /**
     * @param es_juego the es_juego to set
     */
    public void setEs_juego(String es_juego) {
        this.es_juego = es_juego;
    }

    /**
     * @return the unidades
     */
    public Double getUnidades() {
        return unidades;
    }

    /**
     * @param unidades the unidades to set
     */
    public void setUnidades(Double unidades) {
        this.unidades = unidades;
    }

    /**
     * @return the precio_unitario_sin_impuestos
     */
    public Double getPrecio_unitario_sin_impuestos() {
        return precio_unitario_sin_impuestos;
    }

    /**
     * @param precio_unitario_sin_impuestos the precio_unitario_sin_impuestos to set
     */
    public void setPrecio_unitario_sin_impuestos(Double precio_unitario_sin_impuestos) {
        this.precio_unitario_sin_impuestos = precio_unitario_sin_impuestos;
    }

    /**
     * @return the porcentaje_descuento_promocion_volumen
     */
    public Double getPorcentaje_descuento_promocion_volumen() {
        return porcentaje_descuento_promocion_volumen;
    }

    /**
     * @param porcentaje_descuento_promocion_volumen the porcentaje_descuento_promocion_volumen to set
     */
    public void setPorcentaje_descuento_promocion_volumen(Double porcentaje_descuento_promocion_volumen) {
        this.porcentaje_descuento_promocion_volumen = porcentaje_descuento_promocion_volumen;
    }

    /**
     * @return the porcentaje_descuento_articulo_cliente
     */
    public Double getPorcentaje_descuento_articulo_cliente() {
        return porcentaje_descuento_articulo_cliente;
    }

    /**
     * @param porcentaje_descuento_articulo_cliente the porcentaje_descuento_articulo_cliente to set
     */
    public void setPorcentaje_descuento_articulo_cliente(Double porcentaje_descuento_articulo_cliente) {
        this.porcentaje_descuento_articulo_cliente = porcentaje_descuento_articulo_cliente;
    }

    /**
     * @return the porcentaje_iva
     */
    public Double getPorcentaje_iva() {
        return porcentaje_iva;
    }

    /**
     * @param porcentaje_iva the porcentaje_iva to set
     */
    public void setPorcentaje_iva(Double porcentaje_iva) {
        this.porcentaje_iva = porcentaje_iva;
    }

    /**
     * @return the porcentaje_ieps
     */
    public Double getPorcentaje_ieps() {
        return porcentaje_ieps;
    }

    /**
     * @param porcentaje_ieps the porcentaje_ieps to set
     */
    public void setPorcentaje_ieps(Double porcentaje_ieps) {
        this.porcentaje_ieps = porcentaje_ieps;
    }

    /**
     * @return the tipo_politica
     */
    public String getTipo_politica() {
        return tipo_politica;
    }

    /**
     * @param tipo_politica the tipo_politica to set
     */
    public void setTipo_politica(String tipo_politica) {
        this.tipo_politica = tipo_politica;
    }

    /**
     * @return the precio_unitario_con_descuento_sin_impuestos
     */
    public Double getPrecio_unitario_con_descuento_sin_impuestos() {
        return precio_unitario_con_descuento_sin_impuestos;
    }

    /**
     * @param precio_unitario_con_descuento_sin_impuestos the precio_unitario_con_descuento_sin_impuestos to set
     */
    public void setPrecio_unitario_con_descuento_sin_impuestos(Double precio_unitario_con_descuento_sin_impuestos) {
        this.precio_unitario_con_descuento_sin_impuestos = precio_unitario_con_descuento_sin_impuestos;
    }

    /**
     * @return the esPOP
     */
    public String getEsPOP() {
        return esPOP;
    }

    /**
     * @param esPOP the esPOP to set
     */
    public void setEsPOP(String esPOP) {
        this.esPOP = esPOP;
    }

    /**
     * @return the porcentajeDescuentoPOP
     */
    public Double getPorcentajeDescuentoPOP() {
        return porcentajeDescuentoPOP;
    }

    /**
     * @param porcentajeDescuentoPOP the porcentajeDescuentoPOP to set
     */
    public void setPorcentajeDescuentoPOP(Double porcentajeDescuentoPOP) {
        this.porcentajeDescuentoPOP = porcentajeDescuentoPOP;
    }

    /**
     * @return the precioUnitarioConImpuestos
     */
    public Double getPrecioUnitarioConImpuestos() {
        return precioUnitarioConImpuestos;
    }

    /**
     * @param precioUnitarioConImpuestos the precioUnitarioConImpuestos to set
     */
    public void setPrecioUnitarioConImpuestos(Double precioUnitarioConImpuestos) {
        this.precioUnitarioConImpuestos = precioUnitarioConImpuestos;
    }
}
