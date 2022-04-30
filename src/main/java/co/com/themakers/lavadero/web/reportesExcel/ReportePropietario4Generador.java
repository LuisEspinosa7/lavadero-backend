/**
 * 
 */
package co.com.themakers.lavadero.web.reportesExcel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import co.com.themakers.lavadero.entity.LiquidacionFuncionario;
import co.com.themakers.lavadero.model.ItemReportePropietario3;
import co.com.themakers.lavadero.model.ReportePropietario3;
import co.com.themakers.lavadero.model.ReportePropietario4;

/**
 * @author Luis Llanos (Developer)
 *
 */
public class ReportePropietario4Generador {
	
	public static ByteArrayInputStream generarReporte(ReportePropietario4 reporte) throws IOException {
		
		
		List<LiquidacionFuncionario> items = reporte.getItems();
		
	    String[] COLUMNs = {"Nombre", "Apellido", "Identificacion", "Fecha Liquidacion", "Fecha Inicio", "Fecha Fin", "Pago" };
	    try(
	        Workbook workbook = new XSSFWorkbook();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ){
	      CreationHelper createHelper = workbook.getCreationHelper();
	   
	      Sheet sheet = workbook.createSheet("Reporte");
	   
	      Font headerFont = workbook.createFont();
	      headerFont.setBold(true);
	      headerFont.setColor(IndexedColors.BLUE.getIndex());
	   
	      CellStyle headerCellStyle = workbook.createCellStyle();
	      headerCellStyle.setFont(headerFont);
	   
	      // Row for Header
	      Row headerRow = sheet.createRow(0);
	   
	      // Header
	      for (int col = 0; col < COLUMNs.length; col++) {
	        Cell cell = headerRow.createCell(col);
	        cell.setCellValue(COLUMNs[col]);
	        cell.setCellStyle(headerCellStyle);
	      }
	   
	      // CellStyle for Age
	      CellStyle ageCellStyle = workbook.createCellStyle();
	      ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
	   
	      
	      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      
	      
	      int rowIdx = 1;
	      for (LiquidacionFuncionario item : items) {
	        Row row = sheet.createRow(rowIdx++);
	   
	        row.createCell(0).setCellValue(item.getFuncionarioServicio().getUsuario().getNombre1());
	        row.createCell(1).setCellValue(item.getFuncionarioServicio().getUsuario().getApellido1());	  
	        row.createCell(2).setCellValue(item.getFuncionarioServicio().getUsuario().getIdentificacion());	
	        row.createCell(3).setCellValue(formatter.format(item.getFechaCreacion()));	
	        row.createCell(4).setCellValue(formatter.format(item.getFechaInicio()));
	        row.createCell(5).setCellValue(formatter.format(item.getFechaFin()));
	        row.createCell(6).setCellValue(item.getValorPago().toString());
	      }
	      
	      Row rowTotal = sheet.createRow(rowIdx++); 
	      rowTotal.createCell(0).setCellValue("Total Pagos: ");
	      rowTotal.createCell(1).setCellValue("");
	      rowTotal.createCell(2).setCellValue("");
	      rowTotal.createCell(3).setCellValue("");
	      rowTotal.createCell(4).setCellValue("");
	      rowTotal.createCell(5).setCellValue("");
	      rowTotal.createCell(6).setCellValue(reporte.getGranTotal().toString());
	   
	      workbook.write(out);
	      return new ByteArrayInputStream(out.toByteArray());
	    }
	  }

}
