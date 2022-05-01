package co.tyba.tureserva.servicio;

import co.tyba.tureserva.modelo.LoginDTO;
import co.tyba.tureserva.modelo.RespuestaGenericaDTO;

public interface ILogin {
	public RespuestaGenericaDTO login(LoginDTO peticion) throws Exception;
}
