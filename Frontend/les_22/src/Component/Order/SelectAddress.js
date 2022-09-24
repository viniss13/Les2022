import React from 'react';
import AddressService from '../../service/Address/AddressService';
import LocalStorageService from '../../service/config/LocalStorageService';
import { ToastContainer, toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import OrderService from '../../service/Order/OrderService';

const addressService = new AddressService();
const orderService = new OrderService();

const SelectAddress = () => {

  const navigate = useNavigate();
  const [adresses, setAddress] = React.useState(null);
  const [ client_id, setClientId] = React.useState(null);

  const getAllAddress = () => {
    const userData = LocalStorageService.obterItem("_logged_user");
    const id = userData.entities[0].id;
    setClientId(id);

    addressService.getAllAddress(id)
      .then(response => {
        setAddress(response.data.entities);
      }).catch(error => {
        console.log("error")
      })
  }

  React.useEffect(() => {
    getAllAddress();
  }, [])

  const selectAddress = (address_id) => {
    orderService.addAddress({client_id: client_id, address_id: address_id})
      .then( response => {
        
        toast.success("Endereço selecionado com sucesso!");
        navigate("/select_card")
        
      }).catch(error =>{
        toast.error("Erro ao selecionar endereço!")
      })
  }

  return (
    <>
    <div className="card p-5 d-flex flex-row justify-content-center flex-wrap">
      <div className="container">
      <ToastContainer />

      <Link 
        className="btn btn-danger rainbow-bg mx-2" to={"/create_address"} >
              Cadastrar
      </Link>
      </div>
      {adresses?.length === 0 && <h1>Sem Endereços</h1>}
      {adresses?.map((address) => (
        <div key={address.id} className="card m-3">
          <div className="card-body" >
            <ul className="list-group list-group-flush">
              <li className="list-group-item">Logradouro: {address.logradouro}</li>
              <li className="list-group-item">Tipo Logradouro: {address.residencyType}</li>
              <li className="list-group-item">Número: {address.number}</li>
              <li className="list-group-item">CEP: {address.zipCode}</li>
              <li className="list-group-item">Observação: {address.observation}</li>
            </ul>
          </div>
          
          <div className="card-footer d-flex justify-content-center">
          {/* {<button
              type="button"
              className="btn btn-danger rainbow-bg mx-2"
              onClick={e => deleteAddress(address.id)}
            >
              Alterar
            </button>} */}         

            {<button
              type="button"
              className="btn btn-danger rainbow-bg"
              onClick={e => selectAddress(address.id)}
            >
              Selecionar
            </button>}
          </div>

        </div>
      ))}

    </div>
      <a href="/user_home" className="btn btn-outline-secondary mb-2 " >Voltar</a>

    </>
    
  )
}


export default SelectAddress