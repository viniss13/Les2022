package les.fatec.harmonicenter.strategy.client;

import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.repository.ClientRepository;
import les.fatec.harmonicenter.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateAuthentication implements IStrategy {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public String process(DomainEntity domainEntity) {

        String msg = "";

        Client client = (Client) domainEntity;

        String email = client.getEmail();
        String password = client.getPassword();

        client = clientRepository.findByEmail(email);

        if(client == null) msg = "Email ou senha inválidos";

        else if(!password.equals(client.getPassword())) msg = "Email ou senha inválidos";

        return msg;
    }
}
