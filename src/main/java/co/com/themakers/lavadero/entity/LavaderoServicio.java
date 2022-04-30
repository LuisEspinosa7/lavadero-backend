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
@Table(name = "lavadero_servicio")
public class LavaderoServicio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "lse_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lav_codigo")
	private Lavadero lavadero;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tse_codigo")
	private TipoServicio tipoServicio;

	@Column(name = "lse_aplica_comision")
	private int aplicaComision;

	@Column(name = "lse_valor_comision")
	private BigDecimal valorComision;

	@Column(name = "lse_aplica_promocion")
	private int aplicaPromocion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tpr_codigo")
	private TipoPromocion tipoPromocion;

	@Column(name = "lse_promocion_numero_ref")
	private int promocionNumeroRef;

	@Column(name = "lse_promocion_valor")
	private int valorPromocion;

	@Column(name = "lse_precio_estandar")
	private BigDecimal precioEstandar;

	@Column(name = "lse_fecha_creacion", updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;

	@Column(name = "lse_estado", nullable = false)
	private Integer estado;

}
