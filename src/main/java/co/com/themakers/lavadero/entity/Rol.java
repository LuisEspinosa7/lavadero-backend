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
@Table(name = "rol")
public class Rol implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@Column(name = "rol_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "rol_nombre")
	private String nombre;
	
	@Column(name = "rol_estado")
	private int estado;

}
