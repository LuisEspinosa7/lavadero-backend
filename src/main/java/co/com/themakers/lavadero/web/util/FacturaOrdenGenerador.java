/**
 * 
 */
package co.com.themakers.lavadero.web.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import co.com.themakers.lavadero.entity.Orden;
import co.com.themakers.lavadero.entity.OrdenItem;

/**
 * @author Luis Llanos (Developer)
 *
 */
public class FacturaOrdenGenerador {
	
	private static Logger logger = LoggerFactory.getLogger(FacturaOrdenGenerador.class);

	public static ByteArrayInputStream generarFactura(Orden orden)
			throws MalformedURLException, IOException {

		/**
		 * Rectangulo para impresora de 58mm
		 */
		// Rectangle pagesize = new Rectangle(164.41f, 600);

		/**
		 * Rectangulo para impresora de 80mm
		 */
		Rectangle pagesize = new Rectangle(226.77f, 300);

		/**
		 * Configuracion del lienzo del ticket
		 */
		Document document = new Document(pagesize);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

			String fechaCreacion = fo.format(orden.getFechaCreacion());

			/**
			 * Configuracion basica de mensajes para el ticket
			 */
			String line1 = "Empresa: " + orden.getLavadero().getNombre();
			String line2 = "Nit: " + orden.getLavadero().getNit();
			String line3 = "Fecha: " + fechaCreacion;
			String line4 = "ID cliente: " + orden.getClienteVehiculo().getUsuario().getIdentificacion();
			String line5 = "Placa Vehiculo: " + orden.getClienteVehiculo().getPlaca() + "\n";
			
			String line6 = "      SERVICIO                             PRECIO   ";
			
			String lineax = "";
			BigDecimal totalServicios = new BigDecimal(0);
			
			for (OrdenItem item : orden.getItems()) {
				totalServicios = totalServicios.add(item.getPrecioServicio());
				lineax = lineax + "  " + item.getTipoServicio().getNombre().toUpperCase() + "                      $" + item.getPrecioServicio();
				lineax = lineax + "\n";
			}			
			
			lineax  = lineax + "                                              ====================";
			lineax = lineax + "\n";
			lineax  = lineax + "                                             Total: $" + totalServicios;		
			lineax = lineax + "\n";
			String lineF = "****** GRACIAS POR SU COMPRA ******";
			

			/**
			 * Configuracion para la instacia de diseño del PDF
			 */
			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.setMargins(6, 6, 12, 25);
			document.setMarginMirroring(false);

			/**
			 * Se activa el documento
			 */
			document.open();

			/**
			 * Se encarga de dibujar sobre el lienzo del PDF
			 */
			PdfContentByte contentByte = writer.getDirectContent();

			/**
			 * Rectangulos basicos construidos geometricamente
			 */
			

			// Rectangulo 
			Rectangle rectangle = new Rectangle(4, 4, 222, 294);
			// Rectangle rectangle3 = new Rectangle(llx, lly, urx, ury)
			rectangle.setBorder(Rectangle.BOX);
			contentByte.setColorStroke(BaseColor.BLACK);
			rectangle.setBorderWidth(1);
			contentByte.rectangle(rectangle);

			/**
			 * Fuente
			 */

			Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
			Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);

			/**
			 * String que une todos los mensajes
			 */
			String datosGenerales = line1 + "\n" + line2 + "\n" + line3 + "\n" + line4 + "\n" + line5 + "\n" + line6 + "\n";
					
			String datosFinales = lineF + "\n";

			/**
			 * ESTA LINEA SE DEBE CAMBIA A LA RUTA PROPIA DEL EQUIPO DE CADA DESARROLLADOR
			 */
			Image logo = Image.getInstance(Constantes.LAVADERO_FOLDER_FOR_TICKETS + orden.getLavadero().getImagen());

			/**
			 * Logo de la empresa
			 */
			logo.setAlignment(Element.ALIGN_CENTER);
			logo.scaleAbsolute(80f, 53f);
			document.add(logo);

			/**
			 * Parrafo 1
			 */
			Paragraph paragraph1 = new Paragraph(datosGenerales, font1);
			paragraph1.setAlignment(Element.ALIGN_LEFT);
			paragraph1.setLeading(14);
			document.add(paragraph1);
			document.add(Chunk.NEWLINE);
			
			
			/**
			 * Parrafo 2
			 */
			Paragraph paragraph2 = new Paragraph(lineax, font2);
			paragraph2.setAlignment(Element.ALIGN_LEFT);
			paragraph2.setLeading(10);
			document.add(paragraph2);
			document.add(Chunk.NEWLINE);
			
			/**
			 * Parrafo 3
			 */
			Paragraph paragraph3 = new Paragraph(datosFinales, font1);
			paragraph3.setAlignment(Element.ALIGN_LEFT);
			paragraph3.setLeading(14);
			document.add(paragraph3);
			document.add(Chunk.NEWLINE);

			/**
			 * Cierre
			 */
			document.close();
		} catch (DocumentException e) {
			logger.error(e.toString());
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

}
