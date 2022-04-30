/**
 * 
 */
package co.com.themakers.lavadero.web.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.themakers.lavadero.entity.LiquidacionFuncionario;
import co.com.themakers.lavadero.entity.Orden;
import co.com.themakers.lavadero.model.Respuesta;
import co.com.themakers.lavadero.service.OrdenService;
import co.com.themakers.lavadero.web.util.FacturaOrdenGenerador;
import co.com.themakers.lavadero.web.util.Mensajes;
import co.com.themakers.lavadero.web.util.TicketLiquidacionFuncionarioGenerator;

/**
 * @author Luis Llanos (Developer)
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/orden")
public class OrdenRestController {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(OrdenRestController.class);

	@Autowired
	OrdenService ordenService;

	@PreAuthorize("hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/lavadero/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<Respuesta> getOrdenesLavaderoDatatable(@PathVariable("codigo") long codigo) {
		logger.debug("Listando ordenes de un lavadero para datatable Controlador ....");

		List<Orden> ordenes = ordenService.getOrdenesXLavadero(codigo);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.ADICION_CORRECTA);
		respuesta.setData(ordenes);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> adicionarOrden(@RequestBody Orden orden) {
		logger.debug("Adicionar una orden controlador ....");
		ordenService.addOrden(orden);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.ADICION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/generarFactura", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> generarFactura(@RequestBody Orden orden) throws IOException {

		ByteArrayInputStream bis = FacturaOrdenGenerador.generarFactura(orden);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=ticket.pdf");
		// headers.setContentType(MediaType.parseMediaType("application/pdf"));
		// headers.add("Content-Disposition", "attachment; filename=ticket.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}
	
	
	@PreAuthorize("hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/consultaNumeroServiciosConsumidosXCliente", method = RequestMethod.POST)
	public ResponseEntity<Respuesta> consultaNumeroServiciosConsumidosXCliente(
			@RequestParam(value = "codigoLavadero") long codigoLavadero,
			@RequestParam(value = "codigoUsuario") long codigoUsuario,
			@RequestParam(value = "codigoTipoServicio") long codigoTipoServicio) {
		logger.debug("Consultando el numero de servicios que el cliente consumio ....");
		
		int numeroServicios = ordenService.consultarNumeroServiciosConsumidosCliente(codigoLavadero, codigoUsuario, codigoTipoServicio);
		
		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.BUSQUEDA_CORRECTA);
		respuesta.setData(numeroServicios);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK); 	
	}
	
	
}
