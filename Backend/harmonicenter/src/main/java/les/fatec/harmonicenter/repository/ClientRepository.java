package les.fatec.harmonicenter.repository;

import les.fatec.harmonicenter.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByEmail(String email);
}
