/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

import java.util.ArrayList;
import jakarta.xml.bind.annotation.XmlRootElement;

// JavaBean que se convierte en JSON, para estosolo bastará con poner la anotación @XmlRootElement al inicio de la declaración de la clase.
@XmlRootElement
public class MaeMovCa02 {
private int id;
    private int clienteId;
    private String serfol;
    private String numpag;
    private int numalm;
    private String numagt;
    private String fcapmov;
    private String fdocmov;
    private String leymov;
    private String pedmov;
    private double dctmov;
    private String ag2mov;
    private int pzomov;
    private String nommov;
    private double ivatmov;
    private double impmov;
    private double iepsmov;
    private double antmov;
    private String nsormov;
    private String flgmov;
    private String refmov;
    private String numeys;
    private int nummov;
    private int numfol;
    private int aplimov;
    private String numubi;
    private int nfordmov;
    private String horamov;
    private double user_id;
    private String srmov;
    private String numflt;
    private String cvevehi;
    private String callemov;
    private String colmov;
    private String pobmov;
    private String rfcmov;
    private String curpmov;
    private String estadomov;
    private String cpmov;
    private String faxmov;
    private String telmov;
    private int numzeta;
    private int numticket;
    private String nlecte;
    private String nlicte;
    private String vale;
    private String replica;
    private String numalm2;
    private String esquema;
    private String nummon;
    private String observaciones;
    private double impmovcan;
    private double ivatmovcan;
    private String numcte;//ESe lo agregue al final para q este disponible para los moviles
    private int vendedorId;
    private int direccionClienteId;  
    private int dirConsigId;
    private Double descuentoCliente;
    private String uuid;
    private String cotizacionEmiteFactura;
    
    private ArrayList<DetallePedido> listaDetallePedido;   

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the serfol
     */
    public String getSerfol() {
        return serfol;
    }

    /**
     * @param serfol the serfol to set
     */
    public void setSerfol(String serfol) {
        this.serfol = serfol;
    }

    /**
     * @return the numpag
     */
    public String getNumpag() {
        return numpag;
    }

    /**
     * @param numpag the numpag to set
     */
    public void setNumpag(String numpag) {
        this.numpag = numpag;
    }

    /**
     * @return the numalm
     */
    public int getNumalm() {
        return numalm;
    }

    /**
     * @param numalm the numalm to set
     */
    public void setNumalm(int numalm) {
        this.numalm = numalm;
    }

    /**
     * @return the numagt
     */
    public String getNumagt() {
        return numagt;
    }

    /**
     * @param numagt the numagt to set
     */
    public void setNumagt(String numagt) {
        this.numagt = numagt;
    }

    /**
     * @return the fcapmov
     */
    public String getFcapmov() {
        return fcapmov;
    }

    /**
     * @param fcapmov the fcapmov to set
     */
    public void setFcapmov(String fcapmov) {
        this.fcapmov = fcapmov;
    }

    /**
     * @return the fdocmov
     */
    public String getFdocmov() {
        return fdocmov;
    }

    /**
     * @param fdocmov the fdocmov to set
     */
    public void setFdocmov(String fdocmov) {
        this.fdocmov = fdocmov;
    }

    /**
     * @return the leymov
     */
    public String getLeymov() {
        return leymov;
    }

    /**
     * @param leymov the leymov to set
     */
    public void setLeymov(String leymov) {
        this.leymov = leymov;
    }

    /**
     * @return the pedmov
     */
    public String getPedmov() {
        return pedmov;
    }

    /**
     * @param pedmov the pedmov to set
     */
    public void setPedmov(String pedmov) {
        this.pedmov = pedmov;
    }

    /**
     * @return the dctmov
     */
    public double getDctmov() {
        return dctmov;
    }

    /**
     * @param dctmov the dctmov to set
     */
    public void setDctmov(double dctmov) {
        this.dctmov = dctmov;
    }

    /**
     * @return the ag2mov
     */
    public String getAg2mov() {
        return ag2mov;
    }

    /**
     * @param ag2mov the ag2mov to set
     */
    public void setAg2mov(String ag2mov) {
        this.ag2mov = ag2mov;
    }

    /**
     * @return the pzomov
     */
    public int getPzomov() {
        return pzomov;
    }

    /**
     * @param pzomov the pzomov to set
     */
    public void setPzomov(int pzomov) {
        this.pzomov = pzomov;
    }

    /**
     * @return the nommov
     */
    public String getNommov() {
        return nommov;
    }

    /**
     * @param nommov the nommov to set
     */
    public void setNommov(String nommov) {
        this.nommov = nommov;
    }

    /**
     * @return the ivatmov
     */
    public double getIvatmov() {
        return ivatmov;
    }

    /**
     * @param ivatmov the ivatmov to set
     */
    public void setIvatmov(double ivatmov) {
        this.ivatmov = ivatmov;
    }

    /**
     * @return the impmov
     */
    public double getImpmov() {
        return impmov;
    }

    /**
     * @param impmov the impmov to set
     */
    public void setImpmov(double impmov) {
        this.impmov = impmov;
    }

    /**
     * @return the iepsmov
     */
    public double getIepsmov() {
        return iepsmov;
    }

    /**
     * @param iepsmov the iepsmov to set
     */
    public void setIepsmov(double iepsmov) {
        this.iepsmov = iepsmov;
    }

    /**
     * @return the antmov
     */
    public double getAntmov() {
        return antmov;
    }

    /**
     * @param antmov the antmov to set
     */
    public void setAntmov(double antmov) {
        this.antmov = antmov;
    }

    /**
     * @return the nsormov
     */
    public String getNsormov() {
        return nsormov;
    }

    /**
     * @param nsormov the nsormov to set
     */
    public void setNsormov(String nsormov) {
        this.nsormov = nsormov;
    }

    /**
     * @return the flgmov
     */
    public String getFlgmov() {
        return flgmov;
    }

    /**
     * @param flgmov the flgmov to set
     */
    public void setFlgmov(String flgmov) {
        this.flgmov = flgmov;
    }

    /**
     * @return the refmov
     */
    public String getRefmov() {
        return refmov;
    }

    /**
     * @param refmov the refmov to set
     */
    public void setRefmov(String refmov) {
        this.refmov = refmov;
    }

    /**
     * @return the numeys
     */
    public String getNumeys() {
        return numeys;
    }

    /**
     * @param numeys the numeys to set
     */
    public void setNumeys(String numeys) {
        this.numeys = numeys;
    }

    /**
     * @return the nummov
     */
    public int getNummov() {
        return nummov;
    }

    /**
     * @param nummov the nummov to set
     */
    public void setNummov(int nummov) {
        this.nummov = nummov;
    }

    /**
     * @return the numfol
     */
    public int getNumfol() {
        return numfol;
    }

    /**
     * @param numfol the numfol to set
     */
    public void setNumfol(int numfol) {
        this.numfol = numfol;
    }

    /**
     * @return the aplimov
     */
    public int getAplimov() {
        return aplimov;
    }

    /**
     * @param aplimov the aplimov to set
     */
    public void setAplimov(int aplimov) {
        this.aplimov = aplimov;
    }

    /**
     * @return the numubi
     */
    public String getNumubi() {
        return numubi;
    }

    /**
     * @param numubi the numubi to set
     */
    public void setNumubi(String numubi) {
        this.numubi = numubi;
    }

    /**
     * @return the nfordmov
     */
    public int getNfordmov() {
        return nfordmov;
    }

    /**
     * @param nfordmov the nfordmov to set
     */
    public void setNfordmov(int nfordmov) {
        this.nfordmov = nfordmov;
    }

    /**
     * @return the horamov
     */
    public String getHoramov() {
        return horamov;
    }

    /**
     * @param horamov the horamov to set
     */
    public void setHoramov(String horamov) {
        this.horamov = horamov;
    }

    /**
     * @return the user_id
     */
    public double getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(double user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the srmov
     */
    public String getSrmov() {
        return srmov;
    }

    /**
     * @param srmov the srmov to set
     */
    public void setSrmov(String srmov) {
        this.srmov = srmov;
    }

    /**
     * @return the numflt
     */
    public String getNumflt() {
        return numflt;
    }

    /**
     * @param numflt the numflt to set
     */
    public void setNumflt(String numflt) {
        this.numflt = numflt;
    }

    /**
     * @return the cvevehi
     */
    public String getCvevehi() {
        return cvevehi;
    }

    /**
     * @param cvevehi the cvevehi to set
     */
    public void setCvevehi(String cvevehi) {
        this.cvevehi = cvevehi;
    }

    /**
     * @return the callemov
     */
    public String getCallemov() {
        return callemov;
    }

    /**
     * @param callemov the callemov to set
     */
    public void setCallemov(String callemov) {
        this.callemov = callemov;
    }

    /**
     * @return the colmov
     */
    public String getColmov() {
        return colmov;
    }

    /**
     * @param colmov the colmov to set
     */
    public void setColmov(String colmov) {
        this.colmov = colmov;
    }

    /**
     * @return the pobmov
     */
    public String getPobmov() {
        return pobmov;
    }

    /**
     * @param pobmov the pobmov to set
     */
    public void setPobmov(String pobmov) {
        this.pobmov = pobmov;
    }

    /**
     * @return the rfcmov
     */
    public String getRfcmov() {
        return rfcmov;
    }

    /**
     * @param rfcmov the rfcmov to set
     */
    public void setRfcmov(String rfcmov) {
        this.rfcmov = rfcmov;
    }

    /**
     * @return the curpmov
     */
    public String getCurpmov() {
        return curpmov;
    }

    /**
     * @param curpmov the curpmov to set
     */
    public void setCurpmov(String curpmov) {
        this.curpmov = curpmov;
    }

    /**
     * @return the estadomov
     */
    public String getEstadomov() {
        return estadomov;
    }

    /**
     * @param estadomov the estadomov to set
     */
    public void setEstadomov(String estadomov) {
        this.estadomov = estadomov;
    }

    /**
     * @return the cpmov
     */
    public String getCpmov() {
        return cpmov;
    }

    /**
     * @param cpmov the cpmov to set
     */
    public void setCpmov(String cpmov) {
        this.cpmov = cpmov;
    }

    /**
     * @return the faxmov
     */
    public String getFaxmov() {
        return faxmov;
    }

    /**
     * @param faxmov the faxmov to set
     */
    public void setFaxmov(String faxmov) {
        this.faxmov = faxmov;
    }

    /**
     * @return the telmov
     */
    public String getTelmov() {
        return telmov;
    }

    /**
     * @param telmov the telmov to set
     */
    public void setTelmov(String telmov) {
        this.telmov = telmov;
    }

    /**
     * @return the numzeta
     */
    public int getNumzeta() {
        return numzeta;
    }

    /**
     * @param numzeta the numzeta to set
     */
    public void setNumzeta(int numzeta) {
        this.numzeta = numzeta;
    }

    /**
     * @return the numticket
     */
    public int getNumticket() {
        return numticket;
    }

    /**
     * @param numticket the numticket to set
     */
    public void setNumticket(int numticket) {
        this.numticket = numticket;
    }

    /**
     * @return the nlecte
     */
    public String getNlecte() {
        return nlecte;
    }

    /**
     * @param nlecte the nlecte to set
     */
    public void setNlecte(String nlecte) {
        this.nlecte = nlecte;
    }

    /**
     * @return the nlicte
     */
    public String getNlicte() {
        return nlicte;
    }

    /**
     * @param nlicte the nlicte to set
     */
    public void setNlicte(String nlicte) {
        this.nlicte = nlicte;
    }

    /**
     * @return the vale
     */
    public String getVale() {
        return vale;
    }

    /**
     * @param vale the vale to set
     */
    public void setVale(String vale) {
        this.vale = vale;
    }

    /**
     * @return the replica
     */
    public String getReplica() {
        return replica;
    }

    /**
     * @param replica the replica to set
     */
    public void setReplica(String replica) {
        this.replica = replica;
    }

    /**
     * @return the numalm2
     */
    public String getNumalm2() {
        return numalm2;
    }

    /**
     * @param numalm2 the numalm2 to set
     */
    public void setNumalm2(String numalm2) {
        this.numalm2 = numalm2;
    }

    /**
     * @return the esquema
     */
    public String getEsquema() {
        return esquema;
    }

    /**
     * @param esquema the esquema to set
     */
    public void setEsquema(String esquema) {
        this.esquema = esquema;
    }

    /**
     * @return the nummon
     */
    public String getNummon() {
        return nummon;
    }

    /**
     * @param nummon the nummon to set
     */
    public void setNummon(String nummon) {
        this.nummon = nummon;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the impmovcan
     */
    public double getImpmovcan() {
        return impmovcan;
    }

    /**
     * @param impmovcan the impmovcan to set
     */
    public void setImpmovcan(double impmovcan) {
        this.impmovcan = impmovcan;
    }

    /**
     * @return the ivatmovcan
     */
    public double getIvatmovcan() {
        return ivatmovcan;
    }

    /**
     * @param ivatmovcan the ivatmovcan to set
     */
    public void setIvatmovcan(double ivatmovcan) {
        this.ivatmovcan = ivatmovcan;
    }

    /**
     * @return the numcte
     */
    public String getNumcte() {
        return numcte;
    }

    /**
     * @param numcte the numcte to set
     */
    public void setNumcte(String numcte) {
        this.numcte = numcte;
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
     * @return the dirConsigId
     */
    public int getDirConsigId() {
        return dirConsigId;
    }

    /**
     * @param dirConsigId the dirConsigId to set
     */
    public void setDirConsigId(int dirConsigId) {
        this.dirConsigId = dirConsigId;
    }

    /**
     * @return the descuentoCliente
     */
    public Double getDescuentoCliente() {
        return descuentoCliente;
    }

    /**
     * @param descuentoCliente the descuentoCliente to set
     */
    public void setDescuentoCliente(Double descuentoCliente) {
        this.descuentoCliente = descuentoCliente;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the cotizacionEmiteFactura
     */
    public String getCotizacionEmiteFactura() {
        return cotizacionEmiteFactura;
    }

    /**
     * @param cotizacionEmiteFactura the cotizacionEmiteFactura to set
     */
    public void setCotizacionEmiteFactura(String cotizacionEmiteFactura) {
        this.cotizacionEmiteFactura = cotizacionEmiteFactura;
    }

    /**
     * @return the listaDetallePedido
     */
    public ArrayList<DetallePedido> getListaDetallePedido() {
        return listaDetallePedido;
    }

    /**
     * @param listaDetallePedido the listaDetallePedido to set
     */
    public void setListaDetallePedido(ArrayList<DetallePedido> listaDetallePedido) {
        this.listaDetallePedido = listaDetallePedido;
    }
}