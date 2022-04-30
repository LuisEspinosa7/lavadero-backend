/**
 * 
 */
package co.com.themakers.lavadero.model;

import java.math.BigDecimal;
import java.util.List;

import co.com.themakers.lavadero.entity.LiquidacionFuncionario;
import lombok.Data;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Data
public class ReportePropietario4 {
	
	private String fechaHoraInicio;
	private String fechaHoraFin;
	private List<LiquidacionFuncionario> items;
	private BigDecimal granTotal;

	public ReportePropietario4() {
	}

	public ReportePropietario4(String fechaHoraInicio, String fechaHoraFin, List<LiquidacionFuncionario> items, BigDecimal granTotal) {
		this();
		this.fechaHoraInicio = fechaHoraInicio;
		this.fechaHoraFin = fechaHoraFin;
		this.items = items;
		this.granTotal = granTotal;
	}

}
