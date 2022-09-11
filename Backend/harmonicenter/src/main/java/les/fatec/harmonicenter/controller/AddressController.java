package les.fatec.harmonicenter.controller;

import les.fatec.harmonicenter.DTO.AddressDTO;
import les.fatec.harmonicenter.DTO.AddressUpdateDTO;
import les.fatec.harmonicenter.domain.Address;
import les.fatec.harmonicenter.domain.Card;
import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.Result;
import les.fatec.harmonicenter.facade.Facade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/adresses")
public class AddressController {

    @Autowired
    private Facade facade;

    @Autowired
    private Result result;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody AddressDTO dto){

        Address address = new Address(dto);

        result = facade.create(address);

        if( result.getMsg().isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }else{
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@RequestBody AddressUpdateDTO dto){

        Address address = new Address(dto);
        result = facade.update(address);

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@Param("id") Long id){

        Address address = new Address(id);

        result = facade.delete(address);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/read")
    public ResponseEntity<Result> readAll(@Param("client_id") Long client_id){

        Address address = new Address(new Client(client_id));
        address.setGlobalSearch(true);
        result = facade.read(address);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/read_by_id")
    public ResponseEntity<Result> readById(@Param("id") Long id){

        Address address = new Address(id);

        result = facade.readById(address);

        return ResponseEntity.ok().body(result);
    }

}
