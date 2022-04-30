/**
 * 
 */
package co.com.themakers.lavadero.service;

import java.util.List;

import co.com.themakers.lavadero.entity.LiquidacionComision;

/**
 * @author Luis Llanos (Developer)
 *
 */
public interface LiquidacionComisionService {

	public void liquidarComision(LiquidacionComision liquidacionComision);

	public LiquidacionComision calcularComision(LiquidacionComision liquidacionComision);

	public List<LiquidacionComision> buscarHistorialLiquidacionesComisiones(LiquidacionComision liquidacionComision);

}
