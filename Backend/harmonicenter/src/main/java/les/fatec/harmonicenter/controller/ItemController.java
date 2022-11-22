package les.fatec.harmonicenter.controller;

import les.fatec.harmonicenter.DTO.CartDTO;
import les.fatec.harmonicenter.DTO.ItemGenerateDTO;
import les.fatec.harmonicenter.domain.Cart;
import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.Item;
import les.fatec.harmonicenter.domain.Result;
import les.fatec.harmonicenter.facade.Facade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private Facade facade;

    @Autowired
    private Result result;


    @PostMapping("/create")
    public ResponseEntity save(@RequestBody List<ItemGenerateDTO> dtos){
        for(int i = 0; i < dtos.size(); i++){
            Item item = new Item(dtos.get(i));

            result = facade.create(item);
        }

        if( result.getMsg().isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }else{
            return ResponseEntity.badRequest().body(result);
        }
    }
}
