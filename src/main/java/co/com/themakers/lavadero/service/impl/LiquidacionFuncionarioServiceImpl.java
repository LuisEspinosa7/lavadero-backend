/**
 * 
 */
package co.com.themakers.lavadero.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import co.com.themakers.lavadero.entity.LiquidacionFuncionario;
import co.com.themakers.lavadero.entity.Orden;
import co.com.themakers.lavadero.entity.OrdenItem;
import co.com.themakers.lavadero.repository.FuncionarioServicioRepository;
import co.com.themakers.lavadero.repository.LiquidacionFuncionarioRepository;
import co.com.themakers.lavadero.repository.OrdenRepository;
import co.com.themakers.lavadero.service.LiquidacionFuncionarioService;
import co.com.themakers.lavadero.web.util.Constantes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Transactional
@Service("liquidacionFuncionarioService")
public class LiquidacionFuncionarioServiceImpl implements LiquidacionFuncionarioService {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(LiquidacionFuncionarioServiceImpl.class);

	@Autowired
	private LiquidacionFuncionarioRepository liquidacionFuncionarioRepository;

	@Autowired
	private OrdenRepository ordenRepository;

	@Autowired
	private FuncionarioServicioRepository funcionarioServicioRepository;

	@Override
	public void liquidarFuncionarioTecnico(LiquidacionFuncionario liquidacionFuncionario) {
		logger.debug("==== liquidando a un funcionario ....");

		liquidacionFuncionario.setEstado(Constantes.ESTADO_ACTIVO);
		liquidacionFuncionarioRepository.save(liquidacionFuncionario);
	}

	@Override
	public List<LiquidacionFuncionario> buscarRegistrosLiquidablesDisponibles(
			LiquidacionFuncionario liquidacionFuncionario) {

		logger.debug("==== Buscando Registros liquidables para funcionarios service");

		List<LiquidacionFuncionario> registrosLiquidables = new ArrayList<LiquidacionFuncionario>();

		List<Orden> ordenes = ordenRepository.listarOrdenesXLavaderoYRangoFechas(
				liquidacionFuncionario.getLavadero().getCodigo(), liquidacionFuncionario.getFechaInicio(),
				liquidacionFuncionario.getFechaFin());

		logger.debug("==== Imprimiendo Ordenes ...");

		// Dictionary<Long, BigDecimal> d = new Hashtable<Long, BigDecimal>();
		Map<Long, BigDecimal> d = new HashMap<Long, BigDecimal>();
		
		Map<Long, Integer> counter = new HashMap<Long, Integer>();
		
		
		BigDecimal valorServicioActual = null;

		for (Orden orden : ordenes) {
			logger.debug("==== Iterando en Orden...");

			for (OrdenItem ordenItem : orden.getItems()) {
				logger.debug("==== Iterando en Orden Item ...");

				for (ItemFuncionario itemFuncionario : ordenItem.getItemFuncionarios()) {
					logger.debug("==== Iterando en Item Funcionarios ...");

					if (itemFuncionario.getFuncionarioServicio().getTipoServicio().getCodigo() == liquidacionFuncionario
							.getFuncionarioServicio().getTipoServicio().getCodigo()
							&& itemFuncionario.getFuncionarioServicio().getTipoLiquidacion()
									.getCodigo() == liquidacionFuncionario.getFuncionarioServicio().getTipoLiquidacion()
											.getCodigo()) {

						logger.debug("==== Se encontro una coincidencia ...");

						logger.debug("Codigo Orden: " + orden.getCodigo());
						logger.debug("Precio Orden: " + ordenItem.getPrecio());
						valorServicioActual = ordenItem.getPrecio();

						Long codigo = itemFuncionario.getFuncionarioServicio().getCodigo();
						logger.debug("==== Codigo Funcionario Servicio: " + codigo);

						if (d.containsKey(codigo)) {
							logger.debug("==== Se suma al codigo: " + codigo + " - La suma de: "
									+ itemFuncionario.getPagoFuncionario());
							BigDecimal val = (BigDecimal) d.get(codigo);
							d.put(codigo, val.add(itemFuncionario.getPagoFuncionario()));
						} else {
							logger.debug("==== Nuevo codigo: " + codigo + " - Se inicia en: "
									+ itemFuncionario.getPagoFuncionario());
							d.put(codigo, itemFuncionario.getPagoFuncionario());
						}
						
						
						//Contador de Servicios
						if (counter.containsKey(codigo)) {
							logger.debug("==== Se suma al codigo: " + codigo + " - Contador + 1 ");
							Integer val2 = (Integer) counter.get(codigo);
							counter.put(codigo, val2 + 1);
						} else {
							logger.debug("==== Nuevo codigo: " + codigo + " - Se inicia en 1");
							counter.put(codigo, 1);
						}						
						
						

					}

				}

			}

		}

		logger.debug("==== Imprimiendo el diccionario");

		for (Long key : d.keySet()) {
			logger.debug("==== Codigo de Funcionario Servicio: " + key + " - Suma Total Ganada: " + d.get(key));

			FuncionarioServicio funcionarioServi = funcionarioServicioRepository.findByCodigo(key);

			LiquidacionFuncionario liquidacionFun = new LiquidacionFuncionario();
			liquidacionFun.setFechaInicio(liquidacionFuncionario.getFechaInicio());
			liquidacionFun.setFechaFin(liquidacionFuncionario.getFechaFin());
			liquidacionFun.setFuncionarioServicio(funcionarioServi);
			liquidacionFun.setValorPago(d.get(key));
			liquidacionFun.setLavadero(liquidacionFuncionario.getLavadero());
			liquidacionFun.setValorServicio(valorServicioActual);
			liquidacionFun.setNumeroServicios(counter.get(key));

			registrosLiquidables.add(liquidacionFun);

		}
		
		
		List<LiquidacionFuncionario> existentes = liquidacionFuncionarioRepository.buscarHistorialLiquidacionesTecnicos(
						liquidacionFuncionario.getLavadero().getCodigo(),
						liquidacionFuncionario.getFuncionarioServicio().getTipoServicio().getCodigo(),
						liquidacionFuncionario.getFuncionarioServicio().getTipoLiquidacion().getCodigo(),
						liquidacionFuncionario.getFechaInicio(), liquidacionFuncionario.getFechaFin());
		
		
		// Eliminar las liquidaciones que ya se realizaron
		
		logger.debug("==== Eliminando las que ya se realizaron ...");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		List<LiquidacionFuncionario> liquidacionesDisponibles = new ArrayList<LiquidacionFuncionario>();
		
		for (LiquidacionFuncionario registroLiq1 : registrosLiquidables) {		
			
			logger.debug("==== Iterar  Dentro de 1...");
			
			//boolean existe = false;
			
			if(existentes.size() > 0) {
				logger.debug("Si hay historial, se procede a validar");
				
				for (int i = 0; i < existentes.size(); i++) {
					
					logger.debug("==== Iterando dentro de 2 ...");
					
					logger.debug("Imprimiendo Propiedades -----");
					
					logger.debug("Lavaderos: " + registroLiq1.getLavadero().getCodigo() + " - " + existentes.get(i).getLavadero().getCodigo());
					logger.debug("Servicio: " + registroLiq1.getFuncionarioServicio().getTipoServicio().getCodigo() + " - " + existentes.get(i).getFuncionarioServicio().getTipoServicio().getCodigo());
					logger.debug("Tipo Liquidacion: " + registroLiq1.getFuncionarioServicio().getTipoLiquidacion().getCodigo() + " - " + existentes.get(i).getFuncionarioServicio().getTipoLiquidacion().getCodigo());
					logger.debug("Fecha Inicio: " + formatter.format(registroLiq1.getFechaInicio()) + " - " + formatter.format(existentes.get(i).getFechaInicio()));
					logger.debug("Fecha Fin: " + formatter.format(registroLiq1.getFechaFin()) + " - " + formatter.format(existentes.get(i).getFechaFin()));
					
					
					//Buscar si ya existe esa liquidacion en el historial con esos parametros
					if(registroLiq1.getLavadero().getCodigo() == existentes.get(i).getLavadero().getCodigo() &&
							registroLiq1.getFuncionarioServicio().getTipoServicio().getCodigo() == existentes.get(i).getFuncionarioServicio().getTipoServicio().getCodigo() &&
							registroLiq1.getFuncionarioServicio().getTipoLiquidacion().getCodigo() == existentes.get(i).getFuncionarioServicio().getTipoLiquidacion().getCodigo() &&
							formatter.format(registroLiq1.getFechaInicio()).equalsIgnoreCase(formatter.format(existentes.get(i).getFechaInicio())) &&
							formatter.format(registroLiq1.getFechaFin()).equalsIgnoreCase(formatter.format(existentes.get(i).getFechaFin()))) {
						
						logger.debug("==== Ya esta el registro liquidable por eso se elimina ...");
						logger.debug("Eliminando el registro liquidable");
						
						//Se debe sacar de la lista el registro liquidable .... PENDIENTE NO TENGO INTERNET
						//registrosLiquidables.remove(i);
						//existe = true;
					} else {
						logger.debug("==== No esta, entonces se deja dentro de los registros liquidables..");
						liquidacionesDisponibles.add(registroLiq1);
					}
					
				}				
				
				
			} else {
				logger.debug("No hay historial");
				liquidacionesDisponibles = registrosLiquidables;
			}
			
					
		}		

		return liquidacionesDisponibles;

	}

	@Override
	public List<LiquidacionFuncionario> buscarHistorialLiquidacionesTecnicos(
			LiquidacionFuncionario liquidacionFuncionario) {
		logger.debug("==== Buscando Historial liquidaciones para funcionarios service");

		List<LiquidacionFuncionario> historial = liquidacionFuncionarioRepository.buscarHistorialLiquidacionesTecnicos(
				liquidacionFuncionario.getLavadero().getCodigo(),
				liquidacionFuncionario.getFuncionarioServicio().getTipoServicio().getCodigo(),
				liquidacionFuncionario.getFuncionarioServicio().getTipoLiquidacion().getCodigo(),
				liquidacionFuncionario.getFechaInicio(), liquidacionFuncionario.getFechaFin());
		
		
		return historial;

	}

	@Override
	public List<LiquidacionFuncionario> buscarReporteLiquidacionesTecnicosParaPropietario(
			LiquidacionFuncionario liquidacionFuncionario) {
		logger.debug("==== Buscando Reporte liquidaciones de funcionarios para propietario service");

		List<LiquidacionFuncionario> reporte = liquidacionFuncionarioRepository.buscarReporteLiquidacionesTecnicosParaPropietario(
				liquidacionFuncionario.getLavadero().getCodigo(),
				liquidacionFuncionario.getFechaInicio(), 
				liquidacionFuncionario.getFechaFin());
		
		
		return reporte;
	}

}
