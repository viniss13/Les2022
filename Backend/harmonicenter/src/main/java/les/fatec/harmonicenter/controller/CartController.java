package les.fatec.harmonicenter.controller;

import les.fatec.harmonicenter.DTO.CartDTO;
import les.fatec.harmonicenter.DTO.ItemDTO;
import les.fatec.harmonicenter.DTO.ProductDTO;
import les.fatec.harmonicenter.domain.*;
import les.fatec.harmonicenter.facade.Facade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private Facade facade;

    @Autowired
    private Result result;

    /*
    @PostMapping("/create")
    public ResponseEntity save(@RequestBody ProductDTO dto){

        Product product = new Product(dto);

        result = facade.create(product);

        if( result.getMsg().isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }else{
            return ResponseEntity.badRequest().body(result);
        }
    }*/

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody CartDTO dto){
        Cart cart = new Cart(dto);

        result = facade.update(cart);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/read")
    public ResponseEntity read(@Param("client_id") Long client_id){

        Client client = new Client(client_id);
        Cart cart = new Cart(client);

        result = facade.read(cart);

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete_item")
    public ResponseEntity<Result> deleteItem(@Param("item_id") Long item_id){

        Item item = new Item(item_id);

        result = facade.delete(item);

        return ResponseEntity.ok().body(result);
    }
}
