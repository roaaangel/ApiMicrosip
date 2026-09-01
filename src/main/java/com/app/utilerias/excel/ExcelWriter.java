/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.utilerias.excel;

import com.app.contants.ConnectionClient;
import com.app.dao.Controlador;
import com.app.models.LocalizacionMaps;
import com.app.servicios.Resources;
import com.app.utilerias.ResponseRequest;
import com.google.gson.Gson;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

    private static String[] columns = {"Nombre cliente", "Latitud", "Longitud", "Fecha", "Hora", "Proceso", "Nombre vendedor", "Monto realizado"};
    private static List<LocalizacionMaps> contacts = new ArrayList<LocalizacionMaps>();

    public void excel(int vendedorId, String fechaInicial, String fechaFinal) throws IOException,
        InvalidFormatException,
        SQLException {
        Controlador controlador = new Controlador();
        Gson gson = new Gson();
        
        
        ResponseRequest responseRequest = controlador.getLocalizacionMaps(vendedorId, fechaInicial, fechaFinal);
        ArrayList<LocalizacionMaps> listaLocalizacionMaps = new ArrayList<LocalizacionMaps>();
        if (responseRequest.getStatus() == ResponseRequest.DataStatus.OK) {
            listaLocalizacionMaps = (ArrayList<LocalizacionMaps>) responseRequest.getData();

        } else if (responseRequest.getStatus() == ResponseRequest.DataStatus.ERROR) {
            String data = responseRequest.getMensaje();

        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("LocalizacionMapss");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Other rows and cells with contacts data
        int rowNum = 1;

        for (LocalizacionMaps contact : listaLocalizacionMaps) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(contact.getNombre_cliente());
            row.createCell(1).setCellValue(contact.getLatitud());
            row.createCell(2).setCellValue(contact.getLongitud());
            row.createCell(3).setCellValue(contact.getFecha());
            row.createCell(4).setCellValue(contact.getHora());
            row.createCell(5).setCellValue(contact.getProceso());
            row.createCell(6).setCellValue(contact.getNombre_vendedor());
            row.createCell(7).setCellValue(contact.getMonto_realizado());
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        try {
        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(ConnectionClient.RUTA_EXCEL);
        workbook.write(fileOut);
        fileOut.close();
        }catch (Exception exception) {
            Resources.logger.error("Error al crear excel: " + exception.getMessage());
        }
    }

}
