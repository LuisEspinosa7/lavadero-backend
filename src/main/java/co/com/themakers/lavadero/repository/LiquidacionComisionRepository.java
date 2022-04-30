/**
 * 
 */
package co.com.themakers.lavadero.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.themakers.lavadero.entity.LiquidacionComision;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Repository
public interface LiquidacionComisionRepository extends JpaRepository<LiquidacionComision, Long> {
	
	
	@Query("SELECT lc FROM LiquidacionComision lc WHERE lc.lavaderoServicio.lavadero.codigo =:codigoLavadero AND lc.lavaderoServicio.tipoServicio.codigo =:codigoTipoServicio AND lc.estado = 1")
	List<LiquidacionComision> buscarHistorialLiquidacionesComisiones(
			@Param("codigoLavadero") long codigoLavadero,
			@Param("codigoTipoServicio") long codigoTipoServicio);
	
	

}
