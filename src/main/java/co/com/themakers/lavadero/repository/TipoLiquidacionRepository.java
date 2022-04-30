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

import co.com.themakers.lavadero.entity.TipoLiquidacion;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Repository
public interface TipoLiquidacionRepository extends DataTablesRepository<TipoLiquidacion, Long> {
	
	
	@Query("SELECT tl FROM TipoLiquidacion tl where tl.estado = ?1 ")
	List<TipoLiquidacion> findTipoLiquidacionPorEstado(int estado);
	
	@Modifying
	@Query("update TipoLiquidacion tl set tl.estado =:estado  where tl.codigo =:codigo")
	void setEstadoXCodigo(@Param("estado") Integer estado, @Param("codigo") Long codigo);
	
	@Query("SELECT tl FROM TipoLiquidacion tl WHERE tl.estado =:estado")
	List<TipoLiquidacion> listarDisponibles(@Param("estado") int estado);
	
	@Query("SELECT tl FROM TipoLiquidacion tl WHERE tl.nombre =:nombre AND (tl.estado = 1 OR tl.estado = 0)")
	List<TipoLiquidacion> buscarXNombreYEstado(@Param("nombre") String nombre);

}
