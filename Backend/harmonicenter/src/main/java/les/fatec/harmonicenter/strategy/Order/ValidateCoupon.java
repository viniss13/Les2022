package les.fatec.harmonicenter.strategy.Order;

import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.Coupon;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.domain.Order;
import les.fatec.harmonicenter.repository.ClientRepository;
import les.fatec.harmonicenter.repository.CouponRepository;
import les.fatec.harmonicenter.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateCoupon implements IStrategy {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CouponRepository couponRepository;

    @Override
    public String process(DomainEntity domainEntity) {

        String msg = "";

        Order order = (Order) domainEntity;

        Coupon coupon = order.getCoupon();

        if(coupon != null){
            Client client = order.getClient();
            Coupon currentCoupon = couponRepository
                    .findByCodeAndActiveTrueAndQuantityMoreThanZero(coupon.getCode(), client.getId(), "PROMOCIONAL");

            if(currentCoupon == null) msg = "Cupom Inválido";
        }

        return msg;
    }
}
