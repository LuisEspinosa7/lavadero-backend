/**
 * 
 */
package co.com.themakers.lavadero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.themakers.lavadero.entity.TipoPromocion;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Repository
public interface TipoPromocionRepository extends JpaRepository<TipoPromocion, Long> {
	
	@Query("SELECT tpr FROM TipoPromocion tpr where tpr.estado = ?1 ")
	List<TipoPromocion> findTipoPromocionActivos(int estado);

}
