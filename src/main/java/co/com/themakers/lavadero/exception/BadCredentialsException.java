/**
 * 
 */
package co.com.themakers.lavadero.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author LUIS
 *
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class BadCredentialsException extends RuntimeException {
	
	public BadCredentialsException(String exception) {
	    super(exception);
	}

}
