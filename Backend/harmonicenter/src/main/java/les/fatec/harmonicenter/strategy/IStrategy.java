package les.fatec.harmonicenter.strategy;

import les.fatec.harmonicenter.domain.DomainEntity;
import org.springframework.stereotype.Component;

@Component
public interface IStrategy {

    public String process(DomainEntity domainEntity);

}
