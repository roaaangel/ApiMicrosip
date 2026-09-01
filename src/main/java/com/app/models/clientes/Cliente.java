/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.clientes;

/**
 *
 * @author burtebony
 */
public class Cliente {
    private int clienteId;
    private String nombreCliente; 
    private String claveCliente; 
    private int tipoClienteId;
    private String tipoClienteNombre;
    private int zonaClienteId;
    private String zonaClienteNombre;
    private int cobradorId;
    private String cobradorNombre;
    private int vendedorId;
    private String vendedorNombre;
    private String rfcCurp;
    private int direccionClienteId;
    private String calle;
    private String numeroExterior;
    private String numeroInterior;
    private String colonia;
    private String poblacion;
    private String codigoPostal;
    private String telefono1;
    private String telefono2;
    private Double porcentajeDescuentoXPolitica;
    private int politicaDescuentoArticuloClienteId;
    private String nombrePolitica;
    private Double limiteCredito;
    

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
     * @return the tipoClienteId
     */
    public int getTipoClienteId() {
        return tipoClienteId;
    }

    /**
     * @param tipoClienteId the tipoClienteId to set
     */
    public void setTipoClienteId(int tipoClienteId) {
        this.tipoClienteId = tipoClienteId;
    }

    /**
     * @return the tipoClienteNombre
     */
    public String getTipoClienteNombre() {
        return tipoClienteNombre;
    }

    /**
     * @param tipoClienteNombre the tipoClienteNombre to set
     */
    public void setTipoClienteNombre(String tipoClienteNombre) {
        this.tipoClienteNombre = tipoClienteNombre;
    }

    /**
     * @return the zonaClienteId
     */
    public int getZonaClienteId() {
        return zonaClienteId;
    }

    /**
     * @param zonaClienteId the zonaClienteId to set
     */
    public void setZonaClienteId(int zonaClienteId) {
        this.zonaClienteId = zonaClienteId;
    }

    /**
     * @return the zonaClienteNombre
     */
    public String getZonaClienteNombre() {
        return zonaClienteNombre;
    }

    /**
     * @param zonaClienteNombre the zonaClienteNombre to set
     */
    public void setZonaClienteNombre(String zonaClienteNombre) {
        this.zonaClienteNombre = zonaClienteNombre;
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
     * @return the cobradorNombre
     */
    public String getCobradorNombre() {
        return cobradorNombre;
    }

    /**
     * @param cobradorNombre the cobradorNombre to set
     */
    public void setCobradorNombre(String cobradorNombre) {
        this.cobradorNombre = cobradorNombre;
    }

    /**
     * @return the vendedorId
     */
    public int getVendedorId() {
        return vendedorId;
    }

    /**
     * @param vendedorId the vendedorId to set
     */
    public void setVendedorId(int vendedorId) {
        this.vendedorId = vendedorId;
    }

    /**
     * @return the vendedorNombre
     */
    public String getVendedorNombre() {
        return vendedorNombre;
    }

    /**
     * @param vendedorNombre the vendedorNombre to set
     */
    public void setVendedorNombre(String vendedorNombre) {
        this.vendedorNombre = vendedorNombre;
    }

    /**
     * @return the rfcCurp
     */
    public String getRfcCurp() {
        return rfcCurp;
    }

    /**
     * @param rfcCurp the rfcCurp to set
     */
    public void setRfcCurp(String rfcCurp) {
        this.rfcCurp = rfcCurp;
    }

    /**
     * @return the direccionClienteId
     */
    public int getDireccionClienteId() {
        return direccionClienteId;
    }

    /**
     * @param direccionClienteId the direccionClienteId to set
     */
    public void setDireccionClienteId(int direccionClienteId) {
        this.direccionClienteId = direccionClienteId;
    }

    /**
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * @param calle the calle to set
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * @return the numeroExterior
     */
    public String getNumeroExterior() {
        return numeroExterior;
    }

    /**
     * @param numeroExterior the numeroExterior to set
     */
    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    /**
     * @return the numeroInterior
     */
    public String getNumeroInterior() {
        return numeroInterior;
    }

    /**
     * @param numeroInterior the numeroInterior to set
     */
    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    /**
     * @return the colonia
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * @param colonia the colonia to set
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * @return the poblacion
     */
    public String getPoblacion() {
        return poblacion;
    }

    /**
     * @param poblacion the poblacion to set
     */
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    /**
     * @return the codigoPostal
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * @param codigoPostal the codigoPostal to set
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * @return the telefono1
     */
    public String getTelefono1() {
        return telefono1;
    }

    /**
     * @param telefono1 the telefono1 to set
     */
    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    /**
     * @return the telefono2
     */
    public String getTelefono2() {
        return telefono2;
    }

    /**
     * @param telefono2 the telefono2 to set
     */
    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    /**
     * @return the porcentajeDescuentoXPolitica
     */
    public Double getPorcentajeDescuentoXPolitica() {
        return porcentajeDescuentoXPolitica;
    }

    /**
     * @param porcentajeDescuentoXPolitica the porcentajeDescuentoXPolitica to set
     */
    public void setPorcentajeDescuentoXPolitica(Double porcentajeDescuentoXPolitica) {
        this.porcentajeDescuentoXPolitica = porcentajeDescuentoXPolitica;
    }

    /**
     * @return the politicaDescuentoArticuloClienteId
     */
    public int getPoliticaDescuentoArticuloClienteId() {
        return politicaDescuentoArticuloClienteId;
    }

    /**
     * @param politicaDescuentoArticuloClienteId the politicaDescuentoArticuloClienteId to set
     */
    public void setPoliticaDescuentoArticuloClienteId(int politicaDescuentoArticuloClienteId) {
        this.politicaDescuentoArticuloClienteId = politicaDescuentoArticuloClienteId;
    }

    /**
     * @return the nombrePolitica
     */
    public String getNombrePolitica() {
        return nombrePolitica;
    }

    /**
     * @param nombrePolitica the nombrePolitica to set
     */
    public void setNombrePolitica(String nombrePolitica) {
        this.nombrePolitica = nombrePolitica;
    }

    /**
     * @return the limiteCredito
     */
    public Double getLimiteCredito() {
        return limiteCredito;
    }

    /**
     * @param limiteCredito the limiteCredito to set
     */
    public void setLimiteCredito(Double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }
}
