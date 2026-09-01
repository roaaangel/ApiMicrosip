/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

import java.util.Date;

/**
 *
 * @author burtebony
 */
public class VentaUtilidad {
    private Date fecha;
    private String idCliente;
    private String nombreCliente;
    private String agente;
    private String linea;
    private String familia;
    private String codigoArticulo;
    private String nombreArticulo;
    private String claveAlmacen;
    private String nombreAlmacen;    
    private Double costoVenta;
    private Double utilidad;
    private Double cantidad;
    private Double precioVenta;
    private Double precioUno;
    private Double cantidadUno;
    private Double precioDos;
    private Double cantidadDos;
    private Double precioTres;
    private Double cantidadTres;
    private Double precioCuatro;
    private Double cantidadCuatro;
    private Double precioCinco;
    private Double cantidadCinco;

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the idCliente
     */
    public String getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
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
     * @return the agente
     */
    public String getAgente() {
        return agente;
    }

    /**
     * @param agente the agente to set
     */
    public void setAgente(String agente) {
        this.agente = agente;
    }

    /**
     * @return the linea
     */
    public String getLinea() {
        return linea;
    }

    /**
     * @param linea the linea to set
     */
    public void setLinea(String linea) {
        this.linea = linea;
    }

    /**
     * @return the familia
     */
    public String getFamilia() {
        return familia;
    }

    /**
     * @param familia the familia to set
     */
    public void setFamilia(String familia) {
        this.familia = familia;
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
     * @return the claveAlmacen
     */
    public String getClaveAlmacen() {
        return claveAlmacen;
    }

    /**
     * @param claveAlmacen the claveAlmacen to set
     */
    public void setClaveAlmacen(String claveAlmacen) {
        this.claveAlmacen = claveAlmacen;
    }

    /**
     * @return the nombreAlmacen
     */
    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    /**
     * @param nombreAlmacen the nombreAlmacen to set
     */
    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }

    /**
     * @return the costoVenta
     */
    public Double getCostoVenta() {
        return costoVenta;
    }

    /**
     * @param costoVenta the costoVenta to set
     */
    public void setCostoVenta(Double costoVenta) {
        this.costoVenta = costoVenta;
    }

    /**
     * @return the utilidad
     */
    public Double getUtilidad() {
        return utilidad;
    }

    /**
     * @param utilidad the utilidad to set
     */
    public void setUtilidad(Double utilidad) {
        this.utilidad = utilidad;
    }

    /**
     * @return the cantidad
     */
    public Double getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the precioVenta
     */
    public Double getPrecioVenta() {
        return precioVenta;
    }

    /**
     * @param precioVenta the precioVenta to set
     */
    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    /**
     * @return the precioUno
     */
    public Double getPrecioUno() {
        return precioUno;
    }

    /**
     * @param precioUno the precioUno to set
     */
    public void setPrecioUno(Double precioUno) {
        this.precioUno = precioUno;
    }

    /**
     * @return the cantidadUno
     */
    public Double getCantidadUno() {
        return cantidadUno;
    }

    /**
     * @param cantidadUno the cantidadUno to set
     */
    public void setCantidadUno(Double cantidadUno) {
        this.cantidadUno = cantidadUno;
    }

    /**
     * @return the precioDos
     */
    public Double getPrecioDos() {
        return precioDos;
    }

    /**
     * @param precioDos the precioDos to set
     */
    public void setPrecioDos(Double precioDos) {
        this.precioDos = precioDos;
    }

    /**
     * @return the cantidadDos
     */
    public Double getCantidadDos() {
        return cantidadDos;
    }

    /**
     * @param cantidadDos the cantidadDos to set
     */
    public void setCantidadDos(Double cantidadDos) {
        this.cantidadDos = cantidadDos;
    }

    /**
     * @return the precioTres
     */
    public Double getPrecioTres() {
        return precioTres;
    }

    /**
     * @param precioTres the precioTres to set
     */
    public void setPrecioTres(Double precioTres) {
        this.precioTres = precioTres;
    }

    /**
     * @return the cantidadTres
     */
    public Double getCantidadTres() {
        return cantidadTres;
    }

    /**
     * @param cantidadTres the cantidadTres to set
     */
    public void setCantidadTres(Double cantidadTres) {
        this.cantidadTres = cantidadTres;
    }

    /**
     * @return the precioCuatro
     */
    public Double getPrecioCuatro() {
        return precioCuatro;
    }

    /**
     * @param precioCuatro the precioCuatro to set
     */
    public void setPrecioCuatro(Double precioCuatro) {
        this.precioCuatro = precioCuatro;
    }

    /**
     * @return the cantidadCuatro
     */
    public Double getCantidadCuatro() {
        return cantidadCuatro;
    }

    /**
     * @param cantidadCuatro the cantidadCuatro to set
     */
    public void setCantidadCuatro(Double cantidadCuatro) {
        this.cantidadCuatro = cantidadCuatro;
    }

    /**
     * @return the precioCinco
     */
    public Double getPrecioCinco() {
        return precioCinco;
    }

    /**
     * @param precioCinco the precioCinco to set
     */
    public void setPrecioCinco(Double precioCinco) {
        this.precioCinco = precioCinco;
    }

    /**
     * @return the cantidadCinco
     */
    public Double getCantidadCinco() {
        return cantidadCinco;
    }

    /**
     * @param cantidadCinco the cantidadCinco to set
     */
    public void setCantidadCinco(Double cantidadCinco) {
        this.cantidadCinco = cantidadCinco;
    }

}
