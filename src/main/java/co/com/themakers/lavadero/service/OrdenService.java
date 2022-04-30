/**
 * 
 */
package co.com.themakers.lavadero.service;

import java.util.List;

import co.com.themakers.lavadero.entity.Orden;

/**
 * @author Luis Llanos (Developer)
 *
 */
public interface OrdenService {

	public List<Orden> getOrdenesXLavadero(long codigoLavadero);

	public void addOrden(Orden o);
	
	public int consultarNumeroServiciosConsumidosCliente(long codigoLavadero, long codigoUsuario, long codigoTipoServicio);

}
