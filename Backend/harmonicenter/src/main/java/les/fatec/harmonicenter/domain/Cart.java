package les.fatec.harmonicenter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import les.fatec.harmonicenter.DTO.CardDTO;
import les.fatec.harmonicenter.DTO.CartDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name= "_cart")
@Entity
public class Cart extends DomainEntity {

    @ManyToOne(optional = true)
    @JsonIgnore// evita loop infinito
    @JoinColumn(name = "client")
    private Client client;

    @OneToMany(mappedBy = "cart")
   // @LazyCollection(LazyCollectionOption.FALSE)
    private List<Item> items;

    @Column
    private boolean currentCart = true;

    @Column
    private Double total_value;

    public Cart(CartDTO dto){
        this.client = new Client(dto.getClient_id());
        this.items = new ArrayList<>();
        items.add(new Item(dto.getQuantity(), dto.getProduct_id()));
    }

    public Cart(Item item){
        this.items = new ArrayList<>();
        items.add(item);
    }

    public Cart(Client client) {
        this.client = client;
    }
}