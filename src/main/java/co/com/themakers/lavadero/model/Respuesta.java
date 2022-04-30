/**
 * 
 */
package co.com.themakers.lavadero.model;

/**
 * @author LUIS
 *
 */
public class Respuesta {

	private boolean exito;
	private int code;
	private String mensaje;
	private Object data;

	public Respuesta() {
		super();
	}

	public Respuesta(boolean exito, int code, String mensaje, Object data) {
		super();
		this.exito = exito;
		this.code = code;
		this.mensaje = mensaje;
		this.data = data;
	}

	public boolean isExito() {
		return exito;
	}

	public void setExito(boolean exito) {
		this.exito = exito;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
