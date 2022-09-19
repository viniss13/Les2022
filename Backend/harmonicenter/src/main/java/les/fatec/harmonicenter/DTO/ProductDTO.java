package les.fatec.harmonicenter.DTO;

import les.fatec.harmonicenter.domain.Enum.Category;
import les.fatec.harmonicenter.domain.Enum.InstrumentBrand;
import les.fatec.harmonicenter.domain.Enum.InstrumentFamily;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Integer stock;

    private String name;

    private String productDescription;

    private Double price;

    private Category category;

    private InstrumentFamily instrumentFamily;

    private InstrumentBrand instrumentBrand;
}
