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
import org.springframework.web.multipart.MultipartFile;

import co.com.themakers.lavadero.entity.TipoLiquidacion;
import co.com.themakers.lavadero.entity.TipoServicio;
import co.com.themakers.lavadero.exception.ObjectNotFoundException;
import co.com.themakers.lavadero.exception.ValueNotPermittedException;
import co.com.themakers.lavadero.repository.TipoServicioRepository;
import co.com.themakers.lavadero.service.TipoServicioService;
import co.com.themakers.lavadero.web.specifications.SearchCriteria;
import co.com.themakers.lavadero.web.specifications.TipoServicioSpecification;
import co.com.themakers.lavadero.web.util.Constantes;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Transactional
@Service("tipoServicioService")
public class TipoServicioServiceImpl implements TipoServicioService {
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(TipoServicioServiceImpl.class);
	
	@Autowired
	TipoServicioRepository tipoServicioRepository;
	
	@Autowired
	StorageService storageService;

	@Override
	public void addTipoServicio(MultipartFile file, TipoServicio t) {
		logger.debug("==== Adicionar Tipo Servicio Servicio");		
		
		List<TipoServicio> tipoServicios = tipoServicioRepository.buscarXNombreYEstado(t.getNombre());
		
		if(tipoServicios.size() > 0) {
			System.out.println("Ya existe");
			throw new ValueNotPermittedException(Mensajes.EXISTENTE);
		}	
		
		
		t.setEstado(Constantes.ESTADO_ACTIVO);
		
		TipoServicio tipoServicioObject = tipoServicioRepository.save(t);
		storageService.cambiarImagenTipoServicio(file, Constantes.TIPO_SERVICIO_LOCATION, tipoServicioObject.getCodigo());					
	}
	
	
	@Override
	public void updateTipoServicio(MultipartFile file, TipoServicio t) {
		logger.debug("==== Adicionar Tipo Servicio Servicio");
		
		TipoServicio tipoServicio = tipoServicioRepository.findById(t.getCodigo()).orElseThrow(() -> new ObjectNotFoundException(Mensajes.NOT_FOUND));
		
		tipoServicio.setNombre(t.getNombre());
		tipoServicio.setDescripcion(t.getDescripcion());	
		
		tipoServicioRepository.save(tipoServicio);
		storageService.cambiarImagenTipoServicio(file, Constantes.TIPO_SERVICIO_LOCATION, tipoServicio.getCodigo());		
	}
	

	@Override
	public void eliminarTipoServicio(long codigo) {
		logger.debug("==== Eliminar Tipo Servicio Servicio");
		
		boolean existe = tipoServicioRepository.existsById(codigo);
		System.out.println(existe);
		
		if (existe) {
			System.out.println("EXISTE EL REGISTRO");
			tipoServicioRepository.setEstadoXCodigo(Constantes.ESTADO_ELIMINADO, codigo);
		} else {
			System.out.println("NO EXISTE EL REGISTRO");
			throw new ObjectNotFoundException(Mensajes.NOT_FOUND);
		}		
		
	}

	@Override
	public DataTablesOutput<TipoServicio> getTipoServiciosDatatable(DataTablesInput input) {
		logger.debug("==== Listar Tipos de Vehiculo Datatable Servicio");
		TipoServicioSpecification specification1 = new TipoServicioSpecification(new SearchCriteria("estado", ":", 0));
		TipoServicioSpecification specification2 = new TipoServicioSpecification(new SearchCriteria("estado", ":", 1));
		return tipoServicioRepository.findAll(input, Specification.where(specification1).or(specification2));
	}

	@Override
	public void cambiarEstado(long codigo, int estado) {
		logger.debug("==== cambiarEstado - Tipo Vehiculo");
		System.out.println("Codigo: " + codigo + " --- " + "Estado: " + estado);		
		tipoServicioRepository.setEstadoXCodigo(estado, codigo);			
	}


	@Override
	public List<TipoServicio> listarDisponibles() {
		logger.debug("==== Listar Tipos Servicios Disponibles Servicio");
		return tipoServicioRepository.listarDisponibles(Constantes.ESTADO_ACTIVO);
	}


	@Override
	public List<TipoServicio> listarTiposServiciosDisponiblesXLavadero(long codigoLavadero) {
		logger.debug("==== Listar Tipos Servicios X Lavadero Disponibles Servicio");
		return tipoServicioRepository.buscarTiposServiciosXLavadero(codigoLavadero);
	}


	@Override
	public List<TipoServicio> listarTiposServiciosAplicanComisionDisponiblesXLavadero(long codigoLavadero) {
		logger.debug("==== Listar Tipos Servicios Aplican Comision X Lavadero Disponibles Servicio");
		return tipoServicioRepository.buscarTiposServiciosAplicanComisionXLavadero(codigoLavadero);
	}

	
	
	

}
