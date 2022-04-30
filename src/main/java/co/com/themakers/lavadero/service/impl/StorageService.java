/**
 * 
 */
package co.com.themakers.lavadero.service.impl;

import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import co.com.themakers.lavadero.entity.TipoServicio;
import co.com.themakers.lavadero.exception.ObjectNotFoundException;
import co.com.themakers.lavadero.exception.ValueNotPermittedException;
import co.com.themakers.lavadero.repository.LavaderoRepository;
import co.com.themakers.lavadero.repository.MarcaRepository;
import co.com.themakers.lavadero.repository.TipoServicioRepository;
import co.com.themakers.lavadero.repository.TipoVehiculoRepository;
import co.com.themakers.lavadero.repository.UsuarioRepository;
import co.com.themakers.lavadero.web.util.Constantes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Luis Llanos (Developer)
 *
 */
@Service("storageService")
public class StorageService {

	/** The logger. */
	private static Logger logger = LogManager.getLogger(StorageService.class);
	
	private final Path rootLocation = Paths.get(Constantes.ROOT_LOCATION);
	private final Path usuarioFolderLocation = Paths.get(Constantes.USUARIO_LOCATION);
	private final Path tipoVehiculoFolderLocation = Paths.get(Constantes.TIPO_VEHICULO_LOCATION);
	private final Path tipoServicioFolderLocation = Paths.get(Constantes.TIPO_SERVICIO_LOCATION);
	private final Path marcaFolderLocation = Paths.get(Constantes.MARCA_LOCATION);
	private final Path lavaderoFolderLocation = Paths.get(Constantes.LAVADERO_LOCATION);
	
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	TipoVehiculoRepository tipoVehiculoRepository;
	
	@Autowired
	TipoServicioRepository tipoServicioRepository;
	
	@Autowired
	MarcaRepository marcaRepository;
	
	@Autowired
	LavaderoRepository lavaderoRepository;
	
	/**
	 * Crea o actualiza la imagen del usuario
	 * @param file
	 * @param Stringlocation
	 * @param codigoUsuario
	 */
	public void cambiarImagenUsuario(MultipartFile file, String Stringlocation, Long codigoUsuario) {

		String type = file.getContentType();
		String extension = "";

		if (type.equalsIgnoreCase("image/jpeg")) {
			extension = ".jpg";
		} else if (type.equalsIgnoreCase("image/png")) {
			extension = ".png";
		} else {
			throw new ValueNotPermittedException("No se permiten imagenes de ese tipo, solo de tipo png y jpg");
		}

		if (Stringlocation.equals(Constantes.USUARIO_LOCATION)) {

			try {

				// Eliminar todas las imagenes que tengan el nombre del id
				// enviado
				File op1 = new File(Constantes.USUARIO_LOCATION + "//" + String.valueOf(codigoUsuario) + ".jpg");

				File op2 = new File(Constantes.USUARIO_LOCATION + "//" + String.valueOf(codigoUsuario) + ".png");

				File op3 = new File(Constantes.USUARIO_LOCATION + "//" + String.valueOf(codigoUsuario) + ".JPG");

				deleteImage(op1);
				deleteImage(op2);
				deleteImage(op3);

				CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING };
				String newFileName = String.valueOf(codigoUsuario) + extension;

				Files.copy(file.getInputStream(), this.usuarioFolderLocation.resolve(newFileName), options);

				// Cambiar en la db
				usuarioRepository.setImagenXCodigo(newFileName, codigoUsuario);		

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			throw new ObjectNotFoundException("La ruta especificada no corresponde");
		}

	}
	
	
	/**
	 * Cambia la foto
	 * @param file
	 * @param Stringlocation
	 * @param codigoTipoVehiculo
	 */
	public void cambiarImagenTipoVehiculo(MultipartFile file, String Stringlocation, Long codigoTipoVehiculo) {

		String type = file.getContentType();
		String extension = "";

		if (type.equalsIgnoreCase("image/jpeg")) {
			extension = ".jpg";
		} else if (type.equalsIgnoreCase("image/png")) {
			extension = ".png";
		} else {
			throw new ValueNotPermittedException("No se permiten imagenes de ese tipo, solo de tipo png y jpg");
		}

		if (Stringlocation.equals(Constantes.TIPO_VEHICULO_LOCATION)) {

			try {

				// Eliminar todas las imagenes que tengan el nombre del id
				// enviado
				File op1 = new File(Constantes.TIPO_VEHICULO_LOCATION + "//" + String.valueOf(codigoTipoVehiculo) + ".jpg");

				File op2 = new File(Constantes.TIPO_VEHICULO_LOCATION + "//" + String.valueOf(codigoTipoVehiculo) + ".png");

				File op3 = new File(Constantes.TIPO_VEHICULO_LOCATION + "//" + String.valueOf(codigoTipoVehiculo) + ".JPG");

				deleteImage(op1);
				deleteImage(op2);
				deleteImage(op3);

				CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING };
				String newFileName = String.valueOf(codigoTipoVehiculo) + extension;

				Files.copy(file.getInputStream(), this.tipoVehiculoFolderLocation.resolve(newFileName), options);

				// Cambiar en la db
				tipoVehiculoRepository.setImagenXCodigo(newFileName, codigoTipoVehiculo);		

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			throw new ObjectNotFoundException("La ruta especificada no corresponde");
		}

	}
	
	
	
	/**
	 * Cambia la foto
	 * @param file
	 * @param Stringlocation
	 * @param codigoTipoServicio
	 */
	public void cambiarImagenTipoServicio(MultipartFile file, String Stringlocation, Long codigoTipoServicio) {

		String type = file.getContentType();
		String extension = "";

		if (type.equalsIgnoreCase("image/jpeg")) {
			extension = ".jpg";
		} else if (type.equalsIgnoreCase("image/png")) {
			extension = ".png";
		} else {
			throw new ValueNotPermittedException("No se permiten imagenes de ese tipo, solo de tipo png y jpg");
		}

		if (Stringlocation.equals(Constantes.TIPO_SERVICIO_LOCATION)) {

			try {

				// Eliminar todas las imagenes que tengan el nombre del id
				// enviado
				File op1 = new File(Constantes.TIPO_SERVICIO_LOCATION + "//" + String.valueOf(codigoTipoServicio) + ".jpg");

				File op2 = new File(Constantes.TIPO_SERVICIO_LOCATION + "//" + String.valueOf(codigoTipoServicio) + ".png");

				File op3 = new File(Constantes.TIPO_SERVICIO_LOCATION + "//" + String.valueOf(codigoTipoServicio) + ".JPG");

				deleteImage(op1);
				deleteImage(op2);
				deleteImage(op3);

				CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING };
				String newFileName = String.valueOf(codigoTipoServicio) + extension;

				Files.copy(file.getInputStream(), this.tipoServicioFolderLocation.resolve(newFileName), options);

				// Cambiar en la db
				tipoServicioRepository.setImagenXCodigo(newFileName, codigoTipoServicio);		

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			throw new ObjectNotFoundException("La ruta especificada no corresponde");
		}

	}
	
	
	/**
	 * Cambia la foto
	 * @param file
	 * @param Stringlocation
	 * @param codigoMarca
	 */
	public void cambiarImagenMarca(MultipartFile file, String Stringlocation, Long codigoMarca) {

		String type = file.getContentType();
		String extension = "";

		if (type.equalsIgnoreCase("image/jpeg")) {
			extension = ".jpg";
		} else if (type.equalsIgnoreCase("image/png")) {
			extension = ".png";
		} else {
			throw new ValueNotPermittedException("No se permiten imagenes de ese tipo, solo de tipo png y jpg");
		}

		if (Stringlocation.equals(Constantes.MARCA_LOCATION)) {

			try {

				// Eliminar todas las imagenes que tengan el nombre del id
				// enviado
				File op1 = new File(Constantes.MARCA_LOCATION + "//" + String.valueOf(codigoMarca) + ".jpg");

				File op2 = new File(Constantes.MARCA_LOCATION + "//" + String.valueOf(codigoMarca) + ".png");

				File op3 = new File(Constantes.MARCA_LOCATION + "//" + String.valueOf(codigoMarca) + ".JPG");

				deleteImage(op1);
				deleteImage(op2);
				deleteImage(op3);

				CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING };
				String newFileName = String.valueOf(codigoMarca) + extension;

				Files.copy(file.getInputStream(), this.marcaFolderLocation.resolve(newFileName), options);

				// Cambiar en la db
				marcaRepository.setImagenXCodigo(newFileName, codigoMarca);		

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			throw new ObjectNotFoundException("La ruta especificada no corresponde");
		}

	}
	
	
	
	
	/**
	 * Cambia la foto
	 * @param file
	 * @param Stringlocation
	 * @param codigoLavadero
	 */
	public void cambiarImagenLavadero(MultipartFile file, String Stringlocation, Long codigoLavadero) {

		String type = file.getContentType();
		String extension = "";

		if (type.equalsIgnoreCase("image/jpeg")) {
			extension = ".jpg";
		} else if (type.equalsIgnoreCase("image/png")) {
			extension = ".png";
		} else {
			throw new ValueNotPermittedException("No se permiten imagenes de ese tipo, solo de tipo png y jpg");
		}

		if (Stringlocation.equals(Constantes.LAVADERO_LOCATION)) {

			try {

				// Eliminar todas las imagenes que tengan el nombre del id
				// enviado
				File op1 = new File(Constantes.LAVADERO_LOCATION + "//" + String.valueOf(codigoLavadero) + ".jpg");

				File op2 = new File(Constantes.LAVADERO_LOCATION + "//" + String.valueOf(codigoLavadero) + ".png");

				File op3 = new File(Constantes.LAVADERO_LOCATION + "//" + String.valueOf(codigoLavadero) + ".JPG");

				deleteImage(op1);
				deleteImage(op2);
				deleteImage(op3);

				CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING };
				String newFileName = String.valueOf(codigoLavadero) + extension;

				Files.copy(file.getInputStream(), this.lavaderoFolderLocation.resolve(newFileName), options);

				// Cambiar en la db
				lavaderoRepository.setImagenXCodigo(newFileName, codigoLavadero);		

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			throw new ObjectNotFoundException("La ruta especificada no corresponde");
		}

	}
	
	
	
	
	/**
	 * Elimina imagenes
	 * @param file
	 */
	private void deleteImage(File file) {
		boolean success = false;

		if (file.exists()) {
			success = file.delete();
			if (success) {
				logger.debug("Se elimino = " + file.getAbsoluteFile() + " Deleted");
			} else {
				logger.debug("Algo sucedio, no elimino = " + file.getAbsoluteFile() + " Deletion failed!!!");
			}
		}

	}	
	

	/**
	 * Carga las imagenes
	 * 
	 * @param filename
	 * @param Stringlocation
	 * @return
	 */
	public Resource loadFile(String filename, String Stringlocation) {
		logger.debug("Cargando imagen Servicio ....");
		try {

			Path file = null;

			if (Stringlocation.equals(Constantes.USUARIO_LOCATION)) {
				file = usuarioFolderLocation.resolve(filename);
			}			
			else if(Stringlocation.equals(Constantes.TIPO_VEHICULO_LOCATION)) {
				file = tipoVehiculoFolderLocation.resolve(filename);
			}			
			else if(Stringlocation.equals(Constantes.TIPO_SERVICIO_LOCATION)) {
				file = tipoServicioFolderLocation.resolve(filename);
			}		
			else if(Stringlocation.equals(Constantes.MARCA_LOCATION)) {
				file = marcaFolderLocation.resolve(filename);
			}			
			else if(Stringlocation.equals(Constantes.LAVADERO_LOCATION)) {
				file = lavaderoFolderLocation.resolve(filename);
			}
			else {
				throw new ObjectNotFoundException("La ruta especificada no corresponde");
			}

			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new ObjectNotFoundException("La imagen no existe o esta en malas condiciones!");
			}
		} catch (MalformedURLException e) {
			throw new ObjectNotFoundException("Problema con la URL");
		}
	}


}
