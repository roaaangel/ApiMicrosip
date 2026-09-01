/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

/**
 *
 * @author burtebony
 */
public class ReporteMovil {
	private int doctoCCId;
	private int clienteId;
	private String claveCliente;
	private String serieDocumento;
	private String fechaDocumento;
	private double saldoDocumento;
	private String fechaAbono;
	private String horaAbono;
	private double importeAbono;
	private String formaPago;
        private int formaCobroCCId;
	/**
	 * @return the doctoCCId
	 */
	public int getDoctoCCId() {
		return doctoCCId;
	}
	/**
	 * @param doctoCCId the doctoCCId to set
	 */
	public void setDoctoCCId(int doctoCCId) {
		this.doctoCCId = doctoCCId;
	}
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
	 * @return the serieDocumento
	 */
	public String getSerieDocumento() {
		return serieDocumento;
	}
	/**
	 * @param serieDocumento the serieDocumento to set
	 */
	public void setSerieDocumento(String serieDocumento) {
		this.serieDocumento = serieDocumento;
	}
	/**
	 * @return the fechaDocumento
	 */
	public String getFechaDocumento() {
		return fechaDocumento;
	}
	/**
	 * @param fechaDocumento the fechaDocumento to set
	 */
	public void setFechaDocumento(String fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}
	/**
	 * @return the saldoDocumento
	 */
	public double getSaldoDocumento() {
		return saldoDocumento;
	}
	/**
	 * @param saldoDocumento the saldoDocumento to set
	 */
	public void setSaldoDocumento(double saldoDocumento) {
		this.saldoDocumento = saldoDocumento;
	}
	/**
	 * @return the fechaAbono
	 */
	public String getFechaAbono() {
		return fechaAbono;
	}
	/**
	 * @param fechaAbono the fechaAbono to set
	 */
	public void setFechaAbono(String fechaAbono) {
		this.fechaAbono = fechaAbono;
	}
	/**
	 * @return the horaAbono
	 */
	public String getHoraAbono() {
		return horaAbono;
	}
	/**
	 * @param horaAbono the horaAbono to set
	 */
	public void setHoraAbono(String horaAbono) {
		this.horaAbono = horaAbono;
	}
	/**
	 * @return the importeAbono
	 */
	public double getImporteAbono() {
		return importeAbono;
	}
	/**
	 * @param importeAbono the importeAbono to set
	 */
	public void setImporteAbono(double importeAbono) {
		this.importeAbono = importeAbono;
	}
	/**
	 * @return the formaPago
	 */
	public String getFormaPago() {
		return formaPago;
	}
	/**
	 * @param formaPago the formaPago to set
	 */
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
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
	private int cobradorId;

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
}
