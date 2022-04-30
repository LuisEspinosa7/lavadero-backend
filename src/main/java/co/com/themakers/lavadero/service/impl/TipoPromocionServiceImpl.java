/**
 * 
 */
package co.com.themakers.lavadero.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.themakers.lavadero.entity.TipoPromocion;
import co.com.themakers.lavadero.repository.TipoPromocionRepository;
import co.com.themakers.lavadero.service.TipoPromocionService;
import co.com.themakers.lavadero.web.util.Constantes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Service("tipoPromocionService")
public class TipoPromocionServiceImpl implements TipoPromocionService {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(TipoPromocionServiceImpl.class);

	@Autowired
	TipoPromocionRepository tipoPromocionRepository;

	@Override
	public List<TipoPromocion> listarDisponibles() {
		logger.debug("==== Listar Tipos promocion Activos Servicio");
		return tipoPromocionRepository.findTipoPromocionActivos(Constantes.ESTADO_ACTIVO);
	}

}
