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
public class ReportePropietario3 {
	
	private String fechaHoraInicio;
	private String fechaHoraFin;
	private List<ItemReportePropietario3> items;
	private int granTotal;

	public ReportePropietario3() {
	}

	public ReportePropietario3(String fechaHoraInicio, String fechaHoraFin, List<ItemReportePropietario3> items, int granTotal) {
		this();
		this.fechaHoraInicio = fechaHoraInicio;
		this.fechaHoraFin = fechaHoraFin;
		this.items = items;
		this.granTotal = granTotal;
	}

}
