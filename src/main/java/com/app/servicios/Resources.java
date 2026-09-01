/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.servicios;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Resources {
    public static final Logger logger = LogManager.getLogger(Resources.class);
    
    public Resources(){        
        logger.info("========================================================");        
        logger.info("UNA NUEVA PETICION ESTA SUCEDIENDO");        
        logger.info("========================================================");                
    }
}