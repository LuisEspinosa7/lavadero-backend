/**
 * 
 */
package co.com.themakers.lavadero.web.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.themakers.lavadero.entity.ItemFuncionario;
import co.com.themakers.lavadero.entity.Lavadero;
import co.com.themakers.lavadero.entity.LavaderoServicio;
import co.com.themakers.lavadero.entity.LiquidacionFuncionario;
import co.com.themakers.lavadero.entity.Orden;
import co.com.themakers.lavadero.entity.OrdenItem;
import co.com.themakers.lavadero.entity.TipoServicio;
import co.com.themakers.lavadero.entity.Usuario;
import co.com.themakers.lavadero.model.ItemReporteAdmin2;
import co.com.themakers.lavadero.model.ItemReporteAdmin4;
import co.com.themakers.lavadero.model.ItemReportePropietario1;
import co.com.themakers.lavadero.model.ItemReportePropietario2;
import co.com.themakers.lavadero.model.ItemReportePropietario3;
import co.com.themakers.lavadero.model.ModelReporte;
import co.com.themakers.lavadero.model.ReporteAdmin2;
import co.com.themakers.lavadero.model.ReporteAdmin4;
import co.com.themakers.lavadero.model.ReportePropietario1;
import co.com.themakers.lavadero.model.ReportePropietario2;
import co.com.themakers.lavadero.model.ReportePropietario3;
import co.com.themakers.lavadero.model.ReportePropietario4;
import co.com.themakers.lavadero.model.Respuesta;
import co.com.themakers.lavadero.repository.LavaderoRepository;
import co.com.themakers.lavadero.repository.LavaderoServicioRepository;
import co.com.themakers.lavadero.repository.OrdenRepository;
import co.com.themakers.lavadero.repository.TipoServicioRepository;
import co.com.themakers.lavadero.repository.UsuarioRepository;
import co.com.themakers.lavadero.service.LiquidacionFuncionarioService;
import co.com.themakers.lavadero.web.reportes.ReporteEjemplo;
import co.com.themakers.lavadero.web.reportesExcel.ReporteAdmin2Generador;
import co.com.themakers.lavadero.web.reportesExcel.ReporteAdmin4Generador;
import co.com.themakers.lavadero.web.reportesExcel.ReportePropietario1Generador;
import co.com.themakers.lavadero.web.reportesExcel.ReportePropietario2Generador;
import co.com.themakers.lavadero.web.reportesExcel.ReportePropietario3Generador;
import co.com.themakers.lavadero.web.reportesExcel.ReportePropietario4Generador;
import co.com.themakers.lavadero.web.util.Constantes;
import co.com.themakers.lavadero.web.util.CustomOperations;
import co.com.themakers.lavadero.web.util.Mensajes;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

/**
 * @author Luis Llanos (Developer)
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/reportes")
public class ReportesRestController {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(ReportesRestController.class);	
	
	private final String reportes_admin_ruta = "/jasper/admin/";
	// private static final String logo_path =
	// "/jasper/images/stackextend-logo.png";
	// private final String invoice_template = "/jasper/invoice_template.jrxml";
	private final String invoice_template = "/jasper/reporte-ejemplo.jrxml";
	private final String path_template_3 = "/jasper/pdf_rest_resource.jrxml";

	@Autowired
	TipoServicioRepository tipoServicioRepository;

	@Autowired
	LavaderoRepository lavaderoRepository;

	@Autowired
	OrdenRepository ordenRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	LavaderoServicioRepository lavaderoServicioRepository;
	
	@Autowired
	LiquidacionFuncionarioService liquidacionFuncionarioService;
	

	// ######################################################################################################################
	// ######################### ADMINISTRADOR ############################################################################## 
	// ######################################################################################################################

	// -----------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------
	// ------------------------------- REPORTE 1 ----------------------------------------------------------- 
	// -----------------------------------------------------------------------------------------------------

	/**
	 * Reporte 1 - cantidad de empresas que estén vinculadas
	 * 
	 * @return Respuesta
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/admin/reporte1/informacion", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> adminReporte1Informacion() {
		logger.debug("Admin Reporte 1 Informacion Controlador ....");

		List<Lavadero> lavaderosDisponibles = lavaderoRepository.listarLavaderosDisponibles(Constantes.ESTADO_ACTIVO);

		int cantidad = lavaderosDisponibles.size();

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.BUSQUEDA_CORRECTA);
		respuesta.setData(cantidad);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	

	// -----------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------
	// ------------------------------- REPORTE 2 ----------------------------------------------------------- 
	// -----------------------------------------------------------------------------------------------------

	/**
	 * Reporte 2 - La facturación de cada lavadero y La Facturación de las comisiones.
	 * 
	 * @return Respuesta
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/admin/reporte2/informacion", method = RequestMethod.POST)
	public ResponseEntity<Respuesta> adminReporte2Informacion(
			@RequestParam(value = "fechaHoraInicio") String fechaHoraInicioStr,
			@RequestParam(value = "fechaHoraFin") String fechaHoraFinStr) {
		logger.debug("Admin Reporte 2 Informacion Controlador ....");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date fechaHoraInicio = null;
		Date fechaHoraFin = null;

		try {
			fechaHoraInicio = formatter.parse(fechaHoraInicioStr);
			fechaHoraFin = formatter.parse(fechaHoraFinStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Orden> ordenes = ordenRepository.listarOrdenesXDia(fechaHoraInicio, fechaHoraFin);

		// Facturacion
		Map<Long, BigDecimal> d = new HashMap<Long, BigDecimal>();		
		
		//Comisiones
		Map<Long, BigDecimal> comisiones = new HashMap<Long, BigDecimal>();	
		
		BigDecimal precioServicioActual = new BigDecimal(0);
		BigDecimal precioComisionIndividual = new BigDecimal(0);

		for (Orden orden : ordenes) {
			logger.debug("==== Iterando en Orden...");

			Long codigoLavadero = orden.getLavadero().getCodigo();
			logger.debug("==== Codigo Lavadero: " + codigoLavadero);			
			
			BigDecimal precioItems = new BigDecimal(0);				
			
			for (OrdenItem ordenItem : orden.getItems()) {
				logger.debug("==== Iterando en Orden Item...");		
				
				precioItems = precioItems.add(ordenItem.getPrecio());
				
				precioServicioActual = ordenItem.getPrecio();
				
				//Carga configuracion de la comision
				LavaderoServicio configuracion = lavaderoServicioRepository.buscarXLavaderoYTipoServicioYestado(codigoLavadero, ordenItem.getTipoServicio().getCodigo()).get(0);
				
				if(configuracion.getAplicaComision() == 1) {
					precioComisionIndividual = CustomOperations.percentage(precioServicioActual, configuracion.getValorComision());
				} else {
					precioComisionIndividual = new BigDecimal(0);
				}		
				
			}
			
			
			//Ingresar Facturacion
			if (d.containsKey(codigoLavadero)) {
				logger.debug("==== Se suma al codigo: " + codigoLavadero + " - La suma de: " + precioItems);
				BigDecimal val = (BigDecimal) d.get(codigoLavadero);
				d.put(codigoLavadero, val.add(precioItems));
			} else {
				logger.debug("==== Nuevo codigo: " + codigoLavadero + " - Se inicia en: " + precioItems);
				d.put(codigoLavadero, precioItems);
			}			

			
			//Ingresar Comisiones
			if (comisiones.containsKey(codigoLavadero)) {
				logger.debug("==== Se suma al codigo: " + codigoLavadero + " - La suma de: " + precioComisionIndividual);
				BigDecimal val = (BigDecimal) comisiones.get(codigoLavadero);
				comisiones.put(codigoLavadero, val.add(precioComisionIndividual));
			} else {
				logger.debug("==== Nuevo codigo: " + codigoLavadero + " - Se inicia en: " + precioComisionIndividual);
				comisiones.put(codigoLavadero, precioComisionIndividual);
			}			
			
		}

		List<Lavadero> lavaderos = lavaderoRepository.listarLavaderosDisponibles(Constantes.ESTADO_ACTIVO);

		for (Lavadero lavadero : lavaderos) {
			logger.debug("==== Iterando en Lavaderos...");

			Long codigo = lavadero.getCodigo();
			logger.debug("==== Codigo Lavadero: " + codigo);

			//Facturaciones
			if (d.containsKey(codigo)) {
				logger.debug("Ya esta el lavadero");
			} else {
				logger.debug("No tenia servicios entonces la facturacion es $0");
				d.put(codigo, new BigDecimal(0));
			}
			
			//Comisiones
			if (comisiones.containsKey(codigo)) {
				logger.debug("Ya esta el lavadero");
			} else {
				logger.debug("No tenia comisiones en el dia entonces la comision es $0");
				comisiones.put(codigo, new BigDecimal(0));
			}

		}

		List<ItemReporteAdmin2> registros = new ArrayList<ItemReporteAdmin2>();

		logger.debug("==== Imprimiendo el diccionario");

		BigDecimal granTotalFacturacion = new BigDecimal(0);
		BigDecimal granTotalComisiones = new BigDecimal(0);

		for (Long key : d.keySet()) {
			logger.debug("==== Codigo de Lavadero: " + key + " - Facturado: " + d.get(key));

			Lavadero lavadero = lavaderoRepository.findByCodigo(key);

			ItemReporteAdmin2 registro = new ItemReporteAdmin2();
			registro.setLavadero(lavadero.getNombre());
			registro.setFacturacion(d.get(key));
			registro.setComision(comisiones.get(key));

			granTotalFacturacion = granTotalFacturacion.add(d.get(key));
			granTotalComisiones = granTotalComisiones.add(comisiones.get(key));

			registros.add(registro);
		}

		ReporteAdmin2 reporte = new ReporteAdmin2();
		reporte.setFechaHora(formatter.format(fechaHoraInicio));
		reporte.setItems(registros);
		reporte.setGranTotalFacturacion(granTotalFacturacion);
		reporte.setGranTotalComisiones(granTotalComisiones);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.BUSQUEDA_CORRECTA);
		respuesta.setData(reporte);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK); 
	}	
	
	
	
	
	/**
	 * Reporte 2 Generar Reporte - La facturación de cada lavadero  y La Facturación de las comisiones. y un Gran Total
	 * 
	 * @return Respuesta
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/admin/reporte2/generar", method = RequestMethod.POST)
	public ResponseEntity<InputStreamResource> adminReporte2Generar(
			@RequestParam(value = "fechaHoraInicio") String fechaHoraInicioStr,
			@RequestParam(value = "fechaHoraFin") String fechaHoraFinStr) throws IOException {
		logger.debug("Admin Reporte 2 Generar Controlador ....");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date fechaHoraInicio = null;
		Date fechaHoraFin = null;

		try {
			fechaHoraInicio = formatter.parse(fechaHoraInicioStr);
			fechaHoraFin = formatter.parse(fechaHoraFinStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		
		List<Orden> ordenes = ordenRepository.listarOrdenesXDia(fechaHoraInicio, fechaHoraFin);

		// Facturacion
		Map<Long, BigDecimal> d = new HashMap<Long, BigDecimal>();		
		
		//Comisiones
		Map<Long, BigDecimal> comisiones = new HashMap<Long, BigDecimal>();	
		
		BigDecimal precioServicioActual = new BigDecimal(0);
		BigDecimal precioComisionIndividual = new BigDecimal(0);

		for (Orden orden : ordenes) {
			logger.debug("==== Iterando en Orden...");

			Long codigoLavadero = orden.getLavadero().getCodigo();
			logger.debug("==== Codigo Lavadero: " + codigoLavadero);			
			
			BigDecimal precioItems = new BigDecimal(0);				
			
			for (OrdenItem ordenItem : orden.getItems()) {
				logger.debug("==== Iterando en Orden Item...");		
				
				precioItems = precioItems.add(ordenItem.getPrecio());
				
				precioServicioActual = ordenItem.getPrecio();
				
				//Carga configuracion de la comision
				LavaderoServicio configuracion = lavaderoServicioRepository.buscarXLavaderoYTipoServicioYestado(codigoLavadero, ordenItem.getTipoServicio().getCodigo()).get(0);
				
				if(configuracion.getAplicaComision() == 1) {
					precioComisionIndividual = CustomOperations.percentage(precioServicioActual, configuracion.getValorComision());
				} else {
					precioComisionIndividual = new BigDecimal(0);
				}		
				
			}
			
			
			//Ingresar Facturacion
			if (d.containsKey(codigoLavadero)) {
				logger.debug("==== Se suma al codigo: " + codigoLavadero + " - La suma de: " + precioItems);
				BigDecimal val = (BigDecimal) d.get(codigoLavadero);
				d.put(codigoLavadero, val.add(precioItems));
			} else {
				logger.debug("==== Nuevo codigo: " + codigoLavadero + " - Se inicia en: " + precioItems);
				d.put(codigoLavadero, precioItems);
			}			

			
			//Ingresar Comisiones
			if (comisiones.containsKey(codigoLavadero)) {
				logger.debug("==== Se suma al codigo: " + codigoLavadero + " - La suma de: " + precioComisionIndividual);
				BigDecimal val = (BigDecimal) comisiones.get(codigoLavadero);
				comisiones.put(codigoLavadero, val.add(precioComisionIndividual));
			} else {
				logger.debug("==== Nuevo codigo: " + codigoLavadero + " - Se inicia en: " + precioComisionIndividual);
				comisiones.put(codigoLavadero, precioComisionIndividual);
			}			
			
		}

		List<Lavadero> lavaderos = lavaderoRepository.listarLavaderosDisponibles(Constantes.ESTADO_ACTIVO);

		for (Lavadero lavadero : lavaderos) {
			logger.debug("==== Iterando en Lavaderos...");

			Long codigo = lavadero.getCodigo();
			logger.debug("==== Codigo Lavadero: " + codigo);

			//Facturaciones
			if (d.containsKey(codigo)) {
				logger.debug("Ya esta el lavadero");
			} else {
				logger.debug("No tenia servicios entonces la facturacion es $0");
				d.put(codigo, new BigDecimal(0));
			}
			
			//Comisiones
			if (comisiones.containsKey(codigo)) {
				logger.debug("Ya esta el lavadero");
			} else {
				logger.debug("No tenia comisiones en el dia entonces la comision es $0");
				comisiones.put(codigo, new BigDecimal(0));
			}

		}

		List<ItemReporteAdmin2> registros = new ArrayList<ItemReporteAdmin2>();

		logger.debug("==== Imprimiendo el diccionario");

		BigDecimal granTotalFacturacion = new BigDecimal(0);
		BigDecimal granTotalComisiones = new BigDecimal(0);

		for (Long key : d.keySet()) {
			logger.debug("==== Codigo de Lavadero: " + key + " - Facturado: " + d.get(key));

			Lavadero lavadero = lavaderoRepository.findByCodigo(key);

			ItemReporteAdmin2 registro = new ItemReporteAdmin2();
			registro.setLavadero(lavadero.getNombre());
			registro.setFacturacion(d.get(key));
			registro.setComision(comisiones.get(key));

			granTotalFacturacion = granTotalFacturacion.add(d.get(key));
			granTotalComisiones = granTotalComisiones.add(comisiones.get(key));

			registros.add(registro);
		}

		ReporteAdmin2 reporte = new ReporteAdmin2();
		reporte.setFechaHora(formatter.format(fechaHoraInicio));
		reporte.setItems(registros);
		reporte.setGranTotalFacturacion(granTotalFacturacion);
		reporte.setGranTotalComisiones(granTotalComisiones);

		ByteArrayInputStream in = ReporteAdmin2Generador.reporte4ToExcel(reporte);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=reporte2.xlsx");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

	}
	
	
	
	
	// -----------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------
	// ------------------------------- REPORTE 4------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------

	/**
	 * Reporte 4 - Nro de servicios por lavadero al día, y al final un Total
	 * 
	 * @return Respuesta
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/admin/reporte4/informacion", method = RequestMethod.POST)
	public ResponseEntity<Respuesta> adminReporte4Informacion(
			@RequestParam(value = "fechaHoraInicio") String fechaHoraInicioStr,
			@RequestParam(value = "fechaHoraFin") String fechaHoraFinStr) {
		logger.debug("Admin Reporte 4 Informacion Controlador ....");

		logger.debug(fechaHoraInicioStr);
		logger.debug(fechaHoraFinStr);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date fechaHoraInicio = null;
		Date fechaHoraFin = null;

		try {
			fechaHoraInicio = formatter.parse(fechaHoraInicioStr);
			fechaHoraFin = formatter.parse(fechaHoraFinStr);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Orden> ordenes = ordenRepository.listarOrdenesXDia(fechaHoraInicio, fechaHoraFin);

		Map<Long, Integer> counter = new HashMap<Long, Integer>();

		for (Orden orden : ordenes) {
			logger.debug("==== Iterando en Orden...");

			Long codigoLavadero = orden.getLavadero().getCodigo();
			logger.debug("==== Codigo Lavadero: " + codigoLavadero);

			// Contador de Servicios
			if (counter.containsKey(codigoLavadero)) {
				logger.debug("==== Se suma al codigo: " + codigoLavadero + " - Contador + 1 ");
				Integer val2 = (Integer) counter.get(codigoLavadero);
				counter.put(codigoLavadero, val2 + 1);
			} else {
				logger.debug("==== Nuevo codigo: " + codigoLavadero + " - Se inicia en 1");
				counter.put(codigoLavadero, 1);
			}

		}

		List<Lavadero> lavaderos = lavaderoRepository.listarLavaderosDisponibles(Constantes.ESTADO_ACTIVO);

		for (Lavadero lavadero : lavaderos) {
			logger.debug("==== Iterando en Lavaderos...");

			Long codigo = lavadero.getCodigo();
			logger.debug("==== Codigo Lavadero: " + codigo);

			if (counter.containsKey(codigo)) {
				logger.debug("Ya esta el lavadero");
			} else {
				logger.debug("No tenia servicios entonces es 0");
				counter.put(codigo, 0);
			}

		}

		List<ItemReporteAdmin4> registros = new ArrayList<ItemReporteAdmin4>();

		logger.debug("==== Imprimiendo el diccionario");

		int granTotal = 0;

		// SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		for (Long key : counter.keySet()) {
			logger.debug("==== Codigo de Lavadero: " + key + " - Servicios: " + counter.get(key));

			Lavadero lavadero = lavaderoRepository.findByCodigo(key);

			ItemReporteAdmin4 registro = new ItemReporteAdmin4();
			registro.setLavadero(lavadero.getNombre());
			registro.setNumeroServiciosPrestados(counter.get(key));

			granTotal = granTotal + counter.get(key);

			registros.add(registro);
		}

		ReporteAdmin4 reporte = new ReporteAdmin4();
		reporte.setFechaHora(formatter.format(fechaHoraInicio));
		reporte.setItems(registros);
		reporte.setGranTotal(granTotal);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.BUSQUEDA_CORRECTA);
		respuesta.setData(reporte);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

	/**
	 * Reporte 4 Generar Reporte - Nro de servicios por lavadero al día, y al final
	 * un Total
	 * 
	 * @return Respuesta
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/admin/reporte4/generar", method = RequestMethod.POST)
	public ResponseEntity<InputStreamResource> adminReporte4Generar(
			@RequestParam(value = "fechaHoraInicio") String fechaHoraInicioStr,
			@RequestParam(value = "fechaHoraFin") String fechaHoraFinStr) throws IOException {
		logger.debug("Admin Reporte 4 Generar Controlador ....");

		logger.debug(fechaHoraInicioStr);
		logger.debug(fechaHoraFinStr);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date fechaHoraInicio = null;
		Date fechaHoraFin = null;

		try {
			fechaHoraInicio = formatter.parse(fechaHoraInicioStr);
			fechaHoraFin = formatter.parse(fechaHoraFinStr);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Orden> ordenes = ordenRepository.listarOrdenesXDia(fechaHoraInicio, fechaHoraFin);

		Map<Long, Integer> counter = new HashMap<Long, Integer>();

		for (Orden orden : ordenes) {
			logger.debug("==== Iterando en Orden...");

			Long codigoLavadero = orden.getLavadero().getCodigo();
			logger.debug("==== Codigo Lavadero: " + codigoLavadero);

			// Contador de Servicios
			if (counter.containsKey(codigoLavadero)) {
				logger.debug("==== Se suma al codigo: " + codigoLavadero + " - Contador + 1 ");
				Integer val2 = (Integer) counter.get(codigoLavadero);
				counter.put(codigoLavadero, val2 + 1);
			} else {
				logger.debug("==== Nuevo codigo: " + codigoLavadero + " - Se inicia en 1");
				counter.put(codigoLavadero, 1);
			}

		}

		List<Lavadero> lavaderos = lavaderoRepository.listarLavaderosDisponibles(Constantes.ESTADO_ACTIVO);

		for (Lavadero lavadero : lavaderos) {
			logger.debug("==== Iterando en Lavaderos...");

			Long codigo = lavadero.getCodigo();
			logger.debug("==== Codigo Lavadero: " + codigo);

			if (counter.containsKey(codigo)) {
				logger.debug("Ya esta el lavadero");
			} else {
				logger.debug("No tenia servicios entonces es 0");
				counter.put(codigo, 0);
			}

		}

		List<ItemReporteAdmin4> registros = new ArrayList<ItemReporteAdmin4>();

		logger.debug("==== Imprimiendo el diccionario");

		int granTotal = 0;

		// SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		for (Long key : counter.keySet()) {
			logger.debug("==== Codigo de Lavadero: " + key + " - Servicios: " + counter.get(key));

			Lavadero lavadero = lavaderoRepository.findByCodigo(key);

			ItemReporteAdmin4 registro = new ItemReporteAdmin4();
			registro.setLavadero(lavadero.getNombre());
			registro.setNumeroServiciosPrestados(counter.get(key));

			granTotal = granTotal + counter.get(key);

			registros.add(registro);
		}

		ReporteAdmin4 reporte = new ReporteAdmin4();
		reporte.setFechaHora(formatter.format(fechaHoraInicio));
		reporte.setItems(registros);
		reporte.setGranTotal(granTotal);

		ByteArrayInputStream in = ReporteAdmin4Generador.reporte4ToExcel(reporte);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=reporte4.xlsx");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

	}
	
	
	
	
	
	

	// ######################################################################################################################
	// ######################### PROPIETARIO ################################################################################ 
	// ######################################################################################################################

	// -----------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------
	// ------------------------------- REPORTE 1 -----------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------

	/**
	 * Reporte 1 - Reporte por clientes, para determinar quién fue el cliente que
	 * más servicios realizó en el establecimiento, y luego de mayor a menor.
	 * 
	 * @return Respuesta
	 */
	@PreAuthorize("hasAuthority('ROLE_PROPIETARIO')") 
	@RequestMapping(value = "/propietario/reporte1/informacion", method = RequestMethod.POST) 
	public ResponseEntity<Respuesta> propietarioReporte1Informacion(
			@RequestParam(value = "fechaHoraInicio") String fechaHoraInicioStr,
			@RequestParam(value = "fechaHoraFin") String fechaHoraFinStr,
			@RequestParam(value = "lavaderoCodigo") String lavaderoCodigoStr) {
		logger.debug("Propietario Reporte 1 Informacion Controlador ....");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date fechaHoraInicio = null;
		Date fechaHoraFin = null;
		Long lavaderoCodigo = Long.valueOf(lavaderoCodigoStr);

		try {
			fechaHoraInicio = formatter.parse(fechaHoraInicioStr);
			fechaHoraFin = formatter.parse(fechaHoraFinStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Orden> ordenes = ordenRepository.listarOrdenesXLavaderoYRangoFechas(lavaderoCodigo, fechaHoraInicio, fechaHoraFin);

		Map<Long, Integer> counter = new HashMap<Long, Integer>();

		for (Orden orden : ordenes) {
			logger.debug("==== Iterando en Orden...");

			Long codigoUsuario = orden.getClienteVehiculo().getUsuario().getCodigo();
			logger.debug("==== Codigo Usuario: " + codigoUsuario);

			// Contador
			if (counter.containsKey(codigoUsuario)) {
				logger.debug("==== Se suma al codigo: " + codigoUsuario + " - Contador + 1 ");
				Integer val2 = (Integer) counter.get(codigoUsuario);
				counter.put(codigoUsuario, val2 + 1);
			} else {
				logger.debug("==== Nuevo codigo: " + codigoUsuario + " - Se inicia en 1");
				counter.put(codigoUsuario, 1);
			}

		}
	
		
		logger.debug("==== Ordenando de mayor a menor el diccionario");
		Map<Long, Integer> sorted = counter
		        .entrySet()
		        .stream()
		        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
		        .collect(
		            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
		                LinkedHashMap::new));
		
		
		
		
		List<ItemReportePropietario1> registros = new ArrayList<ItemReportePropietario1>();

		logger.debug("==== Imprimiendo el diccionario");

		int granTotal = 0;

		for (Long key : sorted.keySet()) {
			logger.debug("==== Codigo de Usuario: " + key + " - Servicios: " + sorted.get(key));

			Usuario usuario = usuarioRepository.findByCodigo(key);

			ItemReportePropietario1 registro = new ItemReportePropietario1();
			registro.setUsuario(usuario);
			registro.setNumeroServiciosSolicitados(sorted.get(key));

			granTotal = granTotal + sorted.get(key);

			registros.add(registro);
		}

		ReportePropietario1 reporte = new ReportePropietario1();
		reporte.setFechaHoraInicio(formatter.format(fechaHoraInicio));
		reporte.setFechaHoraFin(formatter.format(fechaHoraFin));
		reporte.setItems(registros);
		reporte.setGranTotal(granTotal);		
		

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.BUSQUEDA_CORRECTA);
		respuesta.setData(reporte);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

	
	/**
	 * Reporte 1 Generar Reporte - Reporte por clientes, para determinar quién fue el cliente que 
	 * más servicios realizó en el establecimiento, y luego de mayor a menor.
	 * 
	 * @return Respuesta
	 */
	@PreAuthorize("hasAuthority('ROLE_PROPIETARIO')")
	@RequestMapping(value = "/propietario/reporte1/generar", method = RequestMethod.POST)
	public ResponseEntity<InputStreamResource> propietarioReporte1Generar(
			@RequestParam(value = "fechaHoraInicio") String fechaHoraInicioStr,
			@RequestParam(value = "fechaHoraFin") String fechaHoraFinStr,
			@RequestParam(value = "lavaderoCodigo") String lavaderoCodigoStr) throws IOException {
		logger.debug("Propietario Reporte 1 Generar Controlador ....");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date fechaHoraInicio = null;
		Date fechaHoraFin = null;
		Long lavaderoCodigo = Long.valueOf(lavaderoCodigoStr);

		try {
			fechaHoraInicio = formatter.parse(fechaHoraInicioStr);
			fechaHoraFin = formatter.parse(fechaHoraFinStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Orden> ordenes = ordenRepository.listarOrdenesXLavaderoYRangoFechas(lavaderoCodigo, fechaHoraInicio, fechaHoraFin);

		Map<Long, Integer> counter = new HashMap<Long, Integer>();

		for (Orden orden : ordenes) {
			logger.debug("==== Iterando en Orden...");

			Long codigoUsuario = orden.getClienteVehiculo().getUsuario().getCodigo();
			logger.debug("==== Codigo Usuario: " + codigoUsuario);

			// Contador
			if (counter.containsKey(codigoUsuario)) {
				logger.debug("==== Se suma al codigo: " + codigoUsuario + " - Contador + 1 ");
				Integer val2 = (Integer) counter.get(codigoUsuario);
				counter.put(codigoUsuario, val2 + 1);
			} else {
				logger.debug("==== Nuevo codigo: " + codigoUsuario + " - Se inicia en 1");
				counter.put(codigoUsuario, 1);
			}

		}
	
		
		logger.debug("==== Ordenando de mayor a menor el diccionario");
		Map<Long, Integer> sorted = counter
		        .entrySet()
		        .stream()
		        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
		        .collect(
		            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
		                LinkedHashMap::new));
		
		
		
		
		List<ItemReportePropietario1> registros = new ArrayList<ItemReportePropietario1>();

		logger.debug("==== Imprimiendo el diccionario");

		int granTotal = 0;

		for (Long key : sorted.keySet()) {
			logger.debug("==== Codigo de Usuario: " + key + " - Servicios: " + sorted.get(key));

			Usuario usuario = usuarioRepository.findByCodigo(key);

			ItemReportePropietario1 registro = new ItemReportePropietario1();
			registro.setUsuario(usuario);
			registro.setNumeroServiciosSolicitados(sorted.get(key));

			granTotal = granTotal + sorted.get(key);

			registros.add(registro);
		}

		ReportePropietario1 reporte = new ReportePropietario1();
		reporte.setFechaHoraInicio(formatter.format(fechaHoraInicio));
		reporte.setFechaHoraFin(formatter.format(fechaHoraFin));
		reporte.setItems(registros);
		reporte.setGranTotal(granTotal);

		ByteArrayInputStream in = ReportePropietario1Generador.generarReporte(reporte);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=reporte1.xlsx");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

	}
	
	
	
	
	
	// -----------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------
	// ------------------------------- REPORTE 2 -----------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------

		/**
		 * Reporte 2 - El trabajador más eficiente: cantidad de servicios que realizó tal trabajador, de
		 * mayor a menor
		 * 
		 * @return Respuesta
		 */
		@PreAuthorize("hasAuthority('ROLE_PROPIETARIO')") 
		@RequestMapping(value = "/propietario/reporte2/informacion", method = RequestMethod.POST) 
		public ResponseEntity<Respuesta> propietarioReporte2Informacion(
				@RequestParam(value = "fechaHoraInicio") String fechaHoraInicioStr,
				@RequestParam(value = "fechaHoraFin") String fechaHoraFinStr,
				@RequestParam(value = "lavaderoCodigo") String lavaderoCodigoStr) {
			logger.debug("Propietario Reporte 2 Informacion Controlador ....");
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date fechaHoraInicio = null;
			Date fechaHoraFin = null;
			Long lavaderoCodigo = Long.valueOf(lavaderoCodigoStr);

			try {
				fechaHoraInicio = formatter.parse(fechaHoraInicioStr);
				fechaHoraFin = formatter.parse(fechaHoraFinStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			List<Orden> ordenes = ordenRepository.listarOrdenesXLavaderoYRangoFechas(lavaderoCodigo, fechaHoraInicio, fechaHoraFin);

			Map<Long, Integer> counter = new HashMap<Long, Integer>();

			for (Orden orden : ordenes) {
				logger.debug("==== Iterando en Orden...");
				
				for (OrdenItem ordenItem : orden.getItems()) {
					logger.debug("==== Iterando en OrdenItem...");
					
					for (ItemFuncionario itemFuncionario : ordenItem.getItemFuncionarios()) {
						logger.debug("==== Iterando en ItemFuncionario...");
						
						Long codigoUsuario = itemFuncionario.getFuncionarioServicio().getUsuario().getCodigo();
						logger.debug("==== Codigo Usuario: " + codigoUsuario);
						
						// Contador
						if (counter.containsKey(codigoUsuario)) {
							logger.debug("==== Se suma al codigo: " + codigoUsuario + " - Contador + 1 ");
							Integer val2 = (Integer) counter.get(codigoUsuario);
							counter.put(codigoUsuario, val2 + 1);
						} else {
							logger.debug("==== Nuevo codigo: " + codigoUsuario + " - Se inicia en 1");
							counter.put(codigoUsuario, 1);
						}
						
					}
					
				}
				
			}
		
			
			logger.debug("==== Ordenando de mayor a menor el diccionario");
			Map<Long, Integer> sorted = counter
			        .entrySet()
			        .stream()
			        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
			        .collect(
			            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
			                LinkedHashMap::new));
			
						
			List<ItemReportePropietario2> registros = new ArrayList<ItemReportePropietario2>();

			logger.debug("==== Imprimiendo el diccionario");

			int granTotal = 0;

			for (Long key : sorted.keySet()) {
				logger.debug("==== Codigo de Usuario: " + key + " - Servicios: " + sorted.get(key));

				Usuario usuario = usuarioRepository.findByCodigo(key);

				ItemReportePropietario2 registro = new ItemReportePropietario2();
				registro.setUsuario(usuario);
				registro.setNumeroServiciosRealizados(sorted.get(key));

				granTotal = granTotal + sorted.get(key);

				registros.add(registro);
			}

			ReportePropietario2 reporte = new ReportePropietario2();
			reporte.setFechaHoraInicio(formatter.format(fechaHoraInicio));
			reporte.setFechaHoraFin(formatter.format(fechaHoraFin));
			reporte.setItems(registros);
			reporte.setGranTotal(granTotal);		
			

			Respuesta respuesta = new Respuesta();
			respuesta.setExito(true);
			respuesta.setCode(200);
			respuesta.setMensaje(Mensajes.BUSQUEDA_CORRECTA);
			respuesta.setData(reporte);
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
		}
	
	
	
		/**
		 * Reporte 2 Generar Reporte - El trabajador más eficiente: cantidad de servicios que realizó tal trabajador, de
		 * mayor a menor
		 * 
		 * @return Respuesta
		 */
		@PreAuthorize("hasAuthority('ROLE_PROPIETARIO')")
		@RequestMapping(value = "/propietario/reporte2/generar", method = RequestMethod.POST)
		public ResponseEntity<InputStreamResource> propietarioReporte2Generar(
				@RequestParam(value = "fechaHoraInicio") String fechaHoraInicioStr,
				@RequestParam(value = "fechaHoraFin") String fechaHoraFinStr,
				@RequestParam(value = "lavaderoCodigo") String lavaderoCodigoStr) throws IOException {
			logger.debug("Propietario Reporte 1 Generar Controlador ....");

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date fechaHoraInicio = null;
			Date fechaHoraFin = null;
			Long lavaderoCodigo = Long.valueOf(lavaderoCodigoStr);

			try {
				fechaHoraInicio = formatter.parse(fechaHoraInicioStr);
				fechaHoraFin = formatter.parse(fechaHoraFinStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			List<Orden> ordenes = ordenRepository.listarOrdenesXLavaderoYRangoFechas(lavaderoCodigo, fechaHoraInicio, fechaHoraFin);

			Map<Long, Integer> counter = new HashMap<Long, Integer>();

			for (Orden orden : ordenes) {
				logger.debug("==== Iterando en Orden...");
				
				for (OrdenItem ordenItem : orden.getItems()) {
					logger.debug("==== Iterando en OrdenItem...");
					
					for (ItemFuncionario itemFuncionario : ordenItem.getItemFuncionarios()) {
						logger.debug("==== Iterando en ItemFuncionario...");
						
						Long codigoUsuario = itemFuncionario.getFuncionarioServicio().getUsuario().getCodigo();
						logger.debug("==== Codigo Usuario: " + codigoUsuario);
						
						// Contador
						if (counter.containsKey(codigoUsuario)) {
							logger.debug("==== Se suma al codigo: " + codigoUsuario + " - Contador + 1 ");
							Integer val2 = (Integer) counter.get(codigoUsuario);
							counter.put(codigoUsuario, val2 + 1);
						} else {
							logger.debug("==== Nuevo codigo: " + codigoUsuario + " - Se inicia en 1");
							counter.put(codigoUsuario, 1);
						}
						
					}
					
				}
				
			}
		
			
			logger.debug("==== Ordenando de mayor a menor el diccionario");
			Map<Long, Integer> sorted = counter
			        .entrySet()
			        .stream()
			        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
			        .collect(
			            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
			                LinkedHashMap::new));
			
						
			List<ItemReportePropietario2> registros = new ArrayList<ItemReportePropietario2>();

			logger.debug("==== Imprimiendo el diccionario");

			int granTotal = 0;

			for (Long key : sorted.keySet()) {
				logger.debug("==== Codigo de Usuario: " + key + " - Servicios: " + sorted.get(key));

				Usuario usuario = usuarioRepository.findByCodigo(key);

				ItemReportePropietario2 registro = new ItemReportePropietario2();
				registro.setUsuario(usuario);
				registro.setNumeroServiciosRealizados(sorted.get(key));

				granTotal = granTotal + sorted.get(key);

				registros.add(registro);
			}

			ReportePropietario2 reporte = new ReportePropietario2();
			reporte.setFechaHoraInicio(formatter.format(fechaHoraInicio));
			reporte.setFechaHoraFin(formatter.format(fechaHoraFin));
			reporte.setItems(registros);
			reporte.setGranTotal(granTotal);	

			ByteArrayInputStream in = ReportePropietario2Generador.generarReporte(reporte);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=reporte3.xlsx");

			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

		}
	
	
	
		
		
		// -----------------------------------------------------------------------------------------------------
		// -----------------------------------------------------------------------------------------------------
		// ------------------------------- REPORTE 3 -----------------------------------------------------------
		// -----------------------------------------------------------------------------------------------------

			/**
			 * Reporte 3 - El servicio más solicitado de mayor a menor
			 * 
			 * @return Respuesta
			 */
			@PreAuthorize("hasAuthority('ROLE_PROPIETARIO')") 
			@RequestMapping(value = "/propietario/reporte3/informacion", method = RequestMethod.POST) 
			public ResponseEntity<Respuesta> propietarioReporte3Informacion(
					@RequestParam(value = "fechaHoraInicio") String fechaHoraInicioStr,
					@RequestParam(value = "fechaHoraFin") String fechaHoraFinStr,
					@RequestParam(value = "lavaderoCodigo") String lavaderoCodigoStr) {
				logger.debug("Propietario Reporte 3 Informacion Controlador ....");
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date fechaHoraInicio = null;
				Date fechaHoraFin = null;
				Long lavaderoCodigo = Long.valueOf(lavaderoCodigoStr);

				try {
					fechaHoraInicio = formatter.parse(fechaHoraInicioStr);
					fechaHoraFin = formatter.parse(fechaHoraFinStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				List<Orden> ordenes = ordenRepository.listarOrdenesXLavaderoYRangoFechas(lavaderoCodigo, fechaHoraInicio, fechaHoraFin);

				Map<Long, Integer> counter = new HashMap<Long, Integer>();

				for (Orden orden : ordenes) {
					logger.debug("==== Iterando en Orden...");
					
					for (OrdenItem ordenItem : orden.getItems()) {
						logger.debug("==== Iterando en OrdenItem...");						
												
						Long codigoServicio = ordenItem.getTipoServicio().getCodigo();
						logger.debug("==== Codigo Servicio: " + codigoServicio);
							
						// Contador
						if (counter.containsKey(codigoServicio)) {
							logger.debug("==== Se suma al codigo: " + codigoServicio + " - Contador + 1 ");
							Integer val2 = (Integer) counter.get(codigoServicio);
							counter.put(codigoServicio, val2 + 1);
						} else {
							logger.debug("==== Nuevo codigo: " + codigoServicio + " - Se inicia en 1");
							counter.put(codigoServicio, 1);
						}
						
					}
					
				}
			
				
				logger.debug("==== Ordenando de mayor a menor el diccionario");
				Map<Long, Integer> sorted = counter
				        .entrySet()
				        .stream()
				        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				        .collect(
				            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
				                LinkedHashMap::new));
				
							
				List<ItemReportePropietario3> registros = new ArrayList<ItemReportePropietario3>();

				logger.debug("==== Imprimiendo el diccionario");

				int granTotal = 0;

				for (Long key : sorted.keySet()) {
					logger.debug("==== Codigo de Servicio: " + key + " - Numero: " + sorted.get(key));

					TipoServicio tipoServicio = tipoServicioRepository.findByCodigo(key);

					ItemReportePropietario3 registro = new ItemReportePropietario3();
					registro.setServicio(tipoServicio.getNombre());
					registro.setNumeroRealizados(sorted.get(key));

					granTotal = granTotal + sorted.get(key);

					registros.add(registro);
				}

				ReportePropietario3 reporte = new ReportePropietario3();
				reporte.setFechaHoraInicio(formatter.format(fechaHoraInicio));
				reporte.setFechaHoraFin(formatter.format(fechaHoraFin));
				reporte.setItems(registros);
				reporte.setGranTotal(granTotal);		
				

				Respuesta respuesta = new Respuesta();
				respuesta.setExito(true);
				respuesta.setCode(200);
				respuesta.setMensaje(Mensajes.BUSQUEDA_CORRECTA);
				respuesta.setData(reporte);
				return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
			}
		
		
		
			/**
			 * Reporte 3 Generar Reporte - El servicio más solicitado de mayor a menor
			 * 
			 * @return Respuesta
			 */
			@PreAuthorize("hasAuthority('ROLE_PROPIETARIO')")
			@RequestMapping(value = "/propietario/reporte3/generar", method = RequestMethod.POST)
			public ResponseEntity<InputStreamResource> propietarioReporte3Generar(
					@RequestParam(value = "fechaHoraInicio") String fechaHoraInicioStr,
					@RequestParam(value = "fechaHoraFin") String fechaHoraFinStr,
					@RequestParam(value = "lavaderoCodigo") String lavaderoCodigoStr) throws IOException {
				logger.debug("Propietario Reporte 1 Generar Controlador ....");

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date fechaHoraInicio = null;
				Date fechaHoraFin = null;
				Long lavaderoCodigo = Long.valueOf(lavaderoCodigoStr);

				try {
					fechaHoraInicio = formatter.parse(fechaHoraInicioStr);
					fechaHoraFin = formatter.parse(fechaHoraFinStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				List<Orden> ordenes = ordenRepository.listarOrdenesXLavaderoYRangoFechas(lavaderoCodigo, fechaHoraInicio, fechaHoraFin);

				Map<Long, Integer> counter = new HashMap<Long, Integer>();

				for (Orden orden : ordenes) {
					logger.debug("==== Iterando en Orden...");
					
					for (OrdenItem ordenItem : orden.getItems()) {
						logger.debug("==== Iterando en OrdenItem...");						
												
						Long codigoServicio = ordenItem.getTipoServicio().getCodigo();
						logger.debug("==== Codigo Servicio: " + codigoServicio);
							
						// Contador
						if (counter.containsKey(codigoServicio)) {
							logger.debug("==== Se suma al codigo: " + codigoServicio + " - Contador + 1 ");
							Integer val2 = (Integer) counter.get(codigoServicio);
							counter.put(codigoServicio, val2 + 1);
						} else {
							logger.debug("==== Nuevo codigo: " + codigoServicio + " - Se inicia en 1");
							counter.put(codigoServicio, 1);
						}
						
					}
					
				}
			
				
				logger.debug("==== Ordenando de mayor a menor el diccionario");
				Map<Long, Integer> sorted = counter
				        .entrySet()
				        .stream()
				        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				        .collect(
				            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
				                LinkedHashMap::new));
				
							
				List<ItemReportePropietario3> registros = new ArrayList<ItemReportePropietario3>();

				logger.debug("==== Imprimiendo el diccionario");

				int granTotal = 0;

				for (Long key : sorted.keySet()) {
					logger.debug("==== Codigo de Servicio: " + key + " - Numero: " + sorted.get(key));

					TipoServicio tipoServicio = tipoServicioRepository.findByCodigo(key);

					ItemReportePropietario3 registro = new ItemReportePropietario3();
					registro.setServicio(tipoServicio.getNombre());
					registro.setNumeroRealizados(sorted.get(key));

					granTotal = granTotal + sorted.get(key);

					registros.add(registro);
				}

				ReportePropietario3 reporte = new ReportePropietario3();
				reporte.setFechaHoraInicio(formatter.format(fechaHoraInicio));
				reporte.setFechaHoraFin(formatter.format(fechaHoraFin));
				reporte.setItems(registros);
				reporte.setGranTotal(granTotal);	

				ByteArrayInputStream in = ReportePropietario3Generador.generarReporte(reporte);

				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Disposition", "attachment; filename=reporte3.xlsx");

				return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

			}
		
		
		
		
			// -----------------------------------------------------------------------------------------------------
			// -----------------------------------------------------------------------------------------------------
			// ------------------------------- REPORTE 4 -----------------------------------------------------------
			// -----------------------------------------------------------------------------------------------------

				/**
				 * Reporte 4 - Reporte de lavadero: Reporte de liquidación de técnicos diaria o por fechas
				 * 
				 * @return Respuesta
				 */
				@PreAuthorize("hasAuthority('ROLE_PROPIETARIO')") 
				@RequestMapping(value = "/propietario/reporte4/informacion", method = RequestMethod.POST) 
				public ResponseEntity<Respuesta> propietarioReporte4Informacion(
						@RequestParam(value = "fechaHoraInicio") String fechaHoraInicioStr,
						@RequestParam(value = "fechaHoraFin") String fechaHoraFinStr,
						@RequestParam(value = "lavaderoCodigo") String lavaderoCodigoStr) {
					logger.debug("Propietario Reporte 4 Informacion Controlador ....");
					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date fechaHoraInicio = null;
					Date fechaHoraFin = null;
					Long lavaderoCodigo = Long.valueOf(lavaderoCodigoStr);

					try {
						fechaHoraInicio = formatter.parse(fechaHoraInicioStr);
						fechaHoraFin = formatter.parse(fechaHoraFinStr);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					Lavadero lav = lavaderoRepository.findByCodigo(lavaderoCodigo);
					
					LiquidacionFuncionario objeto = new LiquidacionFuncionario();
					objeto.setFechaInicio(fechaHoraInicio);
					objeto.setFechaFin(fechaHoraFin);
					objeto.setLavadero(lav);					
					
					List<LiquidacionFuncionario> registros = liquidacionFuncionarioService
							.buscarReporteLiquidacionesTecnicosParaPropietario(objeto);		
				
					
					BigDecimal gastoTotal = new BigDecimal(0);

					for (LiquidacionFuncionario liquidacionFuncionario : registros) {
						logger.debug("==== Iterando en LiquidacionFuncionario...");	
						logger.debug("Liquidacion Funcionario Codigo: " + liquidacionFuncionario.getCodigo());
						logger.debug("Valor Pago: " + liquidacionFuncionario.getValorPago());
						gastoTotal = gastoTotal.add(liquidacionFuncionario.getValorPago());						
					}
					

					ReportePropietario4 reporte = new ReportePropietario4();
					reporte.setFechaHoraInicio(formatter.format(fechaHoraInicio));
					reporte.setFechaHoraFin(formatter.format(fechaHoraFin));
					reporte.setItems(registros);
					reporte.setGranTotal(gastoTotal);		
					

					Respuesta respuesta = new Respuesta();
					respuesta.setExito(true);
					respuesta.setCode(200);
					respuesta.setMensaje(Mensajes.BUSQUEDA_CORRECTA);
					respuesta.setData(reporte);
					return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
				}
				
				
				
				
				
				
				
				
				/**
				 * Reporte 4 Generar Reporte - Reporte de lavadero: Reporte de liquidación de técnicos diaria o por fechas
				 * 
				 * @return Respuesta
				 */
				@PreAuthorize("hasAuthority('ROLE_PROPIETARIO')")
				@RequestMapping(value = "/propietario/reporte4/generar", method = RequestMethod.POST)
				public ResponseEntity<InputStreamResource> propietarioReporte4Generar(
						@RequestParam(value = "fechaHoraInicio") String fechaHoraInicioStr,
						@RequestParam(value = "fechaHoraFin") String fechaHoraFinStr,
						@RequestParam(value = "lavaderoCodigo") String lavaderoCodigoStr) throws IOException {
					logger.debug("Propietario Reporte 4 Generar Controlador ....");

					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date fechaHoraInicio = null;
					Date fechaHoraFin = null;
					Long lavaderoCodigo = Long.valueOf(lavaderoCodigoStr);

					try {
						fechaHoraInicio = formatter.parse(fechaHoraInicioStr);
						fechaHoraFin = formatter.parse(fechaHoraFinStr);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					Lavadero lav = lavaderoRepository.findByCodigo(lavaderoCodigo);
					
					LiquidacionFuncionario objeto = new LiquidacionFuncionario();
					objeto.setFechaInicio(fechaHoraInicio);
					objeto.setFechaFin(fechaHoraFin);
					objeto.setLavadero(lav);					
					
					List<LiquidacionFuncionario> registros = liquidacionFuncionarioService
							.buscarReporteLiquidacionesTecnicosParaPropietario(objeto);		
				
					
					BigDecimal gastoTotal = new BigDecimal(0);

					for (LiquidacionFuncionario liquidacionFuncionario : registros) {
						logger.debug("==== Iterando en LiquidacionFuncionario...");	
						logger.debug("Liquidacion Funcionario Codigo: " + liquidacionFuncionario.getCodigo());
						logger.debug("Valor Pago: " + liquidacionFuncionario.getValorPago());
						gastoTotal = gastoTotal.add(liquidacionFuncionario.getValorPago());						
					}
					

					ReportePropietario4 reporte = new ReportePropietario4();
					reporte.setFechaHoraInicio(formatter.format(fechaHoraInicio));
					reporte.setFechaHoraFin(formatter.format(fechaHoraFin));
					reporte.setItems(registros);
					reporte.setGranTotal(gastoTotal);	

					ByteArrayInputStream in = ReportePropietario4Generador.generarReporte(reporte);

					HttpHeaders headers = new HttpHeaders();
					headers.add("Content-Disposition", "attachment; filename=reporte4.xlsx");

					return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

				}
				
				
				
				
		
		
		
	
	
	
	
	// ######################################################################################################################
	// ######################### PDFs ################################################################################ 
	// ######################################################################################################################
	
	// -----------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/reporte1", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> generarReporte() throws IOException {

		logger.debug("Generando Reporte Ejemplo Controller ......");

		List<TipoServicio> tiposServicios = tipoServicioRepository.listarDisponibles(Constantes.ESTADO_ACTIVO);

		ByteArrayInputStream bis = ReporteEjemplo.generarReporteTiposServicios(tiposServicios);

		HttpHeaders headers = new HttpHeaders();
		// headers.add("Content-Disposition", "inline; filename=ticket.pdf");
		// headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("Content-Disposition", "attachment; filename=reporte.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}

	@RequestMapping(value = "/reporte3/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> generarReporte3(@PathVariable(required = false) String username) throws IOException {

		logger.debug("Generando Reporte 3 Ejemplo Controller ......");

		Map<String, Object> params = new HashMap<>();
		params.put("username", username);

		JRDataSource dataSource = new JREmptyDataSource();

		byte[] bytes = null;

		JasperReport jasperReport = null;

		try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {

			// Load the jrxml template.
			jasperReport = loadTemplate3();

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

			// return the PDF in bytes
			bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				// Specify content type as PDF
				.header("Content-Type", "application/pdf; charset=UTF-8")
				// Tell browser to display PDF if it can
				.header("Content-Disposition", "attachment; filename=\"" + username + ".pdf\"").body(bytes);
	}

	// Load invoice jrxml template
	private JasperReport loadTemplate3() throws JRException {

		logger.debug(String.format("Invoice template path : %s", path_template_3));

		final InputStream reportInputStream = getClass().getResourceAsStream(path_template_3);
		final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);

		return JasperCompileManager.compileReport(jasperDesign);
	}

	@RequestMapping(value = "/reporte2", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> generarReporte2() throws IOException {

		logger.debug("Generando Reporte 2 Ejemplo Controller ......");

		List<TipoServicio> tiposServicios = tipoServicioRepository.listarDisponibles(Constantes.ESTADO_ACTIVO);

		SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String fechaActual = fo.format(new Date());

		ModelReporte modelo = new ModelReporte();
		modelo.setFechaHora(fechaActual);
		modelo.setNombreReporte("Reporte Tipos de Servicios");
		modelo.setTiposServicios(tiposServicios);

		JRBeanCollectionDataSource serviciosJRBean = new JRBeanCollectionDataSource(tiposServicios);

		Map<String, Object> parameters2 = new HashMap<String, Object>();
		parameters2.put("ServiciosDataSource", serviciosJRBean);

		byte[] bytes = null;
		JRDataSource dataSource = new JREmptyDataSource();

		JasperReport jasperReport = null;

		try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {

			// Load the jrxml template.
			jasperReport = loadTemplate2();

			// JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
			// parameters2, dataSource);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters2, dataSource);

			// return the PDF in bytes
			bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				// Specify content type as PDF
				.header("Content-Type", "application/pdf; charset=UTF-8")
				// Tell browser to display PDF if it can
				.header("Content-Disposition", "attachment; filename=\"" + "reporte2" + ".pdf\"").body(bytes);

	}

	// Load invoice jrxml template
	private JasperReport loadTemplate2() throws JRException {

		logger.debug(String.format("Invoice template path : %s", invoice_template));

		final InputStream reportInputStream = getClass().getResourceAsStream(invoice_template);
		final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);

		return JasperCompileManager.compileReport(jasperDesign);
	}

}
