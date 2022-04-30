/**
 * 
 */
package co.com.themakers.lavadero.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Data
@Entity
@Table(name = "item_funcionario")
public class ItemFuncionario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ifu_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "oit_codigo")
	@JsonIgnore
	private OrdenItem ordenItem;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fse_codigo")
	private FuncionarioServicio funcionarioServicio;

	@Column(name = "ifu_pago_funcionario")
	private BigDecimal pagoFuncionario;

	@Column(name = "ifu_estado", nullable = false)
	private Integer estado;

}
