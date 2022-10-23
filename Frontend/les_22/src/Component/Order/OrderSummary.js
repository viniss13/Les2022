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
  const [code, setCode] = React.useState('');
  const [total_value, setTotalValue] = React.useState(0);
  const [coupon_value, setCouponValue] = React.useState(0);
  const [client_id, setClientId] = React.useState('');
  const [couponDiscount, setDiscount] = React.useState(0);
  const [couponCode, setCouponCode] = React.useState('');

  const navigate = useNavigate();

  const getOrderData = () => {

    const client_id = parseInt(LocalStorageService.obterItem("_logged_user").entities[0].id);
    setClientId(client_id);
    console.log(client_id);

    orderService.read_draft(client_id)
      .then(response => {
        console.log("ORDERaaaaaaaaaa", response.data.entities[0].cart.total_value);
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

        // toast.error("Não foi possível executar o comando");
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

  const verifyCoupon = () => {
    orderService.addCoupon({ coupon_code: code, client_id: client_id })
      .then(response => {
        console.log('bbbbbbbbbbbbbbbbbbbb', response);
        const qtdMsg = response.data.msg.length;
        if (qtdMsg === 0) {

          console.log(qtdMsg);
          console.log('RESPONSEDATRA', response.data)
          setDiscount(response.data.entities[0].coupon.coupon_value);
          getOrderData();
          toast.success("Cupom validado com sucesso!");

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
      }).catch(err => {
        alert('aaaaaaaaaaaaaaaaaaaaaa', err)
      })
  }

  const sendOrder = () => {
    orderService.sendOrder({ id: order.id, status: 'EM_ANALISE' })
      .then(response => {

        const qtdMsg = response.data.msg.length;
        if (qtdMsg === 0) {

          console.log(qtdMsg);
          console.log('RESPONSEDATRA', response.data);
          toast.success("Pedido realizado com sucesso!");

          setTimeout(() => {
            navigate("/orders");
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
            {console.log('CARDS DENTRO DO MAP', item.product)}
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

      {/* Fim itens */}

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
                <button onClick={selectAddress} className="btn btn-warning">Alterar Seleção</button>
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
                <button onClick={selectCard} className="btn btn-warning">Alterar Seleção</button>
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
      {/* Fim Endereço */}


    </>
  )
}

export default OrderSummary