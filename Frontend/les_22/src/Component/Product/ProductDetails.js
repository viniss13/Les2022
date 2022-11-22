import React, { useState } from 'react'
import { useParams } from 'react-router-dom';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import CartService from '../../service/Cart/CartService';
import LocalStorageService from '../../service/config/LocalStorageService';
import ProductService from '../../service/Product/ProductService';
import { Link, Navigate, useNavigate } from 'react-router-dom';

const productService = new ProductService();
const cartService = new CartService();

const ProductDetails = () => {

  const [product, setProduct] = React.useState(null);

  const [stock, setStock] = React.useState(0);
  const [name, setName] = React.useState("");
  const [description, setDescription] = React.useState("");
  const [price, setPrice] = React.useState(0.0);
  const [category, setCategory] = React.useState("");
  const [family, setFamily] = React.useState("");
  const [brand, setBrand] = React.useState("");
  const [imageUrl, setImage] = React.useState("");

  // const [quantity, dispatch] = React.useReducer(reducer, 0);

  let [ quantity, setquantity] = React.useState(0);

  const navigate = useNavigate();

  const params = useParams();

  console.log(params);


  const getProduct = () => {
    productService.getById(params.id)
      .then(response => {
        console.log('RESPONSE', response)

        setImage(response.data.imageUrl);
        setStock(response.data.stock);
        setName(response.data.name);
        setDescription(response.data.productDescription);
        setPrice(response.data.price);
        setCategory(response.data.category);
        setFamily(response.data.instrumentFamily);
        setBrand(response.data.instrumentBrand);
      })
  }

  React.useEffect(() => {
    getProduct();
  })

  React.useEffect(() => {

    let myQuantity = Number(quantity);

    console.log("MEU NUMBER", myQuantity);

    if(isNaN(myQuantity)){
      setquantity(0);
      toast.error("Quantidade inválida!");
    }
    else if(quantity < 0 ){
      setquantity(0);
      toast.error("Quantidade inválida!");
    }else if(quantity > stock){
      setquantity(stock);
      toast.error("Quantidade inválida!");
    }

  },[quantity])


  const addToCart = () => {
    if(quantity <= 0 || quantity > stock){
      toast.error("Quantidade inválida!");
      return;
    }

    const client_id = LocalStorageService.obterItem("_logged_user").entities[0].id;
    const product_id = parseInt(params.id);

    let item = {};
    item.product_id = product_id;
    item.quantity = quantity;    
    item.client_id = client_id;

    let qtdMsg = 0;

    cartService.update(item)
    .then(response => {      
      
      qtdMsg = response.data.msg.length;

        console.log("QUANTIDADES STRATEGY", qtdMsg);
        

        if (qtdMsg === 0) {

          toast.success('Adicionado com sucesso!');

          setTimeout(() => {
            navigate("/products")
          }, "2000");
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

    }).catch( error =>{

      toast.error('Erro ao adicionar');
    })
   
  }

  const changeValue = (value) =>{
    setquantity( Number(value) );
  }

  return (
    <>
        <div className="container">
          <div className="row">
          <h1 className="mt-4 mb-3">Instrumento</h1>
          <hr />         

          <ToastContainer />

          <div className="col-lg-4">            
            <img className="img-fluid rounded mb-4" src={imageUrl} />            
          </div>
          <div className="col-lg-3">
            <h3 className="mt-4 mb-3"><strong>{name}</strong></h3>
            <p className="card-text "><strong>Preço:</strong> R$ {price}.</p>
            <p className="card-text"><strong>Estoque: </strong> {stock}.</p>
            <p className="card-text"><strong>Categoria: </strong> {category}.</p>
            <p className="card-text"><strong>Familia: </strong> {family}.</p>
            <p className="card-text"><strong>Marca: </strong> {brand}.</p>
            <p className="card-text">
              <strong className="card-text">Quantidade: </strong>
            <input                  
                  id="quantity"
                  className="card-text rounded-pill border border-2 border-dark"
                  name="quantity"
                  placeholder="0"
                  value={quantity}
                  onChange={({ target }) => changeValue(target.value)}
                />

              <button onClick={() => setquantity(quantity + 1)} className="btn btn-primary mx-2">Adicionar</button>
              <button onClick={() => setquantity(quantity - 1)} className="btn btn-danger"> Retirar </button>              
            </p>
          </div>
          <button
            className="btn btn-lg btn-success btn-block mt-4 mb-3"
            onClick={() => addToCart()}>
              Adicionar ao carrinho
          </button>
          <div className="col-lg-3">
            {/* <h3 className="card-text mt-4 mb-3 alert alert-success" ><strong>R${this.state.quantity > 0 ? this.state.price * this.state.quantity : this.state.price} Valor total</strong></h3> */}
          </div>
          <div className="col-lg-2">
            {/* <button
                className="btn btn-lg btn-success btn-block mt-4 mb-3"
                onClick={this.addCart}>Comprar</button> */}
          </div>
        </div>
        <hr />
        <div className="row">
          <div className="col-lg-2">
            <h3><strong>Descrição:</strong></h3>
          </div>
          <div className="col-lg-10">
            <p className="card-text">{description}</p>
          </div>
        </div>
        <hr />    
        </div>
    </>
  )
}

export default ProductDetails