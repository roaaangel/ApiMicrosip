package com.app.models;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author angel
 */
public class ArticuloAlmacenRefactor {
    @SerializedName("A")
    private int articulo_id;
    
    @SerializedName("B")
    private int almacen_id;

    /**
     * @return the articulo_id
     */
    public int getArticulo_id() {
        return articulo_id;
    }

    /**
     * @param articulo_id the articulo_id to set
     */
    public void setArticulo_id(int articulo_id) {
        this.articulo_id = articulo_id;
    }

    /**
     * @return the almacen_id
     */
    public int getAlmacen_id() {
        return almacen_id;
    }

    /**
     * @param almacen_id the almacen_id to set
     */
    public void setAlmacen_id(int almacen_id) {
        this.almacen_id = almacen_id;
    }
}
