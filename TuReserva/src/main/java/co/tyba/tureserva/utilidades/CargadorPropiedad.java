package co.tyba.tureserva.utilidades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import co.tyba.tureserva.constantes.MsConstantes;
import co.tyba.tureserva.excepciones.ExcepcionTecnica;
import co.tyba.tureserva.modelo.CodigosError;


public class CargadorPropiedad {

	/**
	 * Referencia al archivo de propiedades.
	 */
	private Properties propiedades;
	/**
	 * Ruta de archivo de propiedades.
	 */
	private String rutaPropiedades;
	
	public static final CargadorPropiedad instance = new CargadorPropiedad();
	/**
	 * Constructor de la clase.
	 */
	public CargadorPropiedad() {
		super();
		this.rutaPropiedades = MsConstantes.RUTA_ARCHIVO_CONFIG;
	}
	
	/**
	 * Metodo que crea una instancia de las propiedaes.
	 * 
	 * @return propiedades.
	 * @throws FileNotFoundException
	 *             , si no encuentra el archivo.
	 * @throws IOException.
	 *             si hay un error con el archivo.
	 */
	private Properties getPropiedades() throws FileNotFoundException, IOException {
		if (propiedades == null) {
			propiedades = new Properties();
			propiedades.load(new FileInputStream(new File(rutaPropiedades)));
		}
		return propiedades;
	}

	/**
	 * Metodo que obtiene una propiedad del archivode propiedades.
	 * 
	 * 
	 * @param llave
	 *            , propiedad que se desea obtener.
	 * @return retorna el valor de la propiedad.
	 * @throws ExcepcionTecnica 
	 * @throws @throws
	 *             ExcepcionGenerica , si ocurre un error con el archivo.
	 */
	public String getPropiedad(String llave) throws RuntimeException, ExcepcionTecnica {
		String propiedad = MsConstantes.CADENA_VACIA;
		try {
			if (llave == null) {
				return null;
			}
			propiedad = getPropiedades().getProperty(llave);
		} catch (FileNotFoundException e) {
			throw new ExcepcionTecnica(CodigosError.ARCHIVONOEXISTE.getCodigo(),
					MessageFormat.format(CodigosError.ARCHIVONOEXISTE.getMensaje(), new Object[] { rutaPropiedades }),
					e);
		} catch (IOException e) {
			throw new ExcepcionTecnica(CodigosError.FILECONFIG, e);
		}
		return propiedad;
	}

	public static CargadorPropiedad getInstance() {
		return instance;
	}
	
	
	
}
