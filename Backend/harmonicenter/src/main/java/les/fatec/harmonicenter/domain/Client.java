package les.fatec.harmonicenter.domain;

import com.sun.istack.NotNull;
import les.fatec.harmonicenter.DTO.ClientDTO;
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

    public Client(ClientDTO clientDTO) {
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
}
