import React, { useState } from 'react'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import CartService from '../../service/Cart/CartService';
import LocalStorageService from '../../service/config/LocalStorageService';

const cartService = new CartService();

const Cart = () => {

  let quantities = [];
  let [cart, setCart] = React.useState(null);
  let [quantity, setQuantity] = React.useState(0)
  const navigate = useNavigate();

  const getCartData = () => {

    const client_id = parseInt(LocalStorageService.obterItem("_logged_user").entities[0].id);
    console.log(client_id);

    cartService.read(client_id)
      .then(response => {
        console.log('aaaaaaa', response.data.entities);
        setCart(response.data.entities);

        let myQuantities = response.data.entities;

        for (let i = 0; i < myQuantities.length; i++) {
        //  updateQuantities(myQuantities[i].id, myQuantities[i].quantity);
        }

      }).catch(error => {

        toast.error("Não foi possível executar o comando");
        console.log("Error", error);
      })
  }

  React.useEffect(() => {
    getCartData();
  }, [])

  const updateQuantities = (id, myQuantity) => {

    myQuantity = Number(myQuantity);

    let myCart = cart;

    for (let i = 0; i < myCart.length; i++) {
      if (myCart[i].id == id) {
        if (myQuantity <= 0 || myQuantity > myCart[i].product.stock) {
          toast.error("Quantidade inválida!");
          return;
        }

        const client_id = LocalStorageService.obterItem("_logged_user").entities[0].id;
        const product_id = myCart[i].product.id;

        let item = {};
        item.product_id = product_id;
        item.quantity = myQuantity;
        item.client_id = client_id;

        let qtdMsg = 0;

        cartService.update(item)
          .then(response => {

            qtdMsg = response.data.msg.length;

            console.log("QUANTIDADES STRATEGY", qtdMsg);


            if (qtdMsg === 0) {

              toast.success('Adicionado com sucesso!');

              let myCart = cart;
    
        for(let i = 0; i < myCart.length; i++){
          if(myCart[i].id == id){
            myCart[i].quantity = myQuantity;
            //myCart[i].quantity1 = Number(quantity_id);
          }
        }
    
        getCartData();

             // setTimeout(() => {
            //    navigate("/products")
            //  }, "2000");
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

          }).catch(error => {

            toast.error('Erro ao adicionar');
          })
      }
    }
  }


  const deleteCart = (id) => {
    cartService.delete_item(id)
      .then(() => {

        toast.success("Deletado com sucesso!");
        getCartData();
      })
      .catch(error => { })
  }

  const selectOrderAddress = () => {
    navigate("/select_address");
  }

  const changeValue = (value, id) => {
    console.log('value', value)
    console.log('id', id)
  }

  return (
    <>
      <div className="card p-5 d-flex flex-row justify-content-center flex-wrap">
        <ToastContainer />

        {cart?.length === 0 && <h1>Sem items</h1>}
        {cart?.map((item) => (
          <div key={item.product.id} className="card m-3">
            <div className="card-body" >
              <ul className="list-group list-group-flush">
              <li className="list-group-item"> <img src={item.product.imageUrl} width="250" height="250" style={{alignItems: "center", justifyContent: "center"}} /> </li>
                <li className="list-group-item">Nome: {item.product.name}</li>
                <li className="list-group-item">Preço: R$ {item.product.price}</li>
                <li className="list-group-item">Estoque: {item.product.stock}</li>
                <li className="list-group-item">Quantidade: {item.quantity}
                </li>
                <li>
                  <input
                    id="quantity"
                    className="card-text rounded-pill border border-2 border-dark"
                    name="quantity"
                    placeholder="0"
                    onChange={(e) => updateQuantities(item.id, e.target.value)}
                  />
                </li>
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

      <div className="container justify-content-md-right">
        <button onClick={selectOrderAddress} className="btn btn-primary">Selecionar endereço</button>
      </div>
      <a href="/user_home" className="btn btn-outline-secondary mb-2 " >Voltar</a>

    </>
  )
}

export default Cart