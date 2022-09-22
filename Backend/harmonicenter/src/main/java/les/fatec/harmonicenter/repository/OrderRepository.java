package les.fatec.harmonicenter.repository;

import les.fatec.harmonicenter.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value =
            "SELECT " +
            "    * " +
            " FROM " +
            "    _ORDER " +
            "WHERE " +
            "    client = :client_id AND status = 'DRAFT' ", nativeQuery = true)
    Order findByClientIdAndStatusDraft(@Param("client_id") Long client_id);

    List<Order> findAllByClientId(Long client_id);
}
