import React from 'react'
import CartService from '../../service/Cart/CartService';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import OrderService from '../../service/Order/OrderService';
import { useNavigate } from 'react-router-dom';
import LocalStorageService from '../../service/config/LocalStorageService';

const cartService = new CartService();

const orderService = new OrderService();
const OrderSummary = () => {

  const [order, setOrder] = React.useState(null);
  const [cart, setCart] = React.useState(null);
  const [card, setCard] = React.useState(null);
  const [address, setAddress] = React.useState(null);
  const [coupon, setCoupon] = React.useState(null);
  const navigate = useNavigate();

  const getOrderData = () => {

    const client_id = parseInt(LocalStorageService.obterItem("_logged_user").entities[0].id);
    console.log(client_id);

    orderService.read_draft(client_id)
      .then(response => {
        toast.success("Pedido pego com sucesso!");
        console.log("ORDERaaaaaaaaaa", response.data.entities[0].cart.items);
        setOrder(response.data.entities[0]);
        setCart(response.data.entities[0].cart.items);
        setCard(response.data.entities[0].card);
        setAddress(response.data.entities[0].address);
        setCoupon(response.data.entities[0].coupon);

      }).catch(error => {

        toast.error("Não foi possível executar o comando");
        console.log("Error", error);
      })
  }

  React.useEffect(() => {
    getOrderData();
  }, [])

  const selectAddress = () => {
    navigate("/select_address")
  }

  const selectCard = () => {
    navigate("/select_card")
  }

  return (
    <>
      <div className="card p-5 d-flex flex-row justify-content-center flex-wrap">
        <ToastContainer />
        <div className="container  p-5 d-flex flex-row justify-content-center flex-wrap ">
          <h1>Itens do Pedido</h1>
        </div>
        {cart?.length === 0 && <h1>Sem items</h1>}
        {cart?.map((item) => (
          <div key={item.product.id} className="card m-3">
            {console.log('CARDS DENTRO DO MAP', item.product)}
            <div className="card-body" >
              <ul className="list-group list-group-flush">
                <li className="list-group-item">Nome: {item.product.name}</li>
                <li className="list-group-item">Preço: R$ {item.product.price}</li>
                <li className="list-group-item">Estoque: {item.product.stock}</li>
                <li className="list-group-item">Quantidade: {item.quantity}</li>
              </ul>
            </div>


          </div>
        ))}
      </div>

      {/* Fim itens */}

      <div className="container p-5 d-flex flex-row justify-content-center flex-wrap ">
      {address != null &&
        <div key={address.id} className="card m-3">
          <div className="card-body" >
          <h3>Endereço selecionado</h3>
            <ul className="list-group list-group-flush">
              <li className="list-group-item">Logradouro: {address.logradouro}</li>
              <li className="list-group-item">Tipo Logradouro: {address.residencyType}</li>
              <li className="list-group-item">Número: {address.number}</li>
              <li className="list-group-item">CEP: {address.zipCode}</li>
              <li className="list-group-item">Observação: {address.observation}</li>
              <button onClick={selectAddress} className="btn btn-warning">Alterar Seleção</button>
            </ul>
          </div>
        </div>
         }
         {card != null &&
        <div key={card.id} className="card m-3">
            <div className="card-body" >
            <h3>Cartão selecionado</h3>
              <ul className="list-group list-group-flush">
                <li className="list-group-item">Apelido: {card.alias}</li>
                <li className="list-group-item">Bandeira: {card.flag}</li>
                <li className="list-group-item">Número: {card.number}</li>
                <button onClick={selectCard} className="btn btn-warning">Alterar Seleção</button>
              </ul>
            </div>
      </div>
  }

      </div>
      {/* Fim Endereço */}


    </>
  )
}

export default OrderSummary