package les.fatec.harmonicenter.controller;

import les.fatec.harmonicenter.DTO.ClientDTO;
import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.Result;
import les.fatec.harmonicenter.facade.Facade;
import les.fatec.harmonicenter.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    private Facade facade;

    @Autowired
    private Result result;

    @PostMapping
    public ResponseEntity save(@RequestBody ClientDTO dto){

        Client client = new Client(dto);
        result = facade.save(client);

        return ResponseEntity.ok().body(result);
    }
}
