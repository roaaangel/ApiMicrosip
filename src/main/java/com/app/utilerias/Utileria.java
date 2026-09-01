/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.utilerias;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author burtebony
 */
public class Utileria {   
    protected SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    protected java.util.Date dateUtil;
    protected java.sql.Date dateSQL;
    protected Calendar fechaActual;    
}
