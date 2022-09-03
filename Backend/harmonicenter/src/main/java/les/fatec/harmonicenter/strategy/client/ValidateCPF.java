package les.fatec.harmonicenter.strategy.client;

import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.strategy.IStrategy;

public class ValidateCPF implements IStrategy {

    @Override
    public String process(DomainEntity domainEntity) {

        if ( domainEntity instanceof Client){

            Client client = ( Client ) domainEntity;

            StringBuilder stringBuilder = new StringBuilder();

            if( client.getCpf().isEmpty() || client.getCpf() == "" ) stringBuilder.append(" CPF não pode ser vazio. ");

            return stringBuilder.toString();
        }

        return "";
    }
}
