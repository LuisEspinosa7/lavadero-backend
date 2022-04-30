/**
 * 
 */
package co.com.themakers.lavadero.web.reportes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import co.com.themakers.lavadero.entity.TipoServicio;

/**
 * @author Luis Llanos (Developer)
 *
 */
public class ReporteEjemplo {
	
	private static Logger logger = LoggerFactory.getLogger(ReporteEjemplo.class);

	public static ByteArrayInputStream generarReporteTiposServicios(List<TipoServicio> tiposServicios)
			throws MalformedURLException, IOException {

		logger.debug("Generando PDF .....");
		
		Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
		

		try {

			
			SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

			String fechaActual = fo.format(new Date());
			
			String line1 = "Fecha: " + fechaActual;
			String line2 = "Fecha: " + fechaActual;
			
			
			PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(60);
            table.setWidths(new int[]{1, 3, 3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Codigo", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Nombre", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Descripcion", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (TipoServicio tipoServicio : tiposServicios) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(tipoServicio.getCodigo().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(tipoServicio.getNombre()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(tipoServicio.getDescripcion())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }
            

			PdfWriter.getInstance(document, out);
			document.open();

			Font font = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
		
			String info = line1 + "\n" + line2 + "\n";

			/**
			Image logo = Image.getInstance(Constantes.LAVADERO_FOLDER_FOR_TICKETS + ticket.getLavadero().getImagen());
			logo.setAlignment(Element.ALIGN_CENTER);
			logo.scaleAbsolute(80f, 53f);
			document.add(logo);
			**/
			
			Phrase phrase = new Phrase();
            Paragraph paragraph1 = new Paragraph();
            
            Font timesRomanfont = new Font(Font.FontFamily.TIMES_ROMAN,16,Font.BOLDITALIC);
            Chunk timesRomanChunk = new Chunk("Reporte Tipos de Servicios",timesRomanfont);
            phrase.add(timesRomanChunk);
            phrase.add(Chunk.NEWLINE);
            
            paragraph1.add(phrase);
            paragraph1.setAlignment(Element.ALIGN_CENTER);
            
            document.add(paragraph1);

			
			Paragraph paragraph2 = new Paragraph(info, font);
			paragraph2.setAlignment(Element.ALIGN_LEFT);
			paragraph2.setLeading(15);
			document.add(paragraph2);
			document.add(Chunk.NEWLINE);			
			
			document.add(table);
			
			document.close();
		} catch (DocumentException e) {
			logger.error(e.toString());
		}

		return new ByteArrayInputStream(out.toByteArray());
	}


}
