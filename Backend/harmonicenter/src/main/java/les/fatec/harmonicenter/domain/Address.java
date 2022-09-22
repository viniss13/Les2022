package les.fatec.harmonicenter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import les.fatec.harmonicenter.DTO.AddressDTO;
import les.fatec.harmonicenter.DTO.AddressUpdateDTO;
import les.fatec.harmonicenter.domain.Enum.AddressType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name= "_address")
@Entity
public class Address extends DomainEntity {

    @ManyToOne(optional = true)
    @JsonIgnore// evita loop infinito
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "residencyType", nullable = false)
    private String residencyType;

    @Column(name = "observation", nullable = true)
    private String observation;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "district", nullable = false)
    private String district; //bairro

    @Column(name = "zipCode", nullable = false)
    private String zipCode; //cep

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "city")
    private String city;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "addressType", nullable = false)
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
//
//    @OneToMany(mappedBy = "address")
//    private List<Order> addressOrder;

    public Address(AddressDTO addressDTO) {

        Client client = new Client();
        client.setId(addressDTO.getClient());
        this.client = client;
//
//        this.setId(addressDTO.getId());
        this.street = addressDTO.getStreet();
        this.residencyType = addressDTO.getResidencyType();
        this.observation = addressDTO.getObservation();
        this.number = addressDTO.getNumber();
        this.district = addressDTO.getDistrict();
        this.zipCode = addressDTO.getZipCode();
        this.logradouro = addressDTO.getLogradouro();
        this.city = addressDTO.getCity();
        this.country = addressDTO.getCountry();
        this.state = addressDTO.getState();
        this.addressType = addressDTO.getAddressType();
    }


    public Address(AddressUpdateDTO addressDTO) {

        Client client = new Client();
        client.setId(addressDTO.getClient());
        this.client = client;

        this.setId(addressDTO.getId());
        this.client = client;
        this.street = addressDTO.getStreet();
        this.residencyType = addressDTO.getResidencyType();
        this.observation = addressDTO.getObservation();
        this.number = addressDTO.getNumber();
        this.district = addressDTO.getDistrict();
        this.zipCode = addressDTO.getZipCode();
        this.logradouro = addressDTO.getLogradouro();
        this.city = addressDTO.getCity();
        this.country = addressDTO.getCountry();
        this.state = addressDTO.getState();
        this.addressType = addressDTO.getAddressType();
    }

    public Address(Long id) {
        super(id);
    }

    public Address(Client client) {
        this.client = client;
    }
}