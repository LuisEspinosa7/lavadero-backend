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

import co.com.themakers.lavadero.entity.Lavadero;

/**
 * @author Luis Llanos (Developer)
 *
 */

@Repository
public interface LavaderoRepository extends DataTablesRepository<Lavadero, Long> {
	
	@Modifying
	@Query("update Lavadero l set l.imagen =:imagen  where l.codigo =:codigo")
	void setImagenXCodigo(@Param("imagen") String imagen, @Param("codigo") Long codigo);
	
	@Modifying
	@Query("update Lavadero l set l.estado =:estado  where l.codigo =:codigo")
	void setEstadoXCodigo(@Param("estado") Integer estado, @Param("codigo") Long codigo);

	@Query("SELECT l FROM Lavadero l WHERE l.estado =:estado")
	List<Lavadero> listarLavaderosDisponibles(@Param("estado") int estado);

	List<Lavadero> findByNombre(String nombre);
	
	Lavadero findByCodigo(long codigo);
	
		
}
