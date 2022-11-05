package les.fatec.harmonicenter.DAO;

import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientDAO implements IDAO {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public DomainEntity create(DomainEntity domainEntity) {

        Client client = ( Client ) domainEntity;
        client.setCreationDate(LocalDate.now());
        clientRepository.save(client);

        return client;
    }

    @Override
    public void delete(Long id) {

        Client client = clientRepository.findById(id).get();

        client.setId(id);
        client.setDeletedDate(LocalDateTime.now());
        client.setActive(true);

        clientRepository.save(client);
    }

    @Override
    public void update(DomainEntity domainEntity) {

        Client currentClient = ( Client ) domainEntity;
        Client previousClient;

        String confirmOldPassword = currentClient.getConfirmOldPassword();
        if(!confirmOldPassword.isEmpty() && !confirmOldPassword.equals("") && !confirmOldPassword.isBlank() && confirmOldPassword != null) {
            String password = currentClient.getPassword();

            previousClient = clientRepository.getReferenceById(currentClient.getId());

            previousClient.setPassword(password);


        } else {
            previousClient = clientRepository.getReferenceById(currentClient.getId());
            previousClient.setUpdatedDate(LocalDateTime.now());
            previousClient.setName(currentClient.getName());
            previousClient.setEmail(currentClient.getEmail());
            previousClient.setGender(currentClient.getGender());
            previousClient.setBirthDate(currentClient.getBirthDate());
            previousClient.setCpf(currentClient.getCpf());
            previousClient.setAreaCode(currentClient.getAreaCode());
            previousClient.setPhoneType(currentClient.getPhoneType());
        }

        clientRepository.save(previousClient);
    }

    @Override
    @Transactional
    public DomainEntity login(DomainEntity domainEntity) {

        Client client = ( Client ) domainEntity;

        String password = client.getPassword();

        client = clientRepository.findByEmail(client.getEmail());

        if( password.equals(client.getPassword())){
            return client;
        }

        return null;
    }

    @Override
    public DomainEntity readByID(long id) {

         return clientRepository.findById(id).get();
    }

    @Override
    public List<DomainEntity>read(DomainEntity domainEntity) {

        if( domainEntity.isGlobalSearch()){

            List<Client> result = clientRepository.findAll();
            List<DomainEntity> entities = new ArrayList<>();

            for(Client client : result) {
                entities.add(client);
            }
            return entities;
        }

        return null;
    }

}
