package les.fatec.harmonicenter.repository;

import les.fatec.harmonicenter.domain.Cart;
import les.fatec.harmonicenter.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(nativeQuery = true, value =
    " SELECT \n" +
            "    *\n" +
            "FROM \n" +
            "   _ITEM\n" +
            "WHERE \n" +
            "   PRODUCT = :product_id AND CART = :cart_id ")
    Item findByCartAndProduct(@Param("cart_id") Long cart_id,
                              @Param("product_id") Long product_id);

    List<Item> findAllByCart(Cart cart);
}
