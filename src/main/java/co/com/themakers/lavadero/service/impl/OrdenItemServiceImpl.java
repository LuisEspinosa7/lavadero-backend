/**
 * 
 */
package co.com.themakers.lavadero.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.themakers.lavadero.entity.OrdenItem;
import co.com.themakers.lavadero.repository.OrdenItemRepository;
import co.com.themakers.lavadero.service.OrdenItemService;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Transactional
@Service("ordenItemService")
public class OrdenItemServiceImpl implements OrdenItemService {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(OrdenItemServiceImpl.class);

	@Autowired
	OrdenItemRepository ordenItemRepository;

	@Override
	public void addOrdenItem(OrdenItem oi) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<OrdenItem> getOrdenItemsXOrden(long codigoOrden) {
		logger.debug("==== Listar Orden Items Datatable Servicio");
		List<OrdenItem> items = ordenItemRepository.listarOrdenItemsXOrden(codigoOrden);
		return items;
	}

}
