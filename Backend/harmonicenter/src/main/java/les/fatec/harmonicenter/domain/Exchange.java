package les.fatec.harmonicenter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import les.fatec.harmonicenter.DTO.ExchangeDTO;
import les.fatec.harmonicenter.DTO.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name= "_exchange")
@Entity
public class Exchange extends DomainEntity {


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne()
    @JoinColumn(name = "product")
    private Product product;

    @Column
    private Long quantity;

    @Column
    private Double total_value;

    public Exchange(ItemDTO dto) {
        this.quantity = dto.getQuantity();
        this.product = new Product(dto.getProduct_id());

    }

    public Exchange(Long quantity, Long product_id) {
        this.quantity = quantity;
        this.product = new Product(product_id);
    }

    public Exchange(Long item_id) {
        super(item_id);
    }

    public Exchange(ExchangeDTO exchangeDTO) {
        this.quantity = exchangeDTO.getQuantity();
        this.product = new Product(exchangeDTO.getProduct_id());
        this.order = new Order(exchangeDTO.getOrder_id());
    }
}