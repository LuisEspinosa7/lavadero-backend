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
public class ReportePropietario2 {
	
	private String fechaHoraInicio;
	private String fechaHoraFin;
	private List<ItemReportePropietario2> items;
	private int granTotal;

	public ReportePropietario2() {
	}

	public ReportePropietario2(String fechaHoraInicio, String fechaHoraFin, List<ItemReportePropietario2> items, int granTotal) {
		this();
		this.fechaHoraInicio = fechaHoraInicio;
		this.fechaHoraFin = fechaHoraFin;
		this.items = items;
		this.granTotal = granTotal;
	}

}
