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
public class ItemReportePropietario1 {
	
	private Usuario usuario;
	private int numeroServiciosSolicitados;

	public ItemReportePropietario1() {
	}

	public ItemReportePropietario1(Usuario usuario, int numeroServiciosSolicitados) {
		this();
		this.usuario = usuario;
		this.numeroServiciosSolicitados = numeroServiciosSolicitados;
	}

}
