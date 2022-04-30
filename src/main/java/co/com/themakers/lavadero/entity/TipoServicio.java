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
@Table(name = "tipo_servicio")
public class TipoServicio implements Serializable  {
	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@Column(name = "tse_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "tse_nombre")
	private String nombre;
	
	@Column(name = "tse_descripcion")
	private String descripcion;
	
	@Column(name = "tse_imagen")
	private String imagen;
	
	@Column(name = "tse_estado")
	private int estado;
	
	

}
