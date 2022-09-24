package les.fatec.harmonicenter.controller;

import les.fatec.harmonicenter.DTO.CouponDTO;
import les.fatec.harmonicenter.DTO.ProductDTO;
import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.Coupon;
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
@RequestMapping("/api/coupons")
public class CouponController {

    @Autowired
    private Facade facade;

    @Autowired
    private Result result;

    @PostMapping("/create")
    public ResponseEntity save(@RequestBody CouponDTO dto){

        Coupon coupon = new Coupon(dto);

        result = facade.create(coupon);

        if( result.getMsg().isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }else{
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("/read")
    public ResponseEntity read(){

        Coupon coupon = new Coupon();

        result = facade.read(coupon);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/read_client")
    public ResponseEntity read_client(@Param("client_id") Long client_id){

        Client client = new Client(client_id);
        Coupon coupon = new Coupon(client);

        result = facade.read(coupon);

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
