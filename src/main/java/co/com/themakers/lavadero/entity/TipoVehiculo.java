/**
 * 
 */
package co.com.themakers.lavadero.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Data
@Entity
@Table(name = "tipo_vehiculo")
public class TipoVehiculo implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@Column(name = "tve_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "tve_nombre")
	private String nombre;
	
	@Column(name = "tve_descripcion")
	private String descripcion;
	
	@Column(name = "tve_imagen")
	private String imagen;
	
	@Column(name = "tve_estado")
	private int estado;

}
