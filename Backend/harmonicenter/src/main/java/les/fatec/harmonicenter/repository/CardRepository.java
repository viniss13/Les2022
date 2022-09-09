package les.fatec.harmonicenter.repository;

import les.fatec.harmonicenter.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CardRepository extends JpaRepository<Card, Long> {
}
