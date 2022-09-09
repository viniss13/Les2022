package les.fatec.harmonicenter.strategy.Address;

import les.fatec.harmonicenter.domain.Address;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.strategy.IStrategy;
import les.fatec.harmonicenter.strategy.client.VerifyExistingClientID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidateAddressFields implements IStrategy {

    @Autowired
    VerifyExistingClientID veClientID;
    @Override
    public String process(DomainEntity domainEntity) {

        Address address = (Address) domainEntity;

        String msg = "";

        List<String> listMsg = new ArrayList<>();

        String street = address.getStreet();

        String residencyType = address.getResidencyType();

        String observation = address.getObservation();

        String number = address.getNumber();

        String district = address.getDistrict();

        String zipCode = address.getZipCode();

        String logradouro = address.getLogradouro();

        String city = address.getCity();

        String country = address.getCountry();

        String state = address.getState();

        if(street.isEmpty() || street.isBlank()) listMsg.add("Rua obrigatória");

        if(residencyType.isEmpty() || residencyType.isBlank()) listMsg.add("Tipo de residencia obrigatória");

        if(number.isEmpty() || number.isBlank()) listMsg.add("Número é obrigatorio ");

        if(district.isEmpty() || district.isBlank()) listMsg.add("Bairro é obrigatorio ");

        if(zipCode.isEmpty() || zipCode.isBlank()) listMsg.add("Cep é obrigatorio ");

        if(logradouro.isEmpty() || logradouro.isBlank()) listMsg.add("Logradouro é obrigatorio ");

        if(city.isEmpty() || city.isBlank()) listMsg.add("Cidade é obrigatória");

        if(country.isEmpty() || country.isBlank()) listMsg.add("País é obrigatório");

        if(state.isEmpty() || state.isBlank()) listMsg.add("Estado é obrigatório");

        String existingClientMessage = veClientID.process(address.getClient());

        if(!existingClientMessage.isEmpty()) listMsg.add(existingClientMessage);

        if(!listMsg.isEmpty()) msg = String.join("\n", listMsg);

        return msg;
    }
}
