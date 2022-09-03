package les.fatec.harmonicenter.DAO;

import les.fatec.harmonicenter.domain.DomainEntity;

import java.util.List;

public interface IDAO {

    public DomainEntity create(DomainEntity domainEntity);
    public void delete(Long id);
    public void update(DomainEntity domainEntity);
    public List<DomainEntity> list(DomainEntity domainEntity);
//    public DomainEntity get(Long id);
}