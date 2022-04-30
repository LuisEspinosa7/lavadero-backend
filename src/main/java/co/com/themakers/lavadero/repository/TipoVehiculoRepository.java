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

import co.com.themakers.lavadero.entity.TipoVehiculo;

/**
 * @author Luis Llanos (Developer)
 *
 */

@Repository
public interface TipoVehiculoRepository extends DataTablesRepository<TipoVehiculo, Long> {

	@Modifying
	@Query("update TipoVehiculo tv set tv.imagen =:imagen  where tv.codigo =:codigo")
	void setImagenXCodigo(@Param("imagen") String imagen, @Param("codigo") Long codigo);

	@Modifying
	@Query("update TipoVehiculo tv set tv.estado =:estado  where tv.codigo =:codigo")
	void setEstadoXCodigo(@Param("estado") Integer estado, @Param("codigo") Long codigo);

	@Query("SELECT tv FROM TipoVehiculo tv WHERE tv.estado =:estado")
	List<TipoVehiculo> listarDisponibles(@Param("estado") int estado);
	
	@Query("SELECT tv FROM TipoVehiculo tv WHERE tv.nombre =:nombre AND (tv.estado = 1 OR tv.estado = 0)")
	List<TipoVehiculo> buscarXNombreYEstado(@Param("nombre") String nombre);

}
