package les.fatec.harmonicenter.strategy.client;

import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.strategy.IStrategy;
import org.springframework.stereotype.Component;

@Component
public class ValidateFieldPhoneNumber implements IStrategy {
    @Override
    public String process(DomainEntity domainEntity) {

        Client client = (Client) domainEntity;
        StringBuilder msg = new StringBuilder();


        String phoneNumber = client.getPhoneNumber();

        if(phoneNumber.isEmpty() || phoneNumber == null){
            msg.append("O campo telefone é obrigatório");
        }

        return msg.toString();
    }
}