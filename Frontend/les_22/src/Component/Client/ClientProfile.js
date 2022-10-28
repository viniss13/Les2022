
import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom';
import LocalStorageService from '../../service/config/LocalStorageService';
import { ToastContainer, toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const ClientProfile = () => {

  const navigate = useNavigate();

  const [data, setData] = React.useState('');

  useEffect( () => {
    
    const userData = LocalStorageService.obterItem("_logged_user");

    setData(userData.entities[0]);

    console.log("USERDATA", userData.entities[0]);

  }, [])
  
  const deleteAccount = () => {

    LocalStorageService.removerItem("_logged_user");
    navigate('/');
  }

  return (
    <>
    <section className=" mb-5 my-5">

        <form className="container mt-5 mb-5" >
        <ToastContainer />

            <div className="row ">
              <div className="col-12">
                <div className=" px-5 py-5">
                    <h1 className="text-center">Minha conta</h1>
                    <hr />
                    <div className="row">
                        <div className="form-group col-md-6 mb-3">
                            <label ><strong>Email:</strong> {data.email} </label>
                        </div>
                    </div>
                    <div className="row">
                        <div className="form-group col-md-6 mb-3">
                            <label ><strong>Name:</strong> {data.name}</label>
                        </div>
                    </div>

                    <div className="form-group mb-3">
                        <label ><strong>CPF:</strong> {data.cpf} </label>
                    </div>

                    <div className="form-group mb-3">
                        <label ><strong>Data de Nascimento:</strong> {data.birthDate}</label>
                    </div>

                    <div className="form-group mb-3">
                        <label ><strong>Gênero: </strong> {data.gender}</label>
                    </div>

                    <div className="form-group mb-3 ">
                        <label ><strong>Tipo:</strong> {data.phoneType}</label>
                    </div>

                    <div className="form-group mb-3">
                        <label ><strong>DDD:</strong> {data.areaCode}</label>
                    </div>

                    <div className="form-group">
                        <label ><strong>Telefone:</strong> {data.phoneNumber}</label>
                    </div>
                  </div>
                </div>

              <div className="col-12 ">
                <div className="form-group">

                    <a href="/user_home" className="btn btn-outline-secondary mb-2 " >Voltar</a>
                    <a href="/client_update" className="btn btn-primary mb-2 " >Alterar Dados</a>
                    <a href="/password_update" className="btn btn-warning mb-2 " >Alterar Senha</a>

                    <button onClick={deleteAccount} type="button" className="btn btn-danger mb-2 ">Inativar Conta</button>

                </div>
                </div>
            </div>
        </form>
    </section>
</>  )
}

export default ClientProfile