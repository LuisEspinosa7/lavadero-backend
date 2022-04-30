/**
 * 
 */
package co.com.themakers.lavadero.service;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import co.com.themakers.lavadero.entity.PersonalLavadero;

/**
 * @author Luis Llanos (Developer)
 *
 */
public interface PersonalLavaderoService {
	
	public void addPersonalLavadero(PersonalLavadero pl);
	
	public DataTablesOutput<PersonalLavadero> getPersonalLavaderosDatatable(DataTablesInput input);
	
	public void eliminarPersonalLavadero(long codigo);	
	
	public void cambiarEstado(long codigo, int estado);	

}
