/**
 * 
 */
package co.com.themakers.lavadero.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.themakers.lavadero.entity.TipoPago;
import co.com.themakers.lavadero.repository.TipoPagoRepository;
import co.com.themakers.lavadero.service.TipoPagoService;
import co.com.themakers.lavadero.web.util.Constantes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Service("tipoPagoService")
public class TipoPagoServiceImpl implements TipoPagoService {
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(TipoPagoServiceImpl.class);

	@Autowired
	TipoPagoRepository tipoPagoRepository;

	@Override
	public List<TipoPago> listarDisponibles() {
		logger.debug("==== Listar Tipos pago Activos Servicio");
		return tipoPagoRepository.findTipoPagosActivos(Constantes.ESTADO_ACTIVO);
	}
	
	

}
