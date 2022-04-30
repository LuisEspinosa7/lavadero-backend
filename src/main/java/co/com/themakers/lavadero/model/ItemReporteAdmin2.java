/**
 * 
 */
package co.com.themakers.lavadero.model;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Data
public class ItemReporteAdmin2 {
	
	private String lavadero;
	private BigDecimal facturacion;
	private BigDecimal comision;

	public ItemReporteAdmin2() {
	}

	public ItemReporteAdmin2(String lavadero, BigDecimal facturacion, BigDecimal comision) {
		this();
		this.lavadero = lavadero;
		this.facturacion = facturacion;
		this.comision = comision;
	}

}
