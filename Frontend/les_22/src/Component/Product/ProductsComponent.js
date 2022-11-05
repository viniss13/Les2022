
import React from 'react'

const ProductsComponent = (props) => {
  console.log("COMPONENT PRODUCTS", props)
  return (
    <>
     {/* <div className="card p-5 d-flex flex-row justify-content-center flex-wrap">
      <ToastContainer />

      <div className="container">
      </div>
        {products?.length === 0 && <h1>Sem produtos</h1>}
        {products?.map((product) => (
          <div key={product.id} className="card m-3">
            {console.log('PRODUCTS DENTRO DO MAP', products)}
            <div className="card-body" >
              <ul className="list-group list-group-flush">
                <li className="list-group-item">Nome: {product.name}</li>
                <li className="list-group-item">Preço: R$ {product.price}</li>
                <li className="list-group-item">Estoque: {product.stock}</li>
                
              </ul>
            </div>

            <div className="card-footer d-flex justify-content-center">
      
              <Link className="btn btn-danger rainbow-bg mx-2" to={`details/${product.id}`} key={product.id} >
              Detalhes
            </Link>

            </div>
          </div>
        ))} */}
      </div>
      <a href="/user_home" className="btn btn-outline-secondary mb-2 " >Voltar</a>
    </>  
)
}

export default ProductsComponent