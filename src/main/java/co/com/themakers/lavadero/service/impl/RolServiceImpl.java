/**
 * 
 */
package co.com.themakers.lavadero.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.themakers.lavadero.entity.Rol;
import co.com.themakers.lavadero.repository.RolRepository;
import co.com.themakers.lavadero.service.RolService;
import co.com.themakers.lavadero.web.util.Constantes;


/**
 * @author Luis Llanos (Developer)
 *
 */
@Service("rolService")
public class RolServiceImpl implements RolService {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(RolServiceImpl.class);

	@Autowired
	RolRepository rolRepository;

	@Override
	public List<Rol> listarRolesDisponibles() {
		logger.debug("==== Listar Roles Activos Servicio");
		return rolRepository.findRolesActivos(Constantes.ESTADO_ACTIVO);
	}

}
