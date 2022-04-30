/**
 * 
 */
package co.com.themakers.lavadero.service;

import java.util.List;

import co.com.themakers.lavadero.entity.OrdenItem;

/**
 * @author Luis Llanos (Developer)
 *
 */
public interface OrdenItemService {

	public void addOrdenItem(OrdenItem oi);
	
	public List<OrdenItem> getOrdenItemsXOrden(long codigoOrden);

}
