/**
 * 
 */
package co.com.themakers.lavadero.service;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.multipart.MultipartFile;

import co.com.themakers.lavadero.entity.TipoServicio;

/**
 * @author Luis Llanos (Developer)
 *
 */
public interface TipoServicioService {
	
	public void addTipoServicio(MultipartFile file, TipoServicio t);
	
	public void updateTipoServicio(MultipartFile file, TipoServicio t);
	
	public void eliminarTipoServicio(long codigo);	
	
	public DataTablesOutput<TipoServicio> getTipoServiciosDatatable(DataTablesInput input);

	public void cambiarEstado(long codigo, int estado);	
	
	public List<TipoServicio> listarDisponibles();
	
	public List<TipoServicio> listarTiposServiciosDisponiblesXLavadero(long codigoLavadero);
	
	public List<TipoServicio> listarTiposServiciosAplicanComisionDisponiblesXLavadero(long codigoLavadero);

}
