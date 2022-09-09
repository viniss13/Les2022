package les.fatec.harmonicenter.strategy.Address;

import les.fatec.harmonicenter.domain.Address;
import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.repository.AddressRepository;
import les.fatec.harmonicenter.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerifyExistingAddressID implements IStrategy {
    @Autowired
    AddressRepository addressRepository;

    @Override
    public String process(DomainEntity domainEntity) {
        Address address = (Address) domainEntity;
        StringBuilder msg = new StringBuilder();
        long id = address.getId();

        if(!addressRepository.existsById(id)){
            msg.append(" Endereço não existente. ");
        }

        return msg.toString();
    }
}
