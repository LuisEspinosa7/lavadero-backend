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

import co.com.themakers.lavadero.entity.TipoServicio;

/**
 * @author Luis Llanos (Developer)
 *
 */

@Repository
public interface TipoServicioRepository extends DataTablesRepository<TipoServicio, Long> {

	@Modifying
	@Query("update TipoServicio ts set ts.imagen =:imagen  where ts.codigo =:codigo")
	void setImagenXCodigo(@Param("imagen") String imagen, @Param("codigo") Long codigo);

	@Modifying
	@Query("update TipoServicio ts set ts.estado =:estado  where ts.codigo =:codigo")
	void setEstadoXCodigo(@Param("estado") Integer estado, @Param("codigo") Long codigo);

	@Query("SELECT t FROM TipoServicio t WHERE t.estado =:estado")
	List<TipoServicio> listarDisponibles(@Param("estado") int estado);

	@Query("SELECT ts FROM TipoServicio ts WHERE ts.nombre =:nombre AND (ts.estado = 1 OR ts.estado = 0)")
	List<TipoServicio> buscarXNombreYEstado(@Param("nombre") String nombre);

	@Query("SELECT ls.tipoServicio FROM LavaderoServicio ls WHERE ls.lavadero.codigo =:codigoLavadero AND ls.estado = 1 AND ls.tipoServicio.estado = 1")
	List<TipoServicio> buscarTiposServiciosXLavadero(@Param("codigoLavadero") long codigoLavadero);

	@Query("SELECT ls.tipoServicio FROM LavaderoServicio ls WHERE ls.lavadero.codigo =:codigoLavadero AND ls.estado = 1 AND ls.tipoServicio.estado = 1 AND ls.aplicaComision = 1")
	List<TipoServicio> buscarTiposServiciosAplicanComisionXLavadero(@Param("codigoLavadero") long codigoLavadero);

	TipoServicio findByCodigo(long codigo);

}
