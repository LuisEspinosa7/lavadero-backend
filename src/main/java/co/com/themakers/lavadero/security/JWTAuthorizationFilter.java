
package co.com.themakers.lavadero.security;


import static co.com.themakers.lavadero.security.SecurityConstants.HEADER_STRING;
import static co.com.themakers.lavadero.security.SecurityConstants.SECRET;
import static co.com.themakers.lavadero.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import co.com.themakers.lavadero.entity.Rol;
import co.com.themakers.lavadero.entity.Usuario;
import co.com.themakers.lavadero.exception.AccessDeniedException;
import co.com.themakers.lavadero.repository.UsuarioRepository;

/**
 * @author LUIS
 *
 */

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private UsuarioRepository usuarioRepository;

	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	public JWTAuthorizationFilter(AuthenticationManager authManager, UsuarioRepository usuarioRepository) {
		super(authManager);
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("DENTRO DEL doFilterInternal ");

		String header = req.getHeader(HEADER_STRING);

		if (header == null || !header.startsWith(TOKEN_PREFIX) || !validarToken(req)) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req, res);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}
	
	
	

	private boolean validarToken(HttpServletRequest request) {
		System.out.println("Validando el token");
		
		String token = request.getHeader(HEADER_STRING);
		boolean valido = false;
				
		try {
		    Algorithm algorithm = Algorithm.HMAC512(SECRET.getBytes());
		    JWTVerifier verifier = JWT.require(algorithm)		  
		        .build(); //Reusable verifier instance
		    DecodedJWT jwt = verifier.verify(token.replace(TOKEN_PREFIX, ""));
		    valido = true;
		} catch (JWTVerificationException exception){
		    //Invalid signature/claims
			valido = false;
		} 
		
		return valido;
	}
	

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse res) {

		String token = request.getHeader(HEADER_STRING);

		if (token != null) {

			// parse the token.
			String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build()
					.verify(token.replace(TOKEN_PREFIX, "")).getSubject();

			if (user != null) {

				logger.debug("Objeto user: " + user);

				// BUSCAR USUARIO POR EMAIL
				Usuario usuario = usuarioRepository.findByEmail(user);

				Set<Rol> roles = usuario.getRoles();
				List<GrantedAuthority> authorities = new ArrayList<>();

				roles.forEach(role -> {
					authorities.add(new SimpleGrantedAuthority(role.getNombre()));
					logger.debug("Role: " + role.getNombre());
				});

				return new UsernamePasswordAuthenticationToken(user, null, authorities);
			}

			return null;
		}
		return null;
	}
	

}
