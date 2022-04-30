/**
 * 
 */
package co.com.themakers.lavadero.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.com.themakers.lavadero.entity.LavaderoServicio;
import co.com.themakers.lavadero.entity.TipoServicio;
import co.com.themakers.lavadero.entity.Usuario;
import co.com.themakers.lavadero.model.Respuesta;
import co.com.themakers.lavadero.service.LavaderoServicioService;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/lavaderoServicio")
public class LavaderoServicioRestController {
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(LavaderoServicioRestController.class);
	
	@Autowired
	LavaderoServicioService lavaderoServicioService;
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/datatable", method = RequestMethod.POST)
	public DataTablesOutput<LavaderoServicio> getLavaderoServicioDatatable(@Valid @RequestBody DataTablesInput input) {	
		logger.debug("Listando servicios del lavadero para datatable Controlador ....");	
		
		DataTablesOutput<LavaderoServicio> lavaderoServicios = lavaderoServicioService.getLavaderoServiciosDatatable(input);
		return lavaderoServicios;	
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> adicionarLavaderoServicio(@RequestBody LavaderoServicio lavaderoServicio) {
		logger.debug("Adicionar un servicio al lavadero Controlador ....");		
		lavaderoServicioService.addLavaderoServicio(lavaderoServicio);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.ADICION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> modificarLavaderoServicio(@RequestBody LavaderoServicio lavaderoServicio) {
		logger.debug("Modificar un servicio al lavadero Controlador ....");		
		
		lavaderoServicioService.updateLavaderoServicio(lavaderoServicio);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.MODIFICACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> eliminarLavaderoServicio(@PathVariable("codigo") long codigo) {

		logger.debug("Eliminar un personal al lavadero Controlador ....");	
		lavaderoServicioService.eliminarLavaderoServicio(codigo);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.ELIMINACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/estado/{codigo}", method = RequestMethod.POST)
	public ResponseEntity<Respuesta> cambiarEstado(@PathVariable("codigo") long codigo, @RequestParam(value = "estado") int estado) {

		System.out.println("Codigo: " + codigo + " --- " + "Estado: " + estado);
		
		logger.debug("Cambiar estado un personal al lavadero Controlador ....");	
		lavaderoServicioService.cambiarEstado(codigo, estado);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.MODIFICACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') OR hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/configuracion/lavadero/{codigo}", method = RequestMethod.POST)
	public ResponseEntity<Respuesta> buscarLavaderoServicioDisponible(@PathVariable("codigo") long codigo, @RequestParam(value = "codigoTipoServicio") long codigoTipoServicio) {
		logger.debug("======= Listando Lavadero Servicio Disponible");
		
		List<LavaderoServicio> lavaderoServicios = lavaderoServicioService.buscarXLavaderoYTipoServicioDisponible(codigo, codigoTipoServicio);
		
		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.BUSQUEDA_CORRECTA);
		respuesta.setData(lavaderoServicios.get(0));
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

}
