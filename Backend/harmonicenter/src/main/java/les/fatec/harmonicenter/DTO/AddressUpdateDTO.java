package les.fatec.harmonicenter.DTO;

import les.fatec.harmonicenter.domain.Address;
import les.fatec.harmonicenter.domain.Enum.AddressType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressUpdateDTO {

    private Long client;
    private Long id;

    private String street;
    private String residencyType;
    private String observation;
    private String number;
    private String district;
    private String zipCode;
    private String logradouro;
    private String city;
    private String country;
    private String state;
    private AddressType addressType;

    public AddressUpdateDTO(Address address) {

        this.client = address.getClient().getId();
        this.street = address.getStreet();
        this.residencyType = address.getResidencyType();
        this.observation = address.getObservation();
        this.number = address.getNumber();
        this.district = address.getDistrict();
        this.zipCode = address.getZipCode();
        this.logradouro = address.getLogradouro();
        this.city = address.getCity();
        this.country = address.getCountry();
        this.state = address.getState();
    }
}