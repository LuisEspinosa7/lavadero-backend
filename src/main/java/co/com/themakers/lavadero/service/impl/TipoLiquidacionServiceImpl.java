/**
 * 
 */
package co.com.themakers.lavadero.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.themakers.lavadero.entity.TipoLiquidacion;
import co.com.themakers.lavadero.exception.ObjectNotFoundException;
import co.com.themakers.lavadero.exception.ValueNotPermittedException;
import co.com.themakers.lavadero.repository.TipoLiquidacionRepository;
import co.com.themakers.lavadero.service.TipoLiquidacionService;
import co.com.themakers.lavadero.web.specifications.SearchCriteria;
import co.com.themakers.lavadero.web.specifications.TipoLiquidacionSpecification;
import co.com.themakers.lavadero.web.util.Constantes;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Transactional
@Service("tipoLiquidacionService")
public class TipoLiquidacionServiceImpl implements TipoLiquidacionService {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	TipoLiquidacionRepository tipoLiquidacionRepository;
	
	
	@Override
	public void addTipoLiquidacion(TipoLiquidacion t) {
		logger.debug("==== Adicionar Tipo Liquidacion Servicio");	
		
		List<TipoLiquidacion> tipoLiquidacion = tipoLiquidacionRepository.buscarXNombreYEstado(t.getNombre());
		
		if(tipoLiquidacion.size() > 0) {
			System.out.println("Ya existe");
			throw new ValueNotPermittedException(Mensajes.EXISTENTE);
		}		
		
		t.setEstado(Constantes.ESTADO_ACTIVO);		
		tipoLiquidacionRepository.save(t);		
	}

	@Override
	public void eliminarTipoLiquidacion(long codigo) {
		logger.debug("==== Eliminar Tipo Liquidacion Servicio");
		
		boolean existe = tipoLiquidacionRepository.existsById(codigo);
		System.out.println(existe);
		
		if (existe) {
			System.out.println("EXISTE EL REGISTRO");
			tipoLiquidacionRepository.setEstadoXCodigo(Constantes.ESTADO_ELIMINADO, codigo);
		} else {
			System.out.println("NO EXISTE EL REGISTRO");
			throw new ObjectNotFoundException(Mensajes.NOT_FOUND);
		}		
	}

	@Override
	public DataTablesOutput<TipoLiquidacion> getTipoLiquidacionesDatatable(DataTablesInput input) {
		logger.debug("==== Listar Tipos de Liquidacion Datatable Servicio");
		TipoLiquidacionSpecification specification1 = new TipoLiquidacionSpecification(new SearchCriteria("estado", ":", 0));
		TipoLiquidacionSpecification specification2 = new TipoLiquidacionSpecification(new SearchCriteria("estado", ":", 1));
		return tipoLiquidacionRepository.findAll(input, Specification.where(specification1).or(specification2));
	}

	@Override
	public void cambiarEstado(long codigo, int estado) {
		logger.debug("==== cambiarEstado - Tipo Liquidacion");
		System.out.println("Codigo: " + codigo + " --- " + "Estado: " + estado);		
		tipoLiquidacionRepository.setEstadoXCodigo(estado, codigo);				
	}

	@Override
	public List<TipoLiquidacion> listarDisponibles() {
		logger.debug("==== Listar Tipos Tipos Liquidacion Disponibles Servicio");
		return tipoLiquidacionRepository.listarDisponibles(Constantes.ESTADO_ACTIVO);
	}
	
	

}
