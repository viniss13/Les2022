package les.fatec.harmonicenter.facade;

import les.fatec.harmonicenter.DAO.IDAO;
import les.fatec.harmonicenter.domain.DomainEntity;
import les.fatec.harmonicenter.domain.Result;
import les.fatec.harmonicenter.repository.ClientRepository;
import les.fatec.harmonicenter.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class Facade extends AbstractFacade implements IFacade{

    @Autowired
    ClientRepository clientRepository;

    private List<String> errorMessagesList = new ArrayList<>();

    private Result result;

    private void executeRules(DomainEntity domainEntity, List<IStrategy> rules){
        for (IStrategy rule: rules){
            String msg = rule.process(domainEntity);
            if(!StringUtils.isEmpty(msg)){
                errorMessagesList.add(msg);
            }
        }
    }

    @Override
    public Result save(DomainEntity entity) {

        super.initializeMaps();
        result = new Result();
        errorMessagesList.clear();
        String className = entity.getClass().getName();
        Map<String, List<IStrategy>> entityMap = rules.get(className);
        List<IStrategy> entityRules = entityMap.get(SALVAR);

        executeRules(entity, entityRules);

        if(errorMessagesList.isEmpty()){
            IDAO dao = daos.get(className);
            dao.create(entity);
            result.addEntities(entity);
        }else{
            result.addEntities(entity);
            result.setMsg(errorMessagesList);
        }

        return result;
    }

    @Override
    public Result update(DomainEntity domainEntity) {
        return null;
    }

    @Override
    public Result delete(DomainEntity domainEntity) {
        return null;
    }

    @Override
    public Result list(DomainEntity domainEntity) {
        return null;
    }

    @Override
    public Result get(DomainEntity domainEntity) {
        return null;
    }
}
