/**
 * 
 */
package co.com.themakers.lavadero.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author Luis Llanos (Developer)
 *
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessDeniedException extends RuntimeException {

	public AccessDeniedException(String exception) {
		super(exception);
	}

}
