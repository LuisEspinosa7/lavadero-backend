/**
 * 
 */
package co.com.themakers.lavadero.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Luis Llanos (Developer)
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValueNotPermittedException extends RuntimeException {

	public ValueNotPermittedException(String exception) {
	    super(exception);
	}
	
}
