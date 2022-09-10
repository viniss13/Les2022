
import Login from '../../Component/Client/Login';
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import ClientManagement from '../../Component/Client/ClientManagement';
import ClientProfile from '../../Component/Client/ClientProfile';
import Signin from '../../Component/Client/Signin';
import ClientUpdate from '../../Component/Client/ClientUpdate';
import Home from '../../Component/Client/Home';
import Cards from '../../Component/Client/Card/Cards';
import Address from '../../Component/Client/Address/Address';
import AddressUpdate from '../../Component/Client/Address/AddressUpdate';


function RoutesService() {
  return (
    <BrowserRouter>
      <Routes >

        <Route path="/" element={<Home />} />

        <Route path="/login" element={<Login />} />

        <Route path="/signin" element={<Signin />} />

        <Route path="/user_home" element={<ClientManagement />} />

        <Route path="/client_profile" element={<ClientProfile />} />

        <Route path="/client_update" element={<ClientUpdate />} />

        <Route path="/client_cards" element={<Cards />} />

        <Route path="/client_adresses" element={<Address />} />

        <Route path="/client_adresses/update/:id" element={<AddressUpdate />}></Route >



      </Routes>
    </BrowserRouter>
  )
}

export default RoutesService;