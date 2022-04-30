/**
 * 
 */
package co.com.themakers.lavadero.web.reportesExcel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

import co.com.themakers.lavadero.model.ItemReporteAdmin4;
import co.com.themakers.lavadero.model.ItemReportePropietario1;
import co.com.themakers.lavadero.model.ReporteAdmin4;
import co.com.themakers.lavadero.model.ReportePropietario1;

/**
 * @author Luis Llanos (Developer)
 *
 */
public class ReportePropietario1Generador {
	
	public static ByteArrayInputStream generarReporte(ReportePropietario1 reporte) throws IOException {
		
		
		List<ItemReportePropietario1> items = reporte.getItems();
		
	    String[] COLUMNs = {"Identificacion", "Nombre", "Apellido", "N° Servicios"};
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
	   
	      int rowIdx = 1;
	      for (ItemReportePropietario1 item : items) {
	        Row row = sheet.createRow(rowIdx++);
	   
	        row.createCell(0).setCellValue(item.getUsuario().getIdentificacion());
	        row.createCell(1).setCellValue(item.getUsuario().getNombre1());
	        row.createCell(2).setCellValue(item.getUsuario().getApellido1());
	        row.createCell(3).setCellValue(item.getNumeroServiciosSolicitados());
	   
	      }
	      
	      Row rowTotal = sheet.createRow(rowIdx++); 
	      rowTotal.createCell(0).setCellValue("Total Servicios: ");
	      rowTotal.createCell(1).setCellValue("");
	      rowTotal.createCell(2).setCellValue("");
	      rowTotal.createCell(3).setCellValue(reporte.getGranTotal());
	   
	      workbook.write(out);
	      return new ByteArrayInputStream(out.toByteArray());
	    }
	  }

}
