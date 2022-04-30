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
@Table(name = "tipo_liquidacion")
public class TipoLiquidacion  implements Serializable  {
	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@Column(name = "tli_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "tli_nombre")
	private String nombre;
	
	@Column(name = "tli_descripcion")
	private String descripcion;
	
	@Column(name = "tli_estado")
	private int estado;

}
