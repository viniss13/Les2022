package les.fatec.harmonicenter.DAO;

import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.domain.Item;
import les.fatec.harmonicenter.repository.ClientRepository;
import les.fatec.harmonicenter.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemDAO implements IDAO {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public DomainEntity create(DomainEntity domainEntity) {

        Item item = ( Item ) domainEntity;
        itemRepository.save(item);

        return item;
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public void update(DomainEntity domainEntity) {


    }

    @Override
    @Transactional
    public DomainEntity login(DomainEntity domainEntity) {



        return null;
    }

    @Override
    public DomainEntity readByID(long id) {

         return null;
    }

    @Override
    public List<DomainEntity>read(DomainEntity domainEntity) {



        return null;
    }

}
