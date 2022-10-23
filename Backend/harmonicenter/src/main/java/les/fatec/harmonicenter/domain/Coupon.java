package les.fatec.harmonicenter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import les.fatec.harmonicenter.DTO.CouponDTO;
import les.fatec.harmonicenter.domain.Enum.CouponType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name= "_coupon")
@Entity
public class Coupon extends DomainEntity {

    @Column
    private String code;

    @Column
    private Integer quantity;

    @Column
    private Double coupon_value;

    @Column
    @Enumerated(EnumType.STRING)
    private CouponType type;

    @ManyToOne
    @Nullable
    @JsonIgnore
    @JoinColumn(name = "client")
    private Client client;




    public Coupon(CouponDTO dto) {

        this.code = dto.getCode();
        this.quantity = dto.getQuantity();
        this.coupon_value = dto.getCoupon_value();
        this.type = dto.getType();
        if(dto.getClient() != null){
            this.client = new Client(dto.getClient());
        }

    }

    public Coupon(Client client) {
        this.client = client;
    }

    public Coupon(Long coupon_id) {
        super(coupon_id);
    }

    public Coupon(String code){
        this.code = code;
    }

    public Coupon(String exchangeCode, Long quantity, Double couponPrice, CouponType type, Long clientID) {
        this.code = exchangeCode;
        this.quantity = Math.toIntExact(quantity);
        this.coupon_value = couponPrice;
        this.type = type;
        this.client = new Client(clientID);
    }
}