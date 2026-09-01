/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.utilerias;

import com.google.firebase.internal.NonNull;
import javax.annotation.Nullable;

/**
 *
 * @author angel
 */
public class ResponseRequest<T> {
    @NonNull
    private DataStatus status;

    @Nullable
    private T data;

    @Nullable
    private String mensaje;

    public ResponseRequest<T> response(DataStatus dataStatus, @Nullable T data, @NonNull String mensaje) {
        this.status = dataStatus;
        this.data = data;
        this.mensaje = mensaje;
        return this;
    }

    public DataStatus getStatus() {
        return status;
    }

    public void setStatus(DataStatus status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public String getMensaje() {
        return mensaje;
    }


    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public enum DataStatus {
        OK(200),
        NO_CONTENT(204),
        ERROR(500);

        private int label;

        private DataStatus(int label) {
            this.label = label;
        }

        public int getLabel() {
            return label;
        }
    }
}
