
import React, { useEffect } from "react";
import LocalStorageService from "../../service/config/LocalStorageService";

import { ToastContainer, toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const AdmManagement = () => {

  return (
    <>
        <ToastContainer />

      <section className=" mb-5 my-5">
        <form className="container mt-5 mb-5" fragment="seusDados">

          <div className="card px-5 py-5">

            <h6 className="text-center">Bem vindo administrador </h6>
            <div className="row">

              <hr className="mt-3" />

              <div className="card border-0 col-6 my-3 px-5 py-5 " style={{ width: '600px' }}>

                <a className="btn btn-outline-dark list-group-item mx-2 my-2 border border-dark" href="/adm_orders">Pedidos</a>
                <a className="btn btn-outline-dark list-group-item mx-2 my-2 border border-dark" href="/dashboard">Dashboard</a>

              </div>
            </div>
          </div>
        </form>
      </section>
    </>
  )
}

export default AdmManagement