package les.fatec.harmonicenter.domain;

import com.sun.istack.NotNull;
import les.fatec.harmonicenter.DTO.ClientLoginDTO;
import les.fatec.harmonicenter.DTO.ClientSaveDTO;
import les.fatec.harmonicenter.DTO.ClientUpdateDTO;
import les.fatec.harmonicenter.domain.Enum.Gender;
import les.fatec.harmonicenter.domain.Enum.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name= "_client")
@Entity
public class Client extends DomainEntity{

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "PhoneType")
    @Enumerated( EnumType.STRING)
    private PhoneType phoneType;

    @Column(name = "areaCode")//DDD
    private String areaCode;

    @Column( name = "phoneNumber")
    private String phoneNumber;

    @Column( name = "gender")
    @Enumerated( EnumType.STRING)
    private Gender gender;

    public Client(ClientSaveDTO clientDTO) {
        this.name = clientDTO.getName();
        this.cpf = clientDTO.getCpf();
        this.email = clientDTO.getEmail();
        this.password = clientDTO.getPassword();
        this.birthDate = clientDTO.getBirthDate();
        this.phoneType = clientDTO.getType();
        this.areaCode = clientDTO.getAreaCode();
        this.phoneNumber = clientDTO.getPhoneNumber();
        this.gender = clientDTO.getGender();
    }

    public Client(ClientUpdateDTO clientUpdateDTO) {

        Client client = new Client();
        client.setId(clientUpdateDTO.getClient());

        this.setId(client.getId());
        this.name = clientUpdateDTO.getName();
        this.cpf = clientUpdateDTO.getCpf();
        this.email = clientUpdateDTO.getEmail();
        this.birthDate = clientUpdateDTO.getBirthDate();
        this.phoneType = clientUpdateDTO.getType();
        this.areaCode = clientUpdateDTO.getAreaCode();
        this.phoneNumber = clientUpdateDTO.getPhoneNumber();
        this.gender = clientUpdateDTO.getGender();
    }

    public Client(ClientLoginDTO clientDTO) {

        this.email = clientDTO.getEmail();
        this.password = clientDTO.getPassword();

    }
}
