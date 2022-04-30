/**
 * 
 */
package co.com.themakers.lavadero.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.themakers.lavadero.entity.Usuario;

/**
 * @author LUIS
 *
 */

@Repository
public interface UsuarioRepository extends DataTablesRepository<Usuario, Long> {
	
	Usuario findByEmail(String email);
	
	@Modifying
	@Query("update Usuario u set u.imagen =:imagen  where u.codigo =:codigo")
	void setImagenXCodigo(@Param("imagen") String imagen, @Param("codigo") Long codigo);
	
	
	@Modifying
	@Query("update Usuario u set u.estado =:estado  where u.codigo =:codigo")
	void setEstadoXCodigo(@Param("estado") Integer estado, @Param("codigo") Long codigo);
	
	@Modifying
	@Query("update Usuario u set u.password =:pasword  where u.codigo =:codigo")
	void setPasswordXCodigo(@Param("pasword") String pasword, @Param("codigo") Long codigo);
	

	@Query("SELECT u FROM Usuario u WHERE u.codigo =:codigo")
	Usuario obtenerUsuarioXCodigo(@Param("codigo") Long codigo);
	
	
	@Query("SELECT u FROM Usuario u WHERE u.estado =:estado")
	List<Usuario> listarUsuariosDisponibles(@Param("estado") int estado);
	
	
	@Query("SELECT u FROM Usuario u WHERE u.identificacion =:identificacion AND u.estado =:estado")
	Usuario obtenerClienteXDocumentoIdentificacion(@Param("identificacion") String identificacion, @Param("estado") int estado);
	
	@Query("SELECT u FROM Usuario u WHERE u.estado =:estado AND (u.identificacion =:identificacion OR u.email =:email)")
	Usuario buscarUsuarioXIdentificacionYEmail(@Param("estado") int estado, @Param("identificacion") String identificacion, @Param("email") String email);
	
	Usuario findByCodigo(long codigo);
	
}
