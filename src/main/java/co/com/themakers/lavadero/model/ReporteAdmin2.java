/**
 * 
 */
package co.com.themakers.lavadero.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Data
public class ReporteAdmin2 {
	
	private String fechaHora;
	private List<ItemReporteAdmin2> items;
	private BigDecimal granTotalFacturacion;
	private BigDecimal granTotalComisiones;

	public ReporteAdmin2() {
	}

	public ReporteAdmin2(String fechaHora, List<ItemReporteAdmin2> items, BigDecimal granTotalFacturacion, BigDecimal granTotalComisiones) {
		this();
		this.fechaHora = fechaHora;
		this.items = items;
		this.granTotalFacturacion = granTotalFacturacion;
		this.granTotalComisiones = granTotalComisiones;
	}

}
