/**
 * 
 */
package co.com.themakers.lavadero.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.themakers.lavadero.entity.TipoIdentificacion;
import co.com.themakers.lavadero.repository.TipoIdentificacionRepository;
import co.com.themakers.lavadero.service.TipoIdentificacionService;
import co.com.themakers.lavadero.web.util.Constantes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Service("tipoIdentificacionService")
public class TipoIdentificacionServiceImpl implements TipoIdentificacionService {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(TipoIdentificacionServiceImpl.class);

	@Autowired
	TipoIdentificacionRepository tipoIdentificacionRepository;

	@Override
	public List<TipoIdentificacion> listarTipoIdentificacionDisponibles() {
		logger.debug("==== Listar Roles Activos Servicio");
		return tipoIdentificacionRepository.findTipoIdentificacionActivos(Constantes.ESTADO_ACTIVO);
	}

}
