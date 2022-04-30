/**
 * 
 */
package co.com.themakers.lavadero.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.themakers.lavadero.entity.ClienteVehiculo;
import co.com.themakers.lavadero.entity.Usuario;
import co.com.themakers.lavadero.model.Respuesta;
import co.com.themakers.lavadero.service.ClienteVehiculoService;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@RestController
@RequestMapping("/clienteVehiculo")
public class ClienteVehiculoRestController {
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(ClienteVehiculoRestController.class);
	
	@Autowired
	ClienteVehiculoService clienteVehiculoService;
	

	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') OR hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/vehiculo/{placa}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> buscarVehiculoXPlaca(
			@PathVariable("placa") String placa) {
		logger.debug("======= Buscando vehiculo por placa");

		ClienteVehiculo clienteVehiculo = clienteVehiculoService.buscarPorPlaca(placa);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.BUSQUEDA_CORRECTA);
		respuesta.setData(clienteVehiculo);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') OR hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/vehiculo", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> agregarVehiculo(@RequestBody ClienteVehiculo clienteVehiculo) {
		logger.debug("Adicionar un vehiculo Controlador ....");

		clienteVehiculoService.agregarClienteVehiculo(clienteVehiculo);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.ADICION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') OR hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/vehiculo", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> updateVehiculo(@RequestBody ClienteVehiculo clienteVehiculo) {
		logger.debug("Update un vehiculo Controlador ....");

		clienteVehiculoService.updateClienteVehiculo(clienteVehiculo);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.MODIFICACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	
}
