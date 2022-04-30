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

import co.com.themakers.lavadero.entity.TipoPago;
import co.com.themakers.lavadero.service.TipoPagoService;

/**
 * @author Luis Llanos (Developer)
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/tipoPago")
public class TipoPagoRestController {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(TipoPagoRestController.class);

	@Autowired
	TipoPagoService tipoPagoService;

	@RequestMapping(value = "/disponibles", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<TipoPago> listarDisponibles() {
		logger.debug("======= Listando Tipo Pago Disponibles");
		List<TipoPago> tipoPago = tipoPagoService.listarDisponibles();
		return tipoPago;
	}

}
