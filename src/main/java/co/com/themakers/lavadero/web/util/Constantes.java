package co.com.themakers.lavadero.web.util;

/**
 * 
 * @author Luis Llanos (Developer)
 *
 */
public class Constantes {

	// ESTADOS DEL SISTEMA DE LAVADERO
	public static final int ESTADO_INACTIVO = 0;
	public static final int ESTADO_ACTIVO = 1;
	public static final int ESTADO_ELIMINADO = 2;

	public static final Long CODIGO_ROL_ADMINISTRADOR = 1L;
	public static final Long CODIGO_ROL_PROPIETARIO = 2L;
	public static final Long CODIGO_ROL_OPERARIO = 3L;
	public static final Long CODIGO_ROL_TECNICO = 4L;
	public static final Long CODIGO_ROL_CLIENTE = 5L;
	
	
	public static final int CODIGO_TIPO_PAGO_PRECIO_FIJO = 1;
	public static final int CODIGO_TIPO_PAGO_PORCENTAJE = 2;
	
	
	// RUTAS PARA IMAGENES
	
	/**
	 * Desarrollo
	 */
	/** Ubicacion de las carpetas para las imagenes */
	
	public static final String ROOT_LOCATION = "C://imagenes-lavadero";
	public static final String USUARIO_LOCATION = "C://imagenes-lavadero//usuario";
	public static final String TIPO_VEHICULO_LOCATION = "C://imagenes-lavadero//tipo-vehiculo";
	public static final String TIPO_SERVICIO_LOCATION = "C://imagenes-lavadero//tipo-servicio";
	public static final String MARCA_LOCATION = "C://imagenes-lavadero//marca";
	public static final String LAVADERO_LOCATION = "C://imagenes-lavadero//lavadero";
	
	public static final String LAVADERO_FOLDER_FOR_TICKETS = "C:\\imagenes-lavadero\\lavadero\\";	
	
	
	/** RUTA IMAGEN POR DEFECTO DESARROLLO -----*/
	
	public static final String DEFECTO_IMAGE = "default.png";
	public static final String DEFECTO_USUARIO_IMAGE = "C://imagenes-lavadero//usuario//default.png";
	public static final String DEFECTO_TIPO_VEHICULO_IMAGE = "C://imagenes-lavadero//tipo-vehiculo//default.png";
	public static final String DEFECTO_TIPO_SERVICIO_IMAGE = "C://imagenes-lavadero//tipo-servicio//default.png";
	public static final String DEFECTO_MARCA_IMAGE = "C://imagenes-lavadero//marca//default.png";
	public static final String DEFECTO_LAVADERO_IMAGE = "C://imagenes-lavadero//lavadero//default.png";	
	
	
	
	
	/**
	 * Produccion
	 */
	// Las rutas en linux se pueden asi:
	// /root/estudialo/imagenes-estudialo/usuario
	// ./imagenes-estudialo/institucion
	
	/** Ubicacion de las carpetas para las imagenes */
	/**
	public static final String ROOT_LOCATION = "/home/ubuntu/imagenes-lavadero";
	public static final String USUARIO_LOCATION = "/home/ubuntu/imagenes-lavadero/usuario";
	public static final String TIPO_VEHICULO_LOCATION = "/home/ubuntu/imagenes-lavadero/tipo-vehiculo";
	public static final String TIPO_SERVICIO_LOCATION = "/home/ubuntu/imagenes-lavadero/tipo-servicio";
	public static final String MARCA_LOCATION = "/home/ubuntu/imagenes-lavadero/marca";
	public static final String LAVADERO_LOCATION = "/home/ubuntu/imagenes-lavadero/lavadero";
	
	public static final String LAVADERO_FOLDER_FOR_TICKETS = "/home/ubuntu/imagenes-lavadero/lavadero/";	
	**/
	
	
	
	
	
	/** RUTA IMAGEN POR DEFECTO DESARROLLO -----*/
	/**
	public static final String DEFECTO_IMAGE = "default.png";
	public static final String DEFECTO_USUARIO_IMAGE = "/home/ubuntu/imagenes-lavadero/usuario/default.png";
	public static final String DEFECTO_TIPO_VEHICULO_IMAGE = "/home/ubuntu/imagenes-lavadero/tipo-vehiculo/default.png";
	public static final String DEFECTO_TIPO_SERVICIO_IMAGE = "/home/ubuntu/imagenes-lavadero/tipo-servicio/default.png";
	public static final String DEFECTO_MARCA_IMAGE = "/home/ubuntu/imagenes-lavadero/marca/default.png";
	public static final String DEFECTO_LAVADERO_IMAGE = "/home/ubuntu/imagenes-lavadero/lavadero/default.png";	
	**/
	
	
	

}
