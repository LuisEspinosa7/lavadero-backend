/**
 * 
 */
package co.com.themakers.lavadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.themakers.lavadero.entity.ClienteVehiculo;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Repository
public interface ClienteVehiculoRepository extends JpaRepository<ClienteVehiculo, Long> {
	
	@Query("SELECT cv FROM ClienteVehiculo cv WHERE cv.placa =:placa AND cv.estado =:estado")
	ClienteVehiculo obtenerClienteVehiculoXPlaca(@Param("placa") String placa, @Param("estado") int estado);

}
