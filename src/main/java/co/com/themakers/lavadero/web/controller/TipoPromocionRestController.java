/**
 * 
 */
package co.com.themakers.lavadero.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.themakers.lavadero.entity.TipoPromocion;
import co.com.themakers.lavadero.service.TipoPromocionService;

/**
 * @author Luis Llanos (Developer)
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/tipoPromocion")
public class TipoPromocionRestController {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(TipoPromocionRestController.class);

	@Autowired
	TipoPromocionService tipoPromocionService;

	@RequestMapping(value = "/disponibles", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<TipoPromocion> listarDisponibles() {
		logger.debug("======= Listando Tipo Promocion Disponibles");
		List<TipoPromocion> tipoPromocion = tipoPromocionService.listarDisponibles();
		return tipoPromocion;
	}

}
