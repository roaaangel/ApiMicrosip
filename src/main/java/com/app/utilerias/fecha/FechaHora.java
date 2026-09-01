/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.utilerias.fecha;

import com.app.utilerias.Utileria;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author burtebony
 */
public class FechaHora extends Utileria{
    private String DIA_INICIAL = "01";
    
    public FechaHora(){
        fechaActual = Calendar.getInstance();
    }
    
    public Fecha fechaInicioFin(){        
        int anio = fechaActual.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH) + 1;
        
        Calendar calendar = new GregorianCalendar(anio, mes, 1);
        int diasDelMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        Fecha fecha = new Fecha();
        
        String fechaInicioMes = mes + "/" + DIA_INICIAL + "/" + anio;
        //String fechaInicioMes = "01" + "/" + DIA_INICIAL + "/" + anio;
        convierteStringAFecha(fechaInicioMes);
        fecha.setFechaInicio(convierteStringAFecha(fechaInicioMes));
        
        String fechaFinMes = mes + "/" + diasDelMes + "/" + anio;        
        //String fechaFinMes = "01" + "/" + "31" + "/" + anio;        
        fecha.setFechaFin(convierteStringAFecha(fechaFinMes));
        
        
        //System.out.println("Fecha inicio: " + fecha.getFechaInicio());
        //System.out.println("Fecha fin: " + fecha.getFechaFin());
        return fecha;
    }
   
    public java.sql.Date convierteStringAFecha (String fechaString){        
        try {
            dateUtil = simpleDateFormat.parse(fechaString);
            dateSQL = new java.sql.Date(dateUtil.getTime());    
            return dateSQL;
        } catch (ParseException ex) {
            Logger.getLogger(Utileria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}