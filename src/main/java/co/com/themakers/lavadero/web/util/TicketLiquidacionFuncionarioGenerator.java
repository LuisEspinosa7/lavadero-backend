/**
 * 
 */
package co.com.themakers.lavadero.web.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

import co.com.themakers.lavadero.entity.LiquidacionFuncionario;


/**
 * @author ASUS
 *
 */
public class TicketLiquidacionFuncionarioGenerator {

	private static Logger logger = LoggerFactory.getLogger(TicketLiquidacionFuncionarioGenerator.class);

	public static ByteArrayInputStream generarTicketPDF(LiquidacionFuncionario ticket)
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

			String fechaInicio = fo.format(ticket.getFechaInicio());
			String fechaFin = fo.format(ticket.getFechaFin());

			/**
			 * Configuracion basica de mensajes para el ticket
			 */
			String line1 = "Empresa: " + ticket.getLavadero().getNombre();
			String line2 = "Trabajador: " + ticket.getFuncionarioServicio().getUsuario().getNombre1() + " "
					+ ticket.getFuncionarioServicio().getUsuario().getApellido1();
			String line3 = "Identificacion: " + ticket.getFuncionarioServicio().getUsuario().getIdentificacion();
			String line4 = "Servicio: " + ticket.getFuncionarioServicio().getTipoServicio().getNombre();
			String line5 = "Liquidacion: " + ticket.getFuncionarioServicio().getTipoLiquidacion().getNombre();
			String line6 = "Fecha Inicial: " + fechaInicio;
			String line7 = "Fecha Final: " + fechaFin;

			String a = ticket.getFuncionarioServicio().getTipoPago().getCodigo() == 1 ? "$" : "";
			String b = ticket.getFuncionarioServicio().getTipoPago().getCodigo() == 2 ? "%" : "";
			String c = a + ticket.getFuncionarioServicio().getValorPago() + b;

			String line8 = "Tipo Pago: " + ticket.getFuncionarioServicio().getTipoPago().getNombre() + ": " + c;
			String line9 = "Valor Servicio $: " + ticket.getValorServicio();
			String line10 = "N° Servicios Prestados: " + ticket.getNumeroServicios();
			String line11 = "Pago Total: $" + ticket.getValorPago();

			String line12 = "Firma Trabajador: ";

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
			// Rectangulo 1
			Rectangle rectangle = new Rectangle(4, 63, 222, 294);
			// Rectangle rectangle3 = new Rectangle(llx, lly, urx, ury)
			rectangle.setBorder(Rectangle.BOX);
			contentByte.setColorStroke(BaseColor.BLACK);
			rectangle.setBorderWidth(1);
			contentByte.rectangle(rectangle);

			// Rectangulo 2
			Rectangle rectangle2 = new Rectangle(4, 4, 222, 63);
			// Rectangle rectangle3 = new Rectangle(llx, lly, urx, ury)
			rectangle2.setBorder(Rectangle.BOX);
			contentByte.setColorStroke(BaseColor.BLACK);
			rectangle2.setBorderWidth(1);
			contentByte.rectangle(rectangle2);

			/**
			 * Fuente
			 */

			Font font = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

			/**
			 * String que une todos los mensajes
			 */
			String infoTicket = line1 + "\n" + line2 + "\n" + line3 + "\n" + line4 + "\n" + line5 + "\n" + line6 + "\n"
					+ line7 + "\n" + line8 + "\n" + line9 + "\n" + line10 + "\n" + line11 + "\n\n" + line12;

			/**
			 * ESTA LINEA SE DEBE CAMBIA A LA RUTA PROPIA DEL EQUIPO DE CADA DESARROLLADOR
			 */
			Image logo = Image.getInstance(Constantes.LAVADERO_FOLDER_FOR_TICKETS + ticket.getLavadero().getImagen());

			/**
			 * Logo de la empresa
			 */
			logo.setAlignment(Element.ALIGN_CENTER);
			logo.scaleAbsolute(80f, 53f);
			document.add(logo);

			/**
			 * Parrafo
			 */
			Paragraph paragraph = new Paragraph(infoTicket, font);
			paragraph.setAlignment(Element.ALIGN_LEFT);
			paragraph.setLeading(14);
			document.add(paragraph);
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
