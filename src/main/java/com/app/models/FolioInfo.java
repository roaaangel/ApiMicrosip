package com.app.models;

public class FolioInfo {
    private String serie;
    private int consecutivo;

    public FolioInfo() {
        this.serie = "";
        this.consecutivo = 0;
    }

    public FolioInfo(String serie, int consecutivo) {
        this.serie = serie;
        this.consecutivo = consecutivo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }
}