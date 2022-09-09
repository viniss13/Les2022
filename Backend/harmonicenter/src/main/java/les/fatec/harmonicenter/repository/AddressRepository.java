package les.fatec.harmonicenter.repository;

import les.fatec.harmonicenter.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface AddressRepository extends JpaRepository<Address, Long> {
}
