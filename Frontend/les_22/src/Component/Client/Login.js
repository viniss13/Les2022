
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import ClientService from '../../service/Client/ClientService';
import LocalStorageService from '../../service/config/LocalStorageService';
import { errorMessage, successMessage } from '../toastr';

const clientService = new ClientService()

const Login = () => {
  const navigate = useNavigate()

  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const doLogin = () => {
    clientService.authenticate({
      email,
      password
    })
      .then(response => {

        LocalStorageService.removerItem("_logged_user");
        LocalStorageService.addItem('_logged_user', response.data); 
        console.log("RESPONSE", response.data, navigate);

        successMessage('Login feito com sucesso!');
        navigate('/user_home');
      })
      .catch(error => {
        errorMessage(error);
      })
  };

  const doRegister = async () => {

  }

  return (
    <div className="container text-white rounded-circle rounded-5">
      <div className="row">
        <div className="col-sm-9 col-md-7 col-lg-5 mx-auto ">
          <div className="card border-0 shadow rounded-3 my-5">
            <div className="card-body bg-secondary p-4 p-sm-5 rounded-2">
              <h5 className="card-title text-center mb-5 fw-light fs-5">Login</h5>
              <div className="form-floating mb-3">
                <input
                  type="email"
                  className="form-control text-dark"
                  id="floatingInput"
                  placeholder="name@example.com"
                  value={email}
                  name="email"
                  onChange={(e) => setEmail(e.target.value)}
                />
                <label htmlFor="floatingInput">E-mail</label>
              </div>
              <div className="form-floating mb-3">
                <input
                  type="password"
                  className="form-control"
                  id="floatingPassword"
                  placeholder="Senha"
                  value={password}
                  name="password"
                  onChange={(e) => setPassword(e.target.value)}
                />
                <label htmlFor="floatingPassword">Senha</label>
              </div>
              <div className="d-grid">
                <button onClick={doLogin} className="btn btn-primary mt-3" >Entrar</button>
                <button className="btn btn-dark mt-3">Cadastrar</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}


export default Login;