package les.fatec.harmonicenter.repository;

import les.fatec.harmonicenter.domain.Exchange;
import les.fatec.harmonicenter.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long>{
}

