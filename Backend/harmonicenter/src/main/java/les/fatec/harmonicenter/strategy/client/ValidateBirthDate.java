package les.fatec.harmonicenter.strategy.client;

import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.strategy.IStrategy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidateBirthDate implements IStrategy {

    @Override
    public String process(DomainEntity domainEntity) {

        Client client = (Client) domainEntity;
        StringBuilder msg = new StringBuilder();

        if(client.getBirthDate().isAfter(LocalDate.now())){
            msg.append(" A data deve ser menor ou igual a data atual. ");
        }

        if( client.getBirthDate() == null){
            msg.append("A data não pode ser nula");
        }

        return msg.toString();
    }
}
