package les.fatec.harmonicenter.domain;

import les.fatec.harmonicenter.DTO.ProductDTO;
import les.fatec.harmonicenter.domain.Enum.Category;
import les.fatec.harmonicenter.domain.Enum.InstrumentBrand;
import les.fatec.harmonicenter.domain.Enum.InstrumentFamily;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name= "_product")
@Entity
public class Product extends DomainEntity{

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "name")
    private String name;

    @Column(name = "productDescription")
    private String productDescription;

    @Column(name = "price")
    private Double price;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column( name = "category")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column( name = "instrumentFamily")
    private InstrumentFamily instrumentFamily;

    @Enumerated(EnumType.STRING)
    @Column( name = "instrumentBrand", length=1000)
    private InstrumentBrand instrumentBrand;

    @Transient
    private String search;

    public Product(ProductDTO dto) {
        this.stock = dto.getStock();
        this.name = dto.getName();
        this.productDescription = dto.getProductDescription();
        this.price = dto.getPrice();
        this.category = dto.getCategory();
        this.instrumentFamily = dto.getInstrumentFamily();
        this.instrumentBrand = dto.getInstrumentBrand();
        this.imageUrl = dto.getImageUrl();
    }

    public Product(Long id) {super(id);}
}
