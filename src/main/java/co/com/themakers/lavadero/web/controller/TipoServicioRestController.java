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
import co.com.themakers.lavadero.entity.TipoServicio;
import co.com.themakers.lavadero.entity.TipoVehiculo;
import co.com.themakers.lavadero.model.Respuesta;
import co.com.themakers.lavadero.service.TipoServicioService;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/tipoServicio")
public class TipoServicioRestController {
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(TipoServicioRestController.class);
	
	@Autowired
	TipoServicioService tipoServicioService;
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/datatable", method = RequestMethod.POST)
	public DataTablesOutput<TipoServicio> getTiposServicioDatatable(@Valid @RequestBody DataTablesInput input) {	
		logger.debug("Listando tipos servicio para datatable Controlador ....");	
		
		DataTablesOutput<TipoServicio> tiposServicio = tipoServicioService.getTipoServiciosDatatable(input);
		return tiposServicio;	
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Respuesta> adicionarTipoServicio(@RequestPart("tipoServicio") TipoServicio tipoServicio,
			@RequestPart("file") MultipartFile file) {
		logger.debug("Adicionar un tipo servicio Controlador ....");		
		tipoServicioService.addTipoServicio(file, tipoServicio);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.ADICION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Respuesta> modificarTipoServicio(@RequestPart("tipoServicio") TipoServicio tipoServicio,
			@RequestPart("file") MultipartFile file) {
		logger.debug("Modificar un tipo servicio Controlador ....");		
		
		tipoServicioService.updateTipoServicio(file, tipoServicio);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.MODIFICACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> eliminarTipoServicio(@PathVariable("codigo") long codigo) {

		logger.debug("Eliminar un tipo servicio Controlador ....");	
		tipoServicioService.eliminarTipoServicio(codigo);

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
		
		logger.debug("Cambiar estado un tipo servicio Controlador ....");	
		tipoServicioService.cambiarEstado(codigo, estado);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.MODIFICACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') OR hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/disponibles", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<TipoServicio> listarDisponibles() {
		logger.debug("======= Listando Tipos Servicios Disponibles");
		List<TipoServicio> tipoServicioDisponibles = tipoServicioService.listarDisponibles();
		return tipoServicioDisponibles;
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') OR hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/disponibles/lavadero/{codigo}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> listarTiposServiciosXLavadero(@PathVariable("codigo") long codigo) {
		logger.debug("======= Listando Tipos Servicios X Lavadero Disponibles");
		
		List<TipoServicio> tipoServicioDisponibles = tipoServicioService.listarTiposServiciosDisponiblesXLavadero(codigo);
		
		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.BUSQUEDA_CORRECTA);
		respuesta.setData(tipoServicioDisponibles);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/aplicanComision/lavadero/{codigo}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> listarTiposServiciosAplicanComisionXLavadero(@PathVariable("codigo") long codigo) {
		logger.debug("======= Listando Tipos Servicios Que aplican comision X Lavadero Disponibles");
		
		List<TipoServicio> tipoServicioAplicanComisionDisponibles = tipoServicioService.listarTiposServiciosAplicanComisionDisponiblesXLavadero(codigo);
		
		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.BUSQUEDA_CORRECTA);
		respuesta.setData(tipoServicioAplicanComisionDisponibles);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
}
