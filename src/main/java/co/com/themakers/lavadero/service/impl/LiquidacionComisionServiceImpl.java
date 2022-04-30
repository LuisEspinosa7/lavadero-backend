/**
 * 
 */
package co.com.themakers.lavadero.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.themakers.lavadero.entity.LiquidacionComision;
import co.com.themakers.lavadero.entity.LiquidacionFuncionario;
import co.com.themakers.lavadero.entity.Orden;
import co.com.themakers.lavadero.entity.OrdenItem;
import co.com.themakers.lavadero.repository.LiquidacionComisionRepository;
import co.com.themakers.lavadero.repository.OrdenRepository;
import co.com.themakers.lavadero.service.LiquidacionComisionService;
import co.com.themakers.lavadero.web.util.Constantes;
import co.com.themakers.lavadero.web.util.CustomOperations;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Transactional
@Service("liquidacionComisionService")
public class LiquidacionComisionServiceImpl implements LiquidacionComisionService {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(LiquidacionFuncionarioServiceImpl.class);

	@Autowired
	private LiquidacionComisionRepository liquidacionComisionRepository;

	@Autowired
	private OrdenRepository ordenRepository;

	@Override
	public void liquidarComision(LiquidacionComision liquidacionComision) {
		logger.debug("==== liquidando una comision ....");

		liquidacionComision.setEstado(Constantes.ESTADO_ACTIVO);
		liquidacionComisionRepository.save(liquidacionComision);

	}

	@Override
	public LiquidacionComision calcularComision(LiquidacionComision liquidacionComision) {

		logger.debug("==== Calculando la comision service");

		List<Orden> ordenes = ordenRepository.listarOrdenesXLavaderoYRangoFechas(
				liquidacionComision.getLavaderoServicio().getLavadero().getCodigo(),
				liquidacionComision.getFechaInicio(), liquidacionComision.getFechaFin());

		logger.debug("==== Imprimiendo Ordenes ...");

		BigDecimal incremento = new BigDecimal(1);
		BigDecimal numeroServiciosPrestados = new BigDecimal(0);
		BigDecimal precioServicioActual = liquidacionComision.getLavaderoServicio().getPrecioEstandar();
		BigDecimal porcentajeComision = liquidacionComision.getLavaderoServicio().getValorComision();

		for (Orden orden : ordenes) {
			logger.debug("==== Iterando en Orden...");

			for (OrdenItem ordenItem : orden.getItems()) {
				logger.debug("==== Iterando en Orden Item ...");

				if (ordenItem.getTipoServicio().getCodigo() == liquidacionComision.getLavaderoServicio()
						.getTipoServicio().getCodigo()) {

					logger.debug("==== Se encontro una coincidencia ...");

					logger.debug("Codigo Orden: " + orden.getCodigo());
					logger.debug("Precio Orden: " + ordenItem.getPrecio());

					numeroServiciosPrestados = numeroServiciosPrestados.add(incremento);

				}

			}

		}

		logger.debug("==== Realizando calculos ......");

		BigDecimal valorRecaudadoServicios = numeroServiciosPrestados.multiply(precioServicioActual);
		BigDecimal valorParaComision = CustomOperations.percentage(valorRecaudadoServicios, porcentajeComision);

		LiquidacionComision comision = new LiquidacionComision();
		comision.setFechaInicio(liquidacionComision.getFechaInicio());
		comision.setFechaFin(liquidacionComision.getFechaFin());
		comision.setLavaderoServicio(liquidacionComision.getLavaderoServicio());
		comision.setValorLiquidacion(valorParaComision);
		comision.setNumeroServicios(numeroServiciosPrestados.intValue());

		return comision;
	}

	@Override
	public List<LiquidacionComision> buscarHistorialLiquidacionesComisiones(LiquidacionComision liquidacionComision) {
		logger.debug("==== Buscando Historial liquidaciones de comisiones service");

		List<LiquidacionComision> historial = liquidacionComisionRepository.buscarHistorialLiquidacionesComisiones(
				liquidacionComision.getLavaderoServicio().getLavadero().getCodigo(),
				liquidacionComision.getLavaderoServicio().getTipoServicio().getCodigo());
		
		
		return historial;
	}

}
