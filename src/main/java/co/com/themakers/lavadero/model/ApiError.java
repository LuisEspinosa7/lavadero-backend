/**
 * 
 */
package co.com.themakers.lavadero.model;

import java.util.Date;

import lombok.Data;

/**
 * @author LUIS
 *
 */
@Data
public class ApiError {

	private Date timestamp;
	private int status;
	private String error;
	private String message;
	private String path;

	private ApiError() {
		timestamp = new Date();
	}

	public ApiError(int status, String error, String message, String path) {
		this();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

}
