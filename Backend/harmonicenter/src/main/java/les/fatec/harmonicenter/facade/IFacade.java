package les.fatec.harmonicenter.facade;

import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.domain.Result;

public interface IFacade {

    public Result save(DomainEntity domainEntity);
    public Result update(DomainEntity domainEntity);
    public Result delete(DomainEntity domainEntity);
    public Result list(DomainEntity domainEntity);
    public Result get(DomainEntity domainEntity);
    public Result login(DomainEntity domainEntity);
    public Result read(DomainEntity domainEntity);
}