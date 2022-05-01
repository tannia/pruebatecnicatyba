package co.tyba.tureserva.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ConexionBDDTO {
	
	private String url;
	private String usuario;
	private String contrasena;


}
