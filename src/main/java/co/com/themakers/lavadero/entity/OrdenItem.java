/**
 * 
 */
package co.com.themakers.lavadero.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Data
@Entity
@Table(name = "orden_item")
public class OrdenItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "oit_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ord_codigo")
	@JsonIgnore
	private Orden orden;

	@ManyToOne
	@JoinColumn(name = "tse_codigo")
	private TipoServicio tipoServicio;

	//Precio original del servicio
	@Column(name = "oit_precio")
	private BigDecimal precio;
	
	//Precio Definitivo al cliente
	@Column(name = "oit_precio_servicio")
	private BigDecimal precioServicio;	
	
	@Column(name = "oit_aplica_promocion")
	private Integer aplicaPromocion;	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "ordenItem", orphanRemoval = true)
	private List<ItemFuncionario> itemFuncionarios;

	@Column(name = "oit_estado", nullable = false)
	private Integer estado;

}
