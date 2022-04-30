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
public class ItemReportePropietario3 {
	
	private String servicio;
	private int numeroRealizados;

	public ItemReportePropietario3() {
	}

	public ItemReportePropietario3(String servicio, int numeroRealizados) {
		this();
		this.servicio = servicio;
		this.numeroRealizados = numeroRealizados;
	}

}
