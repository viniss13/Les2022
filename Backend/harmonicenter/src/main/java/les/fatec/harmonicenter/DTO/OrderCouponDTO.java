package les.fatec.harmonicenter.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCouponDTO {

    private Long client_id;
    private Long coupon_id;
}
