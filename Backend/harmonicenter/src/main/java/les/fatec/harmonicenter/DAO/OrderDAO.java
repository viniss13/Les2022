package les.fatec.harmonicenter.DAO;

import les.fatec.harmonicenter.domain.*;
import les.fatec.harmonicenter.domain.Enum.OrderStatus;
import les.fatec.harmonicenter.repository.CartRepository;
import les.fatec.harmonicenter.repository.CouponRepository;
import les.fatec.harmonicenter.repository.ItemRepository;
import les.fatec.harmonicenter.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDAO implements IDAO{

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CouponRepository couponRepository;

    @Override
    public DomainEntity create(DomainEntity domainEntity) {

        Order order = (Order) domainEntity;

        return orderRepository.save(order);

    }

    @Override
    public void update(DomainEntity domainEntity) {

        Order order = ( Order ) domainEntity;
        
        Address address = order.getAddress();
        Card card = order.getCard();
        Coupon coupon = order.getCoupon();
        OrderStatus status = order.getStatus();

        Order currentOrder;
        if(status != OrderStatus.DRAFT){
            currentOrder = orderRepository.findById(order.getId()).get();
            currentOrder.setStatus(status);
            currentOrder.getCart().setCurrentCart(false);
            orderRepository.save(currentOrder);
            cartRepository.save(currentOrder.getCart());
        } else {
            currentOrder =  getOrder(order);

            if(address != null) currentOrder.setAddress(address);
            if (card != null) currentOrder.setCard(card);

            if(coupon != null) {
                Coupon currentCoupon = couponRepository
                        .findByCodeAndActiveTrueAndQuantityMoreThanZero(coupon.getCode(), order.getClient().getId(), "PROMOCIONAL");
                currentOrder.setCoupon(currentCoupon);
            }
        }





        orderRepository.save(currentOrder);
    }

    private Order getOrder(Order order){

        Long client_id = order.getClient().getId();

        Order currentOrder = orderRepository.findByClientIdAndStatusDraft(client_id);
        
        if(currentOrder == null){
            Cart cart = cartRepository.findByClientIdAndCurrentCartTrue(client_id);
            if(cart != null) order.setCart(cart);

            this.create(order);
            currentOrder = orderRepository.findByClientIdAndStatusDraft(client_id);
        } else {
            Cart cart = cartRepository.findByClientIdAndCurrentCartTrue(client_id);
            if(cart != null) currentOrder.setCart(cart);
        }

        return currentOrder;
    }


    @Override
    public void delete(Long id) {

        itemRepository.deleteById(id);
    }

    @Override
    public DomainEntity login(DomainEntity domainEntity) {
        return null;
    }

    @Override
    public DomainEntity readByID(long id) {
        Order order = orderRepository.findById(id).get();
        return order;

    }

    @Override
    public List<DomainEntity> read(DomainEntity domainEntity) {

        Order order = (Order) domainEntity;

        List<DomainEntity> entities = new ArrayList<>();

        Client client = order.getClient();

        if(client != null){
            Long client_id = client.getId();

            if(order.getStatus() == OrderStatus.EM_ANALISE){
                List<Order> orders = orderRepository.findAllByClientId(client_id);

                for(Order currentOrder : orders ){
                    entities.add(currentOrder);
                }
            } else {
                Order currentOrder = orderRepository.findByClientIdAndStatusDraft(client_id);
                entities.add(currentOrder);
            }
        } else {
            List<Order> orders = orderRepository.findAll();

            for(Order currentOrder : orders ){
                entities.add(currentOrder);
            }
        }

        return entities;
    }
}
