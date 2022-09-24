package les.fatec.harmonicenter.DAO;

import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.Coupon;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.domain.Product;
import les.fatec.harmonicenter.repository.CouponRepository;
import les.fatec.harmonicenter.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CouponDAO implements IDAO{

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public DomainEntity create(DomainEntity domainEntity) {

        Coupon coupon = ( Coupon ) domainEntity;
        coupon.setCreationDate(LocalDate.now());
        couponRepository.save(coupon);

        return coupon;
    }

    @Override
    public void update(DomainEntity domainEntity) {
    /*
     */
    }

    @Override
    public void delete(Long id) {

        productRepository.deleteById(id);
    }

    @Override
    public DomainEntity login(DomainEntity domainEntity) {
        return null;
    }

    @Override
    public DomainEntity readByID(long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<DomainEntity> read(DomainEntity domainEntity) {

        Coupon coupon = (Coupon) domainEntity;

        Client client = coupon.getClient();

        List<DomainEntity> entities = new ArrayList<>();
        List<Coupon> coupons = new ArrayList<>();

        if(client == null){
            coupons = couponRepository.findAll();
        } else {
            Long client_id = client.getId();
            coupons = couponRepository.findAllByClientId(client_id);
        }

        for(Coupon currentCoupon : coupons ){
            entities.add(currentCoupon);
        }


        return entities;
    }
}
