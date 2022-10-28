package les.fatec.harmonicenter.strategy.client;

import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.repository.ClientRepository;
import les.fatec.harmonicenter.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateChangePassword implements IStrategy {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public String process(DomainEntity domainEntity) {

        String msg = "";

        Client client = (Client) domainEntity;

        String confirmOldPassword = client.getConfirmOldPassword();
        String password = client.getPassword();
        String confirmNewPassword = client.getConfirmNewPassword();

        if(!confirmOldPassword.isEmpty() && !confirmOldPassword.equals("") && !confirmOldPassword.isBlank() && confirmOldPassword != null){
            client = clientRepository.findById(client.getId()).get();

            if(!client.getPassword().equals(confirmOldPassword)){
                msg = "Senha antiga inválida";
            }else if(!password.equals(confirmNewPassword)){
                msg = "As senhas devem ser iguais";
            }
        }

        return msg;
    }
}
