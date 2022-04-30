/**
 * 
 */
package co.com.themakers.lavadero.service;

import java.util.List;

import co.com.themakers.lavadero.entity.LiquidacionFuncionario;

/**
 * @author Luis Llanos (Developer)
 *
 */
public interface LiquidacionFuncionarioService {
	
	public void liquidarFuncionarioTecnico(LiquidacionFuncionario liquidacionFuncionario);
	
	public List<LiquidacionFuncionario> buscarRegistrosLiquidablesDisponibles(LiquidacionFuncionario liquidacionFuncionario);
	
	public List<LiquidacionFuncionario> buscarHistorialLiquidacionesTecnicos(LiquidacionFuncionario liquidacionFuncionario);
	
	public List<LiquidacionFuncionario> buscarReporteLiquidacionesTecnicosParaPropietario(LiquidacionFuncionario liquidacionFuncionario);

}
