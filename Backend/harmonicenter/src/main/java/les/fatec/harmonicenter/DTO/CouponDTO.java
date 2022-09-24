package les.fatec.harmonicenter.DTO;


import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.Coupon;
import les.fatec.harmonicenter.domain.Enum.CouponType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponDTO {

    private String code;

    private Integer quantity;

    private Double coupon_value;

    private CouponType type;

    private Long client;

    public CouponDTO(Coupon coupon){
        this.code = coupon.getCode();
        this.quantity = coupon.getQuantity();
        this.coupon_value = coupon.getCoupon_value();
        this.type = coupon.getType();

        if(client != null){
            this.client = coupon.getClient().getId();
        }
    }
}
