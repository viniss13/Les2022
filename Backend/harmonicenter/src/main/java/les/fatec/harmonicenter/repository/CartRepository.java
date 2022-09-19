package les.fatec.harmonicenter.repository;

import les.fatec.harmonicenter.domain.Card;
import les.fatec.harmonicenter.domain.Cart;
import les.fatec.harmonicenter.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByClientIdAndCurrentCartTrue(Long client_id);
}
