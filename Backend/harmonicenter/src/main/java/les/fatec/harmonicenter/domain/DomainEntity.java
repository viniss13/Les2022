package les.fatec.harmonicenter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public abstract class DomainEntity extends Result implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate = LocalDate.now();

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;

    @Transient
    private boolean globalSearch = false;

    public DomainEntity(Long id) {
        this.id = id;
    }

    public DomainEntity(LocalDate localDate) {this.creationDate = localDate;}
}
