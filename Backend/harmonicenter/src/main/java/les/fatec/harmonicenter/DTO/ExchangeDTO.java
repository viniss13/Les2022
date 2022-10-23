package les.fatec.harmonicenter.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeDTO {

    private Long product_id;
    private Long quantity;
    private Long order_id;
    private Double total_value;

}
