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

import co.com.themakers.lavadero.entity.Marca;

/**
 * @author Luis Llanos (Developer)
 *
 */

@Repository
public interface MarcaRepository  extends DataTablesRepository<Marca, Long> {
	
	@Modifying
	@Query("update Marca m set m.imagen =:imagen  where m.codigo =:codigo")
	void setImagenXCodigo(@Param("imagen") String imagen, @Param("codigo") Long codigo);
	
	@Modifying
	@Query("update Marca m set m.estado =:estado  where m.codigo =:codigo")
	void setEstadoXCodigo(@Param("estado") Integer estado, @Param("codigo") Long codigo);
	
	@Query("SELECT m FROM Marca m WHERE m.estado =:estado")
	List<Marca> listarDisponibles(@Param("estado") int estado);

	@Query("SELECT m FROM Marca m WHERE m.nombre =:nombre AND (m.estado = 1 OR m.estado = 0)")
	List<Marca> buscarXNombreYEstado(@Param("nombre") String nombre);
}
