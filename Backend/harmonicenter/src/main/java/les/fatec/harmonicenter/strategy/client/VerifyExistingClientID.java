package les.fatec.harmonicenter.strategy.client;

import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.repository.ClientRepository;
import les.fatec.harmonicenter.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerifyExistingClientID implements IStrategy {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public String process(DomainEntity domainEntity) {

        Client client = (Client) domainEntity;

        StringBuilder msg = new StringBuilder();
        long id = client.getId();

        if(!clientRepository.existsById(id)){
            msg.append(" Cliente não existente. ");
        }

        return msg.toString();
    }
}
