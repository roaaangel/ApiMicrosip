/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

import com.google.gson.annotations.SerializedName;

public class RutaDTO {

    @SerializedName("A")
    private int rutaMapsOrdenId;

    @SerializedName("B")
    private String estatusCliente;

    @SerializedName("C")
    private Double latitudPedidoEntregado;

    @SerializedName("D")
    private Double longitudPedidoEntregado;

    @SerializedName("E")
    private boolean esMock;

    @SerializedName("F")
    private Double precision;

    public RutaDTO() {
    }

    public RutaDTO(int rutaMapsOrdenId, String estatusCliente,
                   Double latitudPedidoEntregado,
                   Double longitudPedidoEntregado,
                   boolean esMock,
                   Double precision) {

        this.rutaMapsOrdenId = rutaMapsOrdenId;
        this.estatusCliente = estatusCliente;
        this.latitudPedidoEntregado = latitudPedidoEntregado;
        this.longitudPedidoEntregado = longitudPedidoEntregado;
        this.esMock = esMock;
        this.precision = precision;
    }

    public int getRutaMapsOrdenId() {
        return rutaMapsOrdenId;
    }

    public void setRutaMapsOrdenId(int rutaMapsOrdenId) {
        this.rutaMapsOrdenId = rutaMapsOrdenId;
    }

    public String getEstatusCliente() {
        return estatusCliente;
    }

    public void setEstatusCliente(String estatusCliente) {
        this.estatusCliente = estatusCliente;
    }

    public Double getLatitudPedidoEntregado() {
        return latitudPedidoEntregado;
    }

    public void setLatitudPedidoEntregado(Double latitudPedidoEntregado) {
        this.latitudPedidoEntregado = latitudPedidoEntregado;
    }

    public Double getLongitudPedidoEntregado() {
        return longitudPedidoEntregado;
    }

    public void setLongitudPedidoEntregado(Double longitudPedidoEntregado) {
        this.longitudPedidoEntregado = longitudPedidoEntregado;
    }

    public boolean isEsMock() {
        return esMock;
    }

    public void setEsMock(boolean esMock) {
        this.esMock = esMock;
    }

    public Double getPrecision() {
        return precision;
    }

    public void setPrecision(Double precision) {
        this.precision = precision;
    }
}