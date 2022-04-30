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

import co.com.themakers.lavadero.entity.Orden;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {

	@Query("SELECT o FROM Orden o WHERE o.lavadero.codigo =:codigoLavadero AND o.estado = 1")
	List<Orden> listarOrdenesXLavadero(@Param("codigoLavadero") long codigoLavadero);

	@Query("SELECT o FROM Orden o WHERE o.lavadero.codigo =:codigoLavadero AND o.estado = 1 AND o.fechaCreacion >= :fechaInicio AND o.fechaCreacion <= :fechaFin")
	List<Orden> listarOrdenesXLavaderoYRangoFechas(@Param("codigoLavadero") long codigoLavadero,
			@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

	@Query(value = "SELECT * FROM Orden o,  WHERE o.lavadero.codigo = :codigoLavadero and o.estado = 1", nativeQuery = true)
	List<Orden> listarOrdenesXLavaderoNative(@Param("codigoLavadero") long codigoLavadero);
	
	@Query("SELECT o FROM Orden o WHERE o.estado = 1 AND o.fechaCreacion >= :fechaHoraInicio AND o.fechaCreacion <= :fechaHoraFin")
	List<Orden> listarOrdenesXDia(@Param("fechaHoraInicio") Date fechaHoraInicio, @Param("fechaHoraFin") Date fechaHoraFin);
	
	
	@Query("SELECT o FROM Orden o WHERE o.lavadero.codigo =:codigoLavadero AND o.clienteVehiculo.usuario.codigo =:codigoUsuario AND o.estado = 1 AND o.fechaCreacion > :fechaInicio")
	List<Orden> listarOrdenesXLavaderoXClienteYFechaInicial(
			@Param("codigoLavadero") long codigoLavadero,
			@Param("codigoUsuario") long codigoUsuario,
			@Param("fechaInicio") Date fechaInicio);

}
