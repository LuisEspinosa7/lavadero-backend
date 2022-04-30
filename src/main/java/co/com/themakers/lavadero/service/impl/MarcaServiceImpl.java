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

import co.com.themakers.lavadero.entity.Marca;
import co.com.themakers.lavadero.entity.TipoVehiculo;
import co.com.themakers.lavadero.exception.ObjectNotFoundException;
import co.com.themakers.lavadero.exception.ValueNotPermittedException;
import co.com.themakers.lavadero.repository.MarcaRepository;
import co.com.themakers.lavadero.service.MarcaService;
import co.com.themakers.lavadero.web.specifications.MarcaSpecification;
import co.com.themakers.lavadero.web.specifications.SearchCriteria;
import co.com.themakers.lavadero.web.util.Constantes;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Transactional
@Service("marcaService")
public class MarcaServiceImpl implements MarcaService {
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(MarcaServiceImpl.class);
	
	@Autowired
	MarcaRepository marcaRepository;
	
	@Autowired
	StorageService storageService;

	@Override
	public void addMarca(MultipartFile file, Marca t) {
		logger.debug("==== Adicionar Marca Servicio");	
		
		List<Marca> marcas = marcaRepository.buscarXNombreYEstado(t.getNombre());
		
		if(marcas.size() > 0) {
			System.out.println("Ya existe");
			throw new ValueNotPermittedException(Mensajes.EXISTENTE);
		}	
		
		
		t.setEstado(Constantes.ESTADO_ACTIVO);		
		
		Marca marcaObject = marcaRepository.save(t);
		storageService.cambiarImagenMarca(file, Constantes.MARCA_LOCATION, marcaObject.getCodigo());	
	}

	
	@Override
	public void updateMarca(MultipartFile file, Marca m) {
		logger.debug("==== Adicionar Marca Servicio");
		
		Marca marca = marcaRepository.findById(m.getCodigo()).orElseThrow(() -> new ObjectNotFoundException(Mensajes.NOT_FOUND));
		
		marca.setNombre(m.getNombre());	
		
		marcaRepository.save(marca);
		storageService.cambiarImagenMarca(file, Constantes.MARCA_LOCATION, marca.getCodigo());		
	}
	
	
	@Override
	public void eliminarMarca(long codigo) {
		logger.debug("==== Eliminar Marca Servicio");
		
		boolean existe = marcaRepository.existsById(codigo);
		System.out.println(existe);
		
		if (existe) {
			System.out.println("EXISTE EL REGISTRO");
			marcaRepository.setEstadoXCodigo(Constantes.ESTADO_ELIMINADO, codigo);
		} else {
			System.out.println("NO EXISTE EL REGISTRO");
			throw new ObjectNotFoundException(Mensajes.NOT_FOUND);
		}		
		
	}

	@Override
	public DataTablesOutput<Marca> getMarcasDatatable(DataTablesInput input) {
		logger.debug("==== Listar Marca Datatable Servicio");
		MarcaSpecification specification1 = new MarcaSpecification(new SearchCriteria("estado", ":", 0));
		MarcaSpecification specification2 = new MarcaSpecification(new SearchCriteria("estado", ":", 1));
		return marcaRepository.findAll(input, Specification.where(specification1).or(specification2));
	}

	@Override
	public void cambiarEstado(long codigo, int estado) {
		logger.debug("==== cambiarEstado - Marca");
		System.out.println("Codigo: " + codigo + " --- " + "Estado: " + estado);		
		marcaRepository.setEstadoXCodigo(estado, codigo);		
	}


	@Override
	public List<Marca> listarDisponibles() {
		logger.debug("==== Listar Marcas Disponibles Servicio");
		return marcaRepository.listarDisponibles(Constantes.ESTADO_ACTIVO);
	}

	

	

}
