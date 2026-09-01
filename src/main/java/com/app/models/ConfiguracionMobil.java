/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *
 * @author burtebony
 */
public class ConfiguracionMobil {
    private int precioEmpresaId;
    private int conceptoCCId;
    private int condicionPagoId;
    private int rolArticuloClavePrincipalId;
    private int rolArticuloClaveAlternaId;
    private int rolArticuloCodigoBarraId;
    private int rolArticuloCodigoBarraInnerId;
    private int rolArticuloCodigoBarraMasterId;    
    private int microsip2020;    
    private int sucursalId;      
    private int applyPoliticaPrecioClientes;
    private String comportamientoCaptura;
    private int operaDepositos;
    private String tipoPoliticaAOperar;
    private int diasHistoria;
    private String comportamientoAlmacen;
    private int folioFiscalId;
    private String serieConceptoCC;
    private int operaConsignatarios;
    private int applyDescArtsCtsPromo;
    private String reglaGPS;
    @SerializedName("diasGracia")
    private int diasGraciaId;
    private int operaPoliticasXVolumen;
    private int operaPoliticasXPromocion;    
    private int controlaSerieFolioCXC;      
    private int sincExistenciaArts;  
    private int sincArtsCondicionados;  
    private int sincExistArtsCondicionados;  
    private int sincCXCXRuta; 
    private int operaSucursalAlmacen; 
    private String formaCapturaPartida; 
    private int operaMonedaExtranjera; 
    private int disminuyeAbonoParaSaldo; 
    private int sincronizaPedidoTR;
    private int sincronizaAbonoTR;
    private int enviaSMS;
    private int operaPop;
    private List<ConfiguracionAlmacen> configuracionAlmacenes;
    private List<ConfiguracionCliente> configuracionClientes; 

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
     * @return the conceptoCCId
     */
    public int getConceptoCCId() {
        return conceptoCCId;
    }

    /**
     * @param conceptoCCId the conceptoCCId to set
     */
    public void setConceptoCCId(int conceptoCCId) {
        this.conceptoCCId = conceptoCCId;
    }

    /**
     * @return the condicionPagoId
     */
    public int getCondicionPagoId() {
        return condicionPagoId;
    }

    /**
     * @param condicionPagoId the condicionPagoId to set
     */
    public void setCondicionPagoId(int condicionPagoId) {
        this.condicionPagoId = condicionPagoId;
    }

    /**
     * @return the rolArticuloClavePrincipalId
     */
    public int getRolArticuloClavePrincipalId() {
        return rolArticuloClavePrincipalId;
    }

    /**
     * @param rolArticuloClavePrincipalId the rolArticuloClavePrincipalId to set
     */
    public void setRolArticuloClavePrincipalId(int rolArticuloClavePrincipalId) {
        this.rolArticuloClavePrincipalId = rolArticuloClavePrincipalId;
    }

    /**
     * @return the rolArticuloClaveAlternaId
     */
    public int getRolArticuloClaveAlternaId() {
        return rolArticuloClaveAlternaId;
    }

    /**
     * @param rolArticuloClaveAlternaId the rolArticuloClaveAlternaId to set
     */
    public void setRolArticuloClaveAlternaId(int rolArticuloClaveAlternaId) {
        this.rolArticuloClaveAlternaId = rolArticuloClaveAlternaId;
    }

    /**
     * @return the rolArticuloCodigoBarraId
     */
    public int getRolArticuloCodigoBarraId() {
        return rolArticuloCodigoBarraId;
    }

    /**
     * @param rolArticuloCodigoBarraId the rolArticuloCodigoBarraId to set
     */
    public void setRolArticuloCodigoBarraId(int rolArticuloCodigoBarraId) {
        this.rolArticuloCodigoBarraId = rolArticuloCodigoBarraId;
    }

    /**
     * @return the rolArticuloCodigoBarraInnerId
     */
    public int getRolArticuloCodigoBarraInnerId() {
        return rolArticuloCodigoBarraInnerId;
    }

    /**
     * @param rolArticuloCodigoBarraInnerId the rolArticuloCodigoBarraInnerId to set
     */
    public void setRolArticuloCodigoBarraInnerId(int rolArticuloCodigoBarraInnerId) {
        this.rolArticuloCodigoBarraInnerId = rolArticuloCodigoBarraInnerId;
    }

    /**
     * @return the rolArticuloCodigoBarraMasterId
     */
    public int getRolArticuloCodigoBarraMasterId() {
        return rolArticuloCodigoBarraMasterId;
    }

    /**
     * @param rolArticuloCodigoBarraMasterId the rolArticuloCodigoBarraMasterId to set
     */
    public void setRolArticuloCodigoBarraMasterId(int rolArticuloCodigoBarraMasterId) {
        this.rolArticuloCodigoBarraMasterId = rolArticuloCodigoBarraMasterId;
    }

    /**
     * @return the microsip2020
     */
    public int getMicrosip2020() {
        return microsip2020;
    }

    /**
     * @param microsip2020 the microsip2020 to set
     */
    public void setMicrosip2020(int microsip2020) {
        this.microsip2020 = microsip2020;
    }

    /**
     * @return the sucursalId
     */
    public int getSucursalId() {
        return sucursalId;
    }

    /**
     * @param sucursalId the sucursalId to set
     */
    public void setSucursalId(int sucursalId) {
        this.sucursalId = sucursalId;
    }

    /**
     * @return the applyPoliticaPrecioClientes
     */
    public int getApplyPoliticaPrecioClientes() {
        return applyPoliticaPrecioClientes;
    }

    /**
     * @param applyPoliticaPrecioClientes the applyPoliticaPrecioClientes to set
     */
    public void setApplyPoliticaPrecioClientes(int applyPoliticaPrecioClientes) {
        this.applyPoliticaPrecioClientes = applyPoliticaPrecioClientes;
    }

    /**
     * @return the comportamientoCaptura
     */
    public String getComportamientoCaptura() {
        return comportamientoCaptura;
    }

    /**
     * @param comportamientoCaptura the comportamientoCaptura to set
     */
    public void setComportamientoCaptura(String comportamientoCaptura) {
        this.comportamientoCaptura = comportamientoCaptura;
    }

    /**
     * @return the operaDepositos
     */
    public int getOperaDepositos() {
        return operaDepositos;
    }

    /**
     * @param operaDepositos the operaDepositos to set
     */
    public void setOperaDepositos(int operaDepositos) {
        this.operaDepositos = operaDepositos;
    }

    /**
     * @return the tipoPoliticaAOperar
     */
    public String getTipoPoliticaAOperar() {
        return tipoPoliticaAOperar;
    }

    /**
     * @param tipoPoliticaAOperar the tipoPoliticaAOperar to set
     */
    public void setTipoPoliticaAOperar(String tipoPoliticaAOperar) {
        this.tipoPoliticaAOperar = tipoPoliticaAOperar;
    }

    /**
     * @return the diasHistoria
     */
    public int getDiasHistoria() {
        return diasHistoria;
    }

    /**
     * @param diasHistoria the diasHistoria to set
     */
    public void setDiasHistoria(int diasHistoria) {
        this.diasHistoria = diasHistoria;
    }

    /**
     * @return the comportamientoAlmacen
     */
    public String getComportamientoAlmacen() {
        return comportamientoAlmacen;
    }

    /**
     * @param comportamientoAlmacen the comportamientoAlmacen to set
     */
    public void setComportamientoAlmacen(String comportamientoAlmacen) {
        this.comportamientoAlmacen = comportamientoAlmacen;
    }

    /**
     * @return the folioFiscalId
     */
    public int getFolioFiscalId() {
        return folioFiscalId;
    }

    /**
     * @param folioFiscalId the folioFiscalId to set
     */
    public void setFolioFiscalId(int folioFiscalId) {
        this.folioFiscalId = folioFiscalId;
    }

    /**
     * @return the serieConceptoCC
     */
    public String getSerieConceptoCC() {
        return serieConceptoCC;
    }

    /**
     * @param serieConceptoCC the serieConceptoCC to set
     */
    public void setSerieConceptoCC(String serieConceptoCC) {
        this.serieConceptoCC = serieConceptoCC;
    }

    /**
     * @return the operaConsignatarios
     */
    public int getOperaConsignatarios() {
        return operaConsignatarios;
    }

    /**
     * @param operaConsignatarios the operaConsignatarios to set
     */
    public void setOperaConsignatarios(int operaConsignatarios) {
        this.operaConsignatarios = operaConsignatarios;
    }

    /**
     * @return the applyDescArtsCtsPromo
     */
    public int getApplyDescArtsCtsPromo() {
        return applyDescArtsCtsPromo;
    }

    /**
     * @param applyDescArtsCtsPromo the applyDescArtsCtsPromo to set
     */
    public void setApplyDescArtsCtsPromo(int applyDescArtsCtsPromo) {
        this.applyDescArtsCtsPromo = applyDescArtsCtsPromo;
    }

    /**
     * @return the reglaGPS
     */
    public String getReglaGPS() {
        return reglaGPS;
    }

    /**
     * @param reglaGPS the reglaGPS to set
     */
    public void setReglaGPS(String reglaGPS) {
        this.reglaGPS = reglaGPS;
    }

    /**
     * @return the diasGraciaId
     */
    public int getDiasGraciaId() {
        return diasGraciaId;
    }

    /**
     * @param diasGraciaId the diasGraciaId to set
     */
    public void setDiasGraciaId(int diasGraciaId) {
        this.diasGraciaId = diasGraciaId;
    }

    /**
     * @return the operaPoliticasXVolumen
     */
    public int getOperaPoliticasXVolumen() {
        return operaPoliticasXVolumen;
    }

    /**
     * @param operaPoliticasXVolumen the operaPoliticasXVolumen to set
     */
    public void setOperaPoliticasXVolumen(int operaPoliticasXVolumen) {
        this.operaPoliticasXVolumen = operaPoliticasXVolumen;
    }

    /**
     * @return the operaPoliticasXPromocion
     */
    public int getOperaPoliticasXPromocion() {
        return operaPoliticasXPromocion;
    }

    /**
     * @param operaPoliticasXPromocion the operaPoliticasXPromocion to set
     */
    public void setOperaPoliticasXPromocion(int operaPoliticasXPromocion) {
        this.operaPoliticasXPromocion = operaPoliticasXPromocion;
    }

    /**
     * @return the controlaSerieFolioCXC
     */
    public int getControlaSerieFolioCXC() {
        return controlaSerieFolioCXC;
    }

    /**
     * @param controlaSerieFolioCXC the controlaSerieFolioCXC to set
     */
    public void setControlaSerieFolioCXC(int controlaSerieFolioCXC) {
        this.controlaSerieFolioCXC = controlaSerieFolioCXC;
    }

    /**
     * @return the sincExistenciaArts
     */
    public int getSincExistenciaArts() {
        return sincExistenciaArts;
    }

    /**
     * @param sincExistenciaArts the sincExistenciaArts to set
     */
    public void setSincExistenciaArts(int sincExistenciaArts) {
        this.sincExistenciaArts = sincExistenciaArts;
    }

    /**
     * @return the sincArtsCondicionados
     */
    public int getSincArtsCondicionados() {
        return sincArtsCondicionados;
    }

    /**
     * @param sincArtsCondicionados the sincArtsCondicionados to set
     */
    public void setSincArtsCondicionados(int sincArtsCondicionados) {
        this.sincArtsCondicionados = sincArtsCondicionados;
    }

    /**
     * @return the sincExistArtsCondicionados
     */
    public int getSincExistArtsCondicionados() {
        return sincExistArtsCondicionados;
    }

    /**
     * @param sincExistArtsCondicionados the sincExistArtsCondicionados to set
     */
    public void setSincExistArtsCondicionados(int sincExistArtsCondicionados) {
        this.sincExistArtsCondicionados = sincExistArtsCondicionados;
    }

    /**
     * @return the sincCXCXRuta
     */
    public int getSincCXCXRuta() {
        return sincCXCXRuta;
    }

    /**
     * @param sincCXCXRuta the sincCXCXRuta to set
     */
    public void setSincCXCXRuta(int sincCXCXRuta) {
        this.sincCXCXRuta = sincCXCXRuta;
    }

    /**
     * @return the operaSucursalAlmacen
     */
    public int getOperaSucursalAlmacen() {
        return operaSucursalAlmacen;
    }

    /**
     * @param operaSucursalAlmacen the operaSucursalAlmacen to set
     */
    public void setOperaSucursalAlmacen(int operaSucursalAlmacen) {
        this.operaSucursalAlmacen = operaSucursalAlmacen;
    }

    /**
     * @return the formaCapturaPartida
     */
    public String getFormaCapturaPartida() {
        return formaCapturaPartida;
    }

    /**
     * @param formaCapturaPartida the formaCapturaPartida to set
     */
    public void setFormaCapturaPartida(String formaCapturaPartida) {
        this.formaCapturaPartida = formaCapturaPartida;
    }

    /**
     * @return the operaMonedaExtranjera
     */
    public int getOperaMonedaExtranjera() {
        return operaMonedaExtranjera;
    }

    /**
     * @param operaMonedaExtranjera the operaMonedaExtranjera to set
     */
    public void setOperaMonedaExtranjera(int operaMonedaExtranjera) {
        this.operaMonedaExtranjera = operaMonedaExtranjera;
    }

    /**
     * @return the disminuyeAbonoParaSaldo
     */
    public int getDisminuyeAbonoParaSaldo() {
        return disminuyeAbonoParaSaldo;
    }

    /**
     * @param disminuyeAbonoParaSaldo the disminuyeAbonoParaSaldo to set
     */
    public void setDisminuyeAbonoParaSaldo(int disminuyeAbonoParaSaldo) {
        this.disminuyeAbonoParaSaldo = disminuyeAbonoParaSaldo;
    }

    /**
     * @return the sincronizaPedidoTR
     */
    public int getSincronizaPedidoTR() {
        return sincronizaPedidoTR;
    }

    /**
     * @param sincronizaPedidoTR the sincronizaPedidoTR to set
     */
    public void setSincronizaPedidoTR(int sincronizaPedidoTR) {
        this.sincronizaPedidoTR = sincronizaPedidoTR;
    }

    /**
     * @return the sincronizaAbonoTR
     */
    public int getSincronizaAbonoTR() {
        return sincronizaAbonoTR;
    }

    /**
     * @param sincronizaAbonoTR the sincronizaAbonoTR to set
     */
    public void setSincronizaAbonoTR(int sincronizaAbonoTR) {
        this.sincronizaAbonoTR = sincronizaAbonoTR;
    }

    /**
     * @return the enviaSMS
     */
    public int getEnviaSMS() {
        return enviaSMS;
    }

    /**
     * @param enviaSMS the enviaSMS to set
     */
    public void setEnviaSMS(int enviaSMS) {
        this.enviaSMS = enviaSMS;
    }

    /**
     * @return the operaPop
     */
    public int getOperaPop() {
        return operaPop;
    }

    /**
     * @param operaPop the operaPop to set
     */
    public void setOperaPop(int operaPop) {
        this.operaPop = operaPop;
    }

    /**
     * @return the configuracionAlmacenes
     */
    public List<ConfiguracionAlmacen> getConfiguracionAlmacenes() {
        return configuracionAlmacenes;
    }

    /**
     * @param configuracionAlmacenes the configuracionAlmacenes to set
     */
    public void setConfiguracionAlmacenes(List<ConfiguracionAlmacen> configuracionAlmacenes) {
        this.configuracionAlmacenes = configuracionAlmacenes;
    }

    /**
     * @return the configuracionClientes
     */
    public List<ConfiguracionCliente> getConfiguracionClientes() {
        return configuracionClientes;
    }

    /**
     * @param configuracionClientes the configuracionClientes to set
     */
    public void setConfiguracionClientes(List<ConfiguracionCliente> configuracionClientes) {
        this.configuracionClientes = configuracionClientes;
    }
}
