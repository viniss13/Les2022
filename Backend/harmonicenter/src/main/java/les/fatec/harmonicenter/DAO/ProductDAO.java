package les.fatec.harmonicenter.DAO;

import les.fatec.harmonicenter.domain.Address;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.domain.Product;
import les.fatec.harmonicenter.repository.AddressRepository;
import les.fatec.harmonicenter.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDAO implements IDAO{

    @Autowired
    ProductRepository productRepository;

    @Override
    public DomainEntity create(DomainEntity domainEntity) {

        Product product = ( Product ) domainEntity;
        product.setCreationDate(LocalDate.now());
        productRepository.save(product);

        return product;
    }

    @Override
    public void update(DomainEntity domainEntity) {
/*
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

        addressRepository.save(previousAddress);*/
    }

    @Override
    public void delete(Long id) {

        productRepository.deleteById(id);
    }

    @Override
    public DomainEntity login(DomainEntity domainEntity) {
        return null;
    }

    @Override
    public DomainEntity readByID(long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<DomainEntity> read(DomainEntity domainEntity) {

        List<Product> result = productRepository.findAll();
        List<DomainEntity> entities = new ArrayList<>();

        for(Product product : result ){
            entities.add(product);
        }

        return entities;
    }
}
