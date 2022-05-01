package co.tyba.tureserva.servicio;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import co.tyba.tureserva.constantes.MsConstantes;
import co.tyba.tureserva.excepciones.ExcepcionGenerica;
import co.tyba.tureserva.excepciones.ExcepcionTecnica;
import co.tyba.tureserva.modelo.LoginDTO;
import co.tyba.tureserva.modelo.RespuestaGenericaDTO;
import co.tyba.tureserva.utilidades.AdministracionBD;
import co.tyba.tureserva.utilidades.Seguridad;

@Path("login")
public class Login implements ILogin {

	/** Instancia del log4j del contexto */
	private static final Logger loggerService = LogManager.getLogger();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaGenericaDTO login(LoginDTO infoLogin) throws Exception {

		RespuestaGenericaDTO respuesta = RespuestaGenericaDTO.builder().build();
		AdministracionBD adminBd = new AdministracionBD();

		loggerService.info("Validando autenticacion para:" + infoLogin.getNombreUsuario());
		
		// Se codifica la clave en MD5 por seguridad
		String claveEncriptada = Seguridad.encriptarClave(infoLogin.getClave());
		infoLogin.setClave(claveEncriptada);

		try {
			adminBd.login(MsConstantes.RUTA_ARCHIVO_CONFIG, infoLogin);

			respuesta.setCodigoRespuesta(MsConstantes.RESPONSE_CODE_OK);
			respuesta.setMensajeRespuesta("SUCCESS");

		} catch (ExcepcionTecnica | ExcepcionGenerica e) {

			loggerService.error(e);
			respuesta.setCodigoRespuesta(MsConstantes.RESPONSE_CODE_KO);
			respuesta.setMensajeRespuesta(e.getMessage());

		}

		return respuesta;
	}
}
