import React from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import AddressService from '../../../service/Address/AddressService';
import ClientService from '../../../service/Client/ClientService';
import LocalStorageService from '../../../service/config/LocalStorageService';

import SelectMenu from '../../SelectMenu';

const addressService = new AddressService();

const AddressCreate = () => {
    
  const navigate = useNavigate(); 

  const [data, setData] = React.useState(null);
  const [street, setStreet] = React.useState("");
  const [residencyType, setResidencyType] = React.useState("");
  const [observation, setObservation] = React.useState("");
  const [number, setNumber] = React.useState("");
  const [district, setDistrict] = React.useState("");
  const [zipCode, setZipCode] = React.useState("");
  const [logradouro, setLogradouro] = React.useState("");
  const [city, setCity] = React.useState("");
  const [country, setCountry] = React.useState("");
  const [state, setState] = React.useState("");
  const [addressType, setAddressType] = React.useState("");

  const [ client, setClient] = React.useState();

  const addressTypeList = addressService.getAddresType();

  let qtdMsg;  

  const clientService = new ClientService();

  React.useEffect( () =>{

    const userData = LocalStorageService.obterItem("_logged_user");
    let dataUser = userData.entities[0];
    setClient( dataUser.id );
    
  },[])

    const createAddress = () =>{
      
    addressService.create({       
        client,  
        street,
        residencyType,
        observation,
        number,
        district,
        zipCode,
        logradouro,
        city,
        country,
        state,
        addressType
    })
        .then( response => {

            qtdMsg = response.data.msg.length;

            console.log("QUANTIDADES STRATEGY", qtdMsg);
    
            if( qtdMsg === 0 ){
                
                console.log(qtdMsg);
    
                console.log("Criado");
                // successMessage('Cadastro realizado com sucesso!');
                alert('Criação realizada com sucesso!');
    
    
                navigate('/client_adresses');
            } else {
                clientService.alertMessage(response.data);
            }   
          }).catch( error =>{
              alert(error);          
          })
   }
  return (
    <>
        <section className=" card px-5 py-5 mx-5 my-5">
                <div fragment="novoEndereco">

                    <div className="my-2 text-center">
                        <h1 className="display-5 text-primary">Editar Endereço</h1>
                    </div>
                    <form action="/meusDados">
                        <div className="form-group">
                            <label htmlFor="inputLogradouro">Logradouro</label>
                            <input 
                                type="text" 
                                className="form-control" 
                                id="inputLogradouro" 
                                placeholder="Logradouro"
                                name="logradouro"
                                value={logradouro}
                                onChange={(e) => setLogradouro(e.target.value)}
                                />
                        </div>

                        <div className="form-group">
                            <label htmlFor="inputRua">Rua</label>
                            <input 
                                type="text" 
                                className="form-control" 
                                id="inputRua" 
                                placeholder="Rua n sei onde" 
                                name="street"
                                value={street}
                                onChange={(e) => setStreet(e.target.value)}
                                />
                        </div>

                        <div className="form-group">
                            <label htmlFor="inputNumero">Número</label>
                            <input 
                                type="text" 
                                className="form-control numero" 
                                id="inputNumero" 
                                placeholder="" 
                                name="number" 
                                value={number}
                                onChange={(e) => setNumber(e.target.value)}
                                />                                
                        </div>

                        <div className="form-row">
                            <div className="form-group col-md-4">
                                <label htmlFor="inputCEP">CEP</label>
                                <input 
                                    type="text" 
                                    className="form-control" 
                                    id="inputCEP" 
                                    name="zipCode"
                                    value={zipCode}
                                    onChange={(e) => setZipCode(e.target.value)}
                                    />
                            </div>

                            <div className="form-group">
                                <label htmlFor="inputBairro">Bairro</label>
                                <input 
                                    type="text" 
                                    className="form-control" 
                                    id="inputBairro" 
                                    placeholder="Bairro"
                                    name="district" 
                                    value={district}
                                    onChange={(e) => setDistrict(e.target.value)}
                                    />
                            </div>

                            <div className="form-group col-md-4">
                                <label htmlFor="inputTipoResidencia">Tipo de Residencia</label>
                                <input 
                                    type="text" 
                                    className="form-control" 
                                    id="inputTipoResidencia" 
                                    placeholder="Tipo de Residencia"
                                    name="residencyType" 
                                    value={residencyType}
                                    onChange={(e) => setResidencyType(e.target.value)}
                                    />
                            </div>                 

                            <div className="form-group col-md-4">
                                <label htmlFor="inputCidade">Cidade</label>
                                <input 
                                    type="text" 
                                    className="form-control" 
                                    id="inputCidade" 
                                    placeholder="Cidade" 
                                    name="city"
                                    value={city}
                                    onChange={(e) => setCity(e.target.value)}
                                    />  
                            </div>

                            <div className="form-group col-md-4">
                                <label htmlFor="inputEstado">Estado</label>
                                <input 
                                    type="text" 
                                    className="form-control" 
                                    id="inputEstado" 
                                    placeholder="Estado" 
                                    name="state"
                                    value={state}
                                    onChange={(e) => setState(e.target.value)}
                                    />                               
                            </div>

                            <div className="form-group col-md-4">
                                <label htmlFor="inputPais">País</label>
                                <input 
                                    type="text" 
                                    className="form-control" 
                                    id="inputPais" 
                                    placeholder="País" 
                                    name="country"
                                    value={country}
                                    onChange={(e) => setCountry(e.target.value)}
                                    />  
                            </div>
                        </div>

                        <div className="form-group">
                            <label htmlFor="inputTipoTelefone">Tipo Endereço</label>
                            <SelectMenu id="inputType"
                                lista={addressTypeList}
                                className="form-control"
                                name="addressType"
                                value={addressType}
                                onChange={(e) => setAddressType(e.target.value)}
                                />
                        </div>

                        <div className="form-group">
                            <label htmlFor="inputComplemento">Observações</label>
                            <input 
                                type="text" 
                                className="form-control" 
                                id="inputComplemento" 
                                placeholder="Observações"
                                name="observation"
                                required={true} 
                                value={observation}
                                onChange={(e) => setObservation(e.target.value)}
                                />
                        </div>
                        <button onClick={createAddress} type="button" className="btn btn-primary">Salvar</button>

                    </form>
                </div>

                <a href="/client_adresses" className="btn btn-outline-secondary mb-2" style={{maxWidth: '140px'}}>Voltar</a>

            </section>
    </>
  )
}

export default AddressCreate