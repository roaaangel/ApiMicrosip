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
public class StateRequest<T> {

    @NonNull
    private DataStatus status;

    @Nullable
    private T data;

    @Nullable
    private String error;

    public StateRequest() {
        this.status = DataStatus.CREATED;
        this.data = null;
        this.error = null;
    }

    public StateRequest<T> loading() {
        this.status = DataStatus.LOADING;
        this.data = null;
        this.error = null;
        return this;
    }

    public StateRequest<T> success(@NonNull T data) {
        this.status = DataStatus.SUCCESS;
        this.data = data;
        this.error = null;
        return this;
    }

    public StateRequest<T> error(@NonNull String error) {
        this.status = DataStatus.ERROR;
        this.data = null;
        this.error = error;
        return this;
    }

    public StateRequest<T> response(DataStatus dataStatus, @Nullable T data, @NonNull String error) {
        this.status = dataStatus;
        this.data = data;
        this.error = error;
        return this;
    }
    
    public StateRequest<T> complete() {
        this.status = DataStatus.COMPLETE;
        return this;
    }

    @NonNull
    public DataStatus getStatus() {
        return status;
    }

    @Nullable
    public T getData() {
        return data;
    }

    @Nullable
    public String getError() {
        return error;
    }

    public enum DataStatus {
        CREATED,
        SUCCESS,
        ERROR,
        LOADING,
        COMPLETE
    }
}