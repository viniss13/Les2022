package les.fatec.harmonicenter.strategy.client;

import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.repository.ClientRepository;
import les.fatec.harmonicenter.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateExistingEmail implements IStrategy {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public String process(final DomainEntity domainEntity) {

        Client client = (Client) domainEntity;
        StringBuilder msg = new StringBuilder();

        if(client.getEmail() != null){
            if(clientRepository.existsByEmail(client.getEmail())){
                msg.append(" Email já cadastrado. ");
            }
        }
        return msg.toString();
    }
}
