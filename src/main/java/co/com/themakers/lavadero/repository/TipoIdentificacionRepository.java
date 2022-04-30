/**
 * 
 */
package co.com.themakers.lavadero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.themakers.lavadero.entity.TipoIdentificacion;

/**
 * 
 * @author Luis Llanos (Developer)
 *
 */
public interface TipoIdentificacionRepository extends JpaRepository<TipoIdentificacion, Long> {
	
	@Query("SELECT ti FROM TipoIdentificacion ti where ti.estado = ?1 ")
	List<TipoIdentificacion> findTipoIdentificacionActivos(int estado);
}
