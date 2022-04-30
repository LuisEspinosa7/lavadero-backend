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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.com.themakers.lavadero.entity.Usuario;
import co.com.themakers.lavadero.model.Respuesta;
import co.com.themakers.lavadero.service.UsuarioService;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author LUIS
 *
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(UsuarioRestController.class);

	@Autowired
	UsuarioService usuarioService;

	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/datatable", method = RequestMethod.POST)
	public DataTablesOutput<Usuario> getUsuarioDatatable(@Valid @RequestBody DataTablesInput input) {
		logger.debug("Listando usuarios para datatable Controlador ....");

		DataTablesOutput<Usuario> usuarios = usuarioService.getUsuariosDatatable(input);
		return usuarios;
	}

	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') OR hasAuthority('ROLE_OPERARIO') OR hasAuthority('ROLE_PROPIETARIO')")
	@RequestMapping(value = "/findByEmail", method = RequestMethod.POST)
	public ResponseEntity<Respuesta> findByEmail(@RequestParam(value = "email") String email) {

		Usuario usuario = usuarioService.findByEmail(email);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.ENCONTRADO_CORRECTAMENTE);
		respuesta.setData(usuario);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Respuesta> agregarUsuario(@RequestPart("usuario") Usuario usuario,
			@RequestPart("file") MultipartFile file) {
		logger.debug("Adicionar un usuario Controlador ....");

		usuarioService.agregarUsuario(file, usuario);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.ADICION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Respuesta> modificarUsuario(@RequestPart("usuario") Usuario usuario,
			@RequestPart("file") MultipartFile file) {
		logger.debug("Modificar un usuario controlador ....");

		usuarioService.updateUsuario(file, usuario);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.MODIFICACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> eliminarUsuario(@PathVariable("codigo") long codigo) {

		logger.debug("Eliminar una usuario Controlador ....");
		usuarioService.eliminarUsuario(codigo);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.ELIMINACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/estado/{codigo}", method = RequestMethod.POST)
	public ResponseEntity<Respuesta> cambiarEstado(@PathVariable("codigo") long codigo,
			@RequestParam(value = "estado") int estado) {

		System.out.println("Codigo: " + codigo + " --- " + "Estado: " + estado);

		logger.debug("Cambiar estado a una marca Controlador ....");
		usuarioService.cambiarEstado(codigo, estado);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.MODIFICACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') OR hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/disponibles", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Usuario> listarUsuariosDisponibles() {
		logger.debug("======= Listando Usuarios Disponibles");
		List<Usuario> usuariosDisponibles = usuarioService.listarUsuariosDisponibles();
		return usuariosDisponibles;
	}

	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/tecnicos/disponibles", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Usuario> listarUsuariosTecnicosDisponibles() {
		logger.debug("======= Listando Usuarios Tecnicos Disponibles");
		List<Usuario> tecnicosDisponibles = usuarioService.listarTecnicosDisponibles();
		return tecnicosDisponibles;
	}

	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') OR hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/cliente/{identificacion}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> buscarClienteXIdentificacion(
			@PathVariable("identificacion") String identificacion) {
		logger.debug("======= Buscando cliente por identificacion");

		Usuario usuario = usuarioService.buscarPorIdentificacion(identificacion);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.BUSQUEDA_CORRECTA);
		respuesta.setData(usuario);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') OR hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/cliente", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> agregarCliente(@RequestBody Usuario usuario) {
		logger.debug("Adicionar un usuario cliente Controlador ....");

		usuarioService.agregarCliente(usuario);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.ADICION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') OR hasAuthority('ROLE_PROPIETARIO') OR hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/updatePerfil", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Respuesta> updatePerfil(@RequestPart("usuario") Usuario usuario,
			@RequestPart("file") MultipartFile file) {
		logger.debug("Actualizando Perfil controlador ....");

		usuarioService.updatePerfil(file, usuario);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.MODIFICACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') OR hasAuthority('ROLE_PROPIETARIO') OR hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/perfil/password/{codigo}", method = RequestMethod.POST)
	public ResponseEntity<Respuesta> cambiarPassword(@PathVariable("codigo") long codigo,
			@RequestParam(value = "password") String password) {

		System.out.println("Codigo: " + codigo + " --- " + "Password: " + password);

		logger.debug("Cambiar password Controlador ....");
		usuarioService.updatePassword(codigo, password);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.MODIFICACION_CORRECTA);
		respuesta.setData(null);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
	

}
