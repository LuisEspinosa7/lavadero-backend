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

import co.com.themakers.lavadero.entity.Lavadero;
import co.com.themakers.lavadero.model.Respuesta;
import co.com.themakers.lavadero.service.LavaderoService;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/lavadero")
public class LavaderoRestController {
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(LavaderoRestController.class);
	
	@Autowired
	LavaderoService lavaderoService;
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/datatable", method = RequestMethod.POST)
	public DataTablesOutput<Lavadero> getLavaderoDatatable(@Valid @RequestBody DataTablesInput input) {	
		logger.debug("Listando lavaderos para datatable Controlador ....");	
		
		DataTablesOutput<Lavadero> lavaderos = lavaderoService.getLavaderosDatatable(input);
		return lavaderos;	
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Respuesta> adicionarLavadero(@RequestPart("lavadero") Lavadero lavadero,
			@RequestPart("file") MultipartFile file) {
		logger.debug("Adicionar un lavadero Controlador ....");		
		lavaderoService.addLavadero(file, lavadero);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.ADICION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Respuesta> modificarLavadero(@RequestPart("lavadero") Lavadero lavadero,
			@RequestPart("file") MultipartFile file) {
		logger.debug("Modificar un lavadero Controlador ....");		
		
		lavaderoService.updateLavadero(file, lavadero);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.MODIFICACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> eliminarLavadero(@PathVariable("codigo") long codigo) {

		logger.debug("Eliminar un tipo lavadero Controlador ....");	
		lavaderoService.eliminarLavadero(codigo);

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
		
		logger.debug("Cambiar estado un tipo lavadero Controlador ....");	
		lavaderoService.cambiarEstado(codigo, estado);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.MODIFICACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/disponibles", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Lavadero> listarLavaderosDisponibles() {
		logger.debug("======= Listando Lavaderos Disponibles");
		List<Lavadero> lavaderosDisponibles = lavaderoService.listarLavaderosDisponibles();
		return lavaderosDisponibles;
	}

	
	@PreAuthorize("hasAuthority('ROLE_PROPIETARIO') OR hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/XUsuario/{codigo}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Lavadero> listarLavaderosXUsuario(@PathVariable("codigo") long codigo) {
		logger.debug("======= Listando Lavaderos de Usuario");
		List<Lavadero> lavaderos = lavaderoService.listarLavaderosXUsuario(codigo);
		return lavaderos;
	}
	

}
