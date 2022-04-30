/**
 * 
 */
package co.com.themakers.lavadero.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Data 
@Entity
@Table(name = "usuario")
public class UsuarioLogin implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "usu_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "usu_email")
	private String email;
	
	@Column(name = "usu_password")
	private String password;
	
	@Column(name = "usu_imagen")
	private String imagen;

	@Column(name = "usu_estado")
	private int estado;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol", joinColumns = { @JoinColumn(name = "usu_codigo") }, inverseJoinColumns = {
			@JoinColumn(name = "rol_codigo") })
	private Set<Rol> roles = new HashSet<Rol>();
	
	
	@NotBlank
	@Column(name = "usu_nombre_1")
	private String nombre1;

	@Column(name = "usu_nombre_2")
	private String nombre2;

	@NotBlank
	@Column(name = "usu_apellido_1")
	private String apellido1;

	@Column(name = "usu_apellido_2")
	private String apellido2;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tii_codigo")
	private TipoIdentificacion tipoIdentificacion;

	@NotBlank
	@Column(name = "usu_identificacion")
	private String identificacion;

	@NotBlank
	@Column(name = "usu_direccion")
	private String direccion;

	@NotBlank
	@Column(name = "usu_telefono")
	private String telefono;

}
