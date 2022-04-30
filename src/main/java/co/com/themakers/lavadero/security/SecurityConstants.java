/**
 * 
 */
package co.com.themakers.lavadero.security;

/**
 * 
 * @author Luis Llanos (Developer)
 *
 */
public class SecurityConstants {
	
	// LA FECHA DE EXPIRACION ES EN MILISEGUNDOS
		
	// 1 MINUTO = 60 SEGUNDOS
	// 1 HORA = 3600 SEGUNDOS
	// 8 HORAS = 28800 SEGUNDOS
	// 24 HORAS = 86400 SEGUNDOS
	// 10 DIAS = 864000 SEGUNDOS

	public static final String SECRET = "SecretKeyToGenJWTs";
	//public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	//public static final long EXPIRATION_TIME = 60_000; // 1 minuto
	public static final long EXPIRATION_TIME = 28800_000; // 8 hora
	
	//public static final long EXPIRATION_TIME = 28800_000; // 8 horas
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/api/usuario/signUp/**";

}
