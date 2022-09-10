package les.fatec.harmonicenter.strategy.Card;

import les.fatec.harmonicenter.domain.Card;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.strategy.IStrategy;
import les.fatec.harmonicenter.strategy.client.VerifyExistingClientID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class ValidateCardFields implements IStrategy {

    @Autowired
    VerifyExistingClientID veClientID;

    @Override
    public String process(DomainEntity domainEntity) {
        Card card = (Card) domainEntity;

        String msg = "";

        List<String> listMsg = new ArrayList<>();

        String number = card.getNumber();
        String holder = card.getHolder();
        String expirationDateMonth = card.getExpirationDateMonth();
        String expirationDateYear = card.getExpirationDateYear();
        String security = card.getSecurity();
        String holderCpf = card.getHolderCpf();
        String flag = card.getFlag();
        long clientID = card.getClient().getId();

        if(number.isEmpty() || number.isBlank() || number.equals(null)) listMsg.add("Número obrigatório");

        if(holder.isEmpty() || holder.isBlank() || holder.equals(null)) listMsg.add("Proprietário obrigatório");

        if(expirationDateMonth.isEmpty() || expirationDateMonth.isBlank() || expirationDateMonth.equals(null)) listMsg.add("Mês de vencimento obrigatório");

        if(expirationDateYear.isEmpty() || expirationDateYear.isBlank() || expirationDateYear.equals(null)) listMsg.add("Ano de vencimento obrigatório");

        if(security.isEmpty() || security.isBlank() || security.equals(null)) listMsg.add("Código de segurança obrigatório");

        if(holderCpf.isEmpty() || holderCpf.isBlank() || holderCpf.equals(null)) listMsg.add("CPF obrigatório");

        if(flag.isEmpty() || flag.isBlank() || flag.equals(null)) listMsg.add("Bandeira obrigatória");

       // String existingClientMessage = veClientID.process(card.getClient());

       // if(!existingClientMessage.isEmpty()) listMsg.add(existingClientMessage);

        if(!listMsg.isEmpty()) msg = String.join(" \n ", listMsg);

        return msg;
    }
}
