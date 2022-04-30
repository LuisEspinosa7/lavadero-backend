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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.themakers.lavadero.entity.TipoLiquidacion;
import co.com.themakers.lavadero.entity.TipoServicio;
import co.com.themakers.lavadero.model.Respuesta;
import co.com.themakers.lavadero.service.TipoLiquidacionService;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/tipoLiquidacion")
public class TipoLiquidacionRestController {
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(TipoLiquidacionRestController.class);
	
	@Autowired
	TipoLiquidacionService tipoLiquidacionService;
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/datatable", method = RequestMethod.POST)
	public DataTablesOutput<TipoLiquidacion> getTiposLiquidacionDatatable(@Valid @RequestBody DataTablesInput input) {	
		logger.debug("Listando tipos lisquidacion para datatable Controlador ....");		
		
		DataTablesOutput<TipoLiquidacion> tiposLiquidacion = tipoLiquidacionService.getTipoLiquidacionesDatatable(input);
		return tiposLiquidacion;	
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> adicionarTipoLiquidacion(@RequestBody TipoLiquidacion tipoLiquidacion) {
		logger.debug("Adicionar un tipo liquidacion Controlador ....");		
		tipoLiquidacionService.addTipoLiquidacion(tipoLiquidacion);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.ADICION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> eliminarTipoLiquidacion(@PathVariable("codigo") long codigo) {

		logger.debug("Eliminar un tipo liquidacion Controlador ....");	
		tipoLiquidacionService.eliminarTipoLiquidacion(codigo);

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
		
		logger.debug("Cambiar estado un tipo liquidacion Controlador ....");	
		tipoLiquidacionService.cambiarEstado(codigo, estado);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.MODIFICACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') OR hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/disponibles", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<TipoLiquidacion> listarDisponibles() {
		logger.debug("======= Listando Tipos Liquidacion Disponibles");
		List<TipoLiquidacion> tipoLiquidacionDisponibles = tipoLiquidacionService.listarDisponibles();
		return tipoLiquidacionDisponibles;
	}
	

}
