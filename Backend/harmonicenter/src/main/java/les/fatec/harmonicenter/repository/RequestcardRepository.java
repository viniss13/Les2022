package les.fatec.harmonicenter.repository;

import les.fatec.harmonicenter.domain.Requestcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestcardRepository extends JpaRepository<Requestcard, Long>{

    List<Requestcard> findAllRequestCardByOrderId(Long id);

    @Query(nativeQuery = true, value =
                " SELECT * FROM _requestcard WHERE order_id = :orderId AND card_id = :cardId  ")
    Requestcard findByOrderIdAndCardId(@Param("orderId") Long orderId, @Param("cardId") Long cardId);

    List<Requestcard> findAllByOrderId(Long id);
}

