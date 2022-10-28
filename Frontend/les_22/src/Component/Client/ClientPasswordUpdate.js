import React from 'react';
import ClientService from '../../service/Client/ClientService';
import { ToastContainer, toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { Link, Navigate, useNavigate } from 'react-router-dom';

const clientService = new ClientService();

const ClientPasswordUpdate = () => {

  const navigate = useNavigate();  

  const [password, setPassword] = React.useState('');
  const [newPassword, setNewPassword] = React.useState('');
  const [confirmNewPassword, setConfirmNewPassword] = React.useState('');

  const changePassword = () =>{
    const userData = JSON.parse(localStorage.getItem("_logged_user")).entities[0];

    let changePasswordObject = {};
    changePasswordObject.client_id = userData.id;
    changePasswordObject.confirm_old_password = password;
    changePasswordObject.new_password = newPassword;
    changePasswordObject.confirm_new_password = confirmNewPassword;

    console.log("MINHA ALTERACAO", changePasswordObject);

    clientService.changePassword(changePasswordObject).then( response => {
      const qtdMsg = response.data.msg.length;
        if (qtdMsg === 0) {
          toast.success("Senha alterada com sucesso!");

          setTimeout(() => {
            navigate("/client_profile");
          }, "1000");  
          
        } else {
          let messages = response.data.msg;
          console.log("messages", messages);
          for (let i = 0; i < messages.length; i++) {
            let msgs = messages[i].split("\n");
            for (let message in msgs) {
              toast.error(msgs[message]);
            }
          }
        }
    }).catch( err => {

    })
  }

  return (
    <>
      <ToastContainer />
      <section className="row card container justify-content-center align-items-center ">

        {/* <div className="my-5 text-center">
            <h1 className="display-4 font-weight-bold text-white">Cadastre-se Agora</h1>
        </div> */}

        <form id="password" method="POST">
          <div className="row">
            <div className="form-group col-12">
              <label htmlFor="password">Senha atual</label>
              <input
                type="password"
                className="form-control"
                placeholder="Insira sua senha"
                name="password"
                value={password}
                onChange={ (e) => setPassword(e.target.value)}            
              />
            </div>

            <div className="form-group col-12">
              <label htmlFor="newPassword">Nova senha</label>
              <input
              type="password"
              className="form-control"
              placeholder="Insira sua nova senha"
              name="newPassword"
              value={newPassword}
              onChange={ (e) => setNewPassword(e.target.value)}            
              />
            </div>

            <div className="form-group col-12">
              <label htmlFor="confirmNewPassword">Confirmar Nova senha</label>
              <input
              type="password"
              className="form-control"
              placeholder="Insira sua nova senha"
              name="confirmNewPassword"
              value={confirmNewPassword}
              onChange={ (e) => setConfirmNewPassword(e.target.value)}            
              />
            </div>

            <button onClick={changePassword} type="button" className="btn btn-primary mt-3">Salvar</button>
          </div>
          
        </form>        
        
      </section>

    </>
  )
}

export default ClientPasswordUpdate