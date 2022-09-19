import React, { useState } from 'react'
import { useParams } from 'react-router-dom';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import LocalStorageService from '../../service/config/LocalStorageService';
import ProductService from '../../service/Product/ProductService';

const productService = new ProductService();



const ProductDetails = () => {

  const [product, setProduct] = React.useState(null);

  const [stock, setStock] = React.useState(0);
  const [name, setName] = React.useState("");
  const [description, setDescription] = React.useState("");
  const [price, setPrice] = React.useState(0.0);
  const [category, setCategory] = React.useState("");
  const [family, setFamily] = React.useState("");
  const [brand, setBrand] = React.useState("");

  const [quantity, dispatch] = React.useReducer(reducer, 0);

  const params = useParams();

  console.log(params);

  function reducer(quantity, action) {
    console.log(action);
    switch (action) {
      case 'aumentar':
        if (quantity === stock) return quantity;
        return quantity + 1;
      case 'diminuir':
        if (quantity === 0) return quantity;
        return quantity - 1;
      default:
        throw new Error();
    }
  }

  const getProduct = () => {
    productService.getById(params.id)
      .then(response => {
        console.log('RESPONSE', response)

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

  const addToCart = () => {
    if(quantity <= 0 || quantity > stock){
      toast.error("Quantidade inválida!");
      return;
    }

    let item = {};
    item.id = params.id;
    item.quantity = quantity;

    //LocalStorageService.removerItem(item);
    LocalStorageService.addToCart(item);
    toast.success('Adicionado com sucesso!');
  }

  return (
    <>
        <div className="container">
          <div className="row">
          <h1 className="mt-4 mb-3">Instrumento</h1>
          <hr />         

          <ToastContainer />

          <div className="col-lg-4">
            <img className="img-fluid rounded mb-4" src="https://img.elo7.com.br/product/zoom/264B62C/kit-placas-instrumentos-musicais-decoracao.jpg" alt="" />
          </div>
          <div className="col-lg-3">
            <h3 className="mt-4 mb-3"><strong>{name}</strong></h3>
            <p className="card-text "><strong>Preço:</strong> R$ {price}.</p>
            <p className="card-text"><strong>Estoque: </strong> {stock}.</p>
            <p className="card-text"><strong>Categoria: </strong> {category}.</p>
            <p className="card-text"><strong>Familia: </strong> {family}.</p>
            <p className="card-text"><strong>Marca: </strong> {brand}.</p>
            <p className="card-text">
              <strong>Quantidade:</strong>
              <button onClick={() => dispatch('aumentar')} className="btn btn-primary mx-2">Adicionar</button>
              <button onClick={() => dispatch('diminuir')} className="btn btn-danger"> Retirar </button>
              <p>{quantity}</p>
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