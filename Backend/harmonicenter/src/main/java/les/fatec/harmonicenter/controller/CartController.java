package les.fatec.harmonicenter.controller;

import les.fatec.harmonicenter.DTO.CartDTO;
import les.fatec.harmonicenter.DTO.ItemDTO;
import les.fatec.harmonicenter.DTO.ProductDTO;
import les.fatec.harmonicenter.domain.Cart;
import les.fatec.harmonicenter.domain.Item;
import les.fatec.harmonicenter.domain.Product;
import les.fatec.harmonicenter.domain.Result;
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
    public ResponseEntity read(){

        Product product = new Product();

        result = facade.read(product);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/read_by_id")
    public ResponseEntity<Result> readById(@Param("id") Long id){

        Product product = new Product(id);

        result = facade.readById(product);

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@Param("id") Long id){

        Product product = new Product(id);

        result = facade.delete(product);

        return ResponseEntity.ok().body(result);
    }
}
