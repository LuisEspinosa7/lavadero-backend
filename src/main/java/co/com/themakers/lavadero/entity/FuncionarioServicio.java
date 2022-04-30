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
@Table(name = "funcionario_servicio")
public class FuncionarioServicio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "fse_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usu_codigo")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tse_codigo")
	private TipoServicio tipoServicio;	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tli_codigo")
	private TipoLiquidacion tipoLiquidacion;	
	
	@Column(name = "fse_fecha_creacion", updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;	
	
	@Column(name = "fse_estado", nullable = false)
	private Integer estado;	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tpa_codigo")
	private TipoPago tipoPago;	
	
	@Column(name = "fse_valor_pago")
	private BigDecimal valorPago;	

}
