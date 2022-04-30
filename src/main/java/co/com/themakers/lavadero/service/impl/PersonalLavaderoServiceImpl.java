/**
 * 
 */
package co.com.themakers.lavadero.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.themakers.lavadero.entity.Marca;
import co.com.themakers.lavadero.entity.PersonalLavadero;
import co.com.themakers.lavadero.exception.ObjectNotFoundException;
import co.com.themakers.lavadero.exception.ValueNotPermittedException;
import co.com.themakers.lavadero.repository.PersonalLavaderoRepository;
import co.com.themakers.lavadero.service.PersonalLavaderoService;
import co.com.themakers.lavadero.web.specifications.PersonalLavaderoSpecification;
import co.com.themakers.lavadero.web.specifications.SearchCriteria;
import co.com.themakers.lavadero.web.util.Constantes;
import co.com.themakers.lavadero.web.util.Mensajes;

import org.springframework.data.jpa.domain.Specification;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Transactional
@Service("personalLavaderoService")
public class PersonalLavaderoServiceImpl implements PersonalLavaderoService {	
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(PersonalLavaderoServiceImpl.class);
	
	@Autowired
	private PersonalLavaderoRepository personalLavaderoRepository;

	@Override
	public DataTablesOutput<PersonalLavadero> getPersonalLavaderosDatatable(DataTablesInput input) {
		logger.debug("==== Listar Personal Lavadero Datatable Servicio");
		
		PersonalLavaderoSpecification specification1 = new PersonalLavaderoSpecification(new SearchCriteria("estado", ":", 0));
		PersonalLavaderoSpecification specification2 = new PersonalLavaderoSpecification(new SearchCriteria("estado", ":", 1));
		
		return personalLavaderoRepository.findAll(input, Specification.where(specification1).or(specification2));
	}

	@Override
	public void addPersonalLavadero(PersonalLavadero pl) {
		logger.debug("==== Adicionar Tipo Personal Lavadero Servicio");		
		/**
		PersonalLavadero personalLavadero = new PersonalLavadero();                         
		personalLavadero.setEstado(Constantes.ESTADO_ACTIVO);
		personalLavadero.setLavadero(pl.getLavadero());
		personalLavadero.setUsuario(pl.getUsuario());
		Example<PersonalLavadero> example = Example.of(personalLavadero);
		List<PersonalLavadero> results = personalLavaderoRepository.findAll(example);	
		**/
		
		List<PersonalLavadero> personalLavaderos = personalLavaderoRepository.buscarXUsuarioYLavaderoYestado(pl.getUsuario().getCodigo(), pl.getLavadero().getCodigo());
		
		if(personalLavaderos.size() > 0) {
			System.out.println("Ya existe");
			throw new ValueNotPermittedException(Mensajes.EXISTENTE);
		}	
		
		pl.setEstado(Constantes.ESTADO_ACTIVO);		
		personalLavaderoRepository.save(pl);			
	}

	@Override
	public void eliminarPersonalLavadero(long codigo) {
		logger.debug("==== Eliminar Personal Lavadero Servicio");
		
		boolean existe = personalLavaderoRepository.existsById(codigo);
		System.out.println(existe);
		
		if (existe) {
			System.out.println("EXISTE EL REGISTRO");
			personalLavaderoRepository.setEstadoXCodigo(Constantes.ESTADO_ELIMINADO, codigo);
		} else {
			System.out.println("NO EXISTE EL REGISTRO");
			throw new ObjectNotFoundException(Mensajes.NOT_FOUND);
		}		
		
	}

	@Override
	public void cambiarEstado(long codigo, int estado) {
		logger.debug("==== cambiarEstado - Personal Lavadero");
		System.out.println("Codigo: " + codigo + " --- " + "Estado: " + estado);		
		personalLavaderoRepository.setEstadoXCodigo(estado, codigo);			
		
	}
	
	
	

}
