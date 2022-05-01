package co.tyba.tureserva.servicio;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.tyba.tureserva.constantes.MsConstantes;
import co.tyba.tureserva.excepciones.ExcepcionGenerica;
import co.tyba.tureserva.excepciones.ExcepcionTecnica;
import co.tyba.tureserva.modelo.RegistroUsuarioPeticionDTO;
import co.tyba.tureserva.modelo.RespuestaGenericaDTO;
import co.tyba.tureserva.utilidades.AdministracionBD;
import co.tyba.tureserva.utilidades.Seguridad;

@Path("registro/usuario")
public class ServicioRegistroUsuario implements IRegistroUsuario{

	@POST()
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaGenericaDTO registrarUsuario(RegistroUsuarioPeticionDTO peticion) throws Exception {
		
		AdministracionBD adminBd =  new AdministracionBD();
		try {
			String claveEncriptada = Seguridad.encriptarClave(peticion.getClave());
			peticion.setClave(claveEncriptada);
			
			adminBd.insertarUsuario(MsConstantes.RUTA_ARCHIVO_CONFIG, peticion);
			
		} catch (ExcepcionTecnica | ExcepcionGenerica e) {
			
			e.printStackTrace();
			System.out.println("ERRROR " + e);
			
			throw e;
			
		}
		
		
		return RespuestaGenericaDTO.builder().codigoRespuesta(MsConstantes.RESPONSE_CODE_OK).mensajeRespuesta(MsConstantes.RESPONSE_MSG).build();
	}

}
