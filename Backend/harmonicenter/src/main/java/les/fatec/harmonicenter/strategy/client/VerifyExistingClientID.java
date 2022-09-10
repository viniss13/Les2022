package les.fatec.harmonicenter.strategy.client;

import les.fatec.harmonicenter.domain.Address;
import les.fatec.harmonicenter.domain.Card;
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

        long id = -1;


        if(domainEntity instanceof Client){
            Client client = (Client) domainEntity;
            id = client.getId();
        } else if (domainEntity instanceof Address){
            Address address = (Address) domainEntity;
            id = address.getClient().getId();
        } else if (domainEntity instanceof Card){
            Card card = (Card) domainEntity;
            id = card.getClient().getId();
        }

        StringBuilder msg = new StringBuilder();

        if(!clientRepository.existsById(id)){
            msg.append(" Cliente não existente. ");
        }

        return msg.toString();
    }
}
