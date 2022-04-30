/**
 * 
 */
package co.com.themakers.lavadero.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.themakers.lavadero.entity.LavaderoServicio;
import co.com.themakers.lavadero.entity.PersonalLavadero;
import co.com.themakers.lavadero.entity.Rol;
import co.com.themakers.lavadero.entity.TipoIdentificacion;
import co.com.themakers.lavadero.entity.Usuario;
import co.com.themakers.lavadero.exception.ObjectNotFoundException;
import co.com.themakers.lavadero.exception.ValueNotPermittedException;
import co.com.themakers.lavadero.repository.LavaderoServicioRepository;
import co.com.themakers.lavadero.service.LavaderoServicioService;
import co.com.themakers.lavadero.web.specifications.LavaderoServicioSpecification;
import co.com.themakers.lavadero.web.specifications.SearchCriteria;
import co.com.themakers.lavadero.web.util.Constantes;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Transactional
@Service("lavaderoServicioService")
public class LavaderoServicioServiceImpl implements LavaderoServicioService {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(LavaderoServicioServiceImpl.class);

	@Autowired
	private LavaderoServicioRepository lavaderoServicioRepository;

	@Override
	public void addLavaderoServicio(LavaderoServicio ls) {
		logger.debug("==== Adicionar Lavadero Servicio service");
		
		
		List<LavaderoServicio> registros = lavaderoServicioRepository.buscarXLavaderoYTipoServicioYestado(ls.getLavadero().getCodigo(), ls.getTipoServicio().getCodigo());
		
		if(registros.size() > 0) {
			System.out.println("Ya existe");
			throw new ValueNotPermittedException(Mensajes.EXISTENTE);
		}	
		
		ls.setEstado(Constantes.ESTADO_ACTIVO);
		lavaderoServicioRepository.save(ls);
	}

	@Override
	public DataTablesOutput<LavaderoServicio> getLavaderoServiciosDatatable(DataTablesInput input) {
		logger.debug("==== Listar Servicios del Lavadero Datatable Servicio");

		LavaderoServicioSpecification specification1 = new LavaderoServicioSpecification(
				new SearchCriteria("estado", ":", 0));
		LavaderoServicioSpecification specification2 = new LavaderoServicioSpecification(
				new SearchCriteria("estado", ":", 1));

		return lavaderoServicioRepository.findAll(input, Specification.where(specification1).or(specification2));
	}

	@Override
	public void eliminarLavaderoServicio(long codigo) {
		logger.debug("==== Eliminar Personal Lavadero Servicio");

		boolean existe = lavaderoServicioRepository.existsById(codigo);
		System.out.println(existe);

		if (existe) {
			System.out.println("EXISTE EL REGISTRO");
			lavaderoServicioRepository.setEstadoXCodigo(Constantes.ESTADO_ELIMINADO, codigo);
		} else {
			System.out.println("NO EXISTE EL REGISTRO");
			throw new ObjectNotFoundException(Mensajes.NOT_FOUND);
		}

	}

	@Override
	public void cambiarEstado(long codigo, int estado) {
		logger.debug("==== cambiarEstado - Lavadero Servicio service");
		System.out.println("Codigo: " + codigo + " --- " + "Estado: " + estado);
		lavaderoServicioRepository.setEstadoXCodigo(estado, codigo);
	}

	@Override
	public void updateLavaderoServicio(LavaderoServicio ls) {
		logger.debug("==== Modificar Lavadero Servicio Servicio");
		
		LavaderoServicio lavaderoServicio = lavaderoServicioRepository.findById(ls.getCodigo()).orElseThrow(() -> new ObjectNotFoundException(Mensajes.NOT_FOUND));
		System.out.println("Metodo cambiado. ...");
		
		lavaderoServicio.setLavadero(ls.getLavadero());
		lavaderoServicio.setTipoServicio(ls.getTipoServicio());
		lavaderoServicio.setTipoPromocion(ls.getTipoPromocion());
		
		lavaderoServicio.setAplicaComision(ls.getAplicaComision());
		lavaderoServicio.setValorComision(ls.getValorComision());
		
		lavaderoServicio.setAplicaPromocion(ls.getAplicaPromocion());
		lavaderoServicio.setPromocionNumeroRef(ls.getPromocionNumeroRef());
		lavaderoServicio.setValorPromocion(ls.getValorPromocion());
		
		lavaderoServicio.setPrecioEstandar(ls.getPrecioEstandar());
		
		System.out.println("Antes de guardar editado. ...");
		lavaderoServicioRepository.save(lavaderoServicio);
	}

	@Override
	public List<LavaderoServicio> buscarXLavaderoYTipoServicioDisponible(long codigoLavadero, long codigoTipoServicio) {
		logger.debug("==== Listando el lavadero servicio Servicio");
		List<LavaderoServicio> registros = lavaderoServicioRepository.buscarXLavaderoYTipoServicioDisponible(codigoLavadero, codigoTipoServicio);
		
		if(registros.size() == 0) {
			System.out.println("No tiene configuracion");
			throw new ObjectNotFoundException(Mensajes.NOT_FOUND);
		}
		
		return registros;
	}

}
