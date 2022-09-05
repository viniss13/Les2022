package les.fatec.harmonicenter.strategy.client;


import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.strategy.IStrategy;
import org.springframework.stereotype.Component;

@Component
public class ValidatePasswordNull implements IStrategy {


    @Override
    public String process(DomainEntity domainEntity) {
        if (domainEntity instanceof Client) {
            Client client = (Client) domainEntity;

            StringBuilder stringBuilder = new StringBuilder();

            if( client.getPassword() == null || client.getPassword().isEmpty() ) stringBuilder.append(" A senha não pode ser vazia. ");

            return stringBuilder.toString();
        }

        return "";
    }
}