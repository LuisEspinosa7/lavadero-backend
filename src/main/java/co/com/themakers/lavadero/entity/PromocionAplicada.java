/**
 * 
 */
package co.com.themakers.lavadero.entity;

import java.io.Serializable;
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

import lombok.Data;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Data
@Entity
@Table(name = "promocion_aplicada")
public class PromocionAplicada implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pap_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cve_codigo")
	private ClienteVehiculo clienteVehiculo;

	@Column(name = "pap_fecha_hora", updatable = false)
	// @CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lav_codigo")
	private Lavadero lavadero;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "oit_codigo")
	private OrdenItem ordenItem;

}
