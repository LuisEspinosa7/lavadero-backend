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
import co.com.themakers.lavadero.entity.PromocionAplicada;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Repository
public interface PromocionAplicadaRepository extends JpaRepository<PromocionAplicada, Long> {
	
	@Query("SELECT pa FROM PromocionAplicada pa WHERE pa.lavadero.codigo =:codigoLavadero AND pa.clienteVehiculo.usuario.codigo =:codigoUsuario AND pa.ordenItem.tipoServicio.codigo =:codigoTipoServicio ORDER BY pa.fechaCreacion DESC ")
	List<PromocionAplicada> listarPromocionesAplicadasDESC(
			@Param("codigoLavadero") long codigoLavadero,
			@Param("codigoUsuario") long codigoUsuario,
			@Param("codigoTipoServicio") long codigoTipoServicio);

}
