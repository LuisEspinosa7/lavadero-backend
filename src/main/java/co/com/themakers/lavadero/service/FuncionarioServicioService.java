/**
 * 
 */
package co.com.themakers.lavadero.service;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import co.com.themakers.lavadero.entity.FuncionarioServicio;

/**
 * @author Luis Llanos (Developer)
 *
 */
public interface FuncionarioServicioService {
	
	public void addFuncionarioServicio(FuncionarioServicio fs);
	
	public DataTablesOutput<FuncionarioServicio> getFuncionarioServiciosDatatable(DataTablesInput input);
	
	public void eliminarFuncionarioServicio(long codigo);	
	
	public void cambiarEstado(long codigo, int estado);	
	
	public void updateFuncionarioServicio(FuncionarioServicio fs);
	
	public List<FuncionarioServicio> buscarPersonalTecnicoXLavaderoDisponible(long lavaderoCodigo, long tipoServicioCodigo);

}
