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

import co.com.themakers.lavadero.entity.FuncionarioServicio;
import co.com.themakers.lavadero.entity.Rol;
import co.com.themakers.lavadero.entity.Usuario;
import co.com.themakers.lavadero.exception.ObjectNotFoundException;
import co.com.themakers.lavadero.exception.ValueNotPermittedException;
import co.com.themakers.lavadero.repository.FuncionarioServicioRepository;
import co.com.themakers.lavadero.repository.PersonalLavaderoRepository;
import co.com.themakers.lavadero.service.FuncionarioServicioService;
import co.com.themakers.lavadero.web.specifications.FuncionarioServicioSpecification;
import co.com.themakers.lavadero.web.specifications.SearchCriteria;
import co.com.themakers.lavadero.web.util.Constantes;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Transactional
@Service("funcionarioServicioService")
public class FuncionarioServicioServiceImpl implements FuncionarioServicioService {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(FuncionarioServicioServiceImpl.class);

	@Autowired
	private FuncionarioServicioRepository funcionarioServicioRepository;

	@Autowired
	private PersonalLavaderoRepository personalLavaderoRepository;

	@Override
	public void addFuncionarioServicio(FuncionarioServicio fs) {
		logger.debug("==== Adicionar  Funcionario Servicio Servicio");

		List<FuncionarioServicio> registros = funcionarioServicioRepository
				.buscarXUsuarioYTipoServicioYestado(fs.getUsuario().getCodigo(), fs.getTipoServicio().getCodigo());

		if (registros.size() > 0) {
			System.out.println("Ya existe");
			throw new ValueNotPermittedException(Mensajes.EXISTENTE);
		}

		fs.setEstado(Constantes.ESTADO_ACTIVO);
		funcionarioServicioRepository.save(fs);
	}

	@Override
	public DataTablesOutput<FuncionarioServicio> getFuncionarioServiciosDatatable(DataTablesInput input) {
		logger.debug("==== Listar Funcionario Servicio Datatable Servicio");

		FuncionarioServicioSpecification specification1 = new FuncionarioServicioSpecification(
				new SearchCriteria("estado", ":", 0));
		FuncionarioServicioSpecification specification2 = new FuncionarioServicioSpecification(
				new SearchCriteria("estado", ":", 1));

		return funcionarioServicioRepository.findAll(input, Specification.where(specification1).or(specification2));
	}

	@Override
	public void eliminarFuncionarioServicio(long codigo) {
		logger.debug("==== Eliminar Funcionario Servicio Servicio");

		boolean existe = funcionarioServicioRepository.existsById(codigo);
		System.out.println(existe);

		if (existe) {
			System.out.println("EXISTE EL REGISTRO");
			funcionarioServicioRepository.setEstadoXCodigo(Constantes.ESTADO_ELIMINADO, codigo);
		} else {
			System.out.println("NO EXISTE EL REGISTRO");
			throw new ObjectNotFoundException(Mensajes.NOT_FOUND);
		}

	}

	@Override
	public void cambiarEstado(long codigo, int estado) {
		logger.debug("==== cambiarEstado - Funcionario Servicio");
		System.out.println("Codigo: " + codigo + " --- " + "Estado: " + estado);
		funcionarioServicioRepository.setEstadoXCodigo(estado, codigo);
	}

	@Override
	public void updateFuncionarioServicio(FuncionarioServicio fs) {
		logger.debug("==== Modificar funcionario Servicio Servicio");

		FuncionarioServicio funcionarioServicio = funcionarioServicioRepository.findById(fs.getCodigo())
				.orElseThrow(() -> new ObjectNotFoundException(Mensajes.NOT_FOUND));
		System.out.println("Metodo cambiado. ...");

		funcionarioServicio.setUsuario(fs.getUsuario());
		funcionarioServicio.setTipoServicio(fs.getTipoServicio());
		funcionarioServicio.setTipoLiquidacion(fs.getTipoLiquidacion());
		funcionarioServicio.setTipoPago(fs.getTipoPago());
		funcionarioServicio.setValorPago(fs.getValorPago());

		System.out.println("Antes de guardar editado. ...");
		funcionarioServicioRepository.save(funcionarioServicio);

	}

	@Override
	public List<FuncionarioServicio> buscarPersonalTecnicoXLavaderoDisponible(long lavaderoCodigo,
			long tipoServicioCodigo) {
		logger.debug("==== Listando personal tecnico disponible por lavadero y tipo servicio");

		List<Usuario> personal = personalLavaderoRepository.buscarPersonalXLavaderoDisponible(lavaderoCodigo);

		if (personal.size() == 0) {
			System.out.println("El lavadero no tiene personal");
			throw new ObjectNotFoundException(Mensajes.LAVADERO_SIN_PERSONAL);
		}

		List<Long> codigosUsuariosTecnicos = new ArrayList<Long>();
		boolean ingresado = false;

		logger.debug("==== Sacando los codigos de usuarios tecnicos");
		for (Usuario usuario : personal) {
			logger.debug("==== Dentro del usuario: " + usuario.getNombre1());
			ingresado = false;

			for (Rol rol : usuario.getRoles()) {
				logger.debug("==== Dentro del rol: " + rol.getNombre());

				if (rol.getCodigo() == Constantes.CODIGO_ROL_TECNICO) {
					codigosUsuariosTecnicos.add(usuario.getCodigo());
					ingresado = true;
				}

				if (ingresado) {
					break;
				}

			}

		}

		List<FuncionarioServicio> configuracionesFuncionariosTecnicos = funcionarioServicioRepository
				.buscarConfiguracionPersonalTecnicoDisponible(codigosUsuariosTecnicos, tipoServicioCodigo);

		if (configuracionesFuncionariosTecnicos.size() == 0) {
			System.out.println("No existen tecnicos configurados");
			throw new ObjectNotFoundException(Mensajes.LAVADERO_SIN_TECNICOS);
		}

		return configuracionesFuncionariosTecnicos;
	}

}
