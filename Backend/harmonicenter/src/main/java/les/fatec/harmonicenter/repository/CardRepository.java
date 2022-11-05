package les.fatec.harmonicenter.repository;

import les.fatec.harmonicenter.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface CardRepository extends JpaRepository<Card, Long> {

    @Transactional
    List<Card> findAllCardByClientId(Long client_id);

    Card findByIdAndActiveTrue(Long address_id);
}
