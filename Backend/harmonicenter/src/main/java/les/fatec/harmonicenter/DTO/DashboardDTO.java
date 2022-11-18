package les.fatec.harmonicenter.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDTO {

    private Long id;
    private String name;
    private Long quantity;
    private LocalDate creationDate;
}
