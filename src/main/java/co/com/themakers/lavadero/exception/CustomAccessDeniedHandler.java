/**
 * 
 */
package co.com.themakers.lavadero.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;

/**
 * @author LUIS
 *
 */
public class CustomAccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException exe)
			throws IOException, ServletException {
		
		System.out.println("Dentro del Custom");
		
		res.setContentType("application/json;charset=UTF-8");
		res.setStatus(403);
		
		String jsonStr = "{\"errorMessage\":" + "\"" + "Acceso Denegado" + "\"}";
		
		res.getWriter().write(jsonStr);
	}

}
