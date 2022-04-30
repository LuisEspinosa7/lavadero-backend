/**
 * 
 */
package co.com.themakers.lavadero.service;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.multipart.MultipartFile;

import co.com.themakers.lavadero.entity.Usuario;

/**
 * 
 * @author Luis Llanos (Developer)
 *
 */
public interface UsuarioService {

	public Usuario findByEmail(String email);

	public void agregarUsuario(MultipartFile file, Usuario usuario);

	public void updateUsuario(MultipartFile file, Usuario u);

	public void eliminarUsuario(long codigo);

	public DataTablesOutput<Usuario> getUsuariosDatatable(DataTablesInput input);

	public void cambiarEstado(long codigo, int estado);

	public List<Usuario> listarUsuariosDisponibles();

	public List<Usuario> listarTecnicosDisponibles();

	public Usuario buscarPorIdentificacion(String identificacion);

	public void agregarCliente(Usuario cliente);

	public void updatePerfil(MultipartFile file, Usuario u);
	
	public void updatePassword(long codigo, String password);

}
