package les.fatec.harmonicenter.DAO;

import les.fatec.harmonicenter.domain.DomainEntity;

import java.util.List;

public interface IDAO {

    DomainEntity create(DomainEntity domainEntity);
    List<DomainEntity> read(DomainEntity domainEntity);
    void delete(Long id);
    void update(DomainEntity domainEntity);
    DomainEntity login(DomainEntity domainEntity);
    DomainEntity readByID(long id);

}