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
import co.com.themakers.lavadero.model.ItemReportePropietario3;
import co.com.themakers.lavadero.model.ReportePropietario3;

/**
 * @author Luis Llanos (Developer)
 *
 */
public class ReportePropietario3Generador {
	
	public static ByteArrayInputStream generarReporte(ReportePropietario3 reporte) throws IOException {
		
		
		List<ItemReportePropietario3> items = reporte.getItems();
		
	    String[] COLUMNs = {"Servicio", "Realizados"};
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
	      for (ItemReportePropietario3 item : items) {
	        Row row = sheet.createRow(rowIdx++);
	   
	        row.createCell(0).setCellValue(item.getServicio());
	        row.createCell(1).setCellValue(item.getNumeroRealizados());	   
	      }
	      
	      Row rowTotal = sheet.createRow(rowIdx++); 
	      rowTotal.createCell(0).setCellValue("Total Servicios: ");
	      rowTotal.createCell(1).setCellValue(reporte.getGranTotal());
	   
	      workbook.write(out);
	      return new ByteArrayInputStream(out.toByteArray());
	    }
	  }

}
