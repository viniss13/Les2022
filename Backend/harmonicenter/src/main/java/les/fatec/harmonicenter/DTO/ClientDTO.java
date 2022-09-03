package les.fatec.harmonicenter.DTO;

import les.fatec.harmonicenter.domain.Enum.Gender;
import les.fatec.harmonicenter.domain.Enum.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private String name;

    private String cpf;

    private String email;

    private String password;

    @Transient
    private String confirmPassword;

    private LocalDate birthDate;

    private PhoneType type;

    private String areaCode;

    private String phoneNumber;

    private Gender gender;
}