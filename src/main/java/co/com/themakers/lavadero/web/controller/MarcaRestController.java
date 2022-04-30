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

import co.com.themakers.lavadero.entity.Marca;
import co.com.themakers.lavadero.entity.TipoVehiculo;
import co.com.themakers.lavadero.model.Respuesta;
import co.com.themakers.lavadero.service.MarcaService;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/marca")
public class MarcaRestController {
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(MarcaRestController.class);
	
	@Autowired
	MarcaService marcaService;
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/datatable", method = RequestMethod.POST)
	public DataTablesOutput<Marca> getMarcaDatatable(@Valid @RequestBody DataTablesInput input) {	
		logger.debug("Listando marcas para datatable Controlador ....");	
		
		DataTablesOutput<Marca> marcas = marcaService.getMarcasDatatable(input);
		return marcas;	
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Respuesta> adicionarMarca(@RequestPart("marca") Marca marca,
			@RequestPart("file") MultipartFile file) {
		logger.debug("Adicionar una marca Controlador ....");		
		marcaService.addMarca(file, marca);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.ADICION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Respuesta> modificarMarca(@RequestPart("marca") Marca marca,
			@RequestPart("file") MultipartFile file) {
		logger.debug("Modificar una marca Controlador ....");		
		
		marcaService.updateMarca(file, marca);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.MODIFICACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> eliminarMarca(@PathVariable("codigo") long codigo) {

		logger.debug("Eliminar una marca Controlador ....");	
		marcaService.eliminarMarca(codigo);

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
		
		logger.debug("Cambiar estado a una marca Controlador ....");	
		marcaService.cambiarEstado(codigo, estado);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.MODIFICACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') OR hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/disponibles", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Marca> listarDisponibles() {
		logger.debug("======= Listando Marcas Disponibles");
		List<Marca> marcasDisponibles = marcaService.listarDisponibles();
		return marcasDisponibles;
	}
	
}
