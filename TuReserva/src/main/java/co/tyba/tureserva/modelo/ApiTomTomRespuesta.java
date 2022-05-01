package co.tyba.tureserva.modelo;

import java.util.List;

import co.tyba.tureserva.modelo.entidades.Result;
import co.tyba.tureserva.modelo.entidades.Summary;
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
public class ApiTomTomRespuesta {

    private Summary summary;
    private List<Result> results;
    
}
