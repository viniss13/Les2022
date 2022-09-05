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
public class ClientLoginDTO {

    private String email;
    private String password;

}
