package les.fatec.harmonicenter.facade;

import les.fatec.harmonicenter.DAO.*;
import les.fatec.harmonicenter.domain.*;
import les.fatec.harmonicenter.strategy.Address.ValidateAddressFields;
import les.fatec.harmonicenter.strategy.Address.VerifyExistingAddressID;
import les.fatec.harmonicenter.strategy.Card.ValidateCardFields;
import les.fatec.harmonicenter.strategy.Card.VerifyExistingCardID;
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

    public static final String READ_BY_ID = "READ_BY_ID";


    // ***************** DAOS ********************
    @Autowired
    ClientDAO clientDAO;

    @Autowired
    AddressDAO addressDAO;

    @Autowired
    CardDAO cardDAO;

    @Autowired
    CartDAO cartDAO;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    OrderDAO orderDAO;

    // ***************** Strategys ********************

    @Autowired
    ValidateExistingEmail validateExistingEmail;

    @Autowired
    ValidateCPF validateCPF;

    @Autowired
    ValidatePasswordNull validatePasswordNull;


    @Autowired
    ValidateBirthDate validateBirthDate;


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

    @Autowired
    VerifyExistingClientID verifyExistingClientID;

    @Autowired
    VerifyExistingAddressID verifyExistingAddressID;

    @Autowired
    VerifyExistingCardID verifyExistingCardID;

    @Autowired
    ValidateAddressFields validateAddressFields;

    @Autowired
    ValidateCardFields validateCardFields;

    @Autowired
    ValidateAuthentication validateAuthentication;

    protected void initializeMaps() {
        daos.put(Client.class.getName(), clientDAO);
        daos.put(Address.class.getName(), addressDAO);
        daos.put(Card.class.getName(), cardDAO);
        daos.put(Product.class.getName(), productDAO);
        daos.put(Cart.class.getName(), cartDAO);
        daos.put(Item.class.getName(), cartDAO);
        daos.put(Order.class.getName(), orderDAO);

        //***************************** CLIENT *****************************
        List<IStrategy> createClient = new ArrayList<>();

        createClient.add(validateFieldEmail);
        createClient.add(validateFieldBirthDate);
        createClient.add(validateFieldDDD);
        createClient.add(validateFieldGender);
        createClient.add(validateFieldPhoneNumber);
        createClient.add(validateExistingEmail);
        createClient.add(validatePasswordNull);

        /// ************ EDITAR ***************
        List<IStrategy> editClient = new ArrayList<>();
        editClient.add(validateFieldEmail);
        editClient.add(validateFieldBirthDate);
        editClient.add(validateFieldDDD);
        editClient.add(validateFieldGender);
        editClient.add(validateFieldPhoneNumber);

        // ********** pesquisar todos *********
        List<IStrategy> readClient = new ArrayList<>();

        // ********** pesquisar especifico *********
        List<IStrategy> readClientByID = new ArrayList<>();

        readClientByID.add(verifyExistingClientID);

        /// ************ Excluir ***************
        List<IStrategy> deleteClient = new ArrayList<>();
        deleteClient.add(verifyExistingClientID);

        /// ************ Login ***************
        List<IStrategy> loginClient = new ArrayList<>();
        loginClient.add(validateAuthentication);

        Map<String, List<IStrategy>> clientRules = new HashMap<>();
        clientRules.put(CREATE, createClient);
        clientRules.put(UPDATE, editClient);
        clientRules.put(READ, readClient);
        clientRules.put(DELETE, deleteClient);
        clientRules.put(LOGIN, loginClient);
        clientRules.put(READ_BY_ID, readClientByID);

        this.rules.put(Client.class.getName(), clientRules);

        /// ************ Address ********************

        List<IStrategy> createAddress = new ArrayList<>();
        createAddress.add(validateAddressFields);
        createAddress.add(verifyExistingClientID);

        List<IStrategy> updateAddress = new ArrayList<>();
        updateAddress.add(validateAddressFields);
        updateAddress.add(verifyExistingAddressID);

        List<IStrategy> readAddress = new ArrayList<>();

        List<IStrategy> deleteAddress = new ArrayList<>();
        deleteAddress.add(verifyExistingAddressID);


        List<IStrategy> readAddressByID = new ArrayList<>();
        readAddressByID.add(verifyExistingAddressID);

        Map<String, List<IStrategy>> addressRules = new HashMap<>();
        addressRules.put(CREATE, createAddress);
        addressRules.put(UPDATE, updateAddress);
        addressRules.put(READ, readAddress);
        addressRules.put(DELETE, deleteAddress);
        addressRules.put(LOGIN, new ArrayList<>());
        addressRules.put(READ_BY_ID, readAddressByID);

        this.rules.put(Address.class.getName(), addressRules);

        /// ************ Card ********************

        List<IStrategy> createCard = new ArrayList<>();
        createCard.add(validateCardFields);
        createCard.add(verifyExistingClientID);

        List<IStrategy> readCard = new ArrayList<>();
        List<IStrategy> deleteCard = new ArrayList<>();
        deleteCard.add(verifyExistingCardID);

        List<IStrategy> readCardById = new ArrayList<>();
        readCardById.add(verifyExistingCardID);

        Map<String, List<IStrategy>> cardRules = new HashMap<>();
        cardRules.put(CREATE, createCard);
        cardRules.put(READ, readCard);
        cardRules.put(DELETE, deleteCard);
        cardRules.put(READ_BY_ID, readCardById);

        this.rules.put(Card.class.getName(), cardRules);


        /// ************ Product ********************

        List<IStrategy> createProduct = new ArrayList<>();
        List<IStrategy> readProduct = new ArrayList<>();
        List<IStrategy> updateProduct = new ArrayList<>();
        List<IStrategy> deleteProduct = new ArrayList<>();
        List<IStrategy> readByIDProduct = new ArrayList<>();

        Map<String, List<IStrategy>> productRules = new HashMap<>();
        productRules.put(CREATE, createProduct);
        productRules.put(READ, readProduct);
        productRules.put(UPDATE, updateProduct);
        productRules.put(DELETE, deleteProduct);
        productRules.put(READ_BY_ID, readByIDProduct);

        this.rules.put(Product.class.getName(), productRules);


        /// ************ Cart ********************

        List<IStrategy> createCart = new ArrayList<>();
        List<IStrategy> readCart = new ArrayList<>();
        List<IStrategy> updateCart = new ArrayList<>();
        List<IStrategy> deleteCart = new ArrayList<>();
        List<IStrategy> readByIDCart = new ArrayList<>();

        Map<String, List<IStrategy>> cartRules = new HashMap<>();
        cartRules.put(CREATE, createCart);
        cartRules.put(READ, readCart);
        cartRules.put(UPDATE, updateCart);
        cartRules.put(DELETE, deleteCart);
        cartRules.put(READ_BY_ID, readByIDCart);

        this.rules.put(Cart.class.getName(), cartRules);

        /// ************ Item ********************

        List<IStrategy> createItem = new ArrayList<>();
        List<IStrategy> readItem = new ArrayList<>();
        List<IStrategy> updateItem = new ArrayList<>();
        List<IStrategy> deleteItem = new ArrayList<>();
        List<IStrategy> readByIDItem = new ArrayList<>();

        Map<String, List<IStrategy>> itemRules = new HashMap<>();
        itemRules.put(CREATE, createItem);
        itemRules.put(READ, readItem);
        itemRules.put(UPDATE, updateItem);
        itemRules.put(DELETE, deleteItem);
        itemRules.put(READ_BY_ID, readByIDItem);

        this.rules.put(Item.class.getName(), itemRules);

        /// ************ Order ********************

        List<IStrategy> createOrder = new ArrayList<>();
        List<IStrategy> readOrder = new ArrayList<>();
        List<IStrategy> updateOrder = new ArrayList<>();
        List<IStrategy> deleteOrder = new ArrayList<>();
        List<IStrategy> readByIDOrder = new ArrayList<>();

        Map<String, List<IStrategy>> orderRules = new HashMap<>();
        orderRules.put(CREATE, createOrder);
        orderRules.put(READ, readOrder);
        orderRules.put(UPDATE, updateOrder);
        orderRules.put(DELETE, deleteOrder);
        orderRules.put(READ_BY_ID, readByIDOrder);

        this.rules.put(Order.class.getName(), orderRules);
    }

}
