
import React, { useEffect } from "react";
import LocalStorageService from "../../service/config/LocalStorageService";
// import { withRouter } from "react-router-dom/cjs/react-router-dom.min";


import { ToastContainer, toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const ClientManagement = () => {

  const [data, setData] = React.useState('');

  //Executa ao carregar a página
  useEffect(() => {

   // toast.success('Login feito com sucesso!');    

    const userData = LocalStorageService.obterItem("_logged_user");

    setData(userData.entities[0]);

    console.log("USERDATA", userData.entities[0].email);

  }, [])

  return (
    <>
        <ToastContainer />

      <section className=" mb-5 my-5">
        <form className="container mt-5 mb-5" fragment="seusDados">

          <div className="card px-5 py-5">

            <h6 className="text-center">Bem vindo {data.name} </h6>
            <div className="row">

              <hr className="mt-3" />

              <div className="card border-0 col-6 my-3 px-5 py-5 " style={{ width: '600px' }}>

                <a className="btn btn-outline-dark list-group-item mx-2 my-2 border border-dark" href="/client_profile">Minha Conta</a>

                <a className="btn btn-outline-dark list-group-item mx-2 my-2 border border-dark" href="/client_cards">Meus Cartões</a>

                <a className="btn btn-outline-dark list-group-item mx-2 my-2 border border-dark" href="/client_adresses">Meus Endereços</a>

                <a className="btn btn-outline-dark list-group-item mx-2 my-2 border border-dark" href="/products">Produtos</a>

                <a className="btn btn-outline-dark list-group-item mx-2 my-2 border border-dark" href="/orders">Pedidos</a>


              </div>

            </div>
          </div>
        </form>
      </section>
    </>
  )
}

export default ClientManagement