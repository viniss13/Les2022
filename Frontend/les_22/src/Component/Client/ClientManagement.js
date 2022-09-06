
import React, { useEffect, useMemo } from "react";
import LocalStorageService from "../../service/config/LocalStorageService";
// import { withRouter } from "react-router-dom/cjs/react-router-dom.min";

const ClientManagement = () => {

  const [data, setData] = React.useState('');

  const getData = useEffect( () => {
    const userData = LocalStorageService.obterItem("_logged_user");
    setData(userData.entities[0]);
    console.log("USERDATA", userData.entities[0].email);
  }, [])

  return (
    <>
      <section className=" mb-5 my-5">
        <form className="container mt-5 mb-5" fragment="seusDados">

          <div className="card px-5 py-5">

            <h6 className="text-center">Bem vindo {data.name}</h6>
            <div className="row">

              <hr className="mt-3" />

              <div className="card border-0 col-6 my-3 px-5 py-5 " style={{ width: '600px' }}>

                <a className="btn btn-outline-dark list-group-item mx-2 my-2 border border-dark" href="/perfilDetalhes">Minha conta</a>
                {/* <a className="btn btn-outline-dark list-group-item mx-2 my-2 border border-dark" href="/listaEndereco">Endereços</a>
                <a className="btn btn-outline-dark list-group-item mx-2 my-2 border border-dark" href="/cartoes">Cartões</a> */}

              </div>
            
            </div>
          </div>
        </form>
      </section>
    </>
  )
}

export default ClientManagement