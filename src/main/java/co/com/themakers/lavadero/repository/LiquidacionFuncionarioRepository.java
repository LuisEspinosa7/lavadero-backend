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

import co.com.themakers.lavadero.entity.LiquidacionFuncionario;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Repository
public interface LiquidacionFuncionarioRepository extends JpaRepository<LiquidacionFuncionario, Long> {

	@Query("SELECT lf FROM LiquidacionFuncionario lf WHERE lf.lavadero.codigo =:codigoLavadero AND lf.funcionarioServicio.tipoServicio.codigo =:codigoTipoServicio AND lf.funcionarioServicio.tipoLiquidacion.codigo =:codigoTipoLiquidacion AND lf.fechaInicio >= :fechaInicio AND lf.fechaFin <= :fechaFin AND lf.estado = 1")
	List<LiquidacionFuncionario> buscarHistorialLiquidacionesTecnicos(@Param("codigoLavadero") long codigoLavadero,
			@Param("codigoTipoServicio") long codigoTipoServicio,
			@Param("codigoTipoLiquidacion") long codigoTipoLiquidacion, @Param("fechaInicio") Date fechaInicio,
			@Param("fechaFin") Date fechaFin);
	
	
	
	@Query("SELECT lf FROM LiquidacionFuncionario lf WHERE lf.lavadero.codigo =:codigoLavadero AND lf.fechaCreacion >= :fechaInicio AND lf.fechaCreacion <= :fechaFin AND lf.estado = 1")
	List<LiquidacionFuncionario> buscarReporteLiquidacionesTecnicosParaPropietario(
			@Param("codigoLavadero") long codigoLavadero,
			@Param("fechaInicio") Date fechaInicio,
			@Param("fechaFin") Date fechaFin);
	
	/**
	@Query("SELECT lf FROM LiquidacionFuncionario lf WHERE lf.lavadero.codigo =:codigoLavadero AND lf.funcionarioServicio.tipoServicio.codigo =:codigoTipoServicio AND lf.funcionarioServicio.tipoLiquidacion.codigo =:codigoTipoLiquidacion AND lf.fechaInicio >= :fechaInicio AND lf.fechaFin <= :fechaFin AND lf.estado = 1")
	List<LiquidacionFuncionario> buscarLiquidacionFuncionarioExistente(
			@Param("codigoLavadero") long codigoLavadero,
			@Param("codigoTipoServicio") long codigoTipoServicio,
			@Param("codigoTipoLiquidacion") long codigoTipoLiquidacion, 
			@Param("fechaInicio") Date fechaInicio,
			@Param("fechaFin") Date fechaFin);
	**/

}
