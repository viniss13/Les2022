package les.fatec.harmonicenter.controller;

import les.fatec.harmonicenter.DTO.CardDTO;
import les.fatec.harmonicenter.DTO.ClientSaveDTO;
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
@CrossOrigin(origins = "*")
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private Facade facade;

    @Autowired
    private Result result;


    @PostMapping("/create")
    public ResponseEntity save(@RequestBody CardDTO dto){

        Card card = new Card(dto);

        result = facade.create(card);
        if( result.getMsg().isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }else{
            return ResponseEntity.badRequest().body(result);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@Param("id") Long id){

        Card card = new Card(id);

        result = facade.delete(card);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/read")
    public ResponseEntity<Result> readAll(@Param("client_id") Long client_id){

        Card card = new Card(new Client(client_id));
        result = facade.read(card);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/read_by_id")
    public ResponseEntity<Result> readById(@Param("id") Long id){

        Card card = new Card(id);

        result = facade.readById(card);

        return ResponseEntity.ok().body(result);
    }

}
