package co.tyba.tureserva.modelo.entidades;

import java.util.ArrayList;

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
public class Result{
    public String type;
    public String id;
    public double score;
    public double dist;
    public String info;
    public Poi poi;
    public Address address;
    public Position position;
    public Viewport viewport;
    public ArrayList<EntryPoint> entryPoints;
    public DataSources dataSources;
}
