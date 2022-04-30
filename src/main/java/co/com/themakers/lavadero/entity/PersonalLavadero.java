/**
 * 
 */
package co.com.themakers.lavadero.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Data
@Entity
@Table(name = "personal_lavadero")
public class PersonalLavadero implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "pla_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usu_codigo")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lav_codigo")
	private Lavadero lavadero;
	
	@Column(name = "pla_estado", nullable = false)
	private Integer estado;	
}
