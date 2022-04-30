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

import co.com.themakers.lavadero.entity.LiquidacionComision;
import co.com.themakers.lavadero.entity.LiquidacionFuncionario;
import co.com.themakers.lavadero.model.Respuesta;
import co.com.themakers.lavadero.service.LiquidacionComisionService;
import co.com.themakers.lavadero.service.LiquidacionFuncionarioService;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/liquidacionComision")
public class LiquidacionComisionRestController {
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(LiquidacionComisionRestController.class);

	@Autowired
	LiquidacionComisionService liquidacionComisionService;
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> liquidarComision(
			@RequestBody LiquidacionComision liquidacionComision) {
		logger.debug("Liquidando una comision Controlador ....");
		liquidacionComisionService.liquidarComision(liquidacionComision);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.LIQUIDACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/calcularComision", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> calcularComision(
			@RequestBody LiquidacionComision liquidacionComision) {
		logger.debug("Calculando Comision Controlador ....");
		LiquidacionComision comision = liquidacionComisionService.calcularComision(liquidacionComision);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.CALCULO_CORRECTO);
		respuesta.setData(comision);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/historial", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> buscarHistorialLiquidacionesComisiones(
			@RequestBody LiquidacionComision liquidacionComision) {
		logger.debug("Buscando historial liquidaciones comisiones Controlador ....");

		List<LiquidacionComision> historiaLiquidaciones = liquidacionComisionService.buscarHistorialLiquidacionesComisiones(liquidacionComision);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.BUSQUEDA_CORRECTA);
		respuesta.setData(historiaLiquidaciones);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	

}
