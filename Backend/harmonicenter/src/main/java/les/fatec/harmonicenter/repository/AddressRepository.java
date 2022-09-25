package les.fatec.harmonicenter.repository;

import les.fatec.harmonicenter.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllAddressByClientId(Long id);

    Address findByIdAndActiveTrue(Long address_id);
}
