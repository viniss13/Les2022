package les.fatec.harmonicenter.controller;

import les.fatec.harmonicenter.DTO.*;
import les.fatec.harmonicenter.domain.*;
import les.fatec.harmonicenter.domain.Enum.OrderStatus;
import les.fatec.harmonicenter.facade.Facade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/orders")
public class OrderController {

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

    @PutMapping("/add_address")
    public ResponseEntity add_address(@RequestBody OrderAddressDTO dto){

        Address address = new Address(dto.getAddress_id());
        Client client = new Client(dto.getClient_id());
        Order order = new Order(address, client);

        result = facade.update(order);

        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/add_card")
    public ResponseEntity add_card(@RequestBody OrderCardDTO dto){

        Card card = new Card(dto.getCard_id());
        Client client = new Client(dto.getClient_id());
        Order order = new Order(card, client);

        result = facade.update(order);

        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/add_coupon")
    public ResponseEntity add_coupon(@RequestBody OrderCouponDTO dto){

        Coupon coupon = new Coupon(dto.getCoupon_code());
        Client client = new Client(dto.getClient_id());
        Order order = new Order(coupon, client);

        result = facade.update(order);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/read_draft")
    public ResponseEntity read_draft(@Param("client_id") Long client_id){

        Client client = new Client(client_id);
        Order order = new Order(client);

        result = facade.read(order);

        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody OrderStatusDTO dto){

        Order order = new Order(dto.getId(), dto.getStatus());

        result = facade.update(order);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/read_details")
    public ResponseEntity read_details(@Param("order_id") Long order_id){


        Order order = new Order(order_id);

        result = facade.readById(order);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/read")
    public ResponseEntity read(){
        Order order = new Order();

        result = facade.read(order);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/read_client")
    public ResponseEntity read_client(@Param("client_id") Long client_id){
        Client client = new Client(client_id);
        Order order = new Order(client);

        order.setStatus(OrderStatus.EM_ANALISE);

        result = facade.read(order);

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete_item")
    public ResponseEntity<Result> deleteItem(@Param("item_id") Long item_id){

        Item item = new Item(item_id);

        result = facade.delete(item);

        return ResponseEntity.ok().body(result);
    }
}
