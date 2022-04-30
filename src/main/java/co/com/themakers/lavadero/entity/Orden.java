/**
 * 
 */
package co.com.themakers.lavadero.entity;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "orden")
public class Orden implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ord_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cve_codigo")
	private ClienteVehiculo clienteVehiculo;

	@Column(name = "ord_fecha_creacion", updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lav_codigo")
	private Lavadero lavadero;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "orden", orphanRemoval = true)
	private List<OrdenItem> items;

	@Column(name = "ord_estado", nullable = false)
	private Integer estado;

}
