/**
 * 
 */
package co.com.themakers.lavadero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.themakers.lavadero.entity.OrdenItem;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Repository
public interface OrdenItemRepository extends JpaRepository<OrdenItem, Long> {

	@Query("SELECT oi FROM OrdenItem oi WHERE oi.orden.codigo =:codigoOrden AND oi.estado = 1")
	List<OrdenItem> listarOrdenItemsXOrden(@Param("codigoOrden") long codigoOrden);

	@Query(value = "SELECT * FROM OrdenItem oi WHERE oi.orden.codigo = :codigoOrden and oi.estado = 1", nativeQuery = true)
	List<OrdenItem> listarOrdenesItemsXOrdenNative(@Param("codigoOrden") long codigoOrden);

}
