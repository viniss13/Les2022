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
        List<IStrategy> entityRules = entityMap.get(CREATE);

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
    public Result update(DomainEntity entity) {
        super.initializeMaps();
        result = new Result();
        errorMessagesList.clear();
        String className = entity.getClass().getName();
        Map<String, List<IStrategy>> entityMap = rules.get(className);
        List<IStrategy> entityRules = entityMap.get(UPDATE);

        executeRules(entity, entityRules);

        if(errorMessagesList.isEmpty()){
            IDAO dao = daos.get(className);
            dao.update(entity);
            result.addEntities(entity);
        }else{
            result.addEntities(entity);
            result.setMsg(errorMessagesList);
        }

        return result;
    }

    @Override
    public Result delete(DomainEntity domainEntity) {

        super.initializeMaps();
        result = new Result();
        errorMessagesList.clear();
        String className = domainEntity.getClass().getName();
        Map<String, List<IStrategy>> entityMap = rules.get(className);
        List<IStrategy> entityRules = entityMap.get(DELETE);

        executeRules(domainEntity, entityRules);

        if(errorMessagesList.isEmpty()){
            IDAO dao = daos.get(className);
            dao.delete(domainEntity.getId());
            result.addEntities(domainEntity);
        }else{
            result.addEntities(domainEntity);
            result.setMsg(errorMessagesList);
        }

        return result;
    }

    @Override
    public Result list(DomainEntity domainEntity) {
        return null;
    }

    @Override
    public Result read(DomainEntity domainEntity) {

        super.initializeMaps();
        result = new Result();
        errorMessagesList.clear();
        String className = domainEntity.getClass().getName();
        Map<String, List<IStrategy>> entityMap = rules.get(className);
        List<IStrategy> entityRules = entityMap.get(READ);

        executeRules(domainEntity, entityRules);

        if(errorMessagesList.isEmpty()){
            IDAO dao = daos.get(className);
            List<DomainEntity> entities = dao.read(domainEntity);

            for(DomainEntity entity : entities) result.addEntities(entity);
        }else{
            result.addEntities(domainEntity);
            result.setMsg(errorMessagesList);
        }

        return result;
    }

    @Override
    public Result get(DomainEntity domainEntity) {
        return null;
    }

    @Override
    public Result login(DomainEntity domainEntity) {

        super.initializeMaps();
        result = new Result();
        errorMessagesList.clear();
        String className = domainEntity.getClass().getName();
        Map<String, List<IStrategy>> entityMap = rules.get(className);
        List<IStrategy> entityRules = entityMap.get(LOGIN);

        executeRules(domainEntity, entityRules);

        if(errorMessagesList.isEmpty()){
            IDAO dao = daos.get(className);
            domainEntity = dao.login(domainEntity);
            result.addEntities(domainEntity);
        }else{
            result.addEntities(domainEntity);
            result.setMsg(errorMessagesList);
        }

        return result;
    }

}
