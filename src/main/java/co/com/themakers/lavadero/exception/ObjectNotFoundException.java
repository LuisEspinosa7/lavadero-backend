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
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {
	
	public ObjectNotFoundException(String exception) {
	    super(exception);
	  }

}
