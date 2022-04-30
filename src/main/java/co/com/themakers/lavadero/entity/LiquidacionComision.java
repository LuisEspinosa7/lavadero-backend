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
@Table(name = "liquidacion_comision")
public class LiquidacionComision implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "lic_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lse_codigo")
	private LavaderoServicio lavaderoServicio;

	@Column(name = "lic_fecha_creacion", updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	
	@Column(name = "lic_fecha_inicio", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;
	
	@Column(name = "lic_fecha_fin", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFin;
	
	@Column(name = "lic_valor_liquidacion")
	private BigDecimal valorLiquidacion;
	
	@Column(name = "lic_estado", nullable = false)
	private Integer estado;
	
	@Column(name = "lic_n_servicios")
	private Integer numeroServicios;

	

}
