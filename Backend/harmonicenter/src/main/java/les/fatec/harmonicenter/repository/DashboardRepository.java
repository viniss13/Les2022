package les.fatec.harmonicenter.repository;

import les.fatec.harmonicenter.DTO.DashboardDTO;
import les.fatec.harmonicenter.DTO.ProductDTO2;
import les.fatec.harmonicenter.domain.Address;
import les.fatec.harmonicenter.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

public interface DashboardRepository extends JpaRepository<Product, Long>{

    @Query(nativeQuery = true, value = "select \n" +
            "pr1.id,\n" +
            "pr1.name,\n" +
            "sum(it1.quantity) as quantidade,\n" +
            "CAST(it1.creation_date AS varchar) as creation_date\n" +

            "from _PRODUCT  AS pr1\n" +
            "RIGHT JOIN _ITEM AS it1 on pr1.id = it1.product\n" +
            "group by it1.creation_date, pr1.id\n" +
            "order by it1.creation_date")
    List<Object> findDashboard();

    // id: Product
    // name: Product
    // quantidade: Item
    // data: Item

    @Query(value="SELECT new les.fatec.harmonicenter.DTO.DashboardDTO(p.id, p.name, sum(i.quantity), i.creationDate)" +
            "       FROM Item i " +
            "          INNER JOIN i.product p " +
            " group by i.creationDate, p.id")
    List<DashboardDTO> getDashboard();


}
