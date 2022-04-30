/**
 * 
 */
package co.com.themakers.lavadero.service.impl;

import java.util.ArrayList;
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

import co.com.themakers.lavadero.entity.Lavadero;
import co.com.themakers.lavadero.entity.PersonalLavadero;
import co.com.themakers.lavadero.entity.TipoLiquidacion;
import co.com.themakers.lavadero.exception.ObjectNotFoundException;
import co.com.themakers.lavadero.exception.ValueNotPermittedException;
import co.com.themakers.lavadero.repository.LavaderoRepository;
import co.com.themakers.lavadero.repository.PersonalLavaderoRepository;
import co.com.themakers.lavadero.service.LavaderoService;
import co.com.themakers.lavadero.web.specifications.LavaderoSpecification;
import co.com.themakers.lavadero.web.specifications.SearchCriteria;
import co.com.themakers.lavadero.web.util.Constantes;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Transactional
@Service("lavaderoService")
public class LavaderoServiceImpl implements LavaderoService { 

	/** The logger. */
	private static Logger logger = LogManager.getLogger(LavaderoServiceImpl.class);

	@Autowired
	LavaderoRepository lavaderoRepository;
	
	@Autowired
	PersonalLavaderoRepository personalLavaderoRepository;
	
	@Autowired
	StorageService storageService;

	@Override
	public void addLavadero(MultipartFile file, Lavadero t) {
		logger.debug("==== Adicionar Lavadero Servicio");
		
		List<Lavadero> lavaderos = lavaderoRepository.findByNombre(t.getNombre());
		
		if(lavaderos.size() > 0) {
			System.out.println("Ya existe");
			throw new ValueNotPermittedException(Mensajes.EXISTENTE);
		}
		
		t.setEstado(Constantes.ESTADO_ACTIVO);
		
		Lavadero lavaderoObject = lavaderoRepository.save(t);
		storageService.cambiarImagenLavadero(file, Constantes.LAVADERO_LOCATION, lavaderoObject.getCodigo());	
	}

	
	@Override
	public void updateLavadero(MultipartFile file, Lavadero l) {
		logger.debug("==== Modificar Lavadero Servicio");
		
		Lavadero lavadero = lavaderoRepository.findById(l.getCodigo()).orElseThrow(() -> new ObjectNotFoundException(Mensajes.NOT_FOUND));
		
		lavadero.setNombre(l.getNombre());
		lavadero.setDescripcion(l.getDescripcion());
		lavadero.setNit(l.getNit());
		
		
		lavaderoRepository.save(lavadero);
		storageService.cambiarImagenLavadero(file, Constantes.LAVADERO_LOCATION, lavadero.getCodigo());				
	}
	
	
	@Override
	public void eliminarLavadero(long codigo) {
		logger.debug("==== Eliminar Lavadero Servicio");

		boolean existe = lavaderoRepository.existsById(codigo);
		System.out.println(existe);

		if (existe) {
			System.out.println("EXISTE EL REGISTRO");
			lavaderoRepository.setEstadoXCodigo(Constantes.ESTADO_ELIMINADO, codigo);
		} else {
			System.out.println("NO EXISTE EL REGISTRO");
			throw new ObjectNotFoundException(Mensajes.NOT_FOUND);
		}

	}

	@Override
	public DataTablesOutput<Lavadero> getLavaderosDatatable(DataTablesInput input) {
		logger.debug("==== Listar Lavadero Datatable Servicio");
		LavaderoSpecification specification1 = new LavaderoSpecification(new SearchCriteria("estado", ":", 0));
		LavaderoSpecification specification2 = new LavaderoSpecification(new SearchCriteria("estado", ":", 1));
		return lavaderoRepository.findAll(input, Specification.where(specification1).or(specification2));
	}

	@Override
	public void cambiarEstado(long codigo, int estado) {
		logger.debug("==== cambiarEstado - Lavadero");
		System.out.println("Codigo: " + codigo + " --- " + "Estado: " + estado);		
		lavaderoRepository.setEstadoXCodigo(estado, codigo);		

	}


	@Override
	public List<Lavadero> listarLavaderosDisponibles() {
		logger.debug("==== Listar Lavaderos Disponibles Servicio");
		return lavaderoRepository.listarLavaderosDisponibles(Constantes.ESTADO_ACTIVO);
	}


	@Override
	public List<Lavadero> listarLavaderosXUsuario(long codigo) {
		logger.debug("==== Listar Lavaderos asignados a un usuario Servicio");
		
		List<PersonalLavadero> vinculaciones = personalLavaderoRepository.listarVinculacionesActivasUsuario(codigo, Constantes.ESTADO_ACTIVO);
		List<Lavadero> lavaderos = new ArrayList<Lavadero>();
		
		for (PersonalLavadero vinculacion : vinculaciones) {
			lavaderos.add(vinculacion.getLavadero());
		}
		
		return lavaderos;
	}

	

}
