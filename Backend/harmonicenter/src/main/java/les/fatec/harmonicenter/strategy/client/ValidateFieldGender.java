package les.fatec.harmonicenter.strategy.client;

import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.domain.Enum.Gender;
import les.fatec.harmonicenter.strategy.IStrategy;
import org.springframework.stereotype.Component;

@Component
public class ValidateFieldGender implements IStrategy {

    @Override
    public String process(DomainEntity domainEntity) {

        Client client = (Client) domainEntity;
        StringBuilder msg = new StringBuilder();

        Gender gender = client.getGender();
        if(gender == null){
            msg.append("O campo gênero é obrigatório");
            return msg.toString();
        }

        if(gender.equals("")){
            msg.append("O campo gênero é obrigatório");
        }


//        msg.append(".");


        return msg.toString();
    }
}
