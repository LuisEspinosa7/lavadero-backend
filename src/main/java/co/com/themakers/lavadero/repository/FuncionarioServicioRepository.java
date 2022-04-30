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

import co.com.themakers.lavadero.entity.FuncionarioServicio;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Repository
public interface FuncionarioServicioRepository extends DataTablesRepository<FuncionarioServicio, Long> {
	
	FuncionarioServicio findByCodigo(long codigo);
	
	@Query("SELECT fs FROM FuncionarioServicio fs where fs.estado = ?1 ")
	List<FuncionarioServicio> findFuncionarioServicioPorEstado(int estado);
	
	@Modifying
	@Query("update FuncionarioServicio fs set fs.estado =:estado  where fs.codigo =:codigo")
	void setEstadoXCodigo(@Param("estado") Integer estado, @Param("codigo") Long codigo);	
	
	@Query("SELECT fs FROM FuncionarioServicio fs WHERE fs.usuario.codigo =:codigoUsuario AND fs.tipoServicio.codigo =:codigoTipoServicio  AND (fs.estado = 1 OR fs.estado = 0)")
	List<FuncionarioServicio> buscarXUsuarioYTipoServicioYestado(@Param("codigoUsuario") long codigoUsuario, @Param("codigoTipoServicio") long codigoTipoServicio);	
	
	
	@Query("SELECT fs FROM FuncionarioServicio fs WHERE fs.usuario.codigo in :codigos AND fs.tipoServicio.codigo =:codigoTipoServicio  AND fs.estado = 1")
	List<FuncionarioServicio> buscarConfiguracionPersonalTecnicoDisponible(@Param("codigos") List<Long> codigos, @Param("codigoTipoServicio") long codigoTipoServicio);	
	
}
