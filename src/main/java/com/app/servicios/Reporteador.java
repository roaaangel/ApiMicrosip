/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.servicios;

import com.app.dao.Controlador;
import com.app.models.pop.ClientePOP;
import com.app.models.pop.POPDetalle;
import com.app.utilerias.ResponseRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

public class Reporteador {

    String POP_JRXML = "popMicrosip.jrxml";

    public Boolean generatePdfPOP(String pathFile, String tipoArchivo, int anioSeleccionado, int numeroTrimestreSeleccionado) {
        URL url = getClass().getClassLoader().getResource(POP_JRXML);
        POP_JRXML = url.getPath();
        POP_JRXML = POP_JRXML.replace("%20", " ");
        
        //If it starts with /, cut it off.
        if (POP_JRXML.startsWith("/")) POP_JRXML = POP_JRXML.substring(1, POP_JRXML.length());            
        
        Resources.logger.info("RUTA: " + POP_JRXML);

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("NOMBRE", "ANGEL HOLBERG");
        try {
            InputStream is = null;
            is = new FileInputStream(POP_JRXML);
            JasperReport report = null;
                       
            report = JasperCompileManager.compileReport(is);            
            
            Controlador controlador = new Controlador();
            ResponseRequest responseRequest = null;
            try {
                responseRequest = controlador.clientesPOP(anioSeleccionado, numeroTrimestreSeleccionado);
            } catch (SQLException ex) {
                Resources.logger.error("SUCEDIO UNA EXEPCION SQLException 2: " + ex.getMessage());  
            }
            
            Resources.logger.error("clientesPOP: ");  
            
            if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK) {
                List<POPDetalle> listaPOPDetalle = (List<POPDetalle>) responseRequest.getData();
                
                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaPOPDetalle);
                JasperPrint jasperPrint = null;
                
                jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);
                
                removeBlankPage(jasperPrint.getPages());
                
                
                switch (tipoArchivo) {
                    case ".pdf": {
                        Resources.logger.error("antes de generar el pdf: ");  
                        JasperExportManager.exportReportToPdfFile(jasperPrint, pathFile);
                        
                        break;
                    }
                    case ".xlsx": {
                        File destFile = new File(pathFile);
                        JRXlsxExporter exporter = new JRXlsxExporter();
                        SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
                        reportConfigXLS.setSheetNames(new String[]{"Sheet1"});
                        exporter.setConfiguration(reportConfigXLS);
                        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
                        reportConfigXLS.setOnePagePerSheet(false);
                        exporter.setConfiguration(reportConfigXLS);
                        
                        System.out.println("EXCEL");
                        
                        exporter.exportReport();
                        
                        break;
                    }
                }
            }
            return true;
        } catch (FileNotFoundException ex) {
            Resources.logger.error("SUCEDIO UNA EXEPCION FileNotFoundException 2: " + ex.getMessage());       
        } catch (JRException ex) {
            Resources.logger.error("SUCEDIO UNA EXEPCION JRException: " + ex.getMessage());
        }
        return null;
    }

    private void removeBlankPage(List<JRPrintPage> pages) {
        for (Iterator<JRPrintPage> i = pages.iterator(); i.hasNext();) {
            JRPrintPage page = i.next();
            if (page.getElements().size() == 0) {
                i.remove();
            }
        }
    }
}
