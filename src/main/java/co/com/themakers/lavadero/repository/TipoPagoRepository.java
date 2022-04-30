/**
 * 
 */
package co.com.themakers.lavadero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.themakers.lavadero.entity.TipoPago;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Repository
public interface TipoPagoRepository extends JpaRepository<TipoPago, Long> {
	
	@Query("SELECT tpa FROM TipoPago tpa where tpa.estado = ?1 ")
	List<TipoPago> findTipoPagosActivos(int estado);

}
