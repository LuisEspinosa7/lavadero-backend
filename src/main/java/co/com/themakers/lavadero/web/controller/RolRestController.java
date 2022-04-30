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

import co.com.themakers.lavadero.entity.Rol;
import co.com.themakers.lavadero.service.RolService;



/**
 * @author Luis Llanos (Developer)
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/rol")
public class RolRestController {
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(RolRestController.class);


	@Autowired
	RolService rolService;

	@RequestMapping(value = "/disponibles/admin", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Rol> listarRolesDisponibles() {
		logger.debug("======= Listando Roles Disponibles");
		List<Rol> roles = rolService.listarRolesDisponibles();
		return roles;
	}
	

}
