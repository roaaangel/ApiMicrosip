/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.articulos;

import java.util.List;

/**
 *
 * @author burtebony
 */
public class Articulo {
    private int articuloId;
    private String nombreArticulo;
    private String codigoArticulo;
    private String claveArticulo;
    private Double factorVenta;
    private String unidadVenta;
    private Double porcentajeIva;
    private Double porcentajeIeps;
    private String impuestoUsar;
    private Double precio;
    private Double porcentajePromocion;
    private Double precioNeto;
    private Double unidadMinimaVenta;
    private Double piezasXInner;
    private Double piezasXMaster;
    private String codigoBarras;
    private String codigoBarrasInner;
    private String codigoBarrasMaster;
    private Boolean politicaXVolumen;
    private List<ArticuloVolumen> listaArticuloVolumen;

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
     * @return the porcentajePromocion
     */
    public Double getPorcentajePromocion() {
        return porcentajePromocion;
    }

    /**
     * @param porcentajePromocion the porcentajePromocion to set
     */
    public void setPorcentajePromocion(Double porcentajePromocion) {
        this.porcentajePromocion = porcentajePromocion;
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
    public Double getUnidadMinimaVenta() {
        return unidadMinimaVenta;
    }

    /**
     * @param unidadMinimaVenta the unidadMinimaVenta to set
     */
    public void setUnidadMinimaVenta(Double unidadMinimaVenta) {
        this.unidadMinimaVenta = unidadMinimaVenta;
    }

    /**
     * @return the piezasXInner
     */
    public Double getPiezasXInner() {
        return piezasXInner;
    }

    /**
     * @param piezasXInner the piezasXInner to set
     */
    public void setPiezasXInner(Double piezasXInner) {
        this.piezasXInner = piezasXInner;
    }

    /**
     * @return the piezasXMaster
     */
    public Double getPiezasXMaster() {
        return piezasXMaster;
    }

    /**
     * @param piezasXMaster the piezasXMaster to set
     */
    public void setPiezasXMaster(Double piezasXMaster) {
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
     * @return the politicaXVolumen
     */
    public Boolean getPoliticaXVolumen() {
        return politicaXVolumen;
    }

    /**
     * @param politicaXVolumen the politicaXVolumen to set
     */
    public void setPoliticaXVolumen(Boolean politicaXVolumen) {
        this.politicaXVolumen = politicaXVolumen;
    }

    /**
     * @return the listaArticuloVolumen
     */
    public List<ArticuloVolumen> getListaArticuloVolumen() {
        return listaArticuloVolumen;
    }

    /**
     * @param listaArticuloVolumen the listaArticuloVolumen to set
     */
    public void setListaArticuloVolumen(List<ArticuloVolumen> listaArticuloVolumen) {
        this.listaArticuloVolumen = listaArticuloVolumen;
    }
}
