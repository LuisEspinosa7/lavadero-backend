/**
 * 
 */
package co.com.themakers.lavadero.security;

import static co.com.themakers.lavadero.security.SecurityConstants.EXPIRATION_TIME;
import static co.com.themakers.lavadero.security.SecurityConstants.HEADER_STRING;
import static co.com.themakers.lavadero.security.SecurityConstants.SECRET;
import static co.com.themakers.lavadero.security.SecurityConstants.TOKEN_PREFIX;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.themakers.lavadero.entity.Usuario;
import co.com.themakers.lavadero.entity.UsuarioLogin;
import co.com.themakers.lavadero.model.ApiError;
import co.com.themakers.lavadero.web.util.Mapeos;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * 
 * @author Luis Llanos (Developer)
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {

		try {

			UsuarioLogin credentials = new ObjectMapper().readValue(req.getInputStream(), UsuarioLogin.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(),
					credentials.getPassword(), new ArrayList<>()));

		} catch (IOException e) {
			// throw new RuntimeException(e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String token = JWT.create().withSubject(((User) auth.getPrincipal()).getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).sign(HMAC512(SECRET.getBytes()));
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		SecurityContextHolder.clearContext();
		String mensaje = failed.getMessage();
		String responseText;

		switch (mensaje) {
		case "User is disabled":
			responseText = Mensajes.DISABLED_USER;
			break;
		case "User credentials have expired":
			responseText = Mensajes.CREDENTIALS_EXPIRED;
			break;
		case "Bad credentials":
			responseText = Mensajes.BAD_CREDENTIALS;
			break;
		case "User account is locked":
			responseText = Mensajes.USER_BLOQUED;
			break;
		default:
			responseText = Mensajes.ERROR_SERVIDOR;
			break;
		}

		System.out.println(responseText);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();

		ApiError error = new ApiError(Integer.valueOf(HttpStatus.FORBIDDEN.toString().split(" ")[0]), HttpStatus.FORBIDDEN.name(),
				responseText, "");
		
		Mapeos mapper = new Mapeos();
		out.print(mapper.convertObjectToJson(error));
		out.flush();
	}

}
