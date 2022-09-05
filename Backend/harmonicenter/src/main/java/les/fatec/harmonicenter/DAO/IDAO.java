package les.fatec.harmonicenter.DAO;

import les.fatec.harmonicenter.domain.DomainEntity;

import java.util.List;

public interface IDAO {

    DomainEntity create(DomainEntity domainEntity);
    void delete(Long id);
    void update(DomainEntity domainEntity);
    List<DomainEntity> list(DomainEntity domainEntity);
    DomainEntity login(DomainEntity domainEntity);

    List<DomainEntity> read(DomainEntity domainEntity);
//    public DomainEntity get(Long id);
}