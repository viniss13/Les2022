package les.fatec.harmonicenter.controller;

import les.fatec.harmonicenter.DTO.ProductDTO;
import les.fatec.harmonicenter.DTO.RequestCardDTO;
import les.fatec.harmonicenter.domain.Order;
import les.fatec.harmonicenter.domain.Product;
import les.fatec.harmonicenter.domain.Requestcard;
import les.fatec.harmonicenter.domain.Result;
import les.fatec.harmonicenter.facade.Facade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/request_cards")
public class RequestcardController {

    @Autowired
    private Facade facade;

    @Autowired
    private Result result;

    @PostMapping("/create")
    public ResponseEntity save(@RequestBody RequestCardDTO dto){

        Requestcard requestcard = new Requestcard(dto);

        result = facade.create(requestcard);

        if( result.getMsg().isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }else{
            return ResponseEntity.badRequest().body(result);
        }
    }


    @GetMapping("/read")
    public ResponseEntity<Result> read(@Param("order_id") Long order_id){

        Requestcard requestcard = new Requestcard(new Order(order_id));

        result = facade.read(requestcard);

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@Param("id") Long id){

        Product product = new Product(id);

        result = facade.delete(product);

        return ResponseEntity.ok().body(result);
    }
}
