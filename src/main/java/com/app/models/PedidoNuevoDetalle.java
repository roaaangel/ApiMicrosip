/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

import java.sql.Date;

/**
 *
 * @author Administrador
 */
public class PedidoNuevoDetalle {
    private String serie;
    private int folio;
    private Date fecha;
    private String codigoCliente;
    private String nombreCliente;
    private double montoPedido;
    private String estado;
    private String codigoAgente;
    private String nombreAgente;

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the folio
     */
    public int getFolio() {
        return folio;
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(int folio) {
        this.folio = folio;
    }

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
     * @return the codigoCliente
     */
    public String getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * @param codigoCliente the codigoCliente to set
     */
    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
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
     * @return the montoPedido
     */
    public double getMontoPedido() {
        return montoPedido;
    }

    /**
     * @param montoPedido the montoPedido to set
     */
    public void setMontoPedido(double montoPedido) {
        this.montoPedido = montoPedido;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the codigoAgente
     */
    public String getCodigoAgente() {
        return codigoAgente;
    }

    /**
     * @param codigoAgente the codigoAgente to set
     */
    public void setCodigoAgente(String codigoAgente) {
        this.codigoAgente = codigoAgente;
    }

    /**
     * @return the nombreAgente
     */
    public String getNombreAgente() {
        return nombreAgente;
    }

    /**
     * @param nombreAgente the nombreAgente to set
     */
    public void setNombreAgente(String nombreAgente) {
        this.nombreAgente = nombreAgente;
    }

}
