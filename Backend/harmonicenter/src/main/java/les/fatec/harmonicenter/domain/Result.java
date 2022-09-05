package les.fatec.harmonicenter.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Component
public class Result {

    private List<String> msg = new ArrayList<>();
    private List<DomainEntity> entities;

    public Result(){
        entities = new ArrayList<>();
    }

    public void addEntities(DomainEntity entity){
      /*  if(entities == null){
            entities = new ArrayList<DomainEntity>();
        }*/
        entities.add(entity);
    }

}
