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

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Data
@Entity
@Table(name = "cliente_vehiculo")
public class ClienteVehiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cve_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usu_codigo")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tve_codigo")
	private TipoVehiculo tipoVehiculo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mar_codigo")
	private Marca marca;

	@Column(name = "cve_placa")
	private String placa;

	@Column(name = "cve_kilometraje")
	private int kilometraje;

	@Column(name = "cve_fecha_creacion", updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;

	@Column(name = "cve_estado", nullable = false)
	private Integer estado;

}
