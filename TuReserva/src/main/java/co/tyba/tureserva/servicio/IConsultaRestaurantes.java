package co.tyba.tureserva.servicio;

import co.tyba.tureserva.modelo.ApiTomTomRespuesta;
import co.tyba.tureserva.modelo.ObtenerRestaurantesPeticion;

public interface IConsultaRestaurantes {

	public ApiTomTomRespuesta consultarRestaurantes(ObtenerRestaurantesPeticion peticion) throws Exception;
	
}
