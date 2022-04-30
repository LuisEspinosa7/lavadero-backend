/**
 * 
 */
package co.com.themakers.lavadero.service.impl;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.themakers.lavadero.entity.ClienteVehiculo;
import co.com.themakers.lavadero.entity.Rol;
import co.com.themakers.lavadero.entity.TipoIdentificacion;
import co.com.themakers.lavadero.entity.Usuario;
import co.com.themakers.lavadero.exception.ObjectNotFoundException;
import co.com.themakers.lavadero.exception.ValueNotPermittedException;
import co.com.themakers.lavadero.repository.ClienteVehiculoRepository;
import co.com.themakers.lavadero.service.ClienteVehiculoService;
import co.com.themakers.lavadero.web.util.Constantes;
import co.com.themakers.lavadero.web.util.Mensajes;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Transactional
@Service("clienteVehiculoService")
public class ClienteVehiculoServiceImpl implements ClienteVehiculoService {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(ClienteVehiculoServiceImpl.class);

	@Autowired
	ClienteVehiculoRepository clienteVehiculoRepository;

	@Override
	public ClienteVehiculo buscarPorPlaca(String placa) {
		logger.debug("==== Buscar Vehiculo por placa");

		ClienteVehiculo clienteVehiculo = clienteVehiculoRepository.obtenerClienteVehiculoXPlaca(placa,
				Constantes.ESTADO_ACTIVO);

		if (clienteVehiculo == null) {
			System.out.println("NO EXISTE EL REGISTRO");
			throw new ObjectNotFoundException(Mensajes.BUSQUEDA_INCORRECTA);
		}

		return clienteVehiculo;
	}

	@Override
	public void agregarClienteVehiculo(ClienteVehiculo clienteVehiculo) {
		logger.debug("==== Agregar Usuario Cliente Servicio");

		ClienteVehiculo clienteVehiculoObj = clienteVehiculoRepository
				.obtenerClienteVehiculoXPlaca(clienteVehiculo.getPlaca(), Constantes.ESTADO_ACTIVO);

		if (clienteVehiculoObj == null) {

			clienteVehiculo.setEstado(Constantes.ESTADO_ACTIVO);
			clienteVehiculoRepository.save(clienteVehiculo);

		} else {
			System.out.println("Ya existe el vehiculo");
			throw new ValueNotPermittedException(Mensajes.EXISTENTE);
		}

	}

	@Override
	public void updateClienteVehiculo(ClienteVehiculo cv) {
		logger.debug("==== Modificar Vehiculo Servicio");

		ClienteVehiculo clienteVehiculo = clienteVehiculoRepository.findById(cv.getCodigo())
				.orElseThrow(() -> new ObjectNotFoundException(Mensajes.NOT_FOUND));
		System.out.println("Metodo cambiado. ...");

		clienteVehiculo.setUsuario(cv.getUsuario());		
		clienteVehiculo.setTipoVehiculo(cv.getTipoVehiculo());
		clienteVehiculo.setMarca(cv.getMarca());
		clienteVehiculo.setKilometraje(cv.getKilometraje());

		System.out.println("Antes de guardar el usuario editado. ...");

		clienteVehiculoRepository.save(clienteVehiculo);		
	}

}
