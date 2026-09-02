/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.models;

/**
 *
 * @author angelholberg
 */
public class TrimestreAnioInfo {
    private int trimestre;
    private int anio;

    public TrimestreAnioInfo() {
        
    
    }
    
    public TrimestreAnioInfo(int trimestre, int anio) {
        this.trimestre = trimestre;
        this.anio = anio;
    }

    public int getTrimestre() { return trimestre; }
    public int getAnio() { return anio; }
}
