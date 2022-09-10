package les.fatec.harmonicenter.repository;

import les.fatec.harmonicenter.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findAllCardByClientId(Long client_id);
}
