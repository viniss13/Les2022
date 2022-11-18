import React from 'react'
import LocalStorageService from '../../service/config/LocalStorageService';
import OrderService from '../../service/Order/OrderService';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { Link } from 'react-router-dom';
const orderService = new OrderService();

const Orders = () => {

  const [orders, setOrders] = React.useState(null);

  const client_id = parseInt(LocalStorageService.obterItem("_logged_user").entities[0].id);
  console.log(client_id);

  const getAllOrders = () => {
    orderService.getClientOrders(client_id)
      .then(response => {
        console.log(response.data.entities)
        setOrders(response.data.entities)

      }).catch(err => {

      })
  }

  React.useEffect(() => {
    getAllOrders();
  }, [])


  return (
    <>
      <div className="card p-5 d-flex flex-row justify-content-center flex-wrap">
        <ToastContainer />
        <div className="container  p-5 d-flex flex-row justify-content-center flex-wrap ">
          <h1>Pedidos</h1>
        </div>
        {orders?.length === 0 && <h1>Sem items</h1>}
        {orders?.map((order) => (
          <div key={order.id} className="card m-3">
            <div className="card-body" >
              <ul className="list-group list-group-flush">
                <li className="list-group-item">Id do pedido: {order.id}</li>

                <li className="list-group-item">Status: {
                  <p className={{
                    'DRAFT': 'text-secondary',
                    'EM_ANALISE': 'text-info',
                    'EM_TRANSPORTE': 'text-info',
                    'PAGAMENTO_REALIZADO': 'text-info',
                    'ENTREGA_REALIZADA': 'text-success',
                    'RECUSADO': 'text-danger',
                    'FINALIZADO': 'text-success'
                  }[order.status]}>{order.status}
                  </p>}
                </li>
                <li className="list-group-item">Valor total: R$ {order.order_value}</li>
                <Link className="btn btn-danger rainbow-bg mx-2" to={`details/${order.id}`} key={order.id} >
                  Detalhes
                </Link>
              </ul>
            </div>
          </div>
        ))}
      </div>
    </>
  )
}

export default Orders