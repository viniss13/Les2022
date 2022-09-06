
import React from 'react';
import Login from '../../Component/Client/Login';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

function RoutesService(){

  return(
      <BrowserRouter>
        <div>
          <Routes >
            <Route path="/" component={Login}>Login</Route>
          </Routes>
        </div>
      </BrowserRouter> 
  )
}

export default RoutesService;