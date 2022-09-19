package les.fatec.harmonicenter.DAO;

import les.fatec.harmonicenter.domain.Address;
import les.fatec.harmonicenter.domain.Cart;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.domain.Item;
import les.fatec.harmonicenter.repository.AddressRepository;
import les.fatec.harmonicenter.repository.CartRepository;
import les.fatec.harmonicenter.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartDAO implements IDAO{

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public DomainEntity create(DomainEntity domainEntity) {

        Cart cart = (Cart) domainEntity;

        return cartRepository.save(cart);

    }

    @Override
    public void update(DomainEntity domainEntity) {

        Cart cart = ( Cart ) domainEntity;

        Item item = cart.getItems().get(0);

        Long client_id = cart.getClient().getId();

        Cart currentCart = cartRepository.findByClientIdAndCurrentCartTrue(client_id);
        if(currentCart == null){
            this.create(cart);
            currentCart = cartRepository.findByClientIdAndCurrentCartTrue(client_id);
        }

        item.setCart(currentCart);

        Long cart_id = currentCart.getId();
        Long product_id = item.getProduct().getId();

        Item currentItem =  itemRepository.findByCartAndProduct(cart_id, product_id);

        if(currentItem == null){
            itemRepository.save(item);
        }  else {
           currentItem.setQuantity(item.getQuantity());

           itemRepository.save(currentItem);
        }


    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public DomainEntity login(DomainEntity domainEntity) {
        return null;
    }

    @Override
    public DomainEntity readByID(long id) {
        return null;

    }

    @Override
    public List<DomainEntity> read(DomainEntity domainEntity) {

        return null;

    }
}
