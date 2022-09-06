package les.fatec.harmonicenter.strategy.client;

import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.repository.ClientRepository;
import les.fatec.harmonicenter.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateExistingUser implements IStrategy {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public String process(final DomainEntity domainEntity) {

        Client client = (Client) domainEntity;
        StringBuilder msg = new StringBuilder();
        long id = client.getId();

        if(!clientRepository.existsById(id)){
                msg.append(" Usuário não existente. ");
        }

        return msg.toString();
    }
}
