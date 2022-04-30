/**
 * 
 */
package co.com.themakers.lavadero.service;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.multipart.MultipartFile;

import co.com.themakers.lavadero.entity.Lavadero;

/**
 * @author Luis Llanos (Developer)
 *
 */
public interface LavaderoService {
	
	public void addLavadero(MultipartFile file, Lavadero t);
	
	public void updateLavadero(MultipartFile file, Lavadero t);
	
	public void eliminarLavadero(long codigo);	
	
	public DataTablesOutput<Lavadero> getLavaderosDatatable(DataTablesInput input);

	public void cambiarEstado(long codigo, int estado);	
	
	public List<Lavadero> listarLavaderosDisponibles();
	
	public List<Lavadero> listarLavaderosXUsuario(long codigo);

}
