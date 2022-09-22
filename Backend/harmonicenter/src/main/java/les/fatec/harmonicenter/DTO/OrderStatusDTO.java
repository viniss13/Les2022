package les.fatec.harmonicenter.DTO;

import les.fatec.harmonicenter.domain.Enum.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusDTO {

    private Long id;
    private OrderStatus status;
}
