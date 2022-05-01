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
public class Poi{
    private String name;
    private String phone;
    private ArrayList<CategorySet> categorySet;
    private String url;
    private ArrayList<String> categories;
    private ArrayList<Classification> classifications;
}
