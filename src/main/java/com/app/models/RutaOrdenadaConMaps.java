/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

/**
 *
 * @author angel
 */
public class RutaOrdenadaConMaps {
    private int rutaMapsOrdenId;    
    private int orden;
    private int idOrigen;
    private String nombreClienteOrigen;    
    private Double latitudOrigen;
    private Double longitudOrigen;    
    private int idDestino;
    private String nombreClienteDestino;    
    private Double latitudDestino;
    private Double longitudDestino;
    private String distancia;
    private String duracion;
    private String estatusCliente;    
    private int numeroDocumentos;

    /**
     * @return the rutaMapsOrdenId
     */
    public int getRutaMapsOrdenId() {
        return rutaMapsOrdenId;
    }

    /**
     * @param rutaMapsOrdenId the rutaMapsOrdenId to set
     */
    public void setRutaMapsOrdenId(int rutaMapsOrdenId) {
        this.rutaMapsOrdenId = rutaMapsOrdenId;
    }

    /**
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

    /**
     * @return the idOrigen
     */
    public int getIdOrigen() {
        return idOrigen;
    }

    /**
     * @param idOrigen the idOrigen to set
     */
    public void setIdOrigen(int idOrigen) {
        this.idOrigen = idOrigen;
    }

    /**
     * @return the nombreClienteOrigen
     */
    public String getNombreClienteOrigen() {
        return nombreClienteOrigen;
    }

    /**
     * @param nombreClienteOrigen the nombreClienteOrigen to set
     */
    public void setNombreClienteOrigen(String nombreClienteOrigen) {
        this.nombreClienteOrigen = nombreClienteOrigen;
    }

    /**
     * @return the latitudOrigen
     */
    public Double getLatitudOrigen() {
        return latitudOrigen;
    }

    /**
     * @param latitudOrigen the latitudOrigen to set
     */
    public void setLatitudOrigen(Double latitudOrigen) {
        this.latitudOrigen = latitudOrigen;
    }

    /**
     * @return the longitudOrigen
     */
    public Double getLongitudOrigen() {
        return longitudOrigen;
    }

    /**
     * @param longitudOrigen the longitudOrigen to set
     */
    public void setLongitudOrigen(Double longitudOrigen) {
        this.longitudOrigen = longitudOrigen;
    }

    /**
     * @return the idDestino
     */
    public int getIdDestino() {
        return idDestino;
    }

    /**
     * @param idDestino the idDestino to set
     */
    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    /**
     * @return the nombreClienteDestino
     */
    public String getNombreClienteDestino() {
        return nombreClienteDestino;
    }

    /**
     * @param nombreClienteDestino the nombreClienteDestino to set
     */
    public void setNombreClienteDestino(String nombreClienteDestino) {
        this.nombreClienteDestino = nombreClienteDestino;
    }

    /**
     * @return the latitudDestino
     */
    public Double getLatitudDestino() {
        return latitudDestino;
    }

    /**
     * @param latitudDestino the latitudDestino to set
     */
    public void setLatitudDestino(Double latitudDestino) {
        this.latitudDestino = latitudDestino;
    }

    /**
     * @return the longitudDestino
     */
    public Double getLongitudDestino() {
        return longitudDestino;
    }

    /**
     * @param longitudDestino the longitudDestino to set
     */
    public void setLongitudDestino(Double longitudDestino) {
        this.longitudDestino = longitudDestino;
    }

    /**
     * @return the distancia
     */
    public String getDistancia() {
        return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    /**
     * @return the duracion
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the estatusCliente
     */
    public String getEstatusCliente() {
        return estatusCliente;
    }

    /**
     * @param estatusCliente the estatusCliente to set
     */
    public void setEstatusCliente(String estatusCliente) {
        this.estatusCliente = estatusCliente;
    }

    /**
     * @return the numeroDocumentos
     */
    public int getNumeroDocumentos() {
        return numeroDocumentos;
    }

    /**
     * @param numeroDocumentos the numeroDocumentos to set
     */
    public void setNumeroDocumentos(int numeroDocumentos) {
        this.numeroDocumentos = numeroDocumentos;
    }
}
