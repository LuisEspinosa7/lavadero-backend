/**
 * 
 */
package co.com.themakers.lavadero.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.themakers.lavadero.entity.LiquidacionFuncionario;
import co.com.themakers.lavadero.model.Respuesta;
import co.com.themakers.lavadero.service.LiquidacionFuncionarioService;
import co.com.themakers.lavadero.web.util.Mensajes;
import co.com.themakers.lavadero.web.util.TicketLiquidacionFuncionarioGenerator;

import org.springframework.http.MediaType;

import org.springframework.core.io.InputStreamResource;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import org.springframework.http.HttpHeaders;

/**
 * @author Luis Llanos (Developer)
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/liquidacionFuncionario")
public class LiquidacionFuncionarioRestController {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(LiquidacionFuncionarioRestController.class);

	@Autowired
	LiquidacionFuncionarioService liquidacionFuncionarioService;

	@PreAuthorize("hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> liquidarFuncionarioTecnico(
			@RequestBody LiquidacionFuncionario liquidacionFuncionario) {
		logger.debug("Liquidando un funcionario tecnico Controlador ....");
		liquidacionFuncionarioService.liquidarFuncionarioTecnico(liquidacionFuncionario);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.LIQUIDACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/registrosLiquidables", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> buscarRegistrosLiquidablesTecnicos(
			@RequestBody LiquidacionFuncionario liquidacionFuncionario) {
		logger.debug("Buscando registros liquidables Controlador ....");
		List<LiquidacionFuncionario> registrosLiquidables = liquidacionFuncionarioService
				.buscarRegistrosLiquidablesDisponibles(liquidacionFuncionario);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.BUSQUEDA_CORRECTA);
		respuesta.setData(registrosLiquidables);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/historial", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> buscarHistorialLiquidacionesTecnicos(
			@RequestBody LiquidacionFuncionario liquidacionFuncionario) {
		logger.debug("Buscando historial liquidaciones Controlador ....");

		List<LiquidacionFuncionario> historiaLiquidaciones = liquidacionFuncionarioService
				.buscarHistorialLiquidacionesTecnicos(liquidacionFuncionario);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.BUSQUEDA_CORRECTA);
		respuesta.setData(historiaLiquidaciones);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

	@RequestMapping(value = "/generarTicket", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> generarTicketLiquidacion(@RequestBody LiquidacionFuncionario liquidacionFuncionario) throws IOException {

		ByteArrayInputStream bis = TicketLiquidacionFuncionarioGenerator.generarTicketPDF(liquidacionFuncionario);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=ticket.pdf");
		// headers.setContentType(MediaType.parseMediaType("application/pdf"));
		// headers.add("Content-Disposition", "attachment; filename=ticket.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}

}
