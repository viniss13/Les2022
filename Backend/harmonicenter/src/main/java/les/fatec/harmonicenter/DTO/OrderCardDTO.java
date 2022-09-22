package les.fatec.harmonicenter.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCardDTO {

    private Long client_id;
    private Long card_id;
}
