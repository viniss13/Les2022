package les.fatec.harmonicenter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import les.fatec.harmonicenter.DTO.CardDTO;
import les.fatec.harmonicenter.DTO.RequestCardDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Table( name= "_requestcard")
@Entity
public class Requestcard extends DomainEntity {

    @Column(name = "buying_value")
    private Double buyingValue;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    public Requestcard(){

    }

    public Requestcard(RequestCardDTO dto) {
        this.card = new Card(dto.getCard_id());
        this.order = new Order(dto.getOrder_id());
        this.buyingValue = dto.getValue();
    }

    public Requestcard(Order order) {
        this.order = order;
    }
}