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

import co.com.themakers.lavadero.entity.TipoVehiculo;
import co.com.themakers.lavadero.exception.ObjectNotFoundException;
import co.com.themakers.lavadero.exception.ValueNotPermittedException;
import co.com.themakers.lavadero.repository.TipoVehiculoRepository;
import co.com.themakers.lavadero.service.TipoVehiculoService;
import co.com.themakers.lavadero.web.specifications.SearchCriteria;
import co.com.themakers.lavadero.web.specifications.TipoVehiculoSpecification;
import co.com.themakers.lavadero.web.util.Constantes;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Transactional
@Service("tipoVehiculoService")
public class TipoVehiculoServiceImpl implements TipoVehiculoService {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(UsuarioServiceImpl.class);

	@Autowired
	TipoVehiculoRepository tipoVehiculoRepository;

	@Autowired
	StorageService storageService;

	@Override
	public void addTipoVehiculo(MultipartFile file, TipoVehiculo t) {
		logger.debug("==== Adicionar Tipo Vehiculo Servicio");
		
		List<TipoVehiculo> tipoVehiculos = tipoVehiculoRepository.buscarXNombreYEstado(t.getNombre());
		
		if(tipoVehiculos.size() > 0) {
			System.out.println("Ya existe");
			throw new ValueNotPermittedException(Mensajes.EXISTENTE);
		}	
		
		t.setEstado(Constantes.ESTADO_ACTIVO);

		TipoVehiculo tipoVehiculoObject = tipoVehiculoRepository.save(t);
		storageService.cambiarImagenTipoVehiculo(file, Constantes.TIPO_VEHICULO_LOCATION,
				tipoVehiculoObject.getCodigo());
	}

	@Override
	public void updateTipoVehiculo(MultipartFile file, TipoVehiculo t) {
		logger.debug("==== Adicionar Tipo Vehiculo Servicio");

		TipoVehiculo tipoVehiculo = tipoVehiculoRepository.findById(t.getCodigo())
				.orElseThrow(() -> new ObjectNotFoundException(Mensajes.NOT_FOUND));

		tipoVehiculo.setNombre(t.getNombre());
		tipoVehiculo.setDescripcion(t.getDescripcion());

		tipoVehiculoRepository.save(tipoVehiculo);
		storageService.cambiarImagenTipoVehiculo(file, Constantes.TIPO_VEHICULO_LOCATION, tipoVehiculo.getCodigo());
	}

	@Override
	public void eliminarTipoVehiculo(long codigo) {
		logger.debug("==== Eliminar Tipo Vehiculo Servicio");

		boolean existe = tipoVehiculoRepository.existsById(codigo);
		System.out.println(existe);

		if (existe) {
			System.out.println("EXISTE EL REGISTRO");
			tipoVehiculoRepository.setEstadoXCodigo(Constantes.ESTADO_ELIMINADO, codigo);
		} else {
			System.out.println("NO EXISTE EL REGISTRO");
			throw new ObjectNotFoundException(Mensajes.NOT_FOUND);
		}

	}

	@Override
	public DataTablesOutput<TipoVehiculo> getTipoVehiculosDatatable(DataTablesInput input) {
		logger.debug("==== Listar Tipos de Vehiculo Datatable Servicio");
		TipoVehiculoSpecification specification1 = new TipoVehiculoSpecification(new SearchCriteria("estado", ":", 0));
		TipoVehiculoSpecification specification2 = new TipoVehiculoSpecification(new SearchCriteria("estado", ":", 1));
		return tipoVehiculoRepository.findAll(input, Specification.where(specification1).or(specification2));
	}

	@Override
	public void cambiarEstado(long codigo, int estado) {
		logger.debug("==== cambiarEstado - Tipo Vehiculo");
		System.out.println("Codigo: " + codigo + " --- " + "Estado: " + estado);
		tipoVehiculoRepository.setEstadoXCodigo(estado, codigo);

	}

	@Override
	public List<TipoVehiculo> listarDisponibles() {
		logger.debug("==== Listar Tipos Vehiculos Disponibles Servicio");
		return tipoVehiculoRepository.listarDisponibles(Constantes.ESTADO_ACTIVO);
	}

}
