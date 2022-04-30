/**
 * 
 */
package co.com.themakers.lavadero.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.themakers.lavadero.entity.OrdenItem;
import co.com.themakers.lavadero.model.Respuesta;
import co.com.themakers.lavadero.service.OrdenItemService;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/ordenItem")
public class OrdenItemRestController {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(OrdenItemRestController.class);

	@Autowired
	OrdenItemService ordenItemService;

	@PreAuthorize("hasAuthority('ROLE_OPERARIO')")
	@RequestMapping(value = "/orden/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<Respuesta> getItemsXOrdenDatatable(@PathVariable("codigo") long codigo) {
		logger.debug("Listando LOS ITEMS de la orden para datatable Controlador ....");

		List<OrdenItem> items = ordenItemService.getOrdenItemsXOrden(codigo);

		Respuesta respuesta = new Respuesta();
		respuesta.setExito(true);
		respuesta.setCode(200);
		respuesta.setMensaje(Mensajes.ADICION_CORRECTA);
		respuesta.setData(items);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

}
