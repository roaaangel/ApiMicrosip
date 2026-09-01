/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models.vendedorescobradores;

import com.app.models.cobradores.Cobrador;
import com.app.models.vendedores.Vendedor;
import java.util.List;

/**
 *
 * @author burtebony
 */
public class VendedorCobrador {
    private List<Vendedor> listaVendedores;
    private List<Cobrador> listaCobradores;

    /**
     * @return the listaVendedores
     */
    public List<Vendedor> getListaVendedores() {
        return listaVendedores;
    }

    /**
     * @param listaVendedores the listaVendedores to set
     */
    public void setListaVendedores(List<Vendedor> listaVendedores) {
        this.listaVendedores = listaVendedores;
    }

    /**
     * @return the listaCobradores
     */
    public List<Cobrador> getListaCobradores() {
        return listaCobradores;
    }

    /**
     * @param listaCobradores the listaCobradores to set
     */
    public void setListaCobradores(List<Cobrador> listaCobradores) {
        this.listaCobradores = listaCobradores;
    }
}
