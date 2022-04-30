/**
 * 
 */
package co.com.themakers.lavadero.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.themakers.lavadero.entity.FuncionarioServicio;
import co.com.themakers.lavadero.entity.ItemFuncionario;
import co.com.themakers.lavadero.entity.Orden;
import co.com.themakers.lavadero.entity.OrdenItem;
import co.com.themakers.lavadero.entity.PromocionAplicada;
import co.com.themakers.lavadero.repository.ItemFuncionarioRepository;
import co.com.themakers.lavadero.repository.OrdenItemRepository;
import co.com.themakers.lavadero.repository.OrdenRepository;
import co.com.themakers.lavadero.repository.PromocionAplicadaRepository;
import co.com.themakers.lavadero.service.OrdenService;
import co.com.themakers.lavadero.web.util.Constantes;
import co.com.themakers.lavadero.web.util.CustomOperations;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Transactional
@Service("ordenService")
public class OrdenServiceImpl implements OrdenService {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(OrdenServiceImpl.class);

	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

	@Autowired
	OrdenRepository ordenRepository;
	
	@Autowired
	OrdenItemRepository ordenItemRepository;
	
	@Autowired
	ItemFuncionarioRepository itemFuncionarioRepository;
	
	@Autowired
	PromocionAplicadaRepository promocionAplicadaRepository;

	@Override
	public void addOrden(Orden o) { 
		logger.debug("==== Adicionar orden Servicio");

		o.setEstado(Constantes.ESTADO_ACTIVO);

		for (OrdenItem ordenItem : o.getItems()) {
			logger.debug("==== Iterando Orden Items");
			ordenItem.setEstado(Constantes.ESTADO_ACTIVO);
			ordenItem.setOrden(o);

			BigDecimal precioItem = ordenItem.getPrecio();

			for (ItemFuncionario itemFuncionario : ordenItem.getItemFuncionarios()) {
				itemFuncionario.setEstado(Constantes.ESTADO_ACTIVO);
				itemFuncionario.setOrdenItem(ordenItem);

				FuncionarioServicio funcionarioServicio = itemFuncionario.getFuncionarioServicio();
				BigDecimal valorPagoConfiguracion = funcionarioServicio.getValorPago();
				BigDecimal valorAPagar = null;

				if (funcionarioServicio.getTipoPago().getCodigo() == Constantes.CODIGO_TIPO_PAGO_PRECIO_FIJO) {
					valorAPagar = valorPagoConfiguracion;
				} else if (funcionarioServicio.getTipoPago().getCodigo() == Constantes.CODIGO_TIPO_PAGO_PORCENTAJE) {
					valorAPagar = CustomOperations.percentage(precioItem, valorPagoConfiguracion);
				}

				itemFuncionario.setPagoFuncionario(valorAPagar);
			}

		}

		Orden ordenGuardada = ordenRepository.save(o);
		
		//Se itera para guardar las aplicaciones de las promociones		
		

		for (OrdenItem ordenIt : ordenGuardada.getItems()) {
			logger.debug("==== Iterando en Orden Item ...");			
				
			if(ordenIt.getAplicaPromocion() == 1) {					
				// Se guarda el promociones aplicadas
				PromocionAplicada promocion = new PromocionAplicada();
				promocion.setClienteVehiculo(ordenGuardada.getClienteVehiculo());
				promocion.setFechaCreacion(ordenGuardada.getFechaCreacion());
				promocion.setLavadero(ordenGuardada.getLavadero());
				promocion.setOrdenItem(ordenIt);
				
				promocionAplicadaRepository.save(promocion);
			}				

		}	

	}

	@Override
	public List<Orden> getOrdenesXLavadero(long codigoLavadero) {
		logger.debug("==== Listar Ordenes Datatable Servicio");	
		
		List<Orden> ordenes = ordenRepository.listarOrdenesXLavadero(codigoLavadero);
		return ordenes;
	}

	@Override
	public int consultarNumeroServiciosConsumidosCliente(long codigoLavadero, long codigoUsuario,
			long codigoTipoServicio) {
		logger.debug("==== Consultando numero de servicios consumidos por el cliente");
		
		List<PromocionAplicada> promocionesAplicadas = promocionAplicadaRepository.listarPromocionesAplicadasDESC(codigoLavadero, codigoUsuario, codigoTipoServicio);
		
		PromocionAplicada ultimaVez = null;
		Date ultimaFechaAplicacionPromocion = null;
		
		if(promocionesAplicadas.size() == 0) {
			ultimaFechaAplicacionPromocion = new GregorianCalendar(2019, Calendar.APRIL, 11).getTime();
		} else {
			ultimaVez = promocionesAplicadas.get(0);
			ultimaFechaAplicacionPromocion = ultimaVez.getFechaCreacion();
		}
				 
				
		List<Orden> ordenes = ordenRepository.listarOrdenesXLavaderoXClienteYFechaInicial(codigoLavadero, codigoUsuario, ultimaFechaAplicacionPromocion);
				
		int contador = 0;		
		
		for (Orden orden : ordenes) {
			logger.debug("==== Iterando en Orden...");

			for (OrdenItem ordenItem : orden.getItems()) {
				logger.debug("==== Iterando en Orden Item ...");			
				
				if(ordenItem.getTipoServicio().getCodigo() == codigoTipoServicio) {					
					contador = contador + 1;					
				}				

			}

		}
		
		return contador;
	}
	
	
	
	

}
