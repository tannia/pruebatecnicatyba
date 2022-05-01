package co.tyba.tureserva.modelo;

public enum CodigosError {

	GENERICO("0001", "Error desconocido."), ARCHIVONOEXISTE("0003", "El archivo no se encuentra el la ruta: \"{0}\"."),
	FILECONFIG("0002", "Ha ocurrido un error en la lectura del archivo de configuracion."),
	CONFIGVALORNULL("0012", "El valor del parametro {0} del archivo de configuracion/base de datos es nulo.");

	private String codigo;
	private String mensaje;

	/**
	 * Constructor con todos los parámetros
	 * 
	 * @param _codigo
	 * @param _mensaje
	 */
	private CodigosError(String _codigo, String _mensaje) {
		this.codigo = _codigo;
		this.mensaje = _mensaje;
	}

	/**
	 * Función que retorna el código del error
	 * 
	 * @return String
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Función que retorna el mensaje del error
	 * 
	 * @return String
	 */
	public String getMensaje() {
		return mensaje;
	}

}
