/**
 * 
 */
package co.com.themakers.lavadero.model;

import java.util.List;

import co.com.themakers.lavadero.entity.TipoServicio;
import lombok.Data;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Data
public class ModelReporte {

	private String fechaHora;
	private String nombreReporte;
	private List<TipoServicio> tiposServicios;

	public ModelReporte() {
	}

	public ModelReporte(String fechaHora, String nombreReporte, List<TipoServicio> tiposServicios) {
		this();
		this.fechaHora = fechaHora;
		this.nombreReporte = nombreReporte;
		this.tiposServicios = tiposServicios;
	}

}
