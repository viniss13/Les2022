import React from 'react'
import CartService from '../../service/Cart/CartService';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import OrderService from '../../service/Order/OrderService';
import { Link, useNavigate } from 'react-router-dom';
import LocalStorageService from '../../service/config/LocalStorageService';
import CardService from '../../service/Card/CardService';
import RequestCardService from '../../service/Order/RequestCardService';


const cartService = new CartService();
const cardService = new CardService();


const orderService = new OrderService();
const requestCardService = new RequestCardService();
const OrderSummary = () => {

  const [order, setOrder] = React.useState(null);
  const [cart, setCart] = React.useState(null);
  const [card, setCard] = React.useState(null);
  const [address, setAddress] = React.useState(null);
  const [coupon, setCoupon] = React.useState(null);
  const [code, setCode] = React.useState('');
  const [total_value, setTotalValue] = React.useState(0);
  const [coupon_value, setCouponValue] = React.useState(0);
  const [client_id, setClientId] = React.useState('');
  const [couponDiscount, setDiscount] = React.useState(0);
  const [couponCode, setCouponCode] = React.useState('');
  const [cards, setCards] = React.useState(null);
  let [cardValues, setCardValues] = React.useState({});

  let testee = "";

  // let cardValues = {};

  const navigate = useNavigate();

  const getOrderData = () => {

    const client_id = parseInt(LocalStorageService.obterItem("_logged_user").entities[0].id);
    setClientId(client_id);

    orderService.read_draft(client_id)
      .then(response => {
        setOrder(response.data.entities[0]);
        setCart(response.data.entities[0].cart.items);
        setCard(response.data.entities[0].card);
        setAddress(response.data.entities[0].address);
        setTotalValue(response.data.entities[0].cart.total_value);
        setCouponValue(response.data.entities[0].coupon.coupon_value);
        // setCouponValue(response.data.entities[0].)
        setCoupon(response.data.entities[0].coupon);
        setCouponCode(response.data.entities[0].coupon.code);

      }).catch(error => {
        // toast.error("N??o foi poss??vel executar o comando");
      }).finally(final => {
        getAllCards();
      })
  }

  React.useEffect(() => {
    console.log("cardvalues", cardValues);
  }, [cardValues])

  React.useEffect(() => {
    getOrderData();
  }, [])

  const selectAddress = () => {
    navigate("/select_address")
  }

  const selectCard = () => {
    navigate("/select_card")
  }

  const getAllCards = () => {
    const userData = LocalStorageService.obterItem("_logged_user");
    const id = userData.entities[0].id;

    cardService.getAllcards(id)
      .then(response => {
        setCards(response.data.entities);

        let cards = response.data.entities;

        let myCardValues = {};

        for (let i = 0; i < cards.length; i++) {
          myCardValues[cards[i].id] = 0;
        }

        console.log("ihuuuu", myCardValues);
        setCardValues(myCardValues);


      }).catch(error => {
        console.error("erro recuperando cart??es do usu??rio", id, error)
      })
  }

  const verifyCoupon = () => {
    orderService.addCoupon({ coupon_code: code, client_id: client_id })
      .then(response => {
        const qtdMsg = response.data.msg.length;
        if (qtdMsg === 0) {

          setDiscount(response.data.entities[0].coupon.coupon_value);
          getOrderData();
          toast.success("Cupom validado com sucesso!");

        } else {
          let messages = response.data.msg;
          for (let i = 0; i < messages.length; i++) {
            let msgs = messages[i].split("\n");
            for (let message in msgs) {
              toast.error(msgs[message]);
            }
          }
        }
      }).catch(err => {
        alert('aaaaaaaaaaaaaaaaaaaaaa', err)
      })
  }

  const addCard = (request) => {
    requestCardService.addCard(request)
      .then(response => {

      }).catch(err => {

      })
  }

  const setCardValue = (cardID, value) => {

    value = Number(value);

    console.log(value);

    if (value < 0) {
      toast.error("Valor inv??lido");
      value = 0;
    }

    let requestCard = {};


    requestCard.value = value;
    requestCard.card_id = cardID;
    requestCard.order_id = order.id;

    addCard(requestCard);

    console.log(requestCard);

    return value;

  }

  const sendOrder = () => {
    orderService.sendOrder({ id: order.id, status: 'EM_ANALISE' })
      .then(response => {

        const qtdMsg = response.data.msg.length;
        if (qtdMsg === 0) {

          toast.success("Pedido realizado com sucesso!");

          setTimeout(() => {
            navigate("/orders");
          }, "1000");

        } else {
          let messages = response.data.msg;
          for (let i = 0; i < messages.length; i++) {
            let msgs = messages[i].split("\n");
            for (let message in msgs) {
              toast.error(msgs[message]);
            }
          }
        }

      }).catch(err => {
        toast.error(err)
      })
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
            <div className="card-body" >
              <ul className="list-group list-group-flush">
                <li className="list-group-item"> <img src={item.product.imageUrl} width="250" height="250" style={{ alignItems: "center", justifyContent: "center" }} /> </li>
                <li className="list-group-item">Nome: {item.product.name}</li>
                <li className="list-group-item">Pre??o: R$ {item.product.price}</li>
                <li className="list-group-item">Quantidade: {item.quantity}</li>
                <li className="list-group-item">Pre??o Total: R$ {item.total_value}</li>
              </ul>
            </div>


          </div>
        ))}
      </div>

      {/* Fim itens */}

      <div className="card p-5 d-flex flex-row justify-content-center flex-wrap">

        <div className="container  p-5 d-flex flex-row justify-content-center flex-wrap ">
          <h1>Cart??es</h1>
        </div>
        <div className="container">
          <Link className="btn btn-primary rainbow-bg " to={"/create_card"}>Cadastrar Cart??o</Link>
        </div>
        {cards?.length === 0 && <h1>Sem cart??es</h1>}
        {cards?.map((card, index) => (
          <div key={card.id + 'c'} className="card m-3">

            <div className="card-body" >
              <ul className="list-group list-group-flush">
                <li className="list-group-item">Apelido: {card.alias}</li>
                <li className="list-group-item">Bandeira: {card.flag}</li>
                <li className="list-group-item">N??mero: {card.number}</li>
              </ul>
              <input
                type="number"
                className="form-control"
                value={cards.gambi}
                // defaultValue={cardValues[card.id]}
                //defaultValue={0}

                onBlur={(e) => e.target.value = setCardValue(card.id, e.target.value)}
              // onChange={(e) => setCardValue(card.id, e.target.value)}
              />

            </div>
          </div>
        ))}
      </div>

      {/* Fim cartao list */}

      <div className="container p-5 d-flex flex-row justify-content-center flex-wrap ">
        {address != null &&
          <div className="card m-3">
            <div className="card-body" >
              <h3>Endere??o Selecionado</h3>
              <ul className="list-group list-group-flush">
                <li className="list-group-item">Logradouro: {address.logradouro}</li>
                <li className="list-group-item">Tipo Logradouro: {address.residencyType}</li>
                <li className="list-group-item">N??mero: {address.number}</li>
                <li className="list-group-item">CEP: {address.zipCode}</li>
                <li className="list-group-item">Observa????o: {address.observation}</li>
                <button onClick={selectAddress} className="btn btn-warning">Alterar Sele????o</button>
              </ul>
            </div>
          </div>
        }

        {cart != null && //espera os atributos carregarem
          <div className="row">
            <div className="container p-5 d-flex flex-row justify-content-center flex-wrap ">
              <h5 className="constrol-label mx-2">Cupom</h5>
              <div className="card-body col-6">
                <input
                  className="form-control border border-primary"
                  id="code"
                  name="code"
                  value={code}
                  placeholder={couponCode}
                  onChange={(e) => setCode(e.target.value)}>
                </input>

                <button onClick={verifyCoupon} className="btn mt-2" style={{ background: '#20c997', color: 'white' }}>Validar Cupom</button>
                <h4 className="mt-5">Valor da Compra: R$ {total_value}</h4>
                <h4 className="mt-5">Desconto do Cupom: R$ {coupon_value}</h4>
                <h4 className="mt-5">Total: R$ {total_value - coupon_value}</h4>
              </div>
            </div>
          </div>
        }
        <div className="card-body" >
          <button className="btn btn-success" onClick={sendOrder}>Confirmar</button>
        </div>
      </div>
      {/* Fim Endere??o */}


    </>
  )
}

export default OrderSummary