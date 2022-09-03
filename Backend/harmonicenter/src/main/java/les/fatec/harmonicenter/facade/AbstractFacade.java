package les.fatec.harmonicenter.facade;

import les.fatec.harmonicenter.DAO.ClientDAO;
import les.fatec.harmonicenter.DAO.IDAO;
import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractFacade {

    protected Map<String, IDAO> daos = new HashMap<>();

    protected Map<String, Map<String, List<IStrategy>>> rules = new HashMap<>();

    public static final String SALVAR = "SALVAR";

    public static final String EDITAR = "EDITAR";

    public static final String EXCLUIR = "EXCLUIR";

    public static final String PESQUISAR = "PESQUISAR";

    @Autowired
    ClientDAO clientDAO;

    protected void initializeMaps() {
        daos.put(Client.class.getName(), clientDAO);

        //***************************** CLIENT *****************************
        List<IStrategy> saveClient = new ArrayList<>();

        /// ************ EDITAR ***************
        List<IStrategy> editClient = new ArrayList<>();

        // ********** pesquisar *********
        List<IStrategy> consultClient = new ArrayList<>();

        /// ************ Excluir ***************
        List<IStrategy> deleteClient = new ArrayList<>();

        Map<String, List<IStrategy>> clientRules = new HashMap<>();
        clientRules.put(SALVAR, saveClient);
        clientRules.put(EDITAR, editClient);
        clientRules.put(PESQUISAR, consultClient);
        clientRules.put(EXCLUIR, deleteClient);
//        clientRules.put(PESQUISARESPECIFICO, )

        this.rules.put(Client.class.getName(), clientRules);

    }
}
