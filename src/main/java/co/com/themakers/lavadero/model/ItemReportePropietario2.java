/**
 * 
 */
package co.com.themakers.lavadero.model;

import co.com.themakers.lavadero.entity.Usuario;
import lombok.Data;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Data
public class ItemReportePropietario2 {
	
	private Usuario usuario;
	private int numeroServiciosRealizados;

	public ItemReportePropietario2() {
	}

	public ItemReportePropietario2(Usuario usuario, int numeroServiciosRealizados) {
		this();
		this.usuario = usuario;
		this.numeroServiciosRealizados = numeroServiciosRealizados;
	}

}
