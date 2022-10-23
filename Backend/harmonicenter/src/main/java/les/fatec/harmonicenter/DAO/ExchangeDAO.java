package les.fatec.harmonicenter.DAO;

import les.fatec.harmonicenter.domain.*;
import les.fatec.harmonicenter.domain.Enum.OrderStatus;
import les.fatec.harmonicenter.repository.CouponRepository;
import les.fatec.harmonicenter.repository.ExchangeRepository;
import les.fatec.harmonicenter.repository.OrderRepository;
import les.fatec.harmonicenter.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeDAO implements IDAO{

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public DomainEntity create(DomainEntity domainEntity) {

        Exchange exchange = (Exchange) domainEntity;
        Product product = productRepository.getReferenceById(exchange.getProduct().getId());
        exchange.setTotal_value(product.getPrice() * exchange.getQuantity());

        Order order = orderRepository.getReferenceById(exchange.getOrder().getId());

        if(order.getStatus() != OrderStatus.TROCA_PENDENTE){
            order.setStatus(OrderStatus.TROCA_PENDENTE);
            orderRepository.save(order);
        }
        exchangeRepository.save(exchange);

        return exchange;
    }

    @Override
    public void update(DomainEntity domainEntity) {
    /*
     */
    }

    @Override
    public void delete(Long id) {

        productRepository.deleteById(id);
    }

    @Override
    public DomainEntity login(DomainEntity domainEntity) {
        return null;
    }

    @Override
    public DomainEntity readByID(long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<DomainEntity> read(DomainEntity domainEntity) {

        Coupon coupon = (Coupon) domainEntity;

        Client client = coupon.getClient();

        List<DomainEntity> entities = new ArrayList<>();
        List<Coupon> coupons = new ArrayList<>();

        if(client == null){
            coupons = couponRepository.findAll();
        } else {
            Long client_id = client.getId();
            coupons = couponRepository.findAllByClientId(client_id);
        }

        for(Coupon currentCoupon : coupons ){
            entities.add(currentCoupon);
        }


        return entities;
    }
}
