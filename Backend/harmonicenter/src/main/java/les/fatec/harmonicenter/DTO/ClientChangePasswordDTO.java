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
public class ClientChangePasswordDTO {

    private String confirm_old_password;

    private String new_password;

    private String confirm_new_password;

    private Long client_id;


}
