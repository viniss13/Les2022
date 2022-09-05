package les.fatec.harmonicenter.DTO;

import les.fatec.harmonicenter.domain.Enum.Gender;
import les.fatec.harmonicenter.domain.Enum.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientUpdateDTO {

    private Long client;

    private String password;

    private String name;

    private String cpf;

    private String email;

    private LocalDate birthDate;

    private PhoneType type;

    private String areaCode;

    private String phoneNumber;

    private Gender gender;
}
