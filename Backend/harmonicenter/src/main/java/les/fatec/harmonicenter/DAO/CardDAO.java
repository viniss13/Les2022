package les.fatec.harmonicenter.DAO;

import les.fatec.harmonicenter.domain.Address;
import les.fatec.harmonicenter.domain.Card;
import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.repository.AddressRepository;
import les.fatec.harmonicenter.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardDAO implements IDAO{

    @Autowired
    CardRepository cardRepository;

    @Override
    public DomainEntity create(DomainEntity domainEntity) {

        Card card = ( Card ) domainEntity;
        card.setCreationDate(LocalDate.now());
        cardRepository.save(card);

        return card;
    }

    @Override
    public void update(DomainEntity domainEntity) {
    }

    @Override
    public void delete(Long id) {

        cardRepository.deleteById(id);
    }

    @Override
    public DomainEntity login(DomainEntity domainEntity) {
        return null;
    }

    @Override
    public DomainEntity readByID(long id) {
        return cardRepository.findById(id).get();
    }

    @Override
    public List<DomainEntity> read(DomainEntity domainEntity) {

        List<Card> result = cardRepository.findAll();
        List<DomainEntity> entities = new ArrayList<>();

        for(Card card : result ){
            entities.add(card);
        }

        return entities;
    }
}
