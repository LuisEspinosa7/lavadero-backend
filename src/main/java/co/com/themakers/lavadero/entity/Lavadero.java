/**
 * 
 */
package co.com.themakers.lavadero.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "lavadero")
public class Lavadero implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "lav_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "lav_nombre")
	private String nombre;

	@Column(name = "lav_descripcion")
	private String descripcion;

	@Column(name = "lav_fecha_creacion", updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	
	@Column(name = "lav_imagen")
	private String imagen;
	
	@Column(name = "lav_nit")
	private String nit;

	@Column(name = "lav_estado")
	private int estado;

}
