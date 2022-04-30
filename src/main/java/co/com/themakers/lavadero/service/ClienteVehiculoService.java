/**
 * 
 */
package co.com.themakers.lavadero.service;

import co.com.themakers.lavadero.entity.ClienteVehiculo;

/**
 * @author Luis Llanos (Developer)
 *
 */
public interface ClienteVehiculoService {

	public ClienteVehiculo buscarPorPlaca(String placa);

	public void agregarClienteVehiculo(ClienteVehiculo clienteVehiculo);
	
	public void updateClienteVehiculo(ClienteVehiculo clienteVehiculo);

}
