/**
 * 
 */
package co.com.themakers.lavadero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.themakers.lavadero.entity.ItemFuncionario;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Repository
public interface ItemFuncionarioRepository extends JpaRepository<ItemFuncionario, Long> {
	
	@Query(value = "SELECT * FROM ItemFuncionario if WHERE if.ordenItem.codigo = :codigoOrdenItem and if.estado = 1", nativeQuery = true)
	List<ItemFuncionario> listarItemsFuncionariosXOrdenItemNative(@Param("codigoOrdenItem") long codigoOrdenItem);

}
