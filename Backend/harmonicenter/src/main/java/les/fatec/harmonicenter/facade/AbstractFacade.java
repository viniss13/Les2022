package les.fatec.harmonicenter.facade;

import les.fatec.harmonicenter.DAO.ClientDAO;
import les.fatec.harmonicenter.DAO.IDAO;
import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.strategy.IStrategy;
import les.fatec.harmonicenter.strategy.client.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractFacade {

    protected Map<String, IDAO> daos = new HashMap<>();

    protected Map<String, Map<String, List<IStrategy>>> rules = new HashMap<>();

    public static final String CREATE = "CREATE";

    public static final String READ = "READ";

    public static final String UPDATE = "UPDATE";

    public static final String DELETE = "DELETE";

    public static final String LOGIN = "LOGIN";



    @Autowired
    ClientDAO clientDAO;

    @Autowired
    ValidateExistingEmail validateExistingEmail;

    @Autowired
    ValidateCPF validateCPF;

    @Autowired
    ValidatePasswordNull validatePasswordNull;

//    @Autowired
//    ValidatePasswordNumberCaracter validatePasswordNumberCaracter;

//    @Autowired
//    ValidatePasswordEquals validatePasswordEquals;

//    @Autowired
//    ValidateTypePhone validateTypePhone;

    @Autowired
    ValidateBirthDate validateBirthDate;

//    @Autowired
//    Validate validate;

//    @Autowired
//    EncryptPassword passowrd;

//    @Autowired
//    ValidateCreditCardLength validateCreditCardLength;

//    @Autowired
//    ValidateFieldEmail validateFieldEmail;

    @Autowired
    ValidateBirthDate validateFieldBirthDate;

    @Autowired
    ValidateFieldDDD validateFieldDDD;

    @Autowired
    ValidateFieldPhoneNumber validateFieldPhoneNumber;

    @Autowired
    ValidateFieldGender validateFieldGender;

    @Autowired
    ValidateFieldEmail validateFieldEmail;

    @Autowired
    ValidateExistingUser validateExistingUser;

    protected void initializeMaps() {
        daos.put(Client.class.getName(), clientDAO);


        //***************************** CLIENT *****************************
        List<IStrategy> saveClient = new ArrayList<>();

        saveClient.add(validateFieldEmail);
        saveClient.add(validateFieldBirthDate);
        saveClient.add(validateFieldDDD);
        saveClient.add(validateFieldGender);
        saveClient.add(validateFieldPhoneNumber);
        saveClient.add(validateExistingEmail);
        saveClient.add(validatePasswordNull);

        /// ************ EDITAR ***************
        List<IStrategy> editClient = new ArrayList<>();

        editClient.add(validateExistingUser);
        editClient.add(validateFieldEmail);
        editClient.add(validateFieldBirthDate);
        editClient.add(validateFieldDDD);
        editClient.add(validateFieldGender);
        editClient.add(validateFieldPhoneNumber);

        // ********** pesquisar *********
        List<IStrategy> consultClient = new ArrayList<>();

        /// ************ Excluir ***************
        List<IStrategy> deleteClient = new ArrayList<>();
        deleteClient.add(validateExistingUser);

        Map<String, List<IStrategy>> clientRules = new HashMap<>();
        clientRules.put(CREATE, saveClient);
        clientRules.put(UPDATE, editClient);
        clientRules.put(READ, consultClient);
        clientRules.put(DELETE, deleteClient);
        clientRules.put(LOGIN, new ArrayList<>());

        this.rules.put(Client.class.getName(), clientRules);

    }
}
