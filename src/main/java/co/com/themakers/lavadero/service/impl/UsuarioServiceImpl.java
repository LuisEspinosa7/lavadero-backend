/**
 * 
 */
package co.com.themakers.lavadero.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import co.com.themakers.lavadero.entity.Rol;
import co.com.themakers.lavadero.entity.TipoIdentificacion;
import co.com.themakers.lavadero.entity.Usuario;
import co.com.themakers.lavadero.exception.ObjectNotFoundException;
import co.com.themakers.lavadero.exception.ValueNotPermittedException;
import co.com.themakers.lavadero.repository.RolRepository;
import co.com.themakers.lavadero.repository.UsuarioRepository;
import co.com.themakers.lavadero.service.UsuarioService;
import co.com.themakers.lavadero.web.specifications.SearchCriteria;
import co.com.themakers.lavadero.web.specifications.UsuarioSpecification;
import co.com.themakers.lavadero.web.util.Constantes;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author LUIS
 *
 */

@Transactional
@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(UsuarioServiceImpl.class);

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	RolRepository rolRepository;

	@Autowired
	StorageService storageService;

	@Override
	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public void agregarUsuario(MultipartFile file, Usuario usuario) {
		logger.debug("==== Agregar Usuario Servicio");
		
		Usuario usuarioBusqueda = usuarioRepository.buscarUsuarioXIdentificacionYEmail(Constantes.ESTADO_ACTIVO,
				usuario.getIdentificacion(), usuario.getEmail());

		if (usuarioBusqueda == null) {
			usuario.setEstado(Constantes.ESTADO_ACTIVO);
			usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getIdentificacion()));
			
			
			Set<Rol> r = usuario.getRoles();
			Set<Rol> roles = new HashSet<Rol>();
			
			for (Rol ro : r) {
				System.out.println("Recorrer rol");
				Rol rol = rolRepository.findByCodigo(ro.getCodigo());		
				roles.add(rol);
			}  
			
			Rol rolCliente = rolRepository.findByCodigo(Constantes.CODIGO_ROL_CLIENTE);		
			roles.add(rolCliente);		
			

			//Set<Rol> roles = usuario.getRoles();
			//Rol rol = new Rol();
			//rol.setCodigo(Constantes.CODIGO_ROL_CLIENTE);
			//roles.add(rol);

			usuario.setRoles(roles);

			Usuario usuarioObject = usuarioRepository.save(usuario);
			storageService.cambiarImagenUsuario(file, Constantes.USUARIO_LOCATION, usuarioObject.getCodigo());
		} else {
			System.out.println("Ya existe el cliente");
			throw new ValueNotPermittedException(Mensajes.EXISTENTE);
		}
		
	}

	@Override
	public void updateUsuario(MultipartFile file, Usuario u) {
		logger.debug("==== Modificar Usuario Servicio");

		Usuario usuario = usuarioRepository.findById(u.getCodigo())
				.orElseThrow(() -> new ObjectNotFoundException(Mensajes.NOT_FOUND));
		System.out.println("Metodo cambiado. ...");

		usuario.setNombre1(u.getNombre1());
		usuario.setNombre2(u.getNombre2());
		usuario.setApellido1(u.getApellido1());
		usuario.setApellido2(u.getApellido2());

		TipoIdentificacion tipoIdentificacion = new TipoIdentificacion();
		tipoIdentificacion.setCodigo(u.getTipoIdentificacion().getCodigo());
		usuario.setTipoIdentificacion(tipoIdentificacion);

		usuario.setIdentificacion(u.getIdentificacion());
		usuario.setDireccion(u.getDireccion());
		usuario.setTelefono(u.getTelefono());
		usuario.setEmail(u.getEmail());

		System.out.println("Antes de poner los roles. ...");
		
		Set<Rol> r = u.getRoles();
		Set<Rol> roles = new HashSet<Rol>();
		//Set<Rol> roles = usuario.getRoles();	
		
		for (Rol ro : r) {
			System.out.println("Recorrer rol");
			Rol rol = rolRepository.findByCodigo(ro.getCodigo());		
			roles.add(rol);
		} 
		
		Rol rolCliente = rolRepository.findByCodigo(Constantes.CODIGO_ROL_CLIENTE);		
		roles.add(rolCliente);
		
		
		//Rol rol = new Rol();
		//rol.setCodigo(Constantes.CODIGO_ROL_CLIENTE);
		//roles.add(rol);

		usuario.setRoles(roles);

		System.out.println("Antes de guardar el usuario editado. ...");

		usuarioRepository.save(usuario);

		System.out.println("Antes de cambiar la imagen ...");
		storageService.cambiarImagenUsuario(file, Constantes.USUARIO_LOCATION, usuario.getCodigo());
	}

	@Override
	public void eliminarUsuario(long codigo) {
		logger.debug("==== Eliminar Usuario Servicio");

		boolean existe = usuarioRepository.existsById(codigo);
		System.out.println(existe);

		if (existe) {
			System.out.println("EXISTE EL REGISTRO");
			usuarioRepository.setEstadoXCodigo(Constantes.ESTADO_ELIMINADO, codigo);
		} else {
			System.out.println("NO EXISTE EL REGISTRO");
			throw new ObjectNotFoundException(Mensajes.NOT_FOUND);
		}

	}

	@Override
	public DataTablesOutput<Usuario> getUsuariosDatatable(DataTablesInput input) {
		logger.debug("==== Listar Usuario Datatable Servicio");
		UsuarioSpecification specification1 = new UsuarioSpecification(new SearchCriteria("estado", ":", 0));
		UsuarioSpecification specification2 = new UsuarioSpecification(new SearchCriteria("estado", ":", 1));
		return usuarioRepository.findAll(input, Specification.where(specification1).or(specification2));
	}

	@Override
	public void cambiarEstado(long codigo, int estado) {
		logger.debug("==== cambiarEstado - Marca");
		System.out.println("Codigo: " + codigo + " --- " + "Estado: " + estado);
		usuarioRepository.setEstadoXCodigo(estado, codigo);
	}

	@Override
	public List<Usuario> listarUsuariosDisponibles() {
		logger.debug("==== Listar Usuarios Disponibles Servicio");
		return usuarioRepository.listarUsuariosDisponibles(Constantes.ESTADO_ACTIVO);
	}

	@Override
	public List<Usuario> listarTecnicosDisponibles() {
		logger.debug("==== Listar Usuarios Tecnicos Disponibles Servicio");

		List<Usuario> usuarios = usuarioRepository.listarUsuariosDisponibles(Constantes.ESTADO_ACTIVO);
		List<Usuario> tecnicos = new ArrayList<Usuario>();

		logger.debug("==== Filtrando los tecnicos");
		for (Usuario usuario : usuarios) {

			boolean agregado = false;

			for (Rol rol : usuario.getRoles()) {
				if (!agregado) {
					if (rol.getCodigo() == Constantes.CODIGO_ROL_TECNICO) {
						tecnicos.add(usuario);
						agregado = true;
					}
				} else {
					break;
				}
			}

		}

		return tecnicos;
	}

	@Override
	public Usuario buscarPorIdentificacion(String identificacion) {
		logger.debug("==== Buscar Cliente por identificacion");

		Usuario usuario = usuarioRepository.obtenerClienteXDocumentoIdentificacion(identificacion,
				Constantes.ESTADO_ACTIVO);

		if (usuario == null) {
			System.out.println("NO EXISTE EL REGISTRO");
			throw new ObjectNotFoundException(Mensajes.BUSQUEDA_INCORRECTA);
		}

		return usuario;
	}

	@Override
	public void agregarCliente(Usuario cliente) {
		logger.debug("==== Agregar Usuario Cliente Servicio");

		Usuario usuarioBusqueda = usuarioRepository.buscarUsuarioXIdentificacionYEmail(Constantes.ESTADO_ACTIVO,
				cliente.getIdentificacion(), cliente.getEmail());

		if (usuarioBusqueda == null) {

			cliente.setEstado(Constantes.ESTADO_ACTIVO);
			cliente.setPassword(bCryptPasswordEncoder.encode(cliente.getIdentificacion()));

			Set<Rol> roles = new HashSet<Rol>();
			
			Rol rolCliente = rolRepository.findByCodigo(Constantes.CODIGO_ROL_CLIENTE);		
			roles.add(rolCliente);
			
			//Rol rol = new Rol();
			//rol.setCodigo(Constantes.CODIGO_ROL_CLIENTE);
			//roles.add(rol); 

			cliente.setRoles(roles);
			cliente.setImagen(Constantes.DEFECTO_IMAGE);

			usuarioRepository.save(cliente);

		} else {
			System.out.println("Ya existe el cliente");
			throw new ValueNotPermittedException(Mensajes.EXISTENTE);
		}

	}

	@Override
	public void updatePerfil(MultipartFile file, Usuario u) {
		logger.debug("==== Actualizar Datos de Perfil Servicio");

		Usuario usuario = usuarioRepository.findById(u.getCodigo())
				.orElseThrow(() -> new ObjectNotFoundException(Mensajes.NOT_FOUND));
		System.out.println("Metodo cambiado. ...");

		usuario.setNombre1(u.getNombre1());
		usuario.setNombre2(u.getNombre2());
		usuario.setApellido1(u.getApellido1());
		usuario.setApellido2(u.getApellido2());
		
		/**
		TipoIdentificacion tipoIdentificacion = new TipoIdentificacion();
		tipoIdentificacion.setCodigo(u.getTipoIdentificacion().getCodigo());
		usuario.setTipoIdentificacion(tipoIdentificacion);

		usuario.setIdentificacion(u.getIdentificacion());
		**/		
		
		usuario.setDireccion(u.getDireccion());
		usuario.setTelefono(u.getTelefono());
		//usuario.setEmail(u.getEmail());


		System.out.println("Antes de guardar el usuario editado. ...");

		usuarioRepository.save(usuario);

		System.out.println("Antes de cambiar la imagen ...");
		storageService.cambiarImagenUsuario(file, Constantes.USUARIO_LOCATION, usuario.getCodigo());
		
	}

	@Override
	public void updatePassword(long codigo, String password) {
		logger.debug("==== Cambiar password - Service");
		System.out.println("Codigo: " + codigo + " --- " + "Password: " + password);
		
		String passwordEncripted = bCryptPasswordEncoder.encode(password);
		usuarioRepository.setPasswordXCodigo(passwordEncripted, codigo);		
	}

}
