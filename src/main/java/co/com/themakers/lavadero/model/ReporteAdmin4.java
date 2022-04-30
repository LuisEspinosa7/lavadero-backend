/**
 * 
 */
package co.com.themakers.lavadero.model;

import java.util.List;

import lombok.Data;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Data
public class ReporteAdmin4 {
	
	private String fechaHora;
	private List<ItemReporteAdmin4> items;
	private int granTotal;

	public ReporteAdmin4() {
	}

	public ReporteAdmin4(String fechaHora, List<ItemReporteAdmin4> items, int granTotal) {
		this();
		this.fechaHora = fechaHora;
		this.items = items;
		this.granTotal = granTotal;
	}

}
