package les.fatec.harmonicenter.strategy.Order;

import les.fatec.harmonicenter.DAO.OrderDAO;
import les.fatec.harmonicenter.domain.*;
import les.fatec.harmonicenter.domain.Enum.OrderStatus;
import les.fatec.harmonicenter.repository.*;
import les.fatec.harmonicenter.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class ValidateOrder implements IStrategy {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDAO orderDAO;


    @Override
    public String process(DomainEntity domainEntity) {

        String msg = "";
        List<String> messages = new ArrayList<>();

        Order order = (Order) domainEntity;

        OrderStatus status = order.getStatus();

        if(status != null){
            if(status == OrderStatus.EM_ANALISE){
                Order currentOrder = orderRepository.findById(order.getId()).get();

                Long address_id = null;
                Long card_id = null;
                Long cart_id = null;

                if(currentOrder.getAddress() == null) messages.add("Endereço não encontrado");
                if(currentOrder.getCard() == null) messages.add("Cartão não encontrado");


                Cart cart = currentOrder.getCart();
                if(cart != null) {
                    List<Item> items = cart.getItems();
                    if(items != null && items.size() > 0){
                        for(Item item : items){
                            Product product = item.getProduct();
                            if(product != null){
                                Long item_quantity = item.getQuantity();
                                int product_stock = product.getStock();

                                if(item_quantity <= 0 || item_quantity > product_stock) messages.add("Há itens inválidos no carrinho");
                            } else{
                                messages.add("Há itens inválidos no carrinho");
                                break;
                            }

                        }
                    } else messages.add("Não há itens no carrinho");
                }
                else messages.add("Carrinho não encontrado");



            }
        }

        if(!messages.isEmpty()) msg = String.join("\n", messages);

        return msg;
    }
}
