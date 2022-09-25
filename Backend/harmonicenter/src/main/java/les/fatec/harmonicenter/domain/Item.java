package les.fatec.harmonicenter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import les.fatec.harmonicenter.DTO.CardDTO;
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
@Table( name= "_item")
@Entity
public class Item extends DomainEntity {

    @ManyToOne()
    @JsonIgnore// evita loop infinito
    @JoinColumn(name = "cart")
    private Cart cart;

    @ManyToOne()
    @JoinColumn(name = "product")
    private Product product;

    @Column
    private Long quantity;
    
    @Column
    private Double total_value;

    public Item(ItemDTO dto) {
        this.quantity = dto.getQuantity();
        this.product = new Product(dto.getProduct_id());

    }

    public Item(Long quantity, Long product_id) {
        this.quantity = quantity;
        this.product = new Product(product_id);
    }

    public Item(Long item_id) {
        super(item_id);
    }
}