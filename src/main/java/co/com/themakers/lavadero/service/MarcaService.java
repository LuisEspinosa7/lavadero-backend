/**
 * 
 */
package co.com.themakers.lavadero.service;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.multipart.MultipartFile;

import co.com.themakers.lavadero.entity.Marca;

/**
 * @author Luis Llanos (Developer)
 *
 */
public interface MarcaService {

	public void addMarca(MultipartFile file, Marca t);

	public void updateMarca(MultipartFile file, Marca t);

	public void eliminarMarca(long codigo);

	public DataTablesOutput<Marca> getMarcasDatatable(DataTablesInput input);

	public void cambiarEstado(long codigo, int estado);

	public List<Marca> listarDisponibles();

}
