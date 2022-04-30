/**
 * 
 */
package co.com.themakers.lavadero.model;

import lombok.Data;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Data
public class ItemReporteAdmin4 {

	private String lavadero;
	private int numeroServiciosPrestados;

	public ItemReporteAdmin4() {
	}

	public ItemReporteAdmin4(String lavadero, int numeroServiciosPrestados) {
		this();
		this.lavadero = lavadero;
		this.numeroServiciosPrestados = numeroServiciosPrestados;
	}

}
