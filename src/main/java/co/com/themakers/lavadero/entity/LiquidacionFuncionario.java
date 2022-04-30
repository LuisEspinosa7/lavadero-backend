/**
 * 
 */
package co.com.themakers.lavadero.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Data
@Entity
@Table(name = "liquidacion_funcionario")
public class LiquidacionFuncionario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "lif_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fse_codigo")
	private FuncionarioServicio funcionarioServicio;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lav_codigo")
	private Lavadero lavadero;	

	@Column(name = "lif_fecha_creacion", updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	
	@Column(name = "lif_fecha_inicio", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;
	
	@Column(name = "lif_fecha_fin", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFin;
	
	@Column(name = "lif_valor_pago")
	private BigDecimal valorPago;
	
	@Column(name = "lif_valor_servicio")
	private BigDecimal valorServicio;
	
	@Column(name = "lif_numero_servicios")
	private Integer numeroServicios;

	@Column(name = "lif_estado", nullable = false)
	private Integer estado;

}
