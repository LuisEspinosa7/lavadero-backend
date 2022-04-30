/**
 * 
 */
package co.com.themakers.lavadero.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.com.themakers.lavadero.model.ApiError;
import co.com.themakers.lavadero.model.Respuesta;
import co.com.themakers.lavadero.web.util.Mensajes;





/**
 * @author LUIS
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {	
	
	
	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<ApiError> accessDeniedException(AccessDeniedException ex, WebRequest request) {
		
		String responseText = Mensajes.FORBBIDEN;
		ApiError error = new ApiError(Integer.valueOf(HttpStatus.FORBIDDEN.toString().split(" ")[0]), HttpStatus.FORBIDDEN.name(),
				responseText, "");
				
		return new ResponseEntity(error, HttpStatus.FORBIDDEN);
	}	
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String responseText = ex.getMessage();
		ApiError error = new ApiError(Integer.valueOf(HttpStatus.BAD_REQUEST.toString().split(" ")[0]), HttpStatus.BAD_REQUEST.name(),
				responseText, "");
	
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(ObjectNotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundException(ObjectNotFoundException ex, WebRequest request) {
		
		System.out.println("==== " + ex.getMessage());
		
		String responseText = ex.getMessage();
		ApiError error = new ApiError(Integer.valueOf(HttpStatus.NOT_FOUND.toString().split(" ")[0]), HttpStatus.NOT_FOUND.name(),
				responseText, "");
				
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(BadCredentialsException.class)
	public final ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
		
		String responseText = Mensajes.BAD_CREDENTIALS;
		ApiError error = new ApiError(Integer.valueOf(HttpStatus.BAD_REQUEST.toString().split(" ")[0]), HttpStatus.BAD_REQUEST.name(),
				responseText, "");
		
		
		return new ResponseEntity(error, HttpStatus.UNAUTHORIZED);
	}
	
	
	@ExceptionHandler(ValueNotPermittedException.class)
	public final ResponseEntity<Object> handleValueNotPermittedException(ValueNotPermittedException ex, WebRequest request) {
		
		String responseText = ex.getMessage();
		ApiError error = new ApiError(Integer.valueOf(HttpStatus.BAD_REQUEST.toString().split(" ")[0]), HttpStatus.BAD_REQUEST.name(),
				responseText, "");
		
		
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		
		String responseText = Mensajes.BAD_CREDENTIALS;
		ApiError error = new ApiError(Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.toString().split(" ")[0]), HttpStatus.INTERNAL_SERVER_ERROR.name(),
				responseText, "");
		
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	**/
	

}
