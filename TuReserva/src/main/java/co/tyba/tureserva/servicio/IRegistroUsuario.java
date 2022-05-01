package co.tyba.tureserva.servicio;

import co.tyba.tureserva.modelo.RegistroUsuarioPeticionDTO;
import co.tyba.tureserva.modelo.RespuestaGenericaDTO;

public interface IRegistroUsuario {
	
	public RespuestaGenericaDTO registrarUsuario(RegistroUsuarioPeticionDTO peticion) throws Exception;

}
