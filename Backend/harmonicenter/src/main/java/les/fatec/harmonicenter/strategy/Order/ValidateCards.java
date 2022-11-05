package les.fatec.harmonicenter.strategy.Order;

import les.fatec.harmonicenter.domain.*;
import les.fatec.harmonicenter.domain.Enum.CouponType;
import les.fatec.harmonicenter.domain.Enum.OrderStatus;
import les.fatec.harmonicenter.repository.OrderRepository;
import les.fatec.harmonicenter.repository.RequestcardRepository;
import les.fatec.harmonicenter.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidateCards implements IStrategy {

    @Autowired
    RequestcardRepository requestcardRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public String process(DomainEntity domainEntity) {

        Order order = (Order) domainEntity;
        List<String> listMsg = new ArrayList<>();
        String msg = "";

        OrderStatus status = order.getStatus();

        if(status == OrderStatus.EM_ANALISE){

            Order currentOrder = orderRepository.getReferenceById(order.getId());

            List<Requestcard> requestCards = requestcardRepository.findAllRequestCardByOrderId(order.getId());

            Double order_value = currentOrder.getCart().getTotal_value();

            Double cards_value = 0.0;

            if(currentOrder.getCoupon() != null){
                order_value -= currentOrder.getCoupon().getCoupon_value();
            }

            Double remnant = order_value % 10;

            boolean alreadyRemnant = false;

            if(order_value < 10.0 && order_value > 0){
                if(requestCards.size() > 1){
                    listMsg.add("Compras abaixo de 10 reais apenas permitem um cartão de crédito");
                }
            }

            if(order_value <= 0){
                if(requestCards.size() > 0){
                    listMsg.add("Não é possível selecionar nenhum cartão de crédito nessa compra");
                }
            }

            for(int i = 0; i < requestCards.size(); i++){
                Double buyingValue = requestCards.get(i).getBuyingValue();

                if(buyingValue <= 0) listMsg.add("Valor  de cartão de crédito inválido");

                if(buyingValue > 0 && buyingValue < 10.0){
                    if(order_value >= 10.0) listMsg.add("Valor  de cartão de crédito inválido");
                }

                cards_value += buyingValue;

            }

            if(!cards_value.equals(order_value) && order_value > 0) listMsg.add("Valor de pagamento menor ou maior que o do Pedido");
        }

        if(!listMsg.isEmpty()) msg = String.join(" \n ", listMsg);

        return msg;
    }
}
