package les.fatec.harmonicenter.strategy.client;

import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.strategy.IStrategy;

public class ValidateFieldDDD implements IStrategy {

    @Override
    public String process(DomainEntity domainEntity) {

        Client client = (Client) domainEntity;
        StringBuilder msg = new StringBuilder();

        String areaCode = client.getAreaCode();

        if(areaCode.isEmpty() || areaCode == null){
            msg.append("O campo DDD é obrigatório");
        }

        return msg.toString();
    }
}
