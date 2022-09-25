package les.fatec.harmonicenter.DAO;

import les.fatec.harmonicenter.domain.*;
import les.fatec.harmonicenter.repository.AddressRepository;
import les.fatec.harmonicenter.repository.CartRepository;
import les.fatec.harmonicenter.repository.ItemRepository;
import les.fatec.harmonicenter.repository.ProductRepository;
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

    @Autowired
    ProductRepository productRepository;

    @Override
    public DomainEntity create(DomainEntity domainEntity) {

        Cart cart = (Cart) domainEntity;

        return cartRepository.save(cart);

    }

    @Override
    public void update(DomainEntity domainEntity) {

        Cart cart = ( Cart ) domainEntity;

        Item item = cart.getItems().get(0);

        Long item_quantity = item.getQuantity();

        Long client_id = cart.getClient().getId();

        Cart currentCart = getCart(cart);

        item.setCart(currentCart);

        Long cart_id = currentCart.getId();
        Long product_id = item.getProduct().getId();

        Item currentItem =  itemRepository.findByCartAndProduct(cart_id, product_id);

        if(currentItem == null){
            this.setItemQuantity(item, item.getQuantity());
            itemRepository.save(item);
        }  else {
           this.setItemQuantity(currentItem, item.getQuantity());
           itemRepository.save(currentItem);
        }

        this.updateTotalValue(currentCart);

        cartRepository.save(currentCart);

    }

    private void setItemQuantity(Item item, Long quantity){
        Product product = productRepository.findById(item.getProduct().getId()).get();

        Double value = product.getPrice();

        item.setTotal_value(value * quantity);
        item.setQuantity(quantity);
    }

    private void updateTotalValue(Cart cart){
        List<Item> items = itemRepository.findAllByCart(cart);

        Double total_value = 0.0;

        for(Item item : items){
            total_value += item.getTotal_value();
        }

        cart.setTotal_value(total_value);
    }

    private Cart getCart(Cart cart){

        Long client_id = cart.getClient().getId();

        Cart currentCart = cartRepository.findByClientIdAndCurrentCartTrue(client_id);
        
        if(currentCart == null){
            this.create(cart);
            currentCart = cartRepository.findByClientIdAndCurrentCartTrue(client_id);
        }

        return currentCart;
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
        return null;

    }

    @Override
    public List<DomainEntity> read(DomainEntity domainEntity) {

        Cart cart = (Cart) domainEntity;

        Long client_id = cart.getClient().getId();

        Cart currentCart = getCart(cart);

        List<Item> items = itemRepository.findAllByCart(currentCart);

        List<DomainEntity> entities = new ArrayList<>();

        for(Item item : items ){
            entities.add(item);
        }

        return entities;

    }
}
