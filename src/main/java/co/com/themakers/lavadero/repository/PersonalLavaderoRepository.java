/**
 * 
 */
package co.com.themakers.lavadero.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.themakers.lavadero.entity.PersonalLavadero;
import co.com.themakers.lavadero.entity.Usuario;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Repository
public interface PersonalLavaderoRepository extends DataTablesRepository<PersonalLavadero, Long> {

	@Query("SELECT pl FROM PersonalLavadero pl where pl.estado = ?1 ")
	List<PersonalLavadero> findPersonalLavaderoPorEstado(int estado);

	@Modifying
	@Query("update PersonalLavadero pl set pl.estado =:estado  where pl.codigo =:codigo")
	void setEstadoXCodigo(@Param("estado") Integer estado, @Param("codigo") Long codigo);

	@Query("SELECT pl FROM PersonalLavadero pl WHERE pl.usuario.codigo =:codigoUsuario AND pl.estado =:estado AND pl.lavadero.estado =:estado")
	List<PersonalLavadero> listarVinculacionesActivasUsuario(@Param("codigoUsuario") long codigoUsuario,
			@Param("estado") int estado);

	@Query("SELECT pl FROM PersonalLavadero pl WHERE pl.usuario.codigo =:codigoUsuario AND pl.lavadero.codigo =:codigoLavadero AND (pl.estado = 1 OR pl.estado = 0)")
	List<PersonalLavadero> buscarXUsuarioYLavaderoYestado(@Param("codigoUsuario") long codigoUsuario,
			@Param("codigoLavadero") long codigoLavadero);
	
	
	@Query("SELECT pl.usuario FROM PersonalLavadero pl WHERE pl.lavadero.codigo =:codigoLavadero AND pl.estado = 1 AND pl.lavadero.estado = 1")
	List<Usuario> buscarPersonalXLavaderoDisponible(@Param("codigoLavadero") long codigoLavadero);
	
	

}
