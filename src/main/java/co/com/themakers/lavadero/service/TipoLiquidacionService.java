/**
 * 
 */
package co.com.themakers.lavadero.service;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import co.com.themakers.lavadero.entity.TipoLiquidacion;

/**
 * @author Luis Llanos (Developer)
 *
 */
public interface TipoLiquidacionService {
	
	public void addTipoLiquidacion(TipoLiquidacion t);
	
	public void eliminarTipoLiquidacion(long codigo);	
	
	public DataTablesOutput<TipoLiquidacion> getTipoLiquidacionesDatatable(DataTablesInput input);

	public void cambiarEstado(long codigo, int estado);	
	
	public List<TipoLiquidacion> listarDisponibles();

}
