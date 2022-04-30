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

import co.com.themakers.lavadero.entity.LavaderoServicio;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Repository
public interface LavaderoServicioRepository extends DataTablesRepository<LavaderoServicio, Long> {
	
	@Query("SELECT ls FROM LavaderoServicio ls where ls.estado = ?1 ")
	List<LavaderoServicio> findXEstado(int estado);
	
	@Modifying
	@Query("update LavaderoServicio ls set ls.estado =:estado where ls.codigo =:codigo")
	void setEstadoXCodigo(@Param("estado") Integer estado, @Param("codigo") Long codigo);
	
	@Query("SELECT ls FROM LavaderoServicio ls WHERE ls.lavadero.codigo =:codigoLavadero AND ls.tipoServicio.codigo =:codigoTipoServicio  AND (ls.estado = 1 OR ls.estado = 0)")
	List<LavaderoServicio> buscarXLavaderoYTipoServicioYestado(@Param("codigoLavadero") long codigoLavadero, @Param("codigoTipoServicio") long codigoTipoServicio);
	
	@Query("SELECT ls FROM LavaderoServicio ls WHERE ls.lavadero.codigo =:codigoLavadero AND ls.tipoServicio.codigo =:codigoTipoServicio  AND ls.estado = 1")
	List<LavaderoServicio> buscarXLavaderoYTipoServicioDisponible(@Param("codigoLavadero") long codigoLavadero, @Param("codigoTipoServicio") long codigoTipoServicio);

}
