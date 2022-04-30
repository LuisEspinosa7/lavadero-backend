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

import co.com.themakers.lavadero.entity.FuncionarioServicio;
import co.com.themakers.lavadero.entity.LavaderoServicio;
import co.com.themakers.lavadero.model.Respuesta;
import co.com.themakers.lavadero.service.FuncionarioServicioService;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/funcionarioServicio")
public class FuncionarioServicioRestController {
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(FuncionarioServicioRestController.class);
	
	@Autowired
	FuncionarioServicioService funcionarioServicioService;
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/datatable", method = RequestMethod.POST)
	public DataTablesOutput<FuncionarioServicio> getFuncionarioServicioDatatable(@Valid @RequestBody DataTablesInput input) {	
		logger.debug("Listando funcionarios servicios para datatable Controlador ....");	
		
		DataTablesOutput<FuncionarioServicio> funcionarioServicios = funcionarioServicioService.getFuncionarioServiciosDatatable(input);
		return funcionarioServicios;	
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> adicionarFuncionarioServicio(@RequestBody FuncionarioServicio funcionarioServicio) {
		logger.debug("Adicionar un funcionarios servicios Controlador ....");		
		funcionarioServicioService.addFuncionarioServicio(funcionarioServicio);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.ADICION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> eliminarFuncionarioServicio(@PathVariable("codigo") long codigo) {

		logger.debug("Eliminar un funcionarios servicios Controlador ....");	
		funcionarioServicioService.eliminarFuncionarioServicio(codigo);

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
		funcionarioServicioService.cambiarEstado(codigo, estado);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.MODIFICACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> modificarFuncionarioServicio(@RequestBody FuncionarioServicio funcionarioServicio) {
		logger.debug("Modificar un funcionario servicio Controlador ....");		
		
		funcionarioServicioService.updateFuncionarioServicio(funcionarioServicio);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.MODIFICACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/configuracion/lavadero/{codigo}", method = RequestMethod.POST)
	public ResponseEntity<Respuesta> listarConfiguracionFuncionarioServicioXLavadero(@PathVariable("codigo") long codigo, @RequestParam(value = "codigoTipoServicio") long codigoTipoServicio) {
		logger.debug("======= Listando personal tecnico Lavadero Disponible");
		
		List<FuncionarioServicio> funcionarios = funcionarioServicioService.buscarPersonalTecnicoXLavaderoDisponible(codigo, codigoTipoServicio);
		
		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.BUSQUEDA_CORRECTA);
		respuesta.setData(funcionarios);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	

}
