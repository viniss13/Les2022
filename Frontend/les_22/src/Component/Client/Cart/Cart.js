import React, { useState } from 'react'
import CartService from '../../../service/Cart/CartService';
import LocalStorageService from '../../../service/config/LocalStorageService';
import { ToastContainer, toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { Link } from 'react-router-dom';

const cartService = new CartService();

const Cart = () => {

  const [cart, setCart] = React.useState(null);

  
  const getCartData = () => {

    const client_id = parseInt(LocalStorageService.obterItem("_logged_user").entities[0].id);
    console.log(client_id);

    cartService.read(client_id)
      .then(response => {

        setCart(response.data.entities);

      }).catch(error => {

        toast.error("Não foi possível executar o comando");
        console.log("Error", error);
      })
  }

  React.useEffect(() => {
    getCartData();
  }, [])


  const deleteCart = (id) => {
    cartService.delete_item(id)
     .then( () =>{

      toast.success("Deletado com sucesso!");
      getCartData();
     }) 
     .catch(error => {})
  }
  return (
    <>
      <div className="card p-5 d-flex flex-row justify-content-center flex-wrap">
        <ToastContainer />

        <div className="container">
        
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

            <div className="card-footer d-flex justify-content-center">
            <button
              type="button"
              className="btn btn-danger rainbow-bg"
              onClick={e => deleteCart(item.id)}>
              Excluir
            </button>

            </div>
          </div>
        ))}
      </div>

      <a href="/user_home" className="btn btn-outline-secondary mb-2 " >Voltar</a>

     
    </>
  )
}

export default Cart