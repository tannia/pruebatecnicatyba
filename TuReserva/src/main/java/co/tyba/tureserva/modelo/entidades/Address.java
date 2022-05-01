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
public class Address{
    private String streetNumber;
    private String streetName;
    private String municipalitySubdivision;
    private String municipality;
    private String countrySecondarySubdivision;
    private String countrySubdivision;
    private String countrySubdivisionName;
    private String postalCode;
    private String extendedPostalCode;
    private String countryCode;
    private String country;
    private String countryCodeISO3;
    private String freeformAddress;
    public String localName;
}
