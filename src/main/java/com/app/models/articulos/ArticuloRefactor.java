/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.articulos;

import com.google.gson.annotations.SerializedName;

/**
 *    private Double porcentajePromocion;
    
    private Boolean politicaXVolumen;
    
 * @author angel
 */
public class ArticuloRefactor {
    @SerializedName(value = "A")
    private int articuloId;
    @SerializedName(value = "B")
    private String nombreArticulo;
    @SerializedName(value = "C")
    private String codigoArticulo;
    @SerializedName(value = "D")
    private String claveArticulo;
    @SerializedName(value = "E")
    private String esJuego;    
    @SerializedName(value = "F")
    private Double factorVenta;
    @SerializedName(value = "G")
    private String unidadVenta;
    @SerializedName(value = "H")
    private Double porcentajeIva;
    @SerializedName(value = "I")
    private Double porcentajeIeps;
    @SerializedName(value = "J")
    private String impuestoUsar;
    @SerializedName(value = "K")
    private Double precio;
    @SerializedName(value = "L")
    private Double precioNeto;
    @SerializedName(value = "M")
    private int unidadMinimaVenta;
    @SerializedName(value = "N")
    private int piezasXInner;
    @SerializedName(value = "O")
    private int piezasXMaster;
    @SerializedName(value = "P")
    private String codigoBarras;
    @SerializedName(value = "Q")
    private String codigoBarrasInner;
    @SerializedName(value = "R")
    private String codigoBarrasMaster;
    @SerializedName(value = "S")
    private Boolean tieneDescuentoPromocion;
    @SerializedName(value = "T")
    private Double descuentoPromocion;
    @SerializedName(value = "U")
    private String esExclusivoPromocion;   
    @SerializedName(value = "V")
    private Boolean tieneDescuentoVolumen; 
    @SerializedName(value = "W")
    private int precioEmpresaId; 
    
    /*@SerializedName(value = "W")
    private Boolean descuentoVolumenAplicadoVolumen;
    @SerializedName(value = "X")
    private Double descuentoVolumen;
    @SerializedName(value = "Y")
    private int unidades;
    @SerializedName(value = "Z")
    private String esExclusivoVolumen;*/
    @SerializedName(value = "A1")
    private int monedaId; 
    @SerializedName(value = "A2")
    private String esPop; 

    /**
     * @return the articuloId
     */
    public int getArticuloId() {
        return articuloId;
    }

    /**
     * @param articuloId the articuloId to set
     */
    public void setArticuloId(int articuloId) {
        this.articuloId = articuloId;
    }

    /**
     * @return the nombreArticulo
     */
    public String getNombreArticulo() {
        return nombreArticulo;
    }

    /**
     * @param nombreArticulo the nombreArticulo to set
     */
    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    /**
     * @return the codigoArticulo
     */
    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    /**
     * @param codigoArticulo the codigoArticulo to set
     */
    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    /**
     * @return the claveArticulo
     */
    public String getClaveArticulo() {
        return claveArticulo;
    }

    /**
     * @param claveArticulo the claveArticulo to set
     */
    public void setClaveArticulo(String claveArticulo) {
        this.claveArticulo = claveArticulo;
    }

    /**
     * @return the esJuego
     */
    public String getEsJuego() {
        return esJuego;
    }

    /**
     * @param esJuego the esJuego to set
     */
    public void setEsJuego(String esJuego) {
        this.esJuego = esJuego;
    }

    /**
     * @return the factorVenta
     */
    public Double getFactorVenta() {
        return factorVenta;
    }

    /**
     * @param factorVenta the factorVenta to set
     */
    public void setFactorVenta(Double factorVenta) {
        this.factorVenta = factorVenta;
    }

    /**
     * @return the unidadVenta
     */
    public String getUnidadVenta() {
        return unidadVenta;
    }

    /**
     * @param unidadVenta the unidadVenta to set
     */
    public void setUnidadVenta(String unidadVenta) {
        this.unidadVenta = unidadVenta;
    }

    /**
     * @return the porcentajeIva
     */
    public Double getPorcentajeIva() {
        return porcentajeIva;
    }

    /**
     * @param porcentajeIva the porcentajeIva to set
     */
    public void setPorcentajeIva(Double porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    /**
     * @return the porcentajeIeps
     */
    public Double getPorcentajeIeps() {
        return porcentajeIeps;
    }

    /**
     * @param porcentajeIeps the porcentajeIeps to set
     */
    public void setPorcentajeIeps(Double porcentajeIeps) {
        this.porcentajeIeps = porcentajeIeps;
    }

    /**
     * @return the impuestoUsar
     */
    public String getImpuestoUsar() {
        return impuestoUsar;
    }

    /**
     * @param impuestoUsar the impuestoUsar to set
     */
    public void setImpuestoUsar(String impuestoUsar) {
        this.impuestoUsar = impuestoUsar;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * @return the precioNeto
     */
    public Double getPrecioNeto() {
        return precioNeto;
    }

    /**
     * @param precioNeto the precioNeto to set
     */
    public void setPrecioNeto(Double precioNeto) {
        this.precioNeto = precioNeto;
    }

    /**
     * @return the unidadMinimaVenta
     */
    public int getUnidadMinimaVenta() {
        return unidadMinimaVenta;
    }

    /**
     * @param unidadMinimaVenta the unidadMinimaVenta to set
     */
    public void setUnidadMinimaVenta(int unidadMinimaVenta) {
        this.unidadMinimaVenta = unidadMinimaVenta;
    }

    /**
     * @return the piezasXInner
     */
    public int getPiezasXInner() {
        return piezasXInner;
    }

    /**
     * @param piezasXInner the piezasXInner to set
     */
    public void setPiezasXInner(int piezasXInner) {
        this.piezasXInner = piezasXInner;
    }

    /**
     * @return the piezasXMaster
     */
    public int getPiezasXMaster() {
        return piezasXMaster;
    }

    /**
     * @param piezasXMaster the piezasXMaster to set
     */
    public void setPiezasXMaster(int piezasXMaster) {
        this.piezasXMaster = piezasXMaster;
    }

    /**
     * @return the codigoBarras
     */
    public String getCodigoBarras() {
        return codigoBarras;
    }

    /**
     * @param codigoBarras the codigoBarras to set
     */
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    /**
     * @return the codigoBarrasInner
     */
    public String getCodigoBarrasInner() {
        return codigoBarrasInner;
    }

    /**
     * @param codigoBarrasInner the codigoBarrasInner to set
     */
    public void setCodigoBarrasInner(String codigoBarrasInner) {
        this.codigoBarrasInner = codigoBarrasInner;
    }

    /**
     * @return the codigoBarrasMaster
     */
    public String getCodigoBarrasMaster() {
        return codigoBarrasMaster;
    }

    /**
     * @param codigoBarrasMaster the codigoBarrasMaster to set
     */
    public void setCodigoBarrasMaster(String codigoBarrasMaster) {
        this.codigoBarrasMaster = codigoBarrasMaster;
    }

    /**
     * @return the tieneDescuentoPromocion
     */
    public Boolean getTieneDescuentoPromocion() {
        return tieneDescuentoPromocion;
    }

    /**
     * @param tieneDescuentoPromocion the tieneDescuentoPromocion to set
     */
    public void setTieneDescuentoPromocion(Boolean tieneDescuentoPromocion) {
        this.tieneDescuentoPromocion = tieneDescuentoPromocion;
    }

    /**
     * @return the descuentoPromocion
     */
    public Double getDescuentoPromocion() {
        return descuentoPromocion;
    }

    /**
     * @param descuentoPromocion the descuentoPromocion to set
     */
    public void setDescuentoPromocion(Double descuentoPromocion) {
        this.descuentoPromocion = descuentoPromocion;
    }

    /**
     * @return the esExclusivoPromocion
     */
    public String getEsExclusivoPromocion() {
        return esExclusivoPromocion;
    }

    /**
     * @param esExclusivoPromocion the esExclusivoPromocion to set
     */
    public void setEsExclusivoPromocion(String esExclusivoPromocion) {
        this.esExclusivoPromocion = esExclusivoPromocion;
    }

    /**
     * @return the tieneDescuentoVolumen
     */
    public Boolean getTieneDescuentoVolumen() {
        return tieneDescuentoVolumen;
    }

    /**
     * @param tieneDescuentoVolumen the tieneDescuentoVolumen to set
     */
    public void setTieneDescuentoVolumen(Boolean tieneDescuentoVolumen) {
        this.tieneDescuentoVolumen = tieneDescuentoVolumen;
    }

    /**
     * @return the precioEmpresaId
     */
    public int getPrecioEmpresaId() {
        return precioEmpresaId;
    }

    /**
     * @param precioEmpresaId the precioEmpresaId to set
     */
    public void setPrecioEmpresaId(int precioEmpresaId) {
        this.precioEmpresaId = precioEmpresaId;
    }

    /**
     * @return the monedaId
     */
    public int getMonedaId() {
        return monedaId;
    }

    /**
     * @param monedaId the monedaId to set
     */
    public void setMonedaId(int monedaId) {
        this.monedaId = monedaId;
    }

    /**
     * @return the esPop
     */
    public String getEsPop() {
        return esPop;
    }

    /**
     * @param esPop the esPop to set
     */
    public void setEsPop(String esPop) {
        this.esPop = esPop;
    }
}