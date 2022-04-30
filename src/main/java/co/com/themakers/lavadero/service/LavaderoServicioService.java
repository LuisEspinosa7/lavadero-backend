/**
 * 
 */
package co.com.themakers.lavadero.service;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import co.com.themakers.lavadero.entity.LavaderoServicio;

/**
 * @author Luis Llanos (Developer)
 *
 */
public interface LavaderoServicioService {

	public void addLavaderoServicio(LavaderoServicio ls);

	public DataTablesOutput<LavaderoServicio> getLavaderoServiciosDatatable(DataTablesInput input);

	public void eliminarLavaderoServicio(long codigo);

	public void cambiarEstado(long codigo, int estado);

	public void updateLavaderoServicio(LavaderoServicio lavaderoServicio);

	public List<LavaderoServicio> buscarXLavaderoYTipoServicioDisponible(long codigoLavadero, long codigoTipoServicio);

}
