package les.fatec.harmonicenter.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@MappedSuperclass
public abstract class DomainEntity extends Result {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "deleted", nullable = false)
    private boolean active = false;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate = LocalDate.now();

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;

    private boolean globalSearch = false;
}
