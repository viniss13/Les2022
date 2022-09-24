package les.fatec.harmonicenter.repository;

import les.fatec.harmonicenter.domain.Coupon;
import les.fatec.harmonicenter.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{

    Coupon findByCode(String code);

    List<Coupon> findAllByClientId(Long client_id);
}

