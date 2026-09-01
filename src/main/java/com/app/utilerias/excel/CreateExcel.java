/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.utilerias.excel;

import com.app.models.ClientePOPSencillo;
import java.io.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class CreateExcel {
    private static final Logger logger = LoggerFactory.getLogger(CreateExcel.class);

    public byte[] generaExcelPOP(List<ClientePOPSencillo> listaClientesPOP) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Clientes POP");
            createHeaderPOP(sheet, workbook);

            int rowNum = 1;
            for (ClientePOPSencillo item : listaClientesPOP) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(item.getClienteId());
                row.createCell(1).setCellValue(item.getClaveCliente());
                row.createCell(2).setCellValue(item.getNombreCliente());
                row.createCell(3).setCellValue(item.getNombreVendedor());                               
                row.createCell(4).setCellValue(item.getImporteSinImpuestos());
                row.createCell(5).setCellValue(item.getImporteConImpuestos());
                row.createCell(6).setCellValue(item.getImporteDoctoFteSinImpuestos());
                row.createCell(7).setCellValue(item.getTipoCliente());
                row.createCell(8).setCellValue(item.getDiasPlazo());
                row.createCell(9).setCellValue(item.getSumatoriaDiasTardadosDocumentos());
                row.createCell(10).setCellValue(item.getNumeroDocumentos());
                row.createCell(11).setCellValue(item.getPromedioPonderado());
                row.createCell(12).setCellValue(item.getImportePOPGanado());
                row.createCell(13).setCellValue(item.getImportePOPGanadoAjustes());
                row.createCell(14).setCellValue(item.getMontoMinimoVenta());
            }

            // Exportar a byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            byte[] excelData = outputStream.toByteArray();

            logger.info("Excel generado en memoria con tamaño: {} bytes", excelData.length);
            return excelData;

        } catch (Exception e) {
            logger.error("Error al generar el Excel POP", e);
            return null;
        }
    }
 
    private void createHeaderPOP(Sheet sheet, Workbook workbook) {
        String[] headings = {
            "Cliente_Id", "Clave_Cliente", "Nombre_Cliente", "Vendedor", "Importe_Sin_Impuestos",
            "Importe_Con_Impuestos", "Importe_Docto_Fte_Sin_Impuestos", "Tipo_Cliente",
            "Dias_Plazo", "SUM(Dias_Tardados_Docto_Liquidado)", "Número_Documentos",
            "Promedio_Ponderado", "Importe_POP_Restante", "Importe_POP_Ganado", "Monto minímo de venta"
        };

        Row row = sheet.createRow(0);
        CellStyle style = createHeaderStyle(workbook);

        for (int i = 0; i < headings.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(headings[i]);
            cell.setCellStyle(style);
        }
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }
}
