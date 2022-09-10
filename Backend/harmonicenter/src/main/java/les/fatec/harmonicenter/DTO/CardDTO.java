package les.fatec.harmonicenter.DTO;


import les.fatec.harmonicenter.domain.Card;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    private String number;
    private String holder;
    private String expirationDateMonth;
    private String expirationDateYear;
    private String security;
    private String holderCpf;
    private Boolean preferencial;
    private String flag;
    private Long client;

    private String alias;

    public CardDTO(Card card){

        this.number = card.getNumber();
        this.holder = card.getHolder();
        this.expirationDateMonth = card.getExpirationDateMonth();
        this.expirationDateYear = card.getExpirationDateYear();
        this.security = card.getSecurity();
        this.holderCpf = card.getHolderCpf();
        this.preferencial = card.getPreferencial();
        this.flag = card.getFlag();
        this.client = card.getClient().getId();
    }
}
