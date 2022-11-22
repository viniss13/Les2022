package les.fatec.harmonicenter.repository;

import les.fatec.harmonicenter.DTO.DashboardDTO;
import les.fatec.harmonicenter.DTO.ProductDTO2;
import les.fatec.harmonicenter.domain.Address;
import les.fatec.harmonicenter.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.List;

public interface DashboardRepository extends JpaRepository<Product, Long>{


    @Query(value="SELECT new les.fatec.harmonicenter.DTO.DashboardDTO(p.id, p.name, sum(i.quantity), i.creationDate)" +
            "       FROM Item i " +
            "          INNER JOIN i.product p " +
            " group by i.creationDate, p.id")
    List<DashboardDTO> getDashboard();

    @Query(value="SELECT new les.fatec.harmonicenter.DTO.DashboardDTO(p.id, p.name, sum(i.quantity), i.creationDate)" +
            "       FROM Item i " +
            "          INNER JOIN i.product p " +
            " WHERE i.creationDate >= :dtInicio AND i.creationDate <= :dtFim " +
            " group by i.creationDate, p.id")
    List<DashboardDTO> getDashboardByDate(@Param("dtInicio") LocalDate dtInicio, @Param("dtFim") LocalDate dtFim);


}
