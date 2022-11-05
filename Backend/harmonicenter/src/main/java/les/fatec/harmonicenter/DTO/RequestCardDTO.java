package les.fatec.harmonicenter.DTO;


import les.fatec.harmonicenter.domain.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCardDTO {

    private Double value;
    private Long card_id;
    private Long order_id;

}
