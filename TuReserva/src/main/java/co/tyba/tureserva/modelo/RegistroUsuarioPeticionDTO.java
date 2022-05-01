/**
 * 
 */
package co.tyba.tureserva.modelo;

import jakarta.validation.constraints.NotNull;
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
public class RegistroUsuarioPeticionDTO {

	@NotNull
	private String nombreUsuario;
	@NotNull
	private String clave;
	@NotNull
	private String rol;
	
}
