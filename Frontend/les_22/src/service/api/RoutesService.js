
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
import AddressCreate from '../../Component/Client/Address/AddressCreate';
import CardCreate from '../../Component/Client/Card/CardCreate';
import Products from '../../Component/Product/Products';
import ProductDetails from '../../Component/Product/ProductDetails';
import Cart from '../../Component/Order/Cart';
import SelectAddress from '../../Component/Order/SelectAddress';
import SelectCard from '../../Component/Order/SelectCard';
import OrderSummary from '../../Component/Order/OrderSummary';
import Orders from '../../Component/Order/Orders';
import OrderDetails from '../../Component/Order/OrderDetails';
import AdmManagement from '../../Component/Adm/AdmManagement';
import AdmOrders from '../../Component/Adm/AdmOrders';
import AdmOrderDetails from '../../Component/Adm/AdmOrderDetails';



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

        <Route path="/create_card" element={<CardCreate />} />

        <Route path="/create_address" element={<AddressCreate />} />

        <Route path="/client_adresses" element={<Address />} />

        <Route path="/client_adresses/update/:id" element={<AddressUpdate />} />

        <Route path="/products" element={<Products />} />

        <Route path="/products/details/:id" element={<ProductDetails />} />

        <Route path="/cart" element={<Cart />} />

        <Route path="/select_address" element={<SelectAddress />} />

        <Route path="/select_card" element={<SelectCard />} />

        <Route path="/order_summary" element={<OrderSummary />} />

        <Route path="/orders" element={<Orders />} />

        <Route path="/orders/details/:id" element={<OrderDetails />} />

        <Route path="/admin" element={<AdmManagement />} />

        <Route path="/adm_orders" element={<AdmOrders />} />

        <Route path="/adm_orders/details/:id" element={<AdmOrderDetails />} />


      </Routes>
    </BrowserRouter>
  )
}

export default RoutesService;