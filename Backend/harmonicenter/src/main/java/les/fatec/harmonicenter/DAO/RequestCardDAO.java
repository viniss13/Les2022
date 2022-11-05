package les.fatec.harmonicenter.DAO;

import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.domain.Product;
import les.fatec.harmonicenter.domain.Requestcard;
import les.fatec.harmonicenter.repository.ProductRepository;
import les.fatec.harmonicenter.repository.RequestcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestCardDAO implements IDAO{

    @Autowired
    RequestcardRepository requestcardRepository;

    @Override
    public DomainEntity create(DomainEntity domainEntity) {

        Requestcard requestcard = ( Requestcard ) domainEntity;

        Requestcard currentRequestCard = requestcardRepository.findByOrderIdAndCardId(requestcard.getOrder().getId(),
                requestcard.getCard().getId());



        if(requestcard.getBuyingValue() <= 0.0){
            if(currentRequestCard != null) requestcardRepository.deleteById(currentRequestCard.getId());

            return requestcard;
        }

        if(currentRequestCard == null) currentRequestCard = requestcard;


        currentRequestCard.setBuyingValue(requestcard.getBuyingValue());
        requestcard.setCreationDate(LocalDate.now());
        requestcardRepository.save(currentRequestCard);

        return requestcard;
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

     //   productRepository.deleteById(id);
    }

    @Override
    public DomainEntity login(DomainEntity domainEntity) {
        return null;
    }

    @Override
    public DomainEntity readByID(long id) {
        //return productRepository.findById(id).get();
        return null;
    }

    @Override
    public List<DomainEntity> read(DomainEntity domainEntity) {

        Requestcard requestcard = (Requestcard) domainEntity;
        List<Requestcard> result = requestcardRepository.findAllByOrderId(requestcard.getOrder().getId());
        List<DomainEntity> entities = new ArrayList<>();

        for(Requestcard currentCard : result ){
            entities.add(currentCard);
        }

        return entities;


    }
}
