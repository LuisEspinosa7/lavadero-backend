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
@Table(name = "marca")
public class Marca implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@Column(name = "mar_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "mar_nombre")
	private String nombre;
	
	@Column(name = "mar_imagen")
	private String imagen;
	
	@Column(name = "mar_estado")
	private int estado;

}
