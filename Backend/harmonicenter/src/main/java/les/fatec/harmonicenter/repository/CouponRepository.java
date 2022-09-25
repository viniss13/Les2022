package les.fatec.harmonicenter.repository;

import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.Coupon;
import les.fatec.harmonicenter.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{

    Coupon findByCode(String code);

    List<Coupon> findAllByClientId(Long client_id);

    @Query(nativeQuery = true,
            value ="SELECT\n" +
            "    * \n" +
            "FROM\n" +
            "    _COUPON\n" +
            "WHERE\n" +
            "   code = :code AND QUANTITY > 0 AND ACTIVE = TRUE AND (client = :client_id OR `type` = :type)")
    Coupon findByCodeAndActiveTrueAndQuantityMoreThanZero(@Param("code") String code,
                                                          @Param("client_id")Long client_id,
                                                          @Param("type") String type);
}

