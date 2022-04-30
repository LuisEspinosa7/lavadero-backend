/**
 * 
 */
package co.com.themakers.lavadero.service;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.multipart.MultipartFile;

import co.com.themakers.lavadero.entity.TipoVehiculo;

/**
 * @author Luis Llanos (Developer)
 *
 */
public interface TipoVehiculoService {

	public void addTipoVehiculo(MultipartFile file, TipoVehiculo t);

	public void updateTipoVehiculo(MultipartFile file, TipoVehiculo t);

	public void eliminarTipoVehiculo(long codigo);

	public DataTablesOutput<TipoVehiculo> getTipoVehiculosDatatable(DataTablesInput input);

	public void cambiarEstado(long codigo, int estado);

	public List<TipoVehiculo> listarDisponibles();

}
