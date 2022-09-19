
import React from 'react'
import { Link } from 'react-router-dom';
import LocalStorageService from '../../../service/config/LocalStorageService';
import ProductService from '../../../service/Product/ProductService';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const Cart = () => {

  const [cart, setCart] = React.useState([]);

  const productService = new ProductService;

  const getCartData = () => {
    const cartDataFromLocalStorage = LocalStorageService.getCart();

    const promises = Promise.all(cartDataFromLocalStorage.map((item) => productService.getById(item.id)))

    promises.then((values) => {
      const newCartItems = values.map(({ data }) => {
        return {
          id: data.id,
          stock: data.stock,
          name: data.name,
          description: data.productDescription,
          price: data.price,
          category: data.category,
          family: data.instrumentFamily,
          brand: data.instrumentBrand,
          quantity: cartDataFromLocalStorage.find(d => parseInt(d.id) === data.id)?.quantity
        }
      })

      setCart(newCartItems)
    }).catch(console.error)
  }

  React.useEffect(() => {
    getCartData();
  }, [])

  console.log("CART'", cart);

  const deleteCartItem = () => {
    alert("AAAAAAAAAAAAAAAAAAA")
  }

  return (
    <>
      <div className="card p-5 d-flex flex-row justify-content-center flex-wrap">
        <ToastContainer />

        <div className="container">
        </div>
        {cart?.length === 0 && <h1>Sem produtos</h1>}
        {cart?.map((item) => (
          <div key={item.id} className="card m-3">
            {/* {console.log('PRODUCTS DENTRO DO MAP', item)} */}
            <div className="card-body" >
              <ul className="list-group list-group-flush">
                <li className="list-group-item">Nome: {item.name}</li>
                <li className="list-group-item">Preço: R$ {item.price}</li>
                <li className="list-group-item">Estoque: {item.stock}</li>
                <li className="list-group-item">Quantidade: {item.quantity}</li>

              </ul>
            </div>

            <div className="card-footer d-flex justify-content-center">
              {<button
                type="button"
                className="btn btn-danger rainbow-bg"
                onClick={e => deleteCartItem(cart.id)}
              >
                Excluir
              </button>}
            </div>
          </div>
        ))}
      </div>
      <a href="/user_home" className="btn btn-outline-secondary mb-2 " >Voltar</a>
    </>
  )
}

export default Cart