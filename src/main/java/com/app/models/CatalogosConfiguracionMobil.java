/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

import com.app.models.almacenes.Almacen;
import com.app.models.sucursales.Sucursal;
import java.util.List;

/**
 *
 * @author burtebony
 */
public class CatalogosConfiguracionMobil {
    private List<Sucursal> sucursalesEmpresa;
    private List<PrecioEmpresa> preciosEmpresa;
    private List<ConceptoCuentaXCobrar> conceptosCuentasXCobrar;
    private List<CondicionPago> condicionesDePago;
    private List<RolClaveArticulo> rolesClavesArticulos;
    private List<Almacen> catalogoAlmacenes;   

    /**
     * @return the sucursalesEmpresa
     */
    public List<Sucursal> getSucursalesEmpresa() {
        return sucursalesEmpresa;
    }

    /**
     * @param sucursalesEmpresa the sucursalesEmpresa to set
     */
    public void setSucursalesEmpresa(List<Sucursal> sucursalesEmpresa) {
        this.sucursalesEmpresa = sucursalesEmpresa;
    }

    /**
     * @return the preciosEmpresa
     */
    public List<PrecioEmpresa> getPreciosEmpresa() {
        return preciosEmpresa;
    }

    /**
     * @param preciosEmpresa the preciosEmpresa to set
     */
    public void setPreciosEmpresa(List<PrecioEmpresa> preciosEmpresa) {
        this.preciosEmpresa = preciosEmpresa;
    }

    /**
     * @return the conceptosCuentasXCobrar
     */
    public List<ConceptoCuentaXCobrar> getConceptosCuentasXCobrar() {
        return conceptosCuentasXCobrar;
    }

    /**
     * @param conceptosCuentasXCobrar the conceptosCuentasXCobrar to set
     */
    public void setConceptosCuentasXCobrar(List<ConceptoCuentaXCobrar> conceptosCuentasXCobrar) {
        this.conceptosCuentasXCobrar = conceptosCuentasXCobrar;
    }

    /**
     * @return the condicionesDePago
     */
    public List<CondicionPago> getCondicionesDePago() {
        return condicionesDePago;
    }

    /**
     * @param condicionesDePago the condicionesDePago to set
     */
    public void setCondicionesDePago(List<CondicionPago> condicionesDePago) {
        this.condicionesDePago = condicionesDePago;
    }

    /**
     * @return the rolesClavesArticulos
     */
    public List<RolClaveArticulo> getRolesClavesArticulos() {
        return rolesClavesArticulos;
    }

    /**
     * @param rolesClavesArticulos the rolesClavesArticulos to set
     */
    public void setRolesClavesArticulos(List<RolClaveArticulo> rolesClavesArticulos) {
        this.rolesClavesArticulos = rolesClavesArticulos;
    }

    /**
     * @return the catalogoAlmacenes
     */
    public List<Almacen> getCatalogoAlmacenes() {
        return catalogoAlmacenes;
    }

    /**
     * @param catalogoAlmacenes the catalogoAlmacenes to set
     */
    public void setCatalogoAlmacenes(List<Almacen> catalogoAlmacenes) {
        this.catalogoAlmacenes = catalogoAlmacenes;
    }
}
