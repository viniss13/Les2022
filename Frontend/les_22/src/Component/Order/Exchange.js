import React from 'react'
import { useParams } from 'react-router-dom';
import OrderService from '../../service/Order/OrderService';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import ExchangeService from '../../service/Order/ExchangeService';
import { useNavigate } from 'react-router-dom';

const orderService = new OrderService();
const exchangeService = new ExchangeService();

const Exchange = () => {

  const navigate = useNavigate();

  const params = useParams();
  
  const [items, setItems] = React.useState([]);

  const [ number, setNumber] = React.useState(0);

  
  React.useEffect( () => {
    getOrder();
  },[])

  const sendExchange = () => {
    console.log(items);
    let myExchanges = [];

    for(let i = 0; i < items.length; i++){
      let myExchange = {};

      if(items[i].exchange_quantity != 0){
        myExchange.product_id = items[i].product.id;
        myExchange.quantity = items[i].exchange_quantity;
        myExchange.order_id = Number(params.id);
        myExchanges.push(myExchange);
      }
      
    }

    if(myExchanges.length > 0){
      toast.success("Troca requisitada com sucesso!");

      exchangeService.createExchange(myExchanges)
        .then( response => {

          toast.success("Pedido de troca enviado!")
          
          setTimeout(() => {
            navigate(`/orders/details/${params.id}`);
          }, "1000");          

        }).catch( err => {

        })
      
      console.log("Minha troca", myExchanges);
    } else {
      toast.error("É necessário selecionar ao menos um item");
    }
  }

  const setExchangeQuantity = (exchange_quantity, item_id) =>{
    exchange_quantity = Number(exchange_quantity);
    let quantity = 0;
    let myItems = items;
    for(let i = 0; i < myItems.length; i++){
        if(myItems[i].id == item_id){

            if(!(exchange_quantity > myItems[i].quantity || exchange_quantity < 0)){
              myItems[i].exchange_quantity = exchange_quantity;
              quantity = exchange_quantity;
            } else {
              quantity = myItems[i].exchange_quantity;
            }
          
        } 
    }

    setItems(myItems);

   console.log("Meus itens:", items);

   return quantity;
  }

  const getOrder = () =>{
    orderService.getOrderDetails(params.id)
      .then( response => {
        let myItems = response.data.cart.items;

        for(let i = 0; i < myItems.length; i++){
            myItems[i].exchange_quantity = 0;
        }
        setItems(myItems);
      })
  }
  return (
    <>
      <div className="card p-5 d-flex flex-row justify-content-center flex-wrap">
      <ToastContainer />

        {items?.map((item) => (
          <div key={item.product.id} className="card m-3">
            <div className="card-body" >
              <ul className="list-group list-group-flush">
                <li className="list-group-item">Nome: {item.product.name}</li>
                <li className="list-group-item">Preço: R$ {item.product.price}</li>
                <li className="list-group-item">Quantidade: {item.quantity}</li>
                <li className="list-group-item">Preço Total: R$ {item.total_value}</li>

                <input 
                  id="number" 
                  type="number" 
                  placeholder="0"
                  max= {item.quantity}
                  value= {item.exchange_quantity1}
                  onBlur={(e) => 
                    e.target.value = setExchangeQuantity( e.target.value, item.id) 
                    } 
                    />

                  </ul>
            </div>
          </div>
        ))}

          <div className="col-12 flex-row justify-content-center flex-wrap">
              <div className="form-group">
                <button 
                  className="btn btn-success"
                  onClick={sendExchange}
                  >Confirmar troca</button>
              </div>
          </div>

        </div>

    </>
  )
}

export default Exchange