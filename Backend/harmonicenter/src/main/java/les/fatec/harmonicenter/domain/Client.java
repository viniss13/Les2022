package les.fatec.harmonicenter.domain;

import com.sun.istack.NotNull;
import les.fatec.harmonicenter.DTO.ClientChangePasswordDTO;
import les.fatec.harmonicenter.DTO.ClientLoginDTO;
import les.fatec.harmonicenter.DTO.ClientSaveDTO;
import les.fatec.harmonicenter.DTO.ClientUpdateDTO;
import les.fatec.harmonicenter.domain.Enum.Gender;
import les.fatec.harmonicenter.domain.Enum.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// ME INDICA NA SUA EMPRESA ARR FINI https://github.com/LucasDonizeti/e-commerce-notebook zoas kk
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name= "_client")
@Entity
public class Client extends DomainEntity {

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Transient
    private String confirmNewPassword;

    @Transient
    private String confirmOldPassword;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "PhoneType")
    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;

    @Column(name = "areaCode")//DDD
    private String areaCode;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "client")
   // @Fetch(FetchMode.JOIN)
    private List<Address> addressList;

    @OneToMany(mappedBy = "client")
   // @LazyCollection(LazyCollectionOption.FALSE)
    private List<Card> cardList;

    @OneToMany(mappedBy = "client")
   // @LazyCollection(LazyCollectionOption.FALSE)
    private List<Order> orders;

    @OneToMany( mappedBy = "client")
    //@LazyCollection(LazyCollectionOption.FALSE)
    private List<Cart> cart;

    @OneToMany(mappedBy = "client")
   // @LazyCollection(LazyCollectionOption.FALSE)
    private List<Coupon> coupons = new ArrayList<>();

    public Client(ClientSaveDTO clientDTO) {
        this.name = clientDTO.getName();
        this.cpf = clientDTO.getCpf();
        this.email = clientDTO.getEmail();
        this.password = clientDTO.getPassword();
        this.birthDate = clientDTO.getBirthDate();
        this.phoneType = clientDTO.getType();
        this.areaCode = clientDTO.getAreaCode();
        this.phoneNumber = clientDTO.getPhoneNumber();
        this.gender = clientDTO.getGender();
    }

    public Client(ClientUpdateDTO clientUpdateDTO) {

        Client client = new Client();
        client.setId(clientUpdateDTO.getClient());

        this.setId(client.getId());
        this.name = clientUpdateDTO.getName();
        this.cpf = clientUpdateDTO.getCpf();
        this.email = clientUpdateDTO.getEmail();
        this.birthDate = clientUpdateDTO.getBirthDate();
        this.phoneType = clientUpdateDTO.getType();
        this.areaCode = clientUpdateDTO.getAreaCode();
        this.phoneNumber = clientUpdateDTO.getPhoneNumber();
        this.gender = clientUpdateDTO.getGender();
    }

    public Client(ClientLoginDTO clientDTO) {

        this.email = clientDTO.getEmail();
        this.password = clientDTO.getPassword();
    }

    public Client(Long id) {
        super(id);
    }

    public Client(ClientChangePasswordDTO clientChangePasswordDTO) {
        super(clientChangePasswordDTO.getClient_id());
        this.password = clientChangePasswordDTO.getNew_password();
        this.confirmNewPassword = clientChangePasswordDTO.getConfirm_new_password();
        this.confirmOldPassword = clientChangePasswordDTO.getConfirm_old_password();
    }
}
