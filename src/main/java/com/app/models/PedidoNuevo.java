/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.models;

import java.sql.Date;

/**
 *
 * @author burtebony
 */
public class PedidoNuevo {
    private int pedidosNuevos;    
    private double totalMontoPedido;

    /**
     * @return the pedidosNuevos
     */
    public int getPedidosNuevos() {
        return pedidosNuevos;
    }

    /**
     * @param pedidosNuevos the pedidosNuevos to set
     */
    public void setPedidosNuevos(int pedidosNuevos) {
        this.pedidosNuevos = pedidosNuevos;
    }

    /**
     * @return the totalMontoPedido
     */
    public double getTotalMontoPedido() {
        return totalMontoPedido;
    }

    /**
     * @param totalMontoPedido the totalMontoPedido to set
     */
    public void setTotalMontoPedido(double totalMontoPedido) {
        this.totalMontoPedido = totalMontoPedido;
    }
}
