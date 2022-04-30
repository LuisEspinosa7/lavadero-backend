/**
 * 
 */
package co.com.themakers.lavadero.web.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.com.themakers.lavadero.service.impl.StorageService;
import co.com.themakers.lavadero.web.util.Constantes;



/**
 * @author Luis Llanos (Developer)
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/imagen")
public class ImagenRestController {
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(ImagenRestController.class);
	
	
	@Autowired
	StorageService storageService;
	
	
	/**
	 * Obtener imagen de usuarios
	 */
	@RequestMapping(value = "/usuario/{filename:.+}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource> obtenerUsuarioImagen(@PathVariable String filename) {
		logger.debug("Buscando imagen usuairo Controlador ....");	
		Resource file = storageService.loadFile(filename, Constantes.USUARIO_LOCATION);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	
	
	/**
	 * Obtener imagen de tipos vehiculos
	 */
	@RequestMapping(value = "/tipo-vehiculo/{filename:.+}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource> obtenerTipoVehiculoImagen(@PathVariable String filename) {
		logger.debug("Buscando imagen tipo vehiculo Controlador ....");	
		Resource file = storageService.loadFile(filename, Constantes.TIPO_VEHICULO_LOCATION);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	
	/**
	 * Obtener imagen de tipos servicio
	 */
	@RequestMapping(value = "/tipo-servicio/{filename:.+}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource> obtenerTipoServicioImagen(@PathVariable String filename) {
		logger.debug("Buscando imagen tipo servicio Controlador ....");	
		Resource file = storageService.loadFile(filename, Constantes.TIPO_SERVICIO_LOCATION);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	
	
	/**
	 * Obtener imagen de marcas
	 */
	@RequestMapping(value = "/marca/{filename:.+}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource> obtenerMarcaImagen(@PathVariable String filename) {
		logger.debug("Buscando imagen marca Controlador ....");	
		Resource file = storageService.loadFile(filename, Constantes.MARCA_LOCATION);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
		
	
	/**
	 * Obtener imagen de lavaderos
	 */
	@RequestMapping(value = "/lavadero/{filename:.+}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource> obtenerLavaderoImagen(@PathVariable String filename) {
		logger.debug("Buscando imagen lavadero Controlador ....");	
		Resource file = storageService.loadFile(filename, Constantes.LAVADERO_LOCATION);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	
	
	
	

}
