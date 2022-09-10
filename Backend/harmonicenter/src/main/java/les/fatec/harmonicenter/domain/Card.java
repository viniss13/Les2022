package les.fatec.harmonicenter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import les.fatec.harmonicenter.DTO.CardDTO;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name= "_card")
@Entity
public class Card extends DomainEntity {

    @ManyToOne(optional = true)
    @JsonIgnore// evita loop infinito
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "number")
    private String number;

    @Column(name = "holder")
    private String holder;// titular do cartao

    @Column(name = "expirationDateMonth")
    private String expirationDateMonth; // data de validade

    @Column(name = "expirationDateYear")
    private String expirationDateYear; // data de validade

    @Column(name = "security")
    private String security; // 3 digitos la

    @Column(name = "holderCpf")
    private String holderCpf;

    @Column(name = "preferencial")
    private Boolean preferencial;

    @Column(name = "flag")
    private String flag;// bandeira

    @Column(name = "alias")
    private String alias;

    public Card(CardDTO cardDTO) {

        Client client = new Client();
        client.setId(cardDTO.getClient());

        this.client = client;
        this.number = cardDTO.getNumber();
        this.holder = cardDTO.getHolder();
        this.expirationDateMonth = cardDTO.getExpirationDateMonth();
        this.expirationDateYear = cardDTO.getExpirationDateYear();
        this.security = cardDTO.getSecurity();
        this.holderCpf = cardDTO.getHolderCpf();
        this.preferencial = cardDTO.getPreferencial();
        this.flag = cardDTO.getFlag();
        this.alias = cardDTO.getAlias();
    }

    public Card(Long id) {
        super(id);
    }

    public Card(Client client) {
        this.client = client;
    }
}