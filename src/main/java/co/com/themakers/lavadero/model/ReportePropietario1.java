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
public class ReportePropietario1 {
	
	private String fechaHoraInicio;
	private String fechaHoraFin;
	private List<ItemReportePropietario1> items;
	private int granTotal;

	public ReportePropietario1() {
	}

	public ReportePropietario1(String fechaHoraInicio, String fechaHoraFin, List<ItemReportePropietario1> items, int granTotal) {
		this();
		this.fechaHoraInicio = fechaHoraInicio;
		this.fechaHoraFin = fechaHoraFin;
		this.items = items;
		this.granTotal = granTotal;
	}

}
