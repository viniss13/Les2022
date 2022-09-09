package les.fatec.harmonicenter.strategy.Card;

import les.fatec.harmonicenter.domain.Address;
import les.fatec.harmonicenter.domain.Card;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.repository.CardRepository;
import les.fatec.harmonicenter.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerifyExistingCardID implements IStrategy {
    @Autowired
    CardRepository cardRepository;
    @Override
    public String process(DomainEntity domainEntity) {
        Card card = (Card) domainEntity;
        StringBuilder msg = new StringBuilder();
        long id = card.getId();

        if(!cardRepository.existsById(id)){
            msg.append(" Cartão não existente. ");
        }

        return msg.toString();
    }
}
