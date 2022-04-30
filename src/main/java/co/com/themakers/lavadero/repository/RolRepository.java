/**
 * 
 */
package co.com.themakers.lavadero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.themakers.lavadero.entity.Rol;

/**
 * @author Luis Llanos (Developer)
 *
 */
public interface RolRepository extends JpaRepository<Rol, Long> {

	@Query("SELECT r FROM Rol r where r.estado = ?1 ")
	List<Rol> findRolesActivos(int estado);
	
	Rol findByCodigo(long codigo);

}
