
import React, { useState } from 'react';
import { Navigate, useNavigate } from 'react-router-dom';
import ClientService from '../../service/Client/ClientService';
import LocalStorageService from '../../service/config/LocalStorageService';

const Home = () => {

  const navigate = useNavigate();

  const doLogin = () =>{
    navigate('/login');
  }

  const doRegister = () =>{
    navigate('/signin');
  }
  
  return (
    <>
      <div className="container text-white rounded-circle rounded-5">
      <div className="row">
        <div className="col-sm-9 col-md-7 col-lg-5 mx-auto ">
          <div className="card border-0 shadow rounded-3 my-5">
            <div className="card-body bg-secondary p-4 p-sm-5 rounded-2">
              <h5 className="card-title text-center mb-5 fw-light fs-5">Home</h5>
              <div className="form-floating mb-3">
              </div>
              <div className="form-floating mb-3">
                <div className="row">
                  <button onClick={doLogin} className="btn btn-dark mt-3 mr-5 col-6">Login</button>
                  <button onClick={doRegister} className="btn btn-info mt-3 text-light col-6">Cadastrar</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    </>
  )
}

export default Home;