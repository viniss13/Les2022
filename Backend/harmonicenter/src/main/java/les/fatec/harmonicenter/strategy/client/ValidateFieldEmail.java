package les.fatec.harmonicenter.strategy.client;

import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.strategy.IStrategy;
import org.springframework.stereotype.Component;

@Component
public class ValidateFieldEmail implements IStrategy {

    @Override
    public String process(DomainEntity domainEntity) {

        Client client = (Client) domainEntity;
        StringBuilder msg = new StringBuilder();

        String email = client.getEmail();

        if(email.isEmpty() || email == null){
            msg.append("O campo email é obrigatório");
        }

        return msg.toString();
    }
}