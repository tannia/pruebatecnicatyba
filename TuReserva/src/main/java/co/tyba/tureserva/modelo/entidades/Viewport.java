package co.tyba.tureserva.modelo.entidades;

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
public class Viewport{
    private TopLeftPoint topLeftPoint;
    private BtmRightPoint btmRightPoint;
}