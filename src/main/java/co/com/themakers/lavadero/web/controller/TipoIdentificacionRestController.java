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

import co.com.themakers.lavadero.entity.TipoIdentificacion;
import co.com.themakers.lavadero.service.TipoIdentificacionService;

/**
 * @author Luis Llanos (Developer)
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/tipoIdentificacion")
public class TipoIdentificacionRestController {
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(TipoIdentificacionRestController.class);

	@Autowired
	TipoIdentificacionService tipoIdentificacionService;

	@RequestMapping(value = "/disponibles", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<TipoIdentificacion> listarTipoIdentificacionDisponibles() {
		logger.debug("======= Listando Tipo Identificacion Disponibles");
		List<TipoIdentificacion> tipoIdentificaciones = tipoIdentificacionService.listarTipoIdentificacionDisponibles();
		return tipoIdentificaciones;
	}

}
