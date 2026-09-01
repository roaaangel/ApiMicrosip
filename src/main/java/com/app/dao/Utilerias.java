/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import com.app.servicios.Resources;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author angel
 */
public class Utilerias {
    public String getConfiguracion(){             
        try{              
            String CONFIG_FILE_NAME = "configuration.properties";
            URL url = getClass().getClassLoader().getResource(CONFIG_FILE_NAME);   
            
            String rutaAbsoluta = url.getPath();
            rutaAbsoluta = rutaAbsoluta.replace("%20", " ");
        
            //If it starts with /, cut it off.
            if (rutaAbsoluta.startsWith("/")) rutaAbsoluta = rutaAbsoluta.substring(1, rutaAbsoluta.length());  

            Resources.logger.info("rutaAbsoluta: " + rutaAbsoluta);
            Properties prop = null;
            prop = new Properties();              
            try {
                prop.load(new FileInputStream(rutaAbsoluta)); 
                return prop.getProperty("REPOSITORIO");                
            } catch (IOException ex) {
                
            }                                        
        }catch(Exception ex){            
            return null;
        }
        return null;
    }
    
    public String convertDate(Date fechaToConvert){
        try
        {
            Calendar calendario = GregorianCalendar.getInstance();
            Date fecha = fechaToConvert;            
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("MM/dd/yyyy");
            //System.out.println("****"+formatoDeFecha.format(fecha));
            return formatoDeFecha.format(fecha);
        }catch(Exception ex){
            return null;
        }
    }
    
    public java.sql.Date convertStringToDate(String fechaToConvert){     
        try {    
              SimpleDateFormat DOB = new SimpleDateFormat("MM/dd/yyyy");
              java.util.Date date = DOB.parse(fechaToConvert);
              java.sql.Date sqlDate = new Date(date.getTime());                             
              return sqlDate;
        } catch (Exception ex) {
              System.out.println("Error al obtener el formato de la fecha/hora: " + ex.getMessage());
        }
        return null;
    }
    
    public java.sql.Date convertStringToDate2(String fechaToConvert){     
        try {    
              SimpleDateFormat DOB = new SimpleDateFormat("dd/MM/yyyy");
              java.util.Date date = DOB.parse(fechaToConvert);
              java.sql.Date sqlDate = new Date(date.getTime());                             
              return sqlDate;
        } catch (Exception ex) {
              System.out.println("Error al obtener el formato de la fecha/hora: " + ex.getMessage());
        }
        return null;
    }
    
    public String convertDateToString2(java.sql.Date fechaToConvert){     
        try {    
              SimpleDateFormat DOB = new SimpleDateFormat("dd/MM/yyyy");
                         
              return DOB.format(fechaToConvert);
        } catch (Exception ex) {
              System.out.println("Error al obtener el formato de la fecha/hora: " + ex.getMessage());
        }
        return null;
    }

    public String convertTime(Time timeToConvert){
        try
        {
            String time = timeToConvert.toString();
            DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            java.util.Date date = sdf.parse(time);                      
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("HH:mm:ss");
            return formatoDeFecha.format(date);
        }catch(Exception ex){
            return null;
        }
    }
    
    public java.sql.Time convertStringToTime(String timeToConvert){
        try {         
            String[] arrayBusqueda = timeToConvert.split(":");
            timeToConvert = Integer.parseInt(arrayBusqueda[0]) - 1 + ":" + arrayBusqueda[1] + ":" + arrayBusqueda[2];
            java.sql.Time fecFormatoTime = null;
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss", new Locale("es", "ES"));
            fecFormatoTime = new java.sql.Time(sdf.parse(timeToConvert).getTime());

            System.out.println("Fecha con el formato java.sql.Time: " + fecFormatoTime);
            return fecFormatoTime;
        } catch (Exception ex) {
              System.out.println("Error al obtener el formato de la fecha/hora: " + ex.getMessage());
        }
        return null;
    }    

    public String diaDeLaSemana(){
	Calendar now = Calendar.getInstance();

	// Array con los dias de la semana
	String[] strDays = new String[]{"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
 
        System.out.println("Hoy es : " + strDays[now.get(Calendar.DAY_OF_WEEK) - 1].toUpperCase());
	// El dia de la semana inicia en el 1 mientras que el array empieza en el 0
	return strDays[now.get(Calendar.DAY_OF_WEEK) - 1];
    }
    
    public Timestamp getNowDateHourTimestamp(){
        try {
            java.util.Date today = new java.util.Date();
            Timestamp timestamp = new java.sql.Timestamp(today.getTime());     
            return timestamp;
        } catch (Exception exception) {
            Resources.logger.error("Excepcion en getNowDateHourTimestamp: " + exception.getMessage());      
        }
        return null;
    } 
    
    public String timeStampToString(Timestamp fechaHora) {
        SimpleDateFormat sdf =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String fecha = sdf.format(fechaHora);
        return fecha;
    }
}
