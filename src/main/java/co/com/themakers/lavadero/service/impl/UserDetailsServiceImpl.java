/**
 * 
 */
package co.com.themakers.lavadero.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.com.themakers.lavadero.entity.Rol;
import co.com.themakers.lavadero.entity.Usuario;
import co.com.themakers.lavadero.repository.UsuarioRepository;

/**
 * 
 * @author Luis Llanos (Developer)
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);

	private UsuarioRepository usuarioRepository;

	public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Usuario applicationUser = usuarioRepository.findByEmail(email);

		if (applicationUser == null) {
			throw new UsernameNotFoundException("No encontrado:: " + email);
		}

		Set<Rol> roles = applicationUser.getRoles();
		List<GrantedAuthority> authorities = new ArrayList<>();

		roles.forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getNombre()));
			logger.debug("Role: " + role.getNombre());
		});

		return new User(applicationUser.getEmail(), applicationUser.getPassword(), authorities);
	}

}
