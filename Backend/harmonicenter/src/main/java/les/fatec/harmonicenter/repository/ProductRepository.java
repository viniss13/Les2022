package les.fatec.harmonicenter.repository;

import les.fatec.harmonicenter.domain.Product;
import les.fatec.harmonicenter.domain.Requestcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    @Query(nativeQuery = true, value =
            " SELECT * FROM _product " +
                    "WHERE stock > 0 " +
                    "AND active = TRUE " +
                    "AND (UPPER(NAME) LIKE %:search% OR UPPER(PRODUCT_DESCRIPTION) LIKE %:search%)")
    List<Product> readWithFilter(@Param("search") String search);
}

