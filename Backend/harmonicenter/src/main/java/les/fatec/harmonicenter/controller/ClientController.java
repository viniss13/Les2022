package les.fatec.harmonicenter.controller;

import les.fatec.harmonicenter.DTO.ClientLoginDTO;
import les.fatec.harmonicenter.DTO.ClientSaveDTO;
import les.fatec.harmonicenter.DTO.ClientUpdateDTO;
import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.Result;
import les.fatec.harmonicenter.facade.Facade;
import les.fatec.harmonicenter.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    private Facade facade;

    @Autowired
    private Result result;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody ClientLoginDTO dto){

        Client client = new Client(dto);
        result = facade.login(client);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/create")
    public ResponseEntity save(@RequestBody ClientSaveDTO dto){

        Client client = new Client(dto);

        result = facade.save(client);

        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@RequestBody ClientUpdateDTO clientUpdateDTO){

       Client client = new Client(clientUpdateDTO);
       result = facade.update(client);

       return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@Param("id") Long id){

        Client client = new Client();
        client.setId(id);

        result = facade.delete(client);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/read")
    public ResponseEntity<Result> list(){

        List<Client> clientList = new ArrayList<>();
        Client client = new Client();
        client.setGlobalSearch(true);
        result = facade.read(client);

        return ResponseEntity.ok().body(result);
    }

}