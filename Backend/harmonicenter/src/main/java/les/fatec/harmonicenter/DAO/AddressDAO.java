package les.fatec.harmonicenter.DAO;

import les.fatec.harmonicenter.domain.Address;
import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressDAO implements IDAO{

    @Autowired
    AddressRepository addressRepository;

    @Override
    public DomainEntity create(DomainEntity domainEntity) {

        Address address = ( Address ) domainEntity;
        address.setCreationDate(LocalDate.now());
        addressRepository.save(address);

        return address;
    }

    @Override
    public void update(DomainEntity domainEntity) {

        Address currentAddress = ( Address ) domainEntity;
        Address previousAddress = addressRepository.getReferenceById(currentAddress.getId());

        previousAddress.setUpdatedDate(LocalDateTime.now());
        previousAddress.setStreet(currentAddress.getStreet());
        previousAddress.setResidencyType(currentAddress.getResidencyType());
        previousAddress.setObservation(currentAddress.getObservation());
        previousAddress.setNumber(currentAddress.getNumber());
        previousAddress.setDistrict(currentAddress.getDistrict());
        previousAddress.setZipCode(currentAddress.getZipCode());
        previousAddress.setLogradouro(currentAddress.getLogradouro());
        previousAddress.setCity(currentAddress.getCity());
        previousAddress.setCountry(currentAddress.getCountry());
        previousAddress.setState(currentAddress.getState());
        previousAddress.setAddressType(currentAddress.getAddressType());

        addressRepository.save(previousAddress);
    }

    @Override
    public void delete(Long id) {

        addressRepository.deleteById(id);
    }

    @Override
    public DomainEntity login(DomainEntity domainEntity) {
        return null;
    }

    @Override
    public DomainEntity readByID(long id) {
        return addressRepository.findById(id).get();
    }

    @Override
    public List<DomainEntity> read(DomainEntity domainEntity) {

        Long client_id = ((Address) domainEntity).getClient().getId();
        List<Address> result = addressRepository.findAllAddressByClientId(client_id);
        List<DomainEntity> entities = new ArrayList<>();

        for(Address address : result ){
            entities.add(address);
        }

        return entities;
    }
}
