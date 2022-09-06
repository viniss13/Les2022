
import React from 'react';
import Login from '../../Component/Client/Login';
import "bootstrap/dist/css/bootstrap.min.css";

import { BrowserRouter, Routes, Route } from 'react-router-dom';
import ClientManagement from '../../Component/Client/ClientManagement';
import Signin from '../../Component/Client/Signin';

function RoutesService() {
  return (
    <BrowserRouter>
      <Routes >
        <Route path="/login" element={<Login />} />

        <Route path="/signin" element={<Signin />} />

        <Route path="/user_home" element={<ClientManagement />} />

      </Routes>
    </BrowserRouter>
  )
}

export default RoutesService;