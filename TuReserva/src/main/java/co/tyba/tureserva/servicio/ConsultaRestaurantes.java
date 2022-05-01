package co.tyba.tureserva.servicio;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.tyba.tureserva.clientes.TomtomAPICliente;
import co.tyba.tureserva.modelo.ApiTomTomRespuesta;
import co.tyba.tureserva.modelo.ObtenerRestaurantesPeticion;

@Path("consulta/restaurantes")
public class ConsultaRestaurantes implements IConsultaRestaurantes {

	@POST()
	@Produces(MediaType.APPLICATION_JSON)
	public ApiTomTomRespuesta consultarRestaurantes(ObtenerRestaurantesPeticion peticion) throws Exception {
		
		TomtomAPICliente clienteApi = new TomtomAPICliente();
		return clienteApi.obtenerLugaresCercanos(peticion);
		
		
	}

}
