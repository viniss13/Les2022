package les.fatec.harmonicenter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import les.fatec.harmonicenter.domain.Enum.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name= "_order")
@Entity
public class Order extends DomainEntity {

    @ManyToOne(optional = true)
    @JsonIgnore// evita loop infinito
    @JoinColumn(name = "client")
    private Client client;

    @ManyToOne(optional = true)
    @JoinColumn(name = "address")
    private Address address;

    @ManyToOne(optional = true)
    @JoinColumn(name = "card_id")
    private Card card;

    @ManyToOne(optional = true)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.DRAFT;

    @ManyToOne(optional = true)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Column
    private Double order_value;


    @OneToMany(mappedBy = "order")
   // @LazyCollection(LazyCollectionOption.FALSE)
    private List<Exchange> exchanges;

    @OneToMany(mappedBy = "order")
   // @LazyCollection(LazyCollectionOption.FALSE)
    private List<Requestcard> cards;

    public Order(Card card, Client client) {
        this.card = card;
        this.client = client;
    }

    public Order(Address address, Client client) {
        this.address = address;
        this.client = client;
    }

    public Order(Client client){
        this.client = client;
    }

    public Order(Long order_id) {
        super(order_id);
    }

    public Order(Long id, OrderStatus status) {
        super(id);
        this.status = status;
    }

    public Order(Coupon coupon, Client client) {
        this.coupon = coupon;
        this.client = client;
    }
}