import React from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import OrderService from '../../service/Order/OrderService';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


const OrderDetails = () => {

  const navigate = useNavigate();

  const params = useParams();
  const [orderData, setOrderData] = React.useState(null);

  const [order, setOrder] = React.useState(null);
  const [cart, setCart] = React.useState(null);
  const [card, setCard] = React.useState(null);
  const [address, setAddress] = React.useState(null);
  const [coupon, setCoupon] = React.useState(null);
  const [code, setCode] = React.useState('');
  const [cart_value, setCartValue] = React.useState(0);
  const [order_value, setOrderValue] = React.useState(0);
  const [coupon_value, setCouponValue] = React.useState(0);
  const [client_id, setClientId] = React.useState('');
  const [couponDiscount, setDiscount] = React.useState(0);
  const [couponCode, setCouponCode] = React.useState(null);
  const [status, setStatus] = React.useState(null);
  const [exchanges, setExchanges] = React.useState(null);
  const [cards, setCards] = React.useState(null);
  const orderService = new OrderService();

  console.log(params);

  const orderDetails = () => {
    orderService.getOrderDetails(params.id)
      .then(response => {
        console.log("orderrr", response.data);
        setOrder(response.data);
        setCart(response.data.cart.items);
        setCard(response.data.card);
        setAddress(response.data.address);
        setCartValue(response.data.cart.total_value);
        setExchanges(response.data.exchanges);
        setCards(response.data.cards);
        console.log("MEU PEDIDO", response.data.exchanges);
        if (response.data.coupon != null) {
          setCouponValue(response.data.coupon.coupon_value);
          setCoupon(response.data.coupon);
          setCouponCode(response.data.coupon.code);
        }
        setStatus(response.data.status);
        // setCouponValue(response.data.entities[0].)

        setOrderValue(response.data.order_value)
      })
  }

  React.useEffect(() => {
    orderDetails();
  }, [])


  const exchange = () => {
    navigate(`/exchange/${params.id}`)
  }

  return (
    <>

      <div className="card p-5 d-flex flex-row justify-content-center flex-wrap">
        <ToastContainer />
        <div className="container  p-5 d-flex flex-row justify-content-center flex-wrap ">
          <h1>Itens do Pedido</h1>
        </div>
        {cart?.length === 0 && <div></div>}
        {cart?.map((item) => (
          <div key={item.product.id} className="card m-3">
            {console.log('CARDS DENTRO DO MAP', item.product)}
            <div className="card-body" >
              <ul className="list-group list-group-flush">
                <li className="list-group-item"> <img src={item.product.imageUrl} width="250" height="250" style={{ alignItems: "center", justifyContent: "center" }} /> </li>
                <li className="list-group-item">Nome: {item.product.name}</li>
                <li className="list-group-item">Preço: R$ {item.product.price}</li>
                <li className="list-group-item">Quantidade: {item.quantity}</li>
                <li className="list-group-item">Preço Total: R$ {item.total_value}</li>
              </ul>
            </div>
          </div>
        ))}
      </div>

      {/* Fim itens */}

      <div className="card p-5 d-flex flex-row justify-content-center flex-wrap">

        {exchanges?.length !== 0 &&
          <div className="container  p-5 d-flex flex-row justify-content-center flex-wrap ">
            <h1>Itens para Troca</h1>
          </div>
        }

        {exchanges?.map((item) => (
          <div key={item.product.id} className="card m-3">
            <div className="card-body" >
              <ul className="list-group list-group-flush">
                <li className="list-group-item">Nome: {item.product.name}</li>
                <li className="list-group-item">Preço: R$ {item.product.price}</li>
                <li className="list-group-item">Quantidade: {item.quantity}</li>
                <li className="list-group-item">Preço Total: R$ {item.total_value}</li>
              </ul>
            </div>
          </div>
        ))}
      </div>
      {/* Fim pedidos de troca */}

      <div className="card p-5 d-flex flex-row justify-content-center flex-wrap">

        {cards?.length === 0 && <h1>Sem cartões</h1>}
        {cards?.map((card, index) => (
          <div key={card.id + 'c'} className="card m-3">

            <div className="card-body" >
              <ul className="list-group list-group-flush">
                <li className="list-group-item">Apelido: {card.card.alias}</li>
                <li className="list-group-item">Bandeira: {card.card.flag}</li>
                <li className="list-group-item">Número: {card.card.number}</li>
                <li className="list-group-item">Valor: R${card.buyingValue}</li>
              </ul>
            </div>
          </div>
        ))}
      </div>

      {/* Fim cartao list */}

      <div className="container p-5 d-flex flex-row justify-content-center flex-wrap ">
        {address != null &&
          <div className="card m-3">
            <div className="card-body" >
              <h3>Endereço Selecionado</h3>
              <ul className="list-group list-group-flush">
                <li className="list-group-item">Logradouro: {address.logradouro}</li>
                <li className="list-group-item">Tipo Logradouro: {address.residencyType}</li>
                <li className="list-group-item">Número: {address.number}</li>
                <li className="list-group-item">CEP: {address.zipCode}</li>
                <li className="list-group-item">Observação: {address.observation}</li>

              </ul>
            </div>
          </div>
        }
        {card != null &&
          <div key={card.id} className="card m-3">
            <div className="card-body" >
              <h3>Cartão Selecionado</h3>
              <ul className="list-group list-group-flush">
                <li className="list-group-item">Apelido: {card.alias}</li>
                <li className="list-group-item">Bandeira: {card.flag}</li>
                <li className="list-group-item">Número: {card.number}</li>

              </ul>
            </div>
          </div>
        }
        {cart != null && //espera os atributos carregarem
          <div className="row">
            <div className="container p-5 d-flex flex-row justify-content-center flex-wrap ">
              <div className="card-body col-6">
                <h4 className="mt-4 mt-2">Status: {
                  <p className={{
                    'DRAFT': 'text-secondary',
                    'EM_ANALISE': 'text-info',
                    'EM_TRANSPORTE': 'text-info',
                    'PAGAMENTO_REALIZADO': 'text-info',
                    'ENTREGA_REALIZADA': 'text-success',
                    'RECUSADO': 'text-danger',
                    'FINALIZADO': 'text-success'
                  }[status]}>{status}
                  </p>}
                </h4>
                {coupon != null &&
                  <>
                    <h4 className="mt-5">Valor da Compra: R$ {cart_value}</h4>
                    <h4 className="mt-5">Cupom utilizado: R$ {couponCode}</h4>
                    <h4 className="mt-5">Desconto do Cupom: R$ {coupon_value}</h4>
                  </>
                }

                <h4 className="mt-5">Total: R$ {order_value}</h4>


                {status === "ENTREGA_REALIZADA" &&
                  <div className="flex-wrap mt-4">
                    <button
                      className="btn btn-warning text-black"
                      onClick={exchange}
                    >Requisitar Troca</button>
                  </div>
                }

              </div>
            </div>
          </div>
        }
      </div>
      {/* Fim Endereço */}

    </>
  )
}

export default OrderDetails