
import React, { useEffect, useState } from "react";
import ProductService from "../../service/Product/ProductService";
import { ToastContainer, toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { Link, useNavigate } from "react-router-dom";

const productService = new ProductService;

const Products = () => {

  const [products, setProducts] = React.useState(null);
  let [search, setSearch] = React.useState("");

  const navigate = useNavigate();

  const getProducts = () =>{
    productService.getAllProducts(search)
    .then( response => {

      setProducts(response.data.entities);

    }).catch( error => {
      console.error("Erro", error);
    }) 
  }

  React.useEffect( () => {       
    getProducts();
    console.log(search)
  }, [search])
  
  
  return (
    <>
    <div className="form-group">
        <input
          type="text"
          className="form-control col-6"
          id="search"
          placeholder="busca..."
          name="search"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />
    </div>
     <div className="card p-5 d-flex flex-row justify-content-center flex-wrap">
      <ToastContainer />

      <div className="container">
      </div>
        {products?.length === 0 && <h1>Sem produtos</h1>}
        {products?.map((product) => (
          <div key={product.id} className="card m-3">
            <div className="card-body" >
              <ul className="list-group list-group-flush">
                <li className="list-group-item">Nome: {product.name}</li>
                <li className="list-group-item">Preço: R$ {product.price}</li>
                <li className="list-group-item">Estoque: {product.stock}</li>
                
              </ul>
            </div>

            <div className="card-footer d-flex justify-content-center">
              {/* <button
                type="button"
                className="btn btn-danger rainbow-bg"
                onClick={e => deleteProduct(product.id)}
              >
                Excluir
              </button> */}

              <Link className="btn btn-primary rainbow-bg mx-2" to={`/products/details/${product.id}`} key={product.id} >
              Detalhes
            </Link>

            </div>
          </div>
        ))}
      </div>
      <a href="/user_home" className="btn btn-outline-secondary mb-2 " >Voltar</a>
    
    </>
  )
}

export default Products